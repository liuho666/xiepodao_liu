package com.liu.view.ext;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class WarnViewTableModel extends DefaultTableModel {
    static Vector<String> colums = new Vector<>();
    static {
        colums.addElement("序号");
        colums.addElement("报警信息");
        colums.addElement("故障时间");
    }
    private WarnViewTableModel(){
        super(null,colums);
    }
    //单例设计模式
    private static WarnViewTableModel warnViewTableModel = new WarnViewTableModel();
    //添加数据
    public static WarnViewTableModel assembleModel(Vector<Vector<Object>> data){
        warnViewTableModel.setDataVector(data,colums);
        return warnViewTableModel;
    }

    public static void updateModel(Vector<Vector<Object>> data){
        warnViewTableModel.setDataVector(data,colums);
    }
    public static Vector<String> getColums(){
        return colums;
    }
    //是否可编辑
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
