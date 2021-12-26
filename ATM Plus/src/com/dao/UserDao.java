package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.model.User;



public class UserDao {

    //登录验证
    public User login(Connection con, User user) throws Exception {
        User resultUser = null;
        String sql = "select * from user where account=? and password=?";//从user表中选择与设置的 account 和 psaaword 匹配的用户信息
        PreparedStatement pstmt = con.prepareStatement(sql);//预处理
        pstmt.setString(1, user.getAccount());
        pstmt.setString(2, user.getPassword());
        ResultSet rs = pstmt.executeQuery();//获得结果，存在rs中
        if (rs.next()) {
            resultUser = new User();//构造user对象
            resultUser.setId(rs.getInt("id"));//设置user对象的id account psaaword
            resultUser.setAccount(rs.getString("account"));
            resultUser.setPassword(rs.getString("password"));
        }
        return resultUser;
    }

}
