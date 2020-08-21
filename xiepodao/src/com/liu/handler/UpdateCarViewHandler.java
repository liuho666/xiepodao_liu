package com.liu.handler;

import com.liu.entity.CarDo;
import com.liu.service.CarService;
import com.liu.service.impl.CarServiceImpl;
import com.liu.view.AddCarView;
import com.liu.view.CarView;
import com.liu.view.UpdateCarView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateCarViewHandler implements ActionListener {
    private UpdateCarView updateCarView;
    private CarView carView;
    public UpdateCarViewHandler(UpdateCarView updateCarView, CarView carView){
        this.updateCarView = updateCarView;
        this.carView = carView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if ("修改".equals(text)){
            CarService carService = new CarServiceImpl();
            CarDo carDo = updateCarView.buildUpdatedCarDO();
            boolean addResult = carService.update(carDo);
            if (addResult){
                //重新加载表格查到最新数据
                carView.reloadTable();
                updateCarView.dispose();
            }else{
                JOptionPane.showMessageDialog(updateCarView,"修改失败！");
            }
        }
    }
}
