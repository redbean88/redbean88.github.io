---
title: 컴퓨터개론 13
date: 2021-02-23 00:00:00 +0000
categories: [CS]
---

# 명령어의 컴퓨터 내부표현

1. 명령의 해석방법
    - 명령어도 높고 낮은 전기신호의 연속이므로 숫자로 표현하는 것이 가능
    - 레지스터가 명령어에서 참조 되기 때문에 레지스터 이름을 숫자로 매핑하는 규칙이 존재
    - $s0 ~ $s7 == 16 ~ 23
    - $t0 ~ $t7 == 8 ~ 15

2. 어셈블리어의 기계어 변환
    - add $t0, $s1 $s2  
    ![명령어변환]()
    - 명령어의 각 부분을 필드(field)라고 함
    - 위와 같은 형식을 명령어 형식이라고 함
    - 기계어는 보통 16진수를 사용하여 표현함
    - op : 명령어가 실행할 연산의 종류로서 연산자(opcode)라고 한다
    - rs : 첫 번째 근원지(Source) operand 레지스터 (6bit)
    - rt : 두 번째 근원지 operand 레지스터 (5bit)
    - rd : 연산결과가 기억되는 목적지(Destination) 레지스터
    - shamt : 자리이동(shift)량 shift명령어를 배우기 전까지 0으로 사용 (5bit)
    - funct : op 코드에 표시된 연산의 구체적인 종류 지정
    - address : 16bit
3. 기계어 코드표
    |instruction|format|op|rs|rt|rd|shamt|funct|address|
    |:--|:--|:--|:--|:--|:--|:--|:--|:--|
    |add|R|0|reg|reg|reg|0|32|n/a|
    |sub|R|0|reg|reg|reg|0|34|n/a|
    |addi|I|8|reg|reg|n/a|n/a|n/a|constant|
    |lw|I|35|reg|reg|n/a|n/a|n/a|address|
    |sw|I|43|reg|reg|n/a|n/a|n/a|address|

4. 명령어 타입
    - R 타입 : 오른쪽 절반 필드가 3개일 경우
    - I 타입 : 오른쪽 절반 필드가 1개일 경우
    - address는 16비트 주소
    - 명령어 형식이 여러가지가 되면 복잡해지지만 유사하게 설계하므로써 복잡도를 낮춤
    - Q) A[300] = h + A[300]
    - A) lw $t0 , 1200($s3)  
    add $t0, $s3, $t0  
    sw $t0, 1200($s3)
    ![답]()
    