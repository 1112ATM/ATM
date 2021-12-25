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
     * @param
     * @param
     * @return
     * @throws
     */
    public int add(Connection con, Card card) throws Exception {
        String sql = "insert into card values(?,?,?,?,?,?,?)";
        PreparedStatement pstmt = con.prepareCall(sql);
        pstmt.setString(1, card.getAccount());
        pstmt.setString(2, card.getName());
        pstmt.setString(3, card.getSex());
        pstmt.setString(4, card.getAge());
        pstmt.setString(5, card.getPassword());
        pstmt.setString(6, card.getIdCard());
        pstmt.setString(7, card.getBalance());

        return pstmt.executeUpdate();
    }


    /**
     * 删除银行卡
     *
     * @param
     * @param
     * @return
     * @throws
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
     * @param
     * @param
     * @return
     * @throws
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
     * @param
     * @param
     * @return
     * @throws
     */
    public ResultSet listAll(Connection con, Card card) throws Exception {
        String sql = "select * from card";
        PreparedStatement pstmt = con.prepareCall(sql);
        return pstmt.executeQuery();
    }

    /**
     * 查询单个账户信息
     *
     * @param
     * @param
     * @return
     * @throws
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
     * @param
     * @param
     * @return
     * @throws
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
     * @param
     * @param
     * @return
     * @throws
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
     * @param
     * @param
     * @param
     * @return
     * @throws
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
     * 转账
     *
     * @param
     * @param
     * @param
     * @return
     * @throws
     */

    public int transfer(Connection con,String account1,String account2,String withdraw)throws Exception{
        String sql = "select * from card where account=?";
        PreparedStatement pstmt = con.prepareCall(sql.toString());
        pstmt.setString(1, account1);
        ResultSet rs = pstmt.executeQuery();
        String balance = null;
        while (rs.next()) {
            balance = rs.getString("balance");
        }
        String sql1 = "update card set balance=? where account=?";
        PreparedStatement pstmt1 = con.prepareCall(sql1);
        pstmt1.setString(1, String.valueOf(Double.valueOf(balance) - Double.valueOf(withdraw)));
        pstmt1.setString(2, account1);
        pstmt1.executeUpdate();


        String sql2 = "select * from card where account=?";
        PreparedStatement pstmt2 = con.prepareCall(sql2);
        pstmt2.setString(1, account2);
        ResultSet rs1 = pstmt2.executeQuery();
        String balance1 = null;
        while (rs1.next()) {
            balance1 = rs1.getString("balance");
        }
        String sql3 = "update card set balance=? where account=?";
        PreparedStatement pstmt3 = con.prepareCall(sql3);
        pstmt3.setString(1, String.valueOf(Double.valueOf(balance1) + Double.valueOf(withdraw)));
        pstmt3.setString(2, account2);
        pstmt3.executeUpdate();
        return 0;
    }



    /**
     * 匹配原密码
     *
     * @param
     * @param
     * @param
     * @return
     * @throws
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
     * @param
     * @param
     * @param
     * @return
     * @throws
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
     * @param
     * @param
     * @return
     * @throws
     */
    public int passwordChange1(Connection con, String account, String password) throws Exception {
        String sql = "update user set password=? where account=?";
        PreparedStatement pstmt = con.prepareCall(sql);
        pstmt.setString(1, password);
        pstmt.setString(2, account);
        return pstmt.executeUpdate();
    }
}
