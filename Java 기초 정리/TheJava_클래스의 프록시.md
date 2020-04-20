## 클래스의 프록시

- 인터페이스가 없는 경우 프록시는 어떻게 만드는지 알아보자.
  - 이 때는 라이브러리가 필요하다. (CGlib)



### CGlib

~~~xml
<dependency>
  <groupId>cglib</groupId>
  <artifactId>cglib</artifactId>
  <version>3.3.0</version>
</dependency>
~~~

~~~java
@Test
public void di() {
  MethodInterceptor handler = new MethodInterceptor() {
    MyBookService myBookService = new MyBookService();
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
      if(method.getName().equals("rent")) {
        System.out.println("aaaa");
        Object invoke = method.invoke(myBookService, objects);
        System.out.println("bbbb");
        return invoke;
      }
      return method.invoke(myBookService, objects);

    }
  };

  MyBookService myBookService = (MyBookService) Enhancer.create(MyBookService.class, handler);

  Book book = new Book();
  book.setTitle("spring");
  myBookService.rent(book);
  myBookService.returnBook(book);
}
~~~

- 여기에서 중요한 것은 **cglib의 Enhancer.create(MyBookService.class, handler);는 MyBookService를 상속해서 만드는 것을 볼 수 있다.**



### 서브 클래스를 만드는 방법의 단점

- 상속을 사용하지 못하는 경우에는 프록시를 만들 수가 없다.
  - private 생성자만 있는 경우
  - final class인 경우
- 결과적으로,
  - **인터페이스가 있을 때는 인터페이스의 프록시를 만들어서 사용하는 것이 효율적이다.**
    - 인터페이스를 이용해서 프록시를 만드는 것이 훨씬 편하고, 상속에 관련된 제약사항도 없기 때문이다.



