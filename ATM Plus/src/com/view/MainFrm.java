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

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrm frame = new MainFrm(new User());
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
    public MainFrm(User user) {
        setResizable(false);
        setTitle("ATM������");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(650, 250, 653, 449);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel label = new JLabel("��ѡ�������Ŀ");
        label.setForeground(Color.BLACK);
        label.setFont(new Font("΢���ź�", Font.PLAIN, 24));
        label.setBounds(218, 13, 240, 56);
        contentPane.add(label);

        JLabel label_english=new JLabel("Please select the service");
        label_english.setForeground(Color.BLACK);
        label_english.setFont(new Font("΢���ź�",Font.BOLD,15));
        label_english.setBounds(210,60,240,28);
        contentPane.add(label_english);

        JButton btn1 = new JButton("���");
        btn1.setIcon(new ImageIcon(MainFrm.class.getResource("/images/���.png")));
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DepositFrm depositFrm = new DepositFrm(user);
                depositFrm.setVisible(true);
            }
        });
        btn1.setForeground(Color.black);
        btn1.setFont(new Font("΢���ź�", Font.PLAIN, 21));
        btn1.setBounds(0, 120, 180, 40);
        contentPane.add(btn1);

        JButton btn2 = new JButton("ȡ��");
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WithdrawFrm withdrawFrm = new WithdrawFrm(user);
                withdrawFrm.setVisible(true);
            }
        });
        btn2.setIcon(new ImageIcon(MainFrm.class.getResource("/images/ȡ��.png")));
        btn2.setForeground(Color.BLACK);
        btn2.setFont(new Font("΢���ź�", Font.PLAIN, 21));
        btn2.setBounds(0, 190, 180, 40);
        contentPane.add(btn2);

        JButton btn3 = new JButton("��ѯ��Ϣ");
        btn3.setIcon(new ImageIcon(MainFrm.class.getResource("/images/��ѯ.png")));
        btn3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardQueryFrm cardQueryFrm = new CardQueryFrm(user);
                cardQueryFrm.setVisible(true);
            }
        });
        btn3.setForeground(Color.BLACK);
        btn3.setFont(new Font("΢���ź�", Font.PLAIN, 20));
        btn3.setBounds(0, 260, 180, 40);
        contentPane.add(btn3);

        JButton btn4 = new JButton("�޸�����");
        btn4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PasswordChangeFrm passwordChangeFrm = new PasswordChangeFrm(user);
                passwordChangeFrm.setVisible(true);
            }
        });
        btn4.setIcon(new ImageIcon(MainFrm.class.getResource("/images/�޸�����.png")));
        btn4.setForeground(Color.BLACK);
        btn4.setFont(new Font("΢���ź�", Font.PLAIN, 20));
        btn4.setBounds(460, 120, 180, 40);
        contentPane.add(btn4);

        JButton btn5 = new JButton("�޸�����");
        btn5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PasswordChangeFrm passwordChangeFrm = new PasswordChangeFrm(user);
                passwordChangeFrm.setVisible(true);
            }
        });
        btn5.setIcon(new ImageIcon(MainFrm.class.getResource("/images/ת��.png")));
        btn5.setForeground(Color.BLACK);
        btn5.setFont(new Font("΢���ź�", Font.PLAIN, 20));
        btn5.setBounds(460, 190, 180, 40);
        contentPane.add(btn5);

        JButton btnExit = new JButton("�˳�");
        btnExit.setIcon(new ImageIcon(MainFrm.class.getResource("/images/�˳�.png")));
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "�Ƿ��˳�ϵͳ��");
                if(result == 0) {
                    dispose();
                }
            }
        });
        btnExit.setForeground(Color.BLACK);
        btnExit.setFont(new Font("΢���ź�", Font.PLAIN, 22));
        btnExit.setBounds(460, 260, 180, 40);
        contentPane.add(btnExit);

        this.setLocationRelativeTo(null); //����JFrame������ʾ
    }
}