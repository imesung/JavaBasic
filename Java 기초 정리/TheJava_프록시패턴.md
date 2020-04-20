## 프록시 패턴

### 프록시 패턴이란,

<img src="https://user-images.githubusercontent.com/40616436/77825653-07c59b00-714e-11ea-802e-08f0b5cd329e.png" alt="image" style="zoom:50%;" />

- 프록시는 대리한다 라는 개념으로 리얼 서브젝트를 참조하고 있다.
- 서브젝트는 인터페이스로서 프록시와 리얼 서브젝트가 공유하고 있고, 클라이언트는 서브젝트 인터페이스 타입으로 프록시를 사용한다.
- 클라이언트는 프록시를 거쳐 리얼 서브젝트를 사용하기 때문에 **프록시는 리얼 서브젝트에 대한 접근을 관리하거나 부가기능을 제공하고 리턴값을 변경할 수도 있다.**
- 리얼 서브젝트는 자신이 해야할 일만 하면서(SRP) 프록시를 이용해 부가적인 기능(접근제한, AOP, 로깅, 트랜잭션)을 제공할 수 있다.
  - 부가적인 기능마저 리얼 서브젝트에서 제공해주면 매우 복잡해지고 객체지향적 개념에서도 벗어나게 된다.



### 프록시 패턴을 소스로 확인해보자

<img src="https://user-images.githubusercontent.com/40616436/77842646-c4ac0c00-71cf-11ea-93d6-571874d6321c.png" alt="image" style="zoom:50%;" />

~~~java
//BookService
public interface BookService {
  void rent(Book book);
}

//BookServiceProxy
public class BookServiceProxy implements BookService {
  BookService bookService;

  public BookServiceProxy(BookService bookService) {
    this.bookService = bookService;
  }

  @Override
  public void rent(Book book) {
    System.out.println("aaaa");
    bookService.rent(book);
    System.out.println("bbbb");
  }
}

//DefaultBookService
public class DefaultBookService implements BookService{
  public void rent(Book book) {
    System.out.println("rent : " + book.getTitle());
  }
}

//Test
public class BookServiceTest {
  //BookServiceProxy가 DefaultBookService를 참조하기 위해 파라미터를 받음
  BookService bookService = new BookServiceProxy(new DefaultBookService());

  @Test
  public void di() {
    Book book = new Book();
    book.setTitle("spring");
    bookService.rent(book);
  }
}
~~~

- 실행결과

  <img src="https://user-images.githubusercontent.com/40616436/77842691-4b60e900-71d0-11ea-8008-5e256f06fa0d.png" alt="image" style="zoom:50%;" />

- 결과에서도 확인하듯이, 리얼 서브젝트(DefaultBookService)의 수정없이 프록시(BookServiceProxy)의 수정으로 결과값이 변경되는 것을 볼 수 있다.



### 프록시의 단점

- 부가적인 기능이나 서브젝트에 위임하는 작업이 중복 될 수가 있다.

  ~~~java
  //BookServiceProxy
  public class BookServiceProxy implements BookService {
    BookService bookService;
  
    public BookServiceProxy(BookService bookService) {
      this.bookService = bookService;
    }
  
    @Override
    public void rent(Book book) {
      System.out.println("aaaa");
      bookService.rent(book);
      System.out.println("bbbb");
    }
    
    //1.
    @Override
    public void rent2(Book book) {
      System.out.println("bbbb");
      bookService.rent(book);
      System.out.println("cccc");
    }
    
   	//2.
    @Override
    public void renturnBook(Book book) {
      System.out.println("aaaa");
      bookService.renturnBook(book);
      System.out.println("bbbb");
    }
  }
  ~~~

  1. *부가적인 기능은 다르나 서브젝트에 위임하는 작업이 중복된다.*
  2. *부가적인 기능은 동일한데 메소드 호출하는 부분이 달라 부가적인 기능들이 중복으로 발생할 수 있다.*

- **이를 방지하기 위해 매번 프록시에 해당하는 클래스를 만들지 않고, 동적으로 런타임에 생성하는 방법인 다이나믹 프록시를 활용한다.**

