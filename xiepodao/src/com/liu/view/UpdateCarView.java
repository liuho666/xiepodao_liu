package com.liu.view;

import com.liu.entity.CarDo;
import com.liu.handler.UpdateCarViewHandler;
import com.liu.service.CarService;
import com.liu.service.impl.CarServiceImpl;

import javax.swing.*;
import java.awt.*;

public class UpdateCarView extends JDialog {
    JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,20));
    JLabel bianhaoLable = new JLabel("车辆编号",JLabel.RIGHT);
    JTextField bianhaoTxt = new JTextField();
    JLabel leixingLable = new JLabel("车辆类型",JLabel.RIGHT);
    JTextField leixingTxt = new JTextField();
    JLabel xinghaoLable = new JLabel("车辆型号",JLabel.RIGHT);
    JTextField xinghaoTxt = new JTextField();
    JLabel sijiLable = new JLabel("司机姓名",JLabel.RIGHT);
    JTextField sijiTxt = new JTextField();
    JLabel bumenLable = new JLabel("所属部门",JLabel.RIGHT);
    JTextField bumenTxt = new JTextField();
    JLabel carhaoLable = new JLabel("车牌号",JLabel.RIGHT);
    JTextField carhaoTxt = new JTextField();
    JButton updateBtn = new JButton("修改");
    UpdateCarViewHandler updateCarViewHandler;
    public UpdateCarView(CarView carView , int selectedCarId){
        super(carView,"修改车辆信息",true);
        updateCarViewHandler = new UpdateCarViewHandler(this,carView);
        //查询selectedCarId对应的记录并回显
        CarService carService = new CarServiceImpl();
        CarDo selectedCar = carService.getCarById(selectedCarId);
        //车辆编号
        bianhaoLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(bianhaoLable);
        bianhaoTxt.setPreferredSize(new Dimension(200,30));
        bianhaoTxt.setText(selectedCar.getId()+"");
        //设置id不可编辑
        bianhaoTxt.setEnabled(false);
        jPanel.add(bianhaoTxt);

        //车辆类型
        leixingLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(leixingLable);
        leixingTxt.setPreferredSize(new Dimension(200,30));
        leixingTxt.setText(selectedCar.getLeixing());
        jPanel.add(leixingTxt);
        //车辆型号
        xinghaoLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(xinghaoLable);
        xinghaoTxt.setPreferredSize(new Dimension(200,30));
        xinghaoTxt.setText(selectedCar.getXinghao());
        jPanel.add(xinghaoTxt);
        //司机姓名
        sijiLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(sijiLable);
        sijiTxt.setPreferredSize(new Dimension(200,30));
        sijiTxt.setText(selectedCar.getSiji());
        jPanel.add(sijiTxt);
        //所属部门
        bumenLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(bumenLable);
        bumenTxt.setPreferredSize(new Dimension(200,30));
        bumenTxt.setText(selectedCar.getBumen());
        jPanel.add(bumenTxt);
        //车牌号
        carhaoLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(carhaoLable);
        carhaoTxt.setPreferredSize(new Dimension(200,30));
        carhaoTxt.setText(selectedCar.getCarhao());
        jPanel.add(carhaoTxt);
        updateBtn.addActionListener(updateCarViewHandler);
        jPanel.add(updateBtn);
        Container contentPane = getContentPane();
        contentPane.add(jPanel);

        setSize(350,400);
        setLocationRelativeTo(null);
        //DISPOSE_ON_CLOSE 只销毁当前窗体
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    //获取修改后的学生对象
    public CarDo buildUpdatedCarDO() {
        CarDo carDo = new CarDo();
        carDo.setId(Integer.parseInt(bianhaoTxt.getText()));
        carDo.setLeixing(leixingTxt.getText());
        carDo.setXinghao(xinghaoTxt.getText());
        carDo.setSiji(sijiTxt.getText());
        carDo.setBumen(bumenTxt.getText());
        carDo.setCarhao(carhaoTxt.getText());
        return carDo;
    }
}
