## 프로토타입 패턴 2

**얕은 복사와 깊은 복사**

**얕은 복사**

하나의 객체의 주소값을 복사하는 경우를 말한다.

~~~java
public static void main(String[] args) {
  Cat navi = new Cat("navi");

  Cat yo = navi;
  yo.chgName("yo");

  System.out.println(navi.getName());
  System.out.println(yo.getName());
}

//결과
yo
yo
~~~



**깊은 복사**

하나의 객체의 값들을 복사하는 경우를 말한다.

~~~java
public class Cat implements Cloneable{
	...

 	public Cat copy() throws CloneNotSupportedException {
    Cat ret = (Cat)this.clone();
    return ret;
  }
}

public static void main(String[] args) throws CloneNotSupportedException {
  Cat navi = new Cat("navi");

  Cat yo = navi.copy();
  yo.chgName("yo");

  System.out.println(navi.getName());
  System.out.println(yo.getName());
}

//결과
navi
yo
~~~

디버깅을 해보면 주소값이 다르게 나타나는 것을 확인할 수 있다. 즉, yo라는 Cat 클래스를 새로운 메모리에 할당하는 것이다.



**예외**

Cat의 Age도 설정해보자.

~~~java
public class Cat implements Cloneable{
  private String name;
	private Age age;
  ...
}

public class Age {
  private int year;
  private int value;
}

public static void main(String[] args) throws CloneNotSupportedException {
  Cat navi = new Cat("navi", new Age(2018, 3));

  Cat yo = navi.copy();
  yo.chgName("yo");
  yo.chgAge(2013, 2);

  System.out.println(navi.toString());
  System.out.println(yo.toString());
}

//결과
Cat{name='navi', age=Age{year=2013, value=2}}
Cat{name='yo', age=Age{year=2013, value=2}}
~~~

- 결과를 살펴보면 navi를 copy한 yo의 나이를 변경하였더니, navi의 나이 또한 변경된 것을 확인할 수 있다. 해당 부분은 Cat클래스에서 copy를 진행할 때 Age까지는 깊은 복사가 안되는 것을 확인한 것이다.

- 우리가 원하는 결과를 추출하기 위해 소스를 변경하면 copy 부분을 변경하면 된다.

  ~~~java
  public class Cat implements Cloneable{
  	...
  
   	public Cat copy() throws CloneNotSupportedException {
      Cat ret = (Cat)this.clone();
      ret.age = new Age(this.age.getYear(), this.age.getValue());
      return ret;
    }
  }
  ~~~

  