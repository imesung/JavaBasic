## Collection

> Collection 객체는 여러 원소들을 담을 수 있는 자료구조를 뜻한다.

**자료구조의 유형**

- Java에서의 자료구조 유형을 살펴보자.
  - List : 순서가 있는 목록
  - Set : 순서가 중요하지 않는 목록
  - Queue : FIFO
  - Map : Key & Value 형태로 되어 있는 목록
- 자료구조의 유형과 배열은 목록을 저장하는 개념으로는 동일하지만 큰 차이를 가지고 있다.
  - 배열 : 정적 메모리 할당
  - 자료구조 : 동적 메모리 할당



**Collection의 Tree 구조**

![image](https://user-images.githubusercontent.com/40616436/78684629-a8287600-792b-11ea-8780-067bdb439f4b.png)

출처 : https://www.crocus.co.kr/1553



**Collection 인터페이스의 종류**

~~~java
public interface Collection<E> extends Iterable<E> {
}
~~~

List 인터페이스

- 다른 Collection 인터페이스와는 다르게 배열처럼 순서가 있다는 것이다.
- ArrayList, LinkedList, Vector, Stack이 있다.
- ArrayList
  - JDK 1.2부터 제공되는 클래스로서, 내부적으로 배열을 이용하여 요소를 저장한다.
- LinkedList
  - JDK 1.2부터 제공되는 클래스로서, 내부적으로 연결 리스트를 이용하여 요소를 저장한다.
- Vector
  - JDK 1.0부터 제공되는 클래스로서, ArrayList와 같은 동작을 하나 thread safe한다는 차이가 있다.



Map 인터페이스

- Key와 Value로 구성되어 있는 형태로서, Key는 고유값이다.
- 값은 Map에 중복되어도 상관 없고, 데이터 추가 순서는 중요치 않다.
- HashMap, TreeMap이 있다.
- HashMap
  - 데이터를 저장하기 위해 해시 테이블을 사용하는 것이다.
  - 데이터를 저장할 때 저장하려는 요소 외 Key 값에 해당하는 객체를 지정한다.
- TreeMap
  - Map 인터페이스와 SortedMap인터페이스를 구현한 클래스로서, 정렬 기능이 지원되는 형태이다.
  - 정렬이 지원되여 데이터를 얻어오는 속도가 빠르다.
  - HashMap과 달리 오름차순으로 Key 값을 저장한다.



Set 인터페이스

- 요소의 저장순서를 고려하지 않고, 중복 저장을 허용하지 않는다.

- HashSet과 TreeSet이 있다.

- HashSet

  - JDK 1.2부터 제공되고, **해시 알고리즘을 사용하여 검색 속도가 매우 빠르다.**

  - 아래 그림은 해시 알고리즘을 그림으로 간략히 표현한 것이다.

    <img src="https://user-images.githubusercontent.com/40616436/78690292-37d12300-7932-11ea-94eb-8891b3a2c5d9.png" alt="image" style="zoom:50%;" />

    출처 : https://github.com/imesung/JavaBasic/issues/new

  - HashSet 클래스는 내부적으로 HashMap 인스턴스를 이용하여 요소를 저장한다.

  - 요소의 저장순서를 고려하지 않는데, 순서를 원한다면 JDK 1.4부터 제공된 LinkedHashSet을 사용할 수 있다.

- TreeSet

  - 데이터가 정렬된 상태로 저장되는 이진 검색 트리의 형태로 요소를 저장한다.
  - 이진 검색 트리는 데이터를 추가하거나 제거하는 등의 기본 동작 시간이 매우 빠르다.
  - JDK 1.2부터 제공되는 TreeSet 클래스는 NavigableSet 인터페이스를 기존의 이진 검색 트리의 성능을 향상시킨 Red-Black tree로 구현한다.



**Collection의 검색 속도**

때에 따라서 속도의 차이는 다르게 나타난다.

1. **객체 안에 있는 임의의 데이터가 존재하는 지 확인할 때,**
   - 해시 알고리즘을 통해 검색을 진행하므로 **HashSet과 LinkedHashSet**의 속도가 가장 빠르다.
2. **특정 데이터를 검색하여 가져올 때,**
   - **ArrayList의 get()**를 통해 데이터를 가져올 때 속도가 가장 빠르다.



**Collection에 대해서 더 알아보자..**



