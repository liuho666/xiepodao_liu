package com.liu.service;

import com.liu.entity.CarDo;
import com.liu.entity.CarRequest;
import com.liu.entity.TableDTO;

public interface CarService {
    TableDTO retrieveCars(CarRequest carRequest);

    boolean add(CarDo carDo);

    CarDo getCarById(int selectedCarId);

    boolean update(CarDo carDo);

    boolean delete(int[] selectedCarIds);
}
