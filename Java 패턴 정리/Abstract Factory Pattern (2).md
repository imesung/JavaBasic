## 추상 팩토리 패턴 (2)

추상 팩토리 패턴은 어떻게 보면, 팩토리 메소드 패턴을 좀 더 캡슐화한 방식이라고 볼 수 있다.

**요구 사항**

컴퓨터를 생산하는 공장이 있을 때, 마우스, 키보드, 모니터의 제조사로 Samsung과 LG가 있다고 가정해보자. 그럼 컴퓨터를 생산할 때의 부품들은 모두 Samsung이나 LG로 만들어질 것이다.

그럼 먼저, 팩토리 메소드 패턴을 사용하여 컴퓨터 생산하는 로직을 구현해보자.

<img src="https://user-images.githubusercontent.com/40616436/82151655-78a84a00-9897-11ea-9ead-aeda4496b268.png" alt="image" style="zoom:50%;" />

위 설계를 발판으로 소스를 구현해보자.

**키보드 관련 클래스**

~~~java
public interface Keyboard {
}

public class LGKeyboard implements Keyboard {
  public LGKeyboard(){
    System.out.println("LG 키보드 생성");
  }
}

public class SamsungKeyboard implements Keyboard {
  public SamsungKeyboard(){
    System.out.println("Samsung 키보드 생성");
  }
}

public class KeyboardFactory {
  public Keyboard createKeyboard(String type){
    Keyboard keyboard = null;
    if(type.equals("LG")) {
      keyboard = new LGKeyboard();
    } else if(type.equals("Samsung")) {
      keyboard = new SamsungKeyboard();
    }

    return keyboard;
  }
}
~~~



**마우스 관련 클래스**

~~~java
public interface Mouse {
}

public class LGMouse implements Mouse {
  public LGMouse(){
    System.out.println("LG 마우스 생성");
  }
}

public class SamsungMouse implements Mouse {
  public SamsungMouse(){
    System.out.println("Samsung 마우스 생성");
  }
}

public class MouseFactory {
  public Mouse createMouse(String type){
    Mouse mouse = null;
    if(type.equals("LG")) {
      mouse = new LGMouse();
    } else if(type.equals("Samsung")) {
      mouse = new SamsungMouse();
    }

    return mouse;
  }
}
~~~



**컴퓨터 팩토리 클래스**

~~~java
public class ComputerFactory {
  public void createComputer(String type){
    KeyboardFactory keyboardFactory = new KeyboardFactory();
    MouseFactory mouseFactory = new MouseFactory();

    keyboardFactory.createKeyboard(type);
    mouseFactory.createMouse(type);
    System.out.println("--- " + type + " 컴퓨터 완성 ---");
  }
}
~~~



**마지막으로 컴퓨터 생산을 위한 Client 클래스**

~~~java
public class Client {
  public static void main(String args[]){
    ComputerFactory computerFactory = new ComputerFactory();
    computerFactory.createComputer("LG");
  }
}
~~~

자 이제 팩토리 메소드 패턴을 사용하여 컴퓨터를 생산해보았다.

그런데 만약 컴퓨터의 구성품은 키보드, 마우스 뿐만 아니라 본체 구성품들, 모니터, 스피커, 프린터 등등의 여러가지가 있다면..현재의 코드를 그대로 사용할 때 신경쓰이는 코드들이 보이게 될 것이다.

**컴퓨터 팩토리**

~~~java
public class ComputerFactory {
  public void createComputer(String type){
    KeyboardFactory keyboardFactory = new KeyboardFactory();
    MouseFactory mouseFactory = new MouseFactory();
    BodyFactory bodyFactory = new BodyFactory();
    MonitorFactory monitorFactory = new MonitorFactory();
    SpeakerFactory speakerFactory = new SpeakerFactory();
    PrinterFactory printerFactory = new PrinterFactory();

    keyboardFactory.createKeyboard(type);
    mouseFactory.createMouse(type);
    bodyFactory.createBody(type);
    monitorFactory.createMonitor(type);
    speakerFactory.createSpeaker(type);
    printerFactory.createPrinter(type);
    System.out.println("--- " + type + " 컴퓨터 완성 ---");
  }
}
~~~

위 처럼 참 불편한 코드가 작성되게 될 것이다. 그런데 사실 Samsung 컴퓨터라면 구성품이 모두 Samsung일 것이고, LG 컴퓨터라면 구성품이 모두 LG일 것이다. 다시 말해서 구성품이 Samsung이냐 LG냐 구분할 필요 없이, Samsung 컴퓨터를 만들고자 하면 구성품이 모두 Samsung이 되도록 하고, LG 컴퓨터를 만들고자 하면 구성품이 모두 LG가 되도록하는 일관된 방식으로 객체를 생성할 필요가 있다는 것이다. 또한 이렇게 소스가 구현되다면 구성품이 늘어날수록 팩토리 객체를 생성하는 부분이 더욱 길어질 것이다.

