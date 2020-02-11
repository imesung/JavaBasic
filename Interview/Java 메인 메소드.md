## Java의 메인 메소드

**public static void main(String [] args)에 대해서 살펴보자**

-  public static void 까지만 살펴보면,
  - 컴파일 시점에서 해당 메소드는 메소드 영역에 올라가 일반 객체보다 먼저 정의되고, 전 영역에서 접근 가능하다.
  - 또한, 반환 값은 따로 없는 메소드이다.
- 파라미터로 받는 **String [] args**를 살펴보자
  - 해당 String 배열은 프로그램 실행 시 주어진 옵션들이 자동을 담겨진다.
  - 여기서 옵션은,
    - 예를 들어, 도스 창에서 **dir *.txt ** 라는 명령어를 사용하게 되면 txt 확장자를 가진 파일들의 목록이 출력되는데, 여기서 *.txt가 옵션에 해당하는 것이다.
    - java에서는 **java Foo** 로 **Foo라는 자바 프로그램을 실행할 때,
      - *java Foo ABC IBM naver* 이런 식으로 Foo 뒤에 문자열을 붙여서 실행한다면, 
        - **String []에 ABC, IBM, naver**가 저장되는 것이다.
  - 만약 **-help**와 같은 옵션이 설정된다면 프로그래머는 String 배열에서 해당 문자를 꺼내와 알맞은 처리를 진행하면 되는 것이다.



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