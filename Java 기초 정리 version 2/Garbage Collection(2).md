## Garbage Collector(2)

**Stop-the-world**

Stop-the-world란, GC를 실행하기 위해 JVM이 애플리케이션 실행을 멈추는 것이다. Stop-the-world가 발생하면 GC를 실행하는 스레드를 제외한 나머지 스레드는 모두 작업을 멈춘다. 어떤 GC 알고리즘을 사용하더라도 Stop-the-world는 발생하게 되고, **GC 튜닝이란 Stop-the-world의 시간을 줄이는 것이다.**



**Garbage Collector의 가설**

Garbage Collector(이하 GC)는 Java에서 개발자가 메모리를 명시적으로 해제하지 않고 GC를 통해 더 이상 사용하지 않는 객체를 지우는 작업을 하는 것을 말한다. 이런 GC의 가설(전제조건)이 존재하는데,

- 대부분의 객체는 금방 접근 불가능 상태(unreachable)가 된다.
- 오래된 객체에서 젊은 객체로의 참조는 아주 적게 존재한다.

이러한 가설의 장점을 최대한 살리기 위해 HotSpot VM에서는 크게 **2개로 물리적 공간을 나누었다.** 바로 Young 영역과 Old 영역이다.



**Young 영역과 Old 영역**

**Young 영역**

새롭게 생성한 객체의 대부분이 여기에 위치한다. 대부분의 객체가 금방 접근 불가능 상태가 되기 때문에 매우 많은 객체가 Young 영역에 생성되었다가 사라진다. 그리고 이 영역에서 객체가 사라질 때 **Minor GC가 발생한다고 말한다.**

**Old 영역**

접근 불가능 상태로 되지 않아 Young 영역에서 살아남은 객체가 여기로 복사된다. 대부분 Young 영역보다 크게 할당하며, 크기가 큰 만큼 Young 영역보다 GC는 적게 발생한다. **이 영역에서 객체가 사라질 때 Major GC가 발생한다고 말한다.**



**그렇다면 Old 영역에 있는 객체가 Young 영역의 객체를 참조하는 경우가 있을 대에는 어떻게 처리될까?**

이런 경우를 대비해서 Old 영역에는 512byte의 덩어리로 되어 있는 카드 테이블이 존재한다.

카드 테이블에는 Old 영역에 있는 객체가 Young 영역의 객체를 참조할 때마다 정보가 표시된다. 그로 인해, Young 영역의 GC를 실행할 때에는 Old 영역에 있는 모든 객체의 참조를 확인하지 않고 이 카드 테이블만 뒤져서 GC 대상인지 식별하는 것이다.

해당 카드 테이블은 write barrier를 사용하여 관리한다. write barrier는 Minor GC를 빠르게 할 수 있도록 하는 장치이다. **write barriers 때문에 약간의 오버헤드는 발생하지만 전바적인 GC시간은 줄어들게 된다.**



**Young 영역의 구성**

객체가 제일 먼저 생성되는 Young 영역부터 알아보면 Young 영역은 3개의 영역으로 나뉜다.

- Eden 영역
- Survivor 영역(2개)

각 영역의 처리 절차를 순서에 따라 기술해보자.

1. 새로 생성한 대부분의 객체는 Eden 영역에 위치한다.
2. Eden 영역에서 GC가 한 번 발생한 후 살아남은 객체는 Survivor 영역 중 하나로 이동된다.
3. Eden 영역에서 GC가 발생하면 이미 살아남은 객체가 존재하는 Survivor 영역으로 객체가 계속 쌓인다.
4. 하나의 Survivor 영역이 가득차게되면 그 중에서 살아남은 객체를 다른 Survivor 영역으로 이동한다. 그 후 가득 찬 Survivor 영역은 아무 데이터도 없는 상태로 된다.
5. 이 과정을 반복하다가 계속해서 살아남아 있는 객체는 Old 영역으로 이동하게 된다.

이 절차를 확인해보면 알겠지만 Survivor 영역 중 하나는 반드시 비어있는 상태로 남아있어야 한다. 만약 두 Survivor 영역에 모두 데이터가 존재하거나, 두 영역 모두 사용량이 0이라면 매우 비정상적인 것이다.



이런 GC 알고리즘으로 인해 보다 빠른 메모리 할당을 위해서 HotSpot VM에서는 두 가지 기술을 사용한다.

- bump-the-pointer
- TLABs(Thread-Local Allocation Buffers)

bump-the-pointer는 Eden 영역에 할당된 마지막 객체를 추적한다. 마지막 객체는 Eden 영역의 맨 위에 있다. 그리고 그 다음에 생성되는 객체가 있으면, 해당 객체의 크기가 Eden 영역에 넣기 적당한지만 확인한다. 만약 해당 객체의 크기가 적당하면 Eden 영역에 넣게 되고, 새로 생성된 객체가 Eden 영역 맨 위에 있게 된다. 결과적으로, **새로운 객체를 생성할 때 마지막에 추가된 객체만 점검하면 되므로 매우 빠르게 메모리 할당이 이루어진다.**

