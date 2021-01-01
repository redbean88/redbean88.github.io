---
title : "자바스터디 7주차"
date : 2020-12-28
categories : study
--- 

# GOAL
> 자바의 패키지에 대해 학습하세요.

# 학습할 것 (필수)
+ package 키워드
+ import 키워드
+ 클래스패스
+ CLASSPATH 환경변수
+ -classpath 옵션
+ 접근지시자

---


### package 키워드
> package

자바에서 유사한 개념을 그룹화 하는 개념으로, __내장 패키지__ 와 __사용자정의 패키지__ 가 있다.   
__내장 패키지__ 는 자바에서 기본적으로 제공<sup>[1](#footnote_1)</sup>하는 클래스나 인터페이스 등이 속해있는 패키지이며, __사용자 정의 패키지__ 는 사용자가 추가로 생성한 패키지 이다.

> 왜 쓰나요?

1. 클래스, 인터페이스 등을 분리 하여, __유지보수__ 를 쉽게 할 수 있다
2. 접근제어자를 통한 __접근 분리__ 를 할 수 있다.
3. __같은 이름__ 의 클래스, 인터페이스를 사용 가능하다.

### import 키워드

자바에서 다른 패키지를 사용시, 사용되는 키워드로 __같은 패키지__ 의 클래스를 호출 하거나, __java.lang__ 패키지 내부의 클래스를 사용시에는   
해당 키워드를 __사용하지 않아도__ 된다.

> import 사용 방법

자바에서는 3가지의 사용 방법을 제공 하고 있다.
1. import 패키지명.*
2. import 패키지명.클래스명
3. 풀 패키지 경로(fully qualified name)

#### 1. import 패키지명.*
패키지명.* 사용시 해당 패키지안의 모든 클래스나 인터페이스 등을 사용가능가지만, __하위 패키지__ 의 내용은 사용이 불가능 하다.

![파일경로](https://redbean88.github.io/img/importall.png)

_예제_

```java
import test.*;

public class Test {
    public static void main(String[] args) {
    TestA testA = new TestA();
    }
}
```

> 컴파일 소스

```
public class Test {
  public Test();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: new           #7                  // class test/TestA
       3: dup
       4: invokespecial #9                  // Method test/TestA."<init>":()V
       7: astore_1
       8: return
}
```

패키지에 대한 정보는 바이트코드에 반영되지 않는다.

#### 2. import 패키지명.클래스명
패키지명.클래스명 사용시, 사용할 클래스나 인터페이스를 특정하여 사용 가능하다.

![파일경로](https://redbean88.github.io/img/import2.png)

_예제_

```java
import test.TestA;

public class Test {
    public static void main(String[] args) {
    TestA test = new TestA();
    }
}
```

> 컴파일 소스

```
public class Test {
  public Test();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: new           #7                  // class test/TestA
       3: dup
       4: invokespecial #9                  // Method test/TestA."<init>":()V
       7: astore_1
       8: return
}
```

패키지에 대한 정보는 바이트코드에 반영되지 않는다.


#### 3. 풀 패키지 경로(fully qualified name)
import를 사용하지 않고, 사용하는 방식이다. 변수 생성시, 클래스 명이 아닌 해당 클래스의 전체 경로를 입력하여 사용한다. 같은 이름의 클래스를 사용하는 변수를 사용시 이용한다.

![파일경로](https://redbean88.github.io/img/import3.png)

```java
public class Test {
    public static void main(String[] args) {
        test.TestA test = new test.TestA();
    }
}
```

> 컴파일 소스

```
public class Test {
  public Test();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: new           #7                  // class test/TestA
       3: dup
       4: invokespecial #9                  // Method test/TestA."<init>":()V
       7: astore_1
       8: return
}
```

패키지에 대한 정보는 바이트코드에 반영되지 않는다.

### PATH VS CLASSPATH
자바에서 Path와 ClassPath를 환경 설정에 이용한다.  

|차이점|Path|classPath|
|--|:--|:--|
|정의 |자바 프로그램을 실행하고 자바 소스 파일을 컴파일하는 데 사용되는 " java "또는 " javac "명령 과 같은 JDK 바이너리를 찾는 데 사용되는 환경 변수|System 또는 Application ClassLoader 에서 .class 파일에 저장된 Java 바이트 코드를 찾아서로드 하는 데 사용되는 환경 변수|
|설정내용|JDK_HOME / bin 디렉토리 | .class 파일 또는 JAR 파일을 넣은 모든 디렉토리|
|변경가능여부|불가능|"java "및 " javac "명령 모두에 명령 줄 옵션 -classpath 또는 -cp 를 제공 하거나 Class-를 사용하여 CLASSPATH를 재정의 가능|
|사용| 운영체제에서 바이너리타입이나 클래스 타입에 이용된다| 오직 자바의 classLoader에서만 사용된다|

### 클래스패스
자바 컴파일러는 실행시켜야 하는 클래스 파일의 위치를 알아야 하는데 그 클래스 파일의 위치를 __클래스패스__ 를 라고 부른다.
위에 설명한 내용과 같이, 클래스 패스는 클래스 로더에서 사용하는 환경변수 이다.

### CLASSPATH 환경변수
CLASSPATH 명령어를 사용해서 설정이 가능하다.

윈도우   

![윈도우](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcgTFrB%2FbtqRqb5kigF%2FTIDMKzmCWECK2NDscHxQH0%2Fimg.png)   
https://blog.opid.kr/62 


맥   

```
vi /etc/profile
export CLASSPATH=.:$JAVA_HOME/lib/tools.jar
```


### -classpath 옵션
CLI로 쉘등에서 javac를 이용하여 사용 가능하다.

![파일경로](https://redbean88.github.io/img/fileroot1.png)
_Test폴더와 같은 위치에 있을때

윈도우
```
java -cp ".;Test" Test
```

맥
```
java -cp ".:Test" Test
```

> 어떻게 제 소스가 실행되는거죠?

classLoader는 classpath를 기준으로 파일 위치를 확인 하여 실행한다. 여기서 중요한 점은 classPath를 분석할때 java파일의 최상단에 선언된 __package 키워드__ 를 기반으로 해당 클래스를 찾아내는 것이다.


### 지시자
자바에는 두가지 종류의 지시자를 제공하고 있으며, __접근 지시자__ 와 __비 접근 지시자__ 가 있다.

### 비 접근 지시자
종류가 많아 추후 추가예정

### 접근지시자
필드, 메소드, 생성자 또는 클래스의 접근 가능여부 또는 범위를 지정하며, 접근 지시자를 적용하여 필드, 생성자, 메서드 및 클래스의 접근 가능 범위을 변경할 수 있다   
총 4가지 접근지시자가 있다.

![참고이미지](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FKb2tD%2FbtqRrPm3DAn%2F9Q28T3eXG4n3kukPuMiup0%2Fimg.png)   
_참고이미지(출처[https://kils-log-of-develop.tistory.com/430])_

> 왜 쓰나요

모르는게 약이다라는 말이 있다. OOP 세상에서 객체는 살이있는 능동적인 존재다. 사람이 모든 일을 할 수 없듯이, 객체가 모든 것을 __알지 못 하게__ 하는 것도 중요하다.   
생각해 보자 대통령의 고조 할아버지 생애를 알아야 한다는 법률이 제정된다면, 누가 좋아 할 것인가? OOP에서 프로그래머의 명령은 법이나 마찬가지다.   
어쩌면 우리 컴파일 에러를 볼때마다, 클래스 입장에서는 처벌을 받는 것일 지도 모른다.

_예제_

### 참고

[javatpoint](https://www.javatpoint.com/)
[클래스패스](https://effectivesquid.tistory.com/entry/%EC%9E%90%EB%B0%94-%ED%81%B4%EB%9E%98%EC%8A%A4%ED%8C%A8%EC%8A%A4classpath%EB%9E%80)
[what-is-path-and-classpath-in-java-difference](https://www.java67.com/2012/08/what-is-path-and-classpath-in-java-difference.html)
[생활코딩-클래스패스](https://opentutorials.org/course/1223/5527)

---

<a name="footnote_1">1</a>: java, lang, awt, javax, swing, net, io, util, sql 
