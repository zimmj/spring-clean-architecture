package tech.zimmerli.clean.architecture.cleanarchitecture.house.useCases;

import java.util.ArrayList;
import java.util.Optional;

import tech.zimmerli.clean.architecture.cleanarchitecture.house.domain.House;

public interface IHouseRepository {
    ArrayList<House> find();
    Optional<House> get(String name);
    void delete(House house);
    void add(House house);
}
