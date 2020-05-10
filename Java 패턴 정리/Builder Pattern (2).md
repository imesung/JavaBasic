## 빌더 패턴 (2)

> 많은 변수를 가진 객체의 생성을 가독성 높도록 코딩할 수 있다.
>
> 많은 인자를 가진 객체의 생성을 다른 객체의 도움으로 생성하는 패턴이다.

**복잡한 객체를 생성하는 방법과 표현하는 방법을 정의하는 클래스를 별도로 분리하여 서로 다른 표현이라도 이를 생성할 수 있는 동일한 구축 공정을 제공할 수 있도록 한다.**



**일반방식의 객체 생성**

~~~java
public static void main(String[] args) {
  Computer computer = new Computer("i7", "8g", "256g ssd");
}
~~~

일반 방식의 객체를 생성했을 때는 Computer 클래스에 모든 속성값을 필수적으로 넣고 파라미터 순서도 기억한 후 넣어야한다. 지금은 세개의 속성밖에 없지만 추 후 매우 많은 속성값이 필요할 시에는 가독성과 매우 떨어지게 되고 속성을 빼먹는 경우도 발생할 것이다.

이를 개선하기 위해 나타난 것이 빌더 패턴 방식이다.



**빌더 패턴 활용**

~~~java
//Computer
public class Computer {
  private String cpu;
  private String ram;
  private String storage;
  
  //getter/setter
  ...
}

//ComputerBuilder
public class ComputerBuilder {
    private Computer computer;
    private ComputerBuilder(){
        this.computer = new Computer("default", "default" , "default");
    }

    public static ComputerBuilder start() {
        return new ComputerBuilder();
    }

    //CPU를 시작과 동시에 넣겠다.
    public static ComputerBuilder startWithCpu(String cpu) {
        ComputerBuilder builder = new ComputerBuilder();
        builder.computer.setCpu(cpu);
        return builder;
    }

    public ComputerBuilder setCpu(String cpu) {
        computer.setCpu(cpu);
        return this;
    }

    public ComputerBuilder setRam(String ram) {
        computer.setRam(ram);
        return this;
    }

    public ComputerBuilder setStorage(String storage) {
        computer.setStorage(storage);
        return this;
    }

    public Computer build() {
        return this.computer;
    }
}

//Main
public static void main(String[] args) {
  Computer computer = ComputerBuilder
    .startWithCpu("i6")
    //.start()
    //.setCpu("i7")
    .setRam("8G")
    .build();

  System.out.println(computer.toString());
}
~~~

위 소스를 보았듯이, Main에서 인스턴스를 생성할 때 체인 형태로 생성하는 것을 볼 수 있을 것이다. 즉, Computer 클래스를 생성할 때 ComputerBuilder 클래스를 활용하여 체인 형태로 생성할 수 있도록 한 것이다.

이를 활용할 시에는 속성을 빼먹어도 정상적으로 인스턴스가 생성되고, 개발자는 필수적인 속성만 알고 속성을 넣는 순서를 모르더라도 컴파일 오류를 맞닥드릴 일은 없다는 것이다.

현업에서도 매우 많이 활용하고 있는 패턴이니 꼭 알고 지나가야 할 것이다.