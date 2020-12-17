---
title : "자바스터디 4주차"
date : 2020-12-02
categories : study
---

# GOAL
> 자바가 제공하는 제어문을 학습하세요.

+ 학습할 것
+ 선택문
+ 반복문

### 조건문
> if를 사용하는 결정문


if문 안 조건에 해당하는 경우, 그 블록안의 코드를 실행

```
class Main {
  public static void main(String[] args) {
    int x=0 , y;

		if(x==0)  y = 0;
		else if(x==1) y = 1;
		else if(x==2) y = 2;
		else if(x==5) y = 5;
		else if(x==6) y = 6;
  }
}
```

```
Code:
       0: iconst_0                  //int 0 피연산자 스택에 push
       1: istore_1                  //로컬변수1번(x) 에 피연산자 스택에서 pop된 값(0)을 저장
       2: iload_1                   //로컬변수1번에서 값 호출(0)
       3: ifne          11          //값이 0이 아닌경우 , 11번지로 점프
       6: iconst_0                  //int 0 피연산자 스택에 push
       7: istore_2                  //로컬변수2번(y) 에 피연산자 스택에서 pop된 값(0)을 저장
       8: goto          50          //50번지로 이동(return)
      11: iload_1                   //로컬변수1번에서 값 호출(0)
      12: iconst_1                  //int 1 피연산자 스택에 push
      13: if_icmpne     21          //로컬변수1번에서 값(0)과 피연산자 스택에서 pop된 값(1)을 비교하여 서로 다른경우, 21번으로 점프
      16: iconst_1                  //int 1 피연산자 스택에 push
      17: istore_2                  //로컬변수2번(y) 에 피연산자 스택에서 pop된 값(1)을 저장
      18: goto          50          //50번지로 이동(return)
      21: iload_1                   //로컬변수1번에서 값 호출(0)
      22: iconst_2                  //int 2 피연산자 스택에 push
      23: if_icmpne     31          //로컬변수1번에서 값(0)과 피연산자 스택에서 pop된 값(2)을 비교하여 서로 다른경우, 31번으로 점프
      26: iconst_2                  //int 2 피연산자 스택에 push
      27: istore_2                  //로컬변수2번(y) 에 피연산자 스택에서 pop된 값(2)을 저장
      28: goto          50          //50번지로 이동(return)
      31: iload_1                   //로컬변수1번에서 값 호출(0)
      32: iconst_5                  //int 5 피연산자 스택에 push
      33: if_icmpne     41          //로컬변수1번에서 값(0)과 피연산자 스택에서 pop된 값(5)을 비교하여 서로 다른경우, 41번으로 점프
      36: iconst_5                  //int 5 피연산자 스택에 push
      37: istore_2                  //로컬변수2번(y) 에 피연산자 스택에서 pop된 값(5)을 저장
      38: goto          50          //50번지로 이동(return)
      41: iload_1                   //로컬변수1번에서 값 호출(0)
      42: bipush        6           //int 6 피연산자 스택에 push
      44: if_icmpne     50          //로컬변수1번에서 값(0)과 피연산자 스택에서 pop된 값(6)을 비교하여 서로 다른경우, 50번으로 점프
      47: bipush        6           //int 6 피연산자 스택에 push
      49: istore_2                  //로컬변수2번(y) 에 최상위 스택에 쌓인 값(6)을 저장
      50: return                    //void 메서드를 리턴
```

