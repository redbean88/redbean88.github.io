---
title : 자바스터디 15주차
date : 2021-03-05 00:00:00 +0000
categories: [study]
---

# GOAL
자바의 람다식에 대해 학습하세요.

# 학습할 것 (필수)
- 람다식 사용법
- 함수형 인터페이스
- Variable Capture
- 메소드, 생성자 레퍼런스

### 람다식 사용법

자바가 1996년에 처음 등장한 이후로 두 번의 큰 변화가 있었는데, 한번은 JDK 1.5부터 추가된 지네릭스(generics)의 등장이고, 또 한 번은 JDK 1.8부터 추가된 람다식(lambda expression)의 등장이다.
특히 람다식의 도입으로 인해, 자바는 객체지향언어인 동시에 함수형 언어가 되었다.
_자바의 정석 3판_

## 람다식이란?

오라클 공식 문서에서는 다음과 같이 표현하고 있다.
_메서드가 하나 뿐인 클래스의 경우 익명 클래스도 약간 과도하고 번거로운 것처럼 보입니다. Lambda 표현식을 사용하면 단일 메서드 클래스의 인스턴스를보다 간결하게 표현할 수 있습니다._ 익명클래스는 사용하기 쉽지만 잘못하면 복잡해 질수 있기 때문에, 더 __단순화__ 하는 것이 람다의 주요 목적입니다. 때문에 람다식을 __'익명 함수(anonymous function)'__ 라고도 합니다. 사실상 익명함수의 단축화된 표현이기 때문이다.

## 람다식 작성

사용자의 성별을 반환하는 인트페이스를 정의한다.
```java
public interface Func {
    String isMale(String gender);
}
```

일반적으로 익명함수는 다음과 같이 사용한다.
```java
public class Test {
    public static void main(String[] args) {
        Func tes = new Func() {
            @Override
            public String isMale(String gender) {
                return gender;
            }
        };
    }
}
```

> 바이트코드

```
public class week15.Test {
  public week15.Test();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: new           #7                  // class week15/Test$1
       3: dup
       4: invokespecial #9                  // Method week15/Test$1."<init>":()V
       7: astore_1
       8: return
}
```

이 메서드를 람다식으로 변환하면 다음과 같이 할 수 있다.

```java
  public class Test {
    public static void main(String[] args) {
        Func tes = gender -> gender;
    }
  }
```

> 바이트코드

```
public class week15.Test {
  public week15.Test();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: invokedynamic #7,  0              // InvokeDynamic #0:isMale:()Lweek15/Func;
       5: astore_1
       6: return
}
```

코드가 간결해 지며, 내부 바이트 코드에도 변화가 발생하는것을 볼수 있다.
다음은 어떻게 코드가 줄어지는지 순서대로 확인해 보자.

람다식은 <b>'익명 함수'</b>답게 메서드에서 이름과 반환타입을 제거하고 매개변수 선언부와 몸통 { } 사이에 `->`를 추가한다.

<table style="width:100%; background-color:#3a3c42; border:0; margin-bottom:16px;">
  <tr style="border:0">
    <td style="border:0; padding:14px; padding-left:32px; padding-right:32px; font-size:14px; color:white">
      반환타입 메서드이름(매개변수 선언) {<br/>
      문장들<br/>
      }<br/>
      &#8681;<br/>
      <del>반환타입 메서드이름</del> (매개변수 선언) -> {<br/>
      문장들<br/>
      }<br/>
    </td>
  </tr>   
</table>

<br/>

```java

public class Test {
    public static void main(String[] args) {
        Func tes = (String gender) -> {
                return gender;
        };
    }
}
```

반환값이 있는 메서드의 경우, return문 대신 <b>'식(expression)'</b>으로 대신 할 수 있다. 식의 연산결과가 자동적으로 반환값이 되고 이 때는 <b>'문장(statement)'</b>이 아닌 식이므로 끝에 `;`를 붙이지 않는다.

```java
public class Test {
    public static void main(String[] args) {
        Func tes = (String gender) -> 
                gender;
    }
}

```

람다식에 선언된 매개변수의 타입은 추론이 가능한 경우에 생략할 수 있다. 대부분의 경우 생략이 가능한데 람다식에 반환타입이 없는 이유도 항상 추론이 가능하기 때문이다. 한 가지 주의해야 할 점은 매개변수가 두개 이상일 경우 어느 하나의 타입만 생략하는 것은 허용되지 않는다는 점이다.