하지만, 멀티 스레드 환경을 고려하면 이야기가 달라지는데 Thread-Safe하기 위해서 만약 여러 스레드에서 사용하는 객체를 Eden 영역에 저장하려면 락이 발생할 수 밖에 없고, lock-contention 때문에 성능은 매우 떨어지게 될 것이다. **HotSpot VM에서 이를 해결한 것이 TLABs이다.**



TLABs는 각각의 스레드가 각각의 몫에 해당하는 Eden 영역의 작은 덩어리를 가질 수 있도록 하는 것이다. 각 스레드에는 자기가 갖고 있는 TLAB에만 접근할 수 있기 때문에 bump-the-pointer라는 기술을 사용하더라도 아무런 락 없이 메모리 할당이 가능하다.



**Old 영역에 대한 GC**

GC방식은 JDK 7 기준으로 5가지 방식이 있다.

- Serial GC
- Parallel GC
- Parallel Old GC(Parallel Compacting GC)
- Concurrent Mark & Sweep GC(이하 CMS)
- G1(Garbage Firse) GC



**Serial GC**

Young 영역에서의 GC는 앞서 설명한 Minor GC를 사용하고 있다.

Old 영역의 GC는 mark-sweep-compact 라는 알고리즘을 사용한다.

- Mark : Old 영역에 살아있는 객체를 식별한다.
- Sweep : 힙의 앞 부분부터 확인하여 살아있는 것만 남긴다.
- Compact : 각 객체들이 연속되게 쌓이도록 힙의 가장 앞 부분부터 채워서 객체가 존재하는 부분과 객체가 없는 부분으로 나눈다.

Serial GC는 적은 메모리와 CPU 코어 개수가 적을 때 적합한 방식이다.



**Parallel GC**

Parallel GC는 Serial GC와 기본적인 알고리즘은 같다. 그러나 Serial GC는 GC를 처리하는 스레드가 하나인 것에 비해, Parallel GC는 GC를 처리하는 스레드가 여러 개이다. Parallel GC는 메모리가 충분하고 코어의 개수가 많을 때 유리하다. Parallel GC는 Throughput GC라고도 부른다.



**Parallel Old GC**

Parallel Old GC는 JDK 5~6 부터 제공한 GC 방식이다. 이 방식은 Mark-Summary-Compaction 단계를 거친다.

Summary : 앞서 GC를 수행한 영역에 대해서 별도로 살아 있는 객체를 식별한다.

Mark-Sweep-Compaction 알고리즘의 Sweep 단계와 다르며 약간 더 복잡한 단계를 거친다.



**Parallel Old GC**

이 방식은 **Mark-Summary-Compaction** 단계를 거친다.

**Summary**단계는 앞서 GC를 수행한 영역에 대해서 별도로 살아 있는 객체를 식별한다.



**CMS GC**(Low Latency GC)

이 방식은 **Initial Mark-Concurrent Mark-Remark-Concurrent Sweep** 단계를 거친다.

- 클래스 로더에서 가장 가까운 객체 중 살아 있는 객체만 찾는다.(**Initial Mark**)
- 방금 살아있다고 확인한 객체에서 참조하고 있는 객체들을 따라가면서 확인한다.(**Concurrent Mark**)
  - 다른 스레드가 실행 중인 상태에서 동시에 실행이 가능하다.
- Concurrent Mark 단계에서 새로 추가되거나 참조가 끊긴 객체를 확인한다(**Remark**)
- 가비지를 정리하는 작업을 실행한다.(**Concurrent Sweep**)
  - 이 작업도 다른 스레드가 실행되고 있는 상황에서 진행한다.

이 방식은 **stop-the-world**시간이 매우 짧아 모든 애플리케이션의 응답 속도가 매우 중요할 때 **CMS GC**를 사용하기 좋다.

그런데 CMS GC는 **stop-the-world** 시간이 매우 짧다는 장점에 반해 다음과 같은 단점이 존재한다.

- 다른 GC 방식보다 메모리와 CPU를 더 많이 사용한다.
- Compaction 단계가 기본적으로 제공되지 않는다.

이로 인해서, CMS GC를 사용할 때에는 신중히 검토한 후 사용해야한다. 또한, 조각난 메모리가 많아 Compaction 작업을 실행하면 다른 GC 방식의 stop-the-world 시간보다 stop-the-world 시간이 더 길기 때문에 Compaction 작업이 얼마나 자주 오랫동안 수행되는 확인해야 한다.



**G1 GC**

G1 GC는 바둑판의 각 영역에 객체를 할당하고 GC를 실행한다. 그러다가 해당 영역이 꽉 차면 다른 영역에서 객체를 할당하고 GC를 실행한다. 즉, 지금까지 설명한 Young의 세가지 영역에서 데이터가 Old 영역으로 이동하는 단계가 사라진 GC 방식이라고 이해하면 된다.





https://d2.naver.com/helloworld/1329

자바 성능과 튜닝 이야기 서적 참조