### 선택문
> swich를 사용하는 결정문, if문을 이용할 때 보다 속도가 빠르다. [관련문서](https://thinkpro.tistory.com/132)


+ switch 문 표현식은 byte, short, int, long, enum 유형, String 및 Byte, Short, Int 및 Long과 같은 일부 래퍼 유형일 수 있습니다.(단, switch 표현식에서만 wrapper 허용하고, case 에는 wrapper 를 허용하지 않는다.) switch () 대괄호 사이에 변수 또는 표현식을 넣을 수 있습니다 .  (float 은  두 개의 부동 소수점 숫자를 비교하는 것은 x와 y의 십진수 등가가 합리적인 정밀도로 동일하게 보일 때 정확하지 않을 수 있어 허용하지 않습니다.)
+ 케이스 값은 리터럴 또는 상수 여야합니다. 케이스 값 유형은 표현식 유형이어야합니다.
+ 각 케이스는 고유해야합니다. 중복 케이스 값을 생성하면 컴파일 타임 오류가 발생합니다.
+ 각 케이스에는 선택적인 break 문 이 있습니다. 스위치 내부에서 명령문 시퀀스를 종료하고 스위치 표현식 다음에 컨트롤을 점프하는 데 사용됩니다.
+ 어떤 경우에도 break 문을 사용하지 않으면 break  문에 도달 할 때까지 다음 case로 실행이 계속됩니다  .
+ 스위치 문 케이스에 해당하는 경우가 없으면 이 default 가 실행됩니다. 기본 케이스에는 break 문이 필요하지 않습니다 .

```
class Main {
  public static void main(String[] args) {
    int x=0 , y;
    switch(x)
		{
		case 0 : y = 0; break;
		case 1 : y = 1; break;
		case 4 : y = 4; break;
		case 5 : y = 5; break;
		case 6 : y = 6; break;
		}
  }
}
```

```
 Code:
       0: iconst_0                        //int 0 피연산자 스택에 push
       1: istore_1                        //로컬변수1번(x)에 피연산자 스택에서 pop된 값(0)을 저장
       2: iload_1
       3: tableswitch   { // 0 to 6       //스위치 처리를 위한 테이블 생성(변수길이까지 생성, 예제에서는 총 7개(0~6) 생성)
                     0: 44
                     1: 49
                     2: 67
                     3: 67
                     4: 54
                     5: 59
                     6: 64
               default: 67
          }
      44: iconst_0                        //int 0 피연산자 스택에 push
      45: istore_2                        //로컬변수2번(y)에 피연산자 스택에서 pop된 값(0)을 저장
      46: goto          67                //67번지로 점프
      49: iconst_1                        //int 1 피연산자 스택에 push
      50: istore_2                        //로컬변수2번(y)에 피연산자 스택에서 pop된 값(2)을 저장
      51: goto          67                //67번지로 점프
      54: iconst_4                        //int 4 피연산자 스택에 push
      55: istore_2                        //로컬변수2번(y)에 피연산자 스택에서 pop된 값(4)을 저장
      56: goto          67                //67번지로 점프
      59: iconst_5                        //int 5 피연산자 스택에 push
      60: istore_2                        //로컬변수1번(y)에 피연산자 스택에서 pop된 값(5)을 저장
      61: goto          67                //67번지로 점프
      64: bipush        6                 //int 6 피연산자 스택에 push
      66: istore_2                        //로컬변수1번(y)에 피연산자 스택에서 pop된 값(6)을 저장
      67: return                          //void 메서드를 리턴
```

### 반복문
> 반복하는 작업을 처리 할 경우, 조건을 주어 해당 작업이 완료 될때 까지 작업을 처리


+ break
	+ 해당 코드 하위 작업을 더 이상 진행 하지 않으며, 해당 블록의 작업을 종료한다.
+ continue
	+ 해당 코드 하위 작업을 더 이상 진행 하지 않으며, 해당 루프의 다음 작업을 진행한다.
+ label
	+ 위 코드와 혼합하여 사용, 조건에 일치하면 라벨이 붙어 있는 대상으로 점프한다.[참고링크](https://puttico.tistory.com/93)

#### for
```
public static void main(String[] args) {
	
		for(int i= 0; i < 5 ; i+=2) {
			System.out.println(i);
		}
	}
}
```
> 2번에서 15번으로 이동하여 비교 처리 후, 5번으로 돌아와 i값을 출력후, 증감 시킨다.


```
  Code:
       0: iconst_0                  //int 0 피연산자 스택에 push
       1: istore_1                  //로컬변수1번(i)에 피연산자 스택에서 pop된 값(0)을 저장
       2: goto          15          //15번지로 점프
       5: getstatic     #16         //static 메소드 호출(Field java/lang/System.out:Ljava/io/PrintStream)
       8: iload_1                   //로컬변수1번에서 값 호출(0)
       9: invokevirtual #22         //메소드 실행(Method java/io/PrintStream.println:(I)V)(i 출력)
      12: iinc          1, 2        //로컬변수1번(i)를 2만큼 증가
      15: iload_1                   //로컬변수1번에서 값 호출(0)
      16: iconst_5                  //int 5 피연산자 스택에 push
      17: if_icmplt     5           //로컬변수1번에서 값과 피연산자 스택에서 pop된 값을 비교하여 로컬변수가 작은 경우, 5번으로 점프
      20: return
```

#### while
> 놀랍게도 어셈블러는 for문과 동일하게 처리한다.


```
public static void main(String[] args) {
		int i = 0;
		while (i<5) {
			System.out.println(i);
			i+=2;
		}
	}
```
```
  Code:
       0: iconst_0                  //int 0 피연산자 스택에 push
       1: istore_1                  //로컬변수1번(i)에 피연산자 스택에서 pop된 값(0)을 저장
       2: goto          15          //15번지로 점프
       5: getstatic     #16         //static 메소드 호출(Field java/lang/System.out:Ljava/io/PrintStream)
       8: iload_1                   //로컬변수1번에서 값 호출(0)
       9: invokevirtual #22         //메소드 실행(Method java/io/PrintStream.println:(I)V)(i 출력)
      12: iinc          1, 2        //로컬변수1번(i)를 2만큼 증가
      15: iload_1                   //로컬변수1번에서 값 호출(0)
      16: iconst_5                  //int 5 피연산자 스택에 push
      17: if_icmplt     5           //로컬변수1번에서 값과 피연산자 스택에서 pop된 값을 비교하여 로컬변수가 작은 경우, 5번으로 점프
      20: return
```
#### do-while
```
public static void main(String[] args) {
	
		int i = 0;
		do{
			System.out.println(i);
			i+=2;
		}while(i<5);
	}
```
> 비교연산처리부터 하지 않고, 차래대로 실행 된다. 때문에 do구문에 있는 명령은 한번문 무조건 실행 되게 된다.


```
 Code:
       0: iconst_0					//int 0 피연산자 스택에 push
       1: istore_1					//로컬변수1번(i)에 피연산자 스택에서 pop된 값(0)을 저장
       2: getstatic     #16         //static 메소드 호출(Field java/lang/System.out:Ljava/io/PrintStream)
       5: iload_1					//로컬변수1번에서 값 호출(0)
       6: invokevirtual #22         //메소드 실행(Method java/io/PrintStream.println:(I)V)(i 출력)
       9: iinc          1, 2		//로컬변수1번(i)를 2만큼 증가
      12: iload_1					//로컬변수1번에서 값 호출(2)
      13: iconst_5					//int 5 피연산자 스택에 push
      14: if_icmplt     2			//로컬변수1번에서 값과 피연산자 스택에서 pop된 값을 비교하여 로컬변수가 작은 경우, 2번으로 점프
      17: return
```
#### for-each
> 향상된 for문 (어셈블리어 부분은 추가 수정필요)

```
public static void main(String[] args) {
	
		int[] array = {1,2};
		for (int i : array) {
			System.out.println(i);
		}
		
	}
```
```
  Code:
       0: iconst_2					//int 2 피연산자 스택에 push
       1: newarray       int 		//int타입으로 피연산자 스택에서 pop된 값의 크기만큼의 배열 생성
       3: dup						//피연산자 스택의 최상위 값을 복사
       4: iconst_0					//int 0 피연산자 스택에 push
       5: iconst_1					//int 1 피연산자 스택에 push
       6: iastore					//int타입 배열에 피연산자 스택에서 pop된 값저장 //{1}이됨
       7: dup						//피연산자 스택의 최상위 값을 복사
       8: iconst_1					//int 1 피연산자 스택에 push
       9: iconst_2					//int 2 피연산자 스택에 push
      10: iastore					//int타입 배열에 피연산자 스택에서 pop된 값저장 //{2}이됨
      11: astore_1					//배열생성끝
      12: aload_1					//로컬변수1번(array)에서 배열 호출
      13: dup
      14: astore        5
      16: arraylength
      17: istore        4
      19: iconst_0					//int 0 피연산자 스택에 push
      20: istore_3
      21: goto          39
      24: aload         5
      26: iload_3
      27: iaload
      28: istore_2
      29: getstatic     #16                 // Field java/lang/System.out:Ljava/io/PrintStream;
      32: iload_2
      33: invokevirtual #22                 // Method java/io/PrintStream.println:(I)V
      36: iinc          3, 1
      39: iload_3					//로컬변수3번에서 값 호출하여 피연산자 스택에 push
      40: iload         4			//로컬변수4번에서 값 호출하여 피연산자 스택에 push
      42: if_icmplt     24			//로컬변수1번에서 값과 피연산자 스택에서 pop된 값을 비교하여 로컬변수가 작은 경우, 24번으로 점프
      45: return
```

과제(옵션)
+ 과제 0. JUnit 5 학습하세요.  
인텔리J, 이클립스, VS Code에서 JUnit 5로 테스트 코드 작성하는 방법에 익숙해 질 것.
이미 JUnit 알고 계신분들은 다른 것 아무거나!
더 자바, 테스트 강의도 있으니 참고하세요~
+ 과제 1. live-study 대시 보드를 만드는 코드를 작성하세요.  
깃헙 이슈 1번부터 18번까지 댓글을 순회하며 댓글을 남긴 사용자를 체크 할 것.
참여율을 계산하세요. 총 18회에 중에 몇 %를 참여했는지 소숫점 두자리가지 보여줄 것.
Github 자바 라이브러리를 사용하면 편리합니다.
깃헙 API를 익명으로 호출하는데 제한이 있기 때문에 본인의 깃헙 프로젝트에 이슈를 만들고 테스트를 하시면 더 자주 테스트할 수 있습니다.
+ 과제 2. LinkedList를 구현하세요.  
LinkedList에 대해 공부하세요.
정수를 저장하는 ListNode 클래스를 구현하세요.
ListNode add(ListNode head, ListNode nodeToAdd, int position)를 구현하세요.
ListNode remove(ListNode head, int positionToRemove)를 구현하세요.
boolean contains(ListNode head, ListNode nodeTocheck)를 구현하세요.
+ 과제 3. Stack을 구현하세요.  
int 배열을 사용해서 정수를 저장하는 Stack을 구현하세요.
void push(int data)를 구현하세요.
int pop()을 구현하세요.
+ 과제 4. 앞서 만든 ListNode를 사용해서 Stack을 구현하세요.  
ListNode head를 가지고 있는 ListNodeStack 클래스를 구현하세요.
void push(int data)를 구현하세요.
int pop()을 구현하세요.
+ 과제 5. Queue를 구현하세요.(optional)   
배열을 사용해서 한번
ListNode를 사용해서 한번.  
마감일시  
2020년 12월 12일 토요일 오후 1시까지.

[참고](https://kils-log-of-develop.tistory.com/349)
