package com.liu.main;

import com.liu.components.MyButton;
import com.liu.util.DimensionUtil;
import com.liu.view.CarView;
import com.liu.view.WarningView;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.*;
import java.util.*;
import java.util.regex.Pattern;

import static java.lang.Thread.sleep;


public class Demo {

    static ServerSocket server = null;
    static boolean sel_flag = true;
    static Socket socket = null;
    static int success_back = 1;//控制模式标志位，默认0为未连接，连接之后置为1
    static JButton jb_jianting;
    //手动模式下灯的标识位
    static boolean red_170 = true;
    static boolean red_185 = true;
    static boolean red_200top = true;
    static boolean red_200 = true;
    static boolean red_200south = true;
    static boolean red_215 = true;
    static boolean red_230 = true;
    static boolean red_245 = true;
    static JButton jbDeng_170 = null;
    static JButton jbDeng_185 = null;
    static JButton jbDeng_200top = null;
    static JButton jbDeng_200 = null;
    static JButton jbDeng_200south  = null;
    static JButton jbDeng_215 = null;
    static JButton jbDeng_230 = null;
    static JButton jbDeng_245 = null;
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
    static JLabel carButton1 = null;
    static JLabel carButton2 = null;
    static JPanel jPanel_center = null;
    static ImageIcon BigRedIcon = new ImageIcon("img//hong_big.png");
    static ImageIcon RedIcon = new ImageIcon("img//hong.png");
    static ImageIcon GreenIcon = new ImageIcon("img//lv.png");
    static ImageIcon GrayIcon = new ImageIcon("img//mie.png");
    static ImageIcon YellowIcon = new ImageIcon("img//yellow.png");
    static Map<Integer,String> map = new HashMap<>(); //存储车辆编号
    static String carnumber_one = null;//记录200区段a读卡
    static String carnumber_two = null;//记录200区段c读卡
    static String cuoche_200 ;
    static boolean birang_flag = false;//判断是否有车在避让
    static boolean cuoche_flag = false;//有两辆车的情况下，判断是否有车在错车
    static boolean carnumber_one_falg = false;
    static boolean carnumber_two_falg = false;

