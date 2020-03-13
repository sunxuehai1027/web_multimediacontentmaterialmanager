package Constant;

public enum ErrorCode {

    ERROR_EMPTY_NO_SIGN(20000, "用户未登录"),
    ERROR_EMPTY_FILE_UPLOAD(20001, "不允许上传空文件"),
    ERROR_EMPTY_NOT_ADMIN(20000, "请登陆管理员账号");
    private String msg;
    private int code;

    ErrorCode() {
    }

    ErrorCode(int code, String msg) {
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
