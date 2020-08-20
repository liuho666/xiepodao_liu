package com.liu.view;


import com.liu.view.ext.CarViewTable;
import com.liu.view.ext.CarViewTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class CarView extends JFrame {
    JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton addBtn = new JButton("增加");
    JButton updateBtn = new JButton("修改");
    JButton delBtn = new JButton("删除");
    JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton preBtn = new JButton("上一页");
    JButton nextBtn = new JButton("下一页");
    CarViewTable carViewTable = new CarViewTable();
    public CarView(){
        super("车辆信息管控");
        //内容面板
        Container contentPane = getContentPane();
        //放置北边的组件
        layoutNorth(contentPane);
        //设置中间的Jtable
        laoutCenter(contentPane);
        //放置南边的组件
        laoutSouth(contentPane);


        //自定义图标
        setIconImage(new ImageIcon("img//logo.png").getImage());

        //根据屏幕大小设置主界面大小
        //setBounds(DimensionUtil.getBounds());
        //设置窗体完全充满整个屏幕的可见大小
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(1000, 800);
        setLocationRelativeTo(null);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);
    }

    private void laoutCenter(Container contentPane) {
        Vector<Vector<Object>> data = new Vector<>();
        Vector<Object> rowVector1 = new Vector<>();
        rowVector1.addElement("01");
        rowVector1.addElement("铲运机");
        rowVector1.addElement("张三");
        rowVector1.addElement("冀B54622");
        Vector<Object> rowVector2 = new Vector<>();
        rowVector2.addElement("02");
        rowVector2.addElement("铲运机");
        rowVector2.addElement("李四");
        rowVector2.addElement("冀B54622");
        data.addElement(rowVector1);
        data.addElement(rowVector2);
        CarViewTableModel carViewTableModel = CarViewTableModel.assembleModel(data);
        carViewTable.setModel(carViewTableModel);
        carViewTable.renderRule();
        JScrollPane jScrollPane = new JScrollPane(carViewTable);
        contentPane.add(jScrollPane,BorderLayout.CENTER);
    }

    private void laoutSouth(Container contentPane) {
        southPanel.add(preBtn);
        southPanel.add(nextBtn);
        contentPane.add(southPanel,BorderLayout.SOUTH);
    }

    private void layoutNorth(Container contentPane) {
        northPanel.add(addBtn);
        northPanel.add(updateBtn);
        northPanel.add(delBtn);
        contentPane.add(northPanel,BorderLayout.NORTH);
    }
}
