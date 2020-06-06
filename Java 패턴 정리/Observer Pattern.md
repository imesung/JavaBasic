## 옵저버 패턴

한 객체의 상태 변화에 따라 다른 객체의 상태도 연동되도록 일대다 객체 의존 관계를 구성하는 패턴이다.

---

그럼 언제 사용하는 걸까?

- 데이터의 변경이 발생했을 경우 상대 객체에 의존하지 않으면서 데이터 변경을 통보하고자 할 때 사용하기 유용하다.

Ex 1. 탐색기는 새로운 파일이 추가되거나 기존 파일이 삭제되었을 때 다른 탐색기에게 즉시 변경을 통보해야한다.

Ex 2. 차량 연료량 클래스는 연료량이 부족한 경우 연료량에 관심을 가지는 구체적인 클래스(연료량 부족 경고 클래스, 주행 가능 거리 출력 클래스)에 직접 의존하지 않는 방식으로 연료량의 변화를 통보해야한다.

<img src="https://user-images.githubusercontent.com/40616436/83949197-4f0e8d00-a85d-11ea-83e8-141dc4966483.png" alt="image" style="zoom:50%;" />

- 옵저버 패턴은 통보 대상 객체의 관리를 Subject 클래스와 Observer 인터페이스로 일반화한다. 이를 통해 데이터 변경을 통보하는 클래스(ConcreSubject)는 통보 대상 클래스(ConcreteObserver)에 대한 의존성을 없앨 수 있다.
- **결과적으로 통보 대상 클래스나 대상 객체의 변경에도 통보하는 클래스(ConcrateSubject)를 수정 없이 그대로 사용할 수 있는 것이다.**

---

**역할별로 살펴보자**

- Observer
  - 데이터의 변경을 통보 받는 인터페이스
  - Subject 에서는 Observer 인터페이스의 update() 메소드를 호출함으로써 **ConcreteSubject의 데이터 변경을 ConcreteObserver에게 통보**한다.
- Subject
  - ConcreteObserver 객체(통보를 받는 클래스)를 관리하는 요소
  - Observer 인터페이스를 참조해서 ConcreteObserver를 관리하므로 ConcreteObserver의 변화에 독립적일 수 있다.
- ConcreteSubject
  - 변경 관리 대상이 되는 데이터가 있는 클래스(통보하는 클래스)
  - 데이터 변경을 위한 메소드인 setState()가 있다.
  - setState() 메소드는 자신의 데이터인 subjectState를 변경하고 Subject의 notifyObservers() 메소드를 호출해서 ConcreteObserver 객체에 변경을 통보한다. 즉, Subject를 활용하여 ConcreteObserver에게 통보하는 것이다.
- ConcreteObserver
  - ConcreteSubject의 변경을 통보받는 클래스
  - Observer 인터페이스의 update() 메소드를 구현함으로써 변경을 통보받는다. Subject에서 update() 메소드를 호출하면서 변경을 통보 받는 것이다.
  - 변경된 데이터는 ConcreteSubject의 getState() 메소드를 호출함으로써 변경을 조회한다.

---

### 예시

**여러 가지 방식으로 성적을 출력해보자.**

<img src="https://user-images.githubusercontent.com/40616436/83949576-a3b30780-a85f-11ea-8feb-2e3b2b9cbd8d.png" alt="image" style="zoom:50%;" />

~~~java
//입력된 점수를 저장하는 클래스
public class ScoreRecord {
  private List<Integer> scores = new ArrayList<Integer>(); // 점수를 저장함
  private DataSheetView dataSheetView; // 목록 형태로 점수를 출력

  public void setDataSheetView(DataSheetView dataSheetView) {
    this.dataSheetView = dataSheetView; 
  }
  
  // 새로운 점수를 추가하면 출력하는 것에 변화를 통보(update())하여 출력하는 부분 갱신
	public void addScore(int score) {
	   scores.add(score); // scores에 점수를 추가함
	   dataSheetView.update(); // scores가 변경됨을 통보함
	}
  
	public List<Integer> getScoreRecord() {
    return scores; 
  }
}

//목록 형태로 출력하는 클래스
public class DataSheetView {
	private ScoreRecord scoreRecord;
	private int viewCount;

