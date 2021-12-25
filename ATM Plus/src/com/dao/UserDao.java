package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.model.User;


/**
 * 用户Dao类
 *
 * @author dell
 */
public class UserDao {

    /**
     * 登录验证
     *
     * @param con
     * @param user
     * @return
     * @throws Exception
     */
    public User login(Connection con, User user) throws Exception {
        User resultUser = null;
        String sql = "select * from user where account=? and password=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, user.getAccount());
        pstmt.setString(2, user.getPassword());
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            resultUser = new User();
            resultUser.setId(rs.getInt("id"));
            resultUser.setAccount(rs.getString("account"));
            resultUser.setPassword(rs.getString("password"));
        }
        return resultUser;
    }

}
