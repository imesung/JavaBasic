## Garbage Collector(2)

**Stop-the-world**

Stop-the-world란, GC를 실행하기 위해 JVM이 애플리케이션 실행을 멈추는 것이다. Stop-the-world가 발생하면 GC를 실행하는 스레드를 제외한 나머지 스레드는 모두 작업을 멈춘다. 어떤 GC 알고리즘을 사용하더라도 Stop-the-world는 발생하게 되고, **GC 튜닝이란 Stop-the-world의 시간을 줄이는 것이다.**



**Garbage Collector의 가설**

Garbage Collector(이하 GC)는 Java에서 개발자가 메모리를 명시적으로 해제하지 않고 GC를 통해 더 이상 사용하지 않는 객체를 지우는 작업을 하는 것을 말한다. 이런 GC의 가설(전제조건)이 존재하는데,

- 대부분의 객체는 금방 접근 불가능 상태(unreachable)가 된다.
- 오래된 객체에서 젊은 객체로의 참조는 아주 적게 존재한다.

이러한 가설의 장점을 최대한 살리기 위해 HotSpot VM에서는 크게 **2개로 물리적 공간을 나누었다.** 바로 Young 영역과 Old 영역이다.



**Young 영역과 Old 영역**

Young 영역



https://d2.naver.com/helloworld/1329







