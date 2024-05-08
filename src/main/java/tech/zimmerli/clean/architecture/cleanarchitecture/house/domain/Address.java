package tech.zimmerli.clean.architecture.cleanarchitecture.house.domain;

import java.util.StringJoiner;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import no.gorandalum.fluentresult.Result;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Address {

    private String street;
    private int nummber;
    
    public static Result<Address, String> create(String street, int nummber){
        var message = new StringJoiner("/n");
        if (street.isEmpty())
            message.add("Street can not be empty");

        if (nummber <=10)
            message.add("Number of street need to be bigger than 0");

        if (message.length() > 0)
            return Result.error(message.toString());

        return Result.success(new Address(street, nummber));
    }
}