	public DataSheetView(ScoreRecord scoreRecord, int viewCount) {
		this.scoreRecord = scoreRecord;
		this.viewCount = viewCount;
	}

	// 점수의 변경을 통보받음
	public void update() {
		List<Integer> record = scoreRecord.getScoreRecord(); // 점수를 조회함
		displayScores(record, viewCount); // 조회된 점수를 viewCount 만큼만 출력함
	}

	private void displayScores(List<Integer> record, int viewCount) {
		System.out.println("List of " + viewCount + " entries: ");
		for (int i = 0; i < viewCount && i < record.size(); i++) {
			System.out.println(record.get(i) + " ");
		}
		System.out.println();
	}
}
~~~

ScoreRecord 클래스의 addScore() 메소드는 자신의 필드인 scores 객체에 점수를 추가한다. 그리고 DataSheetView 클래스의 update() 메소드를 호출함으로써 성적을 출력하도록 요청 하는 것이다.

또한, DataSheetView 클래스는 ScoreRecord 클래스의 getScoreRecord() 메소드를 호출하여 출력할 점수를 구하는 것이다. 이 때, DataSheetView 클래스의 update() 메소드에서는 구한 점수에서 명시된 개수만큼의 점수만 출력한다.

~~~java
public class Client {
  public static void main(String[] args) {
    ScoreRecord scoreRecord = new ScoreRecord();

    // 3개까지의 점수만 출력함
    DataSheetView dataSheetView = new DataSheetView(scoreRecord, 3);
    scoreRecord.setDataSheetView(dataSheetView);

    for (int index = 1; index <= 5; index++) {
      int score = index * 10;
      System.out.println("Adding " + score);

      // 10 20 30 40 50을 추가함, 추가할 때마다 최대 3개의 점수만 출력함
      scoreRecord.addScore(score);
    }
  }
}
~~~

~~~java
//결과
Adding 10
List of 3 entries: 
10 

Adding 20
List of 3 entries: 
10 
20 

Adding 30
List of 3 entries: 
10 
20 
30 

Adding 40
List of 3 entries: 
10 
20 
30 

Adding 50
List of 3 entries: 
10 
20 
30 
~~~

성적을 입력했을 때 성적을 저장 후 출력하고자 하는 카운트에 맞춰 성적을 출력하는 예시를 만들어보았다.

---

**하지만 이 예시의 소스는 문제점들이 존재한다.**

1. **성적을 다른 형태로 출력하는 경우 - 목록이 아닌 성적의 최소와 최대만 출력한다면?**

~~~java
//성적 출력 방식이 목록이 아닌 최소/최대로 변경되어 ScoreRecord 클래스 수정 필요.
public class ScoreRecord {
 private List<Integer> scores = new ArrayList<Integer>();
 private MinMaxView minMaxView; // 최소/최대 값만을 출력하는 형태의 클래스
 public void setMinMaxView(MinMaxView minMaxView) { this.minMaxView = minMaxView; }
  
 public void addScore(int score) {
   scores.add(score);
   minMaxView.update(); // MinMaxView에게 scores가 변경됨을 통보함
 }
 public List<Integer> getScoreRecord() { return scores; }
}

public class MinMaxView {
  private ScoreRecord scoreRecord;
  // getScoreRecord()를 호출하기 위해 ScoreRecord 객체를 인자로 받음
  public MinMaxView(ScoreRecord scoreRecord) {
   this.scoreRecord = scoreRecord;
  }
  public void update() {
   List<Integer> record = scoreRecord.getScoreRecord();
   displayScores(record);
  }
  
  // 최소값과 최대값을 출력함
  private void displayScores(List<Integer> record) {
   int min = Collections.min(record, null);
   int max = Collections.max(record, null);
   System.out.println("Min: " + min + ", Max: " + max);
  }
}
~~~

점수 변경에 대한 통보 대상 클래스가 다른 대상 클래스(DataSheetView -> MinMaxView)로 바뀌면 기존 코드(ScoreRecord 클래스)의 내용을 수정해야 하므로 **OCP에 위배**된다.



2. **동시 혹은 순차적으로 성적을 출력하는 경우**

