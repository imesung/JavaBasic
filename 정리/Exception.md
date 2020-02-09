## Exception

**Exception,**

Exception은 원하지 않거나 예기치 않은 이벤트로, 프로그램 런타임에 발생하여 프로그램 명령의 정상적인 흐름을 방해한다.



**Exception and Error**

 - Error :  오류 심각한 문제를 나타내므로, 응용 프로그램에서 **try / catch** 문을 사용하며 안 된다.
 - Exception :  Exception은 상태를 나타내므로, 응용 프로그램에서 **try / catch** 문을 사용해야 한다.



**예외계층**

모든 Exception과 Error의 계층은 기본 클래스인 Throwable 클래스의 하위 클래스이다. 

- Exception은 프로그래머가 직접 예외 조건을 잡아야 한다.
  - Ex. NullPointerException
-  Error는 Java 런타임 시스템 ( JVM )에서 런타임 환경 자체 (JRE)와 관련된 Error를 표시하기 위해 사용된다.
  - Ex. StackOverflowError

![image](https://user-images.githubusercontent.com/40616436/73763643-0f536e00-47b5-11ea-92ca-e356494c7849.png)



**JVM이 예외를 처리하는 방법**

*기본 예외 처리*

- 메소드 내부에서 **예외가 발생하면 메소드는 예외 오브젝트라는 오브젝트를 작성하고 이를 런타임 시스템 (JVM)으로 전달한다.**
- 예외 개체에는 **예외의 이름과 설명, 예외가 발생한 프로그램의 현재 상태**가 포함된다.
- 예외 객체를 생성하여 런타임 시스템으로 처리하는 것을 **예외 발생**이라고 한다.
- 예외가 발생한 메서드를 얻기 위해 호출 된 메서드 목록이 있을 수 있다.

이 순서화 된 메소드 목록을 **호출 스택** 이라고한다.

**다음 절차**

 - 런타임 시스템은 호출 스택을 검색하여 발생한 예외를 처리 할 수 있는 코드 블록이 포함 된 메소드를 찾는다. **코드 블록을 예외 처리기** 라고 한다.
 - **런타임 시스템은 예외가 발생한 메소드에서 검색을 시작하고 메소드가 호출 된 역순으로 호출 스택을 진행** 한다..
 - 적절한 핸들러를 찾으면 발생한 **예외를 전달**하고, 여기서 **적절한 처리기는 예외 개체 유형이 처리 할 수 있는 예외 개체 유형과 일치한다**는 것을 의미한다.
 - 런타임 시스템이 호출 스택에서 모든 메소드를 검색하고 적절한 핸들러를 찾을 수없는 경우, 런타임 시스템은 예외 시스템을 런타임 시스템의 일부인 기본 예외 핸들러로 인계한다.
    - 이 핸들러는 다음 형식으로 예외 정보를 인쇄하고 프로그램을 비정상적으로 종료합니다 .

~~~
Exception in thread "XXX" Name of Exception : Description
... ...... .. // Call Stack
~~~

호출 스택의 흐름을 이해하려면 아래 다이어그램을 참조하십시오.

![image](https://user-images.githubusercontent.com/40616436/73764546-8fc69e80-47b6-11ea-897e-3d7748660a27.png)



**프로그래머는 예외를 어떻게 처리하는가?**

사용자 정의 예외 처리

- Java 예외 처리는 **try , catch , throw , throws 및 finally**라는 다섯 가지 키워드를 통해 관리된다.

간단히, 여기 그들이 작동하는 방법있다.

1. 예외를 발생시킬 수 있다고 생각되는 코드는 try 블록 내에 포함된다.
2. try 블록 내에서 예외가 발생하면 예외가 발생한다. 코드는 **catch 예외를 사용**하여, 이 예외를 포착하고 해결한다.
3.  **시스템 생성 예외는 Java 런타임 시스템에서 자동으로 발생** 한다.
   - 예외를 수동으로 발생 시키려면 키워드 throw를 사용하면 된다.
4. 메소드에서 발생하는 예외는 throws 절에 의해 지정되어야 한다.
   - try 블록이 완료된 후에 절대적으로 실행되어야하는 모든 코드는 finally 블록에 배치된다.




**try-catch 절을 사용하는 방법**

~~~java

try {
// block of code to monitor for errors
// the code you think can raise an exception
}
catch (ExceptionType1 exOb) {
// exception handler for ExceptionType1
}
catch (ExceptionType2 exOb) {
// exception handler for ExceptionType2
}
// optional
finally {
// block of code to be executed after try block ends
}

~~~



**전체 요약**

![image](https://user-images.githubusercontent.com/40616436/73765492-f39d9700-47b7-11ea-96bc-8f155cce9cdf.png)



**참고**
https://www.geeksforgeeks.org/exceptions-in-java/

