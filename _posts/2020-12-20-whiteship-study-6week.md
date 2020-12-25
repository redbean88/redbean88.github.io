---
title : "자바스터디 6주차"
date : 2020-12-20
categories : study
---

# GOAL
> 자바의 상속에 대해 학습하세요.

# 학습할 것 (필수)
### 자바 상속의 특징
Java의 상속은 하나의 객체가 부모 객체의 모든 속성과 동작을 획득하는 방법이다.
기본개념은 기존에 만들어 놓은 클래스를 기반으로 하는 새 클래스를 만들어 사용하는 것으로,
상속을 받은 클래스의 경우, 기존의 클래스의 기능을 사용하는 것은 물론, 기존 클래스의 기능을 수정,
신규 메소드의 추가, 필드의 추가를 할 수 있다. 보통 부모-자식관계라고 하며 IS-A 관계이다.
> 왜 사용하죠?
+ 코드의 재사용을 위해서
+ 유연한 구조 생성을 위해서(이 내용에 대해서는 아래의 오버라이딩, 메소드 디스패치 , 더블 디스패치를 확인)

> 사용방법

클래스 생성시, extend 라는 키워드를 이용하여 사용 가능하다
```java
package week6;

public class Parent{
    public void print(){
        System.out.println("parent");
    }
}
```
> 컴파일 코드
```
public class week6.Parent {
  public week6.Parent();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public void print();
    Code:
       0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
       3: ldc           #3                  // 런타임 상수풀에 String parent를 push
       5: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
       8: return
}
```
```java
package week6;

public class Child extends Parent {

    public void print(){
        System.out.println("child");
    }
}
```
> 컴파일 코드
```
public class week6.Child extends week6.Parent {
  public week6.Child();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method week6/Parent."<init>":()V
       4: return

  public void print();
    Code:
       0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
       3: ldc           #3                  // 런타임 상수풀에 String child를 push
       5: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
       8: return
}
```
해당 코드를 사용할 매인메소드를 구성해보자
```java
package week6;

public class Main {
    public static void main(String[] args) {
        Parent parent = new Child();
        parent.print();     // child
    }
}
```
> 컴파일 코드
```
public class week6.Main {
  public week6.Main();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: new           #2                  // class week6/Child
       3: dup
       4: invokespecial #3                  // Method week6/Child."<init>":()V
       7: astore_1
       8: aload_1
       9: invokevirtual #4                  // Method week6/Parent.print:()V
      12: return
}
```
위 코드의 주목할 점은 9번으로 child가 호출되었기 때문에 자식 메소드인 Child가 호출 될것 같았는데,
 놀랍게도(?) 부모메소드인 Parent가 호출된다는것을 확인 할수 있다. 이 궁금증은 잠시뒤에 확인해 보자
  
