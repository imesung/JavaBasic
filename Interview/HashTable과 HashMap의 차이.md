## HashMap과 HashTable 차이

HashMap과 HashTable은 Key와 Value쌍을 쓰고, Map 인터페이스를 Implements한다.

이 뿐만 아니라 Java 내에서 Key와 Value 쌍을 지원하는 대부분 클래스는 Map을 구현하게 된다.

Map을 구현하는 방식 즉, 알고리즘도 다양한데 TreeMap의 경우 tree 알고리즘을 사용하여 구현하는 반면, HashMap과 HashTable은 **hash알고리즘**을 사용하여 구현했다.

그렇다면, 같은 알고리즘을 사용하는 HashMap과 HashTable은 무슨 차이가 있는 것인가?!



**차이점**

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



**ConCurrentHashMap**

- HashMap을 Thread-safe하기 위해 만든 클래스가 ConcurrentHashMap이다. 하지만 HasMap과는 다르게 Key와 Value에 Null을 허용하지 않는다. 또한, putIfAbsent()라는 메소드를 가지고 있다.



**소스를 통해 하나씩 살펴보자**



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



**HashMap은 주요 메소드에 synchronizedr가 없다. 또한 HashTable과 다르게 Key와 Value에 Null을 허용한다.**

~~~java

~~~

