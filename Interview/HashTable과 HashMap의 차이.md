## HashMap과 HashTable 차이

HashTable과 HashMap은 Java API의 이름이다. **HashTable은 JDK1.0부터 있었고, HashMap은 Java2에서 처음 선보인 API이다.**

둘다 제공하는 기능은 같지만 **HashMap은 보조해시함수를 사용**하기 때문에 **보조해시함수를 사용하지 않는 HashTable**보다 **해시 충돌이 덜 발생**할 수 있다.

HashTable의 구현은 거의 변화가 없는 반면, HashMap은 지속적으로 개선되어 왔다. 

HashMap과 HashTable은 Key와 Value쌍을 쓰고, Map 인터페이스를 Implements한다.

이 뿐만 아니라 Java 내에서 Key와 Value 쌍을 지원하는 대부분 클래스는 Map을 구현하게 된다.

Map을 구현하는 방식 즉, 알고리즘도 다양한데 TreeMap의 경우 tree 알고리즘을 사용하여 구현하는 반면, HashMap과 HashTable은 **hash알고리즘**을 사용하여 구현했다.

그렇다면, 같은 알고리즘을 사용하는 HashMap과 HashTable은 무슨 차이가 있는 것인가?!


## 
**차이점**

- HashTable에는 hash 함수가 없는 반면, HashMap에는 hash 함수가 존재한다.

- HashTable은 동기화가 되는 반면, HashMap은 비동기화이다. 이로 인해서 단일 스레드 환경에서는 HashMap을 사용하고 멀티 스레드 환경에서는 HashTable을 사용한다.

  - HashTable은 멀티 스레드 환경에서 메소드 호출 전 스레드간의 동기화 락을 통해 동기성이 보장되므로 data의 무결성을 보장해주는 것이다.

  - HashMap의 경우 멀티 스레드 환경에서 동기성이 보장되지 않아 여러 스레드가 객체의 data를 동시에 조작할 경우 data는 깨져버리게 된다. 

  - 하지만, 주의할 것은 **동기화 락** 작업은 매우 느리기 때문에 단일 스레드 환경에서는 HashTable보다 HashMap이 훨씬 빠르다.

    - **확인 가능한지?**

  - **최근 트렌드는 멀티 스레드 환경에서 HashTable을 쓰지 않고 HashMap을 감싸서 사용한다.**

    - ~~~java
      Map map = Collections.synchronizedMap(new HashMap());
      ~~~

- HashTable은 Null Key나 Value를 허용하지 않는 반면, HashMap은 Null Key와 Value를 허용한다.

## 
**ConCurrentHashMap**

- HashMap을 Thread-safe하기 위해 만든 클래스가 ConcurrentHashMap이다. 하지만 HasMap과는 다르게 Key와 Value에 Null을 허용하지 않는다. 또한, putIfAbsent()라는 메소드를 가지고 있다.



## 

### HashMap의 해시 충돌 해결

이전 포스팅에서 해시 충돌 해결법을 살펴봤는데, HashMap의 해시 충돌 해결법은 **Separate Channing(분리연결법)** 이다.

그 이유는 일반적인 배열(개방연결법)에서 데이터를 삭제할 때 처리가 효율적이기 어려운데 **HashMap에서는 이 일이 빈번하게 일어나기 때문이다.** 또한 **개방연결법을 사용하게 되면 해시 버킷을 채운 밀도가 높아질수록 Worst Case(O(M)) 발생 빈도가 더 높아진다.**

반면, **분리연결법은 사용하면 해시충돌이 잘 발생하지 않도록 '조정'이 가능하다.**




**Java 8의 HashMap**

HashMap의 put() java7과 java8이 서로 다르게 구현되어 있지만 **결론적으로 구현 알고리즘은 동일하다.**

- Java 7에서는 해시 함수 값이 균등 분포 상태라고 하면 get() 호출에 대한 기댓값은 E(N/M)이다. 하지만 Java 8에서는 E(logN/M)을 볼수 있다.

  - 그 이유는 Java 8에서는 데이터 개수가 많아지면 분리연결법에서 **링크드 리스트 대신 트리를 사용하기 때문이다.**

