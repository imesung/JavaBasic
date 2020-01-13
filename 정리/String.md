## String 사용? 미사용?

### String은 메모리에 어떻게 저장되는가? 객체의 값은 변경가능 한가?

- String 객체 대부분은 원시 타입으로 취급하므로 new 키워드를 사용할 필요가 없다.

- Java에서 String은 특별한 참조 자료형이다. 다른 객체들과 마찬가지로 new 생성자를 이용해서 인스턴스를 만드록 heap영역에 올라가지만, 다른 참조형과는 다르게 **한번 객체가 생성되면 해당 값은 변하지가 않는다.**

  ```java
  String str = new String("cat");
  str = str+str;
  //첫번째 str은 cat이라는 문자열을 가지고 있다가, str+str 연산이 실행되면 str은 새로운 객체를 생성하여 'catcat'이라는 문자열을 갖는다.
  ```

- 이 처럼 String 연산은 계속적으로 객체를 만들어내기 때문에 비효율적이다.

- 이 를 해결하기 위해 나타난 것이 **String Constant pool**이다.



**String Constant pool**

반복적으로 객체 생성되는 것을 방지하기 위한 새로운 메모리 영역

```java
String str1 = "car";
String str2 = "car";
//String Constant pool에 의해 str1과 str2는 같은 인스턴스를 참조하고 있다.
//car는 String Constant pool 메모리 영역에 한 부분만 차지하기 때문이다.
```



**intern**(인터닝이란)

```java
String str1 = new String("ball");
String str2 = new String("ball");
String str3 = "ball";

//main
System.out.println(str1 == str1.intern());
System.out.println(str3 == str1.intern());
```

- `intern()`메소드를 사용하면 Heap영역에 있는 인스턴스를 String Constant pool영역으로 이동한다.
- 위 결과를 다시한번 살펴보면,
-  `System.out.println(str1 == str1.intern());`는 `false`가 나타난다.
  - 이유는 `str1`은 이제 Heap영역이 아닌 String Constatn pool 영역에 있으므로 같은 인스턴스를 참조하지 않는다.
-  `System.out.println(str3 == str1.intern());`는 `true`가 나타난다.
  - 위에서 본 바와 같이 `str1.intern()`으로 인해 `str1`은 String Constatn pool로 이동하였으므로 `str3`과 `str1`은 같은 인스턴스를 참조하는 것이다.