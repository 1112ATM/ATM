package com.view;

import com.dao.CardDao;
import com.model.User;
import com.util.DbUtil;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class TransferFrm extends JFrame
{
    private JPanel contentPane;
    private JTextField textField;
    private DbUtil dbUtil = new DbUtil();
    private CardDao cardDao = new CardDao();
    //可运行对象排在事件派发队列的队首时，就调用其run方法。其效果是允许事件派发线程调用另一个线程中的任意一个代码块。
    //启动应用程序
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    TransferFrm frame = new TransferFrm(new User());//实例化一个窗体
                    frame.setVisible(true);//设置是否可见
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
    //创建框架
    public TransferFrm(User user)
    {
        setTitle("转账");
        setResizable(false);   //设置无法缩放
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);   //关闭窗口
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        //设置转账板块
        JLabel label = new JLabel("转账人：");
        label.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        label.setBounds(94, 81, 102, 31);
        contentPane.add(label);
        //设置转账人文本框
        JTextField jtextField1 = new JTextField();
        jtextField1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        jtextField1.setBounds(192, 78, 118, 37);
        contentPane.add(jtextField1);
        jtextField1.setColumns(10);
        //设置转账金额标志
        JLabel label1 = new JLabel("转账金额：");
        label1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        label1.setBounds(94, 121, 102, 31);
        contentPane.add(label1);
        //设置单位
        JLabel label_1 = new JLabel("元");
        label_1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        label_1.setBounds(316, 127, 24, 18);
        contentPane.add(label_1);
        //设置转账金额文本框
        JTextField jtextField2 = new JTextField();
        jtextField2.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        jtextField2.setBounds(192, 118, 118, 37);
        contentPane.add(jtextField2);
        jtextField2.setColumns(10);
        //设置确定按钮并加入点击事件
        JButton button = new JButton("确定");
        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String transfer = jtextField2.getText();
                String account = jtextField1.getText();
                Connection con = null;
                try
                {
                    con = dbUtil.getCon();
                    if(Double.valueOf(transfer) <= 0)
                    {  //转账金额小于等于0报错
                        JOptionPane.showMessageDialog(null, "转账金额金额不能小于或等于零！");
                        jtextField2.setText("");
                        return;
                        //转账金额大于卡内余额报错
                    }else if(Double.valueOf(transfer) > Double.valueOf(cardDao.checkBalance(con, user.getAccount())))
                    {
                        JOptionPane.showMessageDialog(null, "转账失败！转账金额大于卡内余额！");
                        jtextField2.setText("");
                        return;
                    }
                    if(cardDao.list(con, account).next()==false)
                    {   //账户不匹配报错
                        JOptionPane.showMessageDialog(null, "无此用户");
                        jtextField2.setText("");
                        return;
                    }
                    cardDao.transfer(con, user.getAccount(), account, transfer);
                    JOptionPane.showMessageDialog(null, "转账成功！");
                    dispose();
                } catch (Exception e1)
                {
                    e1.printStackTrace();
                }finally
                {
                    try
                    {
                        dbUtil.closeCon(con);
                    } catch (Exception e1)
                    {
                        e1.printStackTrace();
                    }
                }
            }
        });
        button.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        button.setBounds(160, 169, 124, 43);
        contentPane.add(button);
        //设置JFrame居中显示
        this.setLocationRelativeTo(null);
    }
}
