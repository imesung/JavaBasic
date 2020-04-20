## Java에서 어노테이션이란,

Java에서 어노테이션은 JEE5부터 새롭게 추가된 요소이다.

어노테이션을 사용하는 이유는, 유효성 검사를 쉽게 할 수 있고 코드가 깔끔해진다. 하지만, 가장 큰 특징은 **메타 데이터로 볼 수 있다.**

- 메타 데이터 : 데이터를 위한 데이터로서, 데이터 대해서 설명을 한 데이터이다.



**Java에서 제공하는 어노테이션 종류**

@Override

- 선언한 메소드가 오버라이드(재정의) 되었다는 것을 나타낸다.

@Deprecated

- 해당 메소드 혹은 객체가 더 이상 사용되지 않음을 표시한 것이다.
- 만약 해당 메소드 혹은 객체를 사용할 경우 컴파일 경고를 발생시킨다.

@SuppressWarnings

- 선언한 곳의 컴파일 경고를 무시하도록 한다.

@SafeVerargs

- Java7 부터 지원하며, 제네릭 같은 가변인자의 매개변수를 사용할 때의 경고를 무시한다.

@FunctionalInterface

- Java8 부터 지원하며, 함수형 인터페이스를 지정하는 어노테이션이다.
- 만약 메소드가 존재하지 않거나, 1개 이상의 메소드(default 메소드 제외)가 존재할 경우 컴파일 오류를 발생시킨다



**커스텀 어노테이션**

개발자가 직접 작성한 어노테이션으로서, 커스텀 어노테이션을 만들때는 **메타 어노테이션**을 이용한다.

**메타 어노테이션의 종류**

@Retention

- 자바 컴파일러가 어노테이션을 다루는 방법을 기술하며, 특정 시점까지 영향을 미치는 것을 결정한다.
  - RetentionPolicy.SOURCE : 컴파일 전까지만 유효하다.
  - RetentionPolicy.CLASS : 컴파일러가 클래스를 참조할 떄까지 유효하다.
  - RetentionPolicy.RUNTIME : 런타임시 까지 계속 유효, 컴파일 이후에도 JVM에 의해 계속 참조가 가능하다.(리플렉션 사용)

@Target

- 어노테이션이 적용할 위치를 선택한다.
  - **ElementType.PACKAGE** : 패키지 선언
  - **ElementType.TYPE** : 타입 선언
  - **ElementType.ANNOTATION_TYPE** : 어노테이션 타입 선언
  - **ElementType.CONSTRUCTOR** : 생성자 선언
  - **ElementType.FIELD** : 멤버 변수 선언
  - **ElementType.LOCAL_VARIABLE** : 지역 변수 선언
  - **ElementType.METHOD** : 메서드 선언
  - **ElementType.PARAMETER** : 전달인자 선언
  - **ElementType.TYPE_PARAMETER** : 전달인자 타입 선언
  - **ElementType.TYPE_USE** : 타입 선언

@Documented

- 해당 어노테이션을 Javadoc에 포함시킨다.

@Inherited

- 어노테이션의 상속을 가능하게 한다.

@Repeatable

- Java8 부터 지원하며, 연속적으로 어노테이션을 선언할 수 있게 해준다.



### **커스텀 어노테이션을 만들어보자**



**정수 값 주입 예제**

~~~java
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InsertIntData {
    int data() default 0;
}
~~~

해당 어노테이션은 필드 값에 적용하는 것이고, 런타임까지 계속 유효한 값이 된다.

~~~java
public class AnnotationExam01 {
  	//어노테이션에서 정의한 data 값을 30으로 초기화
    @InsertIntData(data = 30)
    private int myAge;

  	//어노테이션에서 정의한 default 값으로 초기화
    @InsertIntData
    private int defaultAge;

    public AnnotationExam01() {
        this.myAge = -1;
        this.defaultAge = -1;
    }
}
~~~



**문자열 값 주입 예제**

~~~java
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InsertStringData {
    String data() default "default";
}
~~~

~~~java
public class AnnotationExam02 {

    @InsertStringData(data = "MHLab")
    private String myData;

    @InsertStringData
    private String defaultData;

    public AnnotationExam02() {
        myData = "No data";
        defaultData = "No data";
    }
}
~~~