- 그럼 항상 링크드리스트 대신 트리를 사용하는 것이 좋은가? 그것은 아니다.

  ~~~java
  static final int TREEIFY_THRESHOLD = 8;
  static final int UNTREEIFY_THRESHOLD.= 6; 
  ~~~

  - 만약 해시 버킷에 8개의 키-값 쌍이 모이면 **링크드 리스트를 트리로 변경** 한다.

  - 만약 해시 버킷에 6개의 키-값 쌍이 모이면 **다시 링크드 리스트로 변경** 한다.
    - 즉 하나의 해시 버킷에 할당된 키-값 쌍의 개수를 보고 결정한다.
    - 이유는 데이터가 적을 땐 링크드리스트가 성능상 좋고 많을 땐 트리가 좋기 때문이다.
      - 데이터가 많을 때 링크드리스트를 사용하게 되면 일부 해시 버킷에 데이터가 집중될 수 있기 때문인다.

- Java 8 HashMap에서는 Entry 클래스 대신 Node 클래스를 사용한다.

  - 링크드 리스트 대신 트리를 사용할 수 있도록 하위 클래스인 TreeNode가 있기 때문에 Node 클래스를 사용한다.

- Java8 에서 사용하는 트리는 **Red-Black Tree** 인데, Java Collection Framwork의 TreeMap가 구현이 거의 같다.

  - 트리 순회 시 대소 판단은 해시 함수 값이 되는데, 보통 해시 값을 대소 판단 기준을 사용하게 되면 Total Ordering문제가 생기지만 Java 8 HashMap에서는 이를 **tieBreakOrder()**로 해결한다.

  ~~~java
  static int tieBreakOrder(Object a, Object b) {
    // TreeNode에서 어떤 두 키의comparator 값이 같다면 서로 동등하게 취급된다.
    // 그런데 어떤 두 개의 키의 hash 값이 서로 같아도 이 둘은 서로 동등하지 
    // 않을 수 있다. 따라서 어떤 두 개의 키에 대한 해시 함수 값이 같을 경우, 
    // 임의로 대소 관계를 지정할 필요가 있는 경우가 있다. 
  }
  ~~~



**해시 버킷 동적 확장**

HashMap은 키-값 쌍 데이터 개수가 일정 개수 이상이 되면 해시 버킷의 개수를 **두 배**로 늘린다. 이렇게 해시 버킷의 개수를 늘리면 값도 작아져서 해시 출돌을 방지할 수 있다.

해시 버킷의 기본값은 16이고 데이터의 개수가 임계점(load factor * 현재 해시 버킷 개수)에 이를 때마다 두배씩 증가한다. **버킷의 최대개수는 2^30개이다.**

버킷의 개수가 증가할 때마다 모든 키-값의 데이터를 읽어 새로운 분리연결법을 구성해야하는 문제가 발생하는데 이때는, **HashMap 생성자의 인자로 해시 버킷 개수를 지정할 수 있는데, 저장될 데이터의 개수가 어느정도 예측 가능한 경우에는 이를 지정하여 불필요한 분리연결법을 재구성하지 않을 수 있다.**

그런데 이렇게 해시 버킷 크기를 두배로 확장하는 것에는 결정적인 문제가 있는데, **해시 버킷의 개수 M이 2^a의 형태이기 때문에 int index = X.hashCode() % M을 계산할 때 X.hashCode()의 값이 2의 배수가 나타날 시 index 값이 동일하게 나타날 수 있다.**

이로 인해 **보조 해시 함수**가 필요하다.



**보조 해시 함수**

int index = X.hashCode() % M를 계산할 때 M값이 소수일 때 index 값 분포가 가장 균등하게 나타난다.

즉, 보조 해시 함수를 사용하여 M값을 소수로 만들도록 하는 것이다.

보조 해시 함수는 JDK1.4에서 처음 등장했고, Java 5~7과 Java 8과 다른 방식을 사용한다.

