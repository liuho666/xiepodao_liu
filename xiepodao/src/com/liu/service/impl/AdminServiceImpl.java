package com.liu.service.impl;

import com.liu.entity.AdminDO;
import com.liu.service.AdminService;
import com.liu.util.DBUtil;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminServiceImpl implements AdminService {
    @Override
    public boolean validateAdmin(AdminDO adminDO) {
        String userName = adminDO.getUsername();
        String pwdParam = adminDO.getPassword();
        String sql="select password from user where username = ?";
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DBUtil.getConn();
            if (connection == null){
                return false;
            }
            ps = connection.prepareStatement(sql);
            ps.setString(1,adminDO.getUsername());
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                String password = resultSet.getString(1);
                System.out.println(password);
                if (adminDO.getPassword().equals(password)){
                        return true;
                }
            }
        }
         catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeRS(rs);
            DBUtil.closePs(ps);
            DBUtil.closeConn(connection);
        }
        return false;
    }
}
