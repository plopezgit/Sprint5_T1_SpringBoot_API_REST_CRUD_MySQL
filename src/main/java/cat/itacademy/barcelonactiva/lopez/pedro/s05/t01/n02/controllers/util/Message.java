package cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.controllers.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private int responseCode;
    private String message;
    private String messageDescription;
    private Date responseTimestamp;
}
