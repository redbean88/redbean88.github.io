---
title : 자바스터디 8주차
date : 2021-01-08 00:00:00 +0000
categories: [study]
--- 

# GOAL
> 자바의 인터페이스에 대해 학습하세요

# 학습할 것 (필수)
+ 인터페이스 정의하는 방법
+ 인터페이스 구현하는 방법
+ 인터페이스 레퍼런스를 통해 구현체를 사용하는 방법
+ 인터페이스 상속
+ 인터페이스의 기본 메소드 (Default Method), 자바 8
+ 인터페이스의 static 메소드, 자바 8
+ 인터페이스의 private 메소드, 자바 9

---

### 인터페이스 정의하는 방법
#### 인터페이스란?
인터페이스란 어원과 같이 대상간의 서로 약속된 규약으로써 이용된다.   
(이러한 규약은 정하기 나름이다. 어떤 나라는 악수를 하면 인사라고 생각하며, 어떤 나라는 볼에 키스를 해서 인사를 표현한다.)   
규약이란 사용을 강제 하는 것이다.
아래의 예를 살펴보자
```java
public interface Monitor {
    int _GLOBAL=0;
    void on();
    void off();
}
```
컴파일 코드
```
public interface week8.Monitor {
  public static final int _GLOBAL;
  public abstract void on();
  public abstract void off();
}

```
컴파일 소스를 보면 인터페이스에서 선언된 변수에는 public static final, 
메소드에는 public abstract 가 붙는 것은 알 수 있다.
이로 인해 우리는 첫째 인터페이스 내부의 변수는 __재할당이 불가능__ 하며, 두번째 추상 메소드와 같이 상속 받은 __자식 메소드에서 구현부를 구현__ 해야 한다는 것을 알 수 있다.

> 추가

접근 제어자로는 public 과 default를 사용한다

### 인터페이스 구현하는 방법
위에서 만든 Monitor의 구현체를 만들어 보자
```java
public class LG implements Monitor{

    public void globalValue(){
        System.out.println(_GLOBAL);
    }

    @Override
    public void on() {
        System.out.println("LG on");
    }

    @Override
    public void off() {
        System.out.println("LG off");
    }
}
public class SONY implements Monitor{

    public void globalValue(){
        System.out.println(_GLOBAL);
    }
    @Override
    public void on() {
        System.out.println("sony on");
    }

    @Override
    public void off() {
        System.out.println("sony off");
    }
}
```
컴파일 소스
```
public class week8.LG implements week8.Monitor {
  public week8.LG();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public void globalValue();
    Code:
       0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
       3: iconst_0
       4: invokevirtual #4                  // Method java/io/PrintStream.println:(I)V
       7: return

  public void on();
    Code:
       0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
       3: ldc           #5                  // String LG on
       5: invokevirtual #6                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
       8: return

  public void off();
    Code:
       0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
       3: ldc           #7                  // String LG off
       5: invokevirtual #6                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
       8: return
}

public class week8.SONY implements week8.Monitor {
  public week8.SONY();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public void globalValue();
    Code:
       0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
       3: iconst_0
       4: invokevirtual #4                  // Method java/io/PrintStream.println:(I)V
       7: return

  public void on();
    Code:
       0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
       3: ldc           #5                  // String sony on
       5: invokevirtual #6                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
       8: return

  public void off();
    Code:
       0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
       3: ldc           #7                  // String sony off
       5: invokevirtual #6                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
       8: return
}
```
인터페이스의 구현은 implements라는 예약어를 통해 인터페이스를 상속받으면, 구현가능하다.
상속받은 구현체(위 예제의 LG,SONY)는 인터페이스의 메소드를 __반드시__ 구현 해야만 한다.
이것이 위에서 설명한 __규약__ 의 이점이다. 예를 들어 모니터를 생산하는 업체가 100개 라고 가정했을때
어떤 업체는 100볼트에 사각형으로 생긴 코드를, 다른 업체는 별모양으로 생긴 코드를 만들었다고 생각해 보면
사용자의 입장에서는 모니터를 구매할 때 마다 새로운 코드를 어뎁터를 구매해야 할것 이다. 

### 인터페이스 레퍼런스를 통해 구현체를 사용하는 방법
사용법을 알아보자   
이전에 배운 상속과 유사하다. 코드를 보자
```java
public class Test {
    public static void main(String[] args) {
        Monitor monitor = new LG();
        monitor.on();
    }
}
```
컴파일소스
```
public class week8.Test {
  public week8.Test();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: new           #2                  // class week8/LG
       3: dup
       4: invokespecial #3                  // Method week8/LG."<init>":()V
       7: astore_1
       8: aload_1
       9: invokeinterface #4,  1            // 실제 인터페이스를 처리 InterfaceMethod week8/Monitor.on:()V
      14: return
}
```
상속과 비슷하게 인터페이스에 구현체를 할당 받아 사용 가능 하다.
할당받은 인터페이스 변수는 __인터페이스에 있는__ 메소드만 사용 가능하다.

> invokeinterface

인터페이스를 처리하는 컴파일러의 코드로 자세한 사항은 [이곳](https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.invokeinterface)에서 살펴보자

> 추가

익명 객체로 구현체를 만들지 않고 잠깐만 구현하여 사용할 수 있다.

