---
title : "자바스터디 9주차"
date : 2021-01-15
categories : study
--- 


# GOAL
자바의 예외 처리에 대해 학습하세요.

# 학습할 것 (필수)
+ 자바에서 예외 처리 방법 (try, catch, throw, throws, finally)
+ 자바가 제공하는 예외 계층 구조
+ Exception과 Error의 차이는?
+ RuntimeException과 RE가 아닌 것의 차이는?
+ 커스텀한 예외 만드는 방법


### 자바에서 예외 처리 방법 (try, catch, throw, throws, finally)

#### 예외(exception)란 ?
> '예외적인 이벤트'의 약자로 프로그램의 정상적인 흐름은 방해하는 이벤트라고 정의 한다   
> _[오라클](https://docs.oracle.com/javase/tutorial/essential/exceptions/definition.html)_

에외 전체 흐름도


![흐름도](https://raw.githubusercontent.com/redbean88/redbean88.github.io/a95ca6a9ef10ed7364961b2c7a22ed66ab79251b/img/exception02.svg)

__try__

+ try블럭 내에서 예외가 발생한 경우, 해당 되는 catch 블록을 실행 한다.
+ 일치하는 catch블럭을 찾으면, 그 catch블럭 내의 문장들을 수행
+ 일치하는 catch블럭이 없으면, 처리하지 못하고 그대로 잔행

__catch__

+ try블럭에서 발생한 예외와 catch블록에 있는 예외를 비교하여 일치하는 예외 블록시 실행 된다

```java
public class TryCatchFinally {
    public static void main(String[] args) {
        try{
          ...처리작업
        }catch (Exception e){
          ...예외발생시 처리작업
        }
    }
}
````

> 컴파일소스

```
public class TryCatchFinally {
  public TryCatchFinally();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: return
}
```

_추가_  
__catch블럭(자바7)__

+ ‘|’기호를 통해 하나의 catch블럭으로 통합
+ ‘|’기호로 연결된 예외 클래스가 부모와 자식 관계에 있다면 컴파일 에러가 발생
+ 참조변수 e는 __상수__ 라서 값을 __변경할 수 없다__

```java
try {
	...
} catch (ExceptionA | ExceptionB e) {
	e.printStackTrace();
}
```

__finally__

+ 블록내 처리후 반드시 실행되는 블럭 io나 connect 등 사용후 종료 하는 자원이 있을 경우 사용
+ finally 블록에서 __예외 발생시, catch블록의 예외 추적이 불가하다__.
+ 이런 문제는 자바7에서 try-with-resources로 해결할 수 있다.

```java
spublic class TryCatchFinally {
    public static void main(String[] args) {
        try{
          ...처리작업
        }catch (Exception e){
          ...예외발생시 처리작업
        }finally {
          ...항상 실행 작업
        }
    }
}

```

> 컴파일소스

```
public class TryCatchFinally {
  public TryCatchFinally();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: return
}
ch
```

__try-with-resources(자바7)__

+ 대상이 되는 메소드는 AutoCloseable 인터페이스를 구현해야 한다.
+ try블록이 종료 되면, AutoCloseable 의 close()메소드의 구현체가 실행된다.
+ try-catch문과 AutoCloseable.close()에서 모두 예외가 발생되면, 두 예외가 동시에 발생할 수는 없기 때문에 close()에서 발생되는 예외는 `억제된 예외` 로 처리되어 실제 발생한 예외(try-catch문에서 발생한 예외)에 저장된다.

```java
public class TryCatchResource {
    public static void main(String[] args){
        try(TryCatchResourceMethod c = new TryCatchResourceMethod()){

        } catch (Exception e) {
        }
    }
}

class TryCatchResourceMethod implements AutoCloseable {

    @Override
    public void close() {
        System.out.println("always exec");
    }
}


```

> 컴파일 소스

```
public class TryCatchResource {
  public TryCatchResource();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: new           #7                  // class TryCatchResourceMethod
       3: dup
       4: invokespecial #9                  // Method TryCatchResourceMethod."<init>":()V
       7: astore_1
       8: aload_1
       9: invokevirtual #10                 // Method TryCatchResourceMethod.close:()V
      12: goto          16
      15: astore_1
      16: return
    Exception table:
       from    to  target type
           0    12    15   Class java/lang/Exception
}
```


```

`getSuppressed,addSuppressed`
+ Throwable에 추가된 getSuppressed메소드를 이용하면 예외를 가져올 수도 있다.
+ Throwable에 추가된 addSuppressed메소드를 이용하면 예외를 추가할 수도 있다.

```java
public class Throwable implements Serializable {

  public final synchronized Throwable[] getSuppressed() {
      if (suppressedExceptions == SUPPRESSED_SENTINEL ||
          suppressedExceptions == null)
          return EMPTY_THROWABLE_ARRAY;
      else
          return suppressedExceptions.toArray(EMPTY_THROWABLE_ARRAY);
  }

  public final synchronized void addSuppressed(Throwable exception) {
    if (exception == this)
        throw new IllegalArgumentException(SELF_SUPPRESSION_MESSAGE, exception);

    Objects.requireNonNull(exception, NULL_CAUSE_MESSAGE);

    if (suppressedExceptions == null) // Suppressed exceptions not recorded
        return;

    if (suppressedExceptions == SUPPRESSED_SENTINEL)
        suppressedExceptions = new ArrayList<>(1);

    suppressedExceptions.add(exception);
}
}
```

__throw__
- throw키워드를 이용해서 고의로 예외를 발생시킬 수 있다.
```java
Exception e = new Exception("예외");
throw e;
```

__throws__

+ throws키워드를 이용해서 예외를 호출한 메소드로 넘길 수 있다. ~~하지마세요~~
+ throws키워드를 사용할 경우, 예외 처리를 위한 Exception table 생성된다

```java
public class TryCatchResource {
    public static void main(String[] args) {
        parent();
    }

    public static void parent(){
        try{
            child();
        } catch (Exception e) {
            System.out.println("parent");
            e.printStackTrace();
        }
    }

    private static void child() throws Exception {
        throw new Exception("child Exception");
    }
}
```

> 컴파일소스

```
public class TryCatchResource {
  public TryCatchResource();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: invokestatic  #7                  // Method parent:()V
       3: return

  public static void parent();
    Code:
       0: invokestatic  #12                 // Method child:()V
       3: goto          19
       6: astore_0
       7: getstatic     #17                 // Field java/lang/System.out:Ljava/io/PrintStream;
      10: ldc           #23                 // String parent
      12: invokevirtual #24                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
      15: aload_0
      16: invokevirtual #30                 // Method java/lang/Exception.printStackTrace:()V
      19: return
    Exception table:
       from    to  target type
           0     3     6   Class java/lang/Exception
}
```
컴파일 소스를 보면 예외 처리를 위한 Exception table이 생긴걸 알 수 있다.

output

```
parent
java.lang.Exception: child Exception
	at TryCatchResource.child(TryCatchResource.java:18)
	at TryCatchResource.parent(TryCatchResource.java:10)
	at TryCatchResource.main(TryCatchResource.java:5)
```

### 자바가 제공하는 예외 계층 구조

![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FRoZlV%2FbtqS3I0AXjN%2FadMoY7CeVX8YCffIlNCoH0%2Fimg.png)

https://madplay.github.io/post/java-checked-unchecked-exceptions

+ Exception과 Error는 Throwable이라는 클래스를 상속받고 있으며 Throwable은 Object를 직접 상속받음

### Exception과 Error의 차이는?

+ Throwable은 Error와 Exception이라는 두 개의 하위 클래스를 갖는데, 필요한 곳에서 Exception클래스를 확인하고 수정하는 것으로 개발자가 직접 처리 할 수 있다
+ Error는 OutOfMemoryError나 NoClassDefFoundError클래스처럼 개발자 스스로 처리 할 수 있는것이 아니다.

### RuntimeException과 RE가 아닌 것의 차이는?


- 예외는 ‘런타임 예외(runtime exception)’이거나 ‘확인해야 하는 예외(checked exception)’ 두 가지로 구분
- 런타임 예외는 모두 RuntimeException의 하위 클래스고, 확인해야 하는 예외는 모두 다른 예외
- 확인해야 하는 예외(checked exception)를 처리하는 메소드(또는 생성자)를 사용할 때는 메소드 정의에 명시적으로 예외가 정의되어야 하며, 따라 코드를 호출하는 모든 호출자들은 해당 예외를 처리 즉, 메소드의 호출자에게 전달하거나 try/catch/finally문으로 예외를 적절히 처리
[참고](https://www.notion.so/3565a9689f714638af34125cbb8abbe8)

### 커스텀한 예외 만드는 방법

- 기존 정의된 예외 클래스 외에 필요에 따라 새로운 예외 클래스를 정의
- 보통 Exception클래스로부터 상속받는 클래스를 만들지만, 필요에 따라 알맞은 예외 클래스를 선택 가능

```java
public class CustomException extends Exception {
    private final int ERROR_CODE;
    CustomException(String message, int errorCode) {
        super(message);
        this.ERROR_CODE = errorCode;
    }
    CustomException(String message) {
        this(message, 100);
    }
    public int getErrorCode() {
        return ERROR_CODE;
    }
}

```

> 컴파일소스

```
ublic class CustomException extends java.lang.Exception {
  CustomException(java.lang.String, int);
    Code:
       0: aload_0
       1: aload_1
       2: invokespecial #1                  // Method java/lang/Exception."<init>":(Ljava/lang/String;)V
       5: aload_0
       6: iload_2
       7: putfield      #7                  // Field ERROR_CODE:I
      10: return

  CustomException(java.lang.String);
    Code:
       0: aload_0
       1: aload_1
       2: bipush        100
       4: invokespecial #13                 // Method "<init>":(Ljava/lang/String;I)V
       7: return

  public int getErrorCode();
    Code:
       0: aload_0
       1: getfield      #7                  // Field ERROR_CODE:I
       4: ireturn
}

```

참고


[9주차](https://github.com/kongduboo/whiteship-java-study/blob/main/week9.md)
