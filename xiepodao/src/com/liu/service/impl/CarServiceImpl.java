package com.liu.service.impl;

import com.liu.entity.CarDo;
import com.liu.entity.CarRequest;
import com.liu.entity.TableDTO;
import com.liu.service.CarService;
import com.liu.util.DBUtil;
import sun.security.util.Length;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class CarServiceImpl implements CarService {
    @Override
    public TableDTO retrieveCars(CarRequest request) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from car ");
        if (request.getSearchKey()!=null&&!"".equals(request.getSearchKey().trim())){
            sql.append(" where leixing like '%"+request.getSearchKey().trim()+"%'");
        }
        sql.append(" order by id asc limit ")
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
            sql.append("select count(*) from car");
            if (request.getSearchKey()!=null&&!"".equals(request.getSearchKey().trim())){
                sql.append(" where leixing like '%"+request.getSearchKey().trim()+"%'");
            }
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
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

    private Vector<Vector<Object>> fillData(ResultSet rs) throws SQLException {
        Vector<Vector<Object>> data = new Vector<>();
        while (rs.next()){
            //处理查出的每一条记录
            Vector<Object> oneRecord = new Vector<>();
            int id = rs.getInt(1);
            String leixing = rs.getString("leixing");
            String xinghao = rs.getString("xinghao");
            String siji = rs.getString("siji");
            String bumen = rs.getString("bumen");
            String carhao = rs.getString("carhao");
            oneRecord.addElement(id);
            oneRecord.addElement(leixing);
            oneRecord.addElement(xinghao);
            oneRecord.addElement(siji);
            oneRecord.addElement(bumen);
            oneRecord.addElement(carhao);
            data.addElement(oneRecord);
        }
        return data;
    }

    @Override
    public boolean add(CarDo carDo) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into car(id,leixing,xinghao,siji,bumen,carhao) ");
        sql.append("values(?,?,?,?,?,?)");
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            ps.setInt(1,carDo.getId());
            ps.setString(2,carDo.getLeixing());
            ps.setString(3,carDo.getXinghao());
            ps.setString(4,carDo.getSiji());
            ps.setString(5,carDo.getBumen());
            ps.setString(6,carDo.getCarhao());
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return false;
    }

    @Override
    public CarDo getCarById(int selectedCarId) {
        StringBuilder sql = new StringBuilder("select * from car where id = ?");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        CarDo carDo = new CarDo();
        try {
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            ps.setInt(1,selectedCarId);
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt(1);
                String leixing = rs.getString("leixing");
                String xinghao = rs.getString("xinghao");
                String siji = rs.getString("siji");
                String bumen = rs.getString("bumen");
                String carhao = rs.getString("carhao");
                carDo.setId(id);
                carDo.setLeixing(leixing);
                carDo.setXinghao(xinghao);
                carDo.setSiji(siji);
                carDo.setBumen(bumen);
                carDo.setCarhao(carhao);
            }
           return carDo;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeRS(rs);
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return null;
    }

    @Override
    public boolean update(CarDo carDo) {
        StringBuilder sql = new StringBuilder();
        sql.append("update car set leixing=?,xinghao=?,siji=?,bumen=?,carhao=? ");
        sql.append("where id = ? ");
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1,carDo.getLeixing());
            ps.setString(2,carDo.getXinghao());
            ps.setString(3,carDo.getSiji());
            ps.setString(4,carDo.getBumen());
            ps.setString(5,carDo.getCarhao());
            ps.setInt(6,carDo.getId());
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return false;
    }

    @Override
    public boolean delete(int[] selectedCarIds) {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from car where id in (");
        int length = selectedCarIds.length;
        for (int i=0;i<length;i++){
            if (i == length -1 ){
                sql.append(" ? ");
            }else {
                sql.append(" ?, ");
            }
        }
        sql.append(" ) ");
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            for (int i=0;i<length;i++){
                    //设置参数
                   ps.setInt(i+1,selectedCarIds[i]);

            }
            return ps.executeUpdate() == length;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return false;
    }
}