~~~java
//Java 5~7 보조 해시 함수
final int hash(Object k) {  
  // Java 7부터는 JRE를 실행할 때, 데이터 개수가 일정 이상이면
  // String 객체에 대해서 JVM에서 제공하는 별도의 옵션으로
  // 해시 함수를 사용하도록 할 수 있다.
  // 만약 이 옵션을 사용하지 않으면 hashSeed의 값은 0이다.
  int h = hashSeed;
  if (0 != h && k instanceof String) {
    return sun.misc.Hashing.stringHash32((String) k);
  }
  h ^= k.hashCode();
  // 해시 버킷의 개수가 2a이기 때문에 해시 값의 a비트 값만을 
  // 해시 버킷의 인덱스로 사용한다. 따라서 상위 비트의 값이 
  // 해시 버킷의 인덱스 값을 결정할 때 반영될 수 있도록
  // shift 연산과 XOR 연산을 사용하여, 원래의 해시 값이 a비트 내에서 
  // 최대한 값이 겹치지 않고 구별되게 한다.
  h ^= (h >>> 20) ^ (h >>> 12);
  return h ^ (h >>> 7) ^ (h >>> 4);
}

//Java 8 보조 해시 함수
static final int hash(Object key) {
  int h; return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16); 
}
~~~

Java 8이 더욱 단순해진 이유는,

- Java 8에서는 해시 충돌이 많이 발생하면 링크드리스트 대신 트리를 사용하고 있다.
- **최근 해시 함수는 균등 분포가 잘 되게 만들어지는 경향이 있어 Java 7의 보조 해시 함수의 역할이 크지가 않다.**

개념상 해시 버킷 인덱스를 계산할 때는 int index = X.hashCode() % M 대신 1 << a-1 과 비트 논리곱(AND, &) 연산을 사용하면 수행이 훨씬 빠르다.



## 

### **소스를 통해 하나씩 살펴보자**

**HashTable은 put과 get 같은 주요 메소드에 synchronized 키워드가 선언되어 있다. 또한, Key와 Value에는 Null을 허용하지 않는다.**

~~~java
//put
public synchronized V put(K key, V value) {	//synchronized 키워드로 동기화
  if (value == null) {
    throw new NullPointerException();	//value값 Null허용하지 않음
  } else {
    Hashtable.Entry<?, ?>[] tab = this.table;
    int hash = key.hashCode();	//해당 키 hashCode 추출(key가 null이면 여기서 NullException 발생)
    int index = (hash & 2147483647) % tab.length;	//hashCode의 값이 int 값의 최대값을 넘는지 확인 후 HashTable 길이만큼 나눔

    //HashTable에 적재
    for(Hashtable.Entry entry = tab[index]; entry != null; entry = entry.next) {
      if (entry.hash == hash && entry.key.equals(key)) {	//키가 같으면 예전 값(같은 key에 있던 value)을 return
        V old = entry.value;
        entry.value = value;
        return old;
      }
    }
	
    //새로운 key와 value put
    this.addEntry(hash, key, value, index);
    return null;
  }
}

//get
public synchronized V get(Object key) {	//synchronized 키워드로 동기화 가능
  Hashtable.Entry<?, ?>[] tab = this.table;
  int hash = key.hashCode();
  int index = (hash & 2147483647) % tab.length;

  //가져오려는 index값 확인하여 hash와 key값 동일할 시 return 
  for(Hashtable.Entry e = tab[index]; e != null; e = e.next) {
    if (e.hash == hash && e.key.equals(key)) {
      return e.value;
    }
  }

  return null;
}
~~~


## 
**HashMap은 주요 메소드에 synchronized가 없다. 또한 HashTable과 다르게 Key와 Value에 Null을 허용한다.**

~~~java
//put
//hashtable과 달리 synchronzied가 없는 것을 확인할 수 있다.
public V put(K key, V value) 
  return this.putVal(hash(key), key, value, false, true);
}

//hash(key)
//key값이 null일 시 hash값은 0으로 초기화 한다.
static final int hash(Object key) {
  int h;
  return key == null ? 0 : (h = key.hashCode()) ^ h >>> 16;
}

//null 허용
//HashMap.Node<K, V> 객체를 활용
static class Node<K, V> implements Entry<K, V> {
  ...

  //Key와 Value는 Null 체크를 하지 않고 HashMap의 Node 객체에 담게 된다.
  Node(int hash, K key, V value, HashMap.Node<K, V> next) {
    this.hash = hash;
    this.key = key;
    this.value = value;
    this.next = next;
  }
  
  ...
}
~~~


## 
**ConcurrentHashMap은 HashMap을 Thread-safe하게 만든 클래스인데, HashMap과는 다르게 Key와 Value에 Null을 허용하지 않는다. 또한, putIfAbsent 메소드를 가지고 있다.**

~~~java
public static void main(String[] args) {

  ConcurrentHashMap<String, Integer> chm = new ConcurrentHashMap<>();

  chm.put("key", 0);
  
  /*
   * putIfAbsent 메소드는 키값이 존재하면 기존의 값을 반환하고
   * 없다면 입력한 값을 저장한 뒤 반환한다.
   * 따라서 아래의 코드는 이미 key라는 값에 0이라는 값이 있으므로
   * key 값은 0을 반환한다.
   */
  chm.putIfAbsent("key", 1);

  /*
   * 아래 코드는 key2의 값이 없기 때문에 -1을 저장하고 반환한다.
   */
  chm.putIfAbsent("key2", -1);

  for( String s : chm.keySet() ){
    System.out.println(chm.get(s)); // print -1, 0
  }
}

~~~


## 
**자주 사용하는 다른 메소드**

- clear()
  - 해당 컬렉션 데이터를 모두 초기화한다.
- containsKey(key)
  - 해당 컬렉션에 입력 받은 key를 가지고 있는지 체크한다.
- remove(key)
  - 해당 컬렉션에 입력 받은 key의 데이터를 제거한다.
- isEmpty()
  - 해당 컬렉션이 비어 있는지 체크한다.
- size()
  - 해당 컬렉션의 엔트리 또는 세그먼트 사이즈를 반환한다.


## 
### Multi Threads

우리는 위 세 종류의 같은 점과 다른 점 모두를 확인했는데, 중점적으로 본 것은 **동기화**부분이다.

이제 실제로 동기화를 위해선 어떤 컬렉션을 사용해야하고, 왜 그래야하는지 코드와 함께 확인해보자!

~~~java
public class MultiThreadsTest {

    private static final int MAX_THREADS = 10;

    private static Hashtable<String, Integer> hashTable = new Hashtable<>();
    private static HashMap<String, Integer> hashMap = new HashMap<>();
    private static HashMap<String, Integer> hashMapSync = new HashMap<>();

    //hashMapSync2는 Collections의 SynchronizedMap을 활용하여 HashMap을 담고, put 혹은 get 등을 할 때 synchronized 키워드를 사용한다.
    //Collections의 SynchronizedMap 클래는 Map 인터페이스를 구현하고 있으므로, Map으로 참조가 가능하다.
    private static Map<String, Integer> hashMapSync2 = Collections.synchronizedMap(new HashMap<String, Integer>());
    private static ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {

        ExecutorService es = Executors.newFixedThreadPool(MAX_THREADS); //10개의 스레드 풀 사

        for( int j = 0 ; j < MAX_THREADS; j++ ){
            es.execute(new Runnable() {
                @Override
                public void run() {
                    for( int i = 0; i < 1000; i++ ){    //각 스레드 1000번 반

                        String key = String.valueOf(i);

                        hashTable.put(key, i);
                        hashMap.put(key, i);
                        concurrentHashMap.put(key, i);
                        hashMapSync2.put(key, i);

                        //일반 hashMap인데 put하는 순간에 synchronized로 접
                        synchronized (hashMapSync) {
                            hashMapSync.put(key, i);
                        }
                    }
                }
            });
        }

        es.shutdown();
        try {
            //지정 시간동안 대기하며 모든 작업이 중지되었는지 확인한다.
            es.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Hashtable size is "+ hashTable.size());
        System.out.println("HashMap size is "+ hashMap.size());
        System.out.println("ConcurrentHashMap size is "+ concurrentHashMap.size());
        System.out.println("HashMap(synchronized) size is "+ hashMapSync.size());
        System.out.println("synchronizedMap size is "+ hashMapSync2.size());

		/*
		for( String s : hm.keySet() ){
			System.out.println("["+s+"] " + hm.get(s));
		}
		*/
    }
}
~~~


## 
**실행 결과**

![image](https://user-images.githubusercontent.com/40616436/73461819-1f86da00-43be-11ea-8daf-8028158d4d25.png)

결과에서 보시다시피 HashMap은 동기화처리가 되어 있지 않기 때문에 총 엔트리 사이즈가 비정상적으로 나타나는 것을 확인할 수 있다.

**결과적으로, 동기화 이슈가 있다면 일반적인 HashMap을 쓰지 말거나, synchronized 블록을 선언하여 동기성을 유지하도록 해주어야 한다.**
