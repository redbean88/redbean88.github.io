---
title : "자바스터디 5주차"
date : 2020-12-15
categories : study
---

# GOAL
> 자바의 Class에 대해 학습하세요.

### 학습할 것 (필수)
1. 클래스 정의하는 방법

클래스 생성시 생성자 메서드가 기본적으로 호출된다.
따라서, 아래 두 코드는 같은 형식으로 컴파일된다
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
> 컴파일 코드
```
public class Test {
  public Test();
    Code:
       0: aload_0                           // 로컬변수0번(array)에서 배열 호출
       1: invokespecial #8                  // 인스턴스 생성을 위한 메소드 호출 Method java/lang/Object."<init>":()V
       4: return
}
```
invokespecial은 특정 상황이 발생 할때, 예외를 발생 하는데 그 예외는 아래와 같다
+ Linking Exceptions
    * During resolution of the symbolic reference to the method, any of the exceptions pertaining to method resolution (§5.4.3.3) can be thrown.
    * Otherwise, if the resolved method is an instance initialization method, and the class in which it is declared is not the class symbolically referenced by the instruction, a NoSuchMethodError is thrown.
    * Otherwise, if the resolved method is a class (static) method, the invokespecial instruction throws an IncompatibleClassChangeError.
+ Run-time Exception
    * Otherwise, if objectref is null, the invokespecial instruction throws a NullPointerException.
    * Otherwise, if no method matching the resolved name and descriptor is selected, invokespecial throws an AbstractMethodError.
    * Otherwise, if the selected method is abstract, invokespecial throws an AbstractMethodError.
    * Otherwise, if the selected method is native and the code that implements the method cannot be bound, invokespecial throws an UnsatisfiedLinkError.


1. 객체 만드는 방법 (new 키워드 이해하기)
1. 메소드 정의하는 방법
1. 생성자 정의하는 방법
1. this 키워드 이해하기

마감일시
2020년 12월 19일 토요일 오후 1시까지.

과제 (Optional)
int 값을 가지고 있는 이진 트리를 나타내는 Node 라는 클래스를 정의하세요.
int value, Node left, right를 가지고 있어야 합니다.
BinrayTree라는 클래스를 정의하고 주어진 노드를 기준으로 출력하는 bfs(Node node)와 dfs(Node node) 메소드를 구현하세요.
DFS는 왼쪽, 루트, 오른쪽 순으로 순회하세요.
