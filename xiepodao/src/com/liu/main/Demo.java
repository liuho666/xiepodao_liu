package com.liu.main;

import com.liu.components.MyButton;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.*;
import java.util.*;
import java.util.regex.Pattern;

public class Demo {

    static ServerSocket server = null;
    private static boolean flag = true;
    static boolean sel_flag = true;
    static Socket socket = null;
    static JButton jb_jianting;
    static java.io.InputStream inputStream = null;
    static java.io.OutputStream out = null;
    static JButton redButton1 = null;
    static JButton greenButton1 = null;
    static JButton yellowButton1 = null;
    static JButton redButton2 = null;
    static JButton greenButton2 = null;
    static JButton yellowButton2 = null;
    static JButton redButton3 = null;
    static JButton greenButton3 = null;
    static JButton yellowButton3 = null;
    static JButton redButton4 = null;
    static JButton greenButton4 = null;
    static JButton yellowButton4 = null;
    static JButton redButton5 = null;
    static JButton greenButton5 = null;
    static JButton yellowButton5 = null;
    static JButton redButton6 = null;
    static JButton greenButton6 = null;
    static JButton yellowButton6 = null;
    static JButton redButton_200top = null;
    static JButton greenButton_200top = null;
    static JButton yellowButton_200top = null;
    static JButton redButton_200south = null;
    static JButton greenButton_200south = null;
    static JButton yellowButton_200south = null;
    static JButton carButton1 = null;
    static JButton carButton2 = null;
    static JPanel jPanel_center = null;
    static ImageIcon RedIcon = new ImageIcon("img//hong.png");
    static ImageIcon GreenIcon = new ImageIcon("img//lv.png");
    static ImageIcon GrayIcon = new ImageIcon("img//mie.png");
    static ImageIcon YellowIcon = new ImageIcon("img//yellow.png");
    static Map<Integer,String> map = new HashMap<>();
    static String carnumber_one = null;//记录200区段a读卡
    static String carnumber_two = null;//记录200区段c读卡
    static String cuoche_200 ;
    static boolean carnumber_one_falg = false;
    static boolean carnumber_two_falg = false;
    static String car_200 = "00";//初始为00
    static JLabel car_numberJB = null;
    static JLabel carId_top = null;
    static JLabel carId_200 = null;
    static JLabel carId_south = null;
    public static void main(String[] agrs) {
        Font font = new Font("黑体", Font.PLAIN, 18);// 创建1个字体实例

        JFrame jf=new JFrame("井下斜坡道");    //创建Frame窗口
        jf.setIconImage(new ImageIcon("img//logo.png").getImage());
        jf.setSize(400,200);
        jf.setLayout(new BorderLayout());    //为Frame窗口设置布局为BorderLayout

        JPanel jPanel_left=new JPanel();
       //中间中部容器
        Image image=new ImageIcon("img//beijing.png").getImage();
        jPanel_center = new BackgroundPanel(image);
        // 设置中部容器空布局，即绝对布局
        jPanel_center.setOpaque(false);
        jPanel_center.setLayout(null);
        Font jinggao_font = new Font("黑体", Font.PLAIN, 25);
        car_numberJB = new JLabel("当前巷道中车辆数量:0");
        carId_top = new JLabel("从-170/-185处进车的车辆编号:");
        carId_200 = new JLabel("从-200处进车的车辆编号:");
        carId_south = new JLabel("从-215/-230/-245处进车的车辆编号:");
        car_numberJB.setFont(jinggao_font);
        carId_top.setFont(jinggao_font);
        carId_200.setFont(jinggao_font);
        carId_south.setFont(jinggao_font);
        car_numberJB.setBounds(50,100,500,50);
        carId_top.setBounds(50,200,500,50);
        carId_200.setBounds(50,300,500,50);
        carId_south.setBounds(50,400,500,50);
        jPanel_center.add(car_numberJB);
        jPanel_center.add(carId_top);
        jPanel_center.add(carId_200);
        jPanel_center.add(carId_south);

        //在北京图片上放置红绿灯素材
        redButton1 = MyButton.getGreyButton(1050,60);
        greenButton1 = MyButton.getGreenButton(1080, 60);
        yellowButton1 = MyButton.getGreyButton(1110, 60);
        redButton2 = MyButton.getGreyButton(940, 210);
        greenButton2 = MyButton.getGreenButton(970, 210);
        yellowButton2 = MyButton.getGreyButton(1000, 210);
        //200 错车上部
        redButton_200top = MyButton.getGreyButton(735,315);
        greenButton_200top = MyButton.getGreenButton(765,315);
        yellowButton_200top = MyButton.getGreyButton(795,315);
        //200处
        redButton3 = MyButton.getGreyButton(820, 355);
        greenButton3 = MyButton.getGreenButton(850, 355);
        yellowButton3 = MyButton.getGreyButton(880, 355);
        //200错车下部
        redButton_200south = MyButton.getGreyButton(640,425);
        greenButton_200south = MyButton.getGreenButton(670,425);
        yellowButton_200south = MyButton.getGreyButton(700,425);



        redButton4 = MyButton.getGreyButton(690, 503);
        greenButton4 = MyButton.getGreenButton(720, 503);
        yellowButton4 = MyButton.getGreyButton(750, 503);
        redButton5 = MyButton.getGreyButton(560, 650);
        greenButton5 = MyButton.getGreenButton(590, 650);
        yellowButton5 = MyButton.getGreyButton(620, 650);
        redButton6 = MyButton.getGreyButton(430, 797);
        greenButton6 = MyButton.getGreenButton(460, 797);
        yellowButton6 = MyButton.getGreyButton(490, 797);

        carButton1 = MyButton.getCarButton(880, 150);
        carButton2 = MyButton.getCarButton(510, 580);

        carButton1.hide();
        carButton2.hide();
        //布局设置边框，左侧，中部容器
        jPanel_left.setBorder(BorderFactory.createBevelBorder(1));
        jPanel_center.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,5));
        //左侧布局设置宽度，高度
        jPanel_left.setPreferredSize(new Dimension(300, 150));


        // =========================服务端ip地址===============================
        //左侧上部组件
        JPanel left_top = new JPanel(new BorderLayout());
        left_top.setBackground(new Color(250, 250, 210));
        left_top.setPreferredSize(new Dimension(280, 150));
        JPanel left_top_text = new JPanel();
        left_top_text.setBackground(Color.white);
        JLabel jl_left_top_text = new JLabel("连接主站IP");
        JLabel jl_text = new JLabel();
        Font font3 = new Font("黑体", Font.PLAIN, 16);// 创建1个字体实例
        jl_left_top_text.setFont(font3);
        jl_text.setPreferredSize(new Dimension(21, 19));
        jl_text.setIcon(new ImageIcon("img//sanjiao.png"));
        left_top_text.add(jl_text);
        left_top_text.add(jl_left_top_text);
        left_top.add(left_top_text, BorderLayout.NORTH);

        JPanel jp_server = new JPanel(new GridLayout(3, 1));
        JPanel jp_server_ip = new JPanel();
        jp_server_ip.setBackground(new Color(250, 250, 210));
        JLabel jl_server_ip = new JLabel("服务端IP地址:");
        Font font_ip = new Font("黑体", Font.PLAIN, 15);// 创建1个字体实例
        jl_server_ip.setFont(font_ip);
        JTextField jt_server_ip = new JTextField(15);

         // 获取电脑的ip地址 填写在ip上

        InetAddress ip4 = null;
        try {
            ip4 = Inet4Address.getLocalHost();
        } catch (UnknownHostException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        jt_server_ip.setText(ip4.getHostAddress());
//		System.out.println(ip4.getHostAddress());
        jp_server_ip.add(jl_server_ip);
        jp_server_ip.add(jt_server_ip);
        jp_server.add(jp_server_ip);
        // =========================end=====================================
        // =========================服务端端口號===============================
        JPanel jp_server_sort = new JPanel();
        jp_server_sort.setBackground(new Color(250, 250, 210));
        JLabel jl_server_sort = new JLabel("服务端端口号:");
        Font font_sort = new Font("黑体", Font.PLAIN, 15);// 创建1个字体实例
        jl_server_sort.setFont(font_sort);
        JTextField jt_server_sort = new JTextField(15);
        jp_server_sort.add(jl_server_sort);
        jp_server_sort.add(jt_server_sort);
        jp_server.add(jp_server_sort);
        // =========================开始监听===============================
        JPanel jp_server_bt = new JPanel(new FlowLayout(FlowLayout.CENTER));
        jp_server_bt.setBackground(new Color(250, 250, 210));
        jb_jianting = new JButton("连接机器人");
        jb_jianting.setBackground(Color.white);
        jb_jianting.setForeground(Color.red);
        jp_server_bt.add(jb_jianting);
        jp_server.add(jp_server_bt);
        left_top.add(jp_server, BorderLayout.CENTER);
        // 点击监听方法
        jb_jianting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean flag = true;
                if (flag == true) {
                    try {
                        String a = jt_server_sort.getText().toString().trim();
                        if (a == null || a.equals("")) {
                            JOptionPane.showMessageDialog(null, "端口号不能为空");
                            return;
                        } else {
                            server = new ServerSocket(Integer.parseInt(a));
                            flag = false;
                            socket_con so = new socket_con();//开启服务端
                            so.start();
                        }
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            }
        });

        jPanel_left.add(left_top,BorderLayout.NORTH);



        //左侧布局 选择控制模式
        JPanel left_centre = new JPanel(new BorderLayout());
        left_centre.setBackground(new Color(250, 250, 210));
        left_centre.setPreferredSize(new Dimension(280, 150));
        JPanel jp_cl_text = new JPanel();
        jp_cl_text.setBackground(Color.white);
        JLabel jl_cb_text = new JLabel("设置控制模式");
        JLabel jl_jiqijiao = new JLabel();
        jl_jiqijiao.setPreferredSize(new Dimension(21, 19));
        jl_jiqijiao.setIcon(new ImageIcon("img//sanjiao.png"));
        jp_cl_text.add(jl_jiqijiao);
        jl_cb_text.setFont(font);

        jp_cl_text.add(jl_cb_text);
        left_centre.add(jp_cl_text, BorderLayout.NORTH);

        // =========================自动手动单选框===============================
        JPanel left_top_centre = new JPanel(new GridLayout(2, 1));
        JPanel jp_cb = new JPanel();
        jp_cb.setBackground(new Color(250, 250, 210));
        JRadioButton c1 = new JRadioButton("自动控制");// 只传了两个参数
        Font font_zidong = new Font("黑体", Font.PLAIN, 15);// 创建1个字体实例
        c1.setFont(font_zidong);
        c1.setBackground(new Color(250, 250, 210));
        JRadioButton c2 = new JRadioButton("手动控制");
        Font font_shoudong = new Font("黑体", Font.PLAIN, 15);// 创建1个字体实例
        c2.setFont(font_shoudong);
        c2.setBackground(new Color(250, 250, 210));
        c1.setSelected(true);
        ButtonGroup group = new ButtonGroup(); // 创建一个按钮组
        group.add(c1);
        group.add(c2);
        jp_cb.add(c1);
        jp_cb.add(c2);
        left_top_centre.add(jp_cb);
        // =========================end===============================
        // =========================确定按钮===============================
        JPanel jp_ok = new JPanel();
        jp_ok.setBackground(new Color(250, 250, 210));
        JButton btn_ok = new JButton("确定");
        btn_ok.setBackground(Color.white);
        jp_ok.add(btn_ok);
        left_top_centre.add(jp_ok);
        left_centre.add(left_top_centre, BorderLayout.CENTER);

        jPanel_left.add(left_centre, BorderLayout.CENTER);

        /*
         * 选择自动或者手动的提交按钮 监听事件
         */
        btn_ok.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                    if (c1.isSelected()) {
                        JOptionPane.showMessageDialog(null, "选择了自动控制模式");
                        /*方法*/
                    }
                    // 手动模式的单选框被选择
                    if (c2.isSelected()) {
                        JOptionPane.showMessageDialog(null, "选择了手动控制模式");
                        /*方法*/
                }
            }
        });

        //左侧布局手动控制模式详细信息
        JPanel left_bottom = new JPanel(new BorderLayout());
        left_bottom.setBackground(new Color(250, 250, 210));
        left_bottom.setPreferredSize(new Dimension(280, 695));
        JPanel jp_sd_text = new JPanel();
        jp_sd_text.setBackground(Color.white);
        JLabel jl_sd_text = new JLabel("手动控制模式");
        JLabel jl_jiqijiao1 = new JLabel();
        jl_jiqijiao1.setPreferredSize(new Dimension(21, 19));
        jl_jiqijiao1.setIcon(new ImageIcon("img//sanjiao.png"));
        jp_sd_text.add(jl_jiqijiao1);
        jl_sd_text.setFont(font);

        jp_sd_text.add(jl_sd_text);
        left_bottom.add(jp_sd_text,BorderLayout.NORTH);
        //灯的第一行
        // 灯
        JPanel jp_deng = new JPanel(new GridLayout(2, 1));
        // 灯的第一行
        JPanel jp_deng_top = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // 第一行灯的第一个
        JPanel jp_deng_top_left = new JPanel(new BorderLayout());
        // 提示字
        JPanel jp_yaliguodi = new JPanel();
        JLabel jl_yaliguodi = new JLabel("斜坡道-170米");
        jp_yaliguodi.add(jl_yaliguodi);
        jp_deng_top.add(jp_yaliguodi, BorderLayout.NORTH);
        // 灯
        JPanel jp_yaliguodi_deng = new JPanel();
        JLabel jl_yaliguodi_deng = new JLabel();
        jl_yaliguodi_deng.setBorder(BorderFactory.createLineBorder(new Color(238, 238, 238)));
        jl_yaliguodi_deng.setIcon(new ImageIcon("img//hong.png"));


        jp_yaliguodi_deng.add(jl_yaliguodi_deng);
        jp_deng_top_left.add(jp_yaliguodi_deng, BorderLayout.CENTER);
        // 按键
        JPanel jp_yaliguodi_btn = new JPanel();
        JButton jl_yaliguodi_btn = new JButton("亮");
