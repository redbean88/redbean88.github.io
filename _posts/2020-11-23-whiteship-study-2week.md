2 주차
  + -1 ~ 5 까지는 iconst_(숫자) 형식으로 처리가 된다. ( 예 : int 4 =>> icont_4)
                + -128 ~ 127 까지는 bipush를 사용한다
                + -32768 ~ 32767 까지는 sipush를 사용한다
                + 그외의 수는 constant pool을 이용한다