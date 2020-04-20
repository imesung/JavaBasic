## 리플렉션 API(2) : 클래스 정보 수정

### Class 인스턴스에 있는 사용자 정의 클래스를 생성해보자

~~~java
//Book
public class Book {
    public static String A = "A";
    private String B = "B";

    public Book(){}
    public Book(String b) {
        B = b;
    }

    public void c() {
        System.out.println("C");
    }
    public int sum(int left, int right) {
        return left + right;
    }
}
~~~



- 사용자 정의 클래스의 위치를 활용하여 클래스를 생성해보자.

  ~~~java
  public static void main( String[] args ) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    
    Class<?> bookClass = Class.forName("org.example.Book");
    
    //파라미터 없는 생성자 이용
    Constructor<?> constructor = bookClass.getConstructor(null);
    Book book = (Book) constructor.newInstance();
    
    //파라미터 있는 생성자 이용
    Constructor<?> constructor = bookClass.getConstructor(String.class);
    Book book = (Book) constructor.newInstance("parameter");
    
    System.out.println(book);
    
  }
  
  ~~~

  ***결과***

  <img src="/Users/mesung/Library/Application Support/typora-user-images/image-20200324221108587.png" alt="image-20200324221108587" style="zoom:50%;" />

  

- Class 인스턴스에서 생성한 클래스의 필드값들을 사용해보자

  ~~~java
  public class App {
      public static void main( String[] args ) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Class<?> bookClass = Class.forName("org.example.Book");
        Constructor<?> constructor = bookClass.getConstructor(String.class);
        Book book = (Book) constructor.newInstance("parameter");
        System.out.println(book);
  
        //필드 값을 가져오자
        Field a = Book.class.getDeclaredField("A");
        System.out.println(a.get(null));    //static하기 때문에 object 자리에 null을 넘겨준다.
        a.set(null, "AAA"); 	//set 또한 첫번째 파라미터는 해당 인스턴스가 들어가야하지만 static 필드이기 때문에 null이 들어간다.
        System.out.println(a.get(null));
      }
  }
  ~~~

  ***결과***

  <img src="https://user-images.githubusercontent.com/40616436/77429554-30335980-6e1d-11ea-85c5-e9c77049a322.png" alt="image" style="zoom:50%;" />

  - A의 필드값은 static하기 때문에 인스턴스가 생성되지 않아도 가져올 수가 있어, a.get(null) 및 a.set(null, "") 처럼 인스턴스 자리에 null이 들어간다.

  - 만약 static 필드가 아니라면,

    ~~~java
    Field b = Book.class.getDeclaredField("B");
    b.setAccessible(true);	//private 접근
    System.out.println(b.get(book));    
    b.set(book, "BBBB"); 
    System.out.println(b.get(book));
    ~~~

    ***결과***

    <img src="https://user-images.githubusercontent.com/40616436/77431729-74742900-6e20-11ea-929e-ca3324c30507.png" alt="image" style="zoom:50%;" />



- Class 인스턴스에서 생성한 클래스의 메소드들을 사용해보자

  ~~~java
  //private 메소드
  Method c = Book.class.getDeclaredMethod("c");
  c.setAccessible(true);	//C라는 메소드는 private 접근 제한자이기 때문에 setAccessible 설정한다.
  c.invoke(book);	//해당 매소드의 인스턴스를 파라미터로 넣어줘야 한다.
  
  //public 메소드
  Method c = Book.class.getDeclaredMethod("sum", int.class, int.class);
  int invoke = (int)c.invoke(book, 1, 2);
  System.out.println(invoke);
  ~~~

  - **getDeclaredMethod에 파라미터가 있을 때, primitive 타입과 reference 타입을 구분한다.**
    - 즉, int와 Integer를 구분하는 것이다.