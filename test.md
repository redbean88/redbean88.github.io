반각문자를 전각문자로 변환하기
private static String toFullChar(String src)
{
    if (src == null)    return null;
    StringBuffer strBuf = new StringBuffer();
    char c = 0;
    for (int i = 0; i < src.length(); i++)
    {
        c = src.charAt(i);
        // 영문 알파벳 이거나 특수 문자
        if (c >= 0x21 && c <= 0x7e)   c += 0xfee0;
        // 공백
        else if (c == 0x20)   c = 0x3000;
        strBuf.append(c);
    }
    return strBuf.toString();
}
전각문자를 반각문자로 변환하기
private static String toHalfChar(String src)
{
    StringBuffer strBuf = new StringBuffer();
    char c = 0;
    for (int i = 0; i < src.length(); i++)
    {
        c = src.charAt(i);
        //영문이거나 특수 문자 일경우.
        if (c >= '！' && c <= '～')   c -= 0xfee0;
        // 공백
        else if (c == '　')   c = 0x20;
        strBuf.append(c);
    }
    return strBuf.toString();
}
