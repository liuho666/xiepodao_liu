package com.liu.view;


import com.liu.entity.CarRequest;
import com.liu.entity.TableDTO;
import com.liu.handler.CarViewHandler;
import com.liu.service.CarService;
import com.liu.service.impl.CarServiceImpl;
import com.liu.util.DimensionUtil;
import com.liu.view.ext.CarViewTable;
import com.liu.view.ext.CarViewTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class CarView extends JFrame {
    JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton addBtn = new JButton("增加");
    JButton updateBtn = new JButton("修改");
    JButton delBtn = new JButton("删除");
    JTextField searchTxt = new JTextField(20);
    JButton searchBtn = new JButton("查询");
    JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton preBtn = new JButton("上一页");
    JButton nextBtn = new JButton("下一页");
    CarViewTable carViewTable = new CarViewTable();
    CarViewHandler carViewHandler = null;
    private int pageNow = 1;//当前是第几页
    private int pageSize =10;//一页显示多少条数据库记录
    public CarView(){
        super("车辆信息管控");
        //内容面板
        Container contentPane = getContentPane();
        Rectangle bounds = DimensionUtil.getBounds();
        pageSize = Math.floorDiv(bounds.height,35);
        carViewHandler= new CarViewHandler(this);
        //放置北边的组件
        layoutNorth(contentPane);
        //设置中间的Jtable
        laoutCenter(contentPane);
        //放置南边的组件
        laoutSouth(contentPane);


        //自定义图标
        setIconImage(new ImageIcon("img//logo.png").getImage());

        //根据屏幕大小设置主界面大小
        //setBounds(DimensionUtil.getBounds());
        //设置窗体完全充满整个屏幕的可见大小
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(1000, 800);
        setLocationRelativeTo(null);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);
    }

    private void laoutCenter(Container contentPane) {
        TableDTO dto = getTableDto();
        CarViewTableModel carViewTableModel = CarViewTableModel.assembleModel(dto.getData());
        //table 关联model
        carViewTable.setModel(carViewTableModel);
        //添加渲染方式
        carViewTable.renderRule();
        //显示表格
        JScrollPane jScrollPane = new JScrollPane(carViewTable);
        contentPane.add(jScrollPane,BorderLayout.CENTER);
        showPreNext(dto.getTotalCount());
    }

    private TableDTO getTableDto() {
        CarService carService = new CarServiceImpl();
        CarRequest request = new CarRequest();
        request.setPageNow(pageNow);
        request.setPageSize(pageSize);
        request.setSearchKey(searchTxt.getText().trim());
        //request.setSearchKey("");
        TableDTO tableDTO = carService.retrieveCars(request);
        return tableDTO;
    }

    private void laoutSouth(Container contentPane) {
        preBtn.addActionListener(carViewHandler);
        nextBtn.addActionListener(carViewHandler);
        southPanel.add(preBtn);
        southPanel.add(nextBtn);
        contentPane.add(southPanel,BorderLayout.SOUTH);
    }
    //设置上一页，下一页是否可见
    private void showPreNext(int totalCount){
        if (pageNow == 1){
            preBtn.setVisible(false);
        }else {
            preBtn.setVisible(true);
        }
        int pageCount = 0;//总共有多少页
        if (totalCount%pageSize == 0){
            pageCount = totalCount/pageSize;
        }else {
            pageCount = totalCount/pageSize+1;
        }
        if (pageNow == pageCount){
            nextBtn.setVisible(false);
        }else {
            nextBtn.setVisible(true);
        }
    }



    private void layoutNorth(Container contentPane) {
        //增加事件监听
        addBtn.addActionListener(carViewHandler);
        updateBtn.addActionListener(carViewHandler);
        delBtn.addActionListener(carViewHandler);
        searchBtn.addActionListener(carViewHandler);
        northPanel.add(addBtn);
        northPanel.add(updateBtn);
        northPanel.add(delBtn);
        northPanel.add(searchTxt);
        northPanel.add(searchBtn);
        contentPane.add(northPanel,BorderLayout.NORTH);
    }

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public void reloadTable() {
        TableDTO dto = getTableDto();
        CarViewTableModel.updateModel(dto.getData());
        carViewTable.renderRule();
        showPreNext(dto.getTotalCount());
    }

    //获取选中的行
    public int[] getSelectedCarIds(){
        int[] selectedRows = carViewTable.getSelectedRows();
        int[] ids = new int[selectedRows.length];
        for (int i = 0;i<selectedRows.length;i++){
            int rowIndex = selectedRows[i];
            Object idObj = carViewTable.getValueAt(rowIndex, 0);
            ids[i] = Integer.valueOf(idObj.toString());
        }
        return ids;
    }
}