//		Font font_yalidi = new Font("黑体", Font.PLAIN, 6);// 创建1个字体实例
//		jl_yaliguodi_btn.setFont(font_yalidi);
//		jl_yaliguodi_btn.setPreferredSize(new Dimension(40,20));
        jl_yaliguodi_btn.setBackground(Color.white);
        jl_yaliguodi_btn.addActionListener(new ActionListener() {
            boolean flag_yalidi = true;
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (flag_yalidi == true) {
                    jl_yaliguodi_deng.setIcon(new ImageIcon("img//mie.png"));
                    jl_yaliguodi_btn.setText("灭");
                    flag_yalidi = false;
                } else {
                    jl_yaliguodi_deng.setIcon(new ImageIcon("img//hong.png"));
                    jl_yaliguodi_btn.setText("亮");
                    flag_yalidi = true;
                }
            }
        });

        jp_yaliguodi_btn.add(jl_yaliguodi_btn);
        jp_deng_top_left.add(jp_yaliguodi_btn, BorderLayout.SOUTH);
        jp_deng_top.add(jp_deng_top_left);

        left_bottom.add(jp_deng_top);

        jPanel_left.add(left_bottom,BorderLayout.SOUTH);
        // 第一行灯的第二个
        JPanel jp_deng_top_right = new JPanel(new BorderLayout());
        // 灯
        JPanel jp_yaliguogao_deng = new JPanel();
        JLabel jl_yaliguogao_deng = new JLabel();
        jl_yaliguogao_deng.setBorder(BorderFactory.createLineBorder(new Color(238, 238, 238)));
        jl_yaliguogao_deng.setIcon(new ImageIcon("img//lv.png"));
        jp_yaliguogao_deng.add(jl_yaliguogao_deng);
        jp_deng_top_right.add(jp_yaliguogao_deng, BorderLayout.CENTER);
        // 按键
        JPanel jp_yaliguogao_btn = new JPanel();
        JButton jl_yaliguogao_btn = new JButton("亮");


        jl_yaliguogao_btn.addActionListener(new ActionListener() {
            boolean flag_yaligao = true;
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (flag_yaligao == true) {
                    jl_yaliguogao_deng.setIcon(new ImageIcon("img//lv.png"));
                    jl_yaliguogao_btn.setText("亮");
                    flag_yaligao = false;
                    /*方法*/
                } else {
                    jl_yaliguogao_deng.setIcon(new ImageIcon("img//mie.png"));
                    jl_yaliguogao_btn.setText("灭");
                    flag_yaligao = true;
                    /*方法*/
                }

            }
        });
        jl_yaliguogao_btn.setBackground(Color.white);
        jp_yaliguogao_btn.add(jl_yaliguogao_btn);
        jp_deng_top_right.add(jp_yaliguogao_btn, BorderLayout.SOUTH);

        jp_deng_top.add(jp_deng_top_right);
        jp_deng.add(jp_deng_top);

        left_bottom.add(jp_deng);





        JButton button1=new JButton("中");
        jPanel_center.add(button1);

        jf.add(jPanel_left,BorderLayout.WEST);
        jf.add(jPanel_center,BorderLayout.CENTER);

        // 底部logo
        JPanel jp_logo = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        jp_logo.setBackground(new Color(221, 231, 246));
        ImageIcon ico12 = new ImageIcon("img//logo_chang.png");
        ico12.setImage(ico12.getImage().getScaledInstance(300, 60, Image.SCALE_DEFAULT));
        JLabel jl_logo = new JLabel(ico12);

        jp_logo.add(jl_logo);

        jf.add(jp_logo, BorderLayout.SOUTH);

        //声明菜单相关的组件
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(207, 220, 243));
        menuBar.setPreferredSize(new Dimension(300,30));


        JMenu xitongMenue = new JMenu("系统");
        JMenu carMenue = new JMenu("车辆信息");
        JMenuItem tuichu = new JMenuItem("退出");
        JMenuItem xinxi = new JMenuItem("查看车辆信息");
        xitongMenue.setFont(font);
        carMenue.setFont(font);
        tuichu.setFont(font);
        xinxi.setFont(font);
        xitongMenue.add(tuichu);
        carMenue.add(xinxi);
        menuBar.add(xitongMenue);
        menuBar.add(carMenue);
        jf.add(menuBar,BorderLayout.NORTH);

        jPanel_center.add(redButton1);
        jPanel_center.add(greenButton1);
        jPanel_center.add(yellowButton1);
        jPanel_center.add(redButton2);
        jPanel_center.add(greenButton2);
        jPanel_center.add(yellowButton2);
        jPanel_center.add(redButton3);
        jPanel_center.add(greenButton3);
        jPanel_center.add(yellowButton3);
        jPanel_center.add(redButton4);
        jPanel_center.add(greenButton4);
        jPanel_center.add(yellowButton4);
        jPanel_center.add(redButton5);
        jPanel_center.add(greenButton5);
        jPanel_center.add(yellowButton5);
        jPanel_center.add(redButton6);
        jPanel_center.add(greenButton6);
        jPanel_center.add(yellowButton6);

        jPanel_center.add(carButton1);
        jPanel_center.add(carButton2);
        jPanel_center.add(redButton_200top);
        jPanel_center.add(greenButton_200top);
        jPanel_center.add(yellowButton_200top);
        jPanel_center.add(redButton_200south);
        jPanel_center.add(greenButton_200south);
        jPanel_center.add(yellowButton_200south);


        //设置显示
        jf.setVisible(true);
        //设置关闭
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置最大化
        jf.setExtendedState(jf.getExtendedState()|JFrame.MAXIMIZED_BOTH );

    }



}