#### 상속의 유형
상속은 크게 단일 , 다중 레벨 , 계층 구조 상속이 있다.(자바는 다중상속은 지원하지 않는다.)
![상속](https://redbean88.github.io/img/typesofinheritance.jpg)

#### 상속의 단점
이렇게 유용한 상속에도 단점이 존재한다. 복잡도가 증가할수록 상속이 중첩될수록 메소드 오버라이드로 인한
사이드이펙트를 감당하기 힘들어진다. 쉽게 예를 들어 위의 예시인 Child 클래스를 상속한 Child2가 있고
 그 Child2를 상속한 Child3가 있고 그런 방식을 반복하여 Child100 까지 있다고 생각했을 경우, 
 print()메소드를 사용하라고 말 할수 있을까? 

### super 키워드
#### super
super 키워드는 호출된 객체의 부모 클래스 또는 상위 클래스 중 해당 필드를 호출할 수 있는 가장 
가까운 값을 호출한다. 이해가 안된다면 아래의 예제를 통해 사용법과 함께 알아보자
```java
package week6;

public class Pparent {
    int test= 10;
}
```
> 컴파일코드
```
public class week6.Pparent {
  int test;

  public week6.Pparent();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: aload_0
       5: bipush        10
       7: putfield      #2                  // Field test:I
      10: return
}
```
```java
package week6;

public class Parent extends Pparent{
    int test = 1;
}
```
> 컴파일 코드
```
public class week6.Parent extends week6.Pparent {
  public week6.Parent();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method week6/Pparent."<init>":()V
       4: return
}

```
```java
package week6;

public class Child extends Parent {

    public void test(){
        System.out.println(test);           // 1
        System.out.println(this.test);      // 1
        System.out.println(super.test);     // 1
    }
}
```
> 컴파일 코드
```
public class week6.Child extends week6.Parent {
  public week6.Child();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method week6/Parent."<init>":()V
       4: return

  public void test();
    Code:
       0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
       3: aload_0
       4: getfield      #3                  // 객체에서 필드를 가져온다 (Field test:I)
       7: invokevirtual #4                  // Method java/io/PrintStream.println:(I)V
      10: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
      13: aload_0
      14: getfield      #3                  // 객체에서 필드를 가져온다 (Field test:I)
      17: invokevirtual #4                  // Method java/io/PrintStream.println:(I)V
      20: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
      23: aload_0
      24: getfield      #5                  // 객체에서 필드를 가져온다 (Field week6/Parent.test:I)
      27: invokevirtual #4                  // Method java/io/PrintStream.println:(I)V
      30: return
}
```
여기서 확인해보면 4,14번은 Child 객체의 필드를 사용하지만, 24번(super.test)의 경우는 부모 객체의 필드를 호출한다.

#### super()

전주 학습에서 우리는 객체 생성시 생성자가 필수적으로 생성되여야 하며 자동으로 생성된다는 것을 알수 있었다.
때문에 자식 클래스에서 부모클래스를 이용하기 위해서는 부모클래스를 이용한 객체가 __생성 될 수 있어야__ 한다.
자바에서는 친절하게도 자식 클래스를 생성시 부모클래스를 생성 할 수 있도록 __자식 클래스의 생성자__ 에서 부모클래스의 생성자를
호출 할 수 있는 __super()__ 를 자동으로 호출 해 준다. 때문에 기본 생성자를 호출 할 수 없는 아래와 같은 경우에는 컴파일 에러가 
발생 한다.

```java
public class Parent{    //이 경우, 자식클래스에서 아규먼트를 넣어 호출하면 정상 호출이 가능하다.
    int test;

    public Parent(int test) {
        this.test = test;
    }
}

public class Parent{

    private Parent() {
    }
}
```

> 왜 있어요?

자식 클래스를 생성시, 부모의 필드의 값을 변경하고 싶을수 있다. 또는 추가적인 로직이 필요할때 응용하면 되겠다.

### 메소드 오버라이딩
하위 클래스 또는 하위 클래스가 해당 부모 클래스 또는 상위 클래스 중 하나에서 이미 제공 한 메서드를 재구현 할수 있도록 하는 기능으로,
 다형성을 __런타임__ 시 지원하는 한가지 방법이다.
 실행되는 메서드는 호출에 사용되는 객체에 의해 결정된다.
### 다이나믹 메소드 디스패치 (Dynamic Method Dispatch)
다이다믹 메소드 디스패치는 오버라이딩된 메소드를 처리할때 컴파일시 처리가 아닌 __런타임__ 시 처리하는 메커니즘이다.
부모클래스를 통해 오버라이딩 된 메소드가 호출 될 경우(자식 클래스를 부모클래스로 형 변환하여, 오버라이딩된 메소드를 호출 할 경우)
자바에서는 호출할 메소드 실행시, 호출 당시의 참조된(가르키는) 객체에 기반하여 해당 메소드를 결정 하게된다.
다시말해, __런타임__ 시에, 어떤 객체에 어떤 메소드를 실행 할지가 결정된다.
런타임시, 참조된 객체의 타입에 의존하며(레퍼런스 변수의 타입이 아니다. 어렵다면 아래 예제를 참고), 해당 객체는 부모클래스가 참조 __할 수있는__ 자식클래스의 실행될 오버라이딩된 메소드를 결정하게 된다.
This is also known as upcasting. Java uses this fact to resolve calls to overridden methods at run time.
이런 방식은 업케스팅이라고도 
### 추상 클래스
추상 클래스는 클래스와 인터페이스의 중간정도의 역할을 하는 클래스이다.
 가장 큰 장점은 객체 생성를 못하면서 상속의 기능을 사용할 수 있는 점이다. 
 위에서 확인 했듯 우리는 객체를 생성하지 못하면 상속도 사용 할 수 없다.
 하지만 우리는 객체 생성을 막아 직접적으로 객체 조작을 못하게 하면서, 구현은 받아서 쓰고 싶을수 있다.
오라클 공식 문서에서는 다음과 같은 사항일때, 추상 메소드 사용을 고려해보라고 하고있다
+ 여러 클래스 간의 구현된 메소드를 공유 하고 싶다.
+ 확장 하려는 클래스에 공통적으로 사용하는 필드가 있거나 public 이외의 접근제어자를 설정 하고 싶다.
+ static 또는 final 이외의 필드를 사용하고 싶다. 
```java
package week6;

public abstract class AbstractClass {
    protected int a;

    public void test(){
        System.out.println("abstract");
    }
}
```

> 컴파일 코드

```
public abstract class week6.AbstractClass {
  protected int a;

  public week6.AbstractClass();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public void test();
    Code:
       0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
       3: ldc           #3                  // String abstract
       5: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
       8: return
}
```

```java
package week6;

public class AbstractImpl extends AbstractClass{
}
```

> 컴파일 코드

```
public class week6.AbstractImpl extends week6.AbstractClass {
  public week6.AbstractImpl();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method week6/AbstractClass."<init>":()V
       4: return
}
```

매인클래스를 확인해보자

```java
package week6;

public class Main {
    public static void main(String[] args) {
        // AbstractClass abs = new AbstractClass(); 컴파일 에러
        AbstractImpl abs = new AbstractImpl();
        abs.test();     //abstract
    }
}
```

> 컴파일 코드

```
public class week6.Main {
  public week6.Main();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: new           #2                  // class week6/AbstractImpl
       3: dup
       4: invokespecial #3                  // Method week6/AbstractImpl."<init>":()V
       7: astore_1
       8: aload_1
       9: invokevirtual #4                  // Method week6/AbstractImpl.test:()V
      12: return
}
``` 

상속과 유사하게, 부모 클래스의 구현 부를 사용할 수 있다. 그러면서도 new 키워드를 통한 생성을 불가능 하다.

### final 키워드
final 은 변수, 메서드 또는 클래스에만 적용 할 수 있는 비접근 수정자 이다. 용법은 아래와 같다.
#### final 변수
변수가 값일 경우, 기본적으로 변경이 불가능한 상수지만, 변수가 참조 값일 경우(예:map , array 등) 
해당 객체의 내부 상태(추가,삭제)가 가능하다
```java
package week6;

import java.util.HashMap;
import java.util.Map;

public class Finalval {
    final int num =1;
    final Map<String , String> map = new HashMap<>();

    public void setData(){
        //num = 2; // 컴파일 에러
        //map = new HashMap<>(); //컴파일 에러
        map.put("test","test");
    }
}
```

#### final 메소드
오버라이딩을 막아 재정의 방지

```java
package week6;

public class FinalMethod {

    final void method(){

    }
}


public class FinalMethodChild extends FinalMethod{
    
   /* void method(){
        
    }*/ //컴파일 에러
}
```

#### final 클래스
상속을 방지하여 확장을 금지(Integer , Float 등)
String 클래스와 같이 __불변 클래스__ 를 생성

```java
package week6;

public final class FinalClass {
}

public class FinalClassChild extends FinalClass {   //컴파일 에러
}
```

컴파일 후 소스에서는 final키워드에 의한 변화를 확인 할 수 없다. 다른 방식으로 확인 방식을 아시는 분은 답글좀 부탁드립니다...


### Object 클래스
Object 클래스는 기본적으로 자바의 모든 클래스의 부모 클래스로, 즉, Java의 최상위 클래스이다.
Object 클래스는 유형을 모르는 객체를 참조하려는 경우 이용하면 좋지만, 가능하다면 제네릭을 사용하도록 하면 좋다.

아래는 Object에서 기본적으로 제공하는 메소드이다.

|메소드명|설명|
|:--|--|
|protected Object clone()	|해당 객체의 복제본을 생성하여 반환함.|
|boolean equals(Object obj)	|해당 객체와 전달받은 객체가 같은지 여부를 반환함.|
|protected void finalize()	|해당 객체를 더는 아무도 참조하지 않아 가비지 컬렉터가 객체의 리소스를 정리하기 위해 호출함.|
|Class<T> getClass()	|해당 객체의 클래스 타입을 반환함.|
|int hashCode()	|해당 객체의 해시 코드값을 반환함.|
|void notify()	|해당 객체의 대기(wait)하고 있는 하나의 스레드를 다시 실행할 때 호출함.|
|void notifyAll()	|해당 객체의 대기(wait)하고 있는 모든 스레드를 다시 실행할 때 호출함.|
|String toString()	|해당 객체의 정보를 문자열로 반환함.|
|void wait()	|해당 객체의 다른 스레드가 notify()나 notifyAll() 메소드를 실행할 때까지 현재 스레드를 일시적으로 대기(wait)시킬 때 호출함.|
|void wait(long timeout)	| 해당 객체의 다른 스레드가 notify()나 notifyAll() 메소드를 실행하거나 전달받은 시간이 지날 때까지 현재 스레드를 일시적으로 대기(wait)시킬 때 호출함.|
|void wait(long timeout, int nanos)	|해당 객체의 다른 스레드가 notify()나 notifyAll() 메소드를 실행하거나 전달받은 시간이 지나거나 다른 스레드가 현재 스레드를 인터럽트(interrupt) 할 때까지 현재 스레드를 일시적으로 대기(wait)시킬 때 호출함.|
