package musinsa.common.config;


public enum StatusEnum {

    OK(200, "OK"),
    BAD_REQUEST(400, "BAD_REQUEST"),
    NOT_FOUND(404, "NOT_FOUND"),
    NO_USER(405, "NO_USER"),
    NO_POINT(406, "NO_POINT"),
    INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR");

    int statusCode;
    String code;

    StatusEnum(int statusCode, String code) {
        this.statusCode = statusCode;
        this.code = code;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getCode() {
        return this.code;
    }
}
