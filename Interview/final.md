## final

### final의 효과

클래스나 메소드 혹은 변수에 final 제어자가 붙을 수가 있는데 각각 다음과 같은 제한이 생긴다.

- final 클래스
  - 다른 클래스에서 상속을 하지 못 한다.
  - **클래스가 final일 시 해당 클래스의 내부 메소드도 final로 선언되나, 변수의 경우는 final 개념을 갖지 않는다.**
- final 메소드
  - 상속 받은 클래스에서 오버라이딩을 못한다.
- final 변수(인스턴스 변수 == 전역 변수)
  - 선언과 동시에 초기화하거나 생성자 내에서 값을 지정할 수 있다. 이 또한 그 이후에는 수정이 불가하다.
- final 변수(지역 변수)
  - 선언과 동시에 초기화하거나 메소드를 실행 시 값을 지정할 수 있다. 단, 그 이후에는 수정이 불가하다.
- static final 변수(클래스 변수)
  - 선언과 동시에 값을 지정하여야하며 그 이후에는 수정이 불가하다.



**소스를 통해 예를 살펴보자**

```java
//1. final 클래스
final class Book {
    
    //2. 클래스 변수
    private static final String title = "Harry Potter";
    //3. 인스턴스 변수
    private final String subTitle = "philosophers stone";
    private final String author;
    
  	//6. final 변수가 아님
  	static int notFinal = 20;	//변경 가능
  
  	//7. final method
  	void finalMethod() {}	//재정의 불가
  
  	//3-1
    public Book() {
        author = "harry";
    }
    
    //4. final 메소드
    public final void func() {
        
        //5. 지역 변수
        final int page = 200;
        final int price;
        
        price = 1000;
        //이제 이 이후에는 price 수정이 불가하다.
    }
    
}
```

1. Book 클래스는 final 클래스이므로 **상속 받아서 파생 클래스를 만들지 못한다.**
2. 클래스 변수는 **반드시 선언 시 초기화를 해주어야 하며 다른 곳에서는 초기화를 하지 못한다.**
3. 클래스 변수와 다르게 인스턴스 변수는 선언 시 초기화를 해주거나, 
   1. **생성자를 통해 값을 지정해줄 수 있다.**
4. 해당 메소드는 final 메소드이므로 **상속 받는 클래스에서 오버라이딩을 하지 못한다.** (이 자체는 final 클래스와 별개로 예제로 보여주기 위한 것이니 신경 쓰지 않아도 된다.)
5. final 지역 변수는 일반 변수와 같이 생명 scope이 동일하지만, 한번 초기화되면 그 이후 값을 변경할 수 없다. 
6. final 클래스 내에 **일반 변수는 final 특징을 갖지 않는다.**
7. final 클래스 내에 일반 메소드는 해당 클래스가 상속을 받지 못하므로 자동으로 final 특징을 갖는다.



**C++ const와 Java final 차이**

- const는 선언 시 값을 지정해야한다.
- finals은 선언 시 값을 지정하거나, 나중에 생성자 혹은 setter로 지정 가능하다.



**final 객체의 일반변수는 final 변수가 아니다!**

~~~java
public class MyFinal {
    int notFinal = 10;
    public static void main(String args[]) {
        final MyFinal t1 = new MyFinal();
        MyFinal t2 = new MyFinal();
        //t1 = t2;    //1. t1은 final Class이므로 컴파일 에러 발생

        System.out.println(t1.notFinal);
        t1.notFinal = 20;	//2. final 변수가 아니므로 변경이 가능
        System.out.println(t1.notFinal);
    }
}
~~~



