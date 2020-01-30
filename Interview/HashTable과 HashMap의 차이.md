## HashMap과 HashTable 차이

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
