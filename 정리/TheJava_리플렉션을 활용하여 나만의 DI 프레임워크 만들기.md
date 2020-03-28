## 리플렉션을 활용하여 나만의 DI 프레임워크 만들기

### Bean을 주입해주는 커스텀 애노테이션을 만들자

**커스텀 애노테이션을 만들자**

~~~java
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {
}
~~~



**커스텀 애노테이션을 사용해보자**

~~~java
public class BookService {
  @Inject
  BookRepository bookRepository;
}
~~~

~~~java
public class BookRepository {
}
~~~

- BookService에서 @Inject를 통해 의존성이 주입되기를 원하고 있다.



**먼저 리플렉션을 사용하여 객체가 생성되는 지 확인해보자.**

~~~java
public class ContainerServiceTest {
  @Test
  public void getObject_BookRepository() {
    BookRepository bookRepository  = ContainerService.getObject(BookRepository.class);
    assertNotNull(bookRepository);
  }
}
~~~

~~~java
public class ContainerService {

  public static <T> T getObject(Class<T> classType) {
    T instance = createInstance(classType);
    return instance;
  }

  //리플렉션 API인 getContructor를 활용하여 클래스를 생성
  private static <T> T createInstance(Class<T> classType) {
    try {
      
      //리플렉션 사용
      return classType.getConstructor(null).newInstance();
      
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
      throw new RuntimeException(e);
    }
  }
}
~~~

***테스트 결과***

<img src="https://user-images.githubusercontent.com/40616436/77661277-c3a39080-6fbd-11ea-8368-43f8b77bd300.png" alt="image" style="zoom:50%;" />

- 정상적으로 객체가 생성된다.



**이제 @Inject를 통해서 의존성 주입이 이루어질 수 있게끔 만들어보자**

~~~java
@Test
public void getObject_BookService() {
  BookService bookService  = ContainerService.getObject(BookService.class);
  assertNotNull(bookService);
  assertNotNull(bookService.bookRepository);
}
~~~

~~~java
public class ContainerService {

  public static <T> T getObject(Class<T> classType) {
    T instance = createInstance(classType);
    
    //1. getDeclaredFields() : 해당 클래스에 있는 필드값들을 본다.
    Arrays.stream(classType.getDeclaredFields()).forEach(f -> {
      
      //2. f.getAnnotation(Inject.class) : 필드에 @Inject라는 애노테이션이 있는지 본다.
      if(f.getAnnotation(Inject.class) != null) {
        //3. f.getType() : 해당 필드의 타입을 가져온다. 여기서는 BookRepository가 된다.
        Object fieldInstance = createInstance(f.getType());
        //4. 필드가 private일 경우가 있기 때문에 접근 가능하게 한다.
        f.setAccessible(true);
        try {
          //5. 필드에 의존성 주입을 해준다. 이 때, 파라미터는 의존하는 인스턴스와 주입할 인스턴스가 필요하다.
          f.set(instance, fieldInstance);
        } catch (IllegalAccessException e) {
          throw new RuntimeException(e);
        }
      }
    });
    return instance;
  }

  //리플렉션 API인 getContructor를 활용하여 클래스를 생성
  private static <T> T createInstance(Class<T> classType) {
    try {
      return classType.getConstructor(null).newInstance();
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
      throw new RuntimeException(e);
    }
  }
}
~~~

1. getDeclaredFields() : 해당 클래스에 있는 필드값들을 본다.
2. f.getAnnotation(Inject.class) : 필드에 @Inject라는 애노테이션이 있는지 본다.
3. f.getType() : 해당 필드의 타입을 가져온다. 여기서는 BookRepository가 된다.
4. f.setAccessible(true) : 필드가 private일 경우가 있기 때문에 접근 가능하게 한다.
5. f.set(instance, fieldInstance) : **필드에 의존성 주입을 해준다. 이 때, 파라미터는 의존하는 인스턴스와 주입할 인스턴스가 필요하다.**



**테스트 결과**

<img src="https://user-images.githubusercontent.com/40616436/77663214-2ac24480-6fc0-11ea-90dc-d5ec3d439541.png" alt="image" style="zoom:50%;" />



**해당 애노테이션을 jar로 만들자**

- maven install을 통해서 해당 애노테이션을 만든 프로젝트를 jar파일로 만들자

<img src="https://user-images.githubusercontent.com/40616436/77813172-c8656300-70e9-11ea-8b4f-18ccf444a2e8.png" alt="image" style="zoom:50%;" />

- 위 명령어를 통해 jar파일이 만들어졌다.

<img src="https://user-images.githubusercontent.com/40616436/77813238-80930b80-70ea-11ea-9d9b-8ad206a2782f.png" alt="image" style="zoom:50%;" />



**만든 jar파일을 다른 프로젝트에서 활용하여 DI를 사용하자**

- 다른 프로젝트에서 우리가 만든 jar 파일을 사용하기 위해선 dependency에 해당 프로젝트의 groupId와 version을 삽입해야한다.

~~~xml
<!-- 우리가 만든 DI 프로젝트 -->
<groupId>com.mesung</groupId>
<artifactId>di-example</artifactId>
<version>1.0-SNAPSHOT</version>
~~~

~~~xml
<!-- DI 사용하는 프로젝트 pom.xml-->
<dependencies>
  ...

  <dependency>
    <groupId>com.mesung</groupId>
    <artifactId>di-example</artifactId>
    <version>1.0-SNAPSHOT</version>
  </dependency>
</dependencies>
~~~



- DI를 사용하려는 프로젝트에서 직접적으로 애노테이션 사용

~~~java
public class AccountService {

  //우리가 만든 @Inject라는 애노테이션
  @Inject
  AccountRepository accountRepository;

  public void join() {
    System.out.println("Service.join");
    accountRepository.save();
  }
}

public class AccountRepository {
    public void save() {
        System.out.println("Repo.save");
    }
}
~~~

​	*우리가 만든 @Inject를 사용하면 자동으로 의존을 주입받을 수 있다.*



- 실행 결과를 살펴보자

  <img src="https://user-images.githubusercontent.com/40616436/77813520-9c97ac80-70ec-11ea-962c-0cbf4132f8c7.png" alt="image" style="zoom:50%;" />

  *AccountRepository를 주입받아서 정상적으로 AccountRepository에 있는 "Repo.save"를 출력하는 것을 볼 수 있다.*



**정리**

*리플렉션 사용시 주의할 것*

- **지나친 사용은 성능 이슈를 야기할 수 있다. 반드시 필요한 경우에만 사용해야 한다.**
  - 인스턴스가 있으면 그냥 인스턴스를 사용하면된다.
- **컴파일 타임에 확인되지 않고 런타임 시에만 발생하는 문제를 만들 가능성이 있다.**
  - *리플렉션은 런타임 시에 객체 만들거나 조작하므로 해당 문제가 생길 수 있다.*
- **접근 지시자를 무시할 수 있다.**

*사용 예*

- ***스프링***
  - 의존성 주입에 리플렉션을 사용한다.
  - MVC 뷰에서 넘어온 데이터를 특정한 객체에 바인딩할 때 사용한다.

- ***하이버네이트***
  - @Entity 클래스에 Setter가 없다면 리플렉션을 사용한다.