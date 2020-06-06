## 어댑터 패턴

> 기계, 기구등을 다른 목적으로 사용하기 위한 부가적인 기구를 말한다.
>
> Ex. 우리 나라는 전기를 220v 사용하는데, 110v 사용하는 나라로 여행을 갈 때면 어댑터가 필요하다.



**기본 설계**

<img src="https://user-images.githubusercontent.com/40616436/79763140-9fd62f00-835e-11ea-8e25-1f37bbfe379e.png" alt="image" style="zoom:50%;" />

- Adaptee라는 알고리즘을 Adapter라는 기능을 통해서 원하는 기능으로 변형하는 것이다.



**구체적인 예제**

*요구사항*

- 두 수에 대한 다음 연산을 수행하는 객체를 만들어라.
  - 수의 두 배의 수를 반환 : twiceOf(Float) : Float
  - 수의 반(1/2)의 수를 반환 : halfOf(Float) : Float
- 구현 객체 이름은 'Adapter'
- Math 클래스에서 두 배와 절반을 구하는 함수는 이미 구현되어있다.



*요구사항 설계*

<img src="https://user-images.githubusercontent.com/40616436/79767422-45d86800-8364-11ea-8a34-210333e3361b.png" alt="image" style="zoom:50%;" />



*소스*

~~~java
//Math
public class Math {
    public static double twoTime(double num) {
        return num*2;
    }

    public static double half(double num) {
        return num/2;
    }
}

//Adapter
public interface Adapter {
  public Float twiceOf(Float f);
  public Float halfOf(Float f);
}

//AdapterImpl
public class AdapterImpl implements Adapter {
  @Override
  public Float twiceOf(Float f) {
    return (float) Math.twoTime(f.doubleValue());

  }
  @Override
  public Float halfOf(Float f) {
    return (float) Math.half(f.doubleValue());
  }
}


//Main
public static void main(String [] args) {
  Adapter adapter = new AdapterImpl();

  System.out.println(adapter.twiceOf(100f));
  System.out.println(adapter.halfOf(100f));
}
~~~

<img src="https://user-images.githubusercontent.com/40616436/79767829-df077e80-8364-11ea-82f4-f77b1c7b48fa.png" alt="image" style="zoom:50%;" />



**알고리즘(Math)를 변경해보자**

- Math 클래스에 두 배를 구할 수 있는 새로운 함수가 추가되었다. 새로 추가된 알고리즘을 이용할 수 있도록 수정하자.
- 절반을 구하는 기능에서 로그찍는 기능을 추가하자.



*Math 클래스에 두 배를 구할 수 있는 새로운 함수 활용*

~~~java
public class Math {
	...

  //두 배를 구할 수 있는 새로운 함수  
  public static Double doubled(Double d) {
    return d*2;
  }
}

//AdapterImpl
public class AdapterImpl implements Adapter {

  @Override
  public Float twiceOf(Float f) {
    //변경
    return Math.doubled(f.doubleValue()).floatValue();
  }

  ...
}
~~~

- Adapter와 Main을 수정하지 않고 알고리즘(Math)만 변경하여 새로운 함수를 활용할 수 있었다.



*절반을 구하는 로직에 로그를 찍어보자*

~~~java
//1. Math
public class Math {
	...

  public static Double doubled(Double d) {
		//System.out.println("절반 함수 호출 로그 ");
    return d*2;
  }
}

//2. AdapterImpl
public class AdapterImpl implements Adapter {
  @Override
  public Float twiceOf(Float f) {
    System.out.println("절반 함수 호출 로그 ");
    return Math.doubled(f.doubleValue()).floatValue();
  }
  ...
}
~~~

- 1번에 로그를 심어놓게 되면, Math는 우리 뿐만 아니라 다른 쪽에서도 사용할 수가 있어 의도치 않게 로그를 찍는 경우가 발생하게된다.
- 2번처럼 재정의한 알고리즘 속에서만 로그를 찍어 우리만 볼 수 있도록 하는 것이 옳다.



**알고리즘을 요구사항에 맞춰 변경하여 사용할 수 있다면 어댑터 패턴에 이해를 한 것이다.**

- 즉, 알고리즘에 대한 변경은 인터페이스 구현체에서 받아 커스텀 후 메인에서 해당 인터페이스를 접근하여 사용하는 구조이다.
- 인터페이스 구현체(Adapter)가 각 개발자의 커스텀을 진행하는 역할을 하는 것이다.