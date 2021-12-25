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

    /**
     * Launch the application.
     */
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

    /**
     * Create the frame.
     */
    public WithdrawFrm(User user) {
        setResizable(false);
        setTitle("取款");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel label = new JLabel("取出金额：");
        label.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        label.setBounds(94, 81, 102, 31);
        contentPane.add(label);


        textField = new JTextField();
        textField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        textField.setBounds(192, 78, 118, 37);
        contentPane.add(textField);
        textField.setColumns(10);

        JButton button = new JButton("确定");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String withdraw = textField.getText();
                Connection con = null;
                try {
                    con = dbUtil.getCon();
                    if (Double.valueOf(withdraw) <= 0) {
                        JOptionPane.showMessageDialog(null, "取款金额不能小于或等于零！");
                        textField.setText("");
                        return;
                    } else if (Double.valueOf(withdraw) > Double.valueOf(cardDao.checkBalance(con, user.getAccount()))) {
                        JOptionPane.showMessageDialog(null, "取款失败！取款金额大于卡内余额！");
                        textField.setText("");
                        return;
                    }
                    cardDao.withdraw(con, user.getAccount(), withdraw);
                    JOptionPane.showMessageDialog(null, "取款成功！");
                    dispose();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } finally {
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

        JLabel label_1 = new JLabel("元");
        label_1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        label_1.setBounds(316, 87, 24, 18);
        contentPane.add(label_1);

        this.setLocationRelativeTo(null); //设置JFrame居中显示
    }
}
