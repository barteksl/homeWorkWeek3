package pl.barss.service;

import org.springframework.stereotype.Component;
import pl.barss.model.Car;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CarService{

    private List<Car> carList;

    public CarService() {
        this.carList = new ArrayList<>();
        carList.add(new Car(1L,"Opel","Astra","Silver"));
        carList.add(new Car(2L,"Ford","Focus","Blue"));
        carList.add(new Car(3L,"Toyota","Yaris","Red"));
    }


    public List<Car> getCars(){
        return carList;
    }


    public Optional<Car> getCarById(long id){
        Optional<Car> first = carList.stream().filter(car -> car.getId() == id ).findFirst();
        return first;
    }


    public List<Car> getCarsByColour(String color){
        List<Car> cars = carList.stream()
                .filter(car -> car.getColor().toUpperCase().equals(color))
                .collect(Collectors.toList());
        System.out.println(cars);
        return cars;
    }


    public Boolean addCar(Car car){
        return carList.add(car);
    }


    public Boolean modCar(Car newCar){
        Optional<Car> first = carList.stream().filter( car -> car.getId() == newCar.getId() ).findFirst();
        if (first.isPresent()){
            carList.remove(first.get());
            carList.add(newCar);
            return true;
        }
        return false;
    }


    public Boolean deleteCarById(Long id){
        Optional<Car> first = carList.stream().filter( car -> car.getId() == id ).findFirst();
        if (first.isPresent()){
            carList.remove(first.get());
            return true;
        }
        return false;
    }
}