```java
public class Test {
    public static void main(String[] args) {
        Monitor monitor = new Monitor() {
            @Override
            public void on() {
                
            }

            @Override
            public void off() {

            }
        };
    }
}
```
컴파일 소스
```
public class week8.Test {
  public week8.Test();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: new           #2                  // class week8/Test$1
       3: dup
       4: invokespecial #3                  // Method week8/Test$1."<init>":()V
       7: astore_1
       8: return
}
```

### 인터페이스 상속
인터페이스의 상속은 기존 상속과 차이점이 있다
+ 인터페이스를 상속 받은 클래스는 인터페이스의 모든 메소드를 구현 해야한다
+ 인터페이스는 __다중 상속__ 이 가능하다

다중 상속이 가능 하기 때문에 메소드 시그니처가 같은 인터페이스를 동시에 상속 받는 경우, 컴파일 에러가 발생 한다.

### 인터페이스의 기본 메소드 (Default Method), 자바 8
자바 8 버전에서 추가된 기능으로, 추상 메소드에서 사용하듯이 default 키워드를 이용 하여, 추가된 기능이 __구현된__ 인터페이스를 제공 할 수 있게 되었다.
아래의 예시를 보자
```java
public interface Monitor {
    void on();
    void off();
    default void add(){
        System.out.println("add");
    }
}

```
컴파일소스
```
public interface week8.Monitor {
  public abstract void on();

  public abstract void off();

  public default void add();
    Code:
       0: getstatic     #1                  // Field java/lang/System.out:Ljava/io/PrintStream;
       3: ldc           #7                  // String add
       5: invokevirtual #9                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
       8: return
}
```

위 처럼 생성한 메소드는 상속 받은 구현체에서 구현하지 않아도, 레퍼런스에서 사용 가능 하다

```java
public class Test {
    public static void main(String[] args) {
        Monitor monitor = new LG();
        monitor.add();
    }
}
```

컴파일 소스

```
public class week8.Test {
  public week8.Test();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: new           #7                  // class week8/LG
       3: dup
       4: invokespecial #9                  // Method week8/LG."<init>":()V
       7: astore_1
       8: aload_1
       9: invokeinterface #10,  1           // InterfaceMethod week8/Monitor.add:()V
      14: return
}
```

사용되는 메소드는 invokeinterface로 
기존 인터페이스와 동일하다


### 인터페이스의 static 메소드, 자바 8

유틸형 클래스에 사용하는 static 또한 지원된다. 인터페이스에 구현하여 유틸성으로 이용 할 수 있다.

```java
public interface Monitor {
    static void whiteUp(){
        System.out.println("whiteUp");
    };
}

public class Test {
    public static void main(String[] args) {
        Monitor.whiteUp();
    }
}
```

컴파일소스

```
public interface week8.Monitor {
  public static void whiteUp();
    Code:
       0: getstatic     #1                  // Field java/lang/System.out:Ljava/io/PrintStream;
       3: ldc           #7                  // String whiteUp
       5: invokevirtual #9                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
       8: return
}

public class week8.Test {
  public week8.Test();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: invokestatic  #7                  // InterfaceMethod week8/Monitor.whiteUp:()V
       3: return
}

```

사용되는 메소드는 invokestatic로 인터페이스의 invokeinterface를 사용하지 않는다

### 인터페이스의 private 메소드, 자바 9

인터페이스 내부에서만 사용 가능한 private 메소드가 추가 되어 내부적인 처리가 필요 할 경우, 이용 가능하도록 기능이 추가 되었다

```java

public interface Monitor {
    static void whiteUpStatic(){
        printStatic();
    };
    private static void printStatic(){
        System.out.println("whiteUpStatic");
    }
    default void whiteUp(){
        print();
    }
    private void print(){
        System.out.println("whiteUp");
    }
}

public class Empty implements Monitor{
}

public class Test {
    public static void main(String[] args) {
        Monitor.whiteUpStatic();
        Monitor monitor = new Empty();
        monitor.whiteUp();
    }
}
```

컴파일 소스

```
public interface week8.Monitor {
  public static void whiteUpStatic();
    Code:
       0: invokestatic  #1                  // InterfaceMethod printStatic:()V
       3: return

  public default void whiteUp();
    Code:
       0: aload_0
       1: invokeinterface #21,  1           // InterfaceMethod print:()V
       6: return
}

public class week8.Empty implements week8.Monitor {
  public week8.Empty();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return
}

public class week8.Test {
  public week8.Test();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: invokestatic  #7                  // InterfaceMethod week8/Monitor.whiteUpStatic:()V
       3: new           #12                 // class week8/Empty
       6: dup
       7: invokespecial #14                 // Method week8/Empty."<init>":()V
      10: astore_1
      11: aload_1
      12: invokeinterface #15,  1           // InterfaceMethod week8/Monitor.whiteUp:()V
      17: return
}

```

Monitor 클래스를 살펴보면 private 메소드는 컴파일소스에서 확인 불가능 하다. 때문에 상속받은 클래스에서 사용이 불가하며 이를 이용하여 캡슐화를 진행 할 수 있다

> 첨언

위에서 설명 했듯이 인터페이스는 규약에 가깝다 하지만, static 이나 default 메소드는 규약에 적혀있지 않은 __편법__ 에 가깝다고 생각한다. 너무 지나친 자유는 오히려 독이 될수 있다.



참조
[인터페이스](https://www.notion.so/4b0cf3f6ff7549adb2951e27519fc0e6)
