package tech.zimmerli.clean.architecture.cleanarchitecture.house.infrastructure;

import java.util.ArrayList;
import java.util.Optional;

import tech.zimmerli.clean.architecture.cleanarchitecture.house.domain.House;
import tech.zimmerli.clean.architecture.cleanarchitecture.house.useCases.IHouseRepository;

public class HouseRepository implements IHouseRepository{

    private static ArrayList<House> houses = new ArrayList<>();

    @Override
    public ArrayList<House> find() {
        return houses;
    }

    @Override
    public Optional<House> get(String name) {
        return houses.stream().filter(house -> house.getAddress().getStreet().equalsIgnoreCase(name) ).findFirst();
    }

    @Override
    public void delete(House house) {
        houses.remove(house);
    }

    @Override
    public void add(House house) {
        houses.add(house);
    }

}
