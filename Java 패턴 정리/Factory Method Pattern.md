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



**게임의 설계**

<img src="https://user-images.githubusercontent.com/40616436/80389226-c6a7de80-88e5-11ea-92dc-f0b429813cdf.png" alt="image" style="zoom:50%;" />



