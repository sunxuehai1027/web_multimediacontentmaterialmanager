package Constant;

public class Constants {

    public static final String FILE_STORAGE_PATH_HEAD = "E://apache-tomcat-8.5.31/webapps/examples/";

    public static enum errorcode {

        ERROR_EMPTY_FILE_UPLOAD(20001,"不允许上传空文件");

        private String msg;
        private int code;

        private errorcode(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public String getMsg() {
            return this.msg;
        }

        public int getCode() {
            return this.code;
        }

    }
}
