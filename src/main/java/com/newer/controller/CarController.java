package com.newer.controller;

import com.newer.pojo.Car;
import com.newer.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://127.0.0.1:8080",maxAge = 3600)
public class CarController {

    @Autowired
    private CarService carService;

    /**
     * 查询所有车信息
     * @return
     */
    @RequestMapping(value = "car",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Car>> query(){
       List<Car> carList = carService.queryList();
       if(carList.isEmpty()){
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }
       return new ResponseEntity<>(carList,HttpStatus.OK);
    }


    /**
     * 查询所有车信息
     * @return
     */
    @RequestMapping(value = "car/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Car> queryById(@PathVariable("id")Integer id){
        Car car = carService.queryById(id);

        return new ResponseEntity<>(car,HttpStatus.OK);
    }

    @RequestMapping(value = "car",method = RequestMethod.POST)
    public ResponseEntity<?> insert(@RequestBody Car car){
        int count = carService.addCar(car);
        return new ResponseEntity<>(count,HttpStatus.OK);
    }

    @RequestMapping(value = "car",method = RequestMethod.PUT)
    public ResponseEntity<?> upd(@RequestBody Car car){
        int count = carService.updCar(car);
        return new ResponseEntity<>(count,HttpStatus.OK);
    }

    @RequestMapping(value = "car/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<?> delCar(@PathVariable("id")Integer id){
        int count = carService.delCar(id);
        return new ResponseEntity<>(count,HttpStatus.OK);
    }
}
