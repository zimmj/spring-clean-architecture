package tech.zimmerli.clean.architecture.cleanarchitecture.house.representation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tech.zimmerli.clean.architecture.cleanarchitecture.house.domain.House;
import tech.zimmerli.clean.architecture.cleanarchitecture.house.representation.DTO.CreateHouse;
import tech.zimmerli.clean.architecture.cleanarchitecture.house.useCases.IHouseRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController()
@RequestMapping("/houses")
public class HouseController {


    @Autowired
    IHouseRepository repository;

    @GetMapping()
    public List<House> Get() {
        return repository.find();
    }
    
    @PostMapping()
    public ResponseEntity<?> add(@RequestBody @Valid CreateHouse createHouse) {
        var houseResult = createHouse.toHouse()
        .consume(repository::add);
        return houseResult.fold(
            house -> ResponseEntity.ok().body(
                repository.find()
            ), error ->  ResponseEntity.badRequest().body(error));
    }
    
}
