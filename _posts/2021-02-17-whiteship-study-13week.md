---
title : "자바스터디 13주차"
date : 2021-02-17
categories : [study]
---

# GOAL
자바의 Input과 Ontput에 대해 학습하세요.

# 학습할 것 (필수)

- 스트림 (Stream) / 버퍼 (Buffer) / 채널 (Channel) 기반의 I/O
- InputStream과 OutputStream
- Byte와 Character 스트림
- 표준 스트림 (System.in, System.out, System.err)
- 파일 읽고 쓰기
- 마감일시


### 스트림 (Stream) / 버퍼 (Buffer) / 채널 (Channel) 기반의 I/O

#### 스트림
스트림은 일련의 데이터입니다. 단순히 데이터 전달하는 모델이라고 볼수 있습니다.

#### 스트림 IO (Stream)

스트림IO는 입력 또는 출력 가능합니다.
 스트림 기반으로 입출력이 이루어 지기에 스트림 IO 로 표현합니다.
스트림에는 디스크 파일, 장치, 기타 프로그램 
및 메모리 배열을 포함하여 다양한 종류의 소스가 대상이 될수 있습니다.

또한 내부적으로 작동하는 방식에 관계없이 모든 스트림은 
이를 사용하는 프로그램에 동일한 단순 모델을 제공합니다.   

__입력 스트림__ 을 사용하여 한 번에 한 항목 씩
 소스에서 데이터를 읽습니다.

