---
title : "자바스터디 5주차"
date : 2020-12-15
categories : study
---

# GOAL
> 자바의 Class에 대해 학습하세요.

# 학습할 것 (필수)
### 클래스 정의하는 방법
> 클래스란
  
클래스(class)는 객체 지향 프로그래밍(OOP)에서 특정 객체를 생성하기 위해 변수와 메소드를 정의하는 일종의 틀이다. 객체를 정의 하기 위한 상태(멤버변수)와 메서드(함수)로 구성된다  - 위키백과

고전적인 붕어빵과 붕어빵틀에서 틀의 역할을 담당한다. 틀만 있으면 같은 모양을 만들수 있는것 처럼 하나의 클래스가 있으면, 같은 기능의 객체(인스턴스)를 생성 할 수 있다. 

##### 생성자

클래스는 생성자 메서드가 기본적으로 호출된다. 때문에 생성자를 실행시키지 않고 객체를 __만들 수 없다.__
또한, 생성자는 __리턴 타입이 없다.__
아래 세가지 코드는 같은 형식으로 컴파일된다

```java
public class Test {
}
```
```java
public class Test {
    public Test() {
    }
}
```
```java
public class Test {
	{}
}
```

> 컴파일 코드

```
public class Test {
  public Test();
    Code:
       0: aload_0                           // 로컬변수배열(array)의 0번째 레퍼런스 값을 호출
       1: invokespecial #8                  // 인스턴스 생성을 위한 메소드 호출 Method java/lang/Object."<init>":()V
       4: return
}
```
invokespecial은 특정 상황에서, 예외를 발생 하는데 그 예외는 아래와 같다
+ Linking Exceptions
    * During resolution of the symbolic reference to the method, any of the exceptions pertaining to method resolution (§5.4.3.3) can be thrown.
    * Otherwise, if the resolved method is an instance initialization method, and the class in which it is declared is not the class symbolically referenced by the instruction, a NoSuchMethodError is thrown.
    * Otherwise, if the resolved method is a class (static) method, the invokespecial instruction throws an IncompatibleClassChangeError.
+ Run-time Exception
    * Otherwise, if objectref is null, the invokespecial instruction throws a NullPointerException.
    * Otherwise, if no method matching the resolved name and descriptor is selected, invokespecial throws an AbstractMethodError.
    * Otherwise, if the selected method is abstract, invokespecial throws an AbstractMethodError.
    * Otherwise, if the selected method is native and the code that implements the method cannot be bound, invokespecial throws an UnsatisfiedLinkError.

_innner class를 사용할 경우, 컴파일시 해당 클래스가 추가로 생성된다_  
아래와 같은 코드는 Test.class, Test2.class를 생성해낸다.

```java
public class Test {
}

class test2{

}
```

_싱글톤이나 빌더패턴에서 사용하는 생성자를 private으로 만들면 어떻게 될까?_
코드가 생성되지 않는다. 메모리에 올라가지 않기 때문에 쓸수 없는 것 같다.
```
public class Test {
    private Test(){
        int a= 0;
    }
}
```

> 컴파일 코드

```
public class Test {
}
```

##### 변수(필드)

변수는 중광호블록을 기준으로 전역변수와 지역변수로 나눌 수 있다
~~전역변수를 많이쓰면 팀원을 괴롭 힐 수 있다~~

```java
public class Test {
    int global = 0;         // 전역 변수
    Test(){                 // 블록
        int local = 1;      // 지역변수
    }                       // 블록
}
```

##### 메소드

실제 동작을 구현하며, 중괄호 {} 블록을 사용한다.

```java
public class Test {
    int method(int arg){
        return arg;
    }
}
``` 

> 컴파일 코드

```
public class Test {
  public Test();
    Code:
       0: aload_0                       // 로컬변수배열(array)의 0번째 레퍼런스 값을 호출
       1: invokespecial #1              // Method java/lang/Object."<init>":()V
       4: return

  int method(int);
    Code:
       0: iload_1                       // 로컬변수배열(array)의 1번째 int 값을 호출
       1: ireturn                       // int를 반환
}
```

### 객체 만드는 방법 (new 키워드 이해하기)

객체생성(인스턴스 생성)은 new 키워드를 통해 생성한다.
우리가 마주치는 Exception 들은 new 키워드에서 발생한다.

```java
public class Test {

    void newKeyword(){
        new Test();
    }

}
```

> 컴파일 코드

```
public class Test {
  public Test();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  void newKeyword();
    Code:
       0: new           #2                  // 객체를 생성한다
       3: dup                               
       4: invokespecial #3                  // Method "<init>":()V
       7: pop
       8: return
}
```

 new는 특정 상황에서, 예외를 발생 하는데 그 예외는 아래와 같다
+ Linking Exceptions
    + 레퍼런스(class, array, interface type)를 해석하는 과정에서 오류를 발생, 
    + 또는, 레퍼런스(class, array, interface type)가 interface 혹은 추상 클래스인 경우 InstantiationError 발생  
+ Run-time Exception
    + 클래스 초기화에 실패 했을 경우

### 메소드 정의하는 방법

메소드는 접근제어자, 리턴타입, 메소드명, 매개변수로 구성되며, 예외 발생시 호출한 메소드로 책임을 전가하는 throws를 이용 할 수 있다.  
~~왠만하면 자기의 일은 스스로 하도록 만들자~~

|이름|설명|
|:--|--|
|private|해당 클래스 내부에서만 호출 가능| 
|default|같은 패키지내의 클래스에서만 호출 가능|
|protected|같은 패키지내의 클래스나 상속받은 클래스에서만 호출 가능|
|public|어디서나 사용가능|


##### 오버로딩

같은 이름의 메소드를 매개변수의 수를 변경하여 정의 할 수 있는 기능

```java
public class Test {

    void newKeyword(){

    }

    void newKeyword(int i){

    }
}
```

> 컴파일 코드

```
public class Test {
  public Test();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  void newKeyword();
    Code:
       0: return

  void newKeyword(int);
    Code:
       0: return
}
```

같은이름의 파라미터만 다른 메소드가 생성 되었다

##### 오버라이딩

### 생성자 정의하는 방법
### this 키워드 이해하기

마감일시
2020년 12월 19일 토요일 오후 1시까지.

과제 (Optional)
int 값을 가지고 있는 이진 트리를 나타내는 Node 라는 클래스를 정의하세요.
int value, Node left, right를 가지고 있어야 합니다.
BinrayTree라는 클래스를 정의하고 주어진 노드를 기준으로 출력하는 bfs(Node node)와 dfs(Node node) 메소드를 구현하세요.
DFS는 왼쪽, 루트, 오른쪽 순으로 순회하세요.
