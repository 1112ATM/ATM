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
                    MainFrm frame = new MainFrm(new User());//ʵ����һ������
                    frame.setVisible(true);//�����Ƿ�ɼ�
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    //���ÿ��
    public MainFrm(User user) {
        setResizable(false);
        setTitle("ATM������");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�رղ����� �������˳�
        setBounds(650, 250, 653, 449);
        contentPane = new JPanel();//ʵ����һ�����
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);//�ڱ�����CardQueryFrm�м������
        contentPane.setLayout(null);//�ֶ����������������λ�úʹ�С
        contentPane.setOpaque(false);//����Ϊ͸������ֹ��ͼƬ����
        //��ӱ���ͼƬ
        ImageIcon icon=new ImageIcon(MainFrm.class.getResource("/images/����.jpg"));
        JLabel labelback=new JLabel(icon);
        labelback.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
        getLayeredPane().add(labelback,new Integer(Integer.MIN_VALUE));
        //����������"��ѡ�������Ŀ"�ı�
        JLabel label = new JLabel("��ѡ�������Ŀ");
        label.setForeground(Color.BLACK);
        label.setFont(new Font("΢���ź�", Font.PLAIN, 30));
        label.setBounds(215, 110, 240, 56);
        contentPane.add(label);
        //����������"Please select the service"�ı�
        JLabel label_english=new JLabel("Please select the service");
        label_english.setForeground(Color.BLACK);
        label_english.setFont(new Font("΢���ź�",Font.BOLD,15));
        label_english.setBounds(225,150,240,28);
        contentPane.add(label_english);
        //��Ӵ�ť
        JButton btn1 = new JButton("���");
        btn1.setIcon(new ImageIcon(MainFrm.class.getResource("/images/���.png")));
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DepositFrm depositFrm = new DepositFrm(user);
                depositFrm.setVisible(true);
            }
        });//��ӵ���¼�
        btn1.setForeground(Color.black);
        btn1.setFont(new Font("΢���ź�", Font.PLAIN, 21));
        btn1.setBounds(0, 185, 180, 40);
        contentPane.add(btn1);
        //���ȡ�ť
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
        btn2.setBounds(0, 245, 180, 40);
        contentPane.add(btn2);
        //��Ӳ�ѯ��Ϣ��ť
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
        btn3.setBounds(0, 305, 180, 40);
        contentPane.add(btn3);
        //����޸����밴ť
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
        btn4.setBounds(460, 185, 180, 40);
        contentPane.add(btn4);
        //���ת�˰�ť
        JButton btn5 = new JButton("ת��");
        btn5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TransferFrm transferFrm = new TransferFrm(user);
                transferFrm.setVisible(true);
            }
        });
        btn5.setIcon(new ImageIcon(MainFrm.class.getResource("/images/ת��.png")));
        btn5.setForeground(Color.BLACK);
        btn5.setFont(new Font("΢���ź�", Font.PLAIN, 20));
        btn5.setBounds(460, 245, 180, 40);
        contentPane.add(btn5);
        //����˿���ť
        JButton btnExit = new JButton("�˿�");
        btnExit.setIcon(new ImageIcon(MainFrm.class.getResource("/images/�˿�.png")));
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "�Ƿ�ȷ���˿���");
                if(result == 0) {
                    dispose();
                }
            }
        });
        btnExit.setForeground(Color.BLACK);
        btnExit.setFont(new Font("΢���ź�", Font.PLAIN, 22));
        btnExit.setBounds(460, 305, 180, 40);
        contentPane.add(btnExit);
        this.setLocationRelativeTo(null); //����JFrame������ʾ
    }
}