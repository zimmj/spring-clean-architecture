package tech.zimmerli.clean.architecture.cleanarchitecture.house.representation.DTO;

import java.util.logging.Level;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.extern.java.Log;
import no.gorandalum.fluentresult.Result;
import tech.zimmerli.clean.architecture.cleanarchitecture.house.domain.Address;
import tech.zimmerli.clean.architecture.cleanarchitecture.house.domain.House;

@Data
@Log
public class CreateHouse {

    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Address is mandatory")
    private String address;
    @Min(value = 1L, message = "The value must be positive")
    private int Number;

    public Result<House, String> toHouse() {        
        return Address.create(address, Number)
        .consumeError(errMsg -> log.log(Level.WARNING, errMsg.toString() + "hello"))
        .flatMap(address -> House.create(address, name));
    }
}
