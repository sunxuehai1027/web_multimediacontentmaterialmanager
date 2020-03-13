package Constant;

public enum TypeAndFolder {

    TYPE_FOLDER_UNKNOWN(-1, "unknown"),

    TYPE_FOLDER_TEXT(1, "text"),

    TYPE_FOLDER_PIC(2, "pic"),

    TYPE_FOLDER_VIDEO(3, "video"),

    TYPE_FOLDER_AUDIO(4, "audio"),

    TYPE_FOLDER_PRESS(5, "press");

    private String msg;
    private int code;


    TypeAndFolder(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }

    public int getCode() {
        return this.code;
    }

    public static String getMsgByCode(int code){
        for(TypeAndFolder typeAndFolder:TypeAndFolder.values()){
            if(typeAndFolder.code==code){
                return typeAndFolder.msg;
            }
        }
        return null;
    }
}
