---
title : "자바스터디 12주차"
date : 2021-02-06
categories : [study]
---


# GOAL
> 자바의 애노테이션에 대해 학습하세요.

# 학습할 것 (필수)
- 애노테이션 정의하는 방법
- @retention
- @target
- @documented
- 애노테이션 프로세서


### 애노테이션 정의하는 방법

#### 어노테이션이란(annotation)?

자바 Annotation 은 메타 데이터 를 나타내는 태그입니다. 즉, 자바 컴파일러 및 JVM에서 사용할 수있는 몇 가지 추가 정보를 나타 내기 위해 클래스, 인터페이스, 메서드 또는 필드에 추가하여 사용합니다.
자바의 어노테이션은 추가 정보를 제공하는 데 사용되므로 XML 및 자바 마커 인터페이스등으로 대체 가능합니다.

#### 자바 내장 어노테이션

자바에는 몇가지 내장 객체가 있습니다. 일부 주석은 자바 코드에 쓰이고, 일부는 다른 용도로 사용됩니다.

자바 코드에 사용되는 내장 어노테이션
- @Override
- @SuppressWarnings
- @Deprecated

다른 용도로 사용되는 내장 어노테이션

- @Target
- @Retention
- @Inherited
- @Documented

### @Override

@Override 어노테이션은 자식클래드가 부모클래스의 메소드를 오버라이딩하고 있다고 단언할 수 있게 해줍니다.
때문에, 실제로 오버라이딩되지 않으면, 컴파일 에러를 발생합니다.

```java
public class Animal {
    void eatSomething(){System.out.println("eating something");}
}

public class Dog extends Animal{
    @Override
    void eatSomething() {
        System.out.println("eating foods");
    }
}

public class TestAnnotation {

    public static void main(String[] args) {
        Animal a = new Dog();
        a.eatSomething();
    }
}
```
> 바이트코드

오버라이드 어노테이션이 있는 Dog만 확인해 본다

```
public class week12.Dog extends week12.Animal {
  public week12.Dog();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method week12/Animal."<init>":()V
       4: return

  void eatSomething();
    Code:
       0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
       3: ldc           #3                  // String eating foods
       5: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
       8: return
}
```
어노테이션은 넣는다고 해서 바이트 코드에 영향이 가지 않는다.

### @SuppressWarnings

@SuppressWarnings 어노테이션은 컴파일러가 알려주는 경고를 무시할 수 있도록 해줍니다.

아래의 코드에서는 제네릭 타입을 사용하지 않았기 때문에 컴파일 타임에 경고를 표시해 줍니다.
```java
public class SuppresTest {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        ArrayList list = new ArrayList();

        list.add("a");
        list.add("b");
        list.add("c");

        for (Object o : list) {
            System.out.println(o);
        }
    }
}
```