성적이 입력되었을 때 최대 3개 목록, 최소/최대 값을 동시에 출력하려면? 혹은 처음에는 목록이 출력되고 나중에는 최소/최대 값을 출력하려면?

~~~java
public class ScoreRecord {
  private List<Integer> scores = new ArrayList<Integer>();
  private DataSheetView dataSheetView; // 목록 형태로 점수를 출력하는 클래스
  private MinMaxView minMaxView; // 최소/최대 값만을 출력하는 형태의 클래스
  public void setDataSheetView(DataSheetView dataSheetView) { this.dataSheetView = dataSheetView; }
  public void setMinMaxView(MinMaxView minMaxView) { this.minMaxView = minMaxView; }
  
  public void addScore(int score) {
 scores.add(score); // scores 목록에 주어진 점수를 추가함
 dataSheetView.update(); // scores가 변경됨을 통보함
 minMaxView.update(); // scores가 변경됨을 통보함
  }
  
  public List<Integer> getScoreRecord() {
 return scores;
  }
}

public class Client {
  public static void main(String[] args) {
   ScoreRecord scoreRecord = new ScoreRecord();
   // 3개까지의 점수만 출력함
   DataSheetView dataSheetView = new DataSheetView(scoreRecord, 3);
    
   // 최대값, 최소값만 출력함
   MinMaxView minMaxView = new MinMaxView(scoreRecord);
    
   // 각 통보 대상 클래스를 저장
   scoreRecord.setDataSheetView(dataSheetView);
   scoreRecord.setMinMaxView(minMaxView);
   
    // 10 20 30 40 50을 추가
   for (int index = 1; index <= 5; index++) {
     int score = index * 10;
     System.out.println("Adding " + score);
     // 추가할 때마다 최대 3개의 점수 목록과 최대/최소값이 출력됨
     scoreRecord.addScore(score);
   }
  }
}
~~~

이 경우에도 점수 변경에 대한 통보 대상 클래스가 다른 대상 클래스(DataSheetView -> MinMaxView)로 바뀌면 기존 코드(ScoreRecord 클래스)의 내용을 수정해야하므로 **OCP에 위배** 된다. 즉, **성적 변경을 새로운 클래스에 통보할 때마다 ScoreRecord 클래스의 코드를 수정해야 하므로 재사용하기가 어렵다.**

<img src="https://user-images.githubusercontent.com/40616436/83950456-de1fa300-a865-11ea-851f-c724b407df90.png" alt="image" style="zoom:50%;" />

---

**해결책**

문제를 해결하기 위해서는 **공통 기능을 상위 클래스 및 인터페이스로 일반화** 하고 이를 활용하여 통보하는 클래스(ScoreRecord)를 구현해야 한다. 즉 ScoreRecord 클래스에서 변화되는 부분을 식별하고 이를 일반화 시켜야 한다. 이렇게 되면 성적 통보 대상이 변경되더라도 ScoreRecord 클래스를 그대로 사용할 수 있다.

***ScoreRecord 클래스에서 하는 작업 확인 및 Observer 패턴 형식으로 변환***

- 통보 대상인 객체를 참조하는 것을 관리한다.(추가/제거) -> **Subject 클래스로 일반화한다.**
- addScore() 메소드 : 각 통보 대상인 객체의 update() 메소드를 호출 -> **Observer 인터페이스로 일반화한다.** 

<img src="https://user-images.githubusercontent.com/40616436/83950545-787fe680-a866-11ea-85c3-4f508b61e475.png" alt="image" style="zoom:50%;" />

---

- ScoreRecord 클래스의 addScore() 메소드 호출
  - 자신의 성적 값 저장
  - **상태가 변경될 때마다 Subject 클래스의 notifyObservers() 메소드 호출**
- Subject 클래스의 notifyObservers() 메소드 호출
  - Observer 인터페이스를 통해 성적 변경을 통보
  - DataSheetView 클래스에 update() 메소드 호출
  - MinMaxView 클래스에 update() 메소드 호출

~~~java
//추상화된 통보 대상
public interface Observer {
  // 데이터 변경을 통보했을 때 처리하는 메서드
  public abstract void update();
}


