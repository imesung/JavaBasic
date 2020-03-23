## 리플렉션 API (1) : 클래스 정보 조회

> *사용자가 정의한 클래스들은 프로그램 실행 시, **클래스 로더** 에 의해 **사용자 정의 클래스 타입의 Class 인스턴스들이 힙 영역**에 올라간다.*



**힙 영역에 올라가 있는 사용자 정의 클래스 타입의 Class 인스턴스를 꺼내보자 (3가지 방법)**

~~~java
//Book => 사용자가 정의한 클래스
public class App {
  public static void main( String[] args ) throws ClassNotFoundException {
    //1. Book.class로 Class 인스턴스에 접근
    Class<Book> bookClass = Book.class; 
		
    //2. 사용자 애플리케이션에 생성된 인스턴스를 사용하여 Class 인스턴스에 접근
    Book book = new Book();
    Class<? extends Book> aClass = book.getClass();

    //3. 사용자 정의 클래스의 위치로 Class 인스턴스 접근
    Class<?> aClass1 = Class.forName("org.example.Book");
  }
}
~~~



**Class 인스턴스를 활용하여 사용자 정의 클래스의 필드들을 접근해보자**

~~~java
//사용자 정의 클래스
public class Book {
  private static String B = "BOOK";
  private static final String C = "BOOK";
  private String a = "a";
  public String d = "d";
  protected String e = "e";
  public Book() {}
  public Book(String a, String d, String e) {
    this.a = a;
    this.d = d;
    this.e = e;
  }
  private void f() {
    System.out.println("F");
  }
  public void g() {
    System.out.println("g");
  }
  public int h () {
    return 100;
  }
}
~~~



1. public한 필드만 가져오자

   ~~~java
   public static void main( String[] args ) throws ClassNotFoundException {
     Class<Book> bookClass = Book.class;
   
     //getFields는 public한 필드만 가져온다.
     Arrays.stream(bookClass.getFields()).forEach(System.out::println);
   }
   ~~~

2. 접근제한자에 상관없이 모든 필드를 가져오자