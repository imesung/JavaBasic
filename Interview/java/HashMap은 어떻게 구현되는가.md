## HashMap은 어떻게 구현되는가?

해시에 대해서 다시 한번 살펴보자

해시 테이블은 key와 value 쌍으로 이루어진 구조로서 key와 value는 매핑되어 있어 접근 시 **O(1)**의 아주 빠른 속도를 가진다.


## 
**직접 주소 테이블**

직접 주소 테이블은 그저 배열이라고 볼 수 있다. key 또한 숫자로만 표현할 수 있고, key는 아무런 연산없이 바로 인덱스로 사용된다.

이로 인해, 매우 빠르고 구현이 간단하다는 장점을 가지고 있으나, key의 값이 배열의 크기 보다 크게되면 사용할 수 없고 사용할 수 있다고 해도 잉여 공간이 많이 생긴다는 단점이 발생한다.


## 
**해시 테이블**

해시 테이블은 **해시함수**를 통해 계산을 해서 인덱스를 얻는 것이다. 이는 해시 테이블의 공간을 좀 더 효율적으로 사용할 수 있고, 해시의 key를 숫자 뿐만 아니라 다른 타입도 사용 가능하다.

그러나 해시함수를 통해 얻은 인덱스는 중복이 발생할 수 있는데, 이를 **해시충돌**이라 칭한다.


##
**해시충돌 해결기법**

Open Addressing과 Separate Chaining 기법이 있는데, Java HashMap에서는 Separate Chaining 기법을 사용한다.

Separate Chaining 기법의 특징은 다음과 같다.

- HashMap의 내부 클래스인 Node 클래스는 Chaining을 구현하기 위한 **단일 연결리스트 노드**이다.
- 해시값과 키값을 가지고 있으며, 단일연결리스트이기 때문에 다음 노드를 가리키는 next만을 가지고 있다.
- 해당 Node 클래스는 Object의 hashCode()를 재정의 한다.

~~~java
public final int hashCode() {
  return Objects.hashCode(key) ^ Objects.hashCode(value);
}
~~~

​		위 코드처럼 키와 값의 해시코드를 XOR하여 새로운 해시코드를 생성한다.

- 또한, 해당 Node 클래스는 Object의 equals()도 재정의한다.

  ~~~java
  public final boolean equals(Object o){
  	if(o == this)
  		return true;
  	if(o instanceof Map.Entry){
  		Map.Entry<?,?> e = (Map.Entry<?,?>)o;
  		if(Objects.equals(key, e.getKey()) && Objects.equals(value, e.getValue()))
  			return true;
  	}
  	return false;
  }
  ~~~

  즉, 해시코드가 같거나 키와 값이 모두 같을 경우에만 true를 반환하는 것이다.


##
**해시 함수**

HashMap이 해시 테이블을 구현한 것 만큼, 해시 함수도 구현하고 있다.

Java 8부터 구현이 더욱 간단해졌는데 그 이유는 HashMap 내부적으로 트리구조가 생겼기 때문이다.

~~~java
static final int hash(Object key){	//index 추출
	int h;
	return (key == null)? 0 : (h = key.hashCode()) ^ (h >>> 16);
}
~~~


##
**put 메소드**

put 메소드는 Map 인터페이스에서 정의된 메소드를 HashMap에서 구현한 것이다.

~~~java
public V put(K key, V value){
	return putVal(hash(key), key, value, false, true);
}

final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
               boolean evict) {
    Node<K,V>[] tab; Node<K,V> p; int n, i;
  	//1.
    if ((tab = table) == null || (n = tab.length) == 0)
        n = (tab = resize()).length;
  	//2.
    if ((p = tab[i = (n - 1) & hash]) == null)
        tab[i] = newNode(hash, key, value, null);
    //3.
    else {
        Node<K,V> e; K k;
      //3-1
        if (p.hash == hash && ((k = p.key) == key || (key != null && key.equals(k))))
            e = p;
        //3-2
        else if (p instanceof TreeNode)
            e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
        //3-3
        else {
            for (int binCount = 0; ; ++binCount) {
                if ((e = p.next) == null) {
                    p.next = newNode(hash, key, value, null);
                  //3-4
                    if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                        treeifyBin(tab, hash);
                    break;
                }
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    break;
                p = e;
            }
        }
        if (e != null) { // existing mapping for key
            V oldValue = e.value;
            if (!onlyIfAbsent || oldValue == null)
                e.value = value;
            afterNodeAccess(e);
            return oldValue;
        }
    }
    ++modCount;
    if (++size > threshold)
        resize();
  	//4.
    afterNodeInsertion(evict);
    return null;
}
~~~

소스를 살펴보자

1. 먼저 테이블(버킷에 저장되는)이 존재하는지와 테이블의 크기를 검사한다.
2. 해시함수를 사용하여 인덱스((n - 1) & hash)를 계산하고 해당 인덱스의 버킷이 비어있다면 새로운 노드를 생성한다.
3. 여기는 모두 해시충돌일 때이다.(e는 값이 바뀔대상, p는 현재 선택된 노드)
   1. 만일 이미 저장되어있는 노드 p가 지금 저장하는 노드와 같은 경우(해시값과 키가 같거나 equals()의 결과가 true인 경우) 로컬변수 e는 p가 되며 이후의 코드에서 변경된다.
   2. p가 TreeNode의 객체라면 putTreeVal 메소드를 통해 TreeNode로서 값을 저장한다.
   3. 이도 아니라면, 리스트로 구현되어 있는 모든 p노드를 따라서 마지막 노드로 이동하고 그 자리에 추가하거나 순환 중에 같은 키를 가진 노드를 발견할 경우 해당 노드의 값을 변경한다.(break로 반복문을 탈출)
   4. 그런데 이때 리스트의 노드의 수를 새는데(binCount 변수), 한 리스트의 노드 수가 일정 수(TREEIFY_THRESHOLD 상수)이상 넘어갈 경우, 각 버킷이 리스트로 이루어져 있는 해시 테이블을 트리화시킨다. 즉, 리스트로 구현된 각 버킷들이 트리로 변하는 것이다.(Node -> TreeNode) **처음에는 리스트로 관리하다가 해시충돌이 너무 많이 일어나 리스트가 커질 경우 버킷을 트리로 변환하는 것이다.**
4. 추가가 마무리된 이후에는 재정의를 통해 사용할 수 있는 afterNodeAccess()나 afterNodeInsertion()을 호출한다. 그리고 그 전에 현재 노드의 수를 확인하여 일정 수준 이상이 되면 해시 테이블의 크기를 다시 설정한다.

##
**참고**

http://kwseo.github.io/2015/11/09/hash-map/
