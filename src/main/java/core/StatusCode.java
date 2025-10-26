package core;

public enum StatusCode {

    SUCCESS(200, "The request succeeded"),
    CREATED(201, "A New Resource was created"),
    BAD_REQUEST(400, "Missing Required field:Name"),
    UNAUTHORIZED(401, "Invalid Access token"),
    NOT_Found(404, "Cannot find Requested Resource"),
    NO_CONTENT(204, "NO content to send in the response body");

    public final int code;
    public final String message;
    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
