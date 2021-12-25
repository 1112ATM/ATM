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

public class TransferFrm extends JFrame {
    private JPanel contentPane;
    private JTextField textField;

    private DbUtil dbUtil = new DbUtil();
    private CardDao cardDao = new CardDao();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TransferFrm frame = new TransferFrm(new User());
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public TransferFrm(User user) {
        setResizable(false);
        setTitle("转账");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel label = new JLabel("转账人：");
        label.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        label.setBounds(94, 81, 102, 31);
        contentPane.add(label);


        JTextField jtextField1 = new JTextField();
        jtextField1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        jtextField1.setBounds(192, 78, 118, 37);
        contentPane.add(jtextField1);
        jtextField1.setColumns(10);

        JLabel label1 = new JLabel("取出金额：");
        label1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        label1.setBounds(94, 121, 102, 31);
        contentPane.add(label1);

        JLabel label_1 = new JLabel("元");
        label_1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        label_1.setBounds(316, 127, 24, 18);
        contentPane.add(label_1);


        textField  = new JTextField();
        textField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        textField.setBounds(192, 118, 118, 37);
        contentPane.add(textField);
        textField.setColumns(10);

        JButton button = new JButton("确定");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String withdraw = textField.getText();
                String account1 = jtextField1.getText();
                Connection con = null;
                try {
                    con = dbUtil.getCon();
                    if(Double.valueOf(withdraw) <= 0) {
                        JOptionPane.showMessageDialog(null, "转账金额金额不能小于或等于零！");
                        textField.setText("");
                        return;
                    }else if(Double.valueOf(withdraw) > Double.valueOf(cardDao.checkBalance(con, user.getAccount()))) {
                        JOptionPane.showMessageDialog(null, "转账失败！转账金额大于卡内余额！");
                        textField.setText("");
                        return;
                    }
                    if(cardDao.list(con, account1).next()==false) {
                        JOptionPane.showMessageDialog(null, "无此用户");
                        textField.setText("");
                        return;
                    }
                    cardDao.transfer(con, user.getAccount(), account1, withdraw);
                    JOptionPane.showMessageDialog(null, "转账成功！");
                    dispose();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }finally {
                    try {
                        dbUtil.closeCon(con);
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            }
        });

        button.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        button.setBounds(160, 169, 124, 43);
        contentPane.add(button);

        this.setLocationRelativeTo(null); //设置JFrame居中显示
    }
}
