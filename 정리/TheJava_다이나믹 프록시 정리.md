## 다이나믹 프록시 정리

### 다이나믹 프록시

- 런타임에 인터페이스 또는 클래스의 프록시 인스턴스 또는 클래스를 만들어 사용하는 프로그래밍 기법이다.



### 다이나믹 프록시 사용처

- 스프링 데이터 JPA
- 스프링 AOP
- Mockito
- 하이버네이트 lazy initialzation
- ...



### Mockito

~~~java
//MyRepository
public interface MyRepository extends JpaRepository<Book, Integer> {

}

//MyService
public class MyService {

  MyRepository myRepository;

  public MyService(MyRepository myRepository) {
    this.myRepository = myRepository;
  }

  public void rent(Book book) {
    Book save = myRepository.save(book);
    System.out.println(save.getTitle());
  }

  public void returnBook(Book book) {
    myRepository.save(book);
  }
}

//MyServiceTest
public class MyServiceTest {

  @Test
  public void my1() throws Exception{
    //1. 
    MyRepository myRepositoryMock = mock(MyRepository.class);
    //2. 
    MyService myService = new MyService(myRepositoryMock);

    Book book = new Book();
    book.setTitle("spring");
    myService.rent(book);
    myService.returnBook(book);
  }
  //실행 결과 : spring
  
  @Test
  public void my2() throws Exception{
    MyRepository myRepositoryMock = mock(MyRepository.class);
    Book hibernateBook = new Book();
    hibernateBook.setTitle("hibernate");
    //3. 
    when(myRepositoryMock.save(any())).thenReturn(hibernateBook);
    MyService myService = new MyService(myRepositoryMock);

    Book book = new Book();
    book.setTitle("spring");
    myService.rent(book);
    myService.returnBook(book);
  }
  //실행 결과 : hibernate

}
~~~

1. MyRepository의 프록시(myRepositoryMock)를 Mockito를 통해 만들어낸다. 
2. MyService는 myRepositoryMock라는 프록시에 의해서 리얼 서브젝트의 save()를 호출한다.
3. 프록시에 의해 접근한 리얼 서브젝트의 save() 리턴 결과에 상관없이 Mockito를 사용하여 사용 객체(hibernateBook)를 정해준다.