class socket_con extends Thread {
    @Override
    public void run() {

        while (Demo.sel_flag == true) {
            try {
                Demo.socket = Demo.server.accept();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "连接成功");
            System.out.println("连接成功！");
            Demo.jb_jianting.setText("已连接");
            Demo.jb_jianting.setForeground(Color.green);
            try {
                Demo.inputStream = Demo.socket.getInputStream();
//				System.out.print(all_page.inputStream);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            try {
                Demo.out = Demo.socket.getOutputStream();

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            //开启交通灯控制线程
            input_one is = new input_one();
            is.start();
        }
    }
}

class input_one extends Thread {
    // 10进制转换16进制
    public static String dncodeHex(Integer num) {
        String hex = Integer.toHexString(num);
        return hex;
    }

    // 判断字符串是不是全部为数字
    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    @Override
    public void run() {
        while (true) {
            byte[] buffer = new byte[30];
            try {
                // 读进buffer
                int size = Demo.inputStream.read(buffer);
            } catch (IOException e) {
                try {
                    Demo.socket.close();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            System.out.println(Arrays.toString(buffer));
            //错车线程关闭
           /* cuoche c = new cuoche();
            c.start();*/

            cheshan1 a = new cheshan1();
            a.start();
            cheshan2 b = new cheshan2();
            b.start();
            //判断第一位指令
            if (buffer[0] == -1) {
                //如果是 170 185 分站
                if (buffer[1] == 1 || buffer[1] == 2) {
                    String car = dncodeHex((int) buffer[3]);
                    //先判断200口是否进车
                    if (Demo.car_200.equals("00")){//200没进车

                        if (Demo.map.size() == 2) {//巷道里有两辆车，肯定是出车，判断是上半段车还是下半段车
                            if (car.equals(Demo.map.get(1))) {//上半段车出车，移除key1，灯不变
                                Demo.map.remove(1);
                                Demo.carnumber_one_falg = false;
                                //报警信息状态改变
                                Demo.car_numberJB = new JLabel("当前巷道中车辆数量:1");
                                Demo.carId_top = new JLabel("从-170/-185处进车的车辆编号:");
                                Demo.carId_200 = new JLabel("从-200处进车的车辆编号:");
                                Demo.carId_south = new JLabel("从-215/-230/-245处进车的车辆编号:"+Demo.map.get(2));

                            }
                            if (car.equals(Demo.map.get(2))) {//下半段车出车，移除key2，灯不变
                                Demo.map.remove(2);
                                Demo.carnumber_two_falg = false;
                                //报警信息状态改变
                                Demo.car_numberJB = new JLabel("当前巷道中车辆数量:1");
                                Demo.carId_top = new JLabel("从-170/-185处进车的车辆编号:"+Demo.map.get(1));
                                Demo.carId_200 = new JLabel("从-200处进车的车辆编号:");
                                Demo.carId_south = new JLabel("从-215/-230/-245处进车的车辆编号:");
                            }
                        } else if (Demo.map.size() == 1) {//巷道里现有一辆车，判断这辆车是出车还是进车
                            if (car.equals(Demo.map.get(1)) || car.equals(Demo.map.get(2))) {
                                //出车
                                //判断车辆信息是那个区段进来的车
                                if (car.equals(Demo.map.get(1))) {//上半段车出车
                                    //如果有一辆车,灯变,上半段变绿灯
                                    Demo.greenButton1.setIcon(Demo.GreenIcon);
                                    Demo.redButton1.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton1.setIcon(Demo.GrayIcon);
                                    Demo.greenButton2.setIcon(Demo.GreenIcon);
                                    Demo.redButton2.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton2.setIcon(Demo.GrayIcon);
                                    Demo.greenButton3.setIcon(Demo.GreenIcon);
                                    Demo.redButton3.setIcon(Demo.RedIcon);
                                    Demo.yellowButton3.setIcon(Demo.GrayIcon);
                                    Demo.greenButton4.setIcon(Demo.GreenIcon);
                                    Demo.redButton4.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton4.setIcon(Demo.GrayIcon);
                                    Demo.greenButton5.setIcon(Demo.GreenIcon);
                                    Demo.redButton5.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton5.setIcon(Demo.GrayIcon);
                                    Demo.greenButton6.setIcon(Demo.GreenIcon);
                                    Demo.redButton6.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton6.setIcon(Demo.GrayIcon);
                                    Demo.redButton_200top.setIcon(Demo.GrayIcon);
                                    Demo.greenButton_200top.setIcon(Demo.GreenIcon);
                                    Demo.yellowButton_200top.setIcon(Demo.GrayIcon);
                                    Demo.map.remove(1);
                                    Demo.carnumber_one_falg = false;
                                    //报警信息状态改变
                                    Demo.car_numberJB = new JLabel("当前巷道中车辆数量:0");
                                    Demo.carId_top = new JLabel("从-170/-185处进车的车辆编号:");
                                    Demo.carId_200 = new JLabel("从-200处进车的车辆编号:");
                                    Demo.carId_south = new JLabel("从-215/-230/-245处进车的车辆编号:");

                                } else {//下半段车出车
                                    Demo.greenButton1.setIcon(Demo.GreenIcon);
                                    Demo.redButton1.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton1.setIcon(Demo.GrayIcon);
                                    Demo.greenButton2.setIcon(Demo.GreenIcon);
                                    Demo.redButton2.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton2.setIcon(Demo.GrayIcon);
                                    Demo.greenButton3.setIcon(Demo.GreenIcon);
                                    Demo.redButton3.setIcon(Demo.GrayIcon);
                                    Demo.greenButton3.setIcon(Demo.GreenIcon);
                                    Demo.yellowButton3.setIcon(Demo.GrayIcon);
                                    Demo.greenButton4.setIcon(Demo.GreenIcon);
                                    Demo.redButton4.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton4.setIcon(Demo.GrayIcon);
                                    Demo.greenButton5.setIcon(Demo.GreenIcon);
                                    Demo.redButton5.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton5.setIcon(Demo.GrayIcon);
                                    Demo.greenButton6.setIcon(Demo.GreenIcon);
                                    Demo.redButton6.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton6.setIcon(Demo.GrayIcon);
                                    Demo.redButton_200south.setIcon(Demo.GrayIcon);
                                    Demo.greenButton_200south.setIcon(Demo.GreenIcon);
                                    Demo.yellowButton_200south.setIcon(Demo.GrayIcon);
                                    Demo.map.remove(2);
                                    Demo.carnumber_two_falg = false;
                                    //报警信息状态改变
                                    Demo.car_numberJB = new JLabel("当前巷道中车辆数量:0");
                                    Demo.carId_top = new JLabel("从-170/-185处进车的车辆编号:");
                                    Demo.carId_200 = new JLabel("从-200处进车的车辆编号:");
                                    Demo.carId_south = new JLabel("从-215/-230/-245处进车的车辆编号:");
                                }
                            }
                            //map.size()==0 进车 添加key1 ，灯变红
                            //进车 记录进车识别标识
                           else{
                                Demo.map.put(1, dncodeHex((int) buffer[3]));
                                //判断是170和185
                                if (buffer[1] == 1) {//如果是170 170黄灯亮，185，200 200S上红灯
                                    Demo.redButton1.setIcon(Demo.GrayIcon);
                                    Demo.greenButton1.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton1.setIcon(Demo.YellowIcon);
                                    Demo.redButton2.setIcon(Demo.RedIcon);
                                    Demo.greenButton2.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton2.setIcon(Demo.GrayIcon);
                                    Demo.redButton3.setIcon(Demo.RedIcon);
                                    Demo.greenButton3.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton3.setIcon(Demo.GrayIcon);
                                    Demo.redButton_200top.setIcon(Demo.RedIcon);
                                    Demo.greenButton_200top.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton_200top.setIcon(Demo.GrayIcon);
                                    Demo.carnumber_one_falg = true;
                                } else {
                                    // 果是185 185黄灯亮 170,200,200上红灯
                                    Demo.redButton1.setIcon(Demo.RedIcon);
                                    Demo.greenButton1.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton1.setIcon(Demo.GrayIcon);
                                    Demo.redButton2.setIcon(Demo.GrayIcon);
                                    Demo.greenButton2.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton2.setIcon(Demo.YellowIcon);
                                    Demo.redButton3.setIcon(Demo.RedIcon);
                                    Demo.greenButton3.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton3.setIcon(Demo.GrayIcon);
                                    Demo.redButton_200top.setIcon(Demo.RedIcon);
                                    Demo.greenButton_200top.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton_200top.setIcon(Demo.GrayIcon);
                                    Demo.carnumber_one_falg = true;
                                }
                            }
                        } else {//肯定是进车 map.size()==0
                            //进车 记录进车识别标识
                            Demo.map.put(1, dncodeHex((int) buffer[3]));
                            //判断是170和185
                            if (buffer[1] == 1) {//如果是170 170黄灯亮，185，200 200S上红灯
                                Demo.redButton1.setIcon(Demo.GrayIcon);
                                Demo.greenButton1.setIcon(Demo.GrayIcon);
                                Demo.yellowButton1.setIcon(Demo.YellowIcon);
                                Demo.redButton2.setIcon(Demo.RedIcon);
                                Demo.greenButton2.setIcon(Demo.GrayIcon);
                                Demo.yellowButton2.setIcon(Demo.GrayIcon);
                                Demo.redButton3.setIcon(Demo.RedIcon);
                                Demo.greenButton3.setIcon(Demo.GrayIcon);
                                Demo.yellowButton3.setIcon(Demo.GrayIcon);
                                Demo.redButton_200top.setIcon(Demo.RedIcon);
                                Demo.greenButton_200top.setIcon(Demo.GrayIcon);
                                Demo.yellowButton_200top.setIcon(Demo.GrayIcon);
                                Demo.carnumber_one_falg = true;
                            } else {
                                // 果是185 185黄灯亮 170,200,200上红灯
                                Demo.redButton1.setIcon(Demo.RedIcon);
                                Demo.greenButton1.setIcon(Demo.GrayIcon);
                                Demo.yellowButton1.setIcon(Demo.GrayIcon);
                                Demo.redButton2.setIcon(Demo.GrayIcon);
                                Demo.greenButton2.setIcon(Demo.GrayIcon);
                                Demo.yellowButton2.setIcon(Demo.YellowIcon);
                                Demo.redButton3.setIcon(Demo.RedIcon);
                                Demo.greenButton3.setIcon(Demo.GrayIcon);
                                Demo.yellowButton3.setIcon(Demo.GrayIcon);
                                Demo.redButton_200top.setIcon(Demo.RedIcon);
                                Demo.greenButton_200top.setIcon(Demo.GrayIcon);
                                Demo.yellowButton_200top.setIcon(Demo.GrayIcon);
                                Demo.carnumber_one_falg = true;
                            }
                        }
                    }
                    if (car.equals(Demo.car_200)){//说明200进车了,现在从上段出车，灯全部变绿
                        Demo.car_200 = "00";
                        Demo.redButton1.setIcon(Demo.GrayIcon);
                        Demo.greenButton1.setIcon(Demo.GreenIcon);
                        Demo.yellowButton1.setIcon(Demo.GrayIcon);
                        Demo.redButton2.setIcon(Demo.GrayIcon);
                        Demo.greenButton2.setIcon(Demo.GreenIcon);
                        Demo.yellowButton2.setIcon(Demo.GrayIcon);
                        Demo.redButton3.setIcon(Demo.GrayIcon);
                        Demo.greenButton3.setIcon(Demo.GreenIcon);
                        Demo.yellowButton3.setIcon(Demo.GrayIcon);
                        Demo.redButton4.setIcon(Demo.GrayIcon);
                        Demo.greenButton4.setIcon(Demo.GreenIcon);
                        Demo.yellowButton4.setIcon(Demo.GrayIcon);
                        Demo.redButton5.setIcon(Demo.GrayIcon);
                        Demo.greenButton5.setIcon(Demo.GreenIcon);
                        Demo.yellowButton5.setIcon(Demo.GrayIcon);
                        Demo.redButton6.setIcon(Demo.GrayIcon);
                        Demo.greenButton6.setIcon(Demo.GreenIcon);
                        Demo.yellowButton6.setIcon(Demo.GrayIcon);
                        Demo.carnumber_one_falg = false;
                    }

                } else if (buffer[1] == 3) {//如果是200分站
                    if (buffer[2] == 1) {
                        //carnumber_one 记录上半段数字
                        //map中key1记录上半段进车的信息
                        Demo.carnumber_one = dncodeHex((int) buffer[3]);
                        if (Demo.map.size() == 2) {//有两辆车，说明需要错车
                            if (Demo.carnumber_one.equals(Demo.map.get(2))) {//检测到下边来的车，说明下边的车在避让，200下变绿灯
                                Demo.redButton_200south.setIcon(Demo.GrayIcon);
                                Demo.greenButton_200south.setIcon(Demo.GreenIcon);
                                Demo.yellowButton_200south.setIcon(Demo.GrayIcon);
                            }
                        }
                        if (Demo.map.size()==1){//说明有一辆车
                            if (Demo.carnumber_one.equals(Demo.map.get(1))){//上半段车过这个点，下半段车不允许走了，变红灯
                                Demo.redButton4.setIcon(Demo.RedIcon);
                                Demo.greenButton4.setIcon(Demo.GrayIcon);
                                Demo.yellowButton4.setIcon(Demo.GrayIcon);
                                Demo.redButton5.setIcon(Demo.RedIcon);
                                Demo.greenButton5.setIcon(Demo.GrayIcon);
                                Demo.yellowButton5.setIcon(Demo.GrayIcon);
                                Demo.redButton6.setIcon(Demo.RedIcon);
                                Demo.greenButton6.setIcon(Demo.GrayIcon);
                                Demo.yellowButton6.setIcon(Demo.GrayIcon);
                            }
                        }
                    } else if (buffer[2] == 2) {
                        if (Demo.map.size() == 2) {//有两辆车，说明需要错车
                            //记录当前在2的车辆信息，提示哪辆车在错车
                            Demo.cuoche_200 = dncodeHex((int) buffer[3]);
                            if (Demo.cuoche_200.equals(Demo.map.get(1))) {//先检测到上半段车进入到避让处,200上变绿灯，让下半段车先行
                                Demo.redButton_200top.setIcon(Demo.GrayIcon);
                                Demo.greenButton_200top.setIcon(Demo.GreenIcon);
                                Demo.yellowButton_200top.setIcon(Demo.GrayIcon);
                            }
                            if (Demo.cuoche_200.equals(Demo.map.get(2))) {//先检测到下半段车进入到避让处，200下变绿灯，让上半段车先行
                                Demo.redButton_200south.setIcon(Demo.GrayIcon);
                                Demo.greenButton_200south.setIcon(Demo.GreenIcon);
                                Demo.yellowButton_200south.setIcon(Demo.GrayIcon);
                            }
                        }
                    } else if (buffer[2] == 3) {
                        Demo.carnumber_two = dncodeHex((int) buffer[3]);
                        if (Demo.map.size() == 2) {//说明通道内有两辆车，需要错车
                            if (Demo.carnumber_two.equals(Demo.map.get(1))) {//如果是上边来的车，说明下边的车在避让，200上变绿灯
                                Demo.redButton_200top.setIcon(Demo.GrayIcon);
                                Demo.greenButton_200top.setIcon(Demo.GreenIcon);
                                Demo.yellowButton_200top.setIcon(Demo.GrayIcon);
                            }
                        }
                        if (Demo.map.size()==1){//说明通道内有一辆车
                            if (Demo.carnumber_two.equals(Demo.map.get(2))){//检测到下半段来车，上半段不允许进车了，变红灯
                                Demo.redButton1.setIcon(Demo.RedIcon);
                                Demo.greenButton1.setIcon(Demo.GrayIcon);
                                Demo.yellowButton1.setIcon(Demo.GrayIcon);
                                Demo.redButton2.setIcon(Demo.RedIcon);
                                Demo.greenButton2.setIcon(Demo.GrayIcon);
                                Demo.yellowButton2.setIcon(Demo.GrayIcon);
                                Demo.redButton3.setIcon(Demo.RedIcon);
                                Demo.greenButton3.setIcon(Demo.GrayIcon);
                                Demo.yellowButton3.setIcon(Demo.GrayIcon);
                            }
                        }

                    } else if (buffer[2] == 4) {
                        String car = dncodeHex((int) buffer[3]);
                        if (Demo.map.size()==2){//巷道里有两辆车，肯定是出车,不需要变灯
                            if (car.equals(Demo.map.get(1))){//上半段车出车，移除key1
                                Demo.map.remove(1);
                                Demo.carnumber_one_falg = false;
                            }
                            if (car.equals(Demo.map.get(2))){//下半段车出车
                                Demo.map.remove(2);
                                Demo.carnumber_two_falg = false;
                            }
                        }else if (Demo.map.size()==1){//也是出车，需要变灯
                            if (car.equals(Demo.map.get(1)) || car.equals(Demo.map.get(2))) {
                                //出车
                                //判断车辆信息是那个区段进来的车，对应区段的绿灯亮
                                if (car.equals(Demo.map.get(1))) {
                                    //上半段车出去，绿灯全亮
                                    Demo.greenButton1.setIcon(Demo.GreenIcon);
                                    Demo.redButton1.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton1.setIcon(Demo.GrayIcon);
                                    Demo.greenButton2.setIcon(Demo.GreenIcon);
                                    Demo.redButton2.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton2.setIcon(Demo.GrayIcon);
                                    Demo.greenButton3.setIcon(Demo.GreenIcon);
                                    Demo.redButton3.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton3.setIcon(Demo.GrayIcon);
                                    Demo.greenButton4.setIcon(Demo.GreenIcon);
                                    Demo.redButton4.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton4.setIcon(Demo.GrayIcon);
                                    Demo.greenButton5.setIcon(Demo.GreenIcon);
                                    Demo.redButton5.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton5.setIcon(Demo.GrayIcon);
                                    Demo.greenButton6.setIcon(Demo.GreenIcon);
                                    Demo.redButton6.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton6.setIcon(Demo.GrayIcon);
                                    Demo.redButton_200top.setIcon(Demo.GrayIcon);
                                    Demo.greenButton_200top.setIcon(Demo.GreenIcon);
                                    Demo.yellowButton_200top.setIcon(Demo.GrayIcon);
                                    Demo.map.remove(1);//出车移除数据
                                    Demo.carnumber_one_falg = false;
                                } else {
                                    //下半段车出去，绿灯全亮
                                    Demo.greenButton1.setIcon(Demo.GreenIcon);
                                    Demo.redButton1.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton1.setIcon(Demo.GrayIcon);
                                    Demo.greenButton2.setIcon(Demo.GreenIcon);
                                    Demo.redButton2.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton2.setIcon(Demo.GrayIcon);
                                    Demo.greenButton3.setIcon(Demo.GreenIcon);
                                    Demo.redButton3.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton3.setIcon(Demo.GrayIcon);
                                    Demo.greenButton4.setIcon(Demo.GreenIcon);
                                    Demo.redButton4.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton4.setIcon(Demo.GrayIcon);
                                    Demo.greenButton5.setIcon(Demo.GreenIcon);
                                    Demo.redButton5.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton5.setIcon(Demo.GrayIcon);
                                    Demo.greenButton6.setIcon(Demo.GreenIcon);
                                    Demo.redButton6.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton6.setIcon(Demo.GrayIcon);
                                    Demo.redButton_200south.setIcon(Demo.GrayIcon);
                                    Demo.greenButton_200south.setIcon(Demo.GreenIcon);
                                    Demo.yellowButton_200south.setIcon(Demo.GrayIcon);
                                    Demo.map.remove(2);//出车移除数据
                                    Demo.carnumber_two_falg = false;
                                }
                            }
                        }else {//map.size == 0，肯定是进车，全部变红灯，只有200的车能行驶，同时记录200标识
                            Demo.car_200 =dncodeHex((int) buffer[3]);
                            Demo.redButton1.setIcon(Demo.RedIcon);
                            Demo.greenButton1.setIcon(Demo.GrayIcon);
                            Demo.yellowButton1.setIcon(Demo.GrayIcon);
                            Demo.redButton2.setIcon(Demo.RedIcon);
                            Demo.greenButton2.setIcon(Demo.GrayIcon);
                            Demo.yellowButton2.setIcon(Demo.GrayIcon);
                            Demo.redButton3.setIcon(Demo.GrayIcon);
                            Demo.greenButton3.setIcon(Demo.GrayIcon);
                            Demo.yellowButton3.setIcon(Demo.YellowIcon);
                            Demo.redButton4.setIcon(Demo.RedIcon);
                            Demo.greenButton4.setIcon(Demo.GrayIcon);
                            Demo.yellowButton4.setIcon(Demo.GrayIcon);
                            Demo.redButton5.setIcon(Demo.RedIcon);
                            Demo.greenButton5.setIcon(Demo.GrayIcon);
                            Demo.yellowButton5.setIcon(Demo.GrayIcon);
                            Demo.redButton6.setIcon(Demo.RedIcon);
                            Demo.greenButton6.setIcon(Demo.GrayIcon);
                            Demo.yellowButton6.setIcon(Demo.GrayIcon);
                            Demo.carnumber_one_falg = true;
                        }
                    } else {
                        System.out.println("200分站错误的rfid站号码");
                    }
                } else if (buffer[1] == 4 || buffer[1] == 5 || buffer[1] == 6) {

                    String car = dncodeHex((int) buffer[3]);
                    if(Demo.car_200.equals("00")){//200没进车
                        if (Demo.map.size()==2){//肯定是出车
                            if (car.equals(Demo.map.get(1))){//上半段车出车，移除key1
                                Demo.map.remove(1);
                                Demo.carnumber_one_falg = false;
                            }
                            if (car.equals(Demo.map.get(2))){//下半段车出车，移除key2
                                Demo.map.remove(2);
                                Demo.carnumber_two_falg = false;
                            }
                        }else if (Demo.map.size()==1){//巷道里存在一辆车。判断这辆车是进车还是出车
                            if (car.equals(Demo.map.get(1)) || car.equals(Demo.map.get(2))) {
                                //出车
                                if (car.equals(Demo.map.get(1))) {//上半段车出车
                                    Demo.greenButton1.setIcon(Demo.GreenIcon);
                                    Demo.redButton1.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton1.setIcon(Demo.GrayIcon);
                                    Demo.greenButton2.setIcon(Demo.GreenIcon);
                                    Demo.redButton2.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton2.setIcon(Demo.GrayIcon);
                                    Demo.greenButton3.setIcon(Demo.GreenIcon);
                                    Demo.redButton3.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton3.setIcon(Demo.GrayIcon);
                                    Demo.redButton4.setIcon(Demo.GrayIcon);
                                    Demo.greenButton4.setIcon(Demo.GreenIcon);
                                    Demo.yellowButton4.setIcon(Demo.GrayIcon);
                                    Demo.redButton5.setIcon(Demo.GrayIcon);
                                    Demo.greenButton5.setIcon(Demo.GreenIcon);
                                    Demo.yellowButton5.setIcon(Demo.GrayIcon);
                                    Demo.redButton6.setIcon(Demo.GrayIcon);
                                    Demo.greenButton6.setIcon(Demo.GreenIcon);
                                    Demo.yellowButton6.setIcon(Demo.GrayIcon);
                                    Demo.redButton_200top.setIcon(Demo.GrayIcon);
                                    Demo.greenButton_200top.setIcon(Demo.GreenIcon);
                                    Demo.yellowButton_200top.setIcon(Demo.GrayIcon);
                                    Demo.map.remove(1);
                                    Demo.carnumber_one_falg = false;
                                } else {//下半段车出去，下半段绿灯亮
                                    Demo.greenButton1.setIcon(Demo.GreenIcon);
                                    Demo.redButton1.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton1.setIcon(Demo.GrayIcon);
                                    Demo.greenButton2.setIcon(Demo.GreenIcon);
                                    Demo.redButton2.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton2.setIcon(Demo.GrayIcon);
                                    Demo.greenButton3.setIcon(Demo.GreenIcon);
                                    Demo.redButton3.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton3.setIcon(Demo.GrayIcon);
                                    Demo.greenButton4.setIcon(Demo.GreenIcon);
                                    Demo.redButton4.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton4.setIcon(Demo.GrayIcon);
                                    Demo.greenButton5.setIcon(Demo.GreenIcon);
                                    Demo.redButton5.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton5.setIcon(Demo.GrayIcon);
                                    Demo.greenButton6.setIcon(Demo.GreenIcon);
                                    Demo.redButton6.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton6.setIcon(Demo.GrayIcon);
                                    Demo.redButton_200south.setIcon(Demo.GrayIcon);
                                    Demo.greenButton_200south.setIcon(Demo.GreenIcon);
                                    Demo.yellowButton_200south.setIcon(Demo.GrayIcon);
                                    Demo.map.remove(2);
                                    Demo.carnumber_two_falg = false;
                                }
                            }
                            else{//下半段还没有记录，说明是进车
                                Demo.map.put(2, dncodeHex((int) buffer[3]));
                                //判断是215、230、245
                                if (buffer[1] == 4) {//如果是215 215黄灯亮，200 ,230，245红灯
                                    Demo.redButton3.setIcon(Demo.RedIcon);
                                    Demo.greenButton3.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton3.setIcon(Demo.GrayIcon);
                                    Demo.redButton4.setIcon(Demo.GrayIcon);
                                    Demo.greenButton4.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton4.setIcon(Demo.YellowIcon);
                                    Demo.redButton5.setIcon(Demo.RedIcon);
                                    Demo.greenButton5.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton5.setIcon(Demo.GrayIcon);
                                    Demo.redButton6.setIcon(Demo.RedIcon);
                                    Demo.greenButton6.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton6.setIcon(Demo.GrayIcon);
                                    Demo.redButton_200south.setIcon(Demo.RedIcon);
                                    Demo.greenButton_200south.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton_200south.setIcon(Demo.GrayIcon);
                                    Demo.carnumber_two_falg = true;
                                } else if (buffer[1] == 5) {//如果是230 230黄灯亮 200,215,245红灯
                                    Demo.redButton3.setIcon(Demo.RedIcon);
                                    Demo.greenButton3.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton3.setIcon(Demo.GrayIcon);
                                    Demo.redButton4.setIcon(Demo.RedIcon);
                                    Demo.greenButton4.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton4.setIcon(Demo.GrayIcon);
                                    Demo.redButton5.setIcon(Demo.GrayIcon);
                                    Demo.greenButton5.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton5.setIcon(Demo.YellowIcon);
                                    Demo.redButton6.setIcon(Demo.RedIcon);
                                    Demo.greenButton6.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton6.setIcon(Demo.GrayIcon);
                                    Demo.redButton_200south.setIcon(Demo.RedIcon);
                                    Demo.greenButton_200south.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton_200south.setIcon(Demo.GrayIcon);
                                    Demo.carnumber_two_falg = true;
                                } else { //如果是245 245黄灯亮 200,215、230红灯
                                    Demo.redButton3.setIcon(Demo.RedIcon);
                                    Demo.greenButton3.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton3.setIcon(Demo.GrayIcon);
                                    Demo.redButton4.setIcon(Demo.RedIcon);
                                    Demo.greenButton4.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton4.setIcon(Demo.GrayIcon);
                                    Demo.redButton5.setIcon(Demo.RedIcon);
                                    Demo.greenButton5.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton5.setIcon(Demo.GrayIcon);
                                    Demo.redButton6.setIcon(Demo.GrayIcon);
                                    Demo.greenButton6.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton6.setIcon(Demo.YellowIcon);
                                    Demo.redButton_200south.setIcon(Demo.RedIcon);
                                    Demo.greenButton_200south.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton_200south.setIcon(Demo.GrayIcon);
                                    Demo.carnumber_two_falg = true;
                                }
                            }
                        } else {//肯定是进车 记录进车识别标识 map.size()==0
                            Demo.map.put(2, dncodeHex((int) buffer[3]));
                            //判断是215、230、245
                            if (buffer[1] == 4) {//如果是215 215黄灯亮，200，230，245红灯
                                Demo.redButton3.setIcon(Demo.RedIcon);
                                Demo.greenButton3.setIcon(Demo.GrayIcon);
                                Demo.yellowButton3.setIcon(Demo.GrayIcon);
                                Demo.redButton4.setIcon(Demo.GrayIcon);
                                Demo.greenButton4.setIcon(Demo.GrayIcon);
                                Demo.yellowButton4.setIcon(Demo.YellowIcon);
                                Demo.redButton5.setIcon(Demo.RedIcon);
                                Demo.greenButton5.setIcon(Demo.GrayIcon);
                                Demo.yellowButton5.setIcon(Demo.GrayIcon);
                                Demo.redButton6.setIcon(Demo.RedIcon);
                                Demo.greenButton6.setIcon(Demo.GrayIcon);
                                Demo.yellowButton6.setIcon(Demo.GrayIcon);
                                Demo.redButton_200south.setIcon(Demo.RedIcon);
                                Demo.greenButton_200south.setIcon(Demo.GrayIcon);
                                Demo.yellowButton_200south.setIcon(Demo.GrayIcon);
                                Demo.carnumber_two_falg = true;
                            } else if (buffer[1] == 5) {//如果是230 230黄灯亮 200，215,245红灯
                                Demo.redButton3.setIcon(Demo.RedIcon);
                                Demo.greenButton3.setIcon(Demo.GrayIcon);
                                Demo.yellowButton3.setIcon(Demo.GrayIcon);
                                Demo.redButton4.setIcon(Demo.RedIcon);
                                Demo.greenButton4.setIcon(Demo.GrayIcon);
                                Demo.yellowButton4.setIcon(Demo.GrayIcon);
                                Demo.redButton5.setIcon(Demo.GrayIcon);
                                Demo.greenButton5.setIcon(Demo.GrayIcon);
                                Demo.yellowButton5.setIcon(Demo.YellowIcon);
                                Demo.redButton6.setIcon(Demo.RedIcon);
                                Demo.greenButton6.setIcon(Demo.GrayIcon);
                                Demo.yellowButton6.setIcon(Demo.GrayIcon);
                                Demo.redButton_200south.setIcon(Demo.RedIcon);
                                Demo.greenButton_200south.setIcon(Demo.GrayIcon);
                                Demo.yellowButton_200south.setIcon(Demo.GrayIcon);
                                Demo.carnumber_two_falg = true;
                            } else { //如果是245 245黄灯亮 200， 215、230红灯
                                Demo.redButton3.setIcon(Demo.RedIcon);
                                Demo.greenButton3.setIcon(Demo.GrayIcon);
                                Demo.yellowButton3.setIcon(Demo.GrayIcon);
                                Demo.redButton4.setIcon(Demo.RedIcon);
                                Demo.greenButton4.setIcon(Demo.GrayIcon);
                                Demo.yellowButton4.setIcon(Demo.GrayIcon);
                                Demo.redButton5.setIcon(Demo.RedIcon);
                                Demo.greenButton5.setIcon(Demo.GrayIcon);
                                Demo.yellowButton5.setIcon(Demo.GrayIcon);
                                Demo.redButton6.setIcon(Demo.GrayIcon);
                                Demo.greenButton6.setIcon(Demo.GrayIcon);
                                Demo.yellowButton6.setIcon(Demo.YellowIcon);
                                Demo.redButton_200south.setIcon(Demo.RedIcon);
                                Demo.greenButton_200south.setIcon(Demo.GrayIcon);
                                Demo.yellowButton_200south.setIcon(Demo.GrayIcon);
                                Demo.carnumber_two_falg = true;
                            }
                        }
                    }
                    if (car.equals(Demo.car_200)){//说明200处出车，全部变绿灯
                        Demo.car_200 = "00";
                        Demo.redButton1.setIcon(Demo.GrayIcon);
                        Demo.greenButton1.setIcon(Demo.GreenIcon);
                        Demo.yellowButton1.setIcon(Demo.GrayIcon);
                        Demo.redButton2.setIcon(Demo.GrayIcon);
                        Demo.greenButton2.setIcon(Demo.GreenIcon);
                        Demo.yellowButton2.setIcon(Demo.GrayIcon);
                        Demo.redButton3.setIcon(Demo.GrayIcon);
                        Demo.greenButton3.setIcon(Demo.GreenIcon);
                        Demo.yellowButton3.setIcon(Demo.GrayIcon);
                        Demo.redButton4.setIcon(Demo.GrayIcon);
                        Demo.greenButton4.setIcon(Demo.GreenIcon);
                        Demo.yellowButton4.setIcon(Demo.GrayIcon);
                        Demo.redButton5.setIcon(Demo.GrayIcon);
                        Demo.greenButton5.setIcon(Demo.GreenIcon);
                        Demo.yellowButton5.setIcon(Demo.GrayIcon);
                        Demo.redButton6.setIcon(Demo.GrayIcon);
                        Demo.greenButton6.setIcon(Demo.GreenIcon);
                        Demo.yellowButton6.setIcon(Demo.GrayIcon);
                        Demo.carnumber_one_falg = false;
                    }
                } else {
                    System.out.println("错误的分站号码，只存在6个分站！");
                }
            }
        }
    }

}
class cheshan1 extends Thread {
    // 10进制转换16进制
    public static String dncodeHex(Integer num) {
        String hex = Integer.toHexString(num);
        return hex;
    }
    // 判断字符串是不是全部为数字
    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }
    @Override
    public void run() {
       while (2>1){
           if(Demo.carnumber_one_falg ==true){
               Demo.carButton1.setVisible(true);
               Demo.carButton1.setIcon(new ImageIcon("img//car.png"));
               try {
                   sleep(500);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               Demo.carButton1.setIcon(new ImageIcon("img//car_mie.png"));
               try {
                   sleep(500);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               Demo.carButton1.setIcon(new ImageIcon("img//car.png"));
           }else {
               Demo.carButton1.setVisible(false);
           }
        }

    }

}

class cheshan2 extends Thread {
    // 10进制转换16进制
    public static String dncodeHex(Integer num) {
        String hex = Integer.toHexString(num);
        return hex;
    }
    // 判断字符串是不是全部为数字
    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }
    @Override
    public void run() {
        while (2>1){
            if (Demo.carnumber_two_falg == true){
                Demo.carButton2.setVisible(true);
                Demo.carButton2.setIcon(new ImageIcon("img//car.png"));
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Demo.carButton2.setIcon(new ImageIcon("img//car_mie.png"));
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Demo.carButton2.setIcon(new ImageIcon("img//car.png"));
            }else{
                Demo.carButton2.hide();
            }
        }

    }

}


class cuoche extends Thread {
    // 10进制转换16进制
    public static String dncodeHex(Integer num) {
        String hex = Integer.toHexString(num);
        return hex;
    }
    // 判断字符串是不是全部为数字
    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }
    @Override
    public void run() {
        if(Demo.map.size()==2){
            if(Demo.carnumber_one==null&&Demo.carnumber_two==null){
                // carnumber_one和 carnumber_two都是空提示会车错车
                System.out.println("即将错车会车");

            }
            //一个是空，提示另一辆车在错车
            //解析数据，看200阶段的1或者2读分站那个先收集到信息
            if(Demo.carnumber_two==null&&Demo.carnumber_one.length()>0){//1先读到，区段1来的车先进行避让
                //200米c灯变红 200米a灯变绿
                Demo.redButton_200south.setIcon(Demo.RedIcon);
                Demo.greenButton_200south.setIcon(Demo.GrayIcon);
                Demo.yellowButton_200south.setIcon(Demo.GrayIcon);
                Demo.redButton_200top.setIcon(Demo.GrayIcon);
                Demo.greenButton_200top.setIcon(Demo.GreenIcon);
                Demo.yellowButton_200south.setIcon(Demo.YellowIcon);

            }else{//2先读到，
                //200米a灯变红 ， 200米c灯变绿
                Demo.redButton_200top.setIcon(Demo.RedIcon);
                Demo.greenButton_200top.setIcon(Demo.GrayIcon);
                Demo.yellowButton_200top.setIcon(Demo.GrayIcon);
                Demo.redButton_200south.setIcon(Demo.GrayIcon);
                Demo.greenButton_200south.setIcon(Demo.GreenIcon);
                Demo.yellowButton_200south.setIcon(Demo.GrayIcon);
            }

        }

    }

}
