

## 템플릿 메소드 패턴

> 일정한 프로세스를 가진 요구사항을 템플릿 메소드 패턴을 이용하여 구현할 수 있다.
>
> 템플릿은, 모양자 중 동그라미를 그리는데 여러 색의 색연필을 사용하면 하나의 도형에 여러 색의 도형을 만들 수 있는 의미로 말할 수 있다.

- 알고리즘의 구조를 메소드에 정의하고 하위 클래스에서 알고리즘 구조의 변경없이 알고리즘을 재정의하는 패턴이다.



**사용 목적**

- 구현하려는 알고리즘이 일정한 프로세스가 있다. (여러 단계로 나눌 수 있다.)
- 구현하려는 알고리즘이 변경 가능성이 클 때 사용한다.



**템플릿 메소드 패턴의 단계**

1. 알고리즘을 **여러 단계**로 나눈다.
2. 나눠진 알고리즘의 단계를 **메소드로 선언**한다.
3. 알고리즘을 수행할 **템플릿 메소드**를 만든다.
4. 하위 클래스에 **나눠진 메소드들을 구현**한다.



**기본 설계**

<img src="https://user-images.githubusercontent.com/40616436/79869266-1422d800-841c-11ea-955b-a2763fce5231.png" alt="image" style="zoom:50%;" />

- 알고리즘을 각 operation1, 2, 3로 단계화 한다.
- 단계를 operation1(), operation2(), operation3()으로 메소드 선언한다.
- 알고리즘을 수행할 templateMethod()를 만든다.
- 나눠진 메소드들을 구현 할 Concrete Class를 만든다.



**요구사항**

- 신작 게임의 접속을 구현해라.
  - requestConnection(String str) : String
- 유저가 게임 접속 시 다음을 고려해야한다.
  - 보안 과정 : 보안 관련 부분을 처리한다.
    - doSecurity(String string) : String
  - 인증 과정 : user name과 password가 일치하는지 확인한다.
    - authentication(String id, String password) : boolean
  - 권한 과정 : 접속자가 유료 회원인지 무료 회원인지 게임 마스터인지 확인한다.
    - authorization(String userName) : int
  - 접속 과정 : 접속자에게 커넥션 정보를 넘겨준다.
    - conection(String info) : String



*요구 사항 설계*

1. 유저가 게임 접속 시 고려해야할 알고리즘을 보안, 인증, 권한, 접속 과정으로 단계화 한다.
2. 각 과정을 추상 메소드로 선언한다.
3. 알고리즘을 수행 할 requestConnection()인 템플릿 메소드를 선언한다.
4. 메소드들을 구현할 Concrete Class를 만든다.



~~~java
//Abstract class
public abstract class AbstGameConnectHelper {
  protected abstract String doSecurity(String string);
  protected abstract boolean authentication(String id, String password);
  protected abstract int authorization(String userName);
  protected abstract String conection(String info);

  //템플릿 메소드
  public String requestConnection(String encodedInfo) {
    //보안 과정 -> 암호화 된 문자열을 디코드(복호화) 한다.
    String decodedInfo = doSecurity(encodedInfo);

    //인증 과정
    String id = "aaa";
    String password = "bbb";
    if(!authentication(id, password)) {
      throw new Error("아이디 암호 불일치");
    }

    //권한 과정
    String userName = "mesung";
    int i = authorization(userName);
    switch (i) {
      case 0: //게임 매니저
        break;
      case 1: //유료 회원
        break;
      case 2: //무료 회원
        break;
      case 3: //권한 없음
        break;
      default://기타 상황
    }

    //접속 과정
    return conection(decodedInfo);
  }
}

//Concrete Class
public class DefaultGameConnectHelper extends AbstGameConnectHelper{
  @Override
  protected String doSecurity(String string) {
    System.out.println("디코드");
    return string;
  }

  @Override
  protected boolean authentication(String id, String password) {
    System.out.println("아이디/암호 확인 과정");
    return true;
  }

  @Override
  protected int authorization(String userName) {
    System.out.println("권한 확인");
    return 0;
  }

  @Override
  protected String conection(String info) {
    System.out.println("마지막 접속 단계");
    return info;
  }
}

//Main
public static void main(String [] args) {
  AbstGameConnectHelper helper = new DefaultGameConnectHelper();
  helper.requestConnection("아이디 암호 등 접속 정보");
}
~~~

- 추상화 클래스를 선언 후 알고리즘을 단계별로 나눈 추상화 메소드와 알고리즘을 수행할 템플릿 메소드를 선언 및 구현한다.
  - 추상화 메소드를 protected로 선언한 이유는, **알고리즘은 외부에 노출이 되면 안 되는 동시에 구현은 진행해야 하므로, 같은 패키지에서만 접근이 가능한 protected로 선언한 것이다.**
- 추상화 메소드를 구현할 클래스를 선언 후 요구사항에 맞게 재정의한다.
- 메인에서 템플릿 메소드를 호출하여 알고리즘을 실행한다.

<img src="https://user-images.githubusercontent.com/40616436/79877226-f313b480-8426-11ea-9bd3-fb0777190ebf.png" alt="image" style="zoom:50%;" />



**추가 요구사항**

- 보안 부분이 더욱 강화되었다. 강화된 방식으로 코드를 변경해야한다.
- 여가부에서 밤 10시 이후에 접속이 제한되도록 한다.

~~~java

public class DefaultGameConnectHelper extends AbstGameConnectHelper{
	//강화된 방식으로 코드를 변경하자.
  @Override
  protected String doSecurity(String string) {
    //System.out.println("암호화 디코드");
    System.out.println("강화된 알고리즘 암호화 디코드");
    return string;
  }

  ... 

  //밤 10시 이후 접속 제한
  @Override
  protected int authorization(String userName) {
    System.out.println("권한 확인");

    //추가 요구 사항 : 서버에서 10시에 유저 이름으로 유저의 나이를 확인 후 성인이 아니면 shutdown
    return -1;
  }
}


//밤 10시 이후 접속 제한
public abstract class AbstGameConnectHelper {
  protected abstract String doSecurity(String string);
  protected abstract boolean authentication(String id, String password);
  protected abstract int authorization(String userName);
  protected abstract String conection(String info);

  //템플릿 메소드
  public String requestConnection(String encodedInfo) {
    ...

    //권한 과정
    String userName = "mesung";
    int i = authorization(userName);
    switch (i) {
      case -1:	//권한 추가
        throw new Error("미성년자 사용 금지");
      case 0: //게임 매니저
        break;
		...
  }
}
~~~

- 보안 부분 및 권한을 변경할 시 정의 된 알고리즘은 변경하지 않고 재정의 된 부분만 변경할 수 있다.

![image](https://user-images.githubusercontent.com/40616436/79877614-674e5800-8427-11ea-8c01-76ff71f94de2.png)



**템플릿 메소드 패턴의 개념을 다시 한번 살펴보자**

- 알고리즘의 구조를 메소드에 정의하고 하위 클래스에서 알고리즘 구조의 변경없이 알고리즘을 재정의하는 패턴이다.
  - **추가적인 요구사항에서 하위 클래스에서 알고리즘을 건들지 않고 요구사항을 충족시키는 것을 확인할 수 있었다.**
- 구현하려는 알고리즘이 일정한 프로세스가 있고, 변경 가능성이 있다.
  - **각 과정의 일정한 프로세스가 존재하고, 보안 부분과 권한 부분이 변경되는 가능성을 확인할 수 있었다.**