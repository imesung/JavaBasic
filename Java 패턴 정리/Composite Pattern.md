## 컴포지트 패턴

> 컴포지트 패턴을 통해 트리 구조를 구현할 수 있다.

**컴포지트란**

하나 이상의 유사한 객체를 구성으로 설계된 객체로 모두 유사한 기능을 나타낸다. 이를 통해 객체 그룹을 조작하는 것처럼 단일 객체를 조작할 수 있다. 



**컴포지트 패턴이란?**

**컴포지트 패턴은 클라이언트가 복합 객체나 단일 객체를 동일하게 취급하는 것을 목적으로 한다. 여기서 컴포지트의 의도는 트리 구조로 작성하여 전체-부분 관계를 표현하는 것이다.**



**컴포지트 패턴은 언제 사용하는가?**

복합 객체와 단일 객체의 처리 방법이 다르지 않을 경우, 전체-부분 관계로 정의할 수 있다.



**기본 설계**

<img src="https://user-images.githubusercontent.com/40616436/82733383-5fe3dc80-9d4e-11ea-9c4f-9e2b94d6e48c.png" alt="image" style="zoom:50%;" />

- Component : 모든 표현할 요소들의 추상적인 인터페이스이다.
- Leaf : Component 인터페이스를 구현하고 구현체 클래스를 나타낸다. (**단일 객체에 해당된다.**)
- Composite : Component 인터페이스를 구현하고, 구현되는 자식들을 가지고 관리하기 위한 메소드(addChild, removeChild..)를 구현한다. 또한, 일반적으로 인터페이스에 작성된 메소드는 자식에게 위임하는 처리를 한다. (**복합 객체에 해당된다.**)

**예제 코드**

<img src="https://user-images.githubusercontent.com/40616436/82733437-adf8e000-9d4e-11ea-863f-3fbb57aae9d0.png" alt="image" style="zoom:50%;" />



**소스를 통해 살펴보자**

~~~java
//구현을 공유해야할 필요가 있으면 추상클래스, 공유할 필요가 없으면 인터페이스
abstract public class Component {
  private String name;

  public Component(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}

//Leaf
public class File extends Component{
  private Object data;

  public File(String name) {
    super(name);
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }
}

//Composite
public class Folder extends Component {
  List<Component> children = new ArrayList<>();

  public Folder(String name) {
    super(name);
  }

  public boolean addComponent(Component component) {
    return children.add(component);
  }

  public boolean removeComponent(Component component) {
    return children.remove(component);
  }

  public List<Component> getChildren() {
    return children;
  }

  public void setChildren(List<Component> children) {
    this.children = children;
  }
}

//Main
public static void main(String[] args) {
  Folder
  root = new Folder("root"),
  home = new Folder("home"),
  mesung = new Folder("mesung"),
  music = new Folder("music"),
  picture = new Folder("picture"),
  doc = new Folder("doc"),
  usr = new Folder("usr");

  File
  track1 = new File("track1"),
  track2 = new File("track2"),
  pic1 = new File("pic1"),
  doc1 = new File("doc1"),
  java = new File("java");

  root.addComponent(home);
  	home.addComponent(mesung);
  		mesung.addComponent(music);
  			music.addComponent(track1);
  			music.addComponent(track2);
  		mesung.addComponent(picture);
  			picture.addComponent(pic1);
  		mesung.addComponent(doc);
  			doc.addComponent(doc1);
  root.addComponent(usr);
  	usr.addComponent(java);

  show(root);
}

//print
private static void show(Component component) {
  System.out.println(component.getClass().getName()+"|"+component.getName());
  if(component instanceof Folder) {
    for(Component c : ((Folder)component).getChildren()) {
      show(c);
    }
  }
}

//실행 결과
com.pattern.ch10_composite.Folder|root
com.pattern.ch10_composite.Folder|home
com.pattern.ch10_composite.Folder|mesung
com.pattern.ch10_composite.Folder|music
com.pattern.ch10_composite.File|track1
com.pattern.ch10_composite.File|track2
com.pattern.ch10_composite.Folder|picture
com.pattern.ch10_composite.File|pic1
com.pattern.ch10_composite.Folder|doc
com.pattern.ch10_composite.File|doc1
com.pattern.ch10_composite.Folder|usr
com.pattern.ch10_composite.File|java
~~~

