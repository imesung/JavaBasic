## Error와 Exception

### Error와 Exception의 차이

**Error(에러)**

- **컴퓨터 하드웨어의 오동작 또는 고장으로 인해 응용 프로그램에 이상이 생겼거나 JVM 실행에 문제가 생겼을 경우 발생하는 것을 말한다.**



**Exception(예외)**

- **개발자의 잘못된 코딩 혹은 API 접근 실패로 인해 발생하는 프로그램 오류를 말한다.**
- 예외가 발생하면 프로그램이 종료 된다는 것은 에러와 동일하지만 **예외는 예외처리를 통해 프로그램을 종료하지 않고 정상적으로 작동되게 만들 수 있다.**
- Java에서는 해당 예외처리를 **try/catch문**을 통해 진행한다.



### 여러 Exception들을 살펴보자

**NullPointException**

- 객체 참조가 없는 상태로서, null 값을 갖는 참조 변수를 호출하려 할 때 발생하는 예외이다.

- 객체를 참조하고 있지 않은데, 객체를 사용하려 하여 발생하는 예외이다.

  ~~~java
  class MyClass {
    public String field = "test";
  }
  
  MyClass myClass = null;
  myClass.field;	//NullPointException 발생
  ~~~



**ArrayIndexOutOfBoundsException**

- 배열에서 인덱스 범위를 초과하여 사용할 경우 발생한다.



**NumberFormatException**

- 문자열로 되어있는 데이터를 숫자 타입으려 변경하는 경우가 있는데, 숫자 타입으로 변경할 수 없는 문자를 치환하려할 때 발생한다.



**ClassCastException**

- 타입 변환은 상위 클래스와 하위 클래스 간에 발생하고, 구현 클래스와 인터페이스 간에도 발생한다. 그러나 이런 관계가 아니면 타입 변환이 일어나지 않는데, 이 규칙을 무시하고 타입 변환을 진행시킬 시 해당 exception이 발생한다.



### try/catch와 throw

**try/catch**

~~~java
try{
  //에러가 발생할 수 있는 코드 : API 접근 실패, 혹은 여러 Exception 발생
  //Ex. 강제 에러 출력 
  throw new Exception();
}catch (Exception e){
  //에러시 수행
  e.printStackTrace(); //오류 출력
  throw e; //최상위 클래스가 아니라면 무조건 던져주자
}finally{
  //무조건 수행
} 
~~~



**throw**

- 예외가 발생했을 때, 최상위 클래스가 아니라면 catch문에서 오류에 관련된 메시지를 출력하고나서 오류는 무조건 던져줘야 한다.
- 하지만 개인적인 생각에서는 무조건 던져줘서는 안된다.
  - 이유는, 현재 애플리케이션을 유지보수하고 있는 환경이라면 정상적인 동작이 꾸준히 이루어져야하기 때문에 **catch문에서 로그를 찍어주는 방식으로 처리하고, throw는 하지 않는 것이 좋다.**