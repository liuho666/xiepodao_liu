package com.liu.handler;

import com.liu.entity.AdminDO;
import com.liu.service.AdminService;
import com.liu.service.impl.AdminServiceImpl;
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
        if (userName == null||"".equals(userName.trim())||password == null){
            JOptionPane.showMessageDialog(loginView.getjFrame(), "用户名密码必填");
            return;
        }
        String pwd = new String(password);
        System.out.println(userName + ":" + pwd);
        //查询db
        AdminServiceImpl adminService = new AdminServiceImpl();
        AdminDO adminDO = new AdminDO();
        adminDO.setUsername(userName);
        adminDO.setPassword(pwd);
        boolean flag = adminService.validateAdmin(adminDO);
        if (flag) {
            System.out.println("登录成功！");
        } else {
            JOptionPane.showMessageDialog(loginView.getjFrame(), "用户名密码错误");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()== KeyEvent.VK_ENTER){
            login();
        }
    }
}
