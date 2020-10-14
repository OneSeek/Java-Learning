public enum TAOSErrorCode {

    UNKNOWN_ERROR(-1,"unknown error");

    int code;
    String massage;
    TAOSErrorCode(int code,String massage){
        this.code = code;
        this.massage = massage;
    }
}
//int short long byte double float  char boolean
