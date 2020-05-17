## 브릿지 패턴

> 어댑터 패턴과 브릿지 패턴을 연결하여 이해한다.

**기능 부분과 구현 부분을 분리하는 것이 이번 패턴의 키워드이다.**

브릿지 패턴은 잘 사용되지 않고, 어댑터 패턴과 매우 비슷하다는 것을 먼저 알고 들어가자.

<img src="https://user-images.githubusercontent.com/40616436/82153283-75b25700-98a1-11ea-89b2-cbcce973dffc.png" alt="image" style="zoom:50%;" />



**먼저 브릿지 패턴을 사용하지 않은 소스룰 확인해보자**

- 요구사항은 입력한 단어를 모스부호 형태로 출력하는 것이다.

  ```java
  public class MorseCode {
    public void dot() {
      System.out.print("·");
    }
  
    public void dash() {
      System.out.print("-");
    }
  
    public void space() {
      System.out.print(" ");
    }
  }
  
  public class PrintMorseCode extends MorseCode {
      //blue
      public PrintMorseCode b() {
          dash();dot();dot();dot();space();
          return this;
      }
  
      public PrintMorseCode l() {
          dot();dash();dot();dot();space();
          return this;
      }
  
      public PrintMorseCode u() {
          dot();dot();dash();space();
          return this;
      }
  
      public PrintMorseCode e() {
          dot();space();
          return this;
      }
  }
  
  public class BridgeMain {
    public static void main(String[] args) {
      PrintMorseCode printMorseCode = new PrintMorseCode();
      printMorseCode.b().l().u().e();
    }
  }
  
  //실행 결과
  //-··· ·-·· ··- ·
  ```

그런데 만약에 모스부호를 노출시켜주는 것이 문자형식이 아닌 음성으로 변경될 경우, MorseCode의 각 메소드들을 변경해줘야 하거나 아니면 음성에 관련된 책임으로 되어있는 MorseCode2라는 클래스를 만들어야 한다. 또한, MorseCode2를 상속받는 PrintMorseCode2도 만들어야 한다는 것이다. 이런 방식은 유지보수 측면에서 매우 번거로움이 발생한다는 것이다.

---

**브릿지 패턴을 사용해보자.**

1. **먼저 기능들을 외부에 노출해주는 인터페이스를 만든다.**

~~~java
public interface MorseCodeFunction {
  public void dot();
  public void dash();
  public void space();
}
~~~

2. **인터페이스의 기능을 구현하는 세부적인 클래스를 만든다.**

~~~java
public class DefaultMCF implements MorseCodeFunction{

  @Override
  public void dot() {
    System.out.print("·");
  }

  @Override
  public void dash() {
    System.out.print("-");
  }

  @Override
  public void space() {
    System.out.print(" ");
  }
}
~~~

3. **그리고 이제 해당 인터페이스를 결합하여 좀 더 구체적인 기능을 구현한다.**

~~~java
public class MorseCode {
  private MorseCodeFunction function;

  public MorseCode(MorseCodeFunction function) {
    this.function = function;
  }

  public void setFunction(MorseCodeFunction function) {
    this.function = function;
  }

  public void dot() {
    function.dot();
  }

  public void dash() {
    function.dash();
  }

  public void space() {
    function.space();
  }
}
~~~

4. **이제 해당 기능들을 상속받아 원하는 로직들을 구성한다. 여기서는 알파벳 별 모스 부호를 구현한다.**

~~~java
public class PrintMorseCode extends MorseCode {

  public PrintMorseCode(MorseCodeFunction function) {
    super(function);
  }

  //blue
  public PrintMorseCode b() {
    dash();dot();dot();dot();space();
    return this;
  }

  public PrintMorseCode l() {
    dot();dash();dot();dot();space();
    return this;
  }

  public PrintMorseCode u() {
    dot();dot();dash();space();
    return this;
  }

  public PrintMorseCode e() {
    dot();space();
    return this;
  }
}
~~~

5. 메인

~~~java
public class BridgeMain {
  public static void main(String[] args) {
    PrintMorseCode printMorseCode = new PrintMorseCode(new DefaultMCF());
    printMorseCode.b().l().u().e();
  }
}
//실행결과
//-··· ·-·· ··- · 
~~~

6. 이제 그 전에 애기했던 음성 모스 부호로 나타내는 요구사항으로 변경되었을 때, MorseCode와 PrintMorseCode를 추가하는 것이 아니라, 해당 부호에 대해 구현하는 클래스를 추가만 하면 되는 것이다. 여기서는 DefaultMCF에 해당되고, 음성 모스 부호인 SoundMCF를 추가해보자.

~~~java
public class SoundMCF implements MorseCodeFunction{
  @Override
  public void dot() {
    System.out.print("삣");
  }

  @Override
  public void dash() {
    System.out.print("삐~");
  }

  @Override
  public void space() {
    System.out.print(" ");
  }
}
~~~

7. 음성 모스 부호를 호출하는 메인도 아래와 같이 간단히 변경되는 것을 확인할 수 있다.

~~~java
public class BridgeMain {
  public static void main(String[] args) {
    PrintMorseCode printMorseCode = new PrintMorseCode(new FlashMCF());
    printMorseCode.b().l().u().e();
  }
}
//실행 결과
//-*-### #-*-## ##-*- #
~~~

우리는 이처럼 브릿지 패턴을 사용하게 된다면, 유지보수 측면에서 매우 간단한 수정으로 요구 사항에 따라 변경할 수 있는 것이다.



