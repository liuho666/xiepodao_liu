package com.liu.view.ext;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Vector;

public class CarViewTable extends JTable {
    //构造函数
    public CarViewTable(){
        JTableHeader tableHeader = getTableHeader();
        tableHeader.setFont(new Font(null,Font.BOLD,16));
        tableHeader.setForeground(Color.red);
        //设置表格体
        setFont(new Font(null,Font.PLAIN,14));
        setForeground(Color.black);
        setGridColor(Color.BLACK);
        setRowHeight(30);
        //设置多行选择
        getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }


    public void renderRule(){
        //设置表格的渲染方式
        Vector<String> columns = CarViewTableModel.getColums();
        CarViewCellRender render = new CarViewCellRender();
        for (int i = 0;i<columns.size();i++) {
            //表头列字段
            TableColumn column = getColumn(columns.get(i));
            //添加渲染方式
            column.setCellRenderer(render);
        }

    }
}
