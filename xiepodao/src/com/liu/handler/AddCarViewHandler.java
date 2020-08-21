package com.liu.handler;

import com.liu.entity.CarDo;
import com.liu.service.CarService;
import com.liu.service.impl.CarServiceImpl;
import com.liu.view.AddCarView;
import com.liu.view.CarView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCarViewHandler implements ActionListener {
    private AddCarView addCarView;
    private CarView carView;
    public AddCarViewHandler(AddCarView addCarView, CarView carView){
        this.addCarView = addCarView;
        this.carView = carView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if ("添加".equals(text)){
            CarService carService = new CarServiceImpl();
            CarDo carDo = addCarView.buildCarDO();
            boolean addResult = carService.add(carDo);
            if (addResult){
                //重新加载表格查到最新数据
                carView.reloadTable();
                addCarView.dispose();
            }else{
                JOptionPane.showMessageDialog(addCarView,"添加失败");
            }
        }
    }
}
