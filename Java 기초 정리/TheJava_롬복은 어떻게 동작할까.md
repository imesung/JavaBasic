## 애노테이션 프로세서 - 롬복은 어떻게 동작할까?

**Lombok**

-  클래스를 만들 때는 Boilerplate 코드 형식을 작성하는 방식이 많았다.

  - Boilerplate : Getter Setter toString ..등을 한 곳에 모여 놓고 실질적인 로직 메소드들과 분리해놓아 코드를 정리한 방식이다.

- 이런 방식을 사용하지 않고 롬복을 활용하여 코드를 정리 할 수 있다.

  ~~~java
  @Getter @Setter @EqualsAndHashCode
  public class Member {
  
      private String name;
  
      private int age;
  
      public boolean isSameAge(Member member) {
          return this.age == member.age;
      }
  
  }
  ~~~

  ![image](https://user-images.githubusercontent.com/40616436/78419786-2b27a300-7684-11ea-9e36-bd3f188010c7.png)
  - 분명 Getter, Setter가 없는데 Set이 가능한 것을 확인할 수 있다.

- 테스트도 성공적으로 나타난 것을 볼 수 있다.

  ~~~java
  @Test
  public void getterSetter() {
    Member member = new Member();
    member.setName("mesung");
    Assert.assertEquals(member.getName(), "mesung");
  }
  ~~~

  ![image](https://user-images.githubusercontent.com/40616436/78419834-79d53d00-7684-11ea-81b5-a075fb35e7ee.png)

- @Getter, @Setter 뿐만 아니라 해당 사이트에서 더 다양한 것을 확인할 수 있다.
  - https://projectlombok.org/features/all



**롬복 동작원리**

- 롬복이 가능한 이유는 Java에서 제공하는 애노테이션 프로세서에 의해서 가능한 것이다.
- 컴파일 시점에 애노테이션 프로세서를 사용하여 애노테이션이 붙어 있는 클래스의 정보를 트리 구조로 참조하여 소스를 구성한다.
  - AST(abstract syntax tree)를 조작하는 것이다.



**그렇다면 애노테이션 프로세서를 사용하면 어떻게 컴파일 타임에 어떤 코드를 만들어낼 수 있는지 확인해보자**