## 클래스 로더와 JVM

### **Java 프로그램의 실행 과정**

1. Java 프로그램을 실행하면 JVM은 OS로 부터 메모리를 할당받는다.
2. 작성한 .java 파일은 컴파일러에 의해 .class(바이트 코드) 파일로 변환된다.
3. 클래스 로더에 의해서 바이트 코드를 읽고 메모리에 저장한다.
4. 실행엔진에 의해 바이트 코드를 한줄 씩 실행하게 된다.



### **클래스 로더**

![image](https://user-images.githubusercontent.com/40616436/77852893-f945b500-721b-11ea-98e5-558f88ea2375.png)

- 클래스 로더는 위 그림처럼 로딩 - 링크 - 초기화 순으로 진행된다.
- 로딩
  - Bootstrap(최상위 부모) <- Extension(Platform) <- Application(**대부분 해당 클래스 로더가 읽음**)의 클래스 로더들이 부모 자식 관계를 맺고 있다.
    - *클래스 로더의 동작 : 클래스를 읽을 때, 최상위 부모에서부터 읽을 수 있는지 확인 후 못 읽으면 자식 클래스 로더로 내려온다. 만약 Application 클래스 로더도 못 읽게 되면 ClassNotFoundException이 발생하게 된다.*
  - 클래스 로더가 .class 파일을 읽고 적절한 바이너리 데이터를 만들고 **메소드 영역에 저장**한다.
    - 이 때 메소드 영역에 저장되는 데이터 : FQCN(**패키지 이름, 풀 패키지 경로, 클래스 이름**), **클래스, 인터페이스, Enum, 메소드와 변수**
  - 로딩이 끝나면 해당 클래스 타입의 Class 객체(해당 클래스.class | Class<T>)를 생성하여 **힙 영역에 저장**한다.
- 링크
  - Verify : .class 형식이 유효한지 체크한다.
  - Preparation : 메모리를 준비하는 과정으로서, 클래스의 static 변수와 기본값에 필요한 메모리를 준비하는 과정이다.
  - Resolve : 해당 과정은 때에 따라 발생하는 것인데, 어떤 객체를 생성할 때 해당 인스턴스를 레퍼런스하게 되지만, 실제 인스턴스를 레퍼런스 하는 것이 아니고 논리적인 인스턴스를 레퍼런스하게 되는 것이다.
- 초기화
  - **Static 변수의 값을 할당한다.**





### **JVM**

![image](https://user-images.githubusercontent.com/40616436/77852658-ca7b0f00-721a-11ea-8bdd-d1a805c9df84.png)

- 클래스 로더 시스템
  - .class에서 바이트 코드를 읽고 메모리에 저장한다.
  - 로딩 : 클래스를 읽어온다.
  - 링크 : 논리적 레퍼런스를 연결한다. 
  - 초기화 : static 값들을 메모리에 할당한다.
- 메모리
  - 메소드 영역에는 클래스 수준의 정보(**클래스 이름, 부모 클래스 이름, 메소드, 변수**)를 저장하고 공유한다.
  - 힙 영역에는 객체를 저장하고 공유한다.
    - Class 객체의 인스턴스들도 저장된다.
  - 스택 영역에는 스레드마다 런타임 스택을 만들고, 그 안에서 메소드 호출을 스택 프레임이라 부르는 블럭으로 쌓는다.
    - 스레드가 종료되면 런타임 스택도 사라진다.
    - Ex. 오류가 발생했을 때 나오는 메소드 리스트들이 런타임 스택이다.
  - PC(Program Counter) 레지스터는 스레드마다 현재 실행되고 있는 스택 프레임을 가리키는 포인터가 생성된다.
  - 네이티브 메소드 스택은 자바로 작성되지 않고 C, C++로 작성된 메소드 스택이다.
- 네이티브 메소드 인터페이스(JNI)
  - Java가 아닌 C, C++로 작성된 메소드를 가진 인터페이스로서, Native 키워드를 사용하여 메소드를 호출한다.
  - 네이티브 메소드 인터페이스가 존재해야지만 네이비스 메소드 스택에 리스트가 존재한다.
- 네이티브 메소드 라이브러리
  - 네이티브 메소드 인터페이스의 구현체이다.
- 실행엔진
  - 인터프리터는 바이트 코드를 한줄씩 컴파일하여 네이티브 코드(컴퓨터가 읽을 수 있는 코드)로 변환하고 실행한다.
  - JIT 컴파일러는 인터프리터 방식의 단점을 보완하는 것이다.
    - 인터프리터는 한줄씩 실행하기 전에 반복되는 코드를 전부 찾아 JIT컴파일러로 보내고, JIT 컴파일러는 받은 바이트 코드를 네이티브 코드로 변경해놓는다.
    - 그 후 인터프리터는 다시 실행하여 네이티브 코드면 그냥 사용하고, 그렇지 않은 것만 컴파일하는 것으로서, 좀 더 빠르게 되는 것이다.

- GC
  - 힙 영역에 있는 New/Young Generation과 Old Genaration 영역에 있는 객체들을 정리한다.
  - New/Young Generation은 Minor GC가 발생하고 Old Generation은 Major GC가 발생한다.