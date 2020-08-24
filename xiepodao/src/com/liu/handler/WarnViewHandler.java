package com.liu.handler;

import com.liu.view.WarningView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WarnViewHandler implements ActionListener {
    private WarningView warningView;
    public WarnViewHandler(WarningView warningView){
        this.warningView=warningView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if ("上一页".equals(text)){
            warningView.setPageNow(warningView.getPageNow()-1);
        }else if ("下一页".equals(text)){
            warningView.setPageNow(warningView.getPageNow()+1);
        }
    }
}
