package dev.patika.VetClinic.core.result;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class Result {
    private boolean status;
    private String message;
    private String code;

    public Result(boolean status, String message, String code) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}
