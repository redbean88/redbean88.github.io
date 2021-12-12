---
title : "자바스터디 3주차"
date : 2020-11-23
categories : [study]
---

# GOAL
> 자바가 제공하는 다양한 연산자를 학습하세요.

1. 학습할 것
1. 산술 연산자
1. 비트 연산자
1. 관계 연산자
1. 논리 연산자
2. instanceof
3. assignment(=) operator
4. 화살표(->) 연산자
5. 3항 연산자
6. 연산자 우선 순위
7. (optional) Java 13. switch 연산자

### 산술 연산자

|산술 연산자|설명|
|:---:|---|
|+|연산자를 기준으로 우항과 좌항의 합연산을 수행합니다|
|-|연산자를 기준으로 우항과 좌항의 차연산을 수행합니다|
|*|연산자를 기준으로 우항과 좌항의 곱연산을 수행합니다|
|/|연산자를 기준으로 좌항을 우항으로 나누기 연산을 수행합니다. 우항이 0일 경우, 예외를 발생 합니다.|
|%|연산자를 기준으로 좌항을 우항으로 나눈 나머지 연산을 수행합니다. 우항이 0일 경우, 예외를 발생 합니다.|

> 나눗셈 연산
단순하게 나머지 연산만 수행 했을 경우, 소수점표기가 되지 않으며, 소수점 연산이 필요한경우, 
1. 좌,우항중 하나를 실수로 표기한후, double로 받아 표기.
2. java.math의 BigDecimal 이용 (추천)
[관련 링크](https://mainia.tistory.com/2020)

> 숫자 승격
자바의 레퍼선스 객체의 오토박싱과 유사한 처리 상세내용은 링크를 확인해 보자
[숫자승격](https://kils-log-of-develop.tistory.com/336)

<iframe height="800px" width="100%" src="https://repl.it/@redbean1/Arithmetic?lite=true" scrolling="no" frameborder="no" allowtransparency="true" allowfullscreen="true" sandbox="allow-forms allow-pointer-lock allow-popups allow-same-origin allow-scripts allow-modals"></iframe>

### 비트연산자

|비트 연산자|설명|
|:---:|---|
|&|연산자를 기준으로 우항과 좌항의 대응되는 비트가 모두 1이면 1을 반환. (AND 연산)|
|\\||연산자를 기준으로 우항과 좌항의 대응되는 비트 중에서 하나라도 1이면 1을 반환. (OR 연산)|
|^|연산자를 기준으로 우항과 좌항의 대응되는 비트가 서로 다르면 1을 반환. (XOR 연산)|
|~|피연산자의 비트를 1이면 0, 0이면 1로 반전. (NOT 연산, 1의 보수)|
|<<|연산자를 기준으로 우항의 수만큼 좌항의 비트를 모두 왼쪽으로 이동. (Left shift 연산)|
|>>|연산자를 기준으로 우항의 수만큼 좌항의 비트를 모두 오른쪽으로 이동. (Right shift 연산)|

> 사용이유
비트연산의 경우 , 연산 속도가 빠름

<iframe height="800px" width="100%" src="https://repl.it/@redbean1/bit?lite=true" scrolling="no" frameborder="no" allowtransparency="true" allowfullscreen="true" sandbox="allow-forms allow-pointer-lock allow-popups allow-same-origin allow-scripts allow-modals"></iframe>

# 관계 연산자 (Relational operator, (비교 연산자, Comparison operator))

반환 값은 항상, boolean 타입이다
동일성을 보장하며, 동등성을 보장하지 않는다.(String,배열, 레퍼런스 객체등)

> 동일성 vs 동등성
동일성 : 메모리 주소값의 정보가 같음
동등성 : 객체의 정보가 같음

|관계 연산자|설명|
|:---:|---|
|==|연산자를 기준으로 우항과 좌항이 같으면 true|
|!=|연산자를 기준으로 우항과 좌항이 다르면 true|
|>|연산자를 기준으로 좌항이 우항보다 크면 true|
|>=|연산자를 기준으로 좌항이 우항보다 더 크거나 같으면 true|
|<|연산자를 기준으로 우항이 좌항보다 크면 true.|
|<=|연산자를 기준으로 우항이 좌항보다 왼쪽보다 더 크거나 같으면 true.|

<iframe height="800px" width="100%" src="https://repl.it/@redbean1/compare?lite=true" scrolling="no" frameborder="no" allowtransparency="true" allowfullscreen="true" sandbox="allow-forms allow-pointer-lock allow-popups allow-same-origin allow-scripts allow-modals"></iframe>

# 논리 연산자 (Logical operator)

|논리 연산자|설명|
|:---:|---|
|&&|모두 참이면 true (AND 연산)|
|&#124;&#124;| 하나라도 참이면 true (OR 연산)|
|!|논리 반전 (true > false , false > true) (NOT 연산)|

<iframe height="800px" width="100%" src="https://repl.it/@redbean1/Comparison?lite=true" scrolling="no" frameborder="no" allowtransparency="true" allowfullscreen="true" sandbox="allow-forms allow-pointer-lock allow-popups allow-same-origin allow-scripts allow-modals"></iframe>

# instanceof
- 참조 변수가 참조하고 있는 인스턴스의 실제 타입을 반환. 
- 실재 JVM에서는 null이 아닌 class , array, interface 의 경우 아래와 같이 처리 후, 참인경우 1 , 거짓인 경우 0을 operand 스택에 쌓는다(원문 push라고 써있음).
- 아래는 1을 반환하는 경우
    + 좌항이 array가 아닌 클래스인 경우
        * 우항이 클래스라면, instanceof 연산자를 기준으로 좌항은 같은 클래스 이거나, 서브클래스인 경우
        * 우항이 인터페이스라면, instanceof 연산자를 기준으로 좌항이 인터페이스를 구현(implement) 한 경우
    + 좌항이 interface인 경우
        * instanceof 연산자를 기준으로 우항은 반드시 Object 클래스인 경우
        * instanceof 연산자를 기준으로 우항과 같은 인터페이스 혹은 우항의 서브인터페이스 경우
    + 좌항이 array인 경우
        * 우항이 클래스라면, instanceof 연산자를 기준으로 우항은 반드시 Object 클래스인 경우
        * 우항이 인터페이스라면, instanceof 연산자를 기준으로 우항은 array를 구현하는 구현체 중 하나인 경우
        * 우항이 array라면, 아래의 경우에 만족할 경우
            - 좌항과 우항이 같은 원시타입일 경우
            - 좌항과 우항이 같은 레퍼런트 타입인 경우

<iframe height="800px" width="100%" src="https://repl.it/@redbean1/InstanceOf?lite=true" scrolling="no" frameborder="no" allowtransparency="true" allowfullscreen="true" sandbox="allow-forms allow-pointer-lock allow-popups allow-same-origin allow-scripts allow-modals"></iframe>

# assignment(=) operator
- 연산자 기준으로, 좌항에 우항을 할당 (이때, 값을 **복사**하는것이 아닌, 메모리값을**할당**하는것)

<iframe height="800px" width="100%" src="https://repl.it/@redbean1/copy?lite=true" scrolling="no" frameborder="no" allowtransparency="true" allowfullscreen="true" sandbox="allow-forms allow-pointer-lock allow-popups allow-same-origin allow-scripts allow-modals"></iframe>

# 화살표(->) 연산자
자바 8버전 부터 지원하는 익명함수 생성 방법, 단일 함수 인터페이스를 구현하는데 사용 가능 (@FunctionalInterface를 통해 단일함수 인터페이스를 강제 가능)

<iframe height="800px" width="100%" src="https://repl.it/@redbean1/lamda?lite=true" scrolling="no" frameborder="no" allowtransparency="true" allowfullscreen="true" sandbox="allow-forms allow-pointer-lock allow-popups allow-same-origin allow-scripts allow-modals"></iframe>

# 3항 연산자
표현식 : [condition] ? [true_value] : [false_value] 
간략한 문장으로 줄일때 사용하면 유용한 방법, 지나치게 사용할 경우 코드 복잡도는 높힐수 있음

<iframe height="800px" width="100%" src="https://repl.it/@redbean1/TernaryOperator?lite=true" scrolling="no" frameborder="no" allowtransparency="true" allowfullscreen="true" sandbox="allow-forms allow-pointer-lock allow-popups allow-same-origin allow-scripts allow-modals"></iframe>

# 연산자 우선 순위
~~싫어하는 동료가 있다면, 괄호를 안쓰면 스트레스를 줄수 있다.~~

|우선 순위|연산자|설명|
|:---:|---|---|
|1|expr++ ㅤ expr--|후위 증가/감소 연산자|
|2|++expr ㅤ --expr ㅤ +expr ㅤ -expr ㅤ ~ ㅤ !|전위 증가/감소 연산자, 양/음 부호, 논리/비트 NOT 연산자|
|3|* ㅤ / ㅤ %|곱셈/나눗셈/나머지 연산자|
|4|+ ㅤ -|덧셈/뺄셈 연산자|
|5|<< ㅤ >> ㅤ >>>|비트 왼쪽/오른쪽 Shift 연산자, Unsigned Shift 연산자|
|6|< ㅤ > ㅤ <= ㅤ >= ㅤ instanceof|보다 작은/보다 큰/보다 작거나 같은/보다 크거나 같은 관계 연산자, instanceof|
|7|== ㅤ !=|와 같은/와 다른 관계 연산자|
|8|&|비트 AND 연산자|
|9|^|비트 XOR 연산자|
|10|\\||비트 OR 연산자|
|11|&&|논리 AND 연산자|
|12|\\|\\||논리 OR 연산자|
|13|? :|삼항 조건 연산자|
|14|= ㅤ += ㅤ -= ㅤ *= ㅤ /= ㅤ %= ㅤ &= ㅤ ^= ㅤ \\|= ㅤ <<= ㅤ >>= ㅤ >>>=|대입 연산자 및 복합 대입 연산자|

# 참고자료

[3주차 과체 : nimkoes](https://blog.naver.com/hsm622/221017369794)
[3주차 과체 : gtpe](https://github.com/gtpe/java-study/blob/master/w3.md)
[오라클 레퍼런스](https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html)

