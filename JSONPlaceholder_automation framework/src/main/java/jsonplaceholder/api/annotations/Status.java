package jsonplaceholder.api.annotations;
import lombok.Getter;
import org.apache.http.HttpStatus;

@Getter
public enum Status {

    NOT_FOUND(HttpStatus.SC_NOT_FOUND),
    TOO_LARGE(HttpStatus.SC_REQUEST_TOO_LONG)
    ;

    private final int status;


    Status(int status) {

        this.status = status;
    }
}
