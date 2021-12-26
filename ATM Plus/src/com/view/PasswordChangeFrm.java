package com.view;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.dao.CardDao;
import com.model.User;
import com.util.DbUtil;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class PasswordChangeFrm extends JFrame {
    private JPanel contentPane;
    private JPasswordField passwordField;
    private JPasswordField passwordField_1;
    private JPasswordField passwordField_2;
    private DbUtil dbUtil = new DbUtil();
    private CardDao cardDao = new CardDao();
    //可运行对象排在事件派发队列的队首时，就调用其run方法。其效果是允许事件派发线程调用另一个线程中的任意一个代码块。
    //启动应用程序
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PasswordChangeFrm frame = new PasswordChangeFrm(new User());
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    //创建框架
    public PasswordChangeFrm(User user) {
        setResizable(false);
        setTitle("修改密码");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        //设置原密码标志
        JLabel label = new JLabel("原密码：");
        label.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        label.setBounds(70, 41, 72, 30);
        contentPane.add(label);
        //原密码输入文本框
        passwordField = new JPasswordField();
        passwordField.setBounds(138, 42, 220, 28);
        contentPane.add(passwordField);
        //设置新密码标志
        JLabel label_1 = new JLabel("请输入新密码：");
        label_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        label_1.setBounds(70, 92, 126, 30);
        contentPane.add(label_1);
        //新密码输入文本框
        passwordField_1 = new JPasswordField();
        passwordField_1.setBounds(194, 93, 164, 28);
        contentPane.add(passwordField_1);
        //设置两次输入新密码标志
        JLabel lblNewLabel = new JLabel("再次输入新密码：");
        lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        lblNewLabel.setBounds(70, 146, 146, 24);
        contentPane.add(lblNewLabel);
        //第二次输入新密码文本框
        passwordField_2 = new JPasswordField();
        passwordField_2.setBounds(211, 144, 147, 28);
        contentPane.add(passwordField_2);
        //设置确定按钮并加入点击事件
        JButton btnNewButton = new JButton("确定");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Connection con = null;
                try {
                    con = dbUtil.getCon();
                    if (cardDao.password(con, user.getAccount(), new String(passwordField.getPassword())) == true) {  //匹配原密码成功
                        if (new String(passwordField_1.getPassword()).equals(new String(passwordField_2.getPassword())) == true) {
                            cardDao.passwordChange(con, user.getAccount(), new String(passwordField_1.getPassword()));  //修改card表里密码
                            cardDao.passwordChange1(con, user.getAccount(), new String(passwordField_1.getPassword()));  //修改user表里密码
                            JOptionPane.showMessageDialog(null, "密码修改成功！");
                            dispose();
                        } else {  //两次新密码输入不相同报错
                            JOptionPane.showMessageDialog(null, "再次输入的新密码与第一次输入的新密码不同！请重新输入！");
                            passwordField_1.setText("");
                            passwordField_2.setText("");
                        }
                    } else {   //原密码不匹配报错
                        JOptionPane.showMessageDialog(null, "原密码输入错误！请重新输入原密码！");
                        passwordField.setText("");
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                } finally {
                    try {
                        dbUtil.closeCon(con);  //关闭数据库连接
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        btnNewButton.setBounds(155, 199, 134, 44);
        contentPane.add(btnNewButton);
        //设置JFrame居中显示
        this.setLocationRelativeTo(null);
    }
}
