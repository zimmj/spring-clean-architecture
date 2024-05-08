package tech.zimmerli.clean.architecture.cleanarchitecture.house.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import no.gorandalum.fluentresult.Result;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class House {

    private String name;
    private Address address;

    public static Result<House, String> create(Address address, String name) {
        if (name.isEmpty())
            return Result.error("Name can not be empty");
        return Result.success(new House(name, address));
    }
}
