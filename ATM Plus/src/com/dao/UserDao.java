package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.model.User;



public class UserDao {

    //��¼��֤
    public User login(Connection con, User user) throws Exception {
        User resultUser = null;
        String sql = "select * from user where account=? and password=?";//��user����ѡ�������õ� account �� psaaword ƥ����û���Ϣ
        PreparedStatement pstmt = con.prepareStatement(sql);//Ԥ����
        pstmt.setString(1, user.getAccount());
        pstmt.setString(2, user.getPassword());
        ResultSet rs = pstmt.executeQuery();//��ý��������rs��
        if (rs.next()) {
            resultUser = new User();//����user����
            resultUser.setId(rs.getInt("id"));//����user�����id account psaaword
            resultUser.setAccount(rs.getString("account"));
            resultUser.setPassword(rs.getString("password"));
        }
        return resultUser;
    }

}
