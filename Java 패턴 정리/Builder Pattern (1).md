## 빌더 패턴 (1)

> 복잡한 단계가 필요한 인스턴스 생성을 빌더 패턴을 통해서 구현가능하다.
>
> 복잡한 단계를 거쳐야 생성되는 객체의 구현을 서브 클래스에게 넘겨주는 패턴



**기본 설계**

<img src="https://user-images.githubusercontent.com/40616436/81491321-38a9fb80-92c8-11ea-9bff-e113224a115f.png" alt="image" style="zoom:50%;" />



**예제 설계**

<img src="https://user-images.githubusercontent.com/40616436/81491473-ee297e80-92c9-11ea-8b93-5fcf81dd5e4f.png" alt="image" style="zoom:50%;" />



**소스 확인**

~~~java
//Factory
public class ComputerFactory {
  private BluePrint print;

  public void setBlueprint(BluePrint blueprint) {
    this.print = blueprint;
  }

  public void make() {
    print.setCpu();
    print.setRam();
    print.setStorage();
  }

  public Computer getComputer() {
    return print.getComputer();
  }
}

//Blue Print
public abstract class BluePrint {
  abstract public void setCpu();
  abstract public void setRam();
  abstract public void setStorage();

  abstract public Computer getComputer();
}

//LG Gram
public class LgGramBluePrint extends BluePrint {

  //computer 참조
  private Computer computer;

  public LgGramBluePrint() {
    this.computer = new Computer("default", "default", "default");
  }

  @Override
  public void setCpu() {
    computer.setCpu("i7");
  }

  @Override
  public void setRam() {
    computer.setRam("8G");
  }

  @Override
  public void setStorage() {
    computer.setStorage("256G SSD");
  }

  @Override
  public Computer getComputer() {
    return computer;
  }
}

//Computer
public class Computer {
  private String cpu;
  private String ram;
  private String storage;
  
  //getter/setter
  ...
}

//Main
public static void main(String[] args) {
  ComputerFactory factory = new ComputerFactory();
  factory.setBlueprint(new LgGramBluePrint());
  //factory.setBlueprint(new SonyBluePrint());

  factory.make();
  Computer computer = factory.getComputer();

  System.out.println(computer.toString());

}
~~~

computer의 추가적인 속성이 필요로 하거나 필요한 속성만 가지고 있는 클래스를 만들고 싶을 땐, make()의 변경 혹은 factory에 메소드를 추가하여 요구사항에 대한 복잡한 인스턴스 생성을 쉽게 진행할 수 있는 것이다.

