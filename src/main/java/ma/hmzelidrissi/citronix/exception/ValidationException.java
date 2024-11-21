package ma.hmzelidrissi.citronix.exception;

import org.springframework.http.HttpStatus;

public class SuperficieException extends BaseException {
  public SuperficieException(String message) {
    super(message, HttpStatus.BAD_REQUEST);
  }
}
