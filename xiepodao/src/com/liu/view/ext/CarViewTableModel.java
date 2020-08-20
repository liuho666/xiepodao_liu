package com.liu.view.ext;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class CarViewTableModel extends DefaultTableModel {
    static Vector<String> colums = new Vector<>();
    static {
        colums.addElement("车辆编号");
        colums.addElement("车辆类型");
        colums.addElement("车辆型号");
        colums.addElement("司机姓名");
        colums.addElement("所属部门");
        colums.addElement("车牌号");
    }
    private CarViewTableModel(){
        super(null,colums);
    }
    private static CarViewTableModel carViewTableModel = new CarViewTableModel();

    public static CarViewTableModel assembleModel(Vector<Vector<Object>> data){
        carViewTableModel.setDataVector(data,colums);
        return carViewTableModel;
    }
    public static Vector<String> getColums(){
        return colums;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
