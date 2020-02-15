## Java의 메인 메소드

**public static void main(String [] args)에 대해서 살펴보자**



**public static void 선언 이유**

**Java의 Main**

- Java는 Main에서 시작해서 Main에서 끝난다
- Main은 여러 클래스와 연결될 수 있는 중심부의 역할이다.

**매인의 public**

- Main을 실행하는데 있어, Main에서는 다른 클래스의 메소드 등을 호출해야하는데, 막상 Main 자체가 다른 곳에서 접근을 허용하지 않는다면 문제가 생길 수 밖에 없어 Main의 접근제한자를 **public**으로 선언한 것이다.

**메인의 static**

- static은 프로그램이 시작하면 따로 인스턴스화 하지 않아도 method 영역 메모리에 호출되어 진다. 이렇게 호출되어진 것들은 프로그램이 종료되기 까지 유지된다.
- 즉, **프로그램의 시작과 끝을 함께하는 Main 메소드는 프로그램이 종료되는 시점까지 유지해야하기 때문에 static으로 선언된 것이다.**

**메인의 void**

- Main 메소드는 프로그램이 종료되는 시점까지 유지가 된다. 즉, **만약 반환 타입이 선언되어 있다면 프로그램이 종료될 때 어떤 값을 반환하겠다라는 것인데, 이는 우리가 추구하는 목적과는 상관없는 로직이다.**
- 우리는 프로그램을 사용할 때 프로그램이 구동되는 그 시점에서만 메시지를 주고 받으므로, 프로그램이 종료되는 시점에서 반환되는 값은 전혀 의미가 없는 값이다.

**메인의 String [] args**

-  결론부터 말하면, **외부에서 값을 받아오기 위해 사용한 매개변수이다.**
- 외부에서 받아오는 값을 옵션이라고 하는데 이 옵션들은 String 배열에 넣어 프로그램을 실행한다.
-  여기서 옵션은,
  - 예를 들어, 도스 창에서 **dir *.txt ** 라는 명령어를 사용하게 되면 txt 확장자를 가진 파일들의 목록이 출력되는데, 여기서 *.txt가 옵션에 해당하는 것이다.
  - java에서는 **java Foo** 로 **Foo라는 자바 프로그램을 실행할 때,
    - *java Foo ABC IBM naver* 이런 식으로 Foo 뒤에 문자열을 붙여서 실행한다면, 
      - **String []에 ABC, IBM, naver**가 저장되는 것이다.
-  만약 **-help**와 같은 옵션이 설정된다면 프로그래머는 String 배열에서 해당 문자를 꺼내와 알맞은 처리를 진행하면 되는 것이다.



### 옵션을 설정해보자

~~~java
public class MainMethod {
    public static void main(String args[]) {

        // 옵션 개수 검사. 옵션 없으면 에러 메시지 출력하고 종료
        if (args.length == 0) {
            System.err.println("옵션을 입력하세요");
            System.exit(1);
        }

        // 옵션 개수 출력
        System.out.println("모두 " + args.length + "개의 옵션을 입력하셨군요.");

        System.out.println();

        // 모든 옵션 하나씩 화면에 출력
        for (int i = 0; i < args.length; i++)
            System.out.format("args[%d] : %s%n", i, args[i]);

    }
}
~~~

**실행결과**

![image](https://user-images.githubusercontent.com/40616436/74254356-ad09e880-4d33-11ea-8b36-1fedfcdb6dcd.png)