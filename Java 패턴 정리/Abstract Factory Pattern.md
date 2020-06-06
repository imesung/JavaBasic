## 추상 팩토리 패턴

> 관련 있는 객체의 생성을 가상화할 수 있다.



**기본 설계**

<img src="https://user-images.githubusercontent.com/40616436/81836949-1c83b400-957f-11ea-96b7-5a64bc87c1c6.png" alt="image" style="zoom:50%;" />



**추상 팩토리 메소드 패턴**

- 팩토리 메소드 패턴은 객체 생성을 팩토리 클래스로 위임하여 팩토리 클래스에서 객체를 생성하게 하는데, 추상 팩토리 패턴은 **서로 관련있는 객체들을 통째로 묶어서 팩토리 클래스로 만들고, 동째로 묶은 팩토리 클래스끼리 다시 팩토리(AbstractFactory)로 만들어서 객체를 생성하는 것이다.**
- 추상 팩토리에 관련된 패키지를 확인해보면, 공통으로 묶은 패키지(abst)와 다른 스타일로 묶은 패키지(gt, sam)가 보일 것이다.

<img src="https://user-images.githubusercontent.com/40616436/81840757-66bb6400-9584-11ea-8ad9-2fe678649c9a.png" alt="image" style="zoom:50%;" />

~~~java
//팩토리
public interface BikeFactory {
  public Body createBody();
  public Wheel createWheel();
}
public interface Body {}
public interface Wheel {}

//GtBike의 관련된 클래스들을 묶음.
public class GtBikeFactory implements BikeFactory {
    @Override
    public Body createBody() {
        return new GtBody();
    }

    @Override
    public Wheel createWheel() {
        return new GtWheel();
    }
}
public class GtBody implements Body {}
public class GtSam implements Sam {}

//Main
public class AbstractFactoryMain {
  public static void main(String[] args) {
    GtBikeFactory bikeFactory = new GtBikeFactory();

    Body body = bikeFactory.createBody();
    Wheel wheel = bikeFactory.createWheel();

    System.out.println(body.getClass());
    System.out.println(wheel.getClass());
  }
}
~~~

