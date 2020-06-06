## 책임 사슬 패턴

> 다양한 처리 방식을 유연하게 처리할 수 있다.

어떤 요청이 그 요청을 담당하는 객체에 들어오면 요청에 대해서 담당하는 것이 일반적이지만, 만약 해당 요청을 수행하지 못하는 객체라면 이 요청을 수행하기 위해 객체들을 연결 리스트와 같은 사슬 방식으로 연결하여 다음 객체에게 책임을 넘기는 형태를 말한다.

**이는 요청을 보내는 객체와 이를 처리하는 객체간의 결합도를 느슨하게 하기 위한 방법이며 여러 객체에 처리 기회를 주는 것이다.**



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

---

**책임 사슬을 설계 후 동적으로 책임을 추가하는 것도 가능하다.** 

이번 예제는 공격을 할 때 방어를 하는 게임을 만들어보자. 공격이 가해졌을 때 우리가 설정한 두개의 방어 객체를 두어 현재의 공격력을 확인해보는 것이다.

~~~java
public static void main(String[] args) {
  Attack attack = new Attack(100);

  Armor armor1 = new Armor(10);	//방어1
  Armor armor2 = new Armor(15);	//방어2

  //첫번째 방어 후 두번째 방어로..
  armor1.setNextDefencse(armor2);

  //공격 개시
  armor1.defense(attack);

  //현재 공격력 확인
  System.out.println(attack.getAmount());
  
  //결과 : 75
}

//Attack
public class Attack {

  private int amount;

  public Attack(int amount) {
    this.amount = amount;
  }
  
  //getter/setter
  //...
}

//Defense
public interface Defense {
  public void defense(Attack attack);
}

//Armor
public class Armor implements Defense {

  private Defense nextDefencse;
  private int def;

  public Armor() {
  }

  public Armor(int def) {
    this.def = def;
  }

  @Override
  public void defense(Attack attack) {

    //point 1. 요청을 처리하지 못하면 다음 객체로 가는 것이 아니라 무조건 다음 객체도 요청을 한다.
    process(attack);

    if(nextDefencse != null) {
      nextDefencse.defense(attack);
    }
  }

  private void process(Attack attack) {
    int amount = attack.getAmount();
    amount -= def;
    attack.setAmount(amount);
  }

  public void setDef(int def) {
    this.def = def;
  }

  public void setNextDefencse(Defense nextDefencse) {
    this.nextDefencse = nextDefencse;
  }
}
~~~

소스를 살펴보면 그 전에 배운 책임사슬패턴과 동일하지만 약간 다른 점이 있다면, ***point 1.*** defense 메소드에서 처리할 때 우리가 배운 책임 사슬 패턴은 해당 요청을 처리하지 못할 때 다음 객체에게 책임을 전임했는데 이 부분은 무조건 다음 객체에게도 책임을 전임하는 것을 볼 수 있다. **즉, 책임 사슬 패턴은 관점에 따라서 다양하게 사용할 수 있다는 것을 확인할 수 있다.**

***그러면 이제 방어를 추가하여 동적으로 책임이 추가되는 것을 확인해보자.***

~~~java
public static void main(String[] args) {
  Attack attack = new Attack(100);
  
  Armor armor1 = new Armor(10);
  Armor armor2 = new Armor(15);

  armor1.setNextDefencse(armor2);
  //첫번째 공격
  armor1.defense(attack);
  System.out.println(attack.getAmount());


	//방어 추가로 동적으로 책임 할당
  Defense defense = new Defense() {
    @Override
    public void defense(Attack attack) {
      int amount = attack.getAmount();
      attack.setAmount(amount -= 50);
    }
  };

  //게임 도중 방어 갑옷 추가 착용(현재 사슬에서 다음 사슬을 추가적으로 세팅)
  armor2.setNextDefencse(defense);

  //두번째 공격
  attack.setAmount(100);
  
  //armor1 방어, armor2 방어, defense 방어
  armor1.defense(attack);

  System.out.println(attack.getAmount());
	//결과 : 25
}
~~~

첫번째 공격을 한 후 새로운 공격인 두번째 공격을 할 때는, 방어가 armor1과 armor2만 있는 것이 아닌 main에서 Defense의 defense() 메소드를 정의한 것까지 방어루 추가하였다.

그리하여 방어 순(책임 사슬)은 armor1 > armor2 > defense가 되었고, 100이라는 공격을 주었을 때 남아있는 공격력은 25라는 결과를 확인할 수 있는 것이다.

**여기에서 우리가 알 수 있는 것은, 책임 사슬 패턴을 이용하여 요청을 보내는 클라이언트와 결합도를 느슨하게 만들 수 있으며 이 패턴을 활용할 시 책임도 원활하게 추가할 수 있는 것이다. 여기에서는 interface인 defense를 main에서 정의했지만, armor3이라는 객체를 생성하거나 MagicArmor라는 객체를 만들어 추가하는 것도 원활할 것이다.**

