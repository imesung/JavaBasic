## static

static은 **컴파일러에 의해 .java에서 .class 파일로 로드될 시 우선적으로 method 영역(static, class영역이라고도 부름) 메모리에 할당된다.**

*이런 이유로, 객체가 heap영역 메모리에 올라가기 전에 호출해서 사용할 수 있는 것이다.*



**static 적용 지점**

- 블록
- 변수
- 메소드
- 중첩 클래스



**static block**

- **해당 객체가 new를 통해 인스턴스화 될 때 최초 한번만 호출한다.**

~~~java
public class MyStaticClass {
  static int a = 10;
  static int b;

  //static block
  static {
    System.out.println("static block");
    b = a * 4;
  }

  public MyStaticClass() {
    System.out.println("new MyStaticClass");
  }
}

//Main
 public static void main(String [] args) {
   System.out.println("front main");
   System.out.println("a : " + MyStaticClass.a + ", b : " + MyStaticClass.b);
   MyStaticClass myStatic = new MyStaticClass();
 }

~~~

![image](https://user-images.githubusercontent.com/40616436/74586584-5bf83e00-502c-11ea-8c09-1b688644b15c.png)

- MyStaticClass.a로 인해 MyStaticClass의 Static Block을 호출하게 되는 것이다.



**static variables**

- static 변수는 Class 수준에서만 선언할 수 있다.

  - 그로인해, static 변수를 **클래스 변수**라고 칭하는 것이다.

  - 메소드에는 static 변수를 선언할 수 없다. 

- static 변수 및 블록은 선언되어 있는 순서대로 실행된다.

  ~~~java
  public class MyStaticClass {
      static int c = print();
  
      static {
          System.out.println("static block");
      }
  
      static int print() {
          System.out.println("static value");
          return 30;
      }
  
      public MyStaticClass() {
          System.out.println("new MyStaticClass");
      }
  
  }
  
  //main
  public static void main(String [] args) {
    System.out.println("front main");
    MyStaticClass myStatic = new MyStaticClass();
  }
  ~~~

  ![image](https://user-images.githubusercontent.com/40616436/74587172-bd231000-5032-11ea-8c7e-4394602b751a.png)



**static method**

- static 메소드를 호출할 때는 호출하는 메소드도 static 메소드여야 한다.

- static 메소드는 class 변수만 접근할 수 있다.

- static 메소드는 super나 this를 사용할 수 없다.

- ~~~java
  public class MyStaticMethod {
      static int a = 10;
      int b = 20;
  
      static void m1() {
          a = 20; //클래스 변수 접근 가능.
          System.out.println("from m1");
  
          //b = 10; //error : 클래스 변수가 아닌 인스턴스 변수이므로 접근 불가.
          
          //m2();  //error : static 메소드가 아니므로 접근 불가.
  
          //System.out.println(super.a); //error : super 불가.
          //System.out.println(this.b);  //eorror : this(MyStaticMethod) 불가.
      }
  
      void m2() {
          System.out.println("from m2");
      }
  }
  ~~~



**static 변수와 static 메소드는 언제 사용할까?**

예를 들어, 학교에 학생들을 추가한다고 생각해보자.

1. 추가하려는 학생들은 같은 학교에 다닌다.
2. 각 학생들은 고유의 학번을 가지고 있다.
3. 학생을 추가하고 학교의 이름을 바꿔보자.(Static 변수 활용)

~~~java
public class StaticStudent {
    private String name;
    private int stdtNo = 0;

		private static String colName = "Static University";
    private static int addStdtNo = 0;

    public StaticStudent(String name) {
        this.name = name;
        this.stdtNo = ++addStdtNo;
    }

    public void stduentInfo() {
        System.out.println("학번 : " + this.stdtNo + ", 이름 : " + this.name);
    }

		public static void updateColName(String name) {
        colName = name;
    }  
  
    static{
        System.out.println("학생을 추가하자!!");
    }
}

//Main
public static void main(String [] a) {
  StaticStudent staticStudent1 = new StaticStudent("mesung");
  StaticStudent staticStudent2 = new StaticStudent("haong");

  staticStudent1.stduentInfo();
  staticStudent2.stduentInfo();
  
  StaticStudent.updateColName("America University");

  staticStudent1.stduentInfo();
  staticStudent2.stduentInfo();
}
~~~

![image](https://user-images.githubusercontent.com/40616436/74587509-b4343d80-5036-11ea-893b-ac798d2d55bc.png)



**static 중첩 클래스(내부 클래스)**

Java에서 최상위 클래스를 static으로 만들수는 없다. 하지만 클래스를 static으로 만들수는 있다.

 **일반 클래스의 내부 클래스를 static으로 선언하면 가능하다. 즉, 중첩 클래스에서만 static을 사용할 수 있다.**

~~~java
public class MyNestedClass {
  
  //static class
  public static class NestedStaticClass {
    public static void printStaticMsg() {
      System.out.println("NestedStaticClass");
    }

    public void printMsg() {
      System.out.println("NestedStaticClass");
    }
  }

  //inner class
  public class InnerClass {
    public void print() {
      System.out.println("InnerClass");
    }
  }
}

//Main
public static void main(String [] a) {
  //static 클래스(중첩 클래스)의 static 메소드 접근
  MyNestedClass.NestedStaticClass.printStaticMsg();

  //static 클래스(중첩 클래스)생성 후 해당 클래스의 일반 메소드 접근
  MyNestedClass.NestedStaticClass nestedStaticClass = new MyNestedClass.NestedStaticClass();
  nestedStaticClass.printMsg();

  //일반 클래스의 내부 클래스 생성 후 메소드 접근
  MyNestedClass.InnerClass innerClass = new MyNestedClass().new InnerClass();
  innerClass.print();
}
~~~

**이 소스의 결론은 static 클래스 및 변수는 static인 것만 접근이 가능하다. 왜? Method 영역에 올라가있는 것만 접근이 가능하니깐!**



