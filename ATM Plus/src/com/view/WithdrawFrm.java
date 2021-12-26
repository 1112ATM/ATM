package com.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import com.dao.CardDao;
import com.model.User;
import com.util.DbUtil;

public class WithdrawFrm extends JFrame {
    private JPanel contentPane;
    private JTextField textField;
    private DbUtil dbUtil = new DbUtil();
    private CardDao cardDao = new CardDao();
    //可运行对象排在事件派发队列的队首时，就调用其run方法。其效果是允许事件派发线程调用另一个线程中的任意一个代码块。
    //启动应用程序
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    WithdrawFrm frame = new WithdrawFrm(new User());
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    //创建框架
    public WithdrawFrm(User user) {
        setTitle("取款");
        setResizable(false);  //设置无法缩放
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);   //关闭窗口
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        //设置取出金额板块
        JLabel label = new JLabel("取出金额：");
        label.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        label.setBounds(94, 81, 102, 31);
        contentPane.add(label);
        //设置文本框
        textField = new JTextField();
        textField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        textField.setBounds(192, 78, 118, 37);
        contentPane.add(textField);
        textField.setColumns(10);
        //设置单位
        JLabel label_1 = new JLabel("元");
        label_1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        label_1.setBounds(316, 87, 24, 18);
        contentPane.add(label_1);
        //设置确定按钮并加入点击事件
        JButton button = new JButton("确定");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String withdraw = textField.getText();
                Connection con = null;
                try {
                    con = dbUtil.getCon();
                    if (Double.valueOf(withdraw) <= 0) {   //当取款金额小于等于0时报错
                        JOptionPane.showMessageDialog(null, "取款金额不能小于或等于零！");
                        textField.setText("");
                        return;
                        //当取款金额大于卡内余额时报错
                    } else if (Double.valueOf(withdraw) > Double.valueOf(cardDao.checkBalance(con, user.getAccount()))) {
                        JOptionPane.showMessageDialog(null, "取款失败！取款金额大于卡内余额！");
                        textField.setText("");
                        return;
                    }
                    cardDao.withdraw(con, user.getAccount(), withdraw);
                    JOptionPane.showMessageDialog(null, "取款成功！");
                    dispose();
                } catch (Exception e1) {
                    e1.printStackTrace();
                } finally {
                    try {
                        dbUtil.closeCon(con);
                    } catch (Exception e1) {
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
