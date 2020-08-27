package com.liu.components;

import javax.swing.*;
import java.awt.*;

public class MyButton {


    public static JButton getRedButton(int x,int y){
        ImageIcon icon = new ImageIcon("img//hong.png");
        //icon.setImage(icon.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT ));
        JButton jButton1 = new JButton();
        jButton1.setIcon(icon);
        jButton1.setBackground(new Color(255,255,255));
        jButton1.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255)));
        jButton1.setBounds(x,y,30,30);
        return jButton1;
    }
    public static JButton getGreenButton(int x,int y){
        ImageIcon icon = new ImageIcon("img//lv.png");
        JButton jButton1 = new JButton();
        jButton1.setIcon(icon);
        jButton1.setBackground(new Color(255,255,255));
        jButton1.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255)));
        jButton1.setBounds(x,y,30,30);
        return jButton1;
    }

    public static JButton getYellowButton(int x, int y){
        ImageIcon icon = new ImageIcon("img//yellow.png");
        JButton jButton1 = new JButton();
        jButton1.setIcon(icon);
        jButton1.setBackground(new Color(255,255,255));
        jButton1.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255)));
        jButton1.setBounds(x,y,30,30);
        return jButton1;
    }

    public static JLabel getCarButton1(int x,int y){
     ImageIcon icon = new ImageIcon("img//car1.png");
     JLabel jButton1 = new JLabel();
     jButton1.setIcon(icon);
     jButton1.setBounds(x,y,425,250);
     jButton1.setOpaque(false);
     return jButton1;
 }

    public static JLabel getCarButton2(int x,int y){
        ImageIcon icon = new ImageIcon("img//car2.png");
        JLabel jButton1 = new JLabel();
        jButton1.setIcon(icon);
        jButton1.setBounds(x,y,425,500);
        jButton1.setOpaque(false);
        return jButton1;
    }

    public static JButton getGreyButton(int x,int y){
        ImageIcon icon = new ImageIcon("img//mie.png");
        JButton jButton1 = new JButton();
        jButton1.setIcon(icon);
        jButton1.setBackground(new Color(255,255,255));
        jButton1.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255)));
        jButton1.setBounds(x,y,30,30);
        return jButton1;
    }






}
