package com.liu.handler;

import com.liu.service.CarService;
import com.liu.service.impl.CarServiceImpl;
import com.liu.view.AddCarView;
import com.liu.view.CarView;
import com.liu.view.UpdateCarView;
import sun.applet.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarViewHandler implements ActionListener {
    private CarView carView;
    public CarViewHandler(CarView carView){
        this.carView = carView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if ("增加".equals(text)){
            new AddCarView(carView);
        }else if("修改".equals(text)){
            int[] selectedCarIds = carView.getSelectedCarIds();
            if(selectedCarIds.length!=1){
                JOptionPane.showMessageDialog(carView,"一次只能修改一行！");
                return;
            }
            new UpdateCarView(carView,selectedCarIds[0]);
        }else if ("删除".equals(text)){
            int[] selectedCarIds = carView.getSelectedCarIds();
            if (selectedCarIds.length==0){
                JOptionPane.showMessageDialog(carView,"请选择要删除的行！");
                return;
            }
            int option = JOptionPane.showConfirmDialog(carView, "你确认要删除选择的"
                            + selectedCarIds.length + "行吗?", "确认删除",
                    JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION){//确认
                //执行删除
                CarService carService = new CarServiceImpl();
                boolean deleteResult = carService.delete(selectedCarIds);
                if (deleteResult){
                    //重新加载表格查到最新数据
                    carView.reloadTable();
                }else{
                    JOptionPane.showMessageDialog(carView,"删除失败");
                }
            }
        }else if ("查询".equals(text)){
            carView.setPageNow(1);
            carView.reloadTable();
        }else if("上一页".equals(text)){
            carView.setPageNow(carView.getPageNow()-1);
            carView.reloadTable();
        }else if ("下一页".equals(text)){
            carView.setPageNow(carView.getPageNow()+1);
            carView.reloadTable();
        }
    }
}
