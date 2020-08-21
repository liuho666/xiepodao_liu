package com.liu.view;

import com.liu.entity.CarRequest;
import com.liu.entity.TableDTO;
import com.liu.service.CarService;
import com.liu.service.impl.CarServiceImpl;
import com.liu.view.ext.CarViewTableModel;

import javax.swing.*;
import java.awt.*;

public class WarningView extends JFrame {

    JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton preBtn = new JButton("上一页");
    JButton nextBtn = new JButton("下一页");
    private int pageNow = 1;//当前是第几页
    private int pageSize =10;//一页显示多少条数据库记录

    public WarningView(){
        super("报警信息查看");
        //内容面板
        Container contentPane = getContentPane();
        //设置中间的Jtable
        laoutCenter(contentPane);
        //放置南边的组件
        laoutSouth(contentPane);
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

}
