package com.liu.view;

import com.liu.entity.CarRequest;
import com.liu.entity.TableDTO;
import com.liu.handler.WarnViewHandler;
import com.liu.service.CarService;
import com.liu.service.WarnService;
import com.liu.service.impl.CarServiceImpl;
import com.liu.service.impl.WarnServiceImpl;
import com.liu.util.DimensionUtil;
import com.liu.view.ext.CarViewTable;
import com.liu.view.ext.CarViewTableModel;
import com.liu.view.ext.WarnViewTable;
import com.liu.view.ext.WarnViewTableModel;

import javax.swing.*;
import java.awt.*;

public class WarningView extends JFrame {

    JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton preBtn = new JButton("上一页");
    JButton nextBtn = new JButton("下一页");
    private int pageNow = 1;//当前是第几页
    private int pageSize =10;//一页显示多少条数据库记录
   WarnViewTable warnViewTable = new WarnViewTable();
    private WarnViewHandler warnViewHandler;
    public WarningView(){
        super("报警信息查看");

        //内容面板
        Container contentPane = getContentPane();
        Rectangle bounds = DimensionUtil.getBounds();
        pageSize = Math.floorDiv(bounds.height,35);

        warnViewHandler = new WarnViewHandler(this);
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
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(true);
        setVisible(true);
    }
    private void laoutCenter(Container contentPane) {
        TableDTO dto = getTableDto();
        WarnViewTableModel warnViewTableModel = WarnViewTableModel.assembleModel(dto.getData());
        //table 关联model
        warnViewTable.setModel(warnViewTableModel);
        //添加渲染方式
        warnViewTable.renderRule();
        //显示表格
        JScrollPane jScrollPane = new JScrollPane(warnViewTable);
        contentPane.add(jScrollPane,BorderLayout.CENTER);
        showPreNext(dto.getTotalCount());
    }
    private void laoutSouth(Container contentPane) {
        preBtn.addActionListener(warnViewHandler);
        nextBtn.addActionListener(warnViewHandler);
        southPanel.add(preBtn);
        southPanel.add(nextBtn);
        contentPane.add(southPanel,BorderLayout.SOUTH);
    }
    private TableDTO getTableDto() {
        WarnService warnService = new WarnServiceImpl();
        CarRequest request = new CarRequest();
        request.setPageNow(pageNow);
        request.setPageSize(pageSize);
        request.setSearchKey("");
        TableDTO tableDTO = warnService.retrieveWarns(request);
        return tableDTO;
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

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }
}
