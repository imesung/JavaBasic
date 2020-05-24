## 방문자 패턴

> 방문자 패턴을 이용하여 객체에서 처리를 분리해서 사용할 수 있다.

**기본 설계**

<img src="https://user-images.githubusercontent.com/40616436/82755850-57080f00-9e11-11ea-9c12-2b0c88d37ecc.png" alt="image" style="zoom:50%;" />

**예제 소스**

**Visitor 및 Visitable**

~~~java
public interface Visitor {
  public void visit(Visitable visitable);
}

public interface Visitable {
  public void accept(Visitor visitor);
}
~~~

**VisitorA 및 VisitableA**

~~~java
public class VisitorA implements Visitor{

  private int ageSum;

  public VisitorA() {
    ageSum = 0;
  }

  @Override
  public void visit(Visitable visitable) {
    if(visitable instanceof VisitableA) {
      ageSum += ((VisitableA) visitable).getAge();
    } else {
      //...
    }
  }

  public int getAgeSum() {
    return ageSum;
  }
}

public class VisitableA implements Visitable {

  private int age;

  public VisitableA(int age) {
    super();
    this.age = age;
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }
}

~~~

**Main 및 결과**

~~~java
public static void main(String[] args) {
  ArrayList<Visitable> visitables = new ArrayList<>();
  visitables.add(new VisitableA(1));
  visitables.add(new VisitableA(2));
  visitables.add(new VisitableA(3));
  visitables.add(new VisitableA(4));
  visitables.add(new VisitableA(5));

  Visitor visitor = new VisitorA();

  //int ageSum = 0;

  for (Visitable visitable : visitables) {
    visitable.accept(visitor);
    //ageSum += ((VisitableA)visitable).getAge();
  }

  System.out.println(((VisitorA)visitor).getAgeSum());
  //System.out.println(ageSum);
}
~~~

주석에서 처리한 것 처럼 for문에서 간단하게 ageSum 변수를 이용해 getAge()를 가져와 덧셈결과를 호출할 수 있지만, 이 방식을 사용하게 되면 외부에 VisitableA의 구현이 노출되는 것으로 해당 구현을 활용하여 외부에서 더 복잡한 로직을 구성하여 for문에 추가할 수도 있다. 이런 경우 추후에 유지보수 측면에서 매우 비효율적인 결과를 나타낼 수 있으므로, Visitor 패턴을 활용하여 age들의 덧셈을 진행해주는 메소드(accept())만을 호출하여 유지보수 측면에서 효율적으로 나타낼 수 있다. (Why? 메소드명이 가시적으로 딱 보이므로!)