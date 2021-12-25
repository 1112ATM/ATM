package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.Card;
import com.util.StringUtil;

/**
 * 银行卡Dao类
 *
 * @author dell
 */
public class CardDao {
    /**
     * 银行卡添加
     *
     * @param con
     * @param card
     * @return
     * @throws Exception
     */
    public int add(Connection con, Card card) throws Exception {
        String sql = "insert into card values(?,?,?,?,?,?,?)";
        PreparedStatement pstmt = con.prepareCall(sql);
        pstmt.setString(0, card.getAccount());
        pstmt.setString(1, card.getName());
        pstmt.setString(2, card.getSex());
        pstmt.setString(3, card.getAge());
        pstmt.setString(4, card.getPassword());
        pstmt.setString(5, card.getIdCard());
        pstmt.setString(6, card.getBalance());

        return pstmt.executeUpdate();
    }


    /**
     * 删除银行卡
     *
     * @param con
     * @param account
     * @return
     * @throws Exception
     */
    public int delete(Connection con, String account) throws Exception {
        String sql = "delete from card where account=?";
        PreparedStatement pstmt = con.prepareCall(sql);
        pstmt.setString(1, account);
        return pstmt.executeUpdate();
    }

    /**
     * 更新银行卡
     *
     * @param con
     * @param card
     * @return
     * @throws Exception
     */
    public int update(Connection con, Card card) throws Exception {
        String sql = "update card set password=?,name=?,sex=?,age=?,idCard=?,balance=? where account=?";
        PreparedStatement pstmt = con.prepareCall(sql);
        pstmt.setString(1, card.getPassword());
        pstmt.setString(2, card.getName());
        pstmt.setString(3, card.getSex());
        pstmt.setString(4, card.getAge());
        pstmt.setString(5, card.getIdCard());
        pstmt.setString(6, card.getBalance());
        pstmt.setString(7, card.getAccount());
        return pstmt.executeUpdate();
    }

    /**
     * 查询所有账户信息
     *
     * @param con
     * @param card
     * @return
     * @throws Exception
     */
    public ResultSet listAll(Connection con, Card card) throws Exception {
        String sql = "select * from card";
        PreparedStatement pstmt = con.prepareCall(sql);
        return pstmt.executeQuery();
    }

    /**
     * 查询单个账户信息
     *
     * @param con
     * @param card
     * @return
     * @throws Exception
     */
    public ResultSet list(Connection con, String account) throws Exception {
        String sql = "select * from card where account=?";
        PreparedStatement pstmt = con.prepareCall(sql);
        pstmt.setString(1, account);
        return pstmt.executeQuery();
    }

    /**
     * 查询余额
     *
     * @param con
     * @param account
     * @return
     * @throws Exception
     */
    public String checkBalance(Connection con, String account) throws Exception {
        String sql = "select balance from card where account=?";
        PreparedStatement pstmt = con.prepareCall(sql);
        pstmt.setString(1, account);
        ResultSet rs = pstmt.executeQuery();
        String balance = null;
        while (rs.next()) {
            balance = rs.getString("balance");
        }
        return balance;
    }


    /**
     * 存款
     *
     * @param con
     * @param balance
     * @return
     * @throws Exception
     */
    public int deposit(Connection con, String account, String deposit) throws Exception {
        String sql = "select * from card where account=?";
        PreparedStatement pstmt = con.prepareCall(sql);
        pstmt.setString(1, account);
        ResultSet rs = pstmt.executeQuery();
        String balance = null;
        while (rs.next()) {
            balance = rs.getString("balance");
        }
        String sql1 = "update card set balance=? where account=?";
        PreparedStatement pstmt1 = con.prepareCall(sql1);
        pstmt1.setString(1, String.valueOf(Double.valueOf(balance) + Double.valueOf(deposit)));
        pstmt1.setString(2, account);
        return pstmt1.executeUpdate();
    }

    /**
     * 取款
     *
     * @param con
     * @param account
     * @param withdraw
     * @return
     * @throws Exception
     */
    public int withdraw(Connection con, String account, String withdraw) throws Exception {
        String sql = "select * from card where account=?";
        PreparedStatement pstmt = con.prepareCall(sql.toString());
        pstmt.setString(1, account);
        ResultSet rs = pstmt.executeQuery();
        String balance = null;
        while (rs.next()) {
            balance = rs.getString("balance");
        }
        String sql1 = "update card set balance=? where account=?";
        PreparedStatement pstmt1 = con.prepareCall(sql1);
        pstmt1.setString(1, String.valueOf(Double.valueOf(balance) - Double.valueOf(withdraw)));
        pstmt1.setString(2, account);
        return pstmt1.executeUpdate();
    }

    /**
     * 匹配原密码
     *
     * @param con
     * @param account
     * @param password
     * @return
     * @throws Exception
     */
    public boolean password(Connection con, String account, String password) throws Exception {
        String sql = "select password from card where account like " + account;
        PreparedStatement pstmt = con.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        String originalPassword = null;
        while (rs.next()) {
            originalPassword = rs.getString("password");
        }
        return originalPassword.equals(password);
    }

    /**
     * 修改card表里的密码
     *
     * @param con
     * @param account
     * @param password
     * @return
     * @throws Exception
     */
    public int passwordChange(Connection con, String account, String password) throws Exception {
        String sql = "update card set password=? where account=?";
        PreparedStatement pstmt = con.prepareCall(sql);
        pstmt.setString(1, password);
        pstmt.setString(2, account);
        return pstmt.executeUpdate();
    }

    /**
     * 修改user表里的密码
     *
     * @param con
     * @param account
     * @param password
     * @return
     * @throws Exception
     */
    public int passwordChange1(Connection con, String account, String password) throws Exception {
        String sql = "update user set password=? where account=?";
        PreparedStatement pstmt = con.prepareCall(sql);
        pstmt.setString(1, password);
        pstmt.setString(2, account);
        return pstmt.executeUpdate();
    }
}
