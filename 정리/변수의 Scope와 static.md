### 변수의 Scope와 static

**변수가 선언된 블럭이 해당 변수의 사용범위이다**

```java
public class ValueScope{
    int global = 0;
    
    public int func(int val) {
        int local = 1;
    }
}
```

- **클래스 속성의 변수**
  - global 변수로서, ValueScope 전체 범위가 해당 변수의 Scope이다.
- **매개변수로 선언된 변수**
  - val 변수로서, func 메소드 블럭만이 해당 변수의 Scope이다.
- **메소드 블럭 내에 선언된 변수**
  - local 변수로서, 매개변수와 마찬가지로 func 메소드의 블럭만이 해당 변수의 Scope이다.

---

**static은 클래스 영역에 할당되며, Class가 인스턴스화 되지 않아도 사용할 수가 있다.**

```java
public class ValueScope{
    int global = 1;
    static int staticVal = 2;
    
    public void func(int val) {
        int local = 3;
    }
    
    public static void main(String[] args) {
        System.out.println(global);	//사용불가
        System.out.println(staticVal);	//사용가능
    }
}
```