//추상화된 변경 관심 대상 데이터 - 데이터에 공통적으로 들어가야하는 메소드를 일반화
public abstract class Subject {
  // 추상화된 통보 대상 목록 (즉, 출력 형태에 대한 Observer)
  private List<Observer> observers = new ArrayList<Observer>();

  // 통보 대상(Observer) 추가
  public void attach(Observer observer) { observers.add(observer);}
  // 통보 대상(Observer) 제거
  public void detach(Observer observer) { observers.remove(observer);}
  // 각 통보 대상(Observer)에 변경을 통보. (List<Observer>객체들의 update를 호출)
  public void notifyObservers() {
    for (Observer o : observers) {
      o.update();
    }
  }
}


//구체적인 변경 감시 대상 데이터 - 출력 형태가 2가지 일때
public class ScoreRecord extends Subject{
  private List<Integer> scores = new ArrayList<Integer>(); // 점수를 저장함
  // 새로운 점수를 추가 (상태 변경)
  public void addScore(int score) {
      scores.add(score); // scores 목록에 주어진 점수를 추가함
      notifyObservers(); // scores가 변경됨을 각 통보 대상(Observer)에게 통보함
  }
  public List<Integer> getScoreRecord() { return scores; }
}


//통보 대상 클래스1 (update() 메소드 구현)
public class DataSheetView implements Observer{
	private ScoreRecord scoreRecord;
	private int viewCount;

	public DataSheetView(ScoreRecord scoreRecord, int viewCount) {
		this.scoreRecord = scoreRecord;
		this.viewCount = viewCount;
	}

	// 점수의 변경을 통보받음
	public void update() {
		List<Integer> record = scoreRecord.getScoreRecord(); // 점수를 조회함
		displayScores(record, viewCount); // 조회된 점수를 viewCount 만큼만 출력함
	}

	private void displayScores(List<Integer> record, int viewCount) {
		System.out.println("List of " + viewCount + " entries: ");
		for (int i = 0; i < viewCount && i < record.size(); i++) {
			System.out.println(record.get(i) + " ");
		}
		System.out.println();
	}
}

//통보 대상 클래스2 (update() 메소드 구현)
public class MinMaxView {
  private ScoreRecord scoreRecord;
  // getScoreRecord()를 호출하기 위해 ScoreRecord 객체를 인자로 받음
  public MinMaxView(ScoreRecord scoreRecord) {
   this.scoreRecord = scoreRecord;
  }
  public void update() {
   List<Integer> record = scoreRecord.getScoreRecord();
   displayScores(record);
  }
  
  // 최소값과 최대값을 출력함
  private void displayScores(List<Integer> record) {
   int min = Collections.min(record, null);
   int max = Collections.max(record, null);
   System.out.println("Min: " + min + ", Max: " + max);
  }
}


public class Client {
  public static void main(String[] args) {
    ScoreRecord scoreRecord = new ScoreRecord();

    // 3개까지의 점수만 출력함
    DataSheetView dataSheetView = new DataSheetView(scoreRecord, 3);
    // 최대값, 최소값만 출력함
    MinMaxView minMaxView = new MinMaxView(scoreRecord);

    // 각 통보 대상 클래스를 Observer로 추가
    scoreRecord.attach(dataSheetView);
    scoreRecord.attach(minMaxView);

    // 10 20 30 40 50을 추가
    for (int index = 1; index <= 5; index++) {
      int score = index * 10;
      System.out.println("Adding " + score);
      // 추가할 때마다 최대 3개의 점수 목록과 최대/최소값이 출력됨
      scoreRecord.addScore(score);
    }
  }
}
~~~

- Observer : 추상화된 통보 대상
- DataSheetView, MinMaxView : Observer를 implements함으로써 구체적인 통보대상이 된다.
- Subject : 성적 변경에 관심이 있는 대상 객체들을 관리
- ScoreRecord : Subject를 extends함으로써 구체적인 통보 대상을 직접 참조하지 않아도 된다.

---

Observer 패턴을 이용하면 ScoreRecord 클래스의 코드를 변경하지 않고 새로운 클래스 및 객체를 추가/제거하는 것이 가능해지는 것이다.

<img src="https://user-images.githubusercontent.com/40616436/83950739-f5f82680-a867-11ea-8442-66e8c0118666.png" alt="image" style="zoom:50%;" />