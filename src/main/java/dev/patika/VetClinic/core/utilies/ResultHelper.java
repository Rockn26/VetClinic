package dev.patika.VetClinic.core.utilies;

import dev.patika.VetClinic.core.result.Result;
import dev.patika.VetClinic.core.result.ResultData;

public class ResultHelper {

    public static Result ok(){ // silme işleminde kullanacağım için data çıkarmama gerek yok
        return new Result(true, Msg.OK, "200");
    }

    public static <T> ResultData<T> created(T data) {
        return new ResultData<>(true, Msg.CREATED, "201", data);
    }

    public static <T> ResultData<T> success(T data) {
        return new ResultData<>(true, Msg.OK, "200", data);
    }

    public static <T> ResultData<T> badRequest(String message) {
        return new ResultData<>(false, message, "400", null);
    }

}
