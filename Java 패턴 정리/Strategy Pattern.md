## 스트레티지 패턴

**인터페이스**

- 기능에 대한 **선언과 구현의 분리**를 말한다.
- 기능을 통로라고 말할 수 있다.
- Ex.

~~~java
//AInterface
public interface AInterface {
    public void funcA();
}


//AInterfaceImpl
public class AInterfaceImpl implements AInterface {
    @Override
    public void funcA() {
        System.out.println("AAA");
    }
}


//Main
public static void main(String [] args) {
  AInterface aInterface = new AInterfaceImpl();
  aInterface.funcA();
}

~~~

- 선언(AInterface)과 구현(AInterfaceImpl)을 분리하여 Main에서 활용하는 것을 확인할 수 있다.



**델리게이트**

- 기능적인 부분을 다른 이에게 위임하여 사용한다.
- Ex.

~~~java
public class AObj {

    AInterface aInterface;

    public AObj(AInterface aInterface) {
        this.aInterface = aInterface;
    }

    public void funcAA() {

        //1. 일반적인 기능 구현
        //System.out.println("AAA");
        //System.out.println("AAA");

        //2. 기능 구현을 위임
        aInterface.funcA();
        aInterface.funcA();
    }
}


//Main
public static void main(String []) {
  AObj aObj = new AObj(aInterface);
  aObj.funcAA();
}
~~~

- AObj.funcAA()는 1번처럼 기능을 직접 구현하여 사용하는 것이 아닌 2번처럼 AInterface에게 기능 구현을 위임하여 사용한다.



**스트레티지 패턴**

- 여러 알고리즘을 하나의 **추상적인 접근점(인터페이스)**을 만들어 접근 점에서 서로 **교환 가능**하도록 하는 패턴을 말한다.

<img src="https://user-images.githubusercontent.com/40616436/79758256-21768e80-8358-11ea-96ea-0e3f9ea81a87.png" alt="image" style="zoom:50%;" />



**요구사항에 맞춰 코드를 작성해보자**

*요구 사항*

- 게임에서 캐릭터와 무기를 구현해보자.
  - 무기는 두 가지 종류 : 칼, 검

~~~java
//접근점
public interface Weapon {
    public void attack();
}

//접근점의 구현체 1.
public class Sword implements Weapon{
    @Override
    public void attack() {
        System.out.println("검 공격");
    }
}

//접근점의 구현체 2.
public class Knife implements Weapon{
    @Override
    public void attack() {
        System.out.println("칼 공격");
    }
}

//클라이언트
public class GameCharacter {
    //접근점
    private Weapon weapon;

    //교환 가능
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void attack() {
        if(weapon == null) {
            System.out.println("맨손 공격");
        } else {
            //델리게이트
            weapon.attack();
        }
    }
}

//Main
public static void main(String [] args) {
  GameCharacter gameCharacter = new GameCharacter();
  gameCharacter.attack();

  gameCharacter.setWeapon(new Knife());
  gameCharacter.attack();

  gameCharacter.setWeapon(new Sword());
  gameCharacter.attack();
}
~~~

<img src="https://user-images.githubusercontent.com/40616436/79761353-453bd380-835c-11ea-9587-4d760fba8da2.png" alt="image" style="zoom:50%;" />



*유지보수*

- 무기 중에 도끼를 추가하자.

~~~java
//접근점 구현체 추가
public class Ax implements Weapon {
    @Override
    public void attack() {
        System.out.println("도끼 공격");
    }
}

//Main
public static void main(String [] args) {
  gameCharacter.setWeapon(new Ax());
  gameCharacter.attack();
}
~~~

- 무기를 추가하는데 게임 캐릭터나 Main의 다른 기능을 건드리지 않고 효율적인 추가가 가능하다.



*다이어그램*

![image](https://user-images.githubusercontent.com/40616436/79880019-6b2fa980-842a-11ea-8657-f7946db350e4.png)

