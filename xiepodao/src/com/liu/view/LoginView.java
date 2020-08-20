package com.liu.view;

import com.liu.handler.LoginViewHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginView extends JFrame{
    // 定义登录界面的组件
    // 北部
    private JLabel titleJLabel = new JLabel("威程科技车辆管控系统",SwingConstants.CENTER);

    // 中部
    private JLabel userNameJLabel = new JLabel("用户名:");
    private JTextField userNameJTextField = new JTextField();

    private JLabel pwdJLabel = new JLabel("密码:");
    private JPasswordField pwdJPasswordField = new JPasswordField();



    private JButton loginBtn = new JButton("登录");
    private JButton resetBtn = new JButton("重置");

    SpringLayout formLayout = new SpringLayout();
    private JPanel formPanel = new JPanel(formLayout);

    private JFrame jFrame = new JFrame("学生成绩管理系统");

    // 托盘
    private TrayIcon trayIcon;
    private SystemTray systemTray;

    //定义点击事件
    private LoginViewHandler loginViewHandler;

    public LoginView(){
        loginViewHandler = new LoginViewHandler(this);
        // 北部
        Font font = new Font("华文行楷", Font.PLAIN, 40);
        titleJLabel.setFont(font);
        titleJLabel.setPreferredSize(new Dimension(0, 80));

        // 中部
        Font centerFont = new Font("楷体", Font.PLAIN, 20);
        userNameJLabel.setFont(centerFont);
        formPanel.add(userNameJLabel);
        userNameJTextField.setFont(centerFont);
        userNameJTextField.setPreferredSize(new Dimension(200, 30));
        formPanel.add(userNameJTextField);

        pwdJLabel.setFont(centerFont);
        formPanel.add(pwdJLabel);

        pwdJPasswordField.setPreferredSize(new Dimension(200,30));
        formPanel.add(pwdJPasswordField);

        loginBtn.setFont(centerFont);
        resetBtn.setFont(centerFont);
        loginBtn.addActionListener(loginViewHandler);
        //添加回车点击事件
        loginBtn.addKeyListener(loginViewHandler);
        formPanel.add(loginBtn);
        resetBtn.addActionListener(loginViewHandler);
        formPanel.add(resetBtn);
        // 设置各个组件的弹簧布局位置

        // 设置用户这一行
        Spring childrenWidth = Spring.sum(Spring.sum(Spring.width(userNameJLabel),
                Spring.width(userNameJTextField)), Spring.constant(10));
        int offset = childrenWidth.getValue() / 2;
        formLayout.putConstraint(SpringLayout.WEST, userNameJLabel, -offset,
                SpringLayout.HORIZONTAL_CENTER, formPanel);
        formLayout.putConstraint(SpringLayout.WEST, userNameJTextField, 10,
                SpringLayout.EAST, userNameJLabel);

        formLayout.putConstraint(SpringLayout.VERTICAL_CENTER, userNameJLabel, -80,
                SpringLayout.VERTICAL_CENTER, formPanel);
        formLayout.putConstraint(SpringLayout.NORTH, userNameJTextField, 0,
                SpringLayout.NORTH, userNameJLabel);


        // 设置密码这一行
        SpringLayout.Constraints uNameLabelC = formLayout.getConstraints(userNameJLabel);
        SpringLayout.Constraints pwdLabelC = formLayout.getConstraints(pwdJLabel);
        pwdLabelC.setConstraint(SpringLayout.NORTH,Spring.sum(
                uNameLabelC.getConstraint(SpringLayout.SOUTH),Spring.constant(30)
        ));
        pwdLabelC.setConstraint(SpringLayout.EAST,uNameLabelC.getConstraint(SpringLayout.EAST));

        SpringLayout.Constraints pwdTextC = formLayout.getConstraints(pwdJPasswordField);
        pwdTextC.setConstraint(SpringLayout.WEST,Spring.sum(pwdLabelC.getConstraint(SpringLayout.EAST),
                Spring.constant(10)));
        pwdTextC.setConstraint(SpringLayout.NORTH,pwdLabelC.getConstraint(SpringLayout.NORTH));

        formLayout.putConstraint(SpringLayout.WEST,loginBtn,20,SpringLayout.WEST,pwdJLabel);
        formLayout.putConstraint(SpringLayout.NORTH,loginBtn,20,SpringLayout.SOUTH,pwdJLabel);

        formLayout.putConstraint(SpringLayout.WEST,resetBtn,50,SpringLayout.EAST,loginBtn);
        formLayout.putConstraint(SpringLayout.NORTH,resetBtn,0,SpringLayout.NORTH,loginBtn);
        //设置loginBtn为默认按钮
        jFrame.getRootPane().setDefaultButton(loginBtn);
        //自定义图标
        jFrame.setIconImage(new ImageIcon("img//logo.png").getImage());
        Container contentPane = jFrame.getContentPane();
        // 定义根容器布局
        contentPane.setLayout(new BorderLayout());

        contentPane.add(titleJLabel,BorderLayout.NORTH);
        contentPane.add(formPanel,BorderLayout.CENTER);

        // 托盘设置
        if (SystemTray.isSupported()) {
            systemTray = SystemTray.getSystemTray();
            trayIcon = new TrayIcon(new ImageIcon("img//logo.png").getImage());
            // 设置托盘图标自适应大小，不然托盘显示不出图片
            trayIcon.setImageAutoSize(true);
            try {
                systemTray.add(trayIcon);
            } catch (AWTException e) {
                e.printStackTrace();
            }
            jFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowIconified(WindowEvent e) {
                    jFrame.dispose();
                }
            });

            trayIcon.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 1) {
                        jFrame.setExtendedState(JFrame.NORMAL);
                    }
                    jFrame.setVisible(true);
                }
            });
        }

        jFrame.setSize(600,400);
        jFrame.setLocationRelativeTo(null);// 居中展示
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setResizable(true);
        jFrame.setVisible(true);
    }


    public static void main(String[] args) {
        new LoginView();
    }

    public JTextField getUserNameJTextField() {
        return userNameJTextField;
    }

    public JPasswordField getPwdJPasswordField() {
        return pwdJPasswordField;
    }

    public JFrame getjFrame() {
        return jFrame;
    }
}
