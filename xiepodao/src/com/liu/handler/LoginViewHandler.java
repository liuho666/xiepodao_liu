package com.liu.handler;

import com.liu.view.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginViewHandler extends KeyAdapter implements ActionListener {
    private LoginView loginView;
    public LoginViewHandler(LoginView loginView){
        this.loginView = loginView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if ("重置".equals(text)){
            System.out.println("重置");
            loginView.getUserNameJTextField().setText("");
            loginView.getPwdJPasswordField().setText("");
        }else if ("登录".equals(text)){
            login();
        }
    }
    private void login() {
        String userName = loginView.getUserNameJTextField().getText();
        char[] password = loginView.getPwdJPasswordField().getPassword();
        String pwd = new String(password);
        System.out.println(userName + ":" + pwd);

        boolean flag = false;
        if (flag) {

        } else {

        }
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()== KeyEvent.VK_ENTER){
            login();
        }
    }
}
