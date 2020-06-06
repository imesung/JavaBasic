## 퍼사드 패턴

> 복잡한 과정을 간단하게 표현하는 퍼사드 패턴을 이해해보자.

Facade는 "건물의 정면"을 의미하는 단어로 **내부의 클래스를 추상화한 인터페이스를 외부에 제공해주는 의미를 지닌 패턴이다.**



**기본 설계**

<img src="https://user-images.githubusercontent.com/40616436/83947885-ae1bd400-a854-11ea-8354-7a4bcb40b46a.png" alt="image" style="zoom:50%;" />

**소스 예제를 통해 살펴보자**

~~~java
package com.pattern.ch14_facade.system;

public class Facade {

  private HelpSystem01 helpSystem01;
  private HelpSystem02 helpSystem02;
  private HelpSystem03 helpSystem03;

  public Facade() {
    helpSystem01 = new HelpSystem01();
    helpSystem02 = new HelpSystem02();
    helpSystem03 = new HelpSystem03();
  }

  public void process() {
    helpSystem01.process();
    helpSystem02.process();
    helpSystem03.process();
  }
}

//HelpSystme01, 02, 03은 모두 같은 패키지
//package com.pattern.ch14_facade.system;
class HelpSystem01 {
  public HelpSystem01() {
    System.out.println("Call Constructor : " + getClass().getSimpleName());
  }

  public void process() {
    System.out.println("Call Process : " + getClass().getSimpleName());
  }
}
class HelpSystem02 {
  public HelpSystem02() {
    System.out.println("Call Constructor : " + getClass().getSimpleName());
  }

  public void process() {
    System.out.println("Call Process : " + getClass().getSimpleName());
  }
}
class HelpSystem03 {
  public HelpSystem03() {
    System.out.println("Call Constructor : " + getClass().getSimpleName());
  }

  public void process() {
    System.out.println("Call Process : " + getClass().getSimpleName());
  }
}
~~~

접근 제한자가 명시되어 있지 않은 class는 default 접근제한자로서 protected 역할을 가지고 있다. 즉 같은 패키지 내에서만 접근이 가능하다.

~~~java
package com.pattern.ch14_facade.main;
import com.pattern.ch14_facade.system.Facade;

public class FacadeMain {
  public static void main(String[] args) {
    Facade facade = new Facade();
    facade.process();

    //패키지가 다르므로 호출 불가.
    //HelpSystem01 helpSystem01 = new HelpSystem01();
  }
}
~~~

외부 Main 클라이언트에서는 HelpSystem01, 02, 03에 직접적으로 접근을 하지 못하고, Facade를 통해서만 접근이 가능하다.

> HelpSystme01은 default 제한자로 되어있으므로, 해당 패키지에서만 접근이 가능하다. 이 소리는 해당 클래스에 요청을 하기 위해서는 Facade 클래스를 통해서만 요청이 가능하다는 것으로서, HelpSystem01의 변화는 외부에 영향을 주지 않으며, 외부에서는 HelpSystem01이 무슨 작업을 하는 지 모르는 상태에서 그냥 메시지를 통해서만 협력이 가능하다는 이야기이다. 
>
> 또한, 이런 분리는 객체들의 결합도를 느슨하게 해준다는 이점이 있고, 캡슐화 역할도 가지고 있다.

