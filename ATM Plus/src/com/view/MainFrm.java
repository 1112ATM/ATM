package com.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.model.User;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.HeadlessException;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class MainFrm extends JFrame {
    private JPanel contentPane;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrm frame = new MainFrm(new User());//实例化一个窗体
                    frame.setVisible(true);//设置是否可见
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    //设置框架
    public MainFrm(User user) {
        setResizable(false);
        setTitle("ATM主界面");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭并销毁 而不是退出
        setBounds(650, 250, 653, 449);
        contentPane = new JPanel();//实例化一个面板
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);//在本窗体CardQueryFrm中加入面板
        contentPane.setLayout(null);//手动的设置组件的坐标位置和大小
        contentPane.setOpaque(false);//设置为透明，防止将图片覆盖
        //添加背景图片
        ImageIcon icon=new ImageIcon(MainFrm.class.getResource("/images/背景.jpg"));
        JLabel labelback=new JLabel(icon);
        labelback.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
        getLayeredPane().add(labelback,new Integer(Integer.MIN_VALUE));
        //在面板上添加"请选择服务项目"文本
        JLabel label = new JLabel("请选择服务项目");
        label.setForeground(Color.BLACK);
        label.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        label.setBounds(215, 110, 240, 56);
        contentPane.add(label);
        //在面板上添加"Please select the service"文本
        JLabel label_english=new JLabel("Please select the service");
        label_english.setForeground(Color.BLACK);
        label_english.setFont(new Font("微软雅黑",Font.BOLD,15));
        label_english.setBounds(225,150,240,28);
        contentPane.add(label_english);
        //添加存款按钮
        JButton btn1 = new JButton("存款");
        btn1.setIcon(new ImageIcon(MainFrm.class.getResource("/images/存款.png")));
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DepositFrm depositFrm = new DepositFrm(user);
                depositFrm.setVisible(true);
            }
        });//添加点击事件
        btn1.setForeground(Color.black);
        btn1.setFont(new Font("微软雅黑", Font.PLAIN, 21));
        btn1.setBounds(0, 185, 180, 40);
        contentPane.add(btn1);
        //添加取款按钮
        JButton btn2 = new JButton("取款");
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WithdrawFrm withdrawFrm = new WithdrawFrm(user);
                withdrawFrm.setVisible(true);
            }
        });
        btn2.setIcon(new ImageIcon(MainFrm.class.getResource("/images/取款.png")));
        btn2.setForeground(Color.BLACK);
        btn2.setFont(new Font("微软雅黑", Font.PLAIN, 21));
        btn2.setBounds(0, 245, 180, 40);
        contentPane.add(btn2);
        //添加查询信息按钮
        JButton btn3 = new JButton("查询信息");
        btn3.setIcon(new ImageIcon(MainFrm.class.getResource("/images/查询.png")));
        btn3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardQueryFrm cardQueryFrm = new CardQueryFrm(user);
                cardQueryFrm.setVisible(true);
            }
        });
        btn3.setForeground(Color.BLACK);
        btn3.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        btn3.setBounds(0, 305, 180, 40);
        contentPane.add(btn3);
        //添加修改密码按钮
        JButton btn4 = new JButton("修改密码");
        btn4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PasswordChangeFrm passwordChangeFrm = new PasswordChangeFrm(user);
                passwordChangeFrm.setVisible(true);
            }
        });
        btn4.setIcon(new ImageIcon(MainFrm.class.getResource("/images/修改密码.png")));
        btn4.setForeground(Color.BLACK);
        btn4.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        btn4.setBounds(460, 185, 180, 40);
        contentPane.add(btn4);
        //添加转账按钮
        JButton btn5 = new JButton("转账");
        btn5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TransferFrm transferFrm = new TransferFrm(user);
                transferFrm.setVisible(true);
            }
        });
        btn5.setIcon(new ImageIcon(MainFrm.class.getResource("/images/转账.png")));
        btn5.setForeground(Color.BLACK);
        btn5.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        btn5.setBounds(460, 245, 180, 40);
        contentPane.add(btn5);
        //添加退卡按钮
        JButton btnExit = new JButton("退卡");
        btnExit.setIcon(new ImageIcon(MainFrm.class.getResource("/images/退卡.png")));
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "是否确认退卡？");
                if(result == 0) {
                    dispose();
                }
            }
        });
        btnExit.setForeground(Color.BLACK);
        btnExit.setFont(new Font("微软雅黑", Font.PLAIN, 22));
        btnExit.setBounds(460, 305, 180, 40);
        contentPane.add(btnExit);
        this.setLocationRelativeTo(null); //设置JFrame居中显示
    }
}