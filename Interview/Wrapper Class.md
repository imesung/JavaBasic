## wrapper Class

기본형 타입을 객체 형식으로 나타내기 위해서 사용되는 것이 **wrapper class이다.**

**자바에서 필드 값은 기본형(primitive type)과 참조형(reference type)으로 나뉜다.**

기본형과 참조형 래퍼 클래스

![image](https://user-images.githubusercontent.com/40616436/74240006-68268780-4d1c-11ea-8b26-937ad220ee7e.png)

- Boolean, Character, Number는 모두 **Object의 자손이다**

- 숫자와 관련된 wrapper Class(Byte, Short, Integer, Long, Float, Double, BinInteger, BigDecimal)은 모두 **Number 클래스의 자손이다.**

  - 여기서 Number 클래스는 추상 클래스이다.

  ~~~java
  public abstract class Number implements Serializable {
      private static final long serialVersionUID = -8742448824652078965L;
      public Number() {
      }
      public abstract int intValue();
      public abstract long longValue();
      public abstract float floatValue();
      public abstract double doubleValue();
      public byte byteValue() {
          return (byte)this.intValue();
      }
      public short shortValue() {
          return (short)this.intValue();
      }
  }
  ~~~

  



### Wrapper Class는 언제 사용될까?

기본형 변수도 객체로 다뤄줘야 하는 경우가 있다.

- 매개변수로 객체가 요구될 때 (JDK1.5버전 이전)
- 기본형 값이 아닌 객체로 저장해야할 때 (JDK1.5버전 이전)
- 객체간의 비교가 필요할 때
  - **wrapper class는 모두 Object의 메소드를 오버라이딩하여 사용한다.**
    - equals(), toString() 등..
    - **wrapper class의 equals()는 객체의 주소값이 아닌 값을 비교한다는 사실을 알아두자**

이때 wrapper Class가 사용된다.



### Wrapper Class의 메소드

- Object 오버라이드
  - equals(), hashCode(), toString() ...
- Number 오버라이드
  - intValue(), floatValue()..
- Comparable 오버라이드
  - compareTo()



### JDK 1.5버전 이후

**Boxing과 UnBoxing의 등장**

- JDK 1.5버전 이전에서는 컴파일러가 기본형과 참조형의 관계를 알지 못했다.

  - 그로 인해, JDK 1.5버전 이전에는 기본형을 참조형(wrapper class)으로 변환하기 위해 많이 사용되었다.

- JDK 1.5버전 이후  Boxing과 UnBoxing이 등장했다.

- Boxing : 기본형을 Wrapper Class로 변환

  ~~~java
  int intAge = 12;
  Integer age = (Integer) 12;	//int -> Integer
  ~~~

- UnBoxing : 각각의 객체를 기본형으로 변환

  ~~~java
  Integer age = new Integer(12);
  int intAge = (int) age;	//Integer -> int
  ~~~



**AutoBoxing과 AutoUnBoxing의 등장**

- JDK 1.5버전 이후 AutoBoxing과 AutoUnBoxing의 등장했다.

  - Boxing과 UnBoxing을 자동으로 처리해주는 것이다.

-  AutoBoxing

  ~~~java
  int intAge = 12;
  Integer age = intAge;	//int -> Integer
  ~~~

- AutoUnBoxing

  ~~~java
  Integer age = new Integer(12);
  int intAge = age;	//Integer -> int
  ~~~

- **AutoBoxing과 AutoUnBoxing은 단지 기본형 타입과 상응하는 Wrapper Class에서만 일어난다.**

  ~~~java
  Double obj = 3.14;
  int num1 = obj.intValue();	//O
  int num2 = obj;							//X
  //기본형 타입과 상응하지 않기 때문에 obj는 AutoUnBoxing이 일어나지 않는다.
  ~~~

  