그럼 이런 부분을 방지하고자 등장한 패턴은 추상 팩토리 패턴이다.

---

**추상 팩토리 패턴**

<img src="https://user-images.githubusercontent.com/40616436/82152430-05550700-989c-11ea-8f6e-795c11ffc76b.png" alt="image" style="zoom:50%;" />

추상 팩토리 패턴과 팩토리 메소드 패턴을 비교해보면,

- 어떤 제조사의 부품을 선택할지 결정하는 팩토리 클래스(KeyboardFactory, MouseFactory)가 제거되고, Computer Factory 클래스가 추가되었다. (SamsungComputerFactory, LGComputerFactory)
- SamsungComputerFactory, LGComputerFactory는 ComputerFactory 인터페이스 캡슐화하고, 어떤 제조사의 부품을 생성할 것인지 명확하므로, 각각의 제조사 부품을 생성한다.
- FactoryOfComputerFactory 클래스에서 컴퓨터를 생산하는 createComputer() 메소드를 호출한다.

~~~java
public interface Keyboard {
}

public class LGKeyboard implements Keyboard {
  public LGKeyboard(){
    System.out.println("LG 키보드 생성");
  }
}

public class SamsungKeyboard implements Keyboard {
  public SamsungKeyboard(){
    System.out.println("Samsung 키보드 생성");
  }
}

public class KeyboardFactory {
  public Keyboard createKeyboard(String type){
    Keyboard keyboard = null;
    if(type.equals("LG")) {
      keyboard = new LGKeyboard();
    } else if(type.equals("Samsung")) {
      keyboard = new SamsungKeyboard();
    }

    return keyboard;
  }
}

public interface Mouse {
}

public class LGMouse implements Mouse {
  public LGMouse(){
    System.out.println("LG 마우스 생성");
  }
}

public class SamsungMouse implements Mouse {
  public SamsungMouse(){
    System.out.println("Samsung 마우스 생성");
  }
}

public class MouseFactory {
  public Mouse createMouse(String type){
    Mouse mouse = null;
    if(type.equals("LG")) {
      mouse = new LGMouse();
    } else if(type.equals("Samsung")) {
      mouse = new SamsungMouse();
    }

    return mouse;
  }
}

public class SamsungComputerFactory implements ComputerFactory {
  public SamsungKeyboard createKeyboard() {
    return new SamsungKeyboard();
  }

  public SamsungMouse createMouse() {
    return new SamsungMouse();
  }
}

public class LGComputerFactory implements ComputerFactory {
    public LGKeyboard createKeyboard() {
        return new LGKeyboard();
    }

    public LGMouse createMouse() {
        return new LGMouse();
    }
}

public interface ComputerFactory {
    public Keyboard createKeyboard();
    public Mouse createMouse();
}
~~~

다음은 FatoryOfComputerFactory 클래스를 정의한다. 이 클래스는 패턴 적용 전 ComputerFactory 클래스와 하는 일이 동일하다.

입력값에 따라 객체 생성을 분기하는데, 이 때 어떤 제조사 컴퓨터 객체를 생성할 지 결정한다. 즉, 부품이 아니라 컴퓨터 객체를 생성한다는 점에서 차이점이 있다.

~~~java
public class FactoryOfComputerFactory {
  public void createComputer(String type){
    ComputerFactory computerFactory= null;
    if(type.equals("LG")) {
        computerFactory = new LGComputerFactory();			      
    } else if(type.equals("Samusng")) {
        computerFactory = new SamsungComputerFactory();      
    }

    computerFactory.createKeyboard();
    computerFactory.createMouse();
  }
}
~~~

클라이언트 클래스

~~~java
public class Client {
  public static void main(String args[]){
    FactoryOfComputerFactory factoryOfComputerFactory = new FactoryOfComputerFactory();
    factoryOfComputerFactory.createComputer("LG");
  }
}
~~~

이 처럼 팩토리 메소드 패턴을 추상 팩토리 패턴으로 변경해보았다. 정리 하면, 팩토리 메소드 패턴에서는 구성품 마다 팩토리를 만들어서 어떤 객체를 형성했는데 그 객체의 구성품은 일정하므로, 추상 팩토리 패턴을 적용하여 관련된 객체들을 한꺼번에 캡슐화하여 팩토리를 만들어서 일관되게 객체를 생성하도록 했다.