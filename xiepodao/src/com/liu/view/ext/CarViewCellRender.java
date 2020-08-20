package com.liu.view.ext;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CarViewCellRender extends DefaultTableCellRenderer {
    //在每一行每一列显示之前都会调用
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        //设置奇偶变色
        if (row%2==0){
            setBackground(Color.LIGHT_GRAY);
        }else{
            setBackground(Color.white);
        }
        //设置字体居中显示
        setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
