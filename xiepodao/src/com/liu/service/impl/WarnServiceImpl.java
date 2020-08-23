package com.liu.service.impl;

import com.liu.entity.CarRequest;
import com.liu.entity.TableDTO;
import com.liu.service.WarnService;
import com.liu.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class WarnServiceImpl implements WarnService {
    @Override
    public TableDTO retrieveWarns(CarRequest request) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from warning ");
        sql.append(" order by id desc limit ")
                .append(request.getStart()).append(",")
                .append(request.getPageSize());
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        TableDTO returnDTO = new TableDTO();
        try {
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            //查询记录
            returnDTO.setData(fillData(rs));
            sql.setLength(0);
            sql.append("select count(*) from warning");
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            //查询报警记录总条数
            while (rs.next()){
                int count = rs.getInt(1);
                returnDTO.setTotalCount(count);
            }
            return returnDTO;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeRS(rs);
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return null;
    }
    //放到表格中显示
    private Vector<Vector<Object>> fillData(ResultSet rs) throws SQLException {
        Vector<Vector<Object>> data = new Vector<>();
        while (rs.next()){
            //处理查出的每一条记录
            Vector<Object> oneRecord = new Vector<>();
            int id = rs.getInt(1);
            String message = rs.getString("message");
            String time = rs.getString("time");
            oneRecord.addElement(id);
            oneRecord.addElement(message);
            oneRecord.addElement(time);
            data.addElement(oneRecord);
        }
        return data;
    }
}
