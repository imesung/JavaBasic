## static과 final의 조합

*들어가기에 앞서 final과 static에 대해서 간략히 살펴보자*

### **final**

> final은 해당 오브젝트가 단 한번 할당할 수 있음을 의미한다.

- final 변수
  - 해당 변수는 생성자나 대입연산자를 통해 단 한번만 할당할 수 있다. 
- final 메소드
  - 해당 메소드는 오버라이드하거나 **숨길 수 없다.**
- final 클래스
  - 해당 클래스는 상속할 수 없다.



**final의 주의 사항**

1. final 변수는 반드시 상수가 되는 것은 아니다. **단 한 번만 할당하는 의미이다.**

   ~~~java
   public class FinalTest {
       private final int value;
       public FinalTest(int value) {
           this.value = value;
       }
       public int getValue() {
           return value;
       }
   }
   
   class FinalMain {
       public static void main(String [] args) {
           FinalTest f1 = new FinalTest(1);
           FinalTest f2 = new FinalTest(2);
           FinalTest f3 = new FinalTest(3);
   
           System.out.println(f1.getValue());
           System.out.println(f2.getValue());
           System.out.println(f3.getValue());
       }
   }
   
   //결과: 1 2 3
   ~~~

   - **FinalTest의 인스턴스들인 f1, f2, f3은 각기 다른 value 값들을 가지고 있으므로, 클래스 레벨에서 통용되는 상수라고 말할 수가 없는 것이다.**

2. private 메소드와 final 클래스의 모든 메소드는 final을 따로 명시하지 않아도 final의 개념을 가지고 있다.

   - **이유는 오버라이드가 불가능하기 때문이다.**



### static

> static은 해당 데이터의 메모리 할당을 컴파일 시간에 하는 것을 의미한다.

- static 변수
  - 클래스 변수라고도 부르며, 해당 클래스 타입들은 모두 같은 메모리에 있는 static 변수를 공유한다.
  - 특정 인스턴스에 종속되지 않으며, 인스턴스를 만들지 않고 사용 가능하다.
- static 메소드
  - 클래스 메소드라고도 부르며, 오버라이드가 불가능하다.
  - 상속 클래스에서 보여지지 않는다.
- static 블록
  - 클래스 내부에 만들 수 있는 초기화 블록이며, 클래스가 초기화될 때 실행되고, main()보다 먼저 수행된다.
- static 클래스
  - Java에서 최상위 클래스를 static으로 만들 수 없으나, 일반 클래스의 내부 클래스를 static으로 선언 시 가능하다.
- sttaic import
  - 다른 클래스에 존재하는 static 멤버들을 불러올 때 사용한다.



### static과 final의 조합은 도대체 무엇인가?

**클래스 멤버 변수를 final로 지정한다.**

- 클래스에서 사용할 멤버 변수의 용도를 고정시키겠다는 뜻이다.

- **즉, 해당 클래스를 사용할 때 변하지 않고 일관된 값으로 사용할 것을 멤버 상수(static final)로 지정하는 것이다.**

  - Ex. *기독교* 클래스에서 멤버 변수 *신의 이름*을 만들어 사용하면 해당 클래스를 사용하는 어느 곳이든 *신의 이름은 하나님*일 것이다.

  - Ex. *중학교 성적* 클래스에서 과목 최대 점수 변수를 만든다면 *100*일 것입니다. **(100점은 고정된 점수다.)**

- 이렇게 멤버 변수가 변하지 않고 일관된 값을 사용할 때는 새로운 메모리를 할당하지 않고 한 메모리 공간을 공유하면 되기 때문에, static과 final을 조합하여 사용하는 것이다.

  - **즉, 변하지 않는다는 의미는 final에 의해서 적용시킬 수 있고, 새로운 메모리를 할당하지 않고 공유한다는 의미는 static에 의해서 적용시킬 수 있는 것이다.**

  ~~~java
  public static final String GOD_NAME = "하나님";
  ~~~



**그렇다면 public static final과 private static final의 차이는 무엇이지?**

-  public static final

  - public 접근 제한자에 의해서 외부에서도 해당 오브젝트에 접근하여 값을 활용할 수 있다.

    ~~~java
    public class FinalTest {
      public static final String PU_STATIC = "public static";
    }
    
    class Main {
      public static void main(String [] args) {
        System.out.println(FinalTest.PU_STATIC);
      }
    }
    ~~~

- private static final

  - private  접근 제한자에 의해서 외부에서는 접근이 불가능하고, 해당 클래스 내부에서만 값을 활용할 수 있다.



### final 멤버 변수에 static을 사용하지 않는 경우가 있는가?

놀랍게도 DI기법을 사용하여 클래스 내부에 외부 클래스 의존성을 집어넣는 경우가 있다.

~~~java
public class MyController {
  private final MyService myService;
  
  @Autowired
  public MyController(MyService myService) {
    this.myService = myService;
  }
}
~~~

- MyService를 static final로 선언하게 되면, 선언과 동시에 초기화를 진행해야 하기 때문에 외부 클래스의 의존성을 받을 수가 없다.
- 해당 소스는 스프링 프레임워크에서 Mycontroller 생성자에 의존성 주입을 하는 예시이다.
  - 즉, bean으로 등록되어 있는 MyService를 애플리케이션 컨텍스트에서 가져와 MyController 내부에 있는 MyService에 의존성을 주입하는 것이다.
