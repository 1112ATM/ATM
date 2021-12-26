package com.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import com.dao.UserDao;
import com.model.User;
import com.util.DbUtil;
import com.util.StringUtil;

public class LogOnFrm extends JFrame {

    private JPanel contentPane;
    private JTextField accountTxt;
    private JButton btnLogin;
    private JPasswordField passwordTxt;
    private DbUtil dbUtil = new DbUtil();
    private UserDao userDao = new UserDao();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LogOnFrm frame = new LogOnFrm();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public LogOnFrm() {
        //设置窗口
        setResizable(false);
        setTitle("ATM");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(650, 250, 653, 449);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);//窗口的布局管理由自己设置
        contentPane.setOpaque(false);//设置为透明

        //设置背景
        ImageIcon icon=new ImageIcon(MainFrm.class.getResource("/images/背景.jpg"));
        JLabel labelback=new JLabel(icon);
        labelback.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
        getLayeredPane().add(labelback,new Integer(Integer.MIN_VALUE));//把该标签放在最底层

        //设置标题“欢迎来到中国银行”
        JLabel lblAtm = new JLabel("欢迎来到中国银行");
        //lblAtm.setIcon(new ImageIcon(LogOnFrm.class.getResource("/images/2.png")));
        lblAtm.setFont(new Font("微软雅黑", Font.BOLD, 30));
        lblAtm.setBounds(350, 25, 300, 59);
        contentPane.add(lblAtm);

        //设置账号标签
        JLabel label = new JLabel("账号:");
        label.setIcon(new ImageIcon(LogOnFrm.class.getResource("/images/account_avatar_person_profile_user_24px_1225506_easyicon.net.png")));
        label.setFont(new Font("微软雅黑", Font.BOLD, 18));
        label.setBounds(150, 127, 88, 31);
        contentPane.add(label);

        //设置文本框
        accountTxt = new JTextField();
        accountTxt.setFont(new Font("楷体", Font.PLAIN, 18));
        accountTxt.setBounds(238, 127, 249, 32);
        contentPane.add(accountTxt);
        accountTxt.setColumns(10);

        //设置密码标签
        JLabel label_1 = new JLabel("密码:");
        label_1.setIcon(new ImageIcon(LogOnFrm.class.getResource("/images/key_lock_open_password_unlock_24px_1225503_easyicon.net.png")));
        label_1.setFont(new Font("微软雅黑", Font.BOLD, 18));
        label_1.setBounds(150, 215, 88, 31);
        contentPane.add(label_1);

        //设置文本框
        passwordTxt = new JPasswordField();
        passwordTxt.setBounds(238, 216, 249, 32);
        contentPane.add(passwordTxt);

        //设置登录按钮
        btnLogin = new JButton("登录");
        btnLogin.addActionListener(new ActionListener() {//添加监听器
            public void actionPerformed(ActionEvent e) {
                loginActionPerformed(e);
            }
        });
        btnLogin.setBounds(132, 299, 125, 47);
        btnLogin.setFont(new Font("微软雅黑", Font.BOLD, 20));
        contentPane.add(btnLogin);

        //设置重置按钮
        JButton button = new JButton("重置");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {//设置监听器
                resetValueActionPerformed(e);
            }
        });
        button.setFont(new Font("微软雅黑", Font.BOLD, 20));
        button.setBounds(389, 299, 125, 47);
        contentPane.add(button);

        //设置JFrame居中显示
        this.setLocationRelativeTo(null);
    }

    //登录事件处理
    private void loginActionPerformed(ActionEvent evt) {
        String account = this.accountTxt.getText();
        String password = new String(this.passwordTxt.getPassword());
        if (StringUtil.isEmpty(account)) {//调用了StringUtil的IsEmpty()
            JOptionPane.showMessageDialog(null, "账号不能为空！");
            return;
        }
        if (StringUtil.isEmpty(password)) {
            JOptionPane.showMessageDialog(null, "密码不能为空！");
            return;
        }
        User user = new User(account, password);
        Connection con = null;
        try {
            con = dbUtil.getCon();//获取数据库的连接
            User currentUser = userDao.login(con, user);//得到登录的用户信息
            if (currentUser != null) {
                dispose();//关闭当前窗口
                MainFrm mainFrm = new MainFrm(user);//打开MainFrm窗口
                mainFrm.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "账号错误或者密码错误！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    //重置事件处理
    private void resetValueActionPerformed(ActionEvent evt) {
        this.accountTxt.setText("");
        this.passwordTxt.setText("");
    }
}
