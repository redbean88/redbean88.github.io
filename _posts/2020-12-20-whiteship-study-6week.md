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
![상속]()

#### 상속의 단점
이렇게 유용한 상속에도 단점이 존재한다. 복잡도가 증가할수록 상속이 중첩될수록 메소드 오버라이드로 인한
사이드이펙트를 감당하기 힘들어진다. 쉽게 예를 들어 위의 예시인 Child 클래스를 상속한 Child2가 있고
 그 Child2를 상속한 Child3가 있고 그런 방식을 반복하여 Child100 까지 있다고 생각했을 경우, 
 print()메소드를 사용하라고 말 할수 있을까? 

### super 키워드
#### super
super 키워드는 호출된 객체를 기중으로 해당 필드를 호출할 수 있는 가장 
가까운 값을 호출한다. 이해가 안된다면 아래의 예제를 통해 사용법과 함께 알아보자
```java
package week6;

public class Pparent {
    int test= 10;
}
```
```java
package week6;

public class Parent extends Pparent{
    int test = 1;
}
```
```java
package week6;

public class Child extends Parent {

    public void test(){
        System.out.println(test);
        System.out.println(this.test);
        System.out.println(super.test);
    }
}

```

#### super()



### 메소드 오버라이딩
메소드 오버라이딩은 자바에서 다형성을 __런타임__ 시 지원하는 한가지 방법이다.
### 다이나믹 메소드 디스패치 (Dynamic Method Dispatch)
다이다믹 메소드 디스패치는 오버라이딩된 메소드를 처리할때 컴파일시 처리가 아닌 __런타임__ 시 처리하는 메커니즘이다.
부모클래스를 통해 오버라이딩 된 메소드가 호출 될 경우(자식 클래스를 부모클래스로 형 변환하여, 오버라이딩된 메소드를 호출 할 경우)
자바에서는 호출할 메소드 실행시, 호출 당시의 참조된(가르키는) 객체에 기반하여 해당 메소드를 결정 하게된다.
다시말해, __런타임__ 시에, 어떤 객체에 어떤 메소드를 실행 할지가 결정된다.
런타임시, 참조된 객체의 타입에 의존하며(레퍼런스 변수의 타입이 아니다. 어렵다면 아래 예제를 참고), 해당 객체는 부모클래스가 참조 __할 수있는__ 자식클래스의 실행될 오버라이딩된 메소드를 결정하게 된다.
This is also known as upcasting. Java uses this fact to resolve calls to overridden methods at run time.
이런 방식은 업케스팅이라고도 
### 추상 클래스
### final 키워드
### Object 클래스

마감일시
2020년 12월 26일 토요일 오후 1시까지.
