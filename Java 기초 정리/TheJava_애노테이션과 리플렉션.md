## 애노테이션과 리플렉션

### Java의 중요 애노테이션

- @Retention : 해당 애노테이션을 언제까지 유지할 것인가? source, class, runtime
- @Inherited : 해당 애노테이션을 하위 클래스까지 전달한다.
- @Target : 어디에 사용할 수 있는가? type, field, method ...



### 리플렉션을 사용하여 애노테이션들의 정보를 살펴보자

~~~java
//custom annotation 1
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
@Inherited
public @interface MyAnnotation {
  String value() default "mesung";
  int number() default 100;
}

//custom annotation 2
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
@Inherited
public @interface AnotherAnnotation {
  String value() default "kimpo";
  int number() default 100;
}

//custom class(Book)
@MyAnnotation("ha ong")
public class Book {
    private static String B = "BOOK";
    private static final String C = "BOOK";
    
		@AnotherAnnotation(value = "hello", number = 20)
    private String a = "a";
  
  	@AnotherAnnotation(value = "hi", number = 60)
    public String d = "d";
    protected String e = "e";
    public Book() {
    }
  
  	@AnotherAnnotation
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

//super class(MyBook)
@AnotherAnnotation
public class MyBook extends Book implements MyInterface{
}
~~~



- **해당 클래스에 붙어있는 애노테이션**의 정보를 보자

  ~~~java
  public static void main( String[] args ) throws ClassNotFoundException {
    Arrays.stream(MyBook.class.getDeclaredAnnotations()).forEach(System.out::println);
  }
  ~~~

  

- **해당 클래스나 상위 클래스에 붙어있는 애노테이션**의 정보를 보자

  ~~~java
  public static void main( String[] args ) throws ClassNotFoundException {
    //1. MyAnnotation만 호출
    Arrays.stream(Book.class.getAnnotations()).forEach(System.out::println);
    //2. MyAnnotation 및 AnotherAnnotation 호출
    Arrays.stream(MyBook.class.getAnnotations()).forEach(System.out::println);
  }
  ~~~

  ***결과 1*** 

  <img src="https://user-images.githubusercontent.com/40616436/77423305-4ab40580-6e12-11ea-993d-363ad45144c7.png" alt="image" style="zoom:50%;" />

  ***결과 2***

  <img src="https://user-images.githubusercontent.com/40616436/77424035-d4180780-6e13-11ea-95bc-014c5a8041ed.png" alt="image" style="zoom:50%;" />

  

- **해당 클래스 필드에 있는 애노테이션** 정보를 보자

  ~~~java
  public static void main( String[] args ) throws ClassNotFoundException {
    Arrays.stream(Book.class.getDeclaredFields()).forEach(f -> {
      Arrays.stream(f.getAnnotations()).forEach(System.out::println);
    });
  }
  ~~~

  ***결과***

  <img src="https://user-images.githubusercontent.com/40616436/77424432-76d08600-6e14-11ea-8330-09800982e709.png" alt="image" style="zoom:50%;" />



- **해당 애노테이션에 입력된 값**들을 가져오자

  ~~~java
  public static void main( String[] args ) throws ClassNotFoundException {
    Arrays.stream(Book.class.getDeclaredFields()).forEach(f -> {
      Arrays.stream(f.getAnnotations()).forEach(a -> {
        if(a instanceof AnotherAnnotation) {
          AnotherAnnotation anotherAnnotation = (AnotherAnnotation) a;
          System.out.println(anotherAnnotation.value());
          System.out.println(anotherAnnotation.number());
        }
      });
    });
  }
  ~~~

  ***결과***

  <img src="https://user-images.githubusercontent.com/40616436/77425609-599cb700-6e16-11ea-8734-73ef268f4770.png" alt="image" style="zoom:50%;" />