## 프로토타입 패턴

> 프로토타입 패턴을 활용하면, **복잡한 인스턴스를 복사**할 수 있다.
>
> 프로토타입은 **생산 비용이 높은 인스턴스를 복사하여 쉽게 생성**할 수 있도록 하는 패턴이다.



**인스턴스 생산 비용이 높은 경우**

- **종류가 너무 많아** 클래스로 정리하기 어려운 경우
- **클래스로부터 인스턴스 생성이 어려운 경우**



**기본 설계**

<img src="https://user-images.githubusercontent.com/40616436/80905840-093a4280-8d4e-11ea-86ed-7742df2dd874.png" alt="image" style="zoom:50%;" />



**요구 사항**

- 일러스트레이터와 같은 그림 그리기 툴을 개발중이다.
- 어떤 모양(shape)을 그릴 수 있도록 하고 복사 붙여넣기 기능을 구현해라.



**소스**

~~~java
//Shape
public class Shape implements Cloneable{
  private String id;
  public String getId() {return id;}
  public void setId(String id) {this.id = id;}
}

//Circle
public class Circle extends Shape{
  private int x,y,r;

  public Circle(int x, int y, int r) {
    this.x = x;
    this.y = y;
    this.r = r;
  }

  public Circle copy() throws CloneNotSupportedException {
    Circle circle = (Circle) clone();	//Cloneable.clone()
    return circle;
  }

  //getter, setter
  ...
}

//Main
public static void main(String [] args) throws CloneNotSupportedException {
  Circle circle1 = new Circle(1, 1, 3);
  Circle circle2 = circle1.copy();
  System.out.println(circle1.getX() + ", " + circle1.getY() + ", " + circle1.getR());
  System.out.println(circle2.getX() + ", " + circle2.getY() + ", " + circle2.getR());
}
~~~

**실행결과**

<img src="https://user-images.githubusercontent.com/40616436/80907197-c6796a00-8d4f-11ea-877c-a2813fc83650.png" alt="image" style="zoom:50%;" />





**추가 요구 사항**

- 복사 후 붙여넣기를 하는 경우 두 도형이 안겹치도록 옆으로 이동하게 해라.



**소스**

~~~java
//copy할 시 이동하여 그려주도록 한다.
public Circle copy() throws CloneNotSupportedException {
  Circle circle = (Circle) clone();
  circle.x = x+1;
  circle.y = y+1;
  return circle;
}
~~~

**실행결과**

<img src="https://user-images.githubusercontent.com/40616436/80907267-b1510b00-8d50-11ea-960c-922bb01cbb2b.png" alt="image" style="zoom:50%;" />



**정리**

- Shape이라는 클래스가 Cloneable의 clone() 함수를 가지고 있는 인터페이스 역할을 하게 되고, Circle이 shape의 clone()을 할당받아 copy()를 진행할 수 있는 것이다.
- 즉, copy를 하는 기능은 생산비용이 높으므로 Cloneable의 clone()을 활용하여 인스턴스를 쉽게 복사할 수 있는 것이다.
- 프로토타입 패턴의 경우 java에서 지원하지 않으면 어려운 부분이 없지 않아 있다.