package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.model.Card;
import com.util.StringUtil;


public class CardDao {
    //���п����
    public int add(Connection con, Card card) throws Exception {
        String sql = "insert into card values(?,?,?,?,?,?,?)";
        PreparedStatement pstmt = con.prepareCall(sql);//��sql����Ԥ����
        pstmt.setString(1, card.getAccount());//����ŷֱ����ʺŸ�ֵ
        pstmt.setString(2, card.getName());
        pstmt.setString(3, card.getSex());
        pstmt.setString(4, card.getAge());
        pstmt.setString(5, card.getPassword());
        pstmt.setString(6, card.getIdCard());
        pstmt.setString(7, card.getBalance());
        return pstmt.executeUpdate();
    }

    //ɾ�����п�
    public int delete(Connection con, String account) throws Exception {
        String sql = "delete from card where account=?";
        PreparedStatement pstmt = con.prepareCall(sql);//����Ԥ����
        pstmt.setString(1, account);
        return pstmt.executeUpdate();//����int�� ��executeQuery()��ͬ  update��delete��executeUpdate() select��executeQuery()
    }

    //�������п�
    public int update(Connection con, Card card) throws Exception {
        String sql = "update card set password=?,name=?,sex=?,age=?,idCard=?,balance=? where account=?";
        PreparedStatement pstmt = con.prepareCall(sql);//Ԥ����
        pstmt.setString(1, card.getPassword());//����ŷֱ����ʺŸ�ֵ
        pstmt.setString(2, card.getName());
        pstmt.setString(3, card.getSex());
        pstmt.setString(4, card.getAge());
        pstmt.setString(5, card.getIdCard());
        pstmt.setString(6, card.getBalance());
        pstmt.setString(7, card.getAccount());
        return pstmt.executeUpdate();
    }

    //��ѯ�����˻���Ϣ
    public ResultSet listAll(Connection con, Card card) throws Exception {
        String sql = "select * from card";
        PreparedStatement pstmt = con.prepareCall(sql);//Ԥ����
        return pstmt.executeQuery();
    }

    //��ѯ�����˻���Ϣ
    public ResultSet list(Connection con, String account) throws Exception {
        String sql = "select * from card where account=?";
        PreparedStatement pstmt = con.prepareCall(sql);//Ԥ����
        pstmt.setString(1, account);//���һ���ʺŸ�ֵ
        return pstmt.executeQuery();
    }


    //��ѯ���
    public String checkBalance(Connection con, String account) throws Exception {
        String sql = "select balance from card where account=?";
        PreparedStatement pstmt = con.prepareCall(sql);
        pstmt.setString(1, account);
        ResultSet rs = pstmt.executeQuery();//rs�д����sql���ִ�к�Ľ��
        String balance = null;
        while (rs.next()) {//next�����ж��Ƿ��������
            balance = rs.getString("balance");//�����������ֵ����balance
        }
        return balance;
    }

    //��Ǯ
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
        pstmt1.setString(1, String.valueOf(Double.valueOf(balance) + Double.valueOf(deposit)));//double.valueOf������ǿ��ת����double��  ��ǰ���Ϊ��ǰ���(balance)+�����Ǯ��deposit��
        pstmt1.setString(2, account);
        return pstmt1.executeUpdate();
    }

    //ȡ��
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
        pstmt1.setString(1, String.valueOf(Double.valueOf(balance) - Double.valueOf(withdraw)));//�������˼·����  ֻ��������ǰ��ȡǮ��
        pstmt1.setString(2, account);
        return pstmt1.executeUpdate();
    }

    //ת��
    public int transfer(Connection con,String account1,String account2,String withdraw)throws Exception{
        String sql = "select * from card where account=?";//���ҳ���¼�û������
        PreparedStatement pstmt = con.prepareCall(sql.toString());
        pstmt.setString(1, account1);
        ResultSet rs = pstmt.executeQuery();
        String balance = null;
        while (rs.next()) {
            balance = rs.getString("balance");
        }
        String sql1 = "update card set balance=? where account=?";
        PreparedStatement pstmt1 = con.prepareCall(sql1);
        pstmt1.setString(1, String.valueOf(Double.valueOf(balance) - Double.valueOf(withdraw)));//����ת���˵����
        pstmt1.setString(2, account1);
        pstmt1.executeUpdate();

        String sql2 = "select * from card where account=?";//���ұ�ת�˵��˻����
        PreparedStatement pstmt2 = con.prepareCall(sql2);
        pstmt2.setString(1, account2);
        ResultSet rs1 = pstmt2.executeQuery();
        String balance1 = null;
        while (rs1.next()) {
            balance1 = rs1.getString("balance");
        }
        String sql3 = "update card set balance=? where account=?";
        PreparedStatement pstmt3 = con.prepareCall(sql3);
        pstmt3.setString(1, String.valueOf(Double.valueOf(balance1) + Double.valueOf(withdraw)));//���±�ת���˵����
        pstmt3.setString(2, account2);
        pstmt3.executeUpdate();
        return 0;
    }

    //ƥ��ԭ����
    public boolean password(Connection con, String account, String password) throws Exception {
        String sql = "select password from card where account like " + account;//ѡ����Ҫ���ܵ��˻�
        PreparedStatement pstmt = con.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();//rs�д��Sqlִ�еĽ��
        String originalPassword = null;//�����������
        while (rs.next()) {
            originalPassword = rs.getString("password");//ȡԭ����
        }
        return originalPassword.equals(password);//����boolean��  ��ͬ����true ��ͬ����false
    }

    //�޸�card���������
    public int passwordChange(Connection con, String account, String password) throws Exception {
        String sql = "update card set password=? where account=?";//��ƥ��account���˻����������
        PreparedStatement pstmt = con.prepareCall(sql);//Ԥ����
        pstmt.setString(1, password);//�����ʺŵ���Ÿ�ֵ
        pstmt.setString(2, account);
        return pstmt.executeUpdate();
    }

    //�޸�user���������
    public int passwordChange1(Connection con, String account, String password) throws Exception {
        String sql = "update user set password=? where account=?";
        PreparedStatement pstmt = con.prepareCall(sql);//˼·���޸�card��������ͬ
        pstmt.setString(1, password);
        pstmt.setString(2, account);
        return pstmt.executeUpdate();
    }
}
