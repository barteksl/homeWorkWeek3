package pl.barss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.barss.model.Car;
import pl.barss.service.CarService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping()
    public ResponseEntity getAllCars(){
        return new ResponseEntity<List<Car>>(carService.getCars(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getCarById(@PathVariable Long id){
        Optional<Car> car = carService.getCarById(id);
        if(car.isPresent()){
            return new ResponseEntity(car.get(),HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<List<Car>> getCarsByColour(@PathVariable String color){
        List<Car> cars = carService.getCarsByColour(color.toUpperCase());
        return new ResponseEntity<List<Car>>(cars,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addCar(@RequestBody Car car){
        if (carService.addCar(car)) {
            return new ResponseEntity(HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCar(@PathVariable Long id){
        if(carService.deleteCarById(id)){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PutMapping
    public ResponseEntity modCar(@RequestBody Car car){
        if(carService.modCar(car)){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
