## String 사용? 미사용?

### String은 메모리에 어떻게 저장되는가? 객체의 값은 변경가능 한가?

- String 객체 대부분은 원시 타입으로 취급하므로 new 키워드를 사용할 필요가 없다.

- Java에서 String은 특별한 참조 자료형이다. 다른 객체들과 마찬가지로 new 생성자를 이용해서 인스턴스를 만들고 heap영역에 올라가지만, 다른 참조형과는 다르게 **한번 객체가 생성되면 해당 값은 변하지가 않는다.**

  ```java
  String str = new String("cat");
  str = str+str;
  //1. str은 cat이라는 문자열을 참조
  //2. str+str 연산 진행으로 인해 "catcat" 문자열 만들어짐
  //3. 새로운 String 객체 생성
  //4. 새로운 String 객체에 "catcat" 삽입
  //5. 기존 str 객체는 새로운 str String 객체를 참조
  ```

- 이 처럼 String 연산은 계속적으로 객체를 만들어내기 때문에 비효율적이다.

- 이 를 해결하기 위해 나타난 것이 **String Constant pool**이다.



**String Constant pool**

반복적으로 객체 생성되는 것을 방지하기 위한 새로운 메모리 영역

```java
String s1 = "Cat";
String s2 = "Cat";
//String Constant pool에 의해 str1과 str2는 같은 인스턴스를 참조하고 있다.
//Cat은 String Constant pool 메모리 영역에 한 부분만 차지하기 때문이다.

String s3 = new String("Cat");	//heap 영역에 할당
s1 == s2;	//true
s1 == s3;	//false
```

![image](https://user-images.githubusercontent.com/40616436/72347403-73859380-371b-11ea-8c96-c302a59c5ef3.png)



**intern**(인터닝이란)

```java
String str1 = new String("ball");
String str2 = new String("ball");
String str3 = "ball";

//main
System.out.println(str1 == str1.intern());	//false
System.out.println(str3 == str1.intern());	//true
```

- `intern()`메소드를 사용하면 Heap영역에 있는 인스턴스를 String Constant pool영역으로 이동한다.
- 위 결과를 다시한번 살펴보면,
-  `System.out.println(str1 == str1.intern());`는 `false`가 나타난다.
  - 이유는 `str1`은 이제 Heap영역이 아닌 String Constatn pool 영역에 있으므로 같은 인스턴스를 참조하지 않는다.
-  `System.out.println(str3 == str1.intern());`는 `true`가 나타난다.
  - 위에서 본 바와 같이 `str1.intern()`으로 인해 `str1`은 String Constatn pool로 이동하였으므로 `str3`과 `str1`은 같은 인스턴스를 참조하는 것이다.



### String vs String Buffer vs String Builder

**String**

- String은 immutable(불변)으로서 한번 메모리에 생성되면 변하지 않는다.
- 위에서 살펴본 바와 같이 +연산 또는 concat을 통해 기존에 생성된 String 클래스의 변화를 주어도 해당 클래스는 변하는 것이 아니라 새로운 String 클래스를 생성 후 기존 String 클래스가 새로운 String 클래스를 참조하도록 하는 것이다.
- String이 객체는 **문자열 연산이 많은 경우 새로운 String 객체를 생성하므로 성능이 우수하지 못하다.**
- 하지만, **immutable한 객체로 인해 간단하게 사용가능**하고, 동기화에 대한 신경을 쓰지 않아도 되기 때문에 **Thread-safe**하다



**StringBuffer와 StringBuilder**

- **StringBuffer와 StringBuilder 공통점**

  - String과는 다르게 문자열 연산으로 인해 객체 공간이 부족한 경우에는 버퍼 크기를 늘려 유연하게 동작한다.
  - 서로 동일한 메소드를 가지고 있다.

  

- **StringBuffer와 StringBuilder 차이점**

  - **StringBuffer**는 메소드별로 Synchronized Keyword가 존재하여 **멀티 스레드 환경에서도 동기화를 지원**하나, **StringBuilder**는 **동기화를 보장하지 않는다.**
  - 그로인해, 멀티 스레드 환경이라면 StringBuffer를 사용하고 단일 스레드 환경이라면 StringBuilder를 사용하는 것이 좋다. **단일 스레드에서 StringBuffer를 사용해도 되나 StringBuilder에 비해 성능이 좋지 않다.**



**결과적으로,**

**String은 짧은 문자열을 연산 시 사용한다.**

**StringBuffer**는 스레드에 안전한 프로그램이 필요할 때나, 개발 중인 시스템이 스레드에 안전한지 모르는 경우에 사용하면 좋다.

**StringBuilder**는 스레드에 안전한지의 여부가 전혀 관계없는 프로그램을 개발할 때 사용하면 좋다.