아래의 사진과 같이 @SuppressWarnings 어노테이션을 주석처리하면 경고를 표시합니다.  
![SuppressWarnings1](https://github.com/redbean88/redbean88.github.io/blob/master/img/suppress01.png?raw=true)


아래의 사진과 같이 @SuppressWarnings 어노테이션을 추가사면 경고를 표시가 사라집니다.  
![SuppressWarnings2](https://github.com/redbean88/redbean88.github.io/blob/master/img/suppress02.png?raw=true)

### @Deprecated

@Deprecated 는 향후에 지원이 중단될 수도 있는 메서드에 대해 경고하는 역할을 합니다.
따라서 @Deprecated 어노테이션이 표시된 메서드는 사용하지 않는 것이 좋습니다.

```java
public class A {

    @Deprecated
    void m(){
        System.out.println("hello");
    }
}


public class DeprecateMain {

    public static void main(String[] args) {
        A a= new A();
        a.m();
    }
}
```

![이미지](https://github.com/redbean88/redbean88.github.io/blob/master/img/deprecate01.png?raw=true)

### 자바 커스텀 어노테이션

자바 커스텀 어노테이션은 사용자 쉽게 정의하여 사용할수 있는 어노테이션으로, @interface 예약어를 사용합니다.(interface 가 아닙니다.) 
커스텀 어노테이션을 만들때 주의할 사항은 아래와 같습니다.

- 메서드에 throws 절이 없어야 합니다.
- 메서드는 다음중 하나를 반환해야 합니다 : 기본 데이터 유형, 문자열, 클래스, 열거 형 또는 데이터 유형의 배열
- 메서드에는 매개 변수가 없어야합니다.
- 어노테이션을 정의하려면 interface 키워드 바로 앞에 @를 붙여야합니다.
- 메서드에 기본값을 할당 할 수 있습니다.

```java
public @interface MyAnno {

}
```

> 바이트코드

```
public interface week12.MyAnno extends java.lang.annotation.Annotation {
}
```

> 사용법

```java
public class TestAnnotation {
    @MyAnno()
    void test(){
    }
}
```

### 어노테이션 유형

어노테이션에는 3가지 유형이 있습니다.

![이미지](https://static.javatpoint.com/java/new/images/java-annotation-types.jpg)

- 마커 어노테이션(Marker Annotation)
- 단일 값 어노테이션(Single-Value Annotation)
- 다중 값 어노테이션(Multi-Value Annotation)

#### 마커 어노테이션

```java
public @interface MyAnno {}
```
메소드가 없는 어노테이션을 마커 어노테이션이라고 합니다.
위에 만든 커스텀 어노테이션도 마커 어노테이션이라 할 수 있으며, @Override 및 @Deprecated는 마커 어노테이션입니다.

#### 단일 값 어노테이션
```java
public @interface MyAnno {
    int value() default 0;
}
```

> 바이트코드

```
public interface week12.MyAnno extends java.lang.annotation.Annotation {
  public abstract int value();
}
```

> 사용법

```java
public class TestAnnotation {
    @MyAnno(value = 1)
    void test(){
    }
}
```

하나의 메서드가 있는 어노테이션을 단일 값 어노테이션이라고 합니다. 바이트코드 확인시 추상 메서드로 생성되는 것을 확인 할 수 있습니다.
단일 메서드 명이 value 인 경우에만 특수하게 아래와 같은 코드도 사용 가능합니다.

```java
public class TestAnnotation {
    @MyAnno(1)
    void test(){
    }
}
```

#### 다중 값 어노테이션

```java
public @interface MyAnno {
    int value() default 0;
    String type() default "animal";
}
```

> 바이트코드

```
public interface week12.MyAnno extends java.lang.annotation.Annotation {
  public abstract int value();

  public abstract java.lang.String type();
}
```

둘 이상의 메소드가 있는 어노테이션을 다중 값 어노테이션이라고 합니다.

> 사용법

```java
public class TestAnnotation {
    @MyAnno(value = 1,type = "human")
    void test(){
    }
}
```

#### 커스텀 에노테이션에 사용되는 내장 어노테이션

- @Retention
- @Target
- @Inherited
- @Documented


### @Retention

@Retention 어노테이션은 해당 주석을 언제 까지 사용 할 수있는지 범위를 정할 수 있습니다.

|Retention 정책 | 범위 |
|:--|:--|
|RetentionPolicy.SOURCE	| 컴파일처리시까지만 사용됩니다. 컴파일 된 클래스에서는 표시되지 않습니다.|
|RetentionPolicy.CLASS	| 자바 컴파일러에서까지 사용 가능하지만 JVM에서는 사용이 불가합니다. 클래스 파일에 포함되어 있습니다.|
|RetentionPolicy.RUNTIME | 자바 컴파일러 및 JVM에서 사용할 수있는 있습니다.|

```java
@Retention(RetentionPolicy.RUNTIME)  
@interface MyAnnotation{  
int value1();  
String value2();  
}  
```

### @target

@Target 태그는 어노테이션이 사용되는 유형을 지정하는 데 사용됩니다.

java.lang.annotation.ElementType은 열거형으로 TYPE, METHOD, FIELD 등과 같이 어노테이션이 적용될 요소의 유형을 지정하기 위해 많은 상수를 지원합니다.

|요소유형|위치|
|:--|:--|
|TYPE	|클래스, 인터페이스 혹은 열거형
|FIELD	|필드
|METHOD	|메소드
|CONSTRUCTOR	| 생성자
|LOCAL_VARIABLE	| 지역 변수
|ANNOTATION_TYPE |	어노테이션타입
|PARAMETER |	파라미터

```java
@Target(ElementType.TYPE)
public @interface MyAnno {
    int value() default 0;
    String type() default "animal";
}
```
### @Inherited

어노테이션은 상속이 불가능 하지만, @Inherited 이 붙은 어노테이션에 한하여 상속을 가능하도록 해줍니다.

```java
@UninheritedAnnotationType
public class A {
}

@InheritedAnnotationType
public class B extends A{
}


public class C extends B{
}

public class TestAnnotation {
    public static void main(String[] args) {

        System.out.println(new A().getClass().getAnnotation(InheritedAnnotationType.class));
        System.out.println(new B().getClass().getAnnotation(InheritedAnnotationType.class));
        System.out.println(new C().getClass().getAnnotation(InheritedAnnotationType.class));
        System.out.println("_________________________________");
        System.out.println(new A().getClass().getAnnotation(UninheritedAnnotationType.class));
        System.out.println(new B().getClass().getAnnotation(UninheritedAnnotationType.class));
        System.out.println(new C().getClass().getAnnotation(UninheritedAnnotationType.class));

    }
}
```

> 결과

```
null
@InheritedAnnotationType()
@InheritedAnnotationType()
_________________________________
@UninheritedAnnotationType()
null
null
```

결과를 보면 @InheritedAnnotationType 어노테이션을 선언하면 해당 어노테이션은 상속받은 어노테이션을 붙이지 않은(위에서 클래스 C)도 해당 
어노테이션을 불러오는 것을 확인 할 수 있습니다.

### @documented

@Documented 어노테이션이 지정된 대상을 JavaDoc에 표시하기 위해 사용합니다. @Documented 어노테이션을 이용하여 API 문서를 만들 수 있습니다. 

![이미지](https://github.com/redbean88/redbean88.github.io/blob/master/img/javadoc01.png?raw=true)

JavaDoc 파일 생성은 아래 블로그를 참고하세요
[JavaDoc](https://shinheechul.tistory.com/43)

### 애노테이션 프로세서

이름에서 유추할 수 있듯이 애노테이션을 이용해서 프로세스를 처리하는것을 의미합니다.
특히 애노테이션 프로세서의 특징은 __컴파일__ 단계에서 애노테이션에 정의된 액션을 처리하는데, 이는 우리의 애플리케이션이 실행되기 전 체킹을 해주기 때문에 애노테이션으로 의도한대로 이루어지지 않을 경우 에러나 경고를 보여주기도 하며, 소스코드인 .java와 바이트코드인 .class를 생성해주기도 합니다.  
_[출처](https://better-dev.netlify.app/java/2020/09/07/thejava_16/)_

우리가 사용하는 spring의 컨트롤러나 lombok 도 애노테이션 프로세서라 할 수 있다.

아래의 블로그도 참조하세요 
[Annotation Processor란?](https://mysend12.medium.com/java-annotation-processor-1-7f95693748ef)



참조  
[javatpoint.com/java-annotation](https://www.javatpoint.com/java-annotation)  
[stackoverflow.com/questions/23973107/how-to-use-inherited-annotation-in-java](https://stackoverflow.com/questions/23973107/how-to-use-inherited-annotation-in-java)