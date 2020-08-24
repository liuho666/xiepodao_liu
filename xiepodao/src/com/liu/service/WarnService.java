package com.liu.service;

import com.liu.entity.CarRequest;
import com.liu.entity.TableDTO;

public interface WarnService {
    TableDTO retrieveWarns(CarRequest carRequest);
}
