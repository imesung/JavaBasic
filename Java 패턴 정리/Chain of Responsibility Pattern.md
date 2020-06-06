## 책임 사슬 패턴

> 다양한 처리 방식을 유연하게 처리할 수 있다.

어떤 요청이 그 요청을 담당하는 객체에 들어오면 요청에 대해서 담당하는 것이 일반적이지만, 만약 해당 요청을 수행하지 못하는 객체라면 이 요청을 수행하기 위해 객체들을 연결 리스트와 같은 사슬 방식으로 연결하여 다음 객체에게 책임을 넘기는 형태를 말한다.

**이는 요청을 보내는 객체와 이를 처리하는 객체간의 결함도를 느슨하게 하기 위한 방법이며 여러 객체에 처리 기회를 주는 것이다.**



**기본 설계**

![image](https://user-images.githubusercontent.com/40616436/83944972-68eea680-a842-11ea-9b52-b946a300a1a0.png)



**예제 : 사칙연산 프로그램**

~~~java
//Main
public class ChainMain {
  public static void main(String[] args) {
    Calculator plus = new PlusCalculator();
    Calculator sub = new SubCalculator();

    plus.setNextCalculator(sub);
    Request request1 = new Request(1, 2, "+");
    Request request2 = new Request(10, 2, "-");

    plus.process(request1);
    plus.process(request2);
  }
}

//Calculator
public abstract class Calculator {

  private Calculator nextCalculator;
  public void setNextCalculator(Calculator nextCalculator) {
    this.nextCalculator = nextCalculator;
  }

  public boolean process(Request request) {
    if(operator(request)) {
      //=
      return true;
    } else {
      if(nextCalculator != null) {
        return nextCalculator.process(request);
      }
    }
    return false;
  }

  abstract protected boolean operator(Request request);
}

//PlusCalculator
public class PlusCalculator extends Calculator {

  @Override
  protected boolean operator(Request request) {
    if(request.getOperator().equals("+")) {
      int a = request.getA();
      int b = request.getB();
      int result = a + b;
      System.out.println(a + "+" + b + "=" + result);
      return true;
    }
    return false;
  }
}

//SubCalculator
public class SubCalculator extends Calculator {
  @Override
  protected boolean operator(Request request) {
    if(request.getOperator().equals("-")) {
      int a = request.getA();
      int b = request.getB();
      int result = a - b;
      System.out.println(a + "-" + b + "=" + result);
      return true;
    }
    return false;
  }
}

//Request
public class Request {

  private int a, b;
  private String operator;
  
  //getter/setter
}
~~~

setNextCalculator는 해당 요청을 처리하지 못할 경우 다음 객체에게 책임을 넘기기위한 설정이다. 

Main에서는 객체 처리 방식을 plus 다음 sub를 할 수 있도록 책임 사슬을 정해놓았다. 그러므로 plus에서 요청을 처리한 후 요청을 처리할 수 없으면 sub 객체에게 책임을 넘기는 것이다.

process 메소드는 Calculator를 상속받고 있는 PlusCalculator의 operator 메소드를 수행 후 true가 아닌 false를 리턴하게 되면, 다음 객체(nextCalculator)를 호출하여 책임을 넘겨 처리를 진행하는 것이다.