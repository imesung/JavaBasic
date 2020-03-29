## 다이나믹 프록시

- 런타임에 특정 인터페이스들을 구현하는 클래스 또는 인스턴스를 만드는 기술이다.



### 다이나믹 프록시를 사용해서 프록시 인스턴스를 만들어보자.

- 프록시 클래스 대신 다이나믹 프록시를 구현한다.

  ~~~java
  public class BookServiceTest {
    	//1. 
      BookService bookService = (BookService) Proxy.newProxyInstance(
        BookService.class.getClassLoader()
        , new Class[]{BookService.class}
        , new InvocationHandler() {
  
          BookService bookService = new DefaultBookService();
  
          @Override
          //2.
          public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
            //3. 
            if(method.getName().equals("rent")) {
              System.out.println("aaaa");
              //4. 
              Object invoke = method.invoke(bookService, objects);
              System.out.println("bbbb");
              return invoke;
            }
  					//5. 
            return method.invoke(bookService, objects);
          }
        }
      );
  
      @Test
      public void di() {
          Book book = new Book();
          book.setTitle("spring");
          bookService.rent(book);
          bookService.returnBook(book);
      }
  }
  ~~~

  1. Proxy.newPorxyInstance()를 사용해 다이나믹 프록시를 만든다.
     - 파라미터
       - BookService.class.getClassLoader() : 서브젝트의 클래스 로더
       - Class [] {BookService.class} : 클래스 배열 (서브젝트의 목록을 줘야한다. 해당 프록시가 어떤 서브젝트(인터페이스) 타입의 프록시인지를 정의한다.)
       - new InvocationHandler() : 해당 프록시에 메소드가 호출될 때 어떤식으로 처리할 것인지에 대한 설명이다.
  2. 프록시에 메소드가 호출될 때 처리할 로직.
  3. 메소드에 이름에 따라 로직을 변경시키기 위한 조건.
  4. 해당 메소드를 호출
     - 파라미터로는 리얼 서브젝트(DefaultBookService)와 argument를 준다.
  5. 다른 메소드의 호출
     - 원래 하던 일만 한다. (return: spring)

- 실행 결과

  <img src="/Users/mesung/Library/Application Support/typora-user-images/image-20200329160758235.png" alt="image-20200329160758235" style="zoom:50%;" />



### 다이나믹 프록시의 단점

**단점**

- ***여러 부가기능이 추가되면 다이나믹 프록시를 구현한 소스가 매우 커질 수가 있거나 해당 다이나믹 프록시를 감싸는 프록시가 만들어 질수가 있다.***
- 그로인해,
  - **Spring은 다이나믹 프록시의 구조를 Spring이 정의한 인터페이스로 고친 것이 있다.**
  - **그것이 바로, Spring AOP이다.**
  - **그래서 Spring AOP를 프록시 기반의 AOP라고 부르는 것이다.**
  - *Spring AOP의 자세한 내용은 토비 스브링 6장을 참고해보자*



**제약사항**

- 만약, Proxy.newPorxyInstance()의 파라미터 중 Class [] {BookService.class}이 Class [] {DefaultBookService.class}이라면 **즉, 클래스 배열이 인터페이스가 아니라 클래스 기반이라면 다이나믹 프록시를 만들 수 없다.**
- 그럼 인터페이스(서브젝트)가 없다면?
  - 이런 경우에는 자바에서 다이나믹 프록시를 사용하지 못한다.
  - **라이브러리를 사용해서 만들면 된다.(CGlib)**