    static String car_200 = "00";//初始为00
    static boolean car_200top = false;
    static boolean car_200south = false;
    static JLabel car_numberJB = null;
    static JLabel carId_top = null;
    static JLabel carId_south = null;
    static JLabel baojing = null;
    //过滤数据
    static String chongfu170_1 = "00";
    static String chongfu170_2 = "00";
    static String chongfu170_3 = "00";
    static String chongfu185_1 = "00";
    static String chongfu185_2 = "00";
    static String chongfu185_3 = "00";
    static String chongfu200top_1 = "00";
    static String chongfu200top_2 = "00";
    static String chongfu200top_3 = "00";
    static String chongfu200_1 = "00";
    static String chongfu200_2 = "00";
    static String chongfu200_3 = "00";
    static String chongfu200south_1 = "00";
    static String chongfu200south_2 = "00";
    static String chongfu200south_3 = "00";
    static String chongfu215_1 = "00";
    static String chongfu215_2 = "00";
    static String chongfu215_3 = "00";
    static String chongfu230_1 = "00";
    static String chongfu230_2 = "00";
    static String chongfu230_3 = "00";
    static String chongfu245_1 = "00";
    static String chongfu245_2 = "00";
    static String chongfu245_3 = "00";
    static byte[] buf = new byte[11];
    static String text = "--请选择--";
    static File file;
    static byte[] buffer_send = new byte[13];
    static JLabel jb_shengji  = null;
    static boolean chu170_flag = true;
    static boolean chu185_flag = true;
    static boolean chu200top_flag = true;
    static boolean chu200south_flag = true;
    static boolean chu200_flag = true;
    static boolean chu215_flag = true;
    static boolean chu230_flag = true;
    static boolean chu245_flag = true;
    public static void main(String[] agrs) {

        mainpage();
    }
   public static  void  IpPage(){
            JFrame IpJframe = new JFrame();
            JPanel left_top = new JPanel(new BorderLayout());
            left_top.setBackground(new Color(250, 250, 210));
            left_top.setPreferredSize(new Dimension(300, 300));
            JPanel left_top_text = new JPanel();
            left_top_text.setBackground(Color.white);
            JLabel jl_left_top_text = new JLabel("连接主站IP");
            JLabel jl_text = new JLabel();
            Font font3 = new Font("黑体", Font.PLAIN, 20);// 创建1个字体实例
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
            jb_jianting = new JButton("连接控制分站");
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
                                //判断读卡分站的线程
                            }
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                }
            });
            IpJframe.add(left_top);
       IpJframe.setSize(400,250);
       IpJframe.setLocationRelativeTo(null);// 居中展示
            IpJframe.setVisible(true);
   }
    //固件升级程序
   public static void shengjiPage(){
       buffer_send[0] = (byte) 0x08;
       buffer_send[1] = (byte) 0x00;
       buffer_send[2] = (byte) 0x00;
       buffer_send[3] = (byte) 0x00;
       buffer_send[4] = (byte) 0x63;
       try {
           UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
       } catch (Exception e) {
           e.printStackTrace();
       }
       JFrame SjJframe = new JFrame();
       JPanel AllJpanel = new JPanel(new BorderLayout());
       JPanel WjJpanel = new JPanel();
       WjJpanel.setSize(400,80);
       JPanel SendJanel = new JPanel();
       SendJanel.setSize(400,50);
       JPanel tishi = new JPanel();
       tishi.setSize(400,20);
       //选择文件
       JTextField textField = new JTextField(20);
       JButton button = new JButton("选择");
       WjJpanel.add(new JLabel("文件"));
       WjJpanel.add(textField);
       WjJpanel.add(button);
       button.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               JFileChooser chooser = new JFileChooser();
               //FileNameExtensionFilter:文件名后缀过滤器
               FileNameExtensionFilter filter = new FileNameExtensionFilter("升级文件","bin","jpg","png");
               chooser.setFileFilter(filter);
               //显示对话框
               int ret = chooser.showOpenDialog(SjJframe);
               //获取用户选择的结果
               if (ret==JFileChooser.APPROVE_OPTION){
                   //结果为：已经存在的一个文件
                    file = chooser.getSelectedFile();
                   textField.setText(file.getAbsolutePath());
               }
           }
       });
       JLabel label1=new JLabel("请选择分站：");    //创建标签
       JComboBox cmb=new JComboBox();    //创建JComboBox
       cmb.addItem("--请选择--");    //向下拉列表中添加一项
       cmb.addItem("170分站");
       cmb.addItem("185分站");
       cmb.addItem("200分站");
       cmb.addItem("215分站");
       cmb.addItem("230分站");
       cmb.addItem("245分站");

       // 添加下拉框事件监听器
        cmb.addItemListener(new ItemListener() {
         @Override
         public void itemStateChanged(ItemEvent e) {
             if (e.getStateChange() == ItemEvent.SELECTED) {
                 // 选择的下拉框选项
                  text = (String) cmb.getSelectedItem();
                 if (text.equals("170分站")){
                     buffer_send[5] = (byte) 0x0B;
                 }else if (text.equals("185分站")){
                     buffer_send[5] = (byte) 0x0C;
                 }else if (text.equals("200分站")){
                     buffer_send[5] = (byte) 0x0D;
                 }else if (text.equals("215分站")){
                     buffer_send[5] = (byte) 0x0E;
                 }else if(text.equals("230分站")){
                     buffer_send[5] = (byte) 0x0F;
                 }else if(text.equals("245分站")){
                     buffer_send[5] = (byte) 0x10;
                 }
             }
         }
     });
        jb_shengji = new JLabel();
        jb_shengji.setFont(new Font("黑体", Font.PLAIN, 15));
        tishi.add(jb_shengji);
       JButton shengji = new JButton("升级程序");
       shengji.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               if (Demo.socket==null){
                   JOptionPane.showMessageDialog(null, "请先进行网络连接！");
               }else{
                   if (textField.getText().equals("")){
                       JOptionPane.showMessageDialog(null, "请选择文件！");
                   }else{
                       if (text.equals("--请选择--")){
                           JOptionPane.showMessageDialog(null, "请选择读卡分站！");
                       }else{
                           buffer_send[6] = (byte) 0x06;
                           buffer_send[7] = (byte) 0x05;
                           buffer_send[8] = (byte) 0x04;
                           buffer_send[9] = (byte) 0x03;
                           System.out.println(Arrays.toString(buffer_send));
                           outMessage(buffer_send);
                           try {
                               sleep(30);
                           } catch (InterruptedException e1) {
                               e1.printStackTrace();
                           }
                           outMessage(buffer_send);
                           jb_shengji.setText("升级中...");
                           jb_shengji.setForeground(Color.red);
                       }
                   }

               }

           }
       });
       SendJanel.add(label1);
       SendJanel.add(cmb);
       SendJanel.add(shengji);



       //下拉框添加点击事件
       AllJpanel.add(WjJpanel,BorderLayout.NORTH);
       AllJpanel.add(SendJanel,BorderLayout.CENTER);
       AllJpanel.add(tishi,BorderLayout.SOUTH);
       SjJframe.add(AllJpanel);
       SjJframe.setSize(400,150);
       SjJframe.setLocationRelativeTo(null);// 居中展示
       SjJframe.setVisible(true);
   }
   //字节数组转成16进制
    public static String byteToHex(byte b){
        String hex = Integer.toHexString(b & 0xFF);
        if(hex.length() < 2){
            hex = "0" + hex;
        }
        return hex;
    }
    //int 转换为byte
    public static byte[] intToByteArray(int i) {
        byte[] result = new byte[4];
        result[0] = (byte)((i >> 24) & 0xFF);
        result[1] = (byte)((i >> 16) & 0xFF);
        result[2] = (byte)((i >> 8) & 0xFF);
        result[3] = (byte)(i & 0xFF);
        return result;
    }


    public static void mainpage(){
            Font font = new Font("黑体", Font.PLAIN, 18);// 创建1个字体实例
            JFrame jf=new JFrame("");    //创建Frame窗口
            jf.setIconImage(new ImageIcon("img//logo.png").getImage());
            jf.setSize(400,200);
            jf.setLayout(new BorderLayout());    //为Frame窗口设置布局为BorderLayout

            JPanel jPanel_left=new JPanel();
            //中间中部容器
            Image image=new ImageIcon("img//beijing.png").getImage();
            JPanel jPanel_centerAll = new JPanel(new BorderLayout());
            JPanel jPanel_centerBottom = new JPanel();
            jPanel_centerBottom.setLayout(new BoxLayout(jPanel_centerBottom, BoxLayout.Y_AXIS));
            jPanel_centerBottom.setBackground(Color.white);
            jPanel_centerBottom.setPreferredSize(new Dimension(280, 50));
            baojing = new JLabel();
            baojing.setForeground(Color.green);
            baojing.setText("读卡分站状态:正常");
            baojing.setFont(new Font("黑体",Font.PLAIN,32));
            baojing.setHorizontalAlignment(JLabel.CENTER);
            jPanel_centerBottom.add(baojing);
            baojing.setAlignmentX(Component.CENTER_ALIGNMENT);
            jPanel_center = new BackgroundPanel(image);
            // 设置中部容器空布局，即绝对布局
            jPanel_center.setOpaque(false);
            jPanel_center.setLayout(null);
            Font jinggao_font = new Font("黑体", Font.PLAIN, 30);
            car_numberJB = new JLabel();
            carId_top = new JLabel();
            carId_south = new JLabel();
            car_numberJB.setText("当前巷道中车辆数量:0");
            car_numberJB.setForeground(Color.red);
            carId_top.setText("");
            carId_top.setForeground(Color.red);
            carId_south.setForeground(Color.red);
            carId_south.setText("");
            car_numberJB.setFont(jinggao_font);
            carId_top.setFont(jinggao_font);
            carId_south.setFont(jinggao_font);

            car_numberJB.setBounds(50,100,500,50);
            carId_top.setBounds(50,180,500,50);
            carId_south.setBounds(50,260,500,50);
            jPanel_center.add(car_numberJB);
            jPanel_center.add(carId_top);
            jPanel_center.add(carId_south);
            //灯的初始化
            //在环境图片上放置红绿灯素材
            redButton1 = MyButton.getGreyButton(1030,55);
            greenButton1 = MyButton.getGreenButton(1060, 55);
            yellowButton1 = MyButton.getGreyButton(1090, 55);
            redButton2 = MyButton.getGreyButton(936, 190);
            greenButton2 = MyButton.getGreenButton(966, 190);
            yellowButton2 = MyButton.getGreyButton(996, 190);
            //200 错车上部
            redButton_200top = MyButton.getGreyButton(730,280);
            greenButton_200top = MyButton.getGreenButton(760,280);
            yellowButton_200top = MyButton.getGreyButton(790,280);
            //200处
            redButton3 = MyButton.getGreyButton(810, 325);
            greenButton3 = MyButton.getGreenButton(840, 325);
            yellowButton3 = MyButton.getGreyButton(870, 325);
            //200错车下部
            redButton_200south = MyButton.getGreyButton(640,385);
            greenButton_200south = MyButton.getGreenButton(670,385);
            yellowButton_200south = MyButton.getGreyButton(700,385);


            redButton4 = MyButton.getGreyButton(685, 458);
            greenButton4 = MyButton.getGreenButton(715, 458);
            yellowButton4 = MyButton.getGreyButton(745, 458);
            redButton5 = MyButton.getGreyButton(560, 590);
            greenButton5 = MyButton.getGreenButton(590, 590);
            yellowButton5 = MyButton.getGreyButton(620, 590);
            redButton6 = MyButton.getGreyButton(430, 725);
            greenButton6 = MyButton.getGreenButton(460, 725);
            yellowButton6 = MyButton.getGreyButton(490, 725);

            carButton1 = MyButton.getCarButton1(732, 40);
            carButton2 = MyButton.getCarButton2(302, 352);

            carButton1.hide();
            carButton2.hide();
            //给容器添加红绿灯按钮
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


            jPanel_centerAll.add(jPanel_center,BorderLayout.CENTER);
            jPanel_centerAll.add(jPanel_centerBottom,BorderLayout.SOUTH);
            //布局设置边框，左侧，中部容器
            jPanel_left.setBorder(BorderFactory.createBevelBorder(1));
            jPanel_center.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,5));
            //左侧布局设置宽度，高度
            jPanel_left.setPreferredSize(new Dimension(300, 150));


            // =========================服务端ip地址===============================
            //左侧上部组件
       /*     JPanel left_top = new JPanel(new BorderLayout());
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

                                //判断读卡分站的线程

                            }
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                }
            });
            jPanel_left.add(left_top,BorderLayout.NORTH);*/
            //左侧布局 选择控制模式
            JPanel left_centre = new JPanel(new BorderLayout());
            left_centre.setBackground(new Color(187, 255, 255));
            left_centre.setPreferredSize(new Dimension(280, 140));
            JPanel jp_cl_text = new JPanel();
            jp_cl_text.setBackground(new Color(255, 235 ,205));
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
            jp_cb.setBackground(new Color(187, 255, 255));
            JRadioButton c1 = new JRadioButton("自动控制");// 只传了两个参数
            Font font_zidong = new Font("黑体", Font.PLAIN, 15);// 创建1个字体实例
            c1.setFont(font_zidong);
            c1.setBackground(new Color(187, 255, 255));
            JRadioButton c2 = new JRadioButton("手动控制");
            Font font_shoudong = new Font("黑体", Font.PLAIN, 15);// 创建1个字体实例
            c2.setFont(font_shoudong);
            c2.setBackground(new Color(187, 255, 255));
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
            jp_ok.setBackground(new Color(187, 255, 255));
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
                    if(socket==null){
                        JOptionPane.showMessageDialog(null, "请检查连接");
                    }else {
                        if (c1.isSelected()) {
                            JOptionPane.showMessageDialog(null, "选择了自动控制模式");
                            /*方法*/
                            /*
                             * 标志位变为1 当前为自动模式
                             */
                            success_back = 1;
                            //初始化所有自动数据
                            map = new HashMap<>(); //存储车辆编号
                            carnumber_one = null;//记录200区段a读卡
                            carnumber_two = null;//记录200区段c读卡
                            cuoche_200 ="00" ;
                            carnumber_one_falg = false;
                            carnumber_two_falg = false;
                            car_200 = "00";//初始为00
                            birang_flag = false;
                            cuoche_flag = false;
                            car_200top = false;
                            car_200south = false;
                            chongfu170_1 = "00";
                            chongfu170_2 = "00";
                            chongfu170_3 = "00";
                            chongfu185_1 = "00";
                            chongfu185_2 = "00";
                            chongfu185_3 = "00";
                            chongfu200top_1 = "00";
                            chongfu200top_2 = "00";
                            chongfu200top_3 = "00";
                            chongfu200_1 = "00";
                            chongfu200_2 = "00";
                            chongfu200_3 = "00";
                            chongfu200south_1 = "00";
                            chongfu200south_2 = "00";
                            chongfu200south_3 = "00";
                            chongfu215_1 = "00";
                            chongfu215_2 = "00";
                            chongfu215_3 = "00";
                            chongfu230_1 = "00";
                            chongfu230_2 = "00";
                            chongfu230_3 = "00";
                            chongfu245_1 = "00";
                            chongfu245_2 = "00";
                            chongfu245_3 = "00";
                            GreenDengInit();
                            car_numberJB.setText("当前巷道中车辆数量:0");
                            carId_top.setText("");
                            carId_south.setText("");
                            System.out.println(success_back);
                        }
                        // 手动模式的单选框被选择
                        if (c2.isSelected()) {
                            JOptionPane.showMessageDialog(null, "选择了手动控制模式");
                            /*方法*/
                            /*
                             * 标志位变为2 当前为手动模式
                             */
                            success_back = 2;
                            //灯进行初始化
                            ReddengInit();
                            car_numberJB.setText("");
                            carId_top.setText("");
                            carId_south.setText("");
                            System.out.println(success_back);
                            //给单片机板子发送指令，初始化全部绿灯
                        }
                    }

                }
            });
            //左侧布局手动控制模式详细信息
            JPanel left_bottom = new JPanel(new BorderLayout());

            //left_bottom.setBackground(new Color(250, 250, 210));
            left_bottom.setPreferredSize(new Dimension(280, 720));
            JPanel jp_sd_text = new JPanel();
            jp_sd_text.setBackground(new Color(255, 235 ,205));
            JLabel jl_sd_text = new JLabel("手动控制模式");
            JLabel jl_jiqijiao1 = new JLabel();
            jl_jiqijiao1.setPreferredSize(new Dimension(21, 19));
            jl_jiqijiao1.setIcon(new ImageIcon("img//sanjiao.png"));
            jp_sd_text.add(jl_jiqijiao1);
            jl_sd_text.setFont(font);

            jp_sd_text.add(jl_sd_text);
            left_bottom.add(jp_sd_text,BorderLayout.NORTH);
            // 灯
            JPanel jp_deng = new JPanel(new GridLayout(8, 1));
            jp_deng.setBackground(new Color(250, 250, 210));
    /*    JPanel jp_deng_185 = getDeng("-185米处");
        JPanel jp_deng_200top = getDeng("-200米北处");
        JPanel jp_deng_200 = getDeng("-200米处");
        JPanel jp_deng_200south = getDeng("-200米南处");
        JPanel jp_deng_215 = getDeng("-215米处");
        JPanel jp_deng_230 = getDeng("-230米处");
        JPanel jp_deng_245 = getDeng("-245米处");*/
            //手动控制模式下170的灯
            JPanel jp_deng_170 = new JPanel(new GridLayout(1,2));
            jp_deng_170.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,1));
            jp_deng_170.setBackground(new Color(187, 255, 255));
            // 提示字
            JLabel jl_height = new JLabel("-170米处");
            jl_height.setFont(new Font("黑体",Font.PLAIN,18));
            jl_height.setHorizontalAlignment(JLabel.CENTER);
            jp_deng_170.add(jl_height);
            jl_height.setAlignmentX(Component.CENTER_ALIGNMENT);

            jbDeng_170 = new JButton();
            jbDeng_170.setIcon(new ImageIcon("img//hong_big.png"));
            jbDeng_170.setBackground(new Color(187, 255, 255));
            jbDeng_170.setBorder(BorderFactory.createLineBorder(new Color(187, 255, 255)));
            //添加按钮点击事件
            jbDeng_170.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (Demo.success_back==2){
                        if (red_170==true){
                            jbDeng_170.setIcon(new ImageIcon("img//lv_big.png"));
                            red_170 = false;
                        }else {
                            jbDeng_170.setIcon(new ImageIcon("img//hong_big.png"));
                            red_170 = true;
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "非手动控制模式，操作无效！");
                    }

                }
            });
            jp_deng_170.add(jl_height);
            jp_deng_170.add(jbDeng_170);
            //手动控制模式下185的灯
            JPanel jp_deng_185 = new JPanel(new GridLayout(1,2));
            jp_deng_185.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,1));
            jp_deng_185.setBackground(new Color(187, 255, 255));
            // 提示字
            JLabel jl_height185 = new JLabel("-185米处");
            jl_height185.setFont(new Font("黑体",Font.PLAIN,18));
            jl_height185.setHorizontalAlignment(JLabel.CENTER);
            jl_height185.setAlignmentX(Component.CENTER_ALIGNMENT);

            jbDeng_185 = new JButton();
            jbDeng_185.setIcon(new ImageIcon("img//hong_big.png"));
            jbDeng_185.setBackground(new Color(187, 255, 255));
            jbDeng_185.setBorder(BorderFactory.createLineBorder(new Color(187, 255, 255)));
            //添加按钮点击事件
            jbDeng_185.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (Demo.success_back==2){
                        if (red_185==true){
                            jbDeng_185.setIcon(new ImageIcon("img//lv_big.png"));
                            red_185 = false;
                        }else {
                            jbDeng_185.setIcon(new ImageIcon("img//hong_big.png"));
                            red_185 = true;
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "非手动控制模式，操作无效！");
                    }

                }
            });
            jp_deng_185.add(jl_height185);
            jp_deng_185.add(jbDeng_185);
            //手动控制模式下200北部的灯
            JPanel jp_deng_200top = new JPanel(new GridLayout(1,2));
            jp_deng_200top.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,1));
            jp_deng_200top.setBackground(new Color(187, 255, 255));
            // 提示字
            JLabel jl_height200top = new JLabel("-200米北处");
            jl_height200top.setFont(new Font("黑体",Font.PLAIN,18));
            jl_height200top.setHorizontalAlignment(JLabel.CENTER);
            jl_height200top.setAlignmentX(Component.CENTER_ALIGNMENT);

            jbDeng_200top = new JButton();
            jbDeng_200top.setIcon(new ImageIcon("img//hong_big.png"));
            jbDeng_200top.setBackground(new Color(187, 255, 255));
            jbDeng_200top.setBorder(BorderFactory.createLineBorder(new Color(187, 255, 255)));
            //添加按钮点击事件
            jbDeng_200top.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (Demo.success_back==2){
                        if (red_200top==true){
                            jbDeng_200top.setIcon(new ImageIcon("img//lv_big.png"));
                            red_200top = false;
                        }else {
                            jbDeng_200top.setIcon(new ImageIcon("img//hong_big.png"));
                            red_200top = true;
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "非手动控制模式，操作无效！");
                    }

                }
            });
            jp_deng_200top.add(jl_height200top);
            jp_deng_200top.add(jbDeng_200top);
            //手动控制模式下200的灯
            JPanel jp_deng_200 = new JPanel(new GridLayout(1,2));
            jp_deng_200.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,1));
            jp_deng_200.setBackground(new Color(187, 255, 255));
            // 提示字
            JLabel jl_height200 = new JLabel("-200米处");
            jl_height200.setFont(new Font("黑体",Font.PLAIN,18));
            jl_height200.setHorizontalAlignment(JLabel.CENTER);
            jl_height200.setAlignmentX(Component.CENTER_ALIGNMENT);

            jbDeng_200 = new JButton();
            jbDeng_200.setIcon(new ImageIcon("img//hong_big.png"));
            jbDeng_200.setBackground(new Color(187, 255, 255));
            jbDeng_200.setBorder(BorderFactory.createLineBorder(new Color(187, 255, 255)));
            //添加按钮点击事件
            jbDeng_200.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (Demo.success_back==2){
                        if (red_200==true){
                            jbDeng_200.setIcon(new ImageIcon("img//lv_big.png"));
                            red_200 = false;
                        }else {
                            jbDeng_200.setIcon(new ImageIcon("img//hong_big.png"));
                            red_200 = true;
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "非手动控制模式，操作无效！");
                    }

                }
            });
            jp_deng_200.add(jl_height200);
            jp_deng_200.add(jbDeng_200);
            //手动控制模式下200南部的灯
            JPanel jp_deng_200south = new JPanel(new GridLayout(1,2));
            jp_deng_200south.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,1));
            jp_deng_200south.setBackground(new Color(187, 255, 255));
            // 提示字
            JLabel jl_height200south = new JLabel("-200米南处");
            jl_height200south.setFont(new Font("黑体",Font.PLAIN,18));
            jl_height200south.setHorizontalAlignment(JLabel.CENTER);
            jl_height200south.setAlignmentX(Component.CENTER_ALIGNMENT);

            jbDeng_200south = new JButton();
            jbDeng_200south.setIcon(new ImageIcon("img//hong_big.png"));
            jbDeng_200south.setBackground(new Color(187, 255, 255));
            jbDeng_200south.setBorder(BorderFactory.createLineBorder(new Color(187, 255, 255)));
            //添加按钮点击事件
            jbDeng_200south.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (Demo.success_back==2){
                        if (red_200south==true){
                            jbDeng_200south.setIcon(new ImageIcon("img//lv_big.png"));
                            red_200south = false;
                        }else {
                            jbDeng_200south.setIcon(new ImageIcon("img//hong_big.png"));
                            red_200south = true;
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "非手动控制模式，操作无效！");
                    }

                }
            });
            jp_deng_200south.add(jl_height200south);
            jp_deng_200south.add(jbDeng_200south);

            //手动控制模式下215的灯
            JPanel jp_deng_215 = new JPanel(new GridLayout(1,2));
            jp_deng_215.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,1));
            jp_deng_215.setBackground(new Color(187, 255, 255));
            // 提示字
            JLabel jl_height215 = new JLabel("-215米处");
            jl_height215.setFont(new Font("黑体",Font.PLAIN,18));
            jl_height215.setHorizontalAlignment(JLabel.CENTER);
            jl_height215.setAlignmentX(Component.CENTER_ALIGNMENT);

            jbDeng_215 = new JButton();
            jbDeng_215.setIcon(new ImageIcon("img//hong_big.png"));
            jbDeng_215.setBackground(new Color(187, 255, 255));
            jbDeng_215.setBorder(BorderFactory.createLineBorder(new Color(187, 255, 255)));
            //添加按钮点击事件
            jbDeng_215.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (Demo.success_back==2){
                        if (red_215==true){
                            jbDeng_215.setIcon(new ImageIcon("img//lv_big.png"));
                            red_215 = false;
                        }else {
                            jbDeng_215.setIcon(new ImageIcon("img//hong_big.png"));
                            red_215 = true;
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "非手动控制模式，操作无效！");
                    }

                }
            });
            jp_deng_215.add(jl_height215);
            jp_deng_215.add(jbDeng_215);

            //手动控制模式下230的灯
            JPanel jp_deng_230 = new JPanel(new GridLayout(1,2));
            jp_deng_230.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,1));
            jp_deng_230.setBackground(new Color(187, 255, 255));
            // 提示字
            JLabel jl_height230 = new JLabel("-230米处");
            jl_height230.setFont(new Font("黑体",Font.PLAIN,18));
            jl_height230.setHorizontalAlignment(JLabel.CENTER);
            jl_height230.setAlignmentX(Component.CENTER_ALIGNMENT);

            jbDeng_230 = new JButton();
            jbDeng_230.setIcon(new ImageIcon("img//hong_big.png"));
            jbDeng_230.setBackground(new Color(187, 255, 255));
            jbDeng_230.setBorder(BorderFactory.createLineBorder(new Color(187, 255, 255)));
            //添加按钮点击事件
            jbDeng_230.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (Demo.success_back==2){
                        if (red_230==true){
                            jbDeng_230.setIcon(new ImageIcon("img//lv_big.png"));
                            red_230 = false;
                        }else {
                            jbDeng_230.setIcon(new ImageIcon("img//hong_big.png"));
                            red_230 = true;
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "非手动控制模式，操作无效！");
                    }

                }
            });
            jp_deng_230.add(jl_height230);
            jp_deng_230.add(jbDeng_230);

            //手动控制模式下245的灯
            JPanel jp_deng_245 = new JPanel(new GridLayout(1,2));
            jp_deng_245.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,1));
            jp_deng_245.setBackground(new Color(187, 255, 255));
            // 提示字
            JLabel jl_height245 = new JLabel("-245米处");
            jl_height245.setFont(new Font("黑体",Font.PLAIN,18));
            jl_height245.setHorizontalAlignment(JLabel.CENTER);
            jl_height245.setAlignmentX(Component.CENTER_ALIGNMENT);

            jbDeng_245 = new JButton();
            jbDeng_245.setIcon(new ImageIcon("img//hong_big.png"));
            jbDeng_245.setBackground(new Color(187, 255, 255));
            jbDeng_245.setBorder(BorderFactory.createLineBorder(new Color(187, 255, 255)));
            //添加按钮点击事件
            jbDeng_245.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (Demo.success_back==2){
                        if (red_245==true){
                            jbDeng_245.setIcon(new ImageIcon("img//lv_big.png"));
                            red_245 = false;
                            System.out.println(red_245);
                        }else {
                            jbDeng_245.setIcon(new ImageIcon("img//hong_big.png"));
                            red_245 = true;
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "非手动控制模式，操作无效！");
                    }

                }
            });
            jp_deng_245.add(jl_height245);
            jp_deng_245.add(jbDeng_245);
            jp_deng.add(jp_deng_170);
            jp_deng.add(jp_deng_185);
            jp_deng.add(jp_deng_200top);
            jp_deng.add(jp_deng_200);
            jp_deng.add(jp_deng_200south);
            jp_deng.add(jp_deng_215);
            jp_deng.add(jp_deng_230);
            jp_deng.add(jp_deng_245);
            left_bottom.add(jp_deng,BorderLayout.CENTER);
            //提交按钮
            JPanel jp_tijiao = new JPanel();
            JButton jb_tijiao = new JButton("提交");
            jb_tijiao.setBackground(Color.white);
            jp_tijiao.add(jb_tijiao);
            jp_tijiao.setPreferredSize(new Dimension(280, 50));
            jp_tijiao.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,1));
            jp_tijiao.setBackground(new Color(187, 255, 255));

            jb_tijiao.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (Demo.success_back==2){
                        byte[] buf = new byte[11];
                        buf[0] = (byte) 0x06;
                        buf[1] = (byte) 0x00;
                        buf[2] = (byte) 0x00;
                        buf[3] = (byte) 0x00;
                        buf[4] = (byte) 0x01;
                        //170
                        if (red_170 == true){//手动控制170红灯亮
                            Demo.redButton1.setIcon(Demo.RedIcon);
                            Demo.greenButton1.setIcon(Demo.GrayIcon);
                            Demo.yellowButton1.setIcon(Demo.GrayIcon);
                            buf[5] = (byte) 0x04;
                        }else{//手动控制170绿灯亮
                            Demo.redButton1.setIcon(Demo.GrayIcon);
                            Demo.greenButton1.setIcon(Demo.GreenIcon);
                            Demo.yellowButton1.setIcon(Demo.GrayIcon);
                            buf[5] = (byte) 0x02;
                        }
                        //185
                        if (red_185 == true){//手动控制185红灯亮
                            Demo.redButton2.setIcon(Demo.RedIcon);
                            Demo.greenButton2.setIcon(Demo.GrayIcon);
                            Demo.yellowButton2.setIcon(Demo.GrayIcon);
                            buf[6] = (byte) 0x04;
                        }else{//手动控制185绿灯亮
                            Demo.redButton2.setIcon(Demo.GrayIcon);
                            Demo.greenButton2.setIcon(Demo.GreenIcon);
                            Demo.yellowButton2.setIcon(Demo.GrayIcon);
                            buf[6] = (byte) 0x02;
                        }
                        //200北部
                        if (red_200top == true){//手动控制200北部红灯亮
                            Demo.redButton_200top.setIcon(Demo.RedIcon);
                            Demo.greenButton_200top.setIcon(Demo.GrayIcon);
                            Demo.yellowButton_200top.setIcon(Demo.GrayIcon);
                            //200南部
                            if (red_200south == true){//手动控制200南部红灯亮
                                Demo.redButton_200south.setIcon(Demo.RedIcon);
                                Demo.greenButton_200south.setIcon(Demo.GrayIcon);
                                Demo.yellowButton_200south.setIcon(Demo.GrayIcon);
                                //200
                                if (red_200 == true){//手动控制200红灯亮
                                    Demo.redButton3.setIcon(Demo.RedIcon);
                                    Demo.greenButton3.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton3.setIcon(Demo.GrayIcon);
                                    buf[7] = (byte) 0xA4;
                                }else{//手动控制170绿灯亮
                                    Demo.redButton3.setIcon(Demo.GrayIcon);
                                    Demo.greenButton3.setIcon(Demo.GreenIcon);
                                    Demo.yellowButton3.setIcon(Demo.GrayIcon);
                                    buf[7] = (byte) 0xA2;
                                }
                            }else{//手动控制200南部绿灯亮
                                Demo.redButton_200south.setIcon(Demo.GrayIcon);
                                Demo.greenButton_200south.setIcon(Demo.GreenIcon);
                                Demo.yellowButton_200south.setIcon(Demo.GrayIcon);
                                //200
                                if (red_200 == true){//手动控制200红灯亮
                                    Demo.redButton3.setIcon(Demo.RedIcon);
                                    Demo.greenButton3.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton3.setIcon(Demo.GrayIcon);
                                    buf[7] = (byte) 0x94;
                                }else{//手动控制200绿灯亮
                                    Demo.redButton3.setIcon(Demo.GrayIcon);
                                    Demo.greenButton3.setIcon(Demo.GreenIcon);
                                    Demo.yellowButton3.setIcon(Demo.GrayIcon);
                                    buf[7] = (byte) 0x92;
                                }
                            }
                        }else{//手动控制200北部绿灯亮
                            Demo.redButton_200top.setIcon(Demo.GrayIcon);
                            Demo.greenButton_200top.setIcon(Demo.GreenIcon);
                            Demo.yellowButton_200top.setIcon(Demo.GrayIcon);
                            //200南部
                            if (red_200south == true){//手动控制200南部红灯亮
                                Demo.redButton_200south.setIcon(Demo.RedIcon);
                                Demo.greenButton_200south.setIcon(Demo.GrayIcon);
                                Demo.yellowButton_200south.setIcon(Demo.GrayIcon);
                                //200
                                if (red_200 == true){//手动控制200红灯亮
                                    Demo.redButton3.setIcon(Demo.RedIcon);
                                    Demo.greenButton3.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton3.setIcon(Demo.GrayIcon);
                                    buf[7] = (byte) 0x64;
                                }else{//手动控制200绿灯亮
                                    Demo.redButton3.setIcon(Demo.GrayIcon);
                                    Demo.greenButton3.setIcon(Demo.GreenIcon);
                                    Demo.yellowButton3.setIcon(Demo.GrayIcon);
                                    buf[7] = (byte) 0x62;
                                }
                            }else{//手动控制200南部绿灯亮
                                Demo.redButton_200south.setIcon(Demo.GrayIcon);
                                Demo.greenButton_200south.setIcon(Demo.GreenIcon);
                                Demo.yellowButton_200south.setIcon(Demo.GrayIcon);
                                //200
                                if (red_200 == true){//手动控制200红灯亮
                                    Demo.redButton3.setIcon(Demo.RedIcon);
                                    Demo.greenButton3.setIcon(Demo.GrayIcon);
                                    Demo.yellowButton3.setIcon(Demo.GrayIcon);
                                    buf[7] = (byte) 0x54;
                                }else{//手动控制200绿灯亮
                                    Demo.redButton3.setIcon(Demo.GrayIcon);
                                    Demo.greenButton3.setIcon(Demo.GreenIcon);
                                    Demo.yellowButton3.setIcon(Demo.GrayIcon);
                                    buf[7] = (byte) 0x52;
                                }
                            }
                        }

                        //215
                        if (red_215 == true){//手动控制215红灯亮
                            Demo.redButton4.setIcon(Demo.RedIcon);
                            Demo.greenButton4.setIcon(Demo.GrayIcon);
                            Demo.yellowButton4.setIcon(Demo.GrayIcon);
                            buf[8] = (byte) 0x04;
                        }else{//手动控制215绿灯亮
                            Demo.redButton4.setIcon(Demo.GrayIcon);
                            Demo.greenButton4.setIcon(Demo.GreenIcon);
                            Demo.yellowButton4.setIcon(Demo.GrayIcon);
                            buf[8] =(byte) 0x02;

                        }
                        //230
                        if (red_230 == true){//手动控制230红灯亮
                            Demo.redButton5.setIcon(Demo.RedIcon);
                            Demo.greenButton5.setIcon(Demo.GrayIcon);
                            Demo.yellowButton5.setIcon(Demo.GrayIcon);
                            buf[9] = (byte) 0x04;
                        }else{//手动控制230绿灯亮
                            Demo.redButton5.setIcon(Demo.GrayIcon);
                            Demo.greenButton5.setIcon(Demo.GreenIcon);
                            Demo.yellowButton5.setIcon(Demo.GrayIcon);
                            buf[9] = (byte) 0x02;
                        }
                        //245
                        if (red_245 == true){//手动控制245红灯亮
                            Demo.redButton6.setIcon(Demo.RedIcon);
                            Demo.greenButton6.setIcon(Demo.GrayIcon);
                            Demo.yellowButton6.setIcon(Demo.GrayIcon);
                            buf[10] = (byte) 0x04;
                        }else{//手动控制245绿灯亮
                            Demo.redButton6.setIcon(Demo.GrayIcon);
                            Demo.greenButton6.setIcon(Demo.GreenIcon);
                            Demo.yellowButton6.setIcon(Demo.GrayIcon);
                            buf[10] = (byte) 0x02;
                        }
                        Demo.outMessage(buf);
                        JOptionPane.showMessageDialog(null,"提交成功！");
                    }else{
                        JOptionPane.showMessageDialog(null, "非手动控制模式，操作无效！");
                    }
                }
            });
            left_bottom.add(jp_tijiao,BorderLayout.SOUTH);
            jPanel_left.add(left_bottom,BorderLayout.SOUTH);
            jf.add(jPanel_left,BorderLayout.WEST);
            jf.add(jPanel_centerAll,BorderLayout.CENTER);
            // 底部logo
            JPanel jp_logo = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            jp_logo.setBackground(new Color(221, 231, 246));
            ImageIcon ico12 = new ImageIcon("img//logo_chang.png");
            ico12.setImage(ico12.getImage().getScaledInstance(300, 60, Image.SCALE_DEFAULT));
            JLabel jl_logo = new JLabel(ico12);
            jp_logo.add(jl_logo);
            jf.add(jp_logo, BorderLayout.SOUTH);
            JPanel jPanel_top  = new JPanel(new BorderLayout());
            jPanel_top.setBackground(new Color(	152 ,245, 255));
            jPanel_top.setPreferredSize(new Dimension(300,60));
            JPanel jP_biaoti = new JPanel(new BorderLayout());
            JLabel jb_biaoti = new JLabel();
            JLabel jb_biaoti_xinxi = new JLabel();
            jb_biaoti.setText("斜坡道管理软件WXXJR-V1.1");
            jb_biaoti.setForeground(Color.black);
        jb_biaoti.setFont(new Font("黑体",Font.PLAIN,24));
        jb_biaoti.setHorizontalAlignment(JLabel.CENTER);
        jb_biaoti.setAlignmentX(Component.CENTER_ALIGNMENT);
            jPanel_top.add(jb_biaoti,BorderLayout.NORTH);
        //声明菜单相关的组件
            JMenuBar menuBar = new JMenuBar();
            menuBar.setBackground(new Color(255, 235 ,205));
            menuBar.setPreferredSize(new Dimension(300,30));
            JMenu xitongMenue = new JMenu("系统");
            JMenu wl_lianjieMenue = new JMenu("网络连接");
            JMenu carMenue = new JMenu("车辆信息");
            JMenu baojingMenue = new JMenu("报警信息");
            JMenu shengji = new JMenu("固件升级");
            JMenuItem tuichu = new JMenuItem("退出");
            JMenuItem ip_lianjie = new JMenuItem("连接主站ip");
            JMenuItem xinxi = new JMenuItem("车辆信息管控");
            JMenuItem baojingMenueItem = new JMenuItem("报警日志查看");
            JMenuItem shengjiItem = new JMenuItem("升级程序");
            xitongMenue.setFont(font);
            wl_lianjieMenue.setFont(font);
            carMenue.setFont(font);
            baojingMenue.setFont(font);
            shengji.setFont(font);
            tuichu.setFont(font);
            ip_lianjie.setFont(font);
            xinxi.setFont(font);
            baojingMenueItem.setFont(font);
            shengjiItem.setFont(font);
            xitongMenue.add(tuichu);
            wl_lianjieMenue.add(ip_lianjie);
            carMenue.add(xinxi);
            baojingMenue.add(baojingMenueItem);
            shengji.add(shengjiItem);
            menuBar.add(xitongMenue);
            menuBar.add(wl_lianjieMenue);
            menuBar.add(carMenue);
            menuBar.add(baojingMenue);
            menuBar.add(shengji);
            jPanel_top.add(menuBar,BorderLayout.SOUTH);
            jf.add(jPanel_top,BorderLayout.NORTH);
            //给车辆信息管控添加点击事件
            xinxi.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new CarView();
                }
            });
            baojingMenueItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new WarningView();
                }
            });
            //给网络连接添加菜单栏
            ip_lianjie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Demo.socket!=null){
                    JOptionPane.showMessageDialog(null, "网络已连接，无需再次连接！");
                }else{
                    IpPage();
                }
            }
        });
            //给升级程序添加点击事件
            shengjiItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    shengjiPage();
                }
            });

            //给菜单栏退出添加点击事件
            tuichu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });

            //根据屏幕大小设置主界面大小
            jf.setBounds(DimensionUtil.getBounds());
            //设置窗体完全充满整个屏幕的可见大小
            jf.setExtendedState(JFrame.MAXIMIZED_BOTH);
            jf.setLocationRelativeTo(null);
            //设置关闭
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //设置显示
            jf.setResizable(false);
            jf.setVisible(true);
            //设置最大化
            //jf.setExtendedState(jf.getExtendedState()|JFrame.MAXIMIZED_BOTH );


    }

    public static void ReddengInit() {
        //初始化全部红灯
        Demo.jbDeng_170.setIcon(Demo.BigRedIcon);
        Demo.jbDeng_185.setIcon(Demo.BigRedIcon);
        Demo.jbDeng_200top.setIcon(Demo.BigRedIcon);
        Demo.jbDeng_200.setIcon(Demo.BigRedIcon);
        Demo.jbDeng_200south.setIcon(Demo.BigRedIcon);
        Demo.jbDeng_215.setIcon(Demo.BigRedIcon);
        Demo.jbDeng_230.setIcon(Demo.BigRedIcon);
        Demo.jbDeng_245.setIcon(Demo.BigRedIcon);
        Demo.red_170 = true;
        Demo.red_185 = true;
        Demo.red_200top = true;
        Demo.red_200 = true;
        Demo.red_200south = true;
        Demo.red_215 = true;
        Demo.red_230 = true;
        Demo.red_245 = true;
        Demo.greenButton1.setIcon(Demo.GrayIcon);
        Demo.redButton1.setIcon(Demo.RedIcon);
        Demo.yellowButton1.setIcon(Demo.GrayIcon);
        Demo.greenButton2.setIcon(Demo.GrayIcon);
        Demo.redButton2.setIcon(Demo.RedIcon);
        Demo.yellowButton2.setIcon(Demo.GrayIcon);
        Demo.redButton_200top.setIcon(Demo.RedIcon);
        Demo.greenButton_200top.setIcon(Demo.GrayIcon);
        Demo.yellowButton_200top.setIcon(Demo.GrayIcon);
        Demo.greenButton3.setIcon(Demo.GrayIcon);
        Demo.redButton3.setIcon(Demo.RedIcon);
        Demo.yellowButton3.setIcon(Demo.GrayIcon);
        Demo.redButton_200south.setIcon(Demo.RedIcon);
        Demo.greenButton_200south.setIcon(Demo.GrayIcon);
        Demo.yellowButton_200south.setIcon(Demo.GrayIcon);
        Demo.greenButton4.setIcon(Demo.GrayIcon);
        Demo.redButton4.setIcon(Demo.RedIcon);
        Demo.yellowButton4.setIcon(Demo.GrayIcon);
        Demo.greenButton5.setIcon(Demo.GrayIcon);
        Demo.redButton5.setIcon(Demo.RedIcon);
        Demo.yellowButton5.setIcon(Demo.GrayIcon);
        Demo.greenButton6.setIcon(Demo.GrayIcon);
        Demo.redButton6.setIcon(Demo.RedIcon);
        Demo.yellowButton6.setIcon(Demo.GrayIcon);
        //Demo.carnumber_one_falg = false;
        Demo.carButton1.setVisible(false);
        //Demo.carnumber_two_falg = false;
        Demo.carButton2.setVisible(false);
        byte[] buf = new byte[11];
        buf[0] = (byte) 0x06;
        buf[1] = (byte) 0x00;
        buf[2] = (byte) 0x00;
        buf[3] = (byte) 0x00;
        buf[4] = (byte) 0x01;
        buf[5] = (byte) 0x04;
        buf[6] = (byte) 0x04;
        buf[7] = (byte) 0xA4;
        buf[8] = (byte) 0x04;
        buf[9] = (byte) 0x04;
        buf[10] = (byte) 0x04;
        outMessage(buf);
    }

    public static void GreenDengInit(){
        //初始化手动模式按钮
        Demo.jbDeng_170.setIcon(Demo.BigRedIcon);
        Demo.jbDeng_185.setIcon(Demo.BigRedIcon);
        Demo.jbDeng_200top.setIcon(Demo.BigRedIcon);
        Demo.jbDeng_200.setIcon(Demo.BigRedIcon);
        Demo.jbDeng_200south.setIcon(Demo.BigRedIcon);
        Demo.jbDeng_215.setIcon(Demo.BigRedIcon);
        Demo.jbDeng_230.setIcon(Demo.BigRedIcon);
        Demo.jbDeng_245.setIcon(Demo.BigRedIcon);
        Demo.red_170 = true;
        Demo.red_185 = true;
        Demo.red_200top = true;
        Demo.red_200 = true;
        Demo.red_200south = true;
        Demo.red_215 = true;
        Demo.red_230 = true;
        Demo.red_245 = true;
        //初始化报警信息
        baojing.setForeground(Color.green);
        baojing.setText("读卡分站状态:正常");
        //初始化全部绿灯
        Demo.greenButton1.setIcon(Demo.GreenIcon);
        Demo.redButton1.setIcon(Demo.GrayIcon);
        Demo.yellowButton1.setIcon(Demo.GrayIcon);
        Demo.greenButton2.setIcon(Demo.GreenIcon);
        Demo.redButton2.setIcon(Demo.GrayIcon);
        Demo.yellowButton2.setIcon(Demo.GrayIcon);
        Demo.redButton_200top.setIcon(Demo.GrayIcon);
        Demo.greenButton_200top.setIcon(Demo.GreenIcon);
        Demo.yellowButton_200top.setIcon(Demo.GrayIcon);
        Demo.greenButton3.setIcon(Demo.GreenIcon);
        Demo.redButton3.setIcon(Demo.GrayIcon);
        Demo.yellowButton3.setIcon(Demo.GrayIcon);
        Demo.redButton_200south.setIcon(Demo.GrayIcon);
        Demo.greenButton_200south.setIcon(Demo.GreenIcon);
        Demo.yellowButton_200south.setIcon(Demo.GrayIcon);
        Demo.greenButton4.setIcon(Demo.GreenIcon);
        Demo.redButton4.setIcon(Demo.GrayIcon);
        Demo.yellowButton4.setIcon(Demo.GrayIcon);
        Demo.greenButton5.setIcon(Demo.GreenIcon);
        Demo.redButton5.setIcon(Demo.GrayIcon);
        Demo.yellowButton5.setIcon(Demo.GrayIcon);
        Demo.greenButton6.setIcon(Demo.GreenIcon);
        Demo.redButton6.setIcon(Demo.GrayIcon);
        Demo.yellowButton6.setIcon(Demo.GrayIcon);
        //Demo.carnumber_one_falg = false;
        Demo.carButton1.setVisible(false);
        //Demo.carnumber_two_falg = false;
        Demo.carButton2.setVisible(false);
        //控制信号灯字节数组
        Demo.buf[0] = (byte) 0x06;
        Demo.buf[1] = (byte) 0x00;
        Demo.buf[2] = (byte) 0x00;
        Demo.buf[3] = (byte) 0x00;
        Demo.buf[4] = (byte) 0x01;
        Demo.buf[5] = (byte) 0x02;
        Demo.buf[6] = (byte) 0x02;
        Demo.buf[7] = (byte) 0x52;
        Demo.buf[8] = (byte) 0x02;
        Demo.buf[9] = (byte) 0x02;
        Demo.buf[10] = (byte) 0x02;
        outMessage(Demo.buf);
    }
    //socket输出
    public static void outMessage(byte[]  buffer){
        try {
            out.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//连接读卡分站
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
            JOptionPane.showMessageDialog(null, "连接成功！");
            System.out.println("连接成功！");
            Demo.jb_jianting.setText("已连接");
            Demo.jb_jianting.setForeground(Color.green);
            try {
                Demo.inputStream = Demo.socket.getInputStream();
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
            //错车线程
           /* cheshan1 cheshan1 = new cheshan1();
            cheshan1.start();
            cheshan2 b = new cheshan2();
            b.start();*/

            //开启交通灯控制线程
            input_one is = new input_one();
            is.start();
            //清除线程
            chu1 chu1 = new chu1();
            chu1.start();
        }
    }
}
class input_one extends Thread {
    /**
     * 将字节数组转换成十六进制的字符串
     *
     * @return
     */
    public static String BinaryToHexString(byte[] bytes) {
        String hexStr = "0123456789ABCDEF";
        String result = "";
        String hex = "";
        for (byte b : bytes) {
            hex = String.valueOf(hexStr.charAt((b & 0xF0) >> 4));
            hex += String.valueOf(hexStr.charAt(b & 0x0F));
            result += hex + " ";
        }
        return result;
    }
    /**
     * 将十六进制的字符串转换成字节数组
     *
     * @param hexString
     * @return
     */
    public static byte[] hexStrToBinaryStr(String hexString) {
        hexString = hexString.replaceAll(" ", "");
        int len = hexString.length();
        int index = 0;
        byte[] bytes = new byte[len / 2];
        while (index < len) {

            String sub = hexString.substring(index, index + 2);

            bytes[index/2] = (byte)Integer.parseInt(sub,16);

            index += 2;
        }
        return bytes;
    }
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
        //控制信号灯字节数组
        Demo.buf[0] = (byte) 0x06;
        Demo.buf[1] = (byte) 0x00;
        Demo.buf[2] = (byte) 0x00;
        Demo.buf[3] = (byte) 0x00;
        Demo.buf[4] = (byte) 0x01;
        Demo.buf[5] = (byte) 0x02;
        Demo.buf[6] = (byte) 0x02;
        Demo.buf[7] = (byte) 0x52;
        Demo.buf[8] = (byte) 0x02;
        Demo.buf[9] = (byte) 0x02;
        Demo.buf[10] = (byte) 0x02;
        Demo.outMessage(Demo.buf);
        while (true) {
            //接收数组
            byte[] buffer = new byte[30];
            try {
                // 读进buffer
                int size = Demo.inputStream.read(buffer);
                int line = 0;
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
            if (buffer[4] == 102){
                if (buffer[5] == 1){
                    Demo.jb_shengji.setText("");
                    JOptionPane.showMessageDialog(null, "升级固件程序成功！");
                }else if (buffer[5] == 2){
                    Demo.jb_shengji.setText("");
                    JOptionPane.showMessageDialog(null, "升级固件程序失败！,请重新操作！");
                    Demo.jb_shengji.setText("");
                }else if (buffer[5] == 3){
                    System.out.println("可以发送升级文件");
                    if (Demo.file.exists()){
                        try {
                            FileInputStream fis = new FileInputStream(Demo.file);
                            byte[] buffer_file = new byte[0];
                            try {
                                buffer_file = new byte[fis.available()];
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            try {
                                int len = fis.read(buffer_file);
                                int yushu = len % 8;
                                byte[] buf_file_send = new byte[13];
                                buf_file_send[0] = (byte) 0x08;
                                buf_file_send[1] = (byte) 0x00;
                                buf_file_send[2] = (byte) 0x00;
                                buf_file_send[3] = (byte) 0x00;
                                buf_file_send[4] = (byte)0x64;
                                for (int i=0;i<len-yushu;i=i+8){
                                    buf_file_send[5] = buffer_file[i];
                                    buf_file_send[6] = buffer_file[i+1];
                                    buf_file_send[7] = buffer_file[i+2];
                                    buf_file_send[8] = buffer_file[i+3];
                                    buf_file_send[9] = buffer_file[i+4];
                                    buf_file_send[10] = buffer_file[i+5];
                                    buf_file_send[11] = buffer_file[i+6];
                                    buf_file_send[12] = buffer_file[i+7];
                                    try {
                                        sleep(20);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    Demo.outMessage(buf_file_send);
                                }
                                //发送最后的余数
                                byte[] buf_yushu = new byte[5+yushu];
                                buf_yushu[1]=(byte) 0x00;
                                buf_yushu[2]=(byte) 0x00;
                                buf_yushu[3]=(byte) 0x00;
                                if (yushu!=0){
                                    for (int i=len-yushu , j = 5;i<len;i++,j++){
                                        buf_yushu[j] = buffer_file[i];
                                    }
                                    buf_yushu[0]=(byte) yushu;
                                    buf_yushu[4] = (byte) 0x64;
                                    Demo.outMessage(buf_yushu);
                                }

                                String strHex = Integer.toHexString(len);
                                byte[] buf_last = new byte[9];
                                buf_last[0]=(byte)0x04 ;
                                buf_last[1]=(byte) 0x00;
                                buf_last[2]=(byte) 0x00;
                                buf_last[3]=(byte) 0x00;
                                buf_last[4] = (byte) 0x65;
                                byte[] number = Demo.intToByteArray(len);
                                for (int i = 0;i<4;i++){
                                    buf_last[5] = number[0];
                                    buf_last[6] = number[1];
                                    buf_last[7] = number[2];
                                    buf_last[8] = number[3];
                                }
                                System.out.println(Arrays.toString(buf_last));
                                Demo.outMessage(buf_last);

                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        } catch (FileNotFoundException e1) {
                            e1.printStackTrace();
                        }
                    }
                }else if(buffer[5] == 5){
//                    JOptionPane.showMessageDialog(null, "密码校验失败！,请重新操作！");
                    //System.out.println("心跳包");
                }else{
                    Demo.jb_shengji.setText("");
                    JOptionPane.showMessageDialog(null, "密码校验失败！,请重新操作！");
                }
            }else{//不是升级程序
                if (buffer[7]==-1){
                    System.out.println("设备掉线");
                    if (buffer[4]==11){
                        //System.out.println("170分站掉线");
                        Demo.baojing.setForeground(Color.red);
                        Demo.baojing.setText("读卡分站状态:170分站掉线,请切换手动控制模式！");
//                    Demo.carId_top.setText("123");
                    }else if (buffer[4]==12){
                        //System.out.println("185分站掉线");
                        Demo.baojing.setForeground(Color.red);
                        Demo.baojing.setText("读卡分站状态:185分站掉线,请切换手动控制模式！");

                    }else if (buffer[4]==13){
                        //System.out.println("200分站掉线");
                        Demo.baojing.setForeground(Color.red);
                        Demo.baojing.setText("读卡分站状态:200分站掉线,请切换手动控制模式！");
                    }else if (buffer[4]==14){
                        //System.out.println("215分站掉线");
                        Demo.baojing.setForeground(Color.red);
                        Demo.baojing.setText("读卡分站状态:215分站掉线,请切换手动控制模式！");
                    }else if (buffer[4]==15){
                        //System.out.println("230分站掉线");
                        Demo.baojing.setForeground(Color.red);
                        Demo.baojing.setText("读卡分站状态:230分站掉线,请切换手动控制模式！");
                    }else {
                        //System.out.println("245分站掉线");
                        Demo.baojing.setForeground(Color.red);
                        Demo.baojing.setText("读卡分站状态:245分站掉线,请切换手动控制模式！");
                    }
                }else {
                    Demo.baojing.setForeground(Color.green);
                    Demo.baojing.setText("读卡分站状态:正常");
                    if (buffer[7]==0){
                        //System.out.println("没有车读到");//不走逻辑
                    }else{
                        if(Demo.success_back == 1){
//                System.out.println("进入了自动线程");
                            if (buffer[0] == 8) {
                                //如果是 170 185 分站
                                if (buffer[4] == 11) {//170分站
                                    if(dncodeHex((int) buffer[4]).equals(Demo.chongfu170_1)
                                            &&dncodeHex((int) buffer[5]).equals(Demo.chongfu170_2)
                                            &&dncodeHex((int) buffer[7]).equals(Demo.chongfu170_3)) {
                                        System.out.println("重复的buffer");
                                        continue;
                                    }
                                    String car = dncodeHex((int) buffer[7]);
                                    //先判断200口是否进车
                                    if (Demo.car_200.equals("00")){//200没进车或者200的车出去了
                                        if (Demo.map.size() == 2) {//巷道里有两辆车，肯定是出车，判断是上半段车还是下半段车
                                            if (car.equals(Demo.map.get(1)) || car.equals(Demo.map.get(2))){
                                                if (car.equals(Demo.map.get(1))) {//上半段车出车，移除key1，灯不变
                                                    Demo.map.remove(1);
                                                    //Demo.carnumber_one_falg = false;
                                                    Demo.carButton1.setVisible(false);
                                                    //报警信息状态改变
                                                    Demo.car_numberJB.setText("当前巷道中车辆数量:01");
                                                    Demo.carId_top.setText("车辆编号"+car+"从上半段驶离");
                                                    if (Demo.cuoche_flag == true){//有车在错车处
                                                        //200上下绿灯 200红灯 170红灯 185红灯
                                                        Demo.greenButton1.setIcon(Demo.GrayIcon);
                                                        Demo.redButton1.setIcon(Demo.RedIcon);
                                                        Demo.yellowButton1.setIcon(Demo.GrayIcon);
                                                        Demo.greenButton2.setIcon(Demo.GrayIcon);
                                                        Demo.redButton2.setIcon(Demo.RedIcon);
                                                        Demo.yellowButton2.setIcon(Demo.GrayIcon);
                                                        Demo.greenButton3.setIcon(Demo.GrayIcon);
                                                        Demo.redButton3.setIcon(Demo.RedIcon);
                                                        Demo.yellowButton3.setIcon(Demo.GrayIcon);
                                                        Demo.redButton_200top.setIcon(Demo.GrayIcon);
                                                        Demo.greenButton_200top.setIcon(Demo.GreenIcon);
                                                        Demo.yellowButton_200top.setIcon(Demo.GrayIcon);
                                                        Demo.redButton_200south.setIcon(Demo.GrayIcon);
                                                        Demo.greenButton_200south.setIcon(Demo.GreenIcon);
                                                        Demo.yellowButton_200south.setIcon(Demo.GrayIcon);
                                                        Demo.buf[5] = (byte) 0x02;
                                                        Demo.buf[6] = (byte) 0x02;
                                                        Demo.buf[7] = (byte) 0x54;
                                                        Demo.outMessage(Demo.buf);
                                                    }else {//没有车在错车处 200上绿灯 200下红灯 200红灯 170绿灯 185绿灯
                                                        Demo.greenButton1.setIcon(Demo.GreenIcon);
                                                        Demo.redButton1.setIcon(Demo.GrayIcon);
                                                        Demo.yellowButton1.setIcon(Demo.GrayIcon);
                                                        Demo.greenButton2.setIcon(Demo.GreenIcon);
                                                        Demo.redButton2.setIcon(Demo.GrayIcon);
                                                        Demo.yellowButton2.setIcon(Demo.GrayIcon);
                                                        Demo.greenButton3.setIcon(Demo.GrayIcon);
                                                        Demo.redButton3.setIcon(Demo.RedIcon);
                                                        Demo.yellowButton3.setIcon(Demo.GrayIcon);
                                                        Demo.redButton_200top.setIcon(Demo.GrayIcon);
                                                        Demo.greenButton_200top.setIcon(Demo.GreenIcon);
                                                        Demo.yellowButton_200top.setIcon(Demo.GrayIcon);
                                                        Demo.redButton_200south.setIcon(Demo.RedIcon);
                                                        Demo.greenButton_200south.setIcon(Demo.GreenIcon);
                                                        Demo.yellowButton_200south.setIcon(Demo.GrayIcon);
                                                        Demo.buf[5] = (byte) 0x04;
                                                        Demo.buf[6] = (byte) 0x04;
                                                        Demo.buf[7] = (byte) 0x64;
                                                        Demo.outMessage(Demo.buf);
                                                    }

                                                }
                                                if (car.equals(Demo.map.get(2))) {//下半段车出车，移除key2，灯不变
                                                    Demo.map.remove(2);
                                                    //Demo.carnumber_two_falg = false;
                                                    Demo.carButton2.setVisible(false);
                                                    //报警信息状态改变
                                                    Demo.car_numberJB.setText("当前巷道中车辆数量:01");
                                                    Demo.carId_south.setText("车辆编号"+car+"从上半段驶离");
                                                }
                                            }else {
                                                System.out.println("上下段都已进车，请退至等候区等待下次识别！");
                                            }
                                        } else if (Demo.map.size() == 1) {//巷道里现有一辆车，判断这辆车是出车还是进车
                                            if (Demo.map.get(1)!=null){
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
                                                    Demo.redButton_200south.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton_200south.setIcon(Demo.GreenIcon);
                                                    Demo.yellowButton_200south.setIcon(Demo.GrayIcon);
                                                    //输出控制信号灯
                                                    Demo.buf[5] = (byte) 0x02;//170绿灯
                                                    Demo.buf[6] = (byte) 0x02;//185绿灯
                                                    Demo.buf[7] = (byte) 0x52;//200全绿灯
                                                    Demo.buf[8] = (byte) 0x02;//215绿灯
                                                    Demo.buf[9] = (byte) 0x02;//230绿灯
                                                    Demo.buf[10] = (byte) 0x02;//245绿灯
                                                    Demo.outMessage(Demo.buf);
                                                    Demo.map.remove(1);
                                                    //Demo.carnumber_one_falg = false;
                                                    Demo.carButton1.setVisible(false);
                                                    //报警信息状态改变
                                                    Demo.car_numberJB.setText("当前巷道中车辆数量:00");
                                                    Demo.carId_top.setText("车辆编号"+car+"从上半段驶离");
                                                }else{
                                                    System.out.println("上半段已进车，请退至等候区等待下次识别！");
                                                }
                                            }
                                            if (Demo.map.get(2)!=null){
                                                if (car.equals(Demo.map.get(2))) {//下半段车出车
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
                                                    Demo.redButton_200top.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton_200top.setIcon(Demo.GreenIcon);
                                                    Demo.yellowButton_200top.setIcon(Demo.GrayIcon);
                                                    Demo.redButton_200south.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton_200south.setIcon(Demo.GreenIcon);
                                                    Demo.yellowButton_200south.setIcon(Demo.GrayIcon);
                                                    //输出控制信号灯
                                                    Demo.buf[5] = (byte) 0x02;//170绿灯
                                                    Demo.buf[6] = (byte) 0x02;//185绿灯
                                                    Demo.buf[7] = (byte) 0x52;//200全绿灯
                                                    Demo.buf[8] = (byte) 0x02;//215绿灯
                                                    Demo.buf[9] = (byte) 0x02;//230绿灯
                                                    Demo.buf[10] = (byte) 0x02;//245绿灯
                                                    Demo.outMessage(Demo.buf);
                                                    Demo.map.remove(2);
                                                    //Demo.carnumber_two_falg = false;
                                                    Demo.carButton2.setVisible(false);
                                                    Demo.car_200south = false;
                                                    //报警信息状态改变
                                                    Demo.car_numberJB.setText("当前巷道中车辆数量:00");
                                                    Demo.carId_south.setText("车辆编号"+car+"从上半段驶离");
                                                } else{//上半段进车 下半段有车 记录进车识别标识
                                                    Demo.map.put(1, dncodeHex((int) buffer[7]));
                                                    //判断是170和185
                                                    if (buffer[4] == 11) {//如果是170 170黄灯亮，185，200 200上红灯
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
                                                        //输出控制信号灯
                                                        Demo.buf[5] = (byte) 0x01;//170黄灯
                                                        Demo.buf[6] = (byte) 0x04;//185红灯
                                                        Demo.buf[7] = (byte) 0xA4;//200红灯 200上红灯 200下红灯
                                                        Demo.outMessage(Demo.buf);

                                                        //Demo.carnumber_one_falg = true;
                                                        Demo.carButton1.setVisible(true);
                                                        Demo.car_numberJB.setText("当前巷道中车辆数量:02");
                                                        Demo.carId_top.setText("车辆编号"+car+"从-170处驶入");
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
                                                        //输出控制信号灯
                                                        Demo.buf[5] = (byte) 0x04;//170红灯
                                                        Demo.buf[6] = (byte) 0x01;//185黄灯
                                                        Demo.buf[7] = (byte) 0xA4;//200红灯 200上红灯 200下红灯
                                                        Demo.outMessage(Demo.buf);
                                                        //Demo.carnumber_one_falg = true;
                                                        Demo.carButton1.setVisible(true);
                                                        Demo.car_numberJB.setText("当前巷道中车辆数量:02");
                                                        Demo.carId_top.setText("车辆编号"+car+"从-185处驶入");
                                                    }
                                                }
                                            }
                                        } else {//肯定是进车 map.size()==0
                                            //进车 记录进车识别标识
                                            Demo.map.put(1, dncodeHex((int) buffer[7]));
                                            //判断是170和185
                                            if (buffer[4] == 11) {//如果是170 170黄灯亮，185，200 200上红灯 200下绿灯
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
                                                //输出控制信号灯
                                                Demo.buf[5] = (byte) 0x01;//170黄灯
                                                Demo.buf[6] = (byte) 0x04;//185红灯
                                                Demo.buf[7] = (byte) 0x94;//200红灯 200上红灯 200下绿灯
                                                Demo.outMessage(Demo.buf);
                                                //车闪标识
                                                //Demo.carnumber_one_falg = true;
                                                Demo.carButton1.setVisible(true);
                                                //提示信息
                                                Demo.car_numberJB.setText("当前巷道中车辆数量:01");
                                                Demo.carId_top.setText("车辆编号"+car+"从-170处驶入");
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
                                                //输出控制信号灯
                                                Demo.buf[5] = (byte) 0x04;//170红灯
                                                Demo.buf[6] = (byte) 0x01;//185黄灯
                                                Demo.buf[7] = (byte) 0x94;//200红灯 200上红灯 200下绿灯
                                                Demo.outMessage(Demo.buf);
                                                //车闪标识
                                                //Demo.carnumber_one_falg = true;
                                                Demo.carButton1.setVisible(true);
                                                //提示信息
                                                Demo.car_numberJB.setText("当前巷道中车辆数量:01");
                                                Demo.carId_top.setText("车辆编号"+car+"从-185处驶入");
                                            }
                                        }
                                    }
                                    if (!Demo.car_200.equals("00")) {//200进车了
                                        if (!car.equals(Demo.car_200)){//200的车从下半段走了，现在上半段可以进车,也可以出车,出的车只能是上半段进的车，200进的车还在下半段没出去
                                            if (Demo.map.size()==1){//出车，但200的车还没出去，170 185 变绿灯，移除key1

                                                if (car.equals(Demo.map.get(1))){
                                                    Demo.map.remove(1);
                                                    Demo.redButton1.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton1.setIcon(Demo.GreenIcon);
                                                    Demo.yellowButton1.setIcon(Demo.GrayIcon);
                                                    Demo.redButton2.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton2.setIcon(Demo.GreenIcon);
                                                    Demo.yellowButton2.setIcon(Demo.GrayIcon);
                                                    //输出控制信号灯
                                                    Demo.buf[5] = (byte) 0x02;//170绿灯
                                                    Demo.buf[6] = (byte) 0x02;//185绿灯
                                                    Demo.outMessage(Demo.buf);
                                                    //提示信息
                                                    Demo.car_numberJB.setText("当前巷道中车辆数量:01");
                                                    Demo.carId_top.setText("车辆编号"+car+"从上半段驶离");
                                                }else {
                                                    System.out.println("上半段和200已进车，请退至等候区等待下次识别！");
                                                }
                                            } else {//进车
                                                //进车 记录进车识别标识
                                                Demo.map.put(1, dncodeHex((int) buffer[7]));
                                                //判断是170和185
                                                if (buffer[4] == 11) {//如果是170 170黄灯亮，185，200上红灯200下绿灯，200黄灯
                                                    Demo.redButton1.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton1.setIcon(Demo.GrayIcon);
                                                    Demo.yellowButton1.setIcon(Demo.YellowIcon);
                                                    Demo.redButton2.setIcon(Demo.RedIcon);
                                                    Demo.greenButton2.setIcon(Demo.GrayIcon);
                                                    Demo.yellowButton2.setIcon(Demo.GrayIcon);
                                                    Demo.redButton3.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton3.setIcon(Demo.GrayIcon);
                                                    Demo.yellowButton3.setIcon(Demo.YellowIcon);
                                                    Demo.redButton_200top.setIcon(Demo.RedIcon);
                                                    Demo.greenButton_200top.setIcon(Demo.GrayIcon);
                                                    Demo.yellowButton_200top.setIcon(Demo.GrayIcon);
                                                    //输出控制信号灯
                                                    Demo.buf[5] = (byte) 0x01;//170黄灯
                                                    Demo.buf[6] = (byte) 0x04;//185红灯
                                                    Demo.buf[7] = (byte) 0x91;//200上红灯200下绿灯，200黄灯
                                                    Demo.outMessage(Demo.buf);
                                                    //车闪标识
                                                    //Demo.carnumber_one_falg = true;
                                                    Demo.carButton1.setVisible(true);
                                                    //提示信息
                                                    Demo.car_numberJB.setText("当前巷道中车辆数量:02");
                                                    Demo.carId_top.setText("车辆编号"+car+"从-170处驶入");
                                                } else {
                                                    // 果是185 185黄灯亮 170,200上红灯
                                                    Demo.redButton1.setIcon(Demo.RedIcon);
                                                    Demo.greenButton1.setIcon(Demo.GrayIcon);
                                                    Demo.yellowButton1.setIcon(Demo.GrayIcon);
                                                    Demo.redButton2.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton2.setIcon(Demo.GrayIcon);
                                                    Demo.yellowButton2.setIcon(Demo.YellowIcon);
                                                    Demo.redButton3.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton3.setIcon(Demo.GrayIcon);
                                                    Demo.yellowButton3.setIcon(Demo.YellowIcon);
                                                    Demo.redButton_200top.setIcon(Demo.RedIcon);
                                                    Demo.greenButton_200top.setIcon(Demo.GrayIcon);
                                                    Demo.yellowButton_200top.setIcon(Demo.GrayIcon);
                                                    //输出控制信号灯
                                                    Demo.buf[5] = (byte) 0x04;//170红灯
                                                    Demo.buf[6] = (byte) 0x01;//185黄灯
                                                    Demo.buf[7] = (byte) 0x91;//200上红灯200下绿灯，200黄灯
                                                    Demo.outMessage(Demo.buf);
                                                    //车闪标识
                                                    //Demo.carnumber_one_falg = true;
                                                    Demo.carButton1.setVisible(true);
                                                    //提示信息
                                                    Demo.car_numberJB.setText("当前巷道中车辆数量:02");
                                                    Demo.carId_top.setText("车辆编号"+car+"从-185处驶入");
                                                }
                                            }
                                        }
                                        if (car.equals(Demo.car_200)){//从200进的车要从上段出车，移除car_200
                                            Demo.car_200 = "00";
                                            //Demo.carnumber_one_falg = false;
                                            Demo.carButton1.setVisible(false);
                                            if (Demo.map.size()==0){//下半段没有车进来，1，2，3变绿灯
                                                Demo.redButton1.setIcon(Demo.GrayIcon);
                                                Demo.greenButton1.setIcon(Demo.GreenIcon);
                                                Demo.yellowButton1.setIcon(Demo.GrayIcon);
                                                Demo.redButton2.setIcon(Demo.GrayIcon);
                                                Demo.greenButton2.setIcon(Demo.GreenIcon);
                                                Demo.yellowButton2.setIcon(Demo.GrayIcon);
                                                Demo.redButton3.setIcon(Demo.GrayIcon);
                                                Demo.greenButton3.setIcon(Demo.GreenIcon);
                                                Demo.yellowButton3.setIcon(Demo.GrayIcon);
                                                //输出控制信号灯
                                                Demo.buf[5] = (byte) 0x02;//170绿灯
                                                Demo.buf[6] = (byte) 0x02;//185绿灯灯
                                                Demo.buf[7] = (byte) 0x52;//200上绿灯200下绿灯，200绿灯
                                                Demo.outMessage(Demo.buf);
                                                //提示信息
                                                Demo.car_numberJB.setText("当前巷道中车辆数量:00");
                                                Demo.carId_top.setText("车辆编号"+car+"从上半段驶离");
                                            }else{//下半段有车进来
                                                //提示信息
                                                Demo.car_numberJB.setText("当前巷道中车辆数量:01");
                                                Demo.carId_top.setText("车辆编号"+car+"从上半段驶离");
                                                if (!Demo.car_200south){//下半段车没到200c处，灯变
                                                    Demo.redButton1.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton1.setIcon(Demo.GreenIcon);
                                                    Demo.yellowButton1.setIcon(Demo.GrayIcon);
                                                    Demo.redButton2.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton2.setIcon(Demo.GreenIcon);
                                                    Demo.yellowButton2.setIcon(Demo.GrayIcon);
                                                    Demo.redButton3.setIcon(Demo.RedIcon);
                                                    Demo.greenButton3.setIcon(Demo.GrayIcon);
                                                    Demo.yellowButton3.setIcon(Demo.GrayIcon);
                                                    //输出控制信号灯
                                                    Demo.buf[5] = (byte) 0x02;//170绿灯
                                                    Demo.buf[6] = (byte) 0x02;//185绿灯
                                                    Demo.buf[7] = (byte) 0x64;//200上绿灯200下红灯，200红灯
                                                    Demo.outMessage(Demo.buf);
                                                }
                                            }
                                        }
                                    }
                                    Demo.chongfu170_1 = dncodeHex((int) buffer[4]);
                                    Demo.chongfu170_2 = dncodeHex((int) buffer[5]);
                                    Demo.chongfu170_3= dncodeHex((int) buffer[7]);
                                    Demo.chu170_flag = false;
                                }else if(buffer[4] == 12){
                                    if(dncodeHex((int) buffer[4]).equals(Demo.chongfu185_1)
                                            &&dncodeHex((int) buffer[5]).equals(Demo.chongfu185_2)
                                            &&dncodeHex((int) buffer[7]).equals(Demo.chongfu185_3)){
                                        System.out.println("重复的buffer");
                                        continue;
                                    }
                                    String car = dncodeHex((int) buffer[7]);
                                    //先判断200口是否进车
                                    if (Demo.car_200.equals("00")){//200没进车
                                        if (Demo.map.size() == 2) {//巷道里有两辆车，肯定是出车，判断是上半段车还是下半段车
                                            if (car.equals(Demo.map.get(1)) || car.equals(Demo.map.get(2))){
                                                if (car.equals(Demo.map.get(1))) {//上半段车出车，移除key1，灯不变
                                                    Demo.map.remove(1);
                                                    //Demo.carnumber_one_falg = false;
                                                    Demo.carButton1.setVisible(false);
                                                    //报警信息状态改变
                                                    Demo.car_numberJB.setText("当前巷道中车辆数量:01");
                                                    Demo.carId_top.setText("车辆编号"+car+"从上半段驶离");
                                                    if (Demo.cuoche_flag == true){//有车在错车处
                                                        //200上下绿灯 200红灯 170红灯 185红灯
                                                        Demo.greenButton1.setIcon(Demo.GrayIcon);
                                                        Demo.redButton1.setIcon(Demo.RedIcon);
                                                        Demo.yellowButton1.setIcon(Demo.GrayIcon);
                                                        Demo.greenButton2.setIcon(Demo.GrayIcon);
                                                        Demo.redButton2.setIcon(Demo.RedIcon);
                                                        Demo.yellowButton2.setIcon(Demo.GrayIcon);
                                                        Demo.greenButton3.setIcon(Demo.GrayIcon);
                                                        Demo.redButton3.setIcon(Demo.RedIcon);
                                                        Demo.yellowButton3.setIcon(Demo.GrayIcon);
                                                        Demo.redButton_200top.setIcon(Demo.GrayIcon);
                                                        Demo.greenButton_200top.setIcon(Demo.GreenIcon);
                                                        Demo.yellowButton_200top.setIcon(Demo.GrayIcon);
                                                        Demo.redButton_200south.setIcon(Demo.GrayIcon);
                                                        Demo.greenButton_200south.setIcon(Demo.GreenIcon);
                                                        Demo.yellowButton_200south.setIcon(Demo.GrayIcon);
                                                        Demo.buf[5] = (byte) 0x02;
                                                        Demo.buf[6] = (byte) 0x02;
                                                        Demo.buf[7] = (byte) 0x54;
                                                        Demo.outMessage(Demo.buf);
                                                    }else {//没有车在错车处 200上绿灯 200下红灯 200红灯 170绿灯 185绿灯
                                                        Demo.greenButton1.setIcon(Demo.GreenIcon);
                                                        Demo.redButton1.setIcon(Demo.GrayIcon);
                                                        Demo.yellowButton1.setIcon(Demo.GrayIcon);
                                                        Demo.greenButton2.setIcon(Demo.GreenIcon);
                                                        Demo.redButton2.setIcon(Demo.GrayIcon);
                                                        Demo.yellowButton2.setIcon(Demo.GrayIcon);
                                                        Demo.greenButton3.setIcon(Demo.GrayIcon);
                                                        Demo.redButton3.setIcon(Demo.RedIcon);
                                                        Demo.yellowButton3.setIcon(Demo.GrayIcon);
                                                        Demo.redButton_200top.setIcon(Demo.GrayIcon);
                                                        Demo.greenButton_200top.setIcon(Demo.GreenIcon);
                                                        Demo.yellowButton_200top.setIcon(Demo.GrayIcon);
                                                        Demo.redButton_200south.setIcon(Demo.RedIcon);
                                                        Demo.greenButton_200south.setIcon(Demo.GreenIcon);
                                                        Demo.yellowButton_200south.setIcon(Demo.GrayIcon);
                                                        Demo.buf[5] = (byte) 0x04;
                                                        Demo.buf[6] = (byte) 0x04;
                                                        Demo.buf[7] = (byte) 0x64;
                                                        Demo.outMessage(Demo.buf);
                                                    }
                                                }
                                                if (car.equals(Demo.map.get(2))) {//下半段车出车，移除key2，灯不变
                                                    Demo.map.remove(2);
                                                    //Demo.carnumber_two_falg = false;
                                                    Demo.carButton2.setVisible(false);
                                                    //报警信息状态改变
                                                    Demo.car_numberJB.setText("当前巷道中车辆数量:01");
                                                    Demo.carId_south.setText("车辆编号"+car+"从上半段驶离");
                                                }
                                            }else {
                                                System.out.println("上下段都已进车，请退至等候区等待下次识别！");
                                            }
                                        } else if (Demo.map.size() == 1) {//巷道里现有一辆车，判断这辆车是出车还是进车
                                            if (Demo.map.get(1)!=null){
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
                                                    Demo.redButton_200south.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton_200south.setIcon(Demo.GreenIcon);
                                                    Demo.yellowButton_200south.setIcon(Demo.GrayIcon);
                                                    //输出控制信号灯
                                                    Demo.buf[5] = (byte) 0x02;//170绿灯
                                                    Demo.buf[6] = (byte) 0x02;//185绿灯
                                                    Demo.buf[7] = (byte) 0x52;//200绿灯
                                                    Demo.buf[8] = (byte) 0x02;//215绿灯
                                                    Demo.buf[9] = (byte) 0x02;//230绿灯
                                                    Demo.buf[10] = (byte) 0x02;//245绿灯
                                                    Demo.outMessage(Demo.buf);
                                                    Demo.map.remove(1);
                                                    //Demo.carnumber_one_falg = false;
                                                    Demo.carButton1.setVisible(false);
                                                    //报警信息状态改变
                                                    Demo.car_numberJB.setText("当前巷道中车辆数量:00");
                                                    Demo.carId_top.setText("车辆编号"+car+"从上半段驶离");
                                                }else{
                                                    System.out.println("上半段已进车，请退至等候区等待下次识别！");
                                                }
                                            }
                                            if (Demo.map.get(2)!=null){
                                                if (car.equals(Demo.map.get(2))) {//下半段车出车
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
                                                    Demo.redButton_200top.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton_200top.setIcon(Demo.GreenIcon);
                                                    Demo.yellowButton_200top.setIcon(Demo.GrayIcon);
                                                    Demo.redButton_200south.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton_200south.setIcon(Demo.GreenIcon);
                                                    Demo.yellowButton_200south.setIcon(Demo.GrayIcon);
                                                    //输出控制信号灯
                                                    Demo.buf[5] = (byte) 0x02;//170绿灯
                                                    Demo.buf[6] = (byte) 0x02;//185绿灯
                                                    Demo.buf[7] = (byte) 0x52;//200全绿灯
                                                    Demo.buf[8] = (byte) 0x02;//215绿灯
                                                    Demo.buf[9] = (byte) 0x02;//230绿灯
                                                    Demo.buf[10] = (byte) 0x02;//245绿灯
                                                    Demo.outMessage(Demo.buf);
                                                    Demo.map.remove(2);
                                                    //Demo.carnumber_two_falg = false;
                                                    Demo.carButton2.setVisible(false);
                                                    Demo.car_200south = false;
                                                    //报警信息状态改变
                                                    Demo.car_numberJB.setText("当前巷道中车辆数量:00");
                                                    //Demo.carId_south.setText("车辆编号"+car+"从上半段驶离");
                                                } else{//上半段进车 下半段有车 记录进车识别标识
                                                    Demo.map.put(1, dncodeHex((int) buffer[7]));
                                                    //判断是170和185
                                                    if (buffer[4] == 11) {//如果是170 170黄灯亮，185，200 200上红灯
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
                                                        //输出控制信号灯
                                                        Demo.buf[5] = (byte) 0x01;//170黄灯
                                                        Demo.buf[6] = (byte) 0x04;//185红灯
                                                        Demo.buf[7] = (byte) 0xA4;//200红灯 200上红灯 200下红灯
                                                        Demo.outMessage(Demo.buf);

                                                        //Demo.carnumber_one_falg = true;
                                                        Demo.carButton1.setVisible(true);
                                                        Demo.car_numberJB.setText("当前巷道中车辆数量:02");
                                                        Demo.carId_top.setText("车辆编号"+car+"从-170处驶入");
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
                                                        //输出控制信号灯
                                                        Demo.buf[5] = (byte) 0x04;//170红灯
                                                        Demo.buf[6] = (byte) 0x01;//185黄灯
                                                        Demo.buf[7] = (byte) 0xA4;//200红灯 200上红灯 200下红灯
                                                        Demo.outMessage(Demo.buf);
                                                        //Demo.carnumber_one_falg = true;
                                                        Demo.carButton1.setVisible(true);
                                                        Demo.car_numberJB.setText("当前巷道中车辆数量:02");
                                                        Demo.carId_top.setText("车辆编号"+car+"从-185处驶入");
                                                    }
                                                }
                                            }
                                        } else {//肯定是进车 map.size()==0
                                            //进车 记录进车识别标识
                                            Demo.map.put(1, dncodeHex((int) buffer[7]));
                                            //判断是170和185
                                            if (buffer[4] == 11) {//如果是170 170黄灯亮，185，200 200上红灯 200下绿灯
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
                                                //输出控制信号灯
                                                Demo.buf[5] = (byte) 0x01;//170黄灯
                                                Demo.buf[6] = (byte) 0x04;//185红灯
                                                Demo.buf[7] = (byte) 0x94;//200红灯 200上红灯 200下绿灯
                                                Demo.outMessage(Demo.buf);
                                                //车闪标识
                                                //Demo.carnumber_one_falg = true;
                                                Demo.carButton1.setVisible(true);
                                                //提示信息
                                                Demo.car_numberJB.setText("当前巷道中车辆数量:01");
                                                Demo.carId_top.setText("车辆编号"+car+"从-170处驶入");
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
                                                //输出控制信号灯
                                                Demo.buf[5] = (byte) 0x04;//170红灯
                                                Demo.buf[6] = (byte) 0x01;//185黄灯
                                                Demo.buf[7] = (byte) 0x94;//200红灯 200上红灯 200下绿灯
                                                Demo.outMessage(Demo.buf);
                                                //车闪标识
                                                //Demo.carnumber_one_falg = true;
                                                Demo.carButton1.setVisible(true);
                                                //提示信息
                                                Demo.car_numberJB.setText("当前巷道中车辆数量:01");
                                                Demo.carId_top.setText("车辆编号"+car+"从-185处驶入");
                                            }
                                        }
                                    }
                                    if (!Demo.car_200.equals("00")){//200进车了
                                        if (!car.equals(Demo.car_200)){//200的车从下半段走了，现在上半段可以进车,也可以出车,出的车只能是上半段进的车，200进的车还在下半段没出去
                                            if (Demo.map.size()==1){//出车，但200的车还没出去，170 185 变绿灯，移除key1
                                                if (car.equals(Demo.map.get(1))){
                                                    Demo.map.remove(1);
                                                    Demo.redButton1.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton1.setIcon(Demo.GreenIcon);
                                                    Demo.yellowButton1.setIcon(Demo.GrayIcon);
                                                    Demo.redButton2.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton2.setIcon(Demo.GreenIcon);
                                                    Demo.yellowButton2.setIcon(Demo.GrayIcon);
                                                    //输出控制信号灯
                                                    Demo.buf[5] = (byte) 0x02;//170绿灯
                                                    Demo.buf[6] = (byte) 0x02;//185绿灯
                                                    Demo.outMessage(Demo.buf);
                                                    //提示信息
                                                    Demo.car_numberJB.setText("当前巷道中车辆数量:01");
                                                    Demo.carId_top.setText("车辆编号"+car+"从上半段驶离");
                                                }else {
                                                    System.out.println("上半段和200已进车，请退至等候区等待下次识别！");
                                                }
                                            } else {//进车
                                                //进车 记录进车识别标识
                                                Demo.map.put(1, dncodeHex((int) buffer[7]));
                                                //判断是170和185
                                                if (buffer[4] == 11) {//如果是170 170黄灯亮，185，200上红灯200下绿灯，200黄灯
                                                    Demo.redButton1.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton1.setIcon(Demo.GrayIcon);
                                                    Demo.yellowButton1.setIcon(Demo.YellowIcon);
                                                    Demo.redButton2.setIcon(Demo.RedIcon);
                                                    Demo.greenButton2.setIcon(Demo.GrayIcon);
                                                    Demo.yellowButton2.setIcon(Demo.GrayIcon);
                                                    Demo.redButton3.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton3.setIcon(Demo.GrayIcon);
                                                    Demo.yellowButton3.setIcon(Demo.YellowIcon);
                                                    Demo.redButton_200top.setIcon(Demo.RedIcon);
                                                    Demo.greenButton_200top.setIcon(Demo.GrayIcon);
                                                    Demo.yellowButton_200top.setIcon(Demo.GrayIcon);
                                                    //输出控制信号灯
                                                    Demo.buf[5] = (byte) 0x01;//170黄灯
                                                    Demo.buf[6] = (byte) 0x04;//185红灯
                                                    Demo.buf[7] = (byte) 0x91;//200上红灯200下绿灯，200黄灯
                                                    Demo.outMessage(Demo.buf);
                                                    //车闪标识
                                                    //Demo.carnumber_one_falg = true;
                                                    Demo.carButton1.setVisible(true);
                                                    //提示信息
                                                    Demo.car_numberJB.setText("当前巷道中车辆数量:02");
                                                    Demo.carId_top.setText("车辆编号"+car+"从-170处驶入");
                                                } else {
                                                    // 果是185 185黄灯亮 170,200上红灯
                                                    Demo.redButton1.setIcon(Demo.RedIcon);
                                                    Demo.greenButton1.setIcon(Demo.GrayIcon);
                                                    Demo.yellowButton1.setIcon(Demo.GrayIcon);
                                                    Demo.redButton2.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton2.setIcon(Demo.GrayIcon);
                                                    Demo.yellowButton2.setIcon(Demo.YellowIcon);
                                                    Demo.redButton3.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton3.setIcon(Demo.GrayIcon);
                                                    Demo.yellowButton3.setIcon(Demo.YellowIcon);
                                                    Demo.redButton_200top.setIcon(Demo.RedIcon);
                                                    Demo.greenButton_200top.setIcon(Demo.GrayIcon);
                                                    Demo.yellowButton_200top.setIcon(Demo.GrayIcon);
                                                    //输出控制信号灯
                                                    Demo.buf[5] = (byte) 0x04;//170红灯
                                                    Demo.buf[6] = (byte) 0x01;//185黄灯
                                                    Demo.buf[7] = (byte) 0x91;//200上红灯200下绿灯，200黄灯
                                                    Demo.outMessage(Demo.buf);
                                                    //车闪标识
                                                    //Demo.carnumber_one_falg = true;
                                                    Demo.carButton1.setVisible(true);
                                                    //提示信息
                                                    Demo.car_numberJB.setText("当前巷道中车辆数量:02");
                                                    Demo.carId_top.setText("车辆编号"+car+"从-185处驶入");
                                                }
                                            }

                                        }
                                        if (car.equals(Demo.car_200)){//从200进的车要从上段出车，移除car_200
                                            Demo.car_200 = "00";
                                            //Demo.carnumber_one_falg = false;
                                            Demo.carButton1.setVisible(false);
                                            if (Demo.map.size()==0){//下半段没有车进来，1，2，3变绿灯
                                                Demo.redButton1.setIcon(Demo.GrayIcon);
                                                Demo.greenButton1.setIcon(Demo.GreenIcon);
                                                Demo.yellowButton1.setIcon(Demo.GrayIcon);
                                                Demo.redButton2.setIcon(Demo.GrayIcon);
                                                Demo.greenButton2.setIcon(Demo.GreenIcon);
                                                Demo.yellowButton2.setIcon(Demo.GrayIcon);
                                                Demo.redButton3.setIcon(Demo.GrayIcon);
                                                Demo.greenButton3.setIcon(Demo.GreenIcon);
                                                Demo.yellowButton3.setIcon(Demo.GrayIcon);
                                                //输出控制信号灯
                                                Demo.buf[5] = (byte) 0x02;//170绿灯
                                                Demo.buf[6] = (byte) 0x02;//185绿灯灯
                                                Demo.buf[7] = (byte) 0x52;//200上绿灯200下绿灯，200绿灯
                                                Demo.outMessage(Demo.buf);
                                                //提示信息
                                                Demo.car_numberJB.setText("当前巷道中车辆数量:00");
                                                Demo.carId_top.setText("车辆编号"+car+"从上半段驶离");
                                            }else{//下半段有车进来
                                                //提示信息
                                                Demo.car_numberJB.setText("当前巷道中车辆数量:01");
                                                Demo.carId_top.setText("车辆编号"+car+"从上半段驶离");
                                                if (!Demo.car_200south){//下半段车没到200c处，灯变
                                                    Demo.redButton1.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton1.setIcon(Demo.GreenIcon);
                                                    Demo.yellowButton1.setIcon(Demo.GrayIcon);
                                                    Demo.redButton2.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton2.setIcon(Demo.GreenIcon);
                                                    Demo.yellowButton2.setIcon(Demo.GrayIcon);
                                                    Demo.redButton3.setIcon(Demo.RedIcon);
                                                    Demo.greenButton3.setIcon(Demo.GrayIcon);
                                                    Demo.yellowButton3.setIcon(Demo.GrayIcon);
                                                    //输出控制信号灯
                                                    Demo.buf[5] = (byte) 0x02;//170绿灯
                                                    Demo.buf[6] = (byte) 0x02;//185绿灯
                                                    Demo.buf[7] = (byte) 0x64;//200上绿灯200下红灯，200红灯
                                                    Demo.outMessage(Demo.buf);
                                                }
                                            }
                                        }
                                    }

                                    Demo.chongfu185_1 = dncodeHex((int) buffer[4]);
                                    Demo.chongfu185_2 = dncodeHex((int) buffer[5]);
                                    Demo.chongfu185_3= dncodeHex((int) buffer[7]);
                                    Demo.chu185_flag = false;
                                } else if (buffer[4] == 13) {//如果是200分站
                                    if (buffer[5] == 1) {
                                        if(dncodeHex((int) buffer[4]).equals(Demo.chongfu200top_1)
                                                &&dncodeHex((int) buffer[5]).equals(Demo.chongfu200top_2)
                                                &&dncodeHex((int) buffer[7]).equals(Demo.chongfu200top_3)){
                                            System.out.println("重复的buffer");
                                            continue;
                                        }
                                        Demo.carnumber_one = dncodeHex((int) buffer[7]);
                                        if (Demo.car_200.equals("00")){//说明200没进车
                                            if (Demo.map.size() == 2) {//说明通道内有两辆车，需要错车

                                                if (Demo.carnumber_one.equals(Demo.map.get(1))) {//如果是上边来的车，上边的车先避让，200上变绿灯，200下红灯，200红灯
                                                    Demo.cuoche_flag = true;
                                                    if (Demo.birang_flag == false){
                                                        Demo.redButton_200top.setIcon(Demo.GrayIcon);
                                                        Demo.greenButton_200top.setIcon(Demo.GreenIcon);
                                                        Demo.yellowButton_200top.setIcon(Demo.GrayIcon);
                                                        Demo.redButton_200south.setIcon(Demo.RedIcon);
                                                        Demo.greenButton_200south.setIcon(Demo.GrayIcon);
                                                        Demo.yellowButton_200south.setIcon(Demo.GrayIcon);
                                                        //输出控制信号灯
                                                        Demo.buf[7] = (byte)0x64;//200上绿灯 200下红灯 200红灯
                                                        Demo.outMessage(Demo.buf);
                                                        //提示信息
                                                        Demo.car_numberJB.setText("当前巷道中车辆数量:02,错车中！");
                                                        Demo.birang_flag=true;
                                                    }else{
                                                        Demo.birang_flag=false;
                                                    }
                                                }
                                                if (Demo.carnumber_one.equals(Demo.map.get(2))) {//如果是下边来的车，说明上边的车先进行避让，200下变绿灯200上绿灯 200红灯
                                                    Demo.cuoche_flag = false;
                                                    //200上
                                                    Demo.redButton_200top.setIcon(Demo.GreenIcon);
                                                    Demo.greenButton_200top.setIcon(Demo.GrayIcon);
                                                    Demo.yellowButton_200top.setIcon(Demo.GrayIcon);
                                                    //200下
                                                    Demo.redButton_200south.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton_200south.setIcon(Demo.GreenIcon);
                                                    Demo.yellowButton_200south.setIcon(Demo.GrayIcon);
                                                    //输出控制信号灯
                                                    Demo.buf[7] = (byte)0x54;//200上绿灯 200下绿灯 200红灯
                                                    Demo.outMessage(Demo.buf);
                                                    //提示信息
                                                    Demo.car_numberJB.setText("当前巷道中车辆数量:02,错车完毕！");
                                                }
                                            }
                                            if (Demo.map.size()==1){//说明通道内有一辆车
                                                /*if (Demo.carnumber_one.equals(Demo.map.get(2))){//检测到下半段来车，下半段变绿灯
                                                    Demo.redButton_200top.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton_200top.setIcon(Demo.GreenIcon);
                                                    Demo.yellowButton_200top.setIcon(Demo.GrayIcon);
                                                    Demo.redButton_200south.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton_200south.setIcon(Demo.GreenIcon);
                                                    Demo.yellowButton_200south.setIcon(Demo.GrayIcon);
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
                                                    //输出控制信号灯
                                                    Demo.buf[7] = (byte)0x52;//200上绿灯 200下绿灯 200绿灯
                                                    Demo.buf[8] = (byte)0x02;//215绿灯
                                                    Demo.buf[9] = (byte)0x02;//230绿灯
                                                    Demo.buf[10] = (byte)0x02;//245绿灯
                                                    Demo.outMessage(Demo.buf);
                                                }*/
                                                if (Demo.carnumber_one.equals(Demo.map.get(1))){//检测到上半段来车，下半段不允许进车了，变红灯
                                                    Demo.redButton4.setIcon(Demo.RedIcon);
                                                    Demo.greenButton4.setIcon(Demo.GrayIcon);
                                                    Demo.yellowButton4.setIcon(Demo.GrayIcon);
                                                    Demo.redButton5.setIcon(Demo.RedIcon);
                                                    Demo.greenButton5.setIcon(Demo.GrayIcon);
                                                    Demo.yellowButton5.setIcon(Demo.GrayIcon);
                                                    Demo.redButton6.setIcon(Demo.RedIcon);
                                                    Demo.greenButton6.setIcon(Demo.GrayIcon);
                                                    Demo.yellowButton6.setIcon(Demo.GrayIcon);
                                                    //输出控制信号灯
                                                    Demo.buf[8] = (byte) 0x04;//215红灯
                                                    Demo.buf[9] = (byte) 0x04;//230红灯
                                                    Demo.buf[10] = (byte) 0x04;//245红灯
                                                    Demo.outMessage(Demo.buf);
                                                }

                                            }
                                        }
                                        if (!Demo.car_200.equals("00")){//说明200进车了
                                            if(Demo.carnumber_one.equals(Demo.car_200)){//说明是200进的车往上边走了，下半区段4,5,6变绿灯 200上变红灯
                                                Demo.redButton4.setIcon(Demo.GrayIcon);
                                                Demo.greenButton4.setIcon(Demo.GreenIcon);
                                                Demo.yellowButton4.setIcon(Demo.GrayIcon);
                                                Demo.redButton5.setIcon(Demo.GrayIcon);
                                                Demo.greenButton5.setIcon(Demo.GreenIcon);
                                                Demo.yellowButton5.setIcon(Demo.GrayIcon);
                                                Demo.redButton6.setIcon(Demo.GrayIcon);
                                                Demo.greenButton6.setIcon(Demo.GreenIcon);
                                                Demo.yellowButton6.setIcon(Demo.GrayIcon);
                                                Demo.redButton_200top.setIcon(Demo.RedIcon);
                                                Demo.greenButton_200top.setIcon(Demo.GrayIcon);
                                                Demo.yellowButton_200top.setIcon(Demo.GrayIcon);

                                                //车闪标识
                                                //Demo.carnumber_one_falg = true;
                                                Demo.carButton1.setVisible(true);
                                                Demo.buf[7] = (byte) 0x02; //200黄 ，200上红 200下绿
                                                Demo.buf[8] = (byte) 0x02;//215绿灯
                                                Demo.buf[9] = (byte) 0x02;//230绿灯
                                                Demo.buf[10] = (byte) 0x02;//245绿灯
                                                Demo.outMessage(Demo.buf);
                                            }
                                            if (!Demo.carnumber_one.equals(Demo.car_200)){//说明200进的车往下边走了，上半段变绿灯，上半段的车经过200a,下半区段变红灯
                                                Demo.car_200top = true;
                                                Demo.redButton4.setIcon(Demo.RedIcon);
                                                Demo.greenButton4.setIcon(Demo.GrayIcon);
                                                Demo.yellowButton4.setIcon(Demo.GrayIcon);
                                                Demo.redButton5.setIcon(Demo.RedIcon);
                                                Demo.greenButton5.setIcon(Demo.GrayIcon);
                                                Demo.yellowButton5.setIcon(Demo.GrayIcon);
                                                Demo.redButton6.setIcon(Demo.RedIcon);
                                                Demo.greenButton6.setIcon(Demo.GrayIcon);
                                                Demo.yellowButton6.setIcon(Demo.GrayIcon);
                                                //输出控制信号灯
                                                Demo.buf[8] = (byte) 0x04;//215红灯
                                                Demo.buf[9] = (byte) 0x04;//230红灯
                                                Demo.buf[10] = (byte) 0x04;//245红灯
                                                Demo.outMessage(Demo.buf);
                                            }
                                        }

                                        Demo.chongfu200top_1 = dncodeHex((int) buffer[4]);
                                        Demo.chongfu200top_2 = dncodeHex((int) buffer[5]);
                                        Demo.chongfu200top_3= dncodeHex((int) buffer[7]);
                                        Demo.chu200top_flag = false;
                                    } else if (buffer[5] == 2) {
                          /* if (Demo.map.size() == 2) {//有两辆车，说明需要错车
                                //记录当前在2的车辆信息，提示哪辆车在错车
                                Demo.cuoche_200 = dncodeHex((int) buffer[6]);
                                if (Demo.cuoche_flag == false){//当前没有车在避让
                                    if (Demo.cuoche_200.equals(Demo.map.get(1))) {//先检测到上半段车进入到避让处,200上变绿灯，让下半段车先行
                                        Demo.redButton_200top.setIcon(Demo.GrayIcon);
                                        Demo.greenButton_200top.setIcon(Demo.GreenIcon);
                                        Demo.yellowButton_200top.setIcon(Demo.GrayIcon);
                                        //输出控制信号灯
                                        Demo.buf[7] = (byte) 0x64;//200上绿灯 200下红灯 200红灯
                                        Demo.outMessage(Demo.buf);
                                        //提示信息
                                        Demo.car_numberJB.setText("当前巷道中车辆数量:02,错车中！");
                                        Demo.cuoche_flag =true;//避让标志设为true
                                    }
                                    if (Demo.cuoche_200.equals(Demo.map.get(2))) {//先检测到下半段车进入到避让处，200下变绿灯，让上半段车先行
                                        Demo.redButton_200south.setIcon(Demo.GrayIcon);
                                        Demo.greenButton_200south.setIcon(Demo.GreenIcon);
                                        Demo.yellowButton_200south.setIcon(Demo.GrayIcon);
                                        //输出控制信号灯
                                        Demo.buf[7] = (byte) 0x94;//200上红灯 200下绿灯 200红灯
                                        Demo.outMessage(Demo.buf);
                                        //提示信息
                                        Demo.car_numberJB.setText("当前巷道中车辆数量:02,错车中！");
                                        Demo.cuoche_flag =true;//避让标志设为true
                                    }
                                }else{//当前有车在避让
                                    System.out.println("当前有车在避让，不做任何改变！");
                                    Demo.cuoche_flag =false;//避让标志设为false
                                }
                            }*/
                                    } else if (buffer[5] == 3) {
                                        if(dncodeHex((int) buffer[4]).equals(Demo.chongfu200south_1)
                                                &&dncodeHex((int) buffer[5]).equals(Demo.chongfu200south_2)
                                                &&dncodeHex((int) buffer[7]).equals(Demo.chongfu200south_3)){
                                            System.out.println("重复的buffer");
                                            continue;
                                        }
                                        Demo.carnumber_two = dncodeHex((int) buffer[7]);
                                        if (Demo.car_200.equals("00")){//说明200没进车
                                            if (Demo.map.size() == 2) {//说明通道内有两辆车，需要错车
                                                if (Demo.carnumber_two.equals(Demo.map.get(1))) {//如果是上边来的车，说明下边的车在避让，200上变绿灯
                                                    Demo.redButton_200top.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton_200top.setIcon(Demo.GreenIcon);
                                                    Demo.yellowButton_200top.setIcon(Demo.GrayIcon);
                                                    //输出控制信号灯
                                                    Demo.buf[7] = (byte)0x54;//200上绿灯 200下绿灯 200红灯
                                                    Demo.outMessage(Demo.buf);
                                                    //提示信息
                                                    Demo.car_numberJB.setText("当前巷道中车辆数量:02,错车完毕！");
                                                    Demo.cuoche_flag = true;
                                                }
                                                if (Demo.carnumber_two.equals(Demo.map.get(2))) {//如果是下边来的车，说明下边的车先进行避让，200下变绿灯200上红灯 200红灯
                                                    Demo.cuoche_flag = true;
                                                    if (Demo.birang_flag==false){
                                                        //200上
                                                        Demo.redButton_200top.setIcon(Demo.RedIcon);
                                                        Demo.greenButton_200top.setIcon(Demo.GrayIcon);
                                                        Demo.yellowButton_200top.setIcon(Demo.GrayIcon);
                                                        //200下
                                                        Demo.redButton_200south.setIcon(Demo.GrayIcon);
                                                        Demo.greenButton_200south.setIcon(Demo.GreenIcon);
                                                        Demo.yellowButton_200south.setIcon(Demo.GrayIcon);
                                                        //输出控制信号灯
                                                        Demo.buf[7] = (byte)0x94;//200上红灯 200下绿灯 200红灯
                                                        Demo.outMessage(Demo.buf);
                                                        //提示信息
                                                        Demo.car_numberJB.setText("当前巷道中车辆数量:02,错车中！");
                                                        Demo.birang_flag=true;
                                                    }else{
                                                        Demo.birang_flag=false;
                                                    }
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
                                                    //输出控制信号灯
                                                    Demo.buf[5] = (byte) 0x04;//170红灯
                                                    Demo.buf[6] = (byte) 0x04;//185红灯
                                                    Demo.buf[7] = (byte) 0x64;//200上绿灯 200下红灯，200红灯
                                                    Demo.outMessage(Demo.buf);
                                                }
                                               /* if (Demo.carnumber_two.equals(Demo.map.get(1))){//检测到上半段来车，上半段变绿灯
                                                    Demo.redButton1.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton1.setIcon(Demo.GreenIcon);
                                                    Demo.yellowButton1.setIcon(Demo.GrayIcon);
                                                    Demo.redButton2.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton2.setIcon(Demo.GreenIcon);
                                                    Demo.yellowButton2.setIcon(Demo.GrayIcon);
                                                    Demo.redButton3.setIcon(Demo.RedIcon);
                                                    Demo.greenButton3.setIcon(Demo.GrayIcon);
                                                    Demo.yellowButton3.setIcon(Demo.GrayIcon);
                                                    Demo.redButton_200top.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton_200top.setIcon(Demo.GreenIcon);
                                                    Demo.yellowButton_200top.setIcon(Demo.GrayIcon);
                                                    Demo.redButton_200south.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton_200south.setIcon(Demo.GreenIcon);
                                                    Demo.yellowButton_200south.setIcon(Demo.GrayIcon);
                                                    //输出控制信号灯
                                                    Demo.buf[5] = (byte) 0x02;//170绿灯
                                                    Demo.buf[6] = (byte) 0x02;//185绿灯
                                                    Demo.buf[7] = (byte) 0x54;//200上绿灯 200下绿灯，200红灯
                                                    Demo.outMessage(Demo.buf);
                                                }*/
                                            }
                                        }
                                        if (!Demo.car_200.equals("00")){//说明200进车了
                                            if (Demo.carnumber_two.equals(Demo.car_200)){//200进的车往下半段走，上半段变绿灯 200下变红灯 200上绿灯 200黄灯
                                                Demo.redButton1.setIcon(Demo.GrayIcon);
                                                Demo.greenButton1.setIcon(Demo.GreenIcon);
                                                Demo.yellowButton1.setIcon(Demo.GrayIcon);
                                                Demo.redButton2.setIcon(Demo.GrayIcon);
                                                Demo.greenButton2.setIcon(Demo.GreenIcon);
                                                Demo.yellowButton2.setIcon(Demo.GrayIcon);
                                                Demo.redButton_200south.setIcon(Demo.RedIcon);
                                                Demo.greenButton_200south.setIcon(Demo.GrayIcon);
                                                Demo.yellowButton_200south.setIcon(Demo.GrayIcon);
                                                //输出控制信号灯
                                                Demo.buf[5] = (byte) 0x02;//170绿灯
                                                Demo.buf[6] = (byte) 0x02;//185绿灯
                                                Demo.buf[7] = (byte) 0x61;//200下变红灯 200上绿灯 200黄灯
                                                Demo.outMessage(Demo.buf);
                                                //车闪标识
                                                //Demo.carnumber_two_falg = true;
                                                Demo.carButton2.setVisible(true);
                                            }
                                            if (!Demo.carnumber_two.equals(Demo.car_200)){//200进的车往上半段走了，下半段的车经过200c，此时200进的车还没出站
                                                Demo.car_200south = true;
                                                Demo.redButton1.setIcon(Demo.RedIcon);
                                                Demo.greenButton1.setIcon(Demo.GrayIcon);
                                                Demo.yellowButton1.setIcon(Demo.GrayIcon);
                                                Demo.redButton2.setIcon(Demo.RedIcon);
                                                Demo.greenButton2.setIcon(Demo.GrayIcon);
                                                Demo.yellowButton2.setIcon(Demo.GrayIcon);
                                                Demo.redButton3.setIcon(Demo.RedIcon);
                                                Demo.greenButton3.setIcon(Demo.GrayIcon);
                                                Demo.yellowButton3.setIcon(Demo.GrayIcon);
                                                //输出控制信号灯
                                                Demo.buf[5] = (byte) 0x04;//170红灯
                                                Demo.buf[6] = (byte) 0x04;//185红灯
                                                Demo.buf[7] = (byte) 0x61;//200上绿灯 200下红灯 200黄灯
                                                Demo.outMessage(Demo.buf);
                                            }
                                        }
                                        Demo.chongfu200south_1 = dncodeHex((int) buffer[4]);
                                        Demo.chongfu200south_2 = dncodeHex((int) buffer[5]);
                                        Demo.chongfu200south_3 = dncodeHex((int) buffer[7]);
                                        Demo.chu200south_flag = false;
                                    } else if (buffer[5] == 4) {
                                        if(dncodeHex((int) buffer[4]).equals(Demo.chongfu200_1)
                                                &&dncodeHex((int) buffer[5]).equals(Demo.chongfu200_2)
                                                &&dncodeHex((int) buffer[7]).equals(Demo.chongfu200_3)){
                                            System.out.println("重复的buffer");
                                            continue;
                                        }
                                        String car = dncodeHex((int) buffer[7]);
                                        if (Demo.map.size()==2){//巷道里有两辆车，肯定是出车,不需要变灯
                                            if (car.equals(Demo.map.get(1)) || car.equals(Demo.map.get(2))){
                                                if (car.equals(Demo.map.get(1))){//上半段车出车，移除key1
                                                    Demo.map.remove(1);
                                                    //Demo.carnumber_one_falg = false;
                                                    Demo.carButton1.setVisible(false);
                                                    //提示信息
                                                    Demo.car_numberJB.setText("当前巷道中车辆数量:01");
                                                    Demo.carId_top.setText("车辆编号"+car+"从-200处驶离");
                                                    Demo.cuoche_flag = false;
                                                    Demo.redButton_200top.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton_200top.setIcon(Demo.GreenIcon);
                                                    Demo.yellowButton_200top.setIcon(Demo.GrayIcon);
                                                    Demo.redButton_200south.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton_200south.setIcon(Demo.GreenIcon);
                                                    Demo.yellowButton_200south.setIcon(Demo.GrayIcon);
                                                    Demo.buf[7] = (byte) 0x54;//200上绿灯 200下红灯 200黄灯
                                                    Demo.outMessage(Demo.buf);
                                                }
                                                if (car.equals(Demo.map.get(2))){//下半段车出车
                                                    Demo.map.remove(2);
                                                    //Demo.carnumber_two_falg = false;
                                                    Demo.carButton2.setVisible(false);
                                                    //提示信息
                                                    Demo.car_numberJB.setText("当前巷道中车辆数量:01");
                                                    Demo.carId_south.setText("车辆编号"+car+"从-200处驶离");
                                                    Demo.cuoche_flag = false;
                                                    Demo.redButton_200top.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton_200top.setIcon(Demo.GreenIcon);
                                                    Demo.yellowButton_200top.setIcon(Demo.GrayIcon);
                                                    Demo.redButton_200south.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton_200south.setIcon(Demo.GreenIcon);
                                                    Demo.yellowButton_200south.setIcon(Demo.GrayIcon);
                                                    Demo.buf[7] = (byte) 0x54;//200上绿灯 200下红灯 200黄灯
                                                    Demo.outMessage(Demo.buf);
                                                }
                                            }else{
                                                System.out.println("上下半段已进车，请退至等候区等待下次识别！");
                                            }
                                        }else if (Demo.map.size()==1){//也是出车，需要变灯
                                            if (car.equals(Demo.map.get(1)) || car.equals(Demo.map.get(2))) {
                                                //出车
                                                //判断车辆信息是那个区段进来的车，对应区段的绿灯亮
                                                if (car.equals(Demo.map.get(1))) {
                                                    Demo.map.remove(1);//出车移除数据
                                                    //Demo.carnumber_one_falg = false;
                                                    Demo.carButton1.setVisible(false);
                                                    Demo.car_200top = false;
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
                                                    Demo.redButton_200south.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton_200south.setIcon(Demo.GreenIcon);
                                                    Demo.yellowButton_200south.setIcon(Demo.GrayIcon);
                                                    //输出控制信号灯
                                                    Demo.buf[5] = (byte) 0x02;//170绿灯
                                                    Demo.buf[6] = (byte) 0x02;//185绿灯
                                                    Demo.buf[7] = (byte) 0x52;//200全绿灯
                                                    Demo.buf[8] = (byte) 0x02;//215绿灯
                                                    Demo.buf[9] = (byte) 0x02;//230绿灯
                                                    Demo.buf[10] = (byte) 0x02;//245绿灯
                                                    Demo.outMessage(Demo.buf);
                                                    //提示信息
                                                    Demo.car_numberJB.setText("当前巷道中车辆数量:00");
                                                    Demo.carId_top.setText("车辆编号"+car+"从-200处驶离");
                                                } else {
                                                    Demo.map.remove(2);//出车移除数据
                                                    //Demo.carnumber_two_falg = false;
                                                    Demo.carButton2.setVisible(false);
                                                    Demo.car_200south = false;
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
                                                    Demo.redButton_200top.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton_200top.setIcon(Demo.GreenIcon);
                                                    Demo.yellowButton_200top.setIcon(Demo.GrayIcon);
                                                    Demo.redButton_200south.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton_200south.setIcon(Demo.GreenIcon);
                                                    Demo.yellowButton_200south.setIcon(Demo.GrayIcon);
                                                    //输出控制信号灯
                                                    Demo.buf[5] = (byte) 0x02;//170绿灯
                                                    Demo.buf[6] = (byte) 0x02;//185绿灯
                                                    Demo.buf[7] = (byte) 0x52;//200全绿灯
                                                    Demo.buf[8] = (byte) 0x02;//215绿灯
                                                    Demo.buf[9] = (byte) 0x02;//230绿灯
                                                    Demo.buf[10] = (byte) 0x02;//245绿灯
                                                    Demo.outMessage(Demo.buf);
                                                    //提示信息
                                                    Demo.car_numberJB.setText("当前巷道中车辆数量:00");
                                                    Demo.carId_south.setText("车辆编号"+car+"从-200处驶离");
                                                }
                                            }else{
                                                System.out.println("已进车，请退至等候区等待下次识别");
                                            }
                                        }else {//map.size == 0，肯定是进车，全部变红灯，只有200的车能行驶，同时记录200标识
                                            if (Demo.car_200.equals("00")){//200还没有车进来
                                                Demo.car_200 =dncodeHex((int) buffer[7]);
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
                                                //输出控制信号灯
                                                Demo.buf[5] = (byte) 0x04;//170红灯
                                                Demo.buf[6] = (byte) 0x04;//185红灯
                                                Demo.buf[7] = (byte) 0x51;//200上绿灯 200下绿灯 200黄灯
                                                Demo.buf[8] = (byte) 0x04;//215红灯
                                                Demo.buf[9] = (byte) 0x04;//230红灯
                                                Demo.buf[10] = (byte) 0x04;//245红灯
                                                Demo.outMessage(Demo.buf);
                                                //提示信息
                                                Demo.car_numberJB.setText("当前巷道中车辆数量:01");
                                                Demo.carId_top.setText("车辆编号"+car+"从-200处驶入");
                                            }else{//200有车进来
                                                if (car.equals(Demo.car_200)){//从200进的车从200出去了
                                                    //全绿灯
                                                    Demo.car_200 = "00";
                                                    Demo.redButton1.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton1.setIcon(Demo.GreenIcon);
                                                    Demo.yellowButton1.setIcon(Demo.GrayIcon);
                                                    Demo.redButton2.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton2.setIcon(Demo.GreenIcon);
                                                    Demo.yellowButton2.setIcon(Demo.GrayIcon);
                                                    Demo.redButton_200top.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton_200top.setIcon(Demo.GreenIcon);
                                                    Demo.yellowButton_200top.setIcon(Demo.GrayIcon);
                                                    Demo.redButton3.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton3.setIcon(Demo.GreenIcon);
                                                    Demo.yellowButton3.setIcon(Demo.GrayIcon);
                                                    Demo.redButton_200south.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton_200south.setIcon(Demo.GreenIcon);
                                                    Demo.yellowButton_200south.setIcon(Demo.GrayIcon);
                                                    Demo.redButton4.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton4.setIcon(Demo.GreenIcon);
                                                    Demo.yellowButton4.setIcon(Demo.GrayIcon);
                                                    Demo.redButton5.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton5.setIcon(Demo.GreenIcon);
                                                    Demo.yellowButton5.setIcon(Demo.GrayIcon);
                                                    Demo.redButton6.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton6.setIcon(Demo.GreenIcon);
                                                    Demo.yellowButton6.setIcon(Demo.GrayIcon);
                                                    //Demo.carnumber_one_falg = false;
                                                    Demo.carButton1.setVisible(false);
                                                    //Demo.carnumber_two_falg = false;
                                                    Demo.carButton2.setVisible(false);
                                                    //控制信号灯字节数组
                                                    Demo.buf[0] = (byte) 0x06;
                                                    Demo.buf[1] = (byte) 0x00;
                                                    Demo.buf[2] = (byte) 0x00;
                                                    Demo.buf[3] = (byte) 0x00;
                                                    Demo.buf[4] = (byte) 0x01;
                                                    Demo.buf[5] = (byte) 0x02;
                                                    Demo.buf[6] = (byte) 0x02;
                                                    Demo.buf[7] = (byte) 0x52;
                                                    Demo.buf[8] = (byte) 0x02;
                                                    Demo.buf[9] = (byte) 0x02;
                                                    Demo.buf[10] = (byte) 0x02;
                                                    Demo.outMessage(Demo.buf);
                                                }else{
                                                    System.out.println("200分站已进车，请退至等候区等待下次识别");
                                                }
                                            }
                                        }
                                        Demo.chongfu200_1 = dncodeHex((int) buffer[4]);
                                        Demo.chongfu200_2 = dncodeHex((int) buffer[5]);
                                        Demo.chongfu200_3= dncodeHex((int) buffer[7]);
                                        Demo.chu200_flag = false;
                                    } else {
                                        System.out.println("200分站错误的rfid站号码");
                                    }

                                } else if (buffer[4] == 14) {
                                    if(dncodeHex((int) buffer[4]).equals(Demo.chongfu215_1)
                                            &&dncodeHex((int) buffer[5]).equals(Demo.chongfu215_2)
                                            &&dncodeHex((int) buffer[7]).equals(Demo.chongfu215_3)){
                                        System.out.println("重复的buffer");
                                        continue;
                                    }
                                    String car = dncodeHex((int) buffer[7]);
                                    if(Demo.car_200.equals("00")){//200没进车或者是已经出去
                                        if (Demo.map.size()==2){//肯定是出车
                                            if (car.equals(Demo.map.get(1)) || car.equals(Demo.map.get(2))){
                                                if (car.equals(Demo.map.get(1))){//上半段车出车，移除key1
                                                    Demo.map.remove(1);
                                                    //Demo.carnumber_one_falg = false;
                                                    Demo.carButton1.setVisible(false);
                                                    //提示信息
                                                    Demo.car_numberJB.setText("当前巷道中车辆数量:01");
                                                    Demo.carId_top.setText("车辆编号"+car+"从下半段驶离");
                                                }
                                                if (car.equals(Demo.map.get(2))){//下半段车出车，移除key2
                                                    Demo.map.remove(2);
                                                    //Demo.carnumber_two_falg = false;
                                                    Demo.carButton2.setVisible(false);
                                                    //提示信息
                                                    Demo.car_numberJB.setText("当前巷道中车辆数量:01");
                                                    Demo.carId_south.setText("车辆编号"+car+"从下半段驶离");
                                                    if (Demo.cuoche_flag ==true){ //有车在错车处
                                                        //200上下绿灯 200红灯 200红灯 215红灯 230红灯 245红灯
                                                        Demo.greenButton_200top.setIcon(Demo.GreenIcon);
                                                        Demo.redButton_200top.setIcon(Demo.GrayIcon);
                                                        Demo.yellowButton_200top.setIcon(Demo.GrayIcon);
                                                        Demo.greenButton_200south.setIcon(Demo.GreenIcon);
                                                        Demo.redButton_200south.setIcon(Demo.GrayIcon);
                                                        Demo.yellowButton_200south.setIcon(Demo.GrayIcon);
                                                        Demo.buf[7] = (byte) 0x54;
                                                        Demo.outMessage(Demo.buf);
                                                    }else {//没有车在错车处
                                                        //200上红灯 200红灯 200下绿灯 215 230 245绿灯
                                                        Demo.greenButton_200top.setIcon(Demo.GrayIcon);
                                                        Demo.redButton_200top.setIcon(Demo.RedIcon);
                                                        Demo.yellowButton_200top.setIcon(Demo.GrayIcon);
                                                        Demo.greenButton_200south.setIcon(Demo.GreenIcon);
                                                        Demo.redButton_200south.setIcon(Demo.GrayIcon);
                                                        Demo.yellowButton_200south.setIcon(Demo.GrayIcon);
                                                        Demo.greenButton3.setIcon(Demo.GrayIcon);
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
                                                        Demo.buf[7] = (byte) 0x94;
                                                        Demo.buf[8] = (byte) 0x02;
                                                        Demo.buf[9] = (byte) 0x02;
                                                        Demo.buf[10] = (byte) 0x02;
                                                        Demo.outMessage(Demo.buf);
                                                    }
                                                }
                                            }else {
                                                System.out.println("上下半段已进车，请退至等候区等待下次识别！");
                                            }
                                        }else if (Demo.map.size()==1){//巷道里存在一辆车。判断这辆车是进车还是出车
                                            if (Demo.map.get(2)!=null){//下半段进的车
                                                if (car.equals(Demo.map.get(2))){//出车
                                                    Demo.map.remove(2);
                                                    //Demo.carnumber_two_falg = false;
                                                    Demo.carButton2.setVisible(false);
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
                                                    //输出控制信号灯
                                                    Demo.buf[5] = (byte) 0x02;//170绿灯
                                                    Demo.buf[6] = (byte) 0x02;//185绿灯
                                                    Demo.buf[7] = (byte) 0x52;//200全绿灯
                                                    Demo.buf[8] = (byte) 0x02;//215绿灯
                                                    Demo.buf[9] = (byte) 0x02;//230绿灯
                                                    Demo.buf[10] = (byte) 0x02;//245绿灯
                                                    Demo.outMessage(Demo.buf);
                                                    //提示信息
                                                    Demo.car_numberJB.setText("当前巷道中车辆数量:00");
                                                    Demo.carId_south.setText("车辆编号"+car+"从下半段驶离");
                                                }else{
                                                    System.out.println("下半段已进车，请退置等候区等待下次识别！");
                                                }
                                            }
                                            if (Demo.map.get(1)!=null){//上半段进来的车
                                                if (car.equals(Demo.map.get(1))) {//上半段车出车
                                                    Demo.map.remove(1);
                                                    //Demo.carnumber_one_falg = false;
                                                    Demo.carButton1.setVisible(false);
                                                    Demo.car_200top = false;
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
                                                    //输出控制信号灯
                                                    Demo.buf[5] = (byte) 0x02;//170绿灯
                                                    Demo.buf[6] = (byte) 0x02;//185绿灯
                                                    Demo.buf[7] = (byte) 0x52;//200全绿灯
                                                    Demo.buf[8] = (byte) 0x02;//215绿灯
                                                    Demo.buf[9] = (byte) 0x02;//230绿灯
                                                    Demo.buf[10] = (byte) 0x02;//245绿灯
                                                    Demo.outMessage(Demo.buf);
                                                    //提示信息
                                                    Demo.car_numberJB.setText("当前巷道中车辆数量:00");
                                                    Demo.carId_top.setText("车辆编号"+car+"从下半段驶离");
                                                }else{//下半段进车，上半段有车
                                                    Demo.map.put(2, dncodeHex((int) buffer[7]));
                                                    //判断是215、230、245
                                                    if (buffer[4] == 14) {//如果是215 215黄灯亮，200 ,230，245红灯
                                                        //Demo.carnumber_two_falg = true;
                                                        Demo.carButton2.setVisible(true);
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
                                                        //输出控制信号灯
                                                        Demo.buf[7] = (byte) 0xA4;//200上红灯 200下红灯 200红灯
                                                        Demo.buf[8] = (byte) 0x01;//215黄灯
                                                        Demo.buf[9] = (byte) 0x04;//230红灯
                                                        Demo.buf[10] = (byte) 0x04;//245红灯
                                                        Demo.outMessage(Demo.buf);
                                                        //提示信息
                                                        Demo.car_numberJB.setText("当前巷道中车辆数量:02");
                                                        Demo.carId_south.setText("车辆编号"+car+"从-215处驶入");
                                                    } else if (buffer[4] == 15) {//如果是230 230黄灯亮 200,215,245红灯
                                                        //Demo.carnumber_two_falg = true;
                                                        Demo.carButton2.setVisible(true);
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
                                                        //输出控制信号灯
                                                        Demo.buf[7] = (byte) 0xA4;//200上红灯 200下红灯 200红灯
                                                        Demo.buf[8] = (byte) 0x04;//215红灯
                                                        Demo.buf[9] = (byte) 0x01;//230黄灯
                                                        Demo.buf[10] = (byte) 0x04;//245红灯
                                                        Demo.outMessage(Demo.buf);
                                                        //提示信息
                                                        Demo.car_numberJB.setText("当前巷道中车辆数量:02");
                                                        Demo.carId_south.setText("车辆编号"+car+"从-230处驶入");
                                                    } else { //如果是245 245黄灯亮 200,215、230红灯
                                                        //Demo.carnumber_two_falg = true;
                                                        Demo.carButton2.setVisible(true);
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
                                                        //输出控制信号灯
                                                        Demo.buf[7] = (byte) 0xA4;//200上红灯 200下红灯 200红灯
                                                        Demo.buf[8] = (byte) 0x04;//215红灯
                                                        Demo.buf[9] = (byte) 0x04;//230红灯
                                                        Demo.buf[10] = (byte) 0x01;//245黄灯
                                                        Demo.outMessage(Demo.buf);
                                                        //提示信息
                                                        Demo.car_numberJB.setText("当前巷道中车辆数量:02");
                                                        Demo.carId_south.setText("车辆编号"+car+"从-245处驶入");
                                                    }
                                                }
                                            }
                                        } else {//肯定是进车 记录进车识别标识 map.size()==0
                                            Demo.map.put(2, dncodeHex((int) buffer[7]));
                                            //判断是215、230、245
                                            if (buffer[4] == 14) {//如果是215 215黄灯亮，200，230，245红灯
                                                //Demo.carnumber_two_falg = true;
                                                Demo.carButton2.setVisible(true);
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
                                                //输出控制信号灯
                                                Demo.buf[7] = (byte) 0x64;//200上绿灯 200下红灯 200红灯
                                                Demo.buf[8] = (byte) 0x01;//215黄灯
                                                Demo.buf[9] = (byte) 0x04;//230红灯
                                                Demo.buf[10] = (byte) 0x04;//245红灯
                                                Demo.outMessage(Demo.buf);
                                                //提示信息
                                                Demo.car_numberJB.setText("当前巷道中车辆数量:01");
                                                Demo.carId_south.setText("车辆编号"+car+"从-215处驶入");
                                            } else if (buffer[4] == 15) {//如果是230 230黄灯亮 200，215,245红灯
                                                //Demo.carnumber_two_falg = true;
                                                Demo.carButton2.setVisible(true);
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
                                                //输出控制信号灯
                                                Demo.buf[7] = (byte) 0x64;//200上绿灯 200下红灯 200红灯
                                                Demo.buf[8] = (byte) 0x04;//215红灯
                                                Demo.buf[9] = (byte) 0x01;//230黄灯
                                                Demo.buf[10] = (byte) 0x04;//245红灯
                                                Demo.outMessage(Demo.buf);
                                                //提示信息
                                                Demo.car_numberJB.setText("当前巷道中车辆数量:01");
                                                Demo.carId_south.setText("车辆编号"+car+"从-230处驶入");
                                            } else { //如果是245 245黄灯亮 200， 215、230红灯
                                                //Demo.carnumber_two_falg = true;
                                                Demo.carButton2.setVisible(true);
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
                                                //输出控制信号灯
                                                Demo.buf[7] = (byte) 0x64;//200上绿灯 200下红灯 200红灯
                                                Demo.buf[8] = (byte) 0x04;//215红灯
                                                Demo.buf[9] = (byte) 0x04;//230红灯
                                                Demo.buf[10] = (byte) 0x01;//245黄灯
                                                Demo.outMessage(Demo.buf);
                                                //提示信息
                                                Demo.car_numberJB.setText("当前巷道中车辆数量:01");
                                                Demo.carId_south.setText("车辆编号"+car+"从-245处驶入");
                                            }
                                        }
                                    }
                                    if (!Demo.car_200.equals("00")){//200进车了
                                        if (!car.equals(Demo.car_200)){//200进的车往上半区段走了，判断此时下半段的车是进车出车
                                            if (Demo.map.size()==1){
                                                if (car.equals(Demo.map.get(2))){//出车，此时200进的车还没从上半段出去
                                                    Demo.map.remove(2);
                                                    //Demo.carnumber_two_falg = false;
                                                    Demo.carButton2.setVisible(false);
                                                    Demo.car_200south = false;
                                                    // 4,5,6变绿灯
                                                    Demo.redButton4.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton4.setIcon(Demo.GreenIcon);
                                                    Demo.yellowButton4.setIcon(Demo.GrayIcon);
                                                    Demo.redButton5.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton5.setIcon(Demo.GreenIcon);
                                                    Demo.yellowButton5.setIcon(Demo.GrayIcon);
                                                    Demo.redButton6.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton6.setIcon(Demo.GreenIcon);
                                                    Demo.yellowButton6.setIcon(Demo.GrayIcon);
                                                    //输出控制信号灯
                                                    Demo.buf[8] = (byte) 0x02;//215绿灯
                                                    Demo.buf[9] = (byte) 0x02;//230绿灯
                                                    Demo.buf[10] = (byte) 0x02;//245绿灯
                                                    Demo.outMessage(Demo.buf);
                                                    //提示信息
                                                    Demo.car_numberJB.setText("当前巷道中车辆数量:01");
                                                    Demo.carId_south.setText("车辆编号"+car+"从下半段驶离");
                                                }else{
                                                    System.out.println("200和下半段已经进车，请退至等候区等待下次识别！");
                                                }
                                            }else{//进车
                                                Demo.map.put(2, dncodeHex((int) buffer[7]));
                                                //判断是215、230、245
                                                if (buffer[4] == 14) {//如果是215 215黄灯亮，200黄灯 ，230，245红灯
                                                    Demo.redButton3.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton3.setIcon(Demo.GrayIcon);
                                                    Demo.yellowButton3.setIcon(Demo.YellowIcon);
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
                                                    //输出控制信号灯
                                                    Demo.buf[7] = (byte) 0x61;//200上绿灯 200下红灯 200黄灯
                                                    Demo.buf[8] = (byte) 0x01;//215黄灯
                                                    Demo.buf[9] = (byte) 0x04;//230红灯
                                                    Demo.buf[10] = (byte) 0x04;//245红灯
                                                    Demo.outMessage(Demo.buf);
                                                    //提示信息
                                                    Demo.car_numberJB.setText("当前巷道中车辆数量:02");
                                                    Demo.carId_south.setText("车辆编号"+car+"从-215处驶入");
                                                    //车闪标识
                                                    //Demo.carnumber_two_falg = true;
                                                    Demo.carButton2.setVisible(true);
                                                } else if (buffer[4] == 15) {//如果是230 230黄灯亮 200黄灯 ，215,245红灯
                                                    Demo.redButton3.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton3.setIcon(Demo.GrayIcon);
                                                    Demo.yellowButton3.setIcon(Demo.YellowIcon);
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
                                                    //输出控制信号灯
                                                    Demo.buf[7] = (byte) 0x61;//200上绿灯 200下红灯 200黄灯
                                                    Demo.buf[8] = (byte) 0x04;//215红灯
                                                    Demo.buf[9] = (byte) 0x01;//230黄灯
                                                    Demo.buf[10] = (byte) 0x04;//245红灯
                                                    Demo.outMessage(Demo.buf);
                                                    //提示信息
                                                    Demo.car_numberJB.setText("当前巷道中车辆数量:02");
                                                    Demo.carId_south.setText("车辆编号"+car+"从-230处驶入");
                                                    //车闪标识
                                                    //Demo.carnumber_two_falg = true;
                                                    Demo.carButton2.setVisible(true);
                                                    System.out.println("5号站进车！");
                                                } else { //如果是245 245黄灯亮 200， 215、230红灯
                                                    Demo.redButton3.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton3.setIcon(Demo.GrayIcon);
                                                    Demo.yellowButton3.setIcon(Demo.YellowIcon);
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
                                                    //输出控制信号灯
                                                    Demo.buf[7] = (byte) 0x61;//200上绿灯 200下红灯 200黄灯
                                                    Demo.buf[8] = (byte) 0x04;//215红灯
                                                    Demo.buf[9] = (byte) 0x04;//230红灯
                                                    Demo.buf[10] = (byte) 0x01;//245黄灯
                                                    Demo.outMessage(Demo.buf);
                                                    //提示信息
                                                    Demo.car_numberJB.setText("当前巷道中车辆数量:02");
                                                    Demo.carId_south.setText("车辆编号"+car+"从-245处驶入");
                                                    //车闪标识
                                                    //Demo.carnumber_two_falg = true;
                                                    Demo.carButton2.setVisible(true);
                                                }
                                            }

                                        }
                                        if (car.equals(Demo.car_200)){//说明从200进的车要出车
                                            Demo.car_200 = "00";
                                            //车闪标识
                                            //Demo.carnumber_two_falg = false;
                                            Demo.carButton2.setVisible(false);
                                            if (Demo.map.size()==0){//上半段没有车进来 3，4，5，6变绿灯
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
                                                //输出控制信号灯
                                                Demo.buf[7] = (byte) 0x52;//200上绿灯 200下绿灯 200绿灯
                                                Demo.buf[8] = (byte) 0x02;//215绿灯
                                                Demo.buf[9] = (byte) 0x02;//230绿灯
                                                Demo.buf[10] = (byte) 0x02;//245绿灯
                                                Demo.outMessage(Demo.buf);
                                                //提示信息
                                                Demo.car_numberJB.setText("当前巷道中车辆数量:00");
                                                Demo.carId_south.setText("车辆编号"+car+"从下半段驶离");
                                            }else{//上半段有车进来
                                                //提示信息
                                                Demo.car_numberJB.setText("当前巷道中车辆数量:01");
                                                Demo.carId_south.setText("车辆编号"+car+"从下半段驶离");
                                                if (!Demo.car_200top){//上半段的车还没经过200a ，变绿灯
                                                    Demo.redButton3.setIcon(Demo.RedIcon);
                                                    Demo.greenButton3.setIcon(Demo.GrayIcon);
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
                                                    //输出控制信号灯
                                                    Demo.buf[7] = (byte) 0x94;//200上红灯 200下绿灯 200红灯
                                                    Demo.buf[8] = (byte) 0x02;//215绿灯
                                                    Demo.buf[9] = (byte) 0x02;//230绿灯
                                                    Demo.buf[10] = (byte) 0x02;//245绿灯
                                                    Demo.outMessage(Demo.buf);
                                                }
                                            }
                                        }
                                    }
                                    Demo.chongfu215_1 = dncodeHex((int) buffer[4]);
                                    Demo.chongfu215_2 = dncodeHex((int) buffer[5]);
                                    Demo.chongfu215_3= dncodeHex((int) buffer[7]);
                                    Demo.chu215_flag = false;
                                }else if (buffer[4] ==15){
                                    if(dncodeHex((int) buffer[4]).equals(Demo.chongfu230_1)
                                            &&dncodeHex((int) buffer[5]).equals(Demo.chongfu230_2)
                                            &&dncodeHex((int) buffer[7]).equals(Demo.chongfu230_3)){
                                        System.out.println("重复的buffer");
                                        continue;
                                    }
                                    String car = dncodeHex((int) buffer[7]);
                                    if(Demo.car_200.equals("00")){//200没进车或者是已经出去
                                        if (Demo.map.size()==2){//肯定是出车
                                            if (car.equals(Demo.map.get(1)) || car.equals(Demo.map.get(2))){
                                                if (car.equals(Demo.map.get(1))){//上半段车出车，移除key1
                                                    Demo.map.remove(1);
                                                    //Demo.carnumber_one_falg = false;
                                                    Demo.carButton1.setVisible(false);
                                                    //提示信息
                                                    Demo.car_numberJB.setText("当前巷道中车辆数量:01");
                                                    Demo.carId_top.setText("车辆编号"+car+"从下半段驶离");
                                                }
                                                if (car.equals(Demo.map.get(2))){//下半段车出车，移除key2
                                                    Demo.map.remove(2);
                                                    //Demo.carnumber_two_falg = false;
                                                    Demo.carButton2.setVisible(false);
                                                    //提示信息
                                                    Demo.car_numberJB.setText("当前巷道中车辆数量:01");
                                                    Demo.carId_south.setText("车辆编号"+car+"从下半段驶离");
                                                    if (Demo.cuoche_flag ==true){ //有车在错车处
                                                        //200上下绿灯 200红灯 200红灯 215红灯 230红灯 245红灯
                                                        Demo.greenButton_200top.setIcon(Demo.GreenIcon);
                                                        Demo.redButton_200top.setIcon(Demo.GrayIcon);
                                                        Demo.yellowButton_200top.setIcon(Demo.GrayIcon);
                                                        Demo.greenButton_200south.setIcon(Demo.GreenIcon);
                                                        Demo.redButton_200south.setIcon(Demo.GrayIcon);
                                                        Demo.yellowButton_200south.setIcon(Demo.GrayIcon);
                                                        Demo.buf[7] = (byte) 0x54;
                                                        Demo.outMessage(Demo.buf);
                                                    }else {//没有车在错车处
                                                        //200上红灯 200红灯 200下绿灯 215 230 245绿灯
                                                        Demo.greenButton_200top.setIcon(Demo.GrayIcon);
                                                        Demo.redButton_200top.setIcon(Demo.RedIcon);
                                                        Demo.yellowButton_200top.setIcon(Demo.GrayIcon);
                                                        Demo.greenButton_200south.setIcon(Demo.GreenIcon);
                                                        Demo.redButton_200south.setIcon(Demo.GrayIcon);
                                                        Demo.yellowButton_200south.setIcon(Demo.GrayIcon);
                                                        Demo.greenButton3.setIcon(Demo.GrayIcon);
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
                                                        Demo.buf[7] = (byte) 0x94;
                                                        Demo.buf[8] = (byte) 0x02;
                                                        Demo.buf[9] = (byte) 0x02;
                                                        Demo.buf[10] = (byte) 0x02;
                                                        Demo.outMessage(Demo.buf);
                                                    }
                                                }
                                            }else {
                                                System.out.println("上下半段已进车，请退至等候区等待下次识别！");
                                            }
                                        }else if (Demo.map.size()==1){//巷道里存在一辆车。判断这辆车是进车还是出车
                                            if (Demo.map.get(2)!=null){//下半段进的车
                                                if (car.equals(Demo.map.get(2))){//出车
                                                    Demo.map.remove(2);
                                                    //Demo.carnumber_two_falg = false;
                                                    Demo.carButton2.setVisible(false);
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
                                                    //输出控制信号灯
                                                    Demo.buf[5] = (byte) 0x02;//170绿灯
                                                    Demo.buf[6] = (byte) 0x02;//185绿灯
                                                    Demo.buf[7] = (byte) 0x52;//200全绿灯
                                                    Demo.buf[8] = (byte) 0x02;//215绿灯
                                                    Demo.buf[9] = (byte) 0x02;//230绿灯
                                                    Demo.buf[10] = (byte) 0x02;//245绿灯
                                                    Demo.outMessage(Demo.buf);
                                                    //提示信息
                                                    Demo.car_numberJB.setText("当前巷道中车辆数量:00");
                                                    Demo.carId_south.setText("车辆编号"+car+"从下半段驶离");
                                                }else{
                                                    System.out.println("下半段已进车，请退置等候区等待下次识别！");
                                                }
                                            }
                                            if (Demo.map.get(1)!=null){//上半段进来的车
                                                if (car.equals(Demo.map.get(1))) {//上半段车出车
                                                    Demo.map.remove(1);
                                                    //Demo.carnumber_one_falg = false;
                                                    Demo.carButton1.setVisible(false);
                                                    Demo.car_200top = false;
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
                                                    //输出控制信号灯
                                                    Demo.buf[5] = (byte) 0x02;//170绿灯
                                                    Demo.buf[6] = (byte) 0x02;//185绿灯
                                                    Demo.buf[7] = (byte) 0x52;//200全绿灯
                                                    Demo.buf[8] = (byte) 0x02;//215绿灯
                                                    Demo.buf[9] = (byte) 0x02;//230绿灯
                                                    Demo.buf[10] = (byte) 0x02;//245绿灯
                                                    Demo.outMessage(Demo.buf);
                                                    //提示信息
                                                    Demo.car_numberJB.setText("当前巷道中车辆数量:00");
                                                    Demo.carId_top.setText("车辆编号"+car+"从下半段驶离");
                                                }else{//下半段进车，上半段有车
                                                    Demo.map.put(2, dncodeHex((int) buffer[7]));
                                                    //判断是215、230、245
                                                    if (buffer[4] == 14) {//如果是215 215黄灯亮，200 ,230，245红灯
                                                        //Demo.carnumber_two_falg = true;
                                                        Demo.carButton2.setVisible(true);
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
                                                        //输出控制信号灯
                                                        Demo.buf[7] = (byte) 0xA4;//200上红灯 200下红灯 200红灯
                                                        Demo.buf[8] = (byte) 0x01;//215黄灯
                                                        Demo.buf[9] = (byte) 0x04;//230红灯
                                                        Demo.buf[10] = (byte) 0x04;//245红灯
                                                        Demo.outMessage(Demo.buf);
                                                        //提示信息
                                                        Demo.car_numberJB.setText("当前巷道中车辆数量:02");
                                                        Demo.carId_south.setText("车辆编号"+car+"从-215处驶入");
                                                    } else if (buffer[4] == 15) {//如果是230 230黄灯亮 200,215,245红灯
                                                        //Demo.carnumber_two_falg = true;
                                                        Demo.carButton2.setVisible(true);
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
                                                        //输出控制信号灯
                                                        Demo.buf[7] = (byte) 0xA4;//200上红灯 200下红灯 200红灯
                                                        Demo.buf[8] = (byte) 0x04;//215红灯
                                                        Demo.buf[9] = (byte) 0x01;//230黄灯
                                                        Demo.buf[10] = (byte) 0x04;//245红灯
                                                        Demo.outMessage(Demo.buf);
                                                        //提示信息
                                                        Demo.car_numberJB.setText("当前巷道中车辆数量:02");
                                                        Demo.carId_south.setText("车辆编号"+car+"从-230处驶入");

                                                    } else { //如果是245 245黄灯亮 200,215、230红灯
                                                        //Demo.carnumber_two_falg = true;
                                                        Demo.carButton2.setVisible(true);
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
                                                        //输出控制信号灯
                                                        Demo.buf[7] = (byte) 0xA4;//200上红灯 200下红灯 200红灯
                                                        Demo.buf[8] = (byte) 0x04;//215红灯
                                                        Demo.buf[9] = (byte) 0x04;//230红灯
                                                        Demo.buf[10] = (byte) 0x01;//245黄灯
                                                        Demo.outMessage(Demo.buf);
                                                        //提示信息
                                                        Demo.car_numberJB.setText("当前巷道中车辆数量:02");
                                                        Demo.carId_south.setText("车辆编号"+car+"从-245处驶入");
                                                    }
                                                }
                                            }
                                        } else {//肯定是进车 记录进车识别标识 map.size()==0
                                            Demo.map.put(2, dncodeHex((int) buffer[7]));
                                            //判断是215、230、245
                                            if (buffer[4] == 14) {//如果是215 215黄灯亮，200，230，245红灯
                                                //Demo.carnumber_two_falg = true;
                                                Demo.carButton2.setVisible(true);
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
                                                //输出控制信号灯
                                                Demo.buf[7] = (byte) 0x64;//200上绿灯 200下红灯 200红灯
                                                Demo.buf[8] = (byte) 0x01;//215黄灯
                                                Demo.buf[9] = (byte) 0x04;//230红灯
                                                Demo.buf[10] = (byte) 0x04;//245红灯
                                                Demo.outMessage(Demo.buf);
                                                //提示信息
                                                Demo.car_numberJB.setText("当前巷道中车辆数量:01");
                                                Demo.carId_south.setText("车辆编号"+car+"从-215处驶入");
                                            } else if (buffer[4] == 15) {//如果是230 230黄灯亮 200，215,245红灯
                                                //Demo.carnumber_two_falg = true;
                                                Demo.carButton2.setVisible(true);
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
                                                //输出控制信号灯
                                                Demo.buf[7] = (byte) 0x64;//200上绿灯 200下红灯 200红灯
                                                Demo.buf[8] = (byte) 0x04;//215红灯
                                                Demo.buf[9] = (byte) 0x01;//230黄灯
                                                Demo.buf[10] = (byte) 0x04;//245红灯
                                                Demo.outMessage(Demo.buf);
                                                //提示信息
                                                Demo.car_numberJB.setText("当前巷道中车辆数量:01");
                                                Demo.carId_south.setText("车辆编号"+car+"从-230处驶入");
                                            } else { //如果是245 245黄灯亮 200， 215、230红灯
                                                //Demo.carnumber_two_falg = true;
                                                Demo.carButton2.setVisible(true);
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
                                                //输出控制信号灯
                                                Demo.buf[7] = (byte) 0x64;//200上绿灯 200下红灯 200红灯
                                                Demo.buf[8] = (byte) 0x04;//215红灯
                                                Demo.buf[9] = (byte) 0x04;//230红灯
                                                Demo.buf[10] = (byte) 0x01;//245黄灯
                                                Demo.outMessage(Demo.buf);
                                                //提示信息
                                                Demo.car_numberJB.setText("当前巷道中车辆数量:01");
                                                Demo.carId_south.setText("车辆编号"+car+"从-245处驶入");
                                            }
                                        }
                                    }
                                    if (!Demo.car_200.equals("00")){//200进车了
                                        if (!car.equals(Demo.car_200)){//200进的车往上半区段走了，判断此时下半段的车是进车出车
                                            if (Demo.map.size()==1){
                                                if (car.equals(Demo.map.get(2))){//出车，此时200进的车还没从上半段出去
                                                    Demo.map.remove(2);
                                                    //Demo.carnumber_two_falg = false;
                                                    Demo.carButton2.setVisible(false);
                                                    // 4,5,6变绿灯
                                                    Demo.redButton4.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton4.setIcon(Demo.GreenIcon);
                                                    Demo.yellowButton4.setIcon(Demo.GrayIcon);
                                                    Demo.redButton5.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton5.setIcon(Demo.GreenIcon);
                                                    Demo.yellowButton5.setIcon(Demo.GrayIcon);
                                                    Demo.redButton6.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton6.setIcon(Demo.GreenIcon);
                                                    Demo.yellowButton6.setIcon(Demo.GrayIcon);
                                                    //输出控制信号灯
                                                    Demo.buf[8] = (byte) 0x02;//215绿灯
                                                    Demo.buf[9] = (byte) 0x02;//230绿灯
                                                    Demo.buf[10] = (byte) 0x02;//245绿灯
                                                    Demo.outMessage(Demo.buf);
                                                    //提示信息
                                                    Demo.car_numberJB.setText("当前巷道中车辆数量:01");
                                                    Demo.carId_south.setText("车辆编号"+car+"从下半段驶离");
                                                }else{
                                                    System.out.println("200和下半段已经进车，请退至等候区等待下次识别！");
                                                }
                                            }else{//进车
                                                Demo.map.put(2, dncodeHex((int) buffer[7]));
                                                //判断是215、230、245
                                                if (buffer[4] == 14) {//如果是215 215黄灯亮，200黄灯 ，230，245红灯
                                                    Demo.redButton3.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton3.setIcon(Demo.GrayIcon);
                                                    Demo.yellowButton3.setIcon(Demo.YellowIcon);
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
                                                    //输出控制信号灯
                                                    Demo.buf[7] = (byte) 0x61;//200上绿灯 200下红灯 200黄灯
                                                    Demo.buf[8] = (byte) 0x01;//215黄灯
                                                    Demo.buf[9] = (byte) 0x04;//230红灯
                                                    Demo.buf[10] = (byte) 0x04;//245红灯
                                                    Demo.outMessage(Demo.buf);
                                                    //提示信息
                                                    Demo.car_numberJB.setText("当前巷道中车辆数量:02");
                                                    Demo.carId_south.setText("车辆编号"+car+"从-215处驶入");
                                                    //车闪标识
                                                    //Demo.carnumber_two_falg = true;
                                                    Demo.carButton2.setVisible(true);
                                                } else if (buffer[4] == 15) {//如果是230 230黄灯亮 200黄灯 ，215,245红灯
                                                    Demo.redButton3.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton3.setIcon(Demo.GrayIcon);
                                                    Demo.yellowButton3.setIcon(Demo.YellowIcon);
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
                                                    //输出控制信号灯
                                                    Demo.buf[7] = (byte) 0x61;//200上绿灯 200下红灯 200黄灯
                                                    Demo.buf[8] = (byte) 0x04;//215红灯
                                                    Demo.buf[9] = (byte) 0x01;//230黄灯
                                                    Demo.buf[10] = (byte) 0x04;//245红灯
                                                    Demo.outMessage(Demo.buf);
                                                    //提示信息
                                                    Demo.car_numberJB.setText("当前巷道中车辆数量:02");
                                                    Demo.carId_south.setText("车辆编号"+car+"从-230处驶入");
                                                    //车闪标识
                                                    //Demo.carnumber_two_falg = true;
                                                    Demo.carButton2.setVisible(true);
                                                    System.out.println("5号站进车！");
                                                } else { //如果是245 245黄灯亮 200， 215、230红灯
                                                    Demo.redButton3.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton3.setIcon(Demo.GrayIcon);
                                                    Demo.yellowButton3.setIcon(Demo.YellowIcon);
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
                                                    //输出控制信号灯
                                                    Demo.buf[7] = (byte) 0x61;//200上绿灯 200下红灯 200黄灯
                                                    Demo.buf[8] = (byte) 0x04;//215红灯
                                                    Demo.buf[9] = (byte) 0x04;//230红灯
                                                    Demo.buf[10] = (byte) 0x01;//245黄灯
                                                    Demo.outMessage(Demo.buf);
                                                    //提示信息
                                                    Demo.car_numberJB.setText("当前巷道中车辆数量:02");
                                                    Demo.carId_south.setText("车辆编号"+car+"从-245处驶入");
                                                    //车闪标识
                                                    //Demo.carnumber_two_falg = true;
                                                    Demo.carButton2.setVisible(true);
                                                }
                                            }

                                        }
                                        if (car.equals(Demo.car_200)){//说明从200进的车要出车
                                            Demo.car_200 = "00";
                                            //车闪标识
                                            //Demo.carnumber_two_falg = false;
                                            Demo.carButton2.setVisible(false);
                                            if (Demo.map.size()==0){//上半段没有车进来 3，4，5，6变绿灯
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
                                                //输出控制信号灯
                                                Demo.buf[7] = (byte) 0x52;//200上绿灯 200下绿灯 200绿灯
                                                Demo.buf[8] = (byte) 0x02;//215绿灯
                                                Demo.buf[9] = (byte) 0x02;//230绿灯
                                                Demo.buf[10] = (byte) 0x02;//245绿灯
                                                Demo.outMessage(Demo.buf);
                                                //提示信息
                                                Demo.car_numberJB.setText("当前巷道中车辆数量:00");
                                                Demo.carId_south.setText("车辆编号"+car+"从下半段驶离");
                                            }else{//上半段有车进来
                                                //提示信息
                                                Demo.car_numberJB.setText("当前巷道中车辆数量:01");
                                                Demo.carId_south.setText("车辆编号"+car+"从下半段驶离");
                                                if (!Demo.car_200top){//上半段的车还没经过200a ，变绿灯
                                                    Demo.redButton3.setIcon(Demo.RedIcon);
                                                    Demo.greenButton3.setIcon(Demo.GrayIcon);
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
                                                    //输出控制信号灯
                                                    Demo.buf[7] = (byte) 0x94;//200上红灯 200下绿灯 200红灯
                                                    Demo.buf[8] = (byte) 0x02;//215绿灯
                                                    Demo.buf[9] = (byte) 0x02;//230绿灯
                                                    Demo.buf[10] = (byte) 0x02;//245绿灯
                                                    Demo.outMessage(Demo.buf);
                                                }
                                            }
                                        }
                                    }
                                    Demo.chongfu230_1 = dncodeHex((int) buffer[4]);
                                    Demo.chongfu230_2 = dncodeHex((int) buffer[5]);
                                    Demo.chongfu230_3= dncodeHex((int) buffer[7]);
                                    Demo.chu230_flag = false;
                                    System.out.println(Demo.chu230_flag+"-----------------");
                                }else if (buffer[4] == 16){
                                    if(dncodeHex((int) buffer[4]).equals(Demo.chongfu245_1)
                                            &&dncodeHex((int) buffer[5]).equals(Demo.chongfu245_2)
                                            &&dncodeHex((int) buffer[7]).equals(Demo.chongfu245_3)){
                                        System.out.println("重复的buffer");
                                        continue;
                                    }
                                    String car = dncodeHex((int) buffer[7]);
                                    if(Demo.car_200.equals("00")){//200没进车或者是已经出去
                                        if (Demo.map.size()==2){//肯定是出车
                                            if (car.equals(Demo.map.get(1)) || car.equals(Demo.map.get(2))){
                                                if (car.equals(Demo.map.get(1))){//上半段车出车，移除key1
                                                    Demo.map.remove(1);
                                                    //Demo.carnumber_one_falg = false;
                                                    Demo.carButton1.setVisible(false);
                                                    //提示信息
                                                    Demo.car_numberJB.setText("当前巷道中车辆数量:01");
                                                    Demo.carId_top.setText("车辆编号"+car+"从下半段驶离");
                                                }
                                                if (car.equals(Demo.map.get(2))){//下半段车出车，移除key2
                                                    Demo.map.remove(2);
                                                    //Demo.carnumber_two_falg = false;
                                                    Demo.carButton2.setVisible(false);
                                                    //提示信息
                                                    Demo.car_numberJB.setText("当前巷道中车辆数量:01");
                                                    Demo.carId_south.setText("车辆编号"+car+"从下半段驶离");
                                                    if (Demo.cuoche_flag ==true){ //有车在错车处
                                                        //200上下绿灯 200红灯 200红灯 215红灯 230红灯 245红灯
                                                        Demo.greenButton_200top.setIcon(Demo.GreenIcon);
                                                        Demo.redButton_200top.setIcon(Demo.GrayIcon);
                                                        Demo.yellowButton_200top.setIcon(Demo.GrayIcon);
                                                        Demo.greenButton_200south.setIcon(Demo.GreenIcon);
                                                        Demo.redButton_200south.setIcon(Demo.GrayIcon);
                                                        Demo.yellowButton_200south.setIcon(Demo.GrayIcon);
                                                        Demo.buf[7] = (byte) 0x54;
                                                        Demo.outMessage(Demo.buf);
                                                    }else {//没有车在错车处
                                                        //200上红灯 200红灯 200下绿灯 215 230 245绿灯
                                                        Demo.greenButton_200top.setIcon(Demo.GrayIcon);
                                                        Demo.redButton_200top.setIcon(Demo.RedIcon);
                                                        Demo.yellowButton_200top.setIcon(Demo.GrayIcon);
                                                        Demo.greenButton_200south.setIcon(Demo.GreenIcon);
                                                        Demo.redButton_200south.setIcon(Demo.GrayIcon);
                                                        Demo.yellowButton_200south.setIcon(Demo.GrayIcon);
                                                        Demo.greenButton3.setIcon(Demo.GrayIcon);
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
                                                        Demo.buf[7] = (byte) 0x94;
                                                        Demo.buf[8] = (byte) 0x02;
                                                        Demo.buf[9] = (byte) 0x02;
                                                        Demo.buf[10] = (byte) 0x02;
                                                        Demo.outMessage(Demo.buf);
                                                    }
                                                }
                                            }else {
                                                System.out.println("上下半段已进车，请退至等候区等待下次识别！");
                                            }
                                        }else if (Demo.map.size()==1){//巷道里存在一辆车。判断这辆车是进车还是出车
                                            if (Demo.map.get(2)!=null){//下半段进的车
                                                if (car.equals(Demo.map.get(2))){//出车
                                                    Demo.map.remove(2);
                                                    //Demo.carnumber_two_falg = false;
                                                    Demo.carButton2.setVisible(false);
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
                                                    //输出控制信号灯
                                                    Demo.buf[5] = (byte) 0x02;//170绿灯
                                                    Demo.buf[6] = (byte) 0x02;//185绿灯
                                                    Demo.buf[7] = (byte) 0x52;//200全绿灯
                                                    Demo.buf[8] = (byte) 0x02;//215绿灯
                                                    Demo.buf[9] = (byte) 0x02;//230绿灯
                                                    Demo.buf[10] = (byte) 0x02;//245绿灯
                                                    Demo.outMessage(Demo.buf);
                                                    //提示信息
                                                    Demo.car_numberJB.setText("当前巷道中车辆数量:00");
                                                    Demo.carId_south.setText("车辆编号"+car+"从下半段驶离");
                                                }else{
                                                    System.out.println("下半段已进车，请退置等候区等待下次识别！");
                                                }
                                            }
                                            if (Demo.map.get(1)!=null){//上半段进来的车
                                                if (car.equals(Demo.map.get(1))) {//上半段车出车
                                                    Demo.map.remove(1);
                                                    //Demo.carnumber_one_falg = false;
                                                    Demo.carButton1.setVisible(false);
                                                    Demo.car_200top = false;
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
                                                    //输出控制信号灯
                                                    Demo.buf[5] = (byte) 0x02;//170绿灯
                                                    Demo.buf[6] = (byte) 0x02;//185绿灯
                                                    Demo.buf[7] = (byte) 0x52;//200全绿灯
                                                    Demo.buf[8] = (byte) 0x02;//215绿灯
                                                    Demo.buf[9] = (byte) 0x02;//230绿灯
                                                    Demo.buf[10] = (byte) 0x02;//245绿灯
                                                    Demo.outMessage(Demo.buf);
                                                    //提示信息
                                                    Demo.car_numberJB.setText("当前巷道中车辆数量:00");
                                                    Demo.carId_top.setText("车辆编号"+car+"从下半段驶离");
                                                }else{//下半段进车，上半段有车
                                                    Demo.map.put(2, dncodeHex((int) buffer[7]));
                                                    //判断是215、230、245
                                                    if (buffer[4] == 14) {//如果是215 215黄灯亮，200 ,230，245红灯
                                                        //Demo.carnumber_two_falg = true;
                                                        Demo.carButton2.setVisible(true);
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
                                                        //输出控制信号灯
                                                        Demo.buf[7] = (byte) 0xA4;//200上红灯 200下红灯 200红灯
                                                        Demo.buf[8] = (byte) 0x01;//215黄灯
                                                        Demo.buf[9] = (byte) 0x04;//230红灯
                                                        Demo.buf[10] = (byte) 0x04;//245红灯
                                                        Demo.outMessage(Demo.buf);
                                                        //提示信息
                                                        Demo.car_numberJB.setText("当前巷道中车辆数量:02");
                                                        Demo.carId_south.setText("车辆编号"+car+"从-215处驶入");
                                                    } else if (buffer[4] == 15) {//如果是230 230黄灯亮 200,215,245红灯
                                                        //Demo.carnumber_two_falg = true;
                                                        Demo.carButton2.setVisible(true);
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
                                                        //输出控制信号灯
                                                        Demo.buf[7] = (byte) 0xA4;//200上红灯 200下红灯 200红灯
                                                        Demo.buf[8] = (byte) 0x04;//215红灯
                                                        Demo.buf[9] = (byte) 0x01;//230黄灯
                                                        Demo.buf[10] = (byte) 0x04;//245红灯
                                                        Demo.outMessage(Demo.buf);
                                                        //提示信息
                                                        Demo.car_numberJB.setText("当前巷道中车辆数量:02");
                                                        Demo.carId_south.setText("车辆编号"+car+"从-230处驶入");

                                                    } else { //如果是245 245黄灯亮 200,215、230红灯
                                                        //Demo.carnumber_two_falg = true;
                                                        Demo.carButton2.setVisible(true);
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
                                                        //输出控制信号灯
                                                        Demo.buf[7] = (byte) 0xA4;//200上红灯 200下红灯 200红灯
                                                        Demo.buf[8] = (byte) 0x04;//215红灯
                                                        Demo.buf[9] = (byte) 0x04;//230红灯
                                                        Demo.buf[10] = (byte) 0x01;//245黄灯
                                                        Demo.outMessage(Demo.buf);
                                                        //提示信息
                                                        Demo.car_numberJB.setText("当前巷道中车辆数量:02");
                                                        Demo.carId_south.setText("车辆编号"+car+"从-245处驶入");
                                                    }
                                                }
                                            }
                                        } else {//肯定是进车 记录进车识别标识 map.size()==0
                                            Demo.map.put(2, dncodeHex((int) buffer[7]));
                                            //判断是215、230、245
                                            if (buffer[4] == 14) {//如果是215 215黄灯亮，200，230，245红灯
                                                //Demo.carnumber_two_falg = true;
                                                Demo.carButton2.setVisible(true);
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
                                                //输出控制信号灯
                                                Demo.buf[7] = (byte) 0x64;//200上绿灯 200下红灯 200红灯
                                                Demo.buf[8] = (byte) 0x01;//215黄灯
                                                Demo.buf[9] = (byte) 0x04;//230红灯
                                                Demo.buf[10] = (byte) 0x04;//245红灯
                                                Demo.outMessage(Demo.buf);
                                                //提示信息
                                                Demo.car_numberJB.setText("当前巷道中车辆数量:01");
                                                Demo.carId_south.setText("车辆编号"+car+"从-215处驶入");
                                            } else if (buffer[4] == 15) {//如果是230 230黄灯亮 200，215,245红灯
                                                //Demo.carnumber_two_falg = true;
                                                Demo.carButton2.setVisible(true);
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
                                                //输出控制信号灯
                                                Demo.buf[7] = (byte) 0x64;//200上绿灯 200下红灯 200红灯
                                                Demo.buf[8] = (byte) 0x04;//215红灯
                                                Demo.buf[9] = (byte) 0x01;//230黄灯
                                                Demo.buf[10] = (byte) 0x04;//245红灯
                                                Demo.outMessage(Demo.buf);
                                                //提示信息
                                                Demo.car_numberJB.setText("当前巷道中车辆数量:01");
                                                Demo.carId_south.setText("车辆编号"+car+"从-230处驶入");
                                            } else { //如果是245 245黄灯亮 200， 215、230红灯
                                                //Demo.carnumber_two_falg = true;
                                                Demo.carButton2.setVisible(true);
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
                                                //输出控制信号灯
                                                Demo.buf[7] = (byte) 0x64;//200上绿灯 200下红灯 200红灯
                                                Demo.buf[8] = (byte) 0x04;//215红灯
                                                Demo.buf[9] = (byte) 0x04;//230红灯
                                                Demo.buf[10] = (byte) 0x01;//245黄灯
                                                Demo.outMessage(Demo.buf);
                                                //提示信息
                                                Demo.car_numberJB.setText("当前巷道中车辆数量:01");
                                                Demo.carId_south.setText("车辆编号"+car+"从-245处驶入");
                                            }
                                        }
                                    }
                                    if (!Demo.car_200.equals("00")){//200进车了
                                        if (!car.equals(Demo.car_200)){//200进的车往上半区段走了，判断此时下半段的车是进车出车
                                            if (Demo.map.size()==1){
                                                if (car.equals(Demo.map.get(2))){//出车，此时200进的车还没从上半段出去
                                                    Demo.map.remove(2);
                                                    //Demo.carnumber_two_falg = false;
                                                    Demo.carButton2.setVisible(false);
                                                    // 4,5,6变绿灯
                                                    Demo.redButton4.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton4.setIcon(Demo.GreenIcon);
                                                    Demo.yellowButton4.setIcon(Demo.GrayIcon);
                                                    Demo.redButton5.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton5.setIcon(Demo.GreenIcon);
                                                    Demo.yellowButton5.setIcon(Demo.GrayIcon);
                                                    Demo.redButton6.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton6.setIcon(Demo.GreenIcon);
                                                    Demo.yellowButton6.setIcon(Demo.GrayIcon);
                                                    //输出控制信号灯
                                                    Demo.buf[8] = (byte) 0x02;//215绿灯
                                                    Demo.buf[9] = (byte) 0x02;//230绿灯
                                                    Demo.buf[10] = (byte) 0x02;//245绿灯
                                                    Demo.outMessage(Demo.buf);
                                                    //提示信息
                                                    Demo.car_numberJB.setText("当前巷道中车辆数量:01");
                                                    Demo.carId_south.setText("车辆编号"+car+"从下半段驶离");
                                                }else{
                                                    System.out.println("200和下半段已经进车，请退至等候区等待下次识别！");
                                                }
                                            }else{//进车
                                                Demo.map.put(2, dncodeHex((int) buffer[7]));
                                                //判断是215、230、245
                                                if (buffer[4] == 14) {//如果是215 215黄灯亮，200黄灯 ，230，245红灯
                                                    Demo.redButton3.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton3.setIcon(Demo.GrayIcon);
                                                    Demo.yellowButton3.setIcon(Demo.YellowIcon);
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
                                                    //输出控制信号灯
                                                    Demo.buf[7] = (byte) 0x61;//200上绿灯 200下红灯 200黄灯
                                                    Demo.buf[8] = (byte) 0x01;//215黄灯
                                                    Demo.buf[9] = (byte) 0x04;//230红灯
                                                    Demo.buf[10] = (byte) 0x04;//245红灯
                                                    Demo.outMessage(Demo.buf);
                                                    //提示信息
                                                    Demo.car_numberJB.setText("当前巷道中车辆数量:02");
                                                    Demo.carId_south.setText("车辆编号"+car+"从-215处驶入");
                                                    //车闪标识
                                                    //Demo.carnumber_two_falg = true;
                                                    Demo.carButton2.setVisible(true);
                                                } else if (buffer[4] == 15) {//如果是230 230黄灯亮 200黄灯 ，215,245红灯
                                                    Demo.redButton3.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton3.setIcon(Demo.GrayIcon);
                                                    Demo.yellowButton3.setIcon(Demo.YellowIcon);
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
                                                    //输出控制信号灯
                                                    Demo.buf[7] = (byte) 0x61;//200上绿灯 200下红灯 200黄灯
                                                    Demo.buf[8] = (byte) 0x04;//215红灯
                                                    Demo.buf[9] = (byte) 0x01;//230黄灯
                                                    Demo.buf[10] = (byte) 0x04;//245红灯
                                                    Demo.outMessage(Demo.buf);
                                                    //提示信息
                                                    Demo.car_numberJB.setText("当前巷道中车辆数量:02");
                                                    Demo.carId_south.setText("车辆编号"+car+"从-230处驶入");
                                                    //车闪标识
                                                    //Demo.carnumber_two_falg = true;
                                                    Demo.carButton2.setVisible(true);
                                                } else { //如果是245 245黄灯亮 200， 215、230红灯
                                                    Demo.redButton3.setIcon(Demo.GrayIcon);
                                                    Demo.greenButton3.setIcon(Demo.GrayIcon);
                                                    Demo.yellowButton3.setIcon(Demo.YellowIcon);
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
                                                    //输出控制信号灯
                                                    Demo.buf[7] = (byte) 0x61;//200上绿灯 200下红灯 200黄灯
                                                    Demo.buf[8] = (byte) 0x04;//215红灯
                                                    Demo.buf[9] = (byte) 0x04;//230红灯
                                                    Demo.buf[10] = (byte) 0x01;//245黄灯
                                                    Demo.outMessage(Demo.buf);
                                                    //提示信息
                                                    Demo.car_numberJB.setText("当前巷道中车辆数量:02");
                                                    Demo.carId_south.setText("车辆编号"+car+"从-245处驶入");
                                                    //车闪标识
                                                    //Demo.carnumber_two_falg = true;
                                                    Demo.carButton2.setVisible(true);
                                                }
                                            }

                                        }
                                        if (car.equals(Demo.car_200)){//说明从200进的车要出车
                                            Demo.car_200 = "00";
                                            //车闪标识
                                            //Demo.carnumber_two_falg = false;
                                            Demo.carButton2.setVisible(false);
                                            if (Demo.map.size()==0){//上半段没有车进来 3，4，5，6变绿灯
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
                                                //输出控制信号灯
                                                Demo.buf[7] = (byte) 0x52;//200上绿灯 200下绿灯 200绿灯
                                                Demo.buf[8] = (byte) 0x02;//215绿灯
                                                Demo.buf[9] = (byte) 0x02;//230绿灯
                                                Demo.buf[10] = (byte) 0x02;//245绿灯
                                                Demo.outMessage(Demo.buf);
                                                //提示信息
                                                Demo.car_numberJB.setText("当前巷道中车辆数量:00");
                                                Demo.carId_south.setText("车辆编号"+car+"从下半段驶离");
                                            }else{//上半段有车进来
                                                //提示信息
                                                Demo.car_numberJB.setText("当前巷道中车辆数量:01");
                                                Demo.carId_south.setText("车辆编号"+car+"从下半段驶离");
                                                if (!Demo.car_200top){//上半段的车还没经过200a ，变绿灯
                                                    Demo.redButton3.setIcon(Demo.RedIcon);
                                                    Demo.greenButton3.setIcon(Demo.GrayIcon);
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
                                                    //输出控制信号灯
                                                    Demo.buf[7] = (byte) 0x94;//200上红灯 200下绿灯 200红灯
                                                    Demo.buf[8] = (byte) 0x02;//215绿灯
                                                    Demo.buf[9] = (byte) 0x02;//230绿灯
                                                    Demo.buf[10] = (byte) 0x02;//245绿灯
                                                    Demo.outMessage(Demo.buf);
                                                }
                                            }
                                        }
                                    }
                                    Demo.chongfu245_1 = dncodeHex((int) buffer[4]);
                                    Demo.chongfu245_2 = dncodeHex((int) buffer[5]);
                                    Demo.chongfu245_3= dncodeHex((int) buffer[7]);
                                    Demo.chu245_flag = false;
                                }
                            }
                        }
                    }
                    }
                    //JIEWEI
                }
            }
            }
    }

class cheshan1 extends Thread {
    public void aaa(){
        int aaaaaa=0;
    }
    @Override
    public void run() {
       while (2>1){
           //System.out.println("车闪1");
           aaa();
           if(Demo.carnumber_one_falg ==true){
               Demo.carButton1.setVisible(true);
               Demo.carButton1.setIcon(new ImageIcon("img//car1.png"));
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
               Demo.carButton1.setIcon(new ImageIcon("img//car1.png"));
           }else {
               Demo.carButton1.setVisible(false);
           }
        }
    }
}
class cheshan2 extends Thread {
    public void aaa(){
        int aaaaaa=0;
    }
    @Override
    public void run() {

        while (2>1){
            //System.out.println("车闪2");
            aaa();
            if (Demo.carnumber_two_falg == true){
                Demo.carButton2.setVisible(true);
                Demo.carButton2.setIcon(new ImageIcon("img//car2.png"));
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
                Demo.carButton2.setIcon(new ImageIcon("img//car2.png"));
            }else{
                Demo.carButton2.setVisible(false);
            }
        }
    }

}

class chu1 extends Thread{
    int num_170 = 0;
    int num_185 = 0;
    int num_200top = 0;
    int num_200south = 0;
    int num_200 = 0;
    int num_215 = 0;
    int num_230 = 0;
    int num_245 = 0;
    public void aaa(){
        int aaaaaa=0;
    }
    @Override
    public void run() {
        while(2>1){

            if(Demo.chu170_flag ==false){
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                num_170++;
                //System.out.println(num_170);
                if(num_170==60){
                    num_170=0;
                    Demo.chu170_flag = true;
                    Demo.chongfu170_1 = "00";
                    Demo.chongfu170_2 = "00";
                    Demo.chongfu170_3 = "00";
                    //System.out.println("170清除成功");
                }
            }else{
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Demo.chu170_flag);
            }
            if(Demo.chu185_flag ==false){
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                num_185++;
                //System.out.println(num_185);
                if(num_185==60){
                    num_185=0;
                    Demo.chu185_flag = true;
                    Demo.chongfu185_1 = "00";
                    Demo.chongfu185_2 = "00";
                    Demo.chongfu185_3 = "00";
                    //System.out.println("185清除成功");
                }
            }

            if(Demo.chu200top_flag ==false){
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                num_200top++;
                //System.out.println(num_200top);
                if(num_200top==60){
                    num_200top=0;
                    Demo.chu200top_flag = true;
                    Demo.chongfu200top_1 = "00";
                    Demo.chongfu200top_2 = "00";
                    Demo.chongfu200top_3 = "00";
                    //System.out.println("200top清除成功");
                }
            }
            if(Demo.chu200south_flag ==false){
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                num_200south++;
                //System.out.println(num_200south);
                if(num_200south==60){
                    num_200south=0;
                    Demo.chu200south_flag = true;
                    Demo.chongfu200south_1 = "00";
                    Demo.chongfu200south_2 = "00";
                    Demo.chongfu200south_3 = "00";
                    //System.out.println("200top清除成功");
                }
            }
            if(Demo.chu200_flag ==false){
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                num_200++;
                //System.out.println(num_200);
                if(num_200 ==60){
                    num_200=0;
                    Demo.chu200_flag = true;
                    Demo.chongfu200_1 = "00";
                    Demo.chongfu200_2 = "00";
                    Demo.chongfu200_3 = "00";
                }
            }
            if(Demo.chu215_flag ==false){
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                num_215++;
                if(num_215==60){
                    num_215=0;
                    Demo.chu215_flag = true;
                    Demo.chongfu215_1 = "00";
                    Demo.chongfu215_2 = "00";
                    Demo.chongfu215_3 = "00";
                }
            }
            if(Demo.chu230_flag ==false){
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                num_230++;
                //System.out.println(num_230);
                if(num_230 ==60){
                    num_230=0;
                    //System.out.println("230清除成功");
                    Demo.chu230_flag = true;
                    Demo.chongfu230_1 = "00";
                    Demo.chongfu230_2 = "00";
                    Demo.chongfu230_3 = "00";
                }
            }
            if(Demo.chu245_flag ==false){
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                num_245++;
                if(num_245==60){
                    num_245=0;
                    Demo.chu245_flag = true;
                    Demo.chongfu245_1 = "00";
                    Demo.chongfu245_2 = "00";
                    Demo.chongfu245_3 = "00";
                }
            }
        }
    }
}

class chu2 extends Thread{

    public void aaa(){
        int aaaaaa=0;
    }
    @Override
    public void run() {
        while(2>1){
            aaa();

        }
    }
}
class chu3 extends Thread{

    public void aaa(){
        int aaaaaa=0;
    }
    @Override
    public void run() {
        while(2>1){
            aaa();

        }
    }
}


class chu4 extends Thread{

    public void aaa(){
        int aaaaaa=0;
    }
    @Override
    public void run() {
        while(2>1){
            aaa();

    }
    }
}


