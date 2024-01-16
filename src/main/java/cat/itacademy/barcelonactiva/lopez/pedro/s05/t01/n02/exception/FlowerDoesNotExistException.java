package cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FlowerDoesNotExistException extends Exception {
    private String message;
}
