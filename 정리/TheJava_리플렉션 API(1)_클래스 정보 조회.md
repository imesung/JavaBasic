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



**리플렉션 API로 Class가 가지고 있는 정보에 접근해보자.**

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

   <img src="https://user-images.githubusercontent.com/40616436/77340258-6070ee80-6d70-11ea-8f91-d55d0217e022.png" alt="image" style="zoom:50%;" />

   

2. 접근제한자에 상관없이 모든 필드를 가져오자

   ~~~java
   public static void main( String[] args ) throws ClassNotFoundException {
     Class<Book> bookClass = Book.class;
   
     //getDeclaredFields는 접근제한자에 상관없이 모든 필드를 가져온다.
     Arrays.stream(bookClass.getDeclaredFields()).forEach(System.out::println);
   }
   ~~~

   <img src="https://user-images.githubusercontent.com/40616436/77340301-754d8200-6d70-11ea-8d99-890b25ae2fd9.png" alt="image" style="zoom:50%;" />

   

3. 접근제한자에 상관없이 모든 필드의 값을 가져와보자(**Error**)

~~~java
public static void main( String[] args ) throws ClassNotFoundException {
  Class<Book> bookClass = Book.class;

  Book book = new Book();
  Arrays.stream(bookClass.getDeclaredFields()).forEach(f -> {
    try {
      System.out.printf("%s %s\n", f, f.get(book));
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
  });
}
~~~

<img src="https://user-images.githubusercontent.com/40616436/77340707-091f4e00-6d71-11ea-9219-46fef4c28a3c.png" alt="image" style="zoom:50%;" />

<img src="https://user-images.githubusercontent.com/40616436/77340773-20f6d200-6d71-11ea-834e-0f5e636a6c0d.png" alt="image" style="zoom:50%;" />

- public은 정상적으로 필드값을 가져오나 private은 접근이 불가능하다. 이때는 어떻게 해야할까?



4. 접근제한자에 상관없이 모든 필드의 값을 가져와보자(**Normal**)

~~~java
public static void main( String[] args ) throws ClassNotFoundException {
  Class<Book> bookClass = Book.class;

  Book book = new Book();
  Arrays.stream(bookClass.getDeclaredFields()).forEach(f -> {
    try {
      //모든 접근제한자 접근 
      f.setAccessible(true);

      System.out.printf("%s %s\n", f, f.get(book));
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
  });
}
~~~

<img src="https://user-images.githubusercontent.com/40616436/77341370-f6f1df80-6d71-11ea-880e-b714732142d6.png" alt="image" style="zoom:50%;" />

- setAccessible(true) : 리플렉션을 활용하여 모든 접근제한자에 접근할 수 있다.



5. 메소드들을 가져오자(**object 상속 받은 것 까지**)

~~~java
public static void main( String[] args ) throws ClassNotFoundException {
  Class<Book> bookClass = Book.class;

  Arrays.stream(bookClass.getMethods()).forEach(System.out::println);
}
~~~

<img src="https://user-images.githubusercontent.com/40616436/77342521-a7acae80-6d73-11ea-8f7b-3d57d974fa16.png" alt="image" style="zoom:50%;" />

6. 생성자를 가져오자

~~~java
public static void main( String[] args ) throws ClassNotFoundException {
  Class<Book> bookClass = Book.class;

  Arrays.stream(bookClass.getDeclaredConstructors()).forEach(System.out::println);
}
~~~

<img src="/Users/mesung/Library/Application Support/typora-user-images/image-20200324020508582.png" alt="image-20200324020508582" style="zoom:50%;" />

7. 부모 클래스를 가져오자

~~~java
public static void main( String[] args ) throws ClassNotFoundException {
  Class<? super MyBook> superclass = MyBook.class.getSuperclass();
  System.out.println(superclass);
}
~~~

<img src="https://user-images.githubusercontent.com/40616436/77342885-36213000-6d74-11ea-9011-2a3539e77498.png" alt="image" style="zoom:50%;" />

8. 확장한 인터페이스를 가져오자

~~~java
public static void main( String[] args ) throws ClassNotFoundException {
  Arrays.stream(MyBook.class.getInterfaces()).forEach(System.out::println);
}
~~~

<img src="https://user-images.githubusercontent.com/40616436/77343039-6ff23680-6d74-11ea-9c9d-f22854d7b039.png" alt="image" style="zoom:50%;" />

9. getModifier를 사용하여 접근제한자나 static 여부를 확인해보자.

~~~java
public static void main( String[] args ) throws ClassNotFoundException {
  Arrays.stream(Book.class.getDeclaredFields()).forEach(f -> {
    int modifiers = f.getModifiers();
    System.out.println(f);
    System.out.println(Modifier.isPrivate(modifiers));
    System.out.println(Modifier.isProtected(modifiers));
    System.out.println(Modifier.isStatic(modifiers));

  });
}
~~~

<img src="https://user-images.githubusercontent.com/40616436/77343360-e5f69d80-6d74-11ea-858e-64d2443d6a62.png" alt="image" style="zoom:50%;" />

10. 메소드의 여러 정보를 확인하자

~~~java
public static void main( String[] args ) throws ClassNotFoundException {
  Arrays.stream(Book.class.getMethods()).forEach(m -> {
    int modifiers = m.getModifiers();
		
    m.get...
    
  });
}
~~~

<img src="https://user-images.githubusercontent.com/40616436/77343773-9795ce80-6d75-11ea-8dff-05f684c7ecc5.png" alt="image" style="zoom:50%;" />

