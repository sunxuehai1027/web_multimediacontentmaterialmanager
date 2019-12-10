package Util;

public class Constant {


    public final static String IP_ADDRESS = "http://111.231.167.250:8080";

    public static final String DOWNLOAD_HEAD_ADDRESS = IP_ADDRESS+"/examples";

    public static final String IMAGE_HEAD_ADDRESS = DOWNLOAD_HEAD_ADDRESS + "/image/";

    public static final String QRCODE_IMAGE_HEAD_ADDRESS = IMAGE_HEAD_ADDRESS + "/qrcode/";

    public static final String SOFTWARE_HEAD_ADDRESS = DOWNLOAD_HEAD_ADDRESS + "/software/";

    /**
     * 接收参数为空
     */
    public final static int ERROR_10001 = 10001;
    /**
     * 接收参数错误
     */
    public final static int ERROR_10002 = 10002;
    /**
     * 不合法的参数
     */
    public final static int ERROR_10003 = 10003;

}
