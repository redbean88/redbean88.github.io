---
title : "자바스터디 14주차"
date : 2021-02-25
categories : study
---

# GOAL
자바의 제네릭에 대해 학습하세요.

# 학습할 것 (필수)

- 제네릭 사용법
- 제네릭 주요 개념 (바운디드 타입, 와일드 카드)
- 제네릭 메소드 만들기
- Erasure


### 제네릭 사용법

#### 제네릭

소프트웨어 프로젝트에서 버그 발생은 당연한 사실입니다. 신중한 계획, 프로그래밍 및 테스트는 버그 발생률을 줄이는 데 도움이 될 수 있지만 코드 어딘가에는 버그가 존재할 수 있습니다. 이것은 새로운 기능이 도입되고 코드 기반이 크기와 복잡성이 증가함에 따라 특히 그 빈도가 능가합니다.

다행히도 일부 버그는 다른 버그보다 감지하기 쉽습니다. 예를 들어 컴파일 타임 버그는 초기에 발견 할 수 있습니다. 컴파일러의 오류 메시지를 사용하여 문제가 무엇인지 파악하고 즉시 수정할 수 있습니다. 그러나 런타임 버그는 훨씬 더 문제가 될 수 있습니다. 런타입 버그는 바로 발견하기 어려우며, 또한 문제의 실제 원인을 발견하기 어려울 수도 있습니다.

제네릭은 이러한 에러를 런타임이 아닌 __컴파일__ 시 검증할 수 있도록 해주며 그로인한 안정성을 추구할 수 있습니다.(JDK 1.5버전 부터 사용 가능합니다.)

#### 사용하는 이유

제네릭은 클래스, 인터페이스 및 메서드를 정의 할 때 (클래스 및 인터페이스의)매개 변수의 타입을 정의 할수 있습니다. 일반적으로 메서드에 사용하는 파라미터 형식으로 매개변수 타입을 정의 할 수 있습니다.

제네릭을 사용하는 코드는 제네릭이 아닌 코드에 비해 많은 이점이 있습니다.

- 컴파일 타임시, 강력한 타입 체크  
 Java 컴파일러는 강력한 타입 검사를 수행하며 코드가 타입 안전성(type-safety)을 위반하면 오류를 발생시킵니다. 컴파일 타임 오류를 수정하는 것은 찾기 어려울 수있는 런타임 오류를 수정하는 것보다 쉽습니다.

- 형변환 제거  
제네릭을 사용하지 않는 다음코드는 형변환이 필요합니다
```java
List list = new ArrayList();
list.add("hello");
String s = (String) list.get(0);
```
하지만 제네릭을 사용시 형변환이 필요 없습니다.
```java
List<String> list = new ArrayList<String>();
list.add("hello");
String s = list.get(0);   // 형 변환 필요 없음
```
- 프로그래머가 일반 알고리즘을 구현할 수 있도록합니다.
프로그래머는 제네릭을 사용하여 다양한 유형의 컬렉션에서 작동하고 사용자 정의 할 수 있으며 타입에 안전(type-safety)하고 읽기 쉬운 제네릭 알고리즘을 구현할 수 있습니다.

#### 선언
class Box\<T1,T2,...\>

|명칭|내용|
|:--|:--|
|T1,T2,...|타입 변수 또는 타입 매개변수|
|Box|원시타입|

#### 비교

위의 선언 방식을 이용하여 제네릭이 적용된 클래스와 비적용된 클래스를 비교해 봅니다.

> 제네릭 비적용

```java
public class NoGenericBox {
    private Object object;

    public void set(Object object) { this.object = object; }
    public Object get() { return object; }
}
```

> 바이트코드

```
public class week14.NoGenericBox {
  public week14.NoGenericBox();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public void set(java.lang.Object);
    Code:
       0: aload_0
       1: aload_1
       2: putfield      #2                  // Field object:Ljava/lang/Object;
       5: return

  public java.lang.Object get();
    Code:
       0: aload_0
       1: getfield      #2                  // Field object:Ljava/lang/Object;
       4: areturn
}
```

>제네릭 적용

```java
public class GenericBox<T> {
    private T t;

    public void set(T t) { this.t = t; }
    public T get() { return t; }
}
```

> 바이트코드

```
public class week14.GenericBox<T> {
  public week14.GenericBox();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public void set(T);
    Code:
       0: aload_0
       1: aload_1
       2: putfield      #2                  // Field t:Ljava/lang/Object;
       5: return

  public T get();
    Code:
       0: aload_0
       1: getfield      #2                  // Field t:Ljava/lang/Object;
       4: areturn
}

```

위 코드 보면 제네릭을 사용시 putfield 함수를 이용하고 있습니다. putfield 함수는 참조된 필드의 타입을 확인하며,  해당 타입의 필드가 static일 경우, IncompatibleClassChangeError에러를 발생 시킵니다. 또한 final일 경우, 초기화를 반드시 진행 해야하며, 그렇지 않으면 IllegalAccessError에러를 발생 시킵니다.




### 제네릭 주요 개념 (바운디드 타입, 와일드 카드)
### 제네릭 메소드 만들기
### Erasure

# 출처
https://docs.oracle.com/javase/tutorial/essential/io/streams.html  