```java
  public class Test {
    public static void main(String[] args) {
        Func tes = (gender) -> gender;
    }
  }
```

선언된 매개변수가 하나뿐인 경우에는 괄호( )를 생략할 수 있다. 단, 매개변수의 타입이 있으면 생략할 수 없다.

```java
  public class Test {
    public static void main(String[] args) {
        Func tes = gender -> gender;
    }
  }
```

추가적으로 괄호{ } 안의 문장이 하나일 때는 역시 괄호{ }를 생략할 수 있다. 이 때 문장의 끝에 `;`를 붙이지 않아야 한다는 것을 기억해야 한다.

```java
    (String name, int i) -> {
        System.out.println(name + " = " + i);
    }

    (String name, int i) ->
        System.out.println(name + " = " + i)
```

그러나 괄호{ } 안의 문장이 return문일 경우 생략할 수 없다. 또한 return문과 식의 차이를 기억해야 한다.

```java
    (int a, int b) -> { return a > b ? a : b; } // return문 Ok.
    (int a, int b) ->   return a > b ? a : b    // return문 error.
    (int a, int b) ->   a > b ? a : b           // 식(expression)
```

# 함수형 인터페이스

자바에서 모든 메서드는 클래스 내에 포함되어야 하는데, 람다식은 어떤 클래스에 포함되는 것일까요? 람다식이 메서드와 동등한 것이라고 생각했지만, 사실 람다식은 익명 클래스의 객체와 동등하다.

그렇다면 람다식으로 정의된 익명 객체의 메서드를 어떻게 호출할 수 있을까요? 참조변수가 있어야 객체의 메서드를 호출할 수 있으니 익명 객체의 주소를 f라는 참조변수에 저장해 보자.

```java
    타입 f = (int a, int b) -> a > b ? a : b;
```

자, 이제 참조변수 f의 타입은 어떤 것이어야 할까? 참조형이니 클래스 또는 인터페이스가 가능하다. 그리고 람다식과 동등한 메서드가 정의되어 있는 것이어야 한다. 그래야 참조변수로 익명 객체(람다식)의 메서드를 호출할 수 있기 때문이다.

예를 들어 max()라는 메서드가 정의된 MyFunction 인터페이스가 아래와 같이 정의되어 있다.

```java
    interface MyFunction {
        public abstract int max(int a, int b);
    }
```

그러면 이 인터페이스를 구현한 익명 클래스의 객체는 다음과 같이 생성할 수 있다.

```java
    MyFunction f = new MyFunction() {
                          public int max(int a, int b) {
                              return a > b ? a : b;
                          }
                  };

    int big = f.max(5, 3);
```

MyFunction 인터페이스에 정의된 메서드 max()는 람다식 `(int a, int b) -> a > b ? a : b`과 메서드의 선언부가 일치한다. 따라서 익명 객체를 람다식으로 대체할 수 있다.

```java
    MyFunction f = (int a, int b) -> a > b ? a : b;
    int big = f.max(5, 3);
```

이처럼 MyFunction 인터페이스를 구현한 익명 객체를 람다식으로 대체 가능한 이유는, 람다식도 실제로는 익명 객체이고, MyFunction 인터페이스를 구현한 익명 객체의 메서드 max()와 람다식의 매개변수의 타입과 개수 그리고 반환값이 일치하기 때문이다.

하나의 메서드가 선언된 인터페이스를 정의하여 람다식을 다루면 기존의 자바 규칙을 어기지 않으면서 자연스럽게 다룰 수 있다. 그래서 인터페이스를 통해 람다식을 다루기로 결정했고, 람다식을 다루기 위한 인터페이스를 <b>'함수형 인터페이스(functional interface)'</b>라고 부른다.

```java
    @FunctionalInterface
    interface MyFunction {
        public abstract int max(int a, int b);
    }
```

단, 함수형 인터페이스에는 오직 하나의 추상 메서드만 정의되어 있어야 한다는 제약이 있다. 애노테이션 `@FunctionalInterface`를 사용하면 컴파일러가 올바르게 정의되어 있는지 확인해주므로 꼭 사용하도록 하자.

## 함수형 인터페이스 타입의 매개변수와 반환타입

함수형 인터페이스 MyFunction이 아래와 같이 정의되어 있다.

```java
    @FunctionalInterface
    interface MyFunction {
        void myMethod();    // 추상 메서드
    }
```

메서드의 매개변수가 MyFunction타입이면, 이 메서드를 호출할 때 람다식을 참조하는 참조변수를 매개변수로 지정해야 한다.

```java
    void aMethod(MyFunction f) {  
        f.myMethod();             // MyFunction에 정의된 메서드 호출
    }
        ...
    MyFunction f = () -> System.out.println("myMethod()");
    aMethod(f);
```

또는 참조변수 없이 아래와 같이 직접 람다식을 매개변수로 지정하는 것도 가능하다.

```java
    aMethod(() -> System.out.println("myMethod()"));
```

메서드의 반환타입이 함수형 인터페이스 타입이라면, 이 함수형 인터페이스의 추상메서드와 동등한 람다식을 가리키는 참조변수를 반환하거나 람다식을 직접 반환할 수 있다.

```java
    MyFunction myMethod() {
        MyFunction f = () -> {};
        return f;
        // return () -> {};
    }
```

람다식을 참조변수로 다룰 수 있다는 것은 메서드를 통해 람다식을 주고받을 수 있다는 것을 의미한다. 즉, 변수처럼 메서드를 주고받는 것이 가능해진 것이다.

<b>&#9654; 예제</b>

&#9654; MyFunction01.java

```java
package lambda;

public interface MyFunction01 {
    void run();
}
```

&9654; Ex01.java

```java
package lambda;

public class Ex01 {

    static void execute(MyFunction01 f) {
        f.run();
    }

    static MyFunction01 getMyFuction() {
        MyFunction01 f = () -> System.out.println("f3.run()");
        return f;
    }

    public static void main(String[] args) {
        MyFunction01 f1 = () -> System.out.println("f1.run()");

        MyFunction01 f2 = new MyFunction() {
            @Override
            public void run() {
                System.out.println("f2.run()");
            }
        };

        MyFunction01 f3 = getMyFuction();

        f1.run();
        f2.run();
        f3.run();

        execute(f1);
        execute( () -> System.out.println("run()") );
    }
}
```

<img src="/assets/img/study/week1501.png" width="70%" align="center"><br/>

## 람다식의 타입과 형변환

함수형 인터페이스로 람다식을 참조할 수 있는 것일 뿐이지 람다식의 타입이 함수형 인터페이스의 타입과 일치하는 것은 아니다. 람다식은 익명 객체이고 익명 객체는 타입이 없다.

정확히는 타입은 있지만 컴파일러가 임의로 이름을 정하기 때문에 알 수 없는 것이다. 그래서 대입 연산자의 양변의 타입을 일치시키기 위해 형변환이 필요하다.

```java
    MyFunction f = (MyFunction)( () -> { } );
```

람다식은 MyFunction 인터페이스를 직접 구현하지 않았지만, 이 인터페이스를 구현한 클래스의 객체와 완전히 동일하기 때문에 위와 같은 형변환을 허용한다. 그리고 이 형변환은 생략가능하다.

람다식은 이름이 없을 뿐 분명히 객체인데도, Object타입으로 형변환 할 수 없다. 람다식은 오직 함수형 인터페이스로만 형변환이 가능하다.

```java
    Object obj = (Object)( () -> { } ); // error. 함수형 인터페이스로만 가능
```

굳이 변경하고자 한다면, 함수형 인터페이스로 변환하고 난 후에 가능하다.

다음 예제는 컴파일러가 람다식의 타입을 어떤 형식으로 만들어내는지 알아내는 코드이다.

&#9654; MyFunction02.java

```java
package lambda;

public interface MyFunction02 {

    void myMethod();

}
```

&#9654; Ex02.java

```java
package lambda;

public class Ex02 {

    public static void main(String[] args) {

        MyFunction02 f = () -> {};
        Object obj = (MyFunction02)(() -> {});
        String str = ((Object)(MyFunction02)(() -> {})).toString();

        System.out.println(f);
        System.out.println(obj);
        System.out.println(str);

        // System.out.println( () -> {} );
        System.out.println((MyFunction02)( () -> {} ));
        // System.out.println((MyFunction02)( () -> {} ).toString());
        System.out.println(((Object)(MyFunction02)(() -> {})).toString());

    }

}
```

<img src="/assets/img/study/week1502.png" width="70%" align="center"><br/>

일반적인 익명 객체라면, 객체의 타입이 `외부클래스이름$번호`와 같은 형식으로 타입이 결정되었을 텐데, 람다식의 타입은 `외부클래스이름$$Lambda$번호`와 같은 형식으로 되어 있는 것을 확인할 수 있다.

# Variable Capture

멤버 메서드 내부에서 클래스의 객체를 생성해 사용할 경우 다음과 같은 문제가 있다.

익명 구현 객체를 포함해 객체를 생성한다는 것은 new라는 키워드를 사용하는데 이 키워드를 사용한다는 의미는 동적 메모리 할당 영역인 heap 영역에 객체를 생성한다는 것이다.  
이렇게 생성된 객체는 자신을 감싸고 있는 멤버 메서드의 실행이 끝난 후에도 heap 영역에 존재하므로 사용할 수 있다. 하지만 이 멤버 메서드에 정의된 매개변수나 지역변수는 런타임 스택 영역에 할당되어 메서드 실행이 끝나면 사라져 더 이상 사용할 수 없게 된다.  
따라서 멤버 메서드 내부에서 생성된 객체가 자신을 감싸고 있는 메서드의 매개변수나 지역변수를 사용하려 할 때 문제가 생길 수 있다.

정리하면

1. 클래스의 멤버 메서드의 매개변수와 이 메서드 실행 블록 내부의 지역변수는 JVM의 런타임 스택 영역에 생성되고 메서드의 실행이 끝나면 런타임 스택 영역에서 사라진다.
2. new 연산자를 사용해 생성한 객체는 JVM의 heap 영역에 객체가 생성되고 GC에 의해 관리되며, 더 이상 사용하지 않는 객체에 대해 메모리에서 제거한다.

heap 영역에 생성된 객체가 스택 영역의 변수를 사용하려고 하는데, 해당 시점에 스택 영역에 더 이상 변수가 존재하지 않을 수 있고, 이 때문에 오류가 발생한다.

자바에서는 이 문제를 <b>variable capture</b>라고 하는 값 복사를 사용해 해결한다.

컴파일 시점에 멤버 메소드의 매개변수나 지역 변수를 멤버 메서드 내부에서 생성한 객체가 사용할 경우 객체 내부로 값을 복사해 사용한다. 하지만 모든 값을 복사해서 사용할 수 있는 것은 아니고, final 키워드로 작성되거나 final 성격을 가져야 한다는 제약이 있다.

## 로컬 변수 캡쳐 (Local Variable Capture)

Local variable은 조건이 final 또는 effectively final이어야 한다. final은 흔히 사용해봐서 알지만 effectively final은 뭘까?

effectively final은 Java 8에 추가된 syntatic sugar의 일종으로 초기화된 이후 값이 한번도 변경되지 않았다는 것을 말한다. effectively final 변수는 final 키워드가 붙어 있지 않지만 final 키워드를 붙인 것과 동일하게 컴파일에서 처리한다.

한마디로 초기화하고 값이 변경되지 않은 것을 말한다.

그렇다면 Local variable에는 왜 이런 조건이 붙어 있을까? 그것은 JVM의 메모리 구조를 보면 알 수 있다.

지역 변수는 쓰레드 간에 공유가 불가능하다. 인스턴스 변수는 JVM의 heap 영역에 생성되는데, 지역 변수와 달리 쓰레드 간에 공유가 가능하다. 즉, 지역 변수가 스택에 저장되기 때문에 람다식에서 값을 바로 참조하는데 제약이 있다. 복사된 값을 사용하는데 멀티 쓰레드 환경에서 변경이 되면 동시성에 대한 이슈를 대응하기 힘들기 때문이다.

# 메소드, 생성자 레퍼런스

## 메소드 레퍼런스

람다식으로 메서드를 간결하게 표현할 수 있는데, 놀랍게도 람다식을 더욱 간결하게 표현할 수 있는 방법이 있다. 람다식이 하나의 메서드만 호출하는 경우에 <b>'메서드 참조(method reference)'</b>라는 방법으로 간략히 할 수 있다.

예를 들어 문자열을 정수로 변환하는 람다식은 아래와 같이 작성할 수 있다.

```java
    Function<String, Integer> f = (String s) -> Integer.parseInt(s);
```

이 람다식을 메서드로 표현하면 다음과 같다.

```java
    Integer wrapper(String s) {
        return Integer.parseInt(s);
    }
```

여기서 wrapper 메서드는 그저 값을 받아서 `Integer.parseInt()`에게 넘겨주는 일만 한다. 그렇다면 이 메서드를 벗겨내고 직접 호출해보면 어떨까?

&#9654; 람다식

```java
    Function<String, Integer> f = (String s) -> Integer.parseInt(s);
```

&#9654; 메서드 참조

```java
    Function<String, Integer> f = Integer::parseInt;
```

메서드 참조에서 람다식의 일부가 생략되었지만, 컴파일러는 생략된 부분을 쉽게 알아낼 수 있다. 우변의 parseInt 메서드의 선언부, 또는 좌변의 Function 인터페이스에 지정된 지네릭 타입으로부터 알아내는 것이다.

다음과 같은 람다식을 메서드 참조로 변경해보자.

```java
    BiFunction<String, String, Boolean> f = (s1, s2) -> s1.equals(s2);
```

참조변수 f의 타입을 보면 람다식이 두 개의 String 타입의 매개변수를 받는다는 것을 알 수 있다. 따라서 람다식의 매개변수들은 없어도 된다. 매개변수들을 제거해서 메서드 참조로 변경하면 다음과 같이 된다.

```java
    BiFunction<String, String, Boolean> f = String::equals;
```

s1과 s2를 생략하고나면 equals만 남는데, 두 개의 String을 받아서 Boolean으로 반환하는 equals라는 이름의 메서드는 다른 클래스에도 존재할 수 있기 때문에 equals 앞에 클래스 이름이 반드시 필요하다.

메서드 참조를 사용할 수 있는 경우가 한 가지 더 있는데, 이미 생성된 객체의 메서드를 람다식에서 사용한 경우에는 클래스 이름 대신 그 객체의 참조변수를 적어줘야 한다.

```java
    MyClass obj = new MyClass();
    Function<String, Boolean> f = (x) -> obj.equals(x); // 람다식
    Function<String, Boolean> f2 = obj::equals;         // 메서드 참조
```

이렇게 살펴본 메서드 참조를 정리해보면 다음과 같다.

&#9654; <b>static 메서드 참조</b>

+ 메서드 참조는 static 메서드를 직접적으로 가리킬 수 있다.

```java
클래스이름::메서드이름
(매개변수) -> Class.staticMethod(매개변수)

String::valueOf
str -> String.valueOf(str)
```

&#9654; <b>인스턴스 메서드 참조</b>

```java
클래스이름::메서드이름
(obj, 매개변수) -> obj.instanceMethod(매개변수)

String::length
(value) -> value.length();
```

&#9654; <b>특정 객체 인스턴스 메서드 참조</b>

+ 특정 인스턴스의 메서드를 참조할 수 있다. 클래스 이름이 아닌 인스턴스 명을 넣는다.

```java
obj::instanceMethod
(매개변수) -> obj.instanceMethod(매개변수)

object::toString
() -> object.toString()
```

## 생성자의 메서드 참조

생성자를 호출하는 람다식도 메서드 참조로 변환할 수 있다.

```java
    Supplier<MyClass> s = () -> new MyClass();
    Supplier<MyClass> s = MyClass::new;
```

매개변수가 필요한 생성자라면, 매개변수의 개수에 따라 알맞은 함수형 인터페이스를 사용하면 된다. 필요에 따라 함수형 인터페이스를 새로 정의해야 한다.

```java
    Function<Integer, MyClass> f = (i) -> new MyClass(i);
    Function<Integer, MyClass> f2 = MyClass::new;
```

---

# 출처
자바의 정석 3판  
[오라클 튜토리얼](https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#approach1)
[개발자 한선우](https://github.com/Yadon079/yadon079.github.io/blob/master/_posts/dev/java/study%20halle/2021-02-28-week-15.md)