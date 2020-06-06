## 데코레이터 패턴

> 동적으로 책임 추가가 필요할 때 데코레이터 패턴을 사용할 수 있다.

**기본 설계**

<img src="https://user-images.githubusercontent.com/40616436/82755192-0c849380-9e0d-11ea-81ee-3da3f478d923.png" alt="image" style="zoom:50%;" />

- Component : 실질적인 인스턴스를 컨트롤하는 역할
- ConcreteComponent : Component의 실질적인 인스턴스 부분이며, 책임의 구현 부분
- Decorator : Component와 Concrete Decorator를 동일 시 하도록 해주는 역할
- ConcnreteDecorator : 실질적인 장식 인스턴스 및 정의이며 추가된 책임의 구현 부분

---

**실습 요구 사항**

커피 제조 방법

- 에스프레소 : 커피의 기본
- 아메리카노 : 에스프레소 + 물
- 카페라떼 : 에스프레소 + 스팀밀크
- 헤이즐넛 : 아메리카노 + 헤이즐넛 시럽
- 카페모카 : 카페라떼 + 초콜릿
- 캬라멜 마끼야또 : 카페라떼 + 카라멜 시럽

<img src="https://user-images.githubusercontent.com/40616436/82755374-ff1bd900-9e0d-11ea-9028-4228e34a7a49.png" alt="image" style="zoom:50%;" />

- IBeverage : 커피 가격 산출
- Base : 커피 가격 산출의 실질적인 책임 부분
- AbstAdding : 추가된 어떤 것들을 가지고 있음
- Espresso : 추가 재료들을 구현

총 가격을 산출하는 프로그램을 짜보자.

---

소스를 통해 살펴보자.

**Base**

~~~java
public class Base implements IBeverage {
  @Override
  public int getTotalPrice() {
    return 0;
  }
}
~~~

**IBeverage**

~~~java
public interface IBeverage {
  int getTotalPrice();
}
~~~

**AbstAdding**

~~~java
abstract public class AbstAdding implements IBeverage {
  private IBeverage base;

  public AbstAdding(IBeverage base) {
    super();
    this.base = base;
  }

  @Override
  public int getTotalPrice() {
    return base.getTotalPrice();
  }

  public IBeverage getBase() {
    return base;
  }

  public void setBase(IBeverage base) {
    this.base = base;
  }
}
~~~

**Espresso && Milk**

~~~java
public class Milk extends AbstAdding {

  public Milk(IBeverage meterial) {
    super(meterial);
  }

  @Override
  public int getTotalPrice() {
    return super.getTotalPrice()+50;
  }
}

public class Espresso extends AbstAdding {
  static protected int espressoCount = 0;

  public Espresso(IBeverage base) {
    super(base);
  }

  @Override
  public int getTotalPrice() {
    return super.getTotalPrice()+getAddPrice();
  }

  private static int getAddPrice() {
    espressoCount++;
    int addPrice = 100;

    if(espressoCount > 1) {
      addPrice = 70;
    }
    return addPrice;
  }
}
~~~

**Main 및 결과**

~~~java
public static void main(String[] args) {
  Scanner sc = new Scanner(System.in);

  IBeverage beverage = new Base();
  boolean done = false;
  while(!done) {
    System.out.println("음료 현재 가격 : " + beverage.getTotalPrice());
    System.out.println("선택 : 1. 샷 추가 / 2. 우유 추가");
    int inputVal = sc.nextInt();
    if(inputVal == 0) {
      done = true;
    } else if(inputVal == 1) {
      beverage = new Espresso(beverage);
    } else if(inputVal == 2) {
      beverage = new Milk(beverage);
    }
  }

  System.out.println("음료 가격 : " + beverage.getTotalPrice());
  sc.close();
}

//실행 결과
음료 현재 가격 : 0
선택 : 1. 샷 추가 / 2. 우유 추가
1
음료 현재 가격 : 100
선택 : 1. 샷 추가 / 2. 우유 추가
2
음료 현재 가격 : 120
선택 : 1. 샷 추가 / 2. 우유 추가
1
음료 현재 가격 : 190
선택 : 1. 샷 추가 / 2. 우유 추가
0
음료 가격 : 190
~~~

