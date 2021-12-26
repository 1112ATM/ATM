package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.model.Card;
import com.util.StringUtil;


public class CardDao {
    //银行卡添加
    public int add(Connection con, Card card) throws Exception {
        String sql = "insert into card values(?,?,?,?,?,?,?)";
        PreparedStatement pstmt = con.prepareCall(sql);//对sql进行预处理
        pstmt.setString(1, card.getAccount());//按序号分别向问号赋值
        pstmt.setString(2, card.getName());
        pstmt.setString(3, card.getSex());
        pstmt.setString(4, card.getAge());
        pstmt.setString(5, card.getPassword());
        pstmt.setString(6, card.getIdCard());
        pstmt.setString(7, card.getBalance());
        return pstmt.executeUpdate();
    }

    //删除银行卡
    public int delete(Connection con, String account) throws Exception {
        String sql = "delete from card where account=?";
        PreparedStatement pstmt = con.prepareCall(sql);//进行预处理
        pstmt.setString(1, account);
        return pstmt.executeUpdate();//返回int型 与executeQuery()不同  update、delete等executeUpdate() select用executeQuery()
    }

    //更新银行卡
    public int update(Connection con, Card card) throws Exception {
        String sql = "update card set password=?,name=?,sex=?,age=?,idCard=?,balance=? where account=?";
        PreparedStatement pstmt = con.prepareCall(sql);//预处理
        pstmt.setString(1, card.getPassword());//按序号分别向问号赋值
        pstmt.setString(2, card.getName());
        pstmt.setString(3, card.getSex());
        pstmt.setString(4, card.getAge());
        pstmt.setString(5, card.getIdCard());
        pstmt.setString(6, card.getBalance());
        pstmt.setString(7, card.getAccount());
        return pstmt.executeUpdate();
    }

    //查询所有账户信息
    public ResultSet listAll(Connection con, Card card) throws Exception {
        String sql = "select * from card";
        PreparedStatement pstmt = con.prepareCall(sql);//预处理
        return pstmt.executeQuery();
    }

    //查询单个账户信息
    public ResultSet list(Connection con, String account) throws Exception {
        String sql = "select * from card where account=?";
        PreparedStatement pstmt = con.prepareCall(sql);//预处理
        pstmt.setString(1, account);//向第一个问号赋值
        return pstmt.executeQuery();
    }


    //查询余额
    public String checkBalance(Connection con, String account) throws Exception {
        String sql = "select balance from card where account=?";
        PreparedStatement pstmt = con.prepareCall(sql);
        pstmt.setString(1, account);
        ResultSet rs = pstmt.executeQuery();//rs中存的是sql语句执行后的结果
        String balance = null;
        while (rs.next()) {//next函数判断是否包含东西
            balance = rs.getString("balance");//将结果的余额的值赋给balance
        }
        return balance;
    }

    //存钱
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
        pstmt1.setString(1, String.valueOf(Double.valueOf(balance) + Double.valueOf(deposit)));//double.valueOf是类型强制转换成double型  当前余额为先前余额(balance)+存入的钱（deposit）
        pstmt1.setString(2, account);
        return pstmt1.executeUpdate();
    }

    //取款
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
        pstmt1.setString(1, String.valueOf(Double.valueOf(balance) - Double.valueOf(withdraw)));//与存款设计思路类似  只不过是先前余额－取钱数
        pstmt1.setString(2, account);
        return pstmt1.executeUpdate();
    }

    //转账
    public int transfer(Connection con,String account1,String account2,String withdraw)throws Exception{
        String sql = "select * from card where account=?";//先找出登录用户的余额
        PreparedStatement pstmt = con.prepareCall(sql.toString());
        pstmt.setString(1, account1);
        ResultSet rs = pstmt.executeQuery();
        String balance = null;
        while (rs.next()) {
            balance = rs.getString("balance");
        }
        String sql1 = "update card set balance=? where account=?";
        PreparedStatement pstmt1 = con.prepareCall(sql1);
        pstmt1.setString(1, String.valueOf(Double.valueOf(balance) - Double.valueOf(withdraw)));//更新转账人的余额
        pstmt1.setString(2, account1);
        pstmt1.executeUpdate();

        String sql2 = "select * from card where account=?";//查找被转人的账户余额
        PreparedStatement pstmt2 = con.prepareCall(sql2);
        pstmt2.setString(1, account2);
        ResultSet rs1 = pstmt2.executeQuery();
        String balance1 = null;
        while (rs1.next()) {
            balance1 = rs1.getString("balance");
        }
        String sql3 = "update card set balance=? where account=?";
        PreparedStatement pstmt3 = con.prepareCall(sql3);
        pstmt3.setString(1, String.valueOf(Double.valueOf(balance1) + Double.valueOf(withdraw)));//更新被转账人的余额
        pstmt3.setString(2, account2);
        pstmt3.executeUpdate();
        return 0;
    }

    //匹配原密码
    public boolean password(Connection con, String account, String password) throws Exception {
        String sql = "select password from card where account like " + account;//选择需要改密的账户
        PreparedStatement pstmt = con.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();//rs中存放Sql执行的结果
        String originalPassword = null;//设置密码变量
        while (rs.next()) {
            originalPassword = rs.getString("password");//取原密码
        }
        return originalPassword.equals(password);//返回boolean型  相同返回true 不同返回false
    }

    //修改card表里的密码
    public int passwordChange(Connection con, String account, String password) throws Exception {
        String sql = "update card set password=? where account=?";//将匹配account的账户的密码更新
        PreparedStatement pstmt = con.prepareCall(sql);//预处理
        pstmt.setString(1, password);//按照问号的序号赋值
        pstmt.setString(2, account);
        return pstmt.executeUpdate();
    }

    //修改user表里的密码
    public int passwordChange1(Connection con, String account, String password) throws Exception {
        String sql = "update user set password=? where account=?";
        PreparedStatement pstmt = con.prepareCall(sql);//思路与修改card表密码相同
        pstmt.setString(1, password);
        pstmt.setString(2, account);
        return pstmt.executeUpdate();
    }
}
