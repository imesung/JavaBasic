## 팩토리 메소드 패턴

> 팩토리 메소드 패턴에서 ***템플릿 메소드 패턴의 사용***됨을 알 수 있다.
>
> 팩토리 메소드 패턴에서의 구조와 구현의 분리를 이해하고 ***구조와 구현의 분리*** 장점을 알 수 있다.



**템플릿 메소드 패턴 간략히 다시보기**

- 어떤 알고리즘이 여러 단계의 step으로 나누어질 수 있다면, 여러 step으로 메소드를 선언하고 하위 클래스에서 해당 메소드의 구현을 하는 패턴이다. 



**기본 설계**

<img src="https://user-images.githubusercontent.com/40616436/80381247-a2df9b00-88db-11ea-8c2c-9a49ff905cd0.png" alt="image" style="zoom:50%;" />

1. Product를 생성하는 DefaultProduct가 있고, DefaultProduct에서 사용하려는 Product들을 구현하게 된다.
2. 템플릿 메소드 패턴을 사용하는데, Creator가 여러 step으로 나누어져 있을 시 DefaultProductCreator에서 구현한다.



**요구 사항**

- 게임 아이템과 아이템 생성을 구현해라.
  - 아이템을 생성하기 전에 데이터베이스에서 아이템 정보를 요청한다.
  - 아이템을 생성한 후 아이템 복제 등의 불법을 방지하기 위해 데이터베이스에 아이템 생성 정보를 남긴다.
- 아이템을 생성하는 주체를 ItemCreator로 짓는다.
- 아이템은 item이라는 interface로 다룰 수 있도록 한다.
  - item은 use함수를 기본 함수로 갖고 있다.
- 현재 아이템의 종류는 체력 회복 물약, 마력 회복 물약이 있다.



**패키지 구조**

<img src="https://user-images.githubusercontent.com/40616436/80390637-aa0ca600-88e7-11ea-94eb-68c40d0af84d.png" alt="image" style="zoom:67%;" />

- 팩토리 메소드 패턴의 구조 측면 framework라는 패키지로 나누고, 구현 측면은 모두 concrete 패키지로 나눈다.



**개발 진행**

1. 알고리즘 스텝을 나눈다.

   - 아이템을 생성하기 전에 DB에서 아이템 정보를 요청한다.
   - 아이템을 생성한 후 아이템 복제 등의 불법을 방지하기 위해 데이터베이스에 아이템 생성 정보를 남긴다.
   - 아이템을 생성한다.

   ~~~java
   //게임의 구조
   public abstract class ItemCreator {
     //알고리즘을 스텝으로 나눈 것 - 템플릿 메소드 패턴
     public Item create() {
       Item item;
   
       requestItemsInfo();
       item = createItem();
       createItemLog();
   
       return item;
     }
     
     //step 1. 아이템을 생성하기 전에 DB에서 아이템 정보를 요청한다.
     abstract protected void requestItemsInfo();
     //step 2. 아이템을 생성한 후 아이템 복제 등의 불법을 방지하기 위해 데이터베이스에 아이템 생성 정보를 남긴다.
     abstract protected void createItemLog();
     //step 3. 아이템을 생성하는 알고리즘
     abstract protected Item createItem();
   }
   
   //게임의 구현1(마력 회복하기)
   public class MpCreator extends ItemCreator {
     @Override
     protected void requestItemsInfo() {
       System.out.println("DB에서 마력 회복 물약의 정보를 가져온다.");
     }
   
     @Override
     protected void createItemLog() {
       System.out.println("마력 회복 물약을 새로 생성했다." + new Date());
     }
   
     @Override
     protected Item createItem() {
       return new MpPotion();
     }
   }
   
   //게임의 구현2(체력 회복하기)
   public class HpCreator extends ItemCreator {
     @Override
     protected void requestItemsInfo() {
       System.out.println("DB에서 체력 회복 물약의 정보를 가져온다.");
     }
   
     @Override
     protected void createItemLog() {
       System.out.println("체력 회복 물약을 새로 생성했다." + new Date());
     }
   
     @Override
     protected Item createItem() {
       return new HpPotion();
     }
   }
   ~~~

2. 물약을 만든다.

   ~~~java
   //물약의 구조
   public interface Item {
     public void use();
   }
   
   //물약의 구현1(마력 회복)
   public class MpPotion implements Item {
     @Override
     public void use() {
       System.out.println("마력 회복");
     }
   }
   
   //물약의 구현2(체력 회복)
   public class HpPotion implements Item {
     @Override
     public void use() {
       System.out.println("체력 회복");
     }
   }
   ~~~

*실행 결과*

![image](https://user-images.githubusercontent.com/40616436/80390251-20f56f00-88e7-11ea-95ba-a1c9f12d305b.png)



**게임의 설계**

<img src="https://user-images.githubusercontent.com/40616436/80389226-c6a7de80-88e5-11ea-92dc-f0b429813cdf.png" alt="image" style="zoom:50%;" />



**요구사항을 좀 더 변경해보자**

- Power Potion이라는 아이템이 추가한다.
  - 이 때, 팩토리 메소드 패턴을 사용하지 않고, Item과 ItemCreator에 직접적으로 기능을 구현하였다면 외부 호출하는 부분에 영향을 가 많은 부분의 수정이 필요할 것이다.
  - 하지만, 팩토리 메소드 패턴을 사용함으로써, 아래와 같이 간단히 추가하여 요구사항을 만족시킬 수 있는 것이다.



**변경된 설계**

<img src="https://user-images.githubusercontent.com/40616436/80389927-b5ab9d00-88e6-11ea-8502-f77b8aaa4e84.png" alt="image" style="zoom:50%;" />



**변경된 소스**

~~~java
//게임의 구현3(파워 상승하기)
public class PowerPotion implements Item {
  @Override
  public void use() {
    System.out.println("파워 상승");
  }
}
//물약의 구현3(파워 물약)
public class PowerCreator extends ItemCreator {
  @Override
  protected void requestItemsInfo() {
    System.out.println("DB에서 파워 물약의 정보를 가져온다.");
  }

  @Override
  protected void createItemLog() {
    System.out.println("파워 물약을 새로 생성했다." + new Date());
  }

  @Override
  protected Item createItem() {
    return new PowerPotion();
  }
}
~~~