![입력](https://docs.oracle.com/javase/tutorial/figures/essential/io-ins.gif)
_출처 : oracle tutorials_

__출력 스트림__ 을 사용하여 한 번에 한 항목 씩
 대상에 데이터를 씁니다.

![출력](https://docs.oracle.com/javase/tutorial/figures/essential/io-outs.gif)
_출처 : oracle tutorials_


#### 버퍼
버퍼라는 공간을 만들어 일정량이 채워지면, 버퍼만큼에 데이터를 전달합니다.

#### 버퍼링 된 IO(Buffered stream)
기존의 스트림 기반의 경우 오버해드 비용이 많이 발생합니다. 
즉, 각 읽기 또는 쓰기 요청은 기본 OS에서 직접 처리되며, 
이러한 각 요청은 디스크 액세스, 네트워크 활동 또는 
상대적으로 비용이 많이 드는 다른 작업을 수행하기 때문에 
프로그램의 효율성이 훨씬 떨어질 수 있습니다.

이러한 종류의 오버 헤드를 줄이기 위해 
 버퍼링 된 IO 스트림을 지원합니다. 
 버퍼 된 __입력 스트림__ 은 버퍼 로 알려진 메모리 영역에서 데이터를 읽습니다.
  기본 입력 API는 버퍼가 __비어있을 때만 호출__ 됩니다.
   마찬가지로 버퍼링 된 __출력 스트림__ 은 버퍼에 데이터를 쓰고 
   기본 출력 API는 버퍼가 __가득 찬 경우에만 호출__ 됩니다.


#### 버퍼링 된 스트림 플러시
버퍼가 채워질 때까지 기다리지 않고 중요한 지점에서 버퍼링된 스트림을 실행할 수 있으며. 이를 버퍼 플러시라고 합니다.
일부 버퍼링 된 출력 클래스 는 선택적 생성자 인수로 지정된 autoflush를 지원 합니다.
 자동 플러시가 활성화되면 특정 키 이벤트로 인해 버퍼가 플러시됩니다. 
스트림을 수동으로 플러시하려면 해당 flush메서드를 호출하면 됩니다.

#### 채널

스트림 방식에 IO는 BLOCKING방식을 사용하고 있습니다. 이는 커널의 상태에 따라 많은 오버해드가 발생할 수 있습니다.
때문에 나온 NIO(Non-blocking-IO)방식을 지원하는 방법입니다. 채널은 IO를 지원하는 통로를 만들어 줍니다.
때문에 스트림 IO와 같이 매번 스레드를 생성할 필요가 없으며, 더이상 처리 완료시 까지 기다릴 필요가 없습니다.

![block](https://mblogthumb-phinf.pstatic.net/20140718_68/joebak_1405647716368SFg7v_JPEG/%BD%BD%B6%F3%C0%CC%B5%E53.jpg?type=w2)
_block IO_
![nonblock](https://mblogthumb-phinf.pstatic.net/20140718_165/joebak_14056477165486yA48_JPEG/%BD%BD%B6%F3%C0%CC%B5%E54.jpg?type=w2)
_non block IO_

### 채널 기반의 IO
스트림 IO에서는 스레드 마다 IO를 발생시키지 때문에 대역폭이 정해져 있는 네트워크의 경우, 정상적인 사용이 불가하고 메인쓰레드가 작업처리시 까지 중단 될수 있습니다.
때문에 2002년 New IO라는 이름의 채널 기반의 새로운 입출력 시스템을 도입합니다. 
채널과 스트림의 주요 차이점은 다음과 같습니다.
- 단방향 데이터 전송에 스트림을 사용할 수 있습니다.
- 채널은 양방향 데이터 전송 기능을 제공합니다.
[channel](https://static.javatpoint.com/core/javanio/images/nio-tutorial7.png)

### InputStream과 OutputStream

![tree](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fr1sYg%2FbtqXxdaL7zQ%2Frc9JTRwqk1jMs1bjUoJ3rk%2Fimg.png)
_nutshell_

### Byte와 Character 스트림

#### Byte 스트림

스트림은 바이트단위로 데이터를 전송하며 입력 대상에 따라 다양하게 이용합니다.
아래는 구현체 중 FIleStream을 이용한 예제 입니다.

```java
public class CopyByte {
    public static void main(String[] args) throws IOException {

        FileInputStream in = null;
        FileOutputStream out = null;

        try {
            in = new FileInputStream("xanadu.txt");
            out = new FileOutputStream("outagain.txt");
            int c;

            while ((c = in.read()) != -1) {
                out.write(c);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}
```

> 바이트코드

```
public class week13.CopyByte {
  public week13.CopyByte();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]) throws java.io.IOException;
    Code:
       0: aconst_null
       1: astore_1
       2: aconst_null
       3: astore_2
       4: new           #7                  // class java/io/FileInputStream
       7: dup
       8: ldc           #9                  // String xanadu.txt
      10: invokespecial #11                 // Method java/io/FileInputStream."<init>":(Ljava/lang/String;)V
      13: astore_1
      14: new           #14                 // class java/io/FileOutputStream
      17: dup
      18: ldc           #16                 // String outagain.txt
      20: invokespecial #18                 // Method java/io/FileOutputStream."<init>":(Ljava/lang/String;)V
      23: astore_2
      24: aload_1
      25: invokevirtual #19                 // Method java/io/FileInputStream.read:()I
      28: dup
      29: istore_3
      30: iconst_m1
      31: if_icmpeq     42
      34: aload_2
      35: iload_3
      36: invokevirtual #23                 // Method java/io/FileOutputStream.write:(I)V
      39: goto          24
      42: aload_1
      43: ifnull        50
      46: aload_1
      47: invokevirtual #27                 // Method java/io/FileInputStream.close:()V
      50: aload_2
      51: ifnull        82
      54: aload_2
      55: invokevirtual #30                 // Method java/io/FileOutputStream.close:()V
      58: goto          82
      61: astore        4
      63: aload_1
      64: ifnull        71
      67: aload_1
      68: invokevirtual #27                 // Method java/io/FileInputStream.close:()V
      71: aload_2
      72: ifnull        79
      75: aload_2
      76: invokevirtual #30                 // Method java/io/FileOutputStream.close:()V
      79: aload         4
      81: athrow
      82: return
    Exception table:
       from    to  target type
           4    42    61   any
          61    63    61   any
}


```

내부적으로 아래 이미지와 같은 작업을 반복한다.  
![이미지](https://docs.oracle.com/javase/tutorial/figures/essential/byteStream.gif)

스트림이 더 이상 필요하지 않을 때 항상 스트림을 닫는 것은 매우 중요 합니다.
 오류가 발생하더라도 두 스트림이 모두 닫히도록 
 finally블록을 사용하는 것 또한 매우 중요 합니다. 이 방법은 심각한 리소스 누출을 방지하는 데 도움이됩니다. (java 7버전 이상인 경우, try-resource-with를 사용하는 것도 좋습니다.)
위 예제 소스는 실제로는 사용하면 안되는 일종의 저수준 IO입니다. 
xanadu.txt문자 데이터가 포함되어 있으므로 가장 좋은 방법은 문자 스트림 을 사용 하는 것입니다. 

#### Character 스트림

Java에서는 유니 코드 규칙을 사용하여 문자 값을 저장합니다.
 Character 스트림은 이 내부 형식을 로컬 문자 집합으로 자동으로 변환합니다.
 스트림 클래스로 수행 된 입력 및 출력은 로컬 문자 집합과 자동으로 변환됩니다. 
 하지만 Character 스트림을 사용하는 프로그램은 프로그래머의 추가 노력없이 로컬 문자 집합에 자동으로 적용하고 인코딩(UTF-16)까지 해줍니다.

```java
public class CopyCharacters {
    public static void main(String[] args) throws IOException {

        FileReader inputStream = null;
        FileWriter outputStream = null;

        try {
            inputStream = new FileReader("xanadu.txt");
            outputStream = new FileWriter("characteroutput.txt");

            int c;
            while ((c = inputStream.read()) != -1) {
                outputStream.write(c);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}
```

> 바이트코드

```
public class week13.CopyCharacters {
  public week13.CopyCharacters();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]) throws java.io.IOException;
    Code:
       0: aconst_null
       1: astore_1
       2: aconst_null
       3: astore_2
       4: new           #7                  // class java/io/FileReader
       7: dup
       8: ldc           #9                  // String xanadu.txt
      10: invokespecial #11                 // Method java/io/FileReader."<init>":(Ljava/lang/String;)V
      13: astore_1
      14: new           #14                 // class java/io/FileWriter
      17: dup
      18: ldc           #16                 // String characteroutput.txt
      20: invokespecial #18                 // Method java/io/FileWriter."<init>":(Ljava/lang/String;)V
      23: astore_2
      24: aload_1
      25: invokevirtual #19                 // Method java/io/FileReader.read:()I
      28: dup
      29: istore_3
      30: iconst_m1
      31: if_icmpeq     42
      34: aload_2
      35: iload_3
      36: invokevirtual #23                 // Method java/io/FileWriter.write:(I)V
      39: goto          24
      42: aload_1
      43: ifnull        50
      46: aload_1
      47: invokevirtual #27                 // Method java/io/FileReader.close:()V
      50: aload_2
      51: ifnull        82
      54: aload_2
      55: invokevirtual #30                 // Method java/io/FileWriter.close:()V
      58: goto          82
      61: astore        4
      63: aload_1
      64: ifnull        71
      67: aload_1
      68: invokevirtual #27                 // Method java/io/FileReader.close:()V
      71: aload_2
      72: ifnull        79
      75: aload_2
      76: invokevirtual #30                 // Method java/io/FileWriter.close:()V
      79: aload         4
      81: athrow
      82: return
    Exception table:
       from    to  target type
           4    42    61   any
          61    63    61   any
}

```
CopyCharacters는 FileReader하고 FileWriter를 FileInputStream과 FileOutputStream 대신 사용합니다. 
Character 스트림은 바이트 스트림의 "래퍼"입니다. 
Character 스트림은 바이트 스트림을 사용하여 물리적 IO를 수행하고, 문자와 바이트 간의 변환을 처리합니다.
요구 사항을 충족하는 사전 패키징 된 문자 스트림 클래스가 없을 땐 문자 스트림을 만들 수 있습니다.
Character 스트림은 일반적으로 단일 문자보다 더 큰 단위로 발생합니다. 
하나의 공통 단위는 줄이며 줄이란 끝에 종결자가있는 문자열입니다. 
줄 종결자는 캐리지 리턴 / 줄 바꿈 시퀀스 ( "\r\n"), 단일 캐리지 리턴 ( "\r") 또는 단일 줄 바꿈 ( "\n") 입니다.

```java

public class CopyLines {
    public static void main(String[] args) throws IOException {
 
        BufferedReader inputStream = null;
        PrintWriter outputStream = null;
 
        try {
            inputStream = new BufferedReader(new FileReader("xanadu.txt"));
            outputStream = new PrintWriter(new FileWriter("characteroutput.txt"));
 
            String l;
            while ((l = inputStream.readLine()) != null) {
                outputStream.println(l);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}
```

> 바이트코드

```
public class week13.CopyLines {
  public week13.CopyLines();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]) throws java.io.IOException;
    Code:
       0: aconst_null
       1: astore_1
       2: aconst_null
       3: astore_2
       4: new           #7                  // class java/io/BufferedReader
       7: dup
       8: new           #9                  // class java/io/FileReader
      11: dup
      12: ldc           #11                 // String xanadu.txt
      14: invokespecial #13                 // Method java/io/FileReader."<init>":(Ljava/lang/String;)V
      17: invokespecial #16                 // Method java/io/BufferedReader."<init>":(Ljava/io/Reader;)V
      20: astore_1
      21: new           #19                 // class java/io/PrintWriter
      24: dup
      25: new           #21                 // class java/io/FileWriter
      28: dup
      29: ldc           #23                 // String characteroutput.txt
      31: invokespecial #25                 // Method java/io/FileWriter."<init>":(Ljava/lang/String;)V
      34: invokespecial #26                 // Method java/io/PrintWriter."<init>":(Ljava/io/Writer;)V
      37: astore_2
      38: iconst_0
      39: istore_3
      40: aload_1
      41: invokevirtual #29                 // Method java/io/BufferedReader.readLine:()Ljava/lang/String;
      44: dup
      45: astore        4
      47: ifnull        84
      50: iinc          3, 1
      53: getstatic     #33                 // Field java/lang/System.out:Ljava/io/PrintStream;
      56: ldc           #39                 // String %d
      58: iconst_1
      59: anewarray     #2                  // class java/lang/Object
      62: dup
      63: iconst_0
      64: iload_3
      65: invokestatic  #41                 // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
      68: aastore
      69: invokestatic  #47                 // Method java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
      72: invokevirtual #53                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
      75: aload_2
      76: aload         4
      78: invokevirtual #58                 // Method java/io/PrintWriter.println:(Ljava/lang/String;)V
      81: goto          40
      84: aload_1
      85: ifnull        92
      88: aload_1
      89: invokevirtual #59                 // Method java/io/BufferedReader.close:()V
      92: aload_2
      93: ifnull        124
      96: aload_2
      97: invokevirtual #62                 // Method java/io/PrintWriter.close:()V
     100: goto          124
     103: astore        5
     105: aload_1
     106: ifnull        113
     109: aload_1
     110: invokevirtual #59                 // Method java/io/BufferedReader.close:()V
     113: aload_2
     114: ifnull        121
     117: aload_2
     118: invokevirtual #62                 // Method java/io/PrintWriter.close:()V
     121: aload         5
     123: athrow
     124: return
    Exception table:
       from    to  target type
           4    84   103   any
         103   105   103   any
}
```

호출하면 라인 별로 호출하여 총 4번 while문이 반복됩니다.

### 표준 스트림 (System.in, System.out, System.err)

System.in, System.out 및 System.err은 Java VM이 시작될 때 Java 런타임에 의해 초기화되므로 스트림을 직접 인스턴스화 할 필요가 없습니다. 
(런타임에 스트림을 교환 할수 있습니다.)

System.in
System.in 일반적으로 콘솔 프로그램의 키보드 입력에 연결 되는 InputStream 입니다. 
즉, 명령 줄에서 Java 응용 프로그램을 시작하고 CLI 콘솔 (또는 터미널)에 포커스가 있는 동안 키보드에 무언가를 입력하면 
일반적으로 해당 Java 응용 프로그램 내부에서 System.in을 통해 키보드 입력을 읽을 수 있습니다. 
그러나 System.in을 통해 읽을 수있는 것은 해당 Java 애플리케이션 (애플리케이션을 시작한 콘솔 / 터미널)에 대한 키보드 입력일뿐입니다.
 다른 응용 프로그램의 키보드 입력은 System.in을 통해 읽을 수 없습니다.

```java
    /**
     * The "standard" input stream. This stream is already
     * open and ready to supply input data. Typically this stream
     * corresponds to keyboard input or another input source specified by
     * the host environment or user.
     */
    public static final InputStream in = null;
```

System.out
System.out문자를 쓸 수 있는 PrintStream 입니다.
 System.out은 일반적으로 CLI 콘솔 / 터미널에 기록한 데이터를 출력합니다.
  System.out은 실행 결과를 사용자에게 표시하는 방법으로 명령 줄 도구와 같은 콘솔 전용 프로그램에서 자주 사용됩니다.
   이것은 또한 프로그램에서 디버그 문을 인쇄하는 데 자주 사용됩니다 (보통은 Log를 사용합니다).

```java
 /**
     * The "standard" output stream. This stream is already
     * open and ready to accept output data. Typically this stream
     * corresponds to display output or another output destination
     * specified by the host environment or user.
     * <p>
     * For simple stand-alone Java applications, a typical way to write
     * a line of output data is:
     * <blockquote><pre>
     *     System.out.println(data)
     * </pre></blockquote>
     * <p>
     * See the {@code println} methods in class {@code PrintStream}.
     *
     * @see     java.io.PrintStream#println()
     * @see     java.io.PrintStream#println(boolean)
     * @see     java.io.PrintStream#println(char)
     * @see     java.io.PrintStream#println(char[])
     * @see     java.io.PrintStream#println(double)
     * @see     java.io.PrintStream#println(float)
     * @see     java.io.PrintStream#println(int)
     * @see     java.io.PrintStream#println(long)
     * @see     java.io.PrintStream#println(java.lang.Object)
     * @see     java.io.PrintStream#println(java.lang.String)
     */
    public static final PrintStream out = null;
```

System.err
System.err입니다 PrintStream. 일반적으로 오류 텍스트를 출력하는 데만 사용된다는 점을 제외하면 System.out과 동일합니다. 
Eclipse와 같은 일부 프로그램은 출력을 System.err 빨간색 텍스트 로 표시하여 오류 텍스트임을 더 명확 하게 보여줍니다 .

```java
    /**
     * The "standard" error output stream. This stream is already
     * open and ready to accept output data.
     * <p>
     * Typically this stream corresponds to display output or another
     * output destination specified by the host environment or user. By
     * convention, this output stream is used to display error messages
     * or other information that should come to the immediate attention
     * of a user even if the principal output stream, the value of the
     * variable {@code out}, has been redirected to a file or other
     * destination that is typically not continuously monitored.
     */
    public static final PrintStream err = null;
```


# 출처
https://docs.oracle.com/javase/tutorial/essential/io/streams.html  
https://adrian0220.tistory.com/150?category=775742  
https://m.blog.naver.com/PostView.nhn?blogId=joebak&logNo=220063974083&proxyReferer=https:%2F%2Fwww.google.com%2F  
https://www.javatpoint.com/java-nio-vs-input-output