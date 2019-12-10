package Util;

public class CharacterUtil {


    /**
     * 字符串分割，将获取到的图片根据逗号分割拼接上前缀地址
     * @param str
     * @param condition
     * @return
     */
    public static String divideStringByCondition(String str, String condition) {
        if (str == null || condition == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        String[] strarray = str.split(condition);
        for (int i = 0; i < strarray.length; i++) {
            sb.append(i==strarray.length-1?Constant.IMAGE_HEAD_ADDRESS + strarray[i]:Constant.IMAGE_HEAD_ADDRESS + strarray[i]+condition);
            System.out.println("CharacterUtil:" + sb.toString());
        }
        return sb.toString();
    }
}
