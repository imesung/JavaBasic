## Exception

**Checked Exception과 Unchecked Exception**

- Checked Exception
  - 컴파일타임에 검사하는 예외로서, 처리하지 않으면 컴파일 에러가 발생하므로 반드시 처리해야한다. 처리 방법으로는 **try/catch로 감싸거나 throws로 호출한 메소드에게 예외를 넘겨준다.**
- Unchecked Exception 
  - 런타임에 검사하는 예외로서, 컴파일하는데 문제는 없지만 실행 중에 에외가 발생할 수 있다. 주로 프로그래머의 실수, 잘못 작성된 코드, 사용자가 애플리케이션을 잘못 사용할 때 이를 처리하는 경우에 발생한다.

<img src="https://user-images.githubusercontent.com/40616436/84009144-ceab7180-a9ad-11ea-886b-28affccd2986.png" alt="image" style="zoom:50%;" />

---

**Java에서 Exception 처리하기**

기존에 예외를 처리할 때는 log를 통해 예외의 메시지만 출력해주고 아무것도 해주지 않았다. 그리고 Checked Exception을 커스텀 런타임 예외(Unchecked Exception)로 변경 후 던져서 처리하는 경우에도 기존의 Checked Exception에 대한 정보를 보존하지 않고 메시지만을 처리하곤 했다. 이러한 경우 예외를 추적하기 힘들고 커스텀 런타임 예외로 변경하여 던지면 이를 호출한 곳에서 처리해야 하는데, 기존의 Exception 정보가 필요한 경우가 생길 수 있다.

기존에 Exception을 처리하는 방식을 한번 살펴보자.

---

**로그에 예외 정보 출력하기**

~~~java
Product test = null;
try {
  test.toString();
} catch(Exception e) {
  logger.error("Fail " + e.getMessage());
}
~~~

로그 시스템을 사용하여 에외를 처리할 때 만약 Exception이 발생하면 아래와 같은 메시지가 출력된다.

<img src="https://user-images.githubusercontent.com/40616436/84011012-7033c280-a9b0-11ea-9cb0-c73ff3658824.png" alt="image" style="zoom:50%;" />

위와 같은 결과로서는 어디서 에러가 발생하는 지는 알 수가 없다. 예외가 발생하는 경우 stack trace를 통해 예외를 추적해야 하는데, 위 그림에서는 이를 전혀 출력하고 있지 않다. stack trace는 아래와 같은 그림을 말한다.

<img src="https://user-images.githubusercontent.com/40616436/84011148-ab35f600-a9b0-11ea-8c53-d906caa06048.png" alt="image" style="zoom:50%;" />

그러므로 stack trace를 확인할 수 있도록 아래와 같이 사용하여 Exception의 stack trace를 확인해야 한다.

~~~java
Product test = null;
try {
  test.toString();
} catch(Exception e) {
  logger.error("Fail " + e);
}
~~~

<img src="https://user-images.githubusercontent.com/40616436/84011457-1b447c00-a9b1-11ea-9ae9-6cba381f8016.png" alt="image" style="zoom:50%;" />

---

