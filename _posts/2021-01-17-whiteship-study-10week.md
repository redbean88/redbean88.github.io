---
title : "자바스터디 10주차"
date : 2021-01-18
categories : study
---


###### 아래 내용은 https://github.com/Yadon079/yadon079.github.io/blob/master/_posts/dev/java/study%20halle/2021-01-18-week-10.md 내용을 차용했음을 알려드립니다.


# GOAL

자바의 멀티쓰레드 프로그래밍에 대해 학습한다.

# 학습할 것

+ [Thread 클래스와 Runnable 인터페이스](#thread-클래스와-runnable-인터페이스)
+ [쓰레드의 상태](#쓰레드의-상태)
+ [쓰레드의 우선순위](#쓰레드의-우선순위)
+ [Main 쓰레드](#main-쓰레드)
+ [동기화](#동기화)
+ [데드락](#데드락)

# Thread 클래스와 Runnable 인터페이스

## 프로세스와 쓰레드

&nbsp;&nbsp;&nbsp;프로세스(process)란 간단히 말해서 '실행 중인 프로그램(program)'이다. 프로그램을 실행하면 OS로부터 실행에 필요한 자원(메모리)를 할당받아 프로세스가 된다.

프로세스는 프로그램을 수행하는 데 필요한 데이터와 메모리 등의 자원 그리고 쓰레드로 구성되어 있으며 프로세스의 자원을 이용해서 실제로 작업을 수행하는 것이 바로 쓰레드이다.

모든 프로세스에는 최소한 하나 이상의 쓰레드가 존재하며, 둘 이상의 쓰레드를 가진 프로세스를 <b>멀티쓰레드 프로세스(multi-threaded process)</b>라고 한다.

공장(프로세스)에서 일하는 일꾼(쓰레드)라고 생각하면 된다.

![](https://github.com/Yadon079/yadon079.github.io/raw/master/assets/img/study/thread04.png)

하나의 프로세스가 가질 수 있는 쓰레드의 개수는 제한되어 있지 않다. 하지만 쓰레드가 작업을 수행하는데 개별적인 메모리 공간(호출스택)을 필요로 하기 때문에 프로세스의 메모리 한계에 따라 생성할 수 있는 쓰레드의 수가 결정된다.

## 멀티태스킹과 멀티쓰레딩

&nbsp;&nbsp;&nbsp;우리가 사용하는 대부분의 OS는 멀티태스킹(multi-tasking, 다중작업)을 지원하기 때문에 여러 개의 프로세스가 동시에 실행될 수 있다.

멀티쓰레딩은 하나의 프로세스 내에서 여러 쓰레드가 동시에 작업을 수행하는 것이다. CPU의 코어(core)가 한 번에 단 하나의 작업만 수행할 수 있으므로, 실제로 동시에 처리되는 작업의 개수는 코어의 개수와 일치한다. 그러나 처리해야 하는 쓰레드의 수는 항상 코어의 수보다 많기 때문에 각 코어가 짧은 시간 동안 여러 작업을 번갈아 가며 수행함으로써 여러 작업들이 모두 동시에 수행되는 것처럼 보이게 한다.

프로세스의 성능이 단순히 쓰레드의 개수에 비례하는 것은 아니며, 하나의 쓰레드를 가진 프로세스 보다 두 개의 쓰레드를 가진 프로세스가 오히려 더 낮은 성능을 보일 수도 있다.

## 쓰레드의 구현과 실행

&nbsp;&nbsp;&nbsp;쓰레드를 구현하는 방법은 Thread 클래스를 상속받는 방법과 Runnable 인터페이스를 구현하는 방법, 모두 두 가지가 있다. 어느 쪽을 선택해도 별 차이는 없지만 <b>Thread 클래스를 상속받으면 다른 클래스를 상속받을 수 없기 때문에</b>, Runnable 인터페이스를 구현하는 방법이 일반적이다.

&#9654;<b>Thread 클래스를 상속</b>

```java
class MyThread extends Thread {

    @Override
    public void run() { ... } // Thread 클래스의 run()을 오버라이딩
}
```

&#9654;<b>Runnable 인터페이스를 구현</b>

```java
class MyThread implements Runnable {
    public void run() { ... } // Runnable 인터페이스의 run()을 구현
}
```

Runnable 인터페이스는 오로지 `run()`만 정의되어 있는 간단한 인터페이스이다. Runnable 인터페이스를 구현하기 위해서 해야 할 일은 추상메서드인 run()의 몸통{ }을 만들어 주는 것 뿐이다.

```java
    public interface Runnable {
        public abstract void run();
    }
```

### 쓰레드를 구현한다는 것은, 위의 두 방법 중 어떤 것을 선택하든, 쓰레드를 통해 작업하고자 하는 내용으로 run()의 몸통{ }을 채우는 것일 뿐이다.

```java
class App {
    public static void main(String[] args) {
        ThreadOne t1 = new ThreadOne();

        Runnable r = new ThreadTwo();
        Thread t2 = new Thread(r);      // 생성자 Thread(Runnable Target)

        t1.start();
        t2.start();
    }
}

class ThreadOne extends Thread {

    @Override
    public void run() {
        for(int i = 0; i < 3; i++) {
            System.out.println(getName()); // 조상인 Thread의 getName()을 호출
        }
    }
}

class ThreadTwo implements Runnable {
    public void run() {
        for(int i = 0; i < 3; i++) {
            // Thread.currentThread() : 현재 실행 중인 Thread를 반환
            System.out.println(Thread.currentThread().getName());
        }
    }
}
```

![](https://github.com/Yadon079/yadon079.github.io/raw/master/assets/img/study/thread01.png)

상속받을 때와 구현할 때의 인스턴스 생성 방법이 다르다.

```java
  ThreadOne t1 = new ThreadOne();   // Thread의 자손 클래스의 인스턴스를 생성

  Runnable r = new ThreadTwo();     // Runnable을 구현한 클래스의 인스턴스를 생성
  Thread t2 = new Thread(r);        // 생성자 Thread(Runnable Target)
```

Runnable 인터페이스를 구현한 경우, Runnable 인터페이스를 구현한 클래스의 인스턴스를 생성한 다음, 이 인스턴스를 Thread 클래스의 생성자의 매개변수로 제공해야 한다.

Thread 클래스를 상속받으면, 자손 클래스에서 조상인 Thread 클래스의 메서드를 직접 호출할 수 있지만, Runnable을 구현하면 Thread 클래스의 static 메서드인 `currentThread()`를 호출하여 쓰레드에 대한 참조를 얻어 와야만 호출이 가능하다.

+ <b>static Thread currentThread()</b>
  + 현재 실행중인 쓰레드의 참조를 반환한다.
+ <b>String getName()</b>
  + 쓰레드의 이름을 반환한다.

&#9654;<b>쓰레드의 이름</b>

쓰레드의 이름은 다음과 같은 생성자나 메서드를 통해서 지정 또는 변경할 수 있다.

```java
    Thread(Runnable target, String name)
    Thread(String name)
    void setName(String name)
```

쓰레드의 이름을 지정하지 않으면 'Thread-번호'의 형식으로 이름이 정해진다.

```java
    System.out.println(Thread.currentThread().getName());
```

위 코드는 아래 코드를 한 줄로 쓴 것이라고 생각하면 된다.

```java
    Thread t = Thread.currentThread();
    String name = t.getName();
    System.out.println(name);
```

## 쓰레드의 실행 - start()

&nbsp;&nbsp;&nbsp;쓰레드를 생성했다고 해서 자동으로 실행되는 것은 아니다. start()를 호출해야만 쓰레드가 실행된다.

```java
    t1.start();
    t2.start();
```

사실 start()가 호출되었다고 바로 실행되는 것은 아니고 실행대기 상태에 있다가 자신의 차례가 되어야 실행된다. 물론 실행대기 중인 쓰레드가 하나도 없으면 바로 실행상태가 된다.

한 번 실행이 종료된 쓰레드는 다시 실행할 수 없다. 즉, 하나의 쓰레드에 대해 start()가 한 번만 호출될 수 있다는 뜻이다.  
따라서 쓰레드의 작업을 한 번 더 수행해야 한다면 새로운 쓰레드를 생성한 다음 start()를 호출해야 한다. 만일 하나의 쓰레드에 대해 start()를 두 번 이상 호출하면 실행 시에 `IllegalThreadStateException`이 발생한다.

&#9654;<b>잘못된 호출</b>

```java
class App {
    public static void main(String[] args) {
        ThreadOne t1 = new ThreadOne();

        t1.start();
        t1.start();
    }
}

class ThreadOne extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(getName());
        }
    }
}
```

![](https://github.com/Yadon079/yadon079.github.io/raw/master/assets/img/study/thread02.png)

&#9654;<b>올바른 호출</b>

```java
class App {
    public static void main(String[] args) {
        ThreadOne t1 = new ThreadOne();

        t1.start();
        t1 = new ThreadOne(); // 다시 생성
        t1.start();
    }
}

class ThreadOne extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(getName());
        }
    }
}
```

![](https://github.com/Yadon079/yadon079.github.io/raw/master/assets/img/study/thread03.png)

# 쓰레드의 상태

&nbsp;&nbsp;&nbsp;쓰레드 프로그래밍이 어려운 이유는 동기화(synchronization)와 스케쥴링(scheduling)때문이다. 효율적인 멀티쓰레드 프로그램을 만들기 위해서는 보다 정교한 스케쥴링을 통해 프로세스에게 주어진 자원과 시간을 여러 쓰레드가 낭비없이 잘 사용하도록 프로그래밍 해야 한다.

## 쓰레드의 상태

쓰레드의 상태는 다음과 같다.

1. <b>NEW</b>  
: 쓰레드가 생성되고 아직 start()가 호출되지 않은 상태
2. <b>RUNNABLE</b>  
: 실행 중 또는 실행 가능한 상태
3. <b>BLOCKED</b>
: 동기화 블럭에 의해서 일시정지된 상태(lock이 풀릴 때까지 기다리는 상태)
4. <b>WAITING, TIMED_WATITING</b>
: 쓰레드의 작업이 종료되지는 않았지만 실행가능하지 않은(unrunnable) 일시정지 상태  
TIMED_WATITING은 일시정지시간이 지정된 경우를 의미한다.
5. <b>TERMINATED</b>
: 쓰레드의 작업이 종료된 상태

이러한 쓰레드의 상태는 JDK1.5부터 추가된 Thread의 `getState()` 메서드를 호출해서 확인할 수 있다.

## 쓰레드의 생성부터 소멸까지

![](https://github.com/Yadon079/yadon079.github.io/raw/master/assets/img/study/threadstate.png)

1. 쓰레드를 생성하고 start()를 호출하면 바로 실행되는 것이 아니라 실행대기열에 저장되어 차례를 기다린다. 실행대기열은 Queue와 같은 구조로 먼저 실행대기열에 들어온 쓰레드가 먼저 실행된다.
2. 실행대기상태에 있다가 자신의 차례가 되면 실행상태가 된다.
3. 주어진 실행시간이 다되거나 yield()를 만나면 다시 실행대기상태가 되고 다음 차례의 쓰레드가 실행상태가 된다.
4. 실행 중에 `suspend()`, `sleep()`, `wait()`, `join()`, `I/O block`에 의해 일시정지상태가 될 수 있다. `I/O block`은 입출력작업에서 발생하는 지연상태를 말한다.
5. 지정된 일시정지시간이 다되거나(time-out), `notify()`, `resume()`, `interrupt()`가 호출되면 일시정지상태를 벗어나 다시 실행대기열에 저장되어 차례를 기다린다.
6. 실행을 모두 마치거나 stop()이 호출되면 쓰레드는 소멸된다.

단, 무조건 번호 순서대로 쓰레드가 수행되는 것은 아니다.

## 쓰레드의 스케쥴링과 관련된 메서드

### &#9654; <b>sleep(long millis) - 일정시간동안 쓰레드를 멈추게 한다.</b>

&nbsp;&nbsp;&nbsp;sleep()은 지정된 시간동안 쓰레드를 멈추게 한다.

```java
    static void sleep(long millis)
    static void sleep(long millis, int nanos)
```

세밀하게 값을 지정할 수 있지만 어느 정도의 오차가 발생할 수도 있다.

sleep()에 의해 일시정지 상태가 된 쓰레드는 지정된 시간이 다 되거나 interrupt()가 호출되면(InterruptedException 발생), 실행대기 상태가 된다.  
따라서 sleep()을 호출할 때는 항상 try-catch문으로 예외를 처리해줘야 한다. 매번 try-catch문을 사용하기 번거롭기 때문에, 새로운 메서드로 만들어 사용하기도 한다.

```java
    void delay(long millis) {
        try {
            Thread.sleep(millis)
        } catch (InterruptedException e) {}
    }
```

```java
class App {
    public static void main(String[] args) {
        ThreadOne th1 = new ThreadOne();
        ThreadTwo th2 = new ThreadTwo();
        th1.start();
        th2.start();

        try {
            th1.sleep(2000);
        } catch(InterruptedException e) { }

        System.out.println("<< main 종료 >>");
    }
}

class ThreadOne extends Thread {

    @Override
    public void run() {
        for(int i = 0; i < 50; i++) {
            System.out.print("-");
        }
        System.out.println("<< th1 종료 >>");
    }
}

class ThreadTwo extends Thread {

    @Override
    public void run() {
        for(int i = 0; i < 50; i++) {
            System.out.print("|");
        }
        System.out.println("<< th2 종료 >>");
    }
}
```

![](https://github.com/Yadon079/yadon079.github.io/raw/master/assets/img/study/sleep.png)

예제의 결과를 보면 th1, th2, main 순으로 종료되었는데, 아래 코드를 생각하면 조금 의외이다.

```java
    th1.start();
    th2.start();

    try {
        th1.sleep(2000);
    } catch (InterruptedException e) { }

    System.out.println("<< main 종료 >>");
```

start()를 호출하고 `th1.sleep(2000)`를 호출하여 th1을 2초동안 일시정지상태로 만들었는데 th1이 가장 먼저 종료되었다.

그 이유는 sleep()이 항상 현재 실행 중인 쓰레드에 대해 작동하기 때문에 `th1.sleep(2000)`으로 호출하여도 실제로 영향을 받는 것은 main 메서드를 실행하는 main 쓰레드이다.

그래서 sleep()은 static으로 선언되어 있으며 참조변수를 이용해서 호출하기 보다는 `Thread.sleep(2000)`과 같이 해야한다.

### &#9654; <b>interrupt()와 interrupted() - 쓰레드의 작업을 취소한다.</b>

&nbsp;&nbsp;&nbsp;진행 중인 쓰레드의 작업이 끝나기 전에 취소시켜야할 때가 있다. interrupt()를 사용하면 쓰레드에게 작업을 멈추라고 요청한다. 단지 멈추라고 요청만 하는 것이고 쓰레드를 강제로 종료시키지는 못한다. interrupt()는 그저 쓰레드의 interrupted상태(인스턴스 변수)를 바꾸는 것일 뿐이다.

그리고 interrupted()는 쓰레드에 대해 interrupt()가 호출되었는지 알려준다. interrupt()가 호출되지 않았다면 false를, 호출되었다면 true를 반환한다.

```java
    Thread th = new Thread();
    th.start();
      ...
    th.interrupt();

    class MyThread extends Thread {
        public void run() {
            while(!interrupted()) {
                ...
            }
        }
     }
```

&nbsp;&nbsp;&nbsp;`isInterrupted()`도 쓰레드의 interrupt()가 호출되었는지 확인하는데 사용할 수 있지만, interrupted()와 달리 isInterrupted()는 쓰레드의 interrupt상태를 false로 초기화하지 않는다.

+ <b>void interrupt()</b>
  + 쓰레드의 interrupted상태를 false에서 true로 변경.
+ <b>boolean isInterrupted()</b>
  + 쓰레드의 interrupted상태를 반환.
+ <b>static boolean interrupted()</b>
  + 현재 쓰레드의 interrupted상태를 반환 후, false로 변경.

쓰레드가 sleep(), wait(), join()에 의해 <b>일시정지 상태(WAITING)</b>에 있을 때, 해당 쓰레드에 대해 interrupt()를 호출하면, sleep(), wait(), join()에서 InterruptedException이 발생하고 쓰레드는 <b>실행대기 상태(RUNNABLE)</b>로 바뀐다. 즉, 멈춰있던 쓰레드를 깨워서 실행가능한 상태로 만드는 것이다.

### &#9654; <b>suspend(), resume(), stop()</b>

&nbsp;&nbsp;&nbsp;suspend()는 sleep()처럼 쓰레드를 멈추게 한다. suspend()에 의해 정지된 쓰레드는 resume()을 호출해야 다시 실행대기 상태가 된다. stop()은 호출되는 즉시 쓰레드가 종료된다.

suspend(), resume(), stop()은 쓰레드의 실행을 제어하는 가장 손쉬운 방법이지만, suspend()와 stop()이 교착상태(deadlock)을 일으키기 쉽게 작성되어있으므로 사용이 권장되지 않는다. 그래서 이 메서드들은 모두 <b>deprecated</b>되었다. Java API문서 stop()을 찾아보면 아래와 같이 <b>Deprecated.</b>라고 적혀있다.

```
void stop(Throwable obj)
  Deprecated.
This method was originally designed to force a thread to stop and throw a given Throwable as an exception. It was inherently unsafe (see stop() for details), and furthermore could be used to generate exceptions that the target thread was not prepared to handle.
```

<b>deprecated</b>의 의미는 '전에는 사용되었지만, 앞으로는 사용하지 않을 것을 권장한다'는 의미이다. deprecated된 메서드는 하위 호환성을 위해서 삭제하지 않는 것일 뿐이므로 사용해서는 안 된다.

### &#9654; <b>yield() - 다른 쓰레드에게 양보한다.</b>

&nbsp;&nbsp;&nbsp;yield()는 쓰레드 자신에게 주어진 실행시간을 다음 차례의 쓰레드에게 양보(yield)한다. 예를 들어 스케쥴러에 의해 1초의 실행시간을 할당받은 쓰레드가 0.5초의 시간동안 작업한 상태에서 yield()가 호출되면, 나머지 0.5초는 포기하고 다시 실행대기상태가 된다.

### &#9654; <b>join() - 다른 쓰레드의 작업을 기다린다.</b>

&nbsp;&nbsp;&nbsp;join()은 자신의 작업 중간에 다른 쓰레드의 작업을 참여(join)시킨다는 의미로 이름 지어진 것이다. 쓰레드 자신이 하던 작업을 잠시 멈추고 다른 쓰레드가 지정된 시간동안 작업을 수행하도록 할 때 사용한다.

```java
    void join()
    void join(long millis)
    void join(long millis, int nanos)
```

시간을 지정하지 않으면, 해당 쓰레드가 작업을 모두 마칠 때까지 기다리게 된다. 작업 중에 다른 쓰레드의 작업이 먼저 수행되어야할 필요가 있을 때 join()을 사용한다.

```java
    try {
        th1.join()
    } catch (InterruptedException e) { }
```

join()도 sleep()처럼 interrupt()에 의해 대기상태에서 벗어날 수 있으며, join()이 호출되는 부분을 try-catch문으로 감싸야 한다. join()은 여러모로 sleep()과 유사한 점이 많은데, sleep()과 다른 점은 join()은 현재 쓰레드가 아닌 특정 쓰레드에 대해 동작하므로 static메서드가 아니라는 것이다.

# 쓰레드의 우선순위

&nbsp;&nbsp;&nbsp;쓰레드는 우선순위(priority)라는 속성(멤버변수)를 가지고 있는데, 이 우선순위의 값에 따라 쓰레드가 얻는 실행시간이 달라진다. 쓰레드가 수행하는 작업의 중요도에 따라 쓰레드의 우선순위를 서로 다르게 지정하여 특정 쓰레드가 더 많은 작업시간을 갖도록 할 수 있다.

## 우선순위 지정하기

&nbsp;&nbsp;&nbsp;쓰레드의 우선순위와 관련된 메서드와 상수는 다음과 같다.

```java
    void setPriority(int newPriority) // 쓰레드의 우선순위를 지정한 값으로 변경한다.
    int getPriority()                 // 쓰레드의 우선순위를 반환한다.

    public static final int MAX_PRIORITY  = 10   // 최대 우선순위
    public static final int MIN_PRIORITY  = 1    // 최소 우선순위
    public static final int NORM_PRIORITY = 5    // 보통 우선순위
```

쓰레드가 가질 수 있는 우선순위의 범위는 1 ~ 10이며 숫자가 높을수록 우선순위가 높다.

&nbsp;&nbsp;&nbsp;쓰레드의 우선순위는 쓰레드를 생성한 쓰레드로부터 상속받는다. main메서드를 수행하는 쓰레드는 우선순위가 5이므로 main메서드 내에서 생성하는 쓰레드의 우선순위는 자동적으로 5가 된다.

```java
class App {
    public static void main(String[] args) {
        ThreadOne th1 = new ThreadOne();
        ThreadTwo th2 = new ThreadTwo();

        th2.setPriority(10);

        System.out.println("Priority of th1(-) : " + th1.getPriority());
        System.out.println("Priority of th2(|) : " + th2.getPriority());

        th1.start();
        th2.start();
    }
}

class ThreadOne extends Thread {

    @Override
    public void run() {
        for(int i = 0; i < 300; i++) {
            System.out.print("-");
            for(int x = 0; x < 10000000; x++); // 작업을 지연시키기위한 for문
        }
    }
}

class ThreadTwo extends Thread {

    @Override
    public void run() {
        for(int i = 0; i < 300; i++) {
            System.out.print("|");
            for(int x = 0; x < 10000000; x++);
        }
    }
}
```

![](https://github.com/Yadon079/yadon079.github.io/raw/master/assets/img/study/thread06.png)

한 가지 의문은 예제에서 th2의 우선순위를 높게했음에도 불구하고 th1이 먼저 완료되는 결과가 나왔다.

![](https://github.com/Yadon079/yadon079.github.io/raw/master/assets/img/study/thread05.png)

우선순위가 10에서 7로 낮아졌는데 이번에는 th2가 먼저 완료되었다. 몇 번 더 실행해본 결과 th2가 먼저 완료되는 경우가 많기는 했지만 th1이 먼저 완료되는 경우도 출력이 되었다. 이러한 결과에서 알 수 있듯이 <b>우선순위는 절대적으로 지켜지는 것이 아니다.</b> 다만 우선순위가 높은 쓰레드에게 상대적으로 많은 양의 실행시간이 주어지는 것일 뿐이다.

# Main 쓰레드

&nbsp;&nbsp;&nbsp;실제로 쓰레드를 실행시킬 때는 run()이 아니라 start()를 사용한다.

main메서드에서 run()을 호출하는 것은 생성된 쓰레드를 실행시키는 것이 아니라 단순히 클래스에 선언된 메서드를 호출하는 것일 뿐이다.

반면에 start()는 새로운 쓰레드가 작업을 실행하는데 필요한 호출스택(call stack)을 생성한 다음에 run()을 호출해서, 생성된 호출스택에 run()이 첫번째로 올라가게 한다.

모든 쓰레드는 독립적인 작업을 수행하기 위해 자신만의 호출스택이 필요하고, 새로운 쓰레드를 생성하고 실행시킬 때마다 새로운 호출스택이 생성되고 소멸되는 것을 반복한다.

호출스택은 이름처럼 스택(stack)과 같이 동작하며 가장 위에 있는 메서드가 현재 실행중인 메서드이고 나머지 메서드들은 대기상태이다. 그러나 쓰레드가 둘 이상인 경우에는 호출스택의 최상위에 있는 메서드일지라도 대기상태에 있을 수 있다.

## main쓰레드

&nbsp;&nbsp;&nbsp;main메서드의 작업을 수행하는 것도 쓰레드이며, 이를 main 쓰레드라고 한다. 프로그램을 실행하면 기본적으로 하나의 쓰레드를 생성하고, 그 쓰레드가 main메서드를 호출해서 작업이 수행되는 것이다.

보통 main메서드가 수행을 마치면 프로그램이 종료되지만, main메서드는 수행을 마쳤는데 다른 쓰레드가 아직 작업을 마치지 않은 상태라면 프로그램이 종료되지 않는다.

```java
class App {
    public static void main(String[] args) {
        ThreadOne th1 = new ThreadOne();
        th1.start();
    }
}

class ThreadOne extends Thread {

    @Override
    public void run() {
        throwException();
    }

    public void throwException() {
        try {
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```
![](https://github.com/Yadon079/yadon079.github.io/raw/master/assets/img/study/thread07.png)

결과를 보면 알 수 있듯이 호출스택의 첫 번째 메서드가 main메서드가 아니라 run메서드이다.

한 쓰레드가 예외를 발생해서 종료되어도 다른 쓰레드의 실행에는 영향을 미치지 않는다.

```java
class App {
    public static void main(String[] args) {
        ThreadOne th1 = new ThreadOne();
        th1.run();
    }
}

class ThreadOne extends Thread {

    @Override
    public void run() {
        throwException();
    }

    public void throwException() {
        try {
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

![](https://github.com/Yadon079/yadon079.github.io/raw/master/assets/img/study/thread08.png)

이전 예제와 달리 run()을 사용하여 새로운 쓰레드가 생성되지 않았다.

# 동기화

&nbsp;&nbsp;&nbsp;멀티쓰레드 프로세스의 경우 여러 쓰레드가 같은 프로세스 내의 자원을 공유하기 때문에 서로의 작업에 영향을 줄 수 있다.

이러한 일을 방지하기 위해서 한 쓰레드가 특정 작업을 끝마치기 전까지 다른 쓰레드에 의해 방해받지 않도록 하는 것이 필요하다. 그래서 도입된 개념이 바로 '임계 영역(critical section)'과 '잠금(락, lock)'이다.

공유 데이터를 사용하는 코드 영역을 임계 영역으로 지정해놓고, 공유 데이터(객체)가 가지고 있는 lock을 획득한 단 하나의 쓰레드만 이 영역 내의 코드를 수행할 수 있게 한다. 그리고 해당 쓰레드가 임계 영역 내의 모든 코드를 수행하고 벗어나서 lock을 반납해야만 다른 쓰레드가 반납된 lock을 획득하여 임계 영역의 코드를 수행할 수 있게 된다.

이렇게 <b>한 쓰레드가 진행 중인 작업을 다른 쓰레드가 간섭하지 못하도록 막는 것을 '쓰레드의 동기화(synchronization)'</b>라고 한다.

자바에서는 synchronized블럭을 이용해서 쓰레드의 동기화를 지원했지만, JDK1.5부터 `java.util.concurrent.locks`와 `java.util.concurrent.atomic`패키지를 통해서 다양한 방식으로 동기화를 구현할 수 있도록 지원하고 있다.

## synchronized를 이용한 동기화

&nbsp;&nbsp;&nbsp;가장 간단한 동기화 방법인 synchronized 키워드를 이용한 동기화는 두 가지 방식이 있다.

1. <b>메서드 전체를 임계 영역으로 지정</b>  
public <b>synchronized</b> void calcSum() {  
    // ...  
}

2. <b>특정한 영역을 임계 영역으로 지정</b>  
<b>synchronized</b>(객체의 참조변수) {  
    // ...  
}

첫 번째 방법은 메서드 앞에 synchronized를 붙이는 것인데, synchronized를 붙이면 메서드 전체가 임계 영역으로 설정된다. 쓰레드는 synchronized메서드가 호출된 시점부터 해당 메서드가 포함된 객체의 lock을 얻어 작업을 수행하다가 메서드가 종료되면 lock을 반환한다.

두 번째 방법은 메서드 내의 코드 일부를 블럭으로 감싸고 블럭 앞에 `synchronized(참조변수)`를 붙이는 것인데, 이 때 참조변수는 락을 걸고자 하는 객체를 참조하는 것이어야 한다. 이 블럭을 synchronized블럭이라고 부르며, 이 블럭의 영역 안으로 들어가면서부터 쓰레드는 지정된 객체의 lock을 얻게 되고, 이 블럭을 벗어나면 lock을 반납한다.

&nbsp;&nbsp;&nbsp;두 방법 모두 lock의 획득과 반납이 모두 자동적으로 이루어지므로 우리가 해야 할 일은 그저 임계 영역만 설정해주는 것뿐이다.

```java
class App {
    public static void main(String[] args) {
        Runnable r = new RunnableEx();
        new Thread(r).start();
        new Thread(r).start();
        new Thread(r).start();
    }
}

class Account {
    private int balance = 1000;

    public int getBalance() {
        return balance;
    }

    public void withdraw(int money) {
        if (balance >= money) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
            balance -= money;
        }
    }
}

class RunnableEx implements Runnable {
    Account acc = new Account();

    public void run() {
        while (acc.getBalance() > 0) {
            // 100, 200, 300 중 임의의 한 값으로 출금(withdraw)
            int money = (int) (Math.random() * 3 + 1) * 100;
            acc.withdraw(money);
            System.out.println("balance : " + acc.getBalance());
            System.out.println("출금되었습니다.");
        }
    }
}
```

![](https://github.com/Yadon079/yadon079.github.io/raw/master/assets/img/study/thread10.png)

은행계좌에서 잔고를 확인하고 임의의 금액을 출금하는 예제이다. 코드 중 withdraw부분을 살펴보면 잔고가 출금하려는 금액보다 큰 경우에만 출금하도록 되어 있는 것을 확인할 수 있다.

```java
    public void withdraw(int money) {
        if (balance >= money) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) { }
            balance -= money;
        }
    }
```

그러나 실행결과를 보면 잔고가 음수 값으로 되어있는데, 그 이유는 한 쓰레드가 if문의 조건식을
통과하고 출금하기 바로 직전에 다른 쓰레드가 끼어들어서 출금을 먼저 했기 때문이다.

이러한 상황을 막기 위해서 synchronized를 사용하는 것이다.

아래 코드는 synchronized를 사용하여 수정한 코드이다.

```java
class App {
    public static void main(String[] args) {
        Runnable r = new RunnableEx();
        new Thread(r).start();
        new Thread(r).start();
        new Thread(r).start();
    }
}

class Account {
    private int balance = 1000;

    public int getBalance() {
        return balance;
    }

    public synchronized void withdraw(int money) {
        if (balance >= money) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
            balance -= money;
        }
    }
}

class RunnableEx implements Runnable {
    Account acc = new Account();

    public void run() {
        while (acc.getBalance() > 0) {
            // 100, 200, 300 중 임의의 한 값으로 출금(withdraw)
            int money = (int) (Math.random() * 3 + 1) * 100;
            acc.withdraw(money);
            System.out.println("balance : " + acc.getBalance());
            System.out.println("출금되었습니다.");
        }
    }
}
```

![](https://github.com/Yadon079/yadon079.github.io/raw/master/assets/img/study/thread09.png)

결과 값에 음수가 사라진 것을 확인할 수 있다. 여기서 한 가지 주의할 점은 Account클래스의 인스턴스 변수인 balance의 접근 제어자가 private이라는 것이다. 만일 private이 아니면, 외부에서 직접 접근할 수 있기 때문에 동기화가 무의미해 진다. <b>synchronized를 이용한 동기화는 지정된 영역의 코드를 한 번에 하나의 쓰레드가 수행하는 것을 보장하는 것</b>일 뿐이기 때문이다.

## wait()와 notify()

&nbsp;&nbsp;&nbsp;동기화를 이용해서 공유 데이터를 보호하는 것은 좋은데, 특정 쓰레드가 락을 보유한 채로 상황이 해결될 때까지 오랜 시간을 보내게 된다면, 다른 작업들이 원활히 진행되지 않을 것이다.

&nbsp;&nbsp;&nbsp;이러한 상황을 개선하기 위해 고안된 것이 바로 `wait()`와 `notify()`이다. 동기화된 임계 영역의 코드를 수행하다가 작업을 더 이상 진행할 상황이 아니면, 일단 wait()를 호출하여 쓰레드가 락을 반납하고 기다리게 한다. 그러면 다른 쓰레드가 락을 얻어 해당 객체에 대한 작업을 수행할 수 있게 된다. 나중에 작업을 진행할 수 있는 상황이 되면 notify()를 호출해서, 작업을 중단했던 쓰레드가 다시 락을 얻어 작업을 진행할 수 있게 한다.

lock이 넘어간다고 해서 무조건 오래 기다리던 쓰레드가 받게 된다는 보장은 없다. wait()을 호출하면 작업을 하던 쓰레드는 해당 객체의 대기실(waiting pool)로 이동하여 연락을 기다린다. notify()가 호출되면, 해당 객체의 대기실에 있던 모든 쓰레드 중 임의의 쓰레드만 연락을 받게되고, notifyAll()이 호출되면 연락은 모든 쓰레드가 받지만 랜덤하게 선택된 하나의 쓰레드가 lock을 받게 된다.

&nbsp;&nbsp;&nbsp;wait()와 notify()는 특정 객체에 대한 것이므로 Object클래스에 정의되어있다.

```java
    void wait()
    void wait(long timeout)
    void wait(long timeout, int nanos)
    void notify()
    void notifyAll()
```

wait()는 notify() 또는 notifyAll()이 호출될 때까지 기다리지만, 매개변수가 있는 wait()는 지정된 시간동안만 기다린다.

waiting pool은 객체마다 존재하는 것이므로 notifyAll()이 호출된다고 해서 모든 객체의 waiting pool에 있는 쓰레드가 깨워지는 것은 아니다. notifyAll()이 호출된 객체의 waiting pool에 대기 중인 쓰레드만 해당된다.

+ <b>wait(), notify(), notifyAll()</b>
  + Object에 정의되어 있다.
  + 동기화 블록(synchronized블록)내에서만 사용할 수 있다.
  + 보다 효율적인 동기화를 가능하게 한다.

### 기아 현상과 경쟁 상태

정말 지독하게 운이 나빠서 쓰레드가 연락을 받지 못하고 오랫동안 기다리게 되는데, 이것은 <b>기아(starvation) 현상</b>이라고 한다. 이 현상을 막으려면, notify() 대신 notifyAll()을 사용해야 한다.

notifyAll()로 원하는 쓰레드의 기아현상은 막았지만, 다른 쓰레드까지 연락을 받아서 불필요하게 lock을 얻기 위해 경쟁하게 된다. 이처럼 여러 쓰레드가 lock을 얻기 위해 서로 경쟁하는 것을 <b>경쟁 상태(race condition)</b>라고 하는데, 이것을 개선하기 위해서는 구별해서 연락하는 것이 필요하다.

## Lock과 Condition을 이용한 동기화

&nbsp;&nbsp;&nbsp;동기화할 수 있는 방법은 synchronized블럭 외에도 `java.util.concurrent.locks`패키지가 제공하는 lock클래스들을 이용하는 방법이 있다. 이 패키지는 JDK1.5에 와서야 추가된 것으로 그 전에는 동기화 방법이 synchronized블럭뿐이었다.

&nbsp;&nbsp;&nbsp;synchronized블럭으로 동기화를 하면 자동적으로 lock이 잠기고 풀리기 때문에 편리하다. 하지만 같은 메서드 내에서만 lock을 걸 수 있다는 제약이 불편하기도 하다. 그럴 때 이 lock클래스를 사용한다. lock클래스의 종류는 다음과 같이 3가지가 있다.

+ <b>ReentrantLock</b>
  + 재진입이 가능한 lock. 가장 일반적인 배타 lock
+ <b>ReentrantReadWriteLock</b>
  + 읽기에는 공유적이고, 쓰기에는 배타적인 lock
+ <b>StampedLock</b>
  + ReentrantReadWriteLock에 낙관적인 lock을 추가

<b>&#9654; ReentrantLock</b>

&nbsp;&nbsp;&nbsp;ReentrantLock은 가장 일반적인 lock이다. 'reentrant(재진입할 수 있는)'이라는 단어가 앞에 붙은 이유는 wait() & notify()처럼, 특정 조건에서 lock을 풀고 나중에 다시 lock을 얻어 이후의 작업을 수행할 수 있기 때문이다.

<b>&#9654; ReentrantReadWriteLock</b>

&nbsp;&nbsp;&nbsp;ReentrantReadWriteLock은 읽기를 위한 lock과 쓰기를 위한 lock을 제공한다. ReentrantLock은 배타적인 lock이라서 무조건 lock이 있어야만 임계 영역의 코드를 수행할 수 있지만, ReentrantReadWriteLock은 읽기 lock이 걸려있으면, 다른 쓰레드가 읽기 lock을 중복해서 걸고 읽기를 수행할 수 있다. 읽기는 내용을 변경하지 않으므로 동시에 여러 쓰레드가 읽어도 문제가 되지 않는다. 그러나 읽기 lock이 걸린 상태에서 쓰기 lock을 거는 것은 허용되지 않는다. 반대의 경우도 마찬가지다. 읽기를 할 때는 읽기 lock을 걸고, 쓰기 할 때는 쓰기 lock을 거는 것일 뿐 lock을 거는 방법은 같다.

<b>&#9654; StampedLock</b>

&nbsp;&nbsp;&nbsp;StampedLock은 lock을 걸거나 해지할 때 '스탬프(long타입의 정수값)'를 사용하며, 읽기와 쓰기를 위한 lock외에 '낙관적 읽기 lock(optimistic reading lock)'이 추가된 것이다. 읽기 lock이 걸려있으면, 쓰기 lock을 얻기 위해서는 읽기 lock이 풀릴 때까지 기다려야하는데 비해 '낙관적 읽기 lock'은 쓰기 lock에 의해 바로 풀린다. 따라서 낙관적 읽기에 실패하면, 읽기 lock을 얻어서 다시 읽어 와야 한다. <b>무조건 읽기 lock을 걸지 않고, 쓰기와 읽기가 충돌할 때만 쓰기가 끝난 후에 읽기 lock을 거는 것이다.</b>

```java
int getBalance() {
    long stamp = lock.tryOptimisticRead();  // 낙관적 읽기 lock을 건다.

    int curBalance = this.balance;    // 공유 데이터인 balance를 읽어온다.

    if(!lock.validate(stamp)) {   // 쓰기 lock에 의해 낙관적 읽기 lock이 풀렸는지 확인
        stamp = lock.readLock(); // lock이 풀렸으면, 읽기 lock을 얻으려고 기다린다.

        try {
            curBalance = this.balance;    // 공유 데이터를 다시 읽어온다.
        } finally {
            lock.unlockRead(stamp);     // 읽기 lock을 푼다.
        }
    }

    return curBalance;    // 낙관적 읽기 lock이 풀리지 않았으면 곧바로 읽어온 값을 반환
}
```

### ReentrantLock의 생성자

```java
    ReentrantLock()
    ReentrantLock(boolean fair)
```

생성자의 매개변수를 true로 주면, lock이 풀렸을 때 가장 오래 기다린 쓰레드가 lock을 획득할 수 있게, 즉 공정(fair)하게 처리한다. 그러나 공정하게 처리하면 가장 오래된 쓰레드를 찾는 과정이 추가되어 성능이 떨어진다.

&nbsp;&nbsp;&nbsp;대부분의 경우 공정하게 처리하지 않아도 문제가 되지 않으므로 공정함보다 성능을 선택한다.

```java
    void lock()         // lock을 잠근다.
    void unlock()       // lock을 해제한다.
    boolean isLocked()  // lock이 잠겼는지 확인한다.
```

자동적으로 lock의 잠금과 해제가 관리되는 synchronized블럭과 달리, ReentrantLock과 같은 lock클래스들은 수동으로 lock을 잠그고 해제해야 한다. 하지만 메서드를 호출하기만 하면되기 때문에 간단하다. lock을 걸고 나서 푸는 것을 잊지않도록 주의하자.

```java
    lock.lock();
    // 임계 영역
    lock.unlock();
```

임계 영역 내에서 예외가 발생하거나 return문으로 빠져 나가게 되면 lock이 풀리지 않을 수 있으므로 unlock()은 try-finally문으로 감싸는 것이 일반적이다. 참조변수 lock은 ReentrantLock객체를 참조한다고 가정하였다.

```java
    lock.lock();
    try {
        // 임계 영역
    } finally {
        lock.unlock();
    }
```

이렇게 하면, try블럭 내에서 어떤 일이 발생해도 finally블럭에 있는 unlock()이 수행되어 lock이 풀리지 않는 일은 발생하지 않는다. 대부분의 경우 synchronized블럭을 사용할 수 있어서 그냥 synchronized블럭을 사용하는 것이 나을 수 있다.

&#9654; tryLock()

&nbsp;&nbsp;&nbsp;tryLock()은 lock()과 달리, 다른 쓰레드에 의해 lock이 걸려 있으면 lock을 얻으려고 기다리지 않는다. 또는 지정된 시간만큼만 기다린다. lock을 얻으면 true, 얻지 못하면 false를 반환한다.

```java
    boolean tryLock()
    boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException
```

lock()은 lock을 얻을 때까지 쓰레드를 블락(block)시키므로 쓰레드의 응답성이 나빠질 수 있다. 응답성이 중요한 경우, tryLock()을 이용해서 지정된 시간동안 lock을 얻지 못하면 다시 작업을 시도할 것인지 포기할 것인지를 사용자가 결정할 수 있게 하는 것이 좋다.

이 메서드는 InterruptedException을 발생시킬 수 있는데, 이것은 지정된 시간동안 lock을 얻을려고 기다리는 중에 interrupt()에 의해 작업이 취소될 수 있도록 코드를 작성할 수 있다는 뜻이다.

### ReentrantLock과 Condition

Condition은 wait() & notify()에서 쓰레드를 구분해서 연락하지 못한다는 단점을 해결하기 위한 것이다.

&nbsp;&nbsp;&nbsp;wait() & notify()로 쓰레드의 종류를 구분하지 않고, 공유 객체의 waiting pool에 같이 몰아넣은 대신, 각각의 쓰레드의 Condition을 만들어서 각각의 waiting pool에서 기다리도록 하면 문제는 해결된다.

Condition은 이미 생성된 lock으로부터 new Condition()을 호출해서 생성한다.

```java
    private ReentrantLock lock = new ReentrantLock(); // lock을 생성

    // lock으로 condition을 생성
    private Condition forOne = lock.newCondition();
    private Condition forTwo = lock.newCondition();
```

그리고 wait() & notify() 대신 await() & signal()을 사용하면된다.

+ <b>void await()</b>
  + void await()
  + void awaitUninterruptibly()
+ <b>void await(long timeout)</b>
  + boolean await(long time, TimeUnit unit)
  + long awaitNanos(long nanosTimeout)
  + boolean awaitUntil(Date deadline)
+ <b>void notify()</b>
  + void signal()
+ <b>void notifyAll()</b>
  + void signalAll()

# 데드락

&nbsp;&nbsp;&nbsp;교착상태(데드락, deadlock)은 두 개 이상의 작업이 서로 상대방의 작업이 끝나기를 기다리고 있어서 아무것도 완료되지 못하는 상태를 말한다.

### 교착상태의 조건

1. 상호배제(Mutual exclusion) : 프로세스들이 필요로 하는 자원에 대해 배타적인 통제권을 요구한다.
2. 점유대기(Hold and wait) : 프로세스가 할당된 자원을 가진 상태에서 다른 자원을 기다린다.
3. 비선점(No preemption) : 프로세스가 어떤 자원의 사용을 끝낼 때까지 그 자원을 뺏을 수 없다.
4. 순환대기(Circular wait) : 각 프로세스는 순환적으로 다음 프로세스가 요구하는 자원을 가지고 있다.

> 위 조건 중에서 한 가지라도 만족하지 않으면 교착 상태는 발생하지 않는다. 이중 순환대기 조건은 점유대기 조건과 비선점 조건을 만족해야 성립하는 조건이므로, 위 4가지 조건은 서로 완전히 독립적인 것은 아니다.

교착상태는 예방, 회피, 무시 세 가지 방법으로 관리할 수 있다.

## 예방

+ <b>상호배제 조건의 제거</b>
  + 교착 상태는 두 개 이상의 프로세스가 공유가능한 자원을 사용할 때 발생하는 것이므로 공유 불가능한, 즉 상호 배제 조건을 제거하면 교착 상태를 해결할 수 있다.
+ <b>점유와 대기 조건의 제거</b>
  + 한 프로세스에 수행되기 전에 모든 자원을 할당시키고 나서 점유하지 않을 때에는 다른 프로세스가 자원을 요구하도록 하는 방법이다. 자원 과다 사용으로 인한 효율성, 프로세스가 요구하는 자원을 파악하는 데에 대한 비용, 자원에 대한 내용을 저장 및 복원하기 위한 비용, 기아 상태, 무한대기 등의 문제점이 있다.
+ <b>비선점 조건의 제거</b>
  + 비선점 프로세스에 대해 선점 가능한 프로토콜을 만들어 준다.
+ <b>환형 대기 조건의 제거</b>
  + 자원 유형에 따라 순서를 매긴다.

이 해결 방법들은 자원 사용의 효율성이 떨어지고 비용이 많이 드는 문제점이 있다.

## 회피

자원이 어떻게 요청될지에 대한 추가정보를 제공하도록 요구하는 것으로 시스템에 circular wait가 발생하지 않도록 자원 할당 상태를 검사한다.

교착 상태 회피 알고리즘은 크게 두가지가 있다.

1. [자원 할당 그래프 알고리즘](https://ko.m.wikipedia.org/w/index.php?title=%EC%9E%90%EC%9B%90_%ED%95%A0%EB%8B%B9_%EA%B7%B8%EB%9E%98%ED%94%84_%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98&action=edit&redlink=1) (Resource Allocation Graph Algorithm)
2. [은행원 알고리즘](https://ko.m.wikipedia.org/w/index.php?title=%EC%9D%80%ED%96%89%EC%9B%90_%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98&action=edit&redlink=1) (Banker's algorithm)

## 무시

예방과 회피방법을 활용하면 성능 상 이슈가 발생하는데, 데드락 발생에 대한 상황을 고려하는 것에 대한 비용이 낮다면 별다른 조치를 하지 않을 수도 있다고 한다.

다음 코드는 오라클에서 제공하는 데드락의 예제이다.

```java
public class Deadlock {
    static class Friend {
        private final String name;
        public Friend(String name) {
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
        public synchronized void bow(Friend bower) {
            System.out.format("%s: %s"
                + "  has bowed to me!%n",
                this.name, bower.getName());
            bower.bowBack(this);
        }
        public synchronized void bowBack(Friend bower) {
            System.out.format("%s: %s"
                + " has bowed back to me!%n",
                this.name, bower.getName());
        }
    }

    public static void main(String[] args) {
        final Friend alphonse =
            new Friend("Alphonse");
        final Friend gaston =
            new Friend("Gaston");
        new Thread(new Runnable() {
            public void run() { alphonse.bow(gaston); }
        }).start();
        new Thread(new Runnable() {
            public void run() { gaston.bow(alphonse); }
        }).start();
    }
}
```

![](https://github.com/Yadon079/yadon079.github.io/raw/master/assets/img/study/thread11.png)

---
**Reference**
+ [자바의 정석 3/e](http://www.kyobobook.co.kr/product/detailViewKor.laf?mallGb=KOR&ejkGb=KOR&barcode=9788994492032)
+ [Java in a Nutshell](https://www.amazon.com/Java-Nutshell-Desktop-Quick-Reference/dp/1492037257/ref=sr_1_1?dchild=1&keywords=Java+in+a+Nutshell&qid=1605393888&s=books&sr=1-1)
+ [오라클 공식 가이드](https://docs.oracle.com/javase/tutorial/java/TOC.html)
+ <https://docs.oracle.com/javase/tutorial/essential/concurrency/deadlock.html>