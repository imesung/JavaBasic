요구사항
상품 주문하기 위해 주문서진입, 결제과정을 구현하라

- 상품객체
- 고객객체 (gubun 0: 임직원, 1: 일반고객)

### 주문서 진입가능한 상태인지 체크한다.

- 상품을 주문하기 위한 주문서 진입 전 주문가능한 상태인지 일련의 과정을 체크해야한다.
- Method명 :orderSheetRequest(Customer customer, Product product) : Map<String, String>
- return type : Map<String, String> 결과값으로  Status(E:주문가능, S:주문불가)와 Message를 리턴받는다. 

과정

1. 주문서에 진입 시 로그인 상태인지 체크한다. checkLoginStatus() : boolean

- Cookie 값에 로그인 한 정보가 없으면 진입불가

2. 고객이 주문가능한 상태인지 체크한다. checkCustomerStatus(Customer customer) : boolean

- 일반고객과 임직원고객에 대해 주문가능 상태 체크 알고리즘이 다르다
- 일반고객일 경우 블랙컨슈머 여부를 체크하며 블랙컨슈머이면 주문 불가
- 임직원은 보유 포인트 여부를 체크하며 보유 포인트가 0원일 경우 주문 불가

3. 상품에 대한 재고를 체크한다.  checkProduct(Product product) : boolean

- 재고가 없으면 주문 불가 

4. 사은품에 대한 재고를 체크한다. checkGift(String giftNo) : boolean

- 일반 고객은 사은품 재고를 체크하여 사은품 재고가 없으면 주문 불가
- 임직원은 사은품이 있으면 주문 불가


### 주문서 주문가능한 상태일 경우 결제한다.

- Method : pay(long prc)
- return type : void

1. 결제수단은 카카오페이, 신용카드, 현금결제, 포인트사용 중 가능하다.
2. 포인트 사용은 보유 포인트가 3000원 이상 일경우에만 가능하며 상품가격이 3000원 이상일 경우는 나머지 금액은 카카오페이, 신용카드, 현금결제 중 결제한다.



- 상품객체

```java
public class Product {
    public long prdCd;      // 상품코드
    public long prdPrc;     // 상품가격
    public long giftNo;     // 사은품 코드 - 0 일 경우 사은품 없음!
    public int stock;       // 재고

    public Product(long prdCd, long prdPrc, long giftNo, int stock) {
        this.prdCd = prdCd;
        this.prdPrc = prdPrc;
        this.giftNo = giftNo;
	this.stock = stock;
    }
}
```

- 고객객체

```java
public class Customer {

    public String userId;		//ID
    public String userName;		//name
    public int gubun;			//0: 임직원, 1: 일반고객
    public long point;			// 보유포인트
    public boolean blackConsumerFlg;	//
    
    public Customer(String userId, String userName, int gubun, long point, boolean blackConsumerFlg) {
        this.userId = userId;
        this.userName = userName;
        this.gubun = gubun;
        this.point = point;
        this.blackConsumerFlg = blackConsumerFlg;
    }
}
```

- 

```java
public class shopMain {

    public static void main(String [] args) {

        Customer customer = new Customer("dynee313", "dy", 0, 0, false);    	  //도연 임직원
        //Customer customer = new Customer("imesung", "hs", 0, 10000, false);     //혜성 임직원
        //Customer customer = new Customer("mike6321", "jw", 1, 2000, false);     //준우 일반고객
        //Customer customer = new Customer("leetsh", "sh", 2, 0, true);      	  //상현 일반고객 블랙컨슈머
	
	Product product1 = new Product(111111, 20000, 12345, 10);	// 상품코드, 가격, 사은품코드, 재고
	Product product2 = new Product(222222, 10000, 0, 40);	
	Product product2 = new Product(222222, 10000, 0, 0);	
    }
}
```