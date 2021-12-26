package com.view;

import java.awt.BorderLayout;
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
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class DepositFrm extends JFrame {
    private JPanel contentPane;
    private JTextField textField;
    private DbUtil dbUtil = new DbUtil();
    private CardDao cardDao = new CardDao();
     //�����ж��������¼��ɷ����еĶ���ʱ���͵�����run��������Ч���������¼��ɷ��̵߳�����һ���߳��е�����һ������顣
     //����Ӧ�ó���
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DepositFrm frame = new DepositFrm(new User());
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    //�������
    public DepositFrm(User user) {
        setTitle("���");
        setResizable(false);   //�����޷�����
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  //�رմ���
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        //���ô�������
        JLabel label = new JLabel("�����");
        label.setFont(new Font("΢���ź�", Font.PLAIN, 20));
        label.setBounds(94, 81, 102, 31);
        contentPane.add(label);
        //�����ı���
        textField = new JTextField();
        textField.setFont(new Font("΢���ź�", Font.PLAIN, 20));
        textField.setBounds(192, 78, 118, 37);
        contentPane.add(textField);
        textField.setColumns(10);
        //���õ�λ
        JLabel label_1 = new JLabel("Ԫ");
        label_1.setFont(new Font("΢���ź�", Font.PLAIN, 20));
        label_1.setBounds(316, 87, 24, 18);
        contentPane.add(label_1);
        //����ȷ����ť���������¼�
        JButton button = new JButton("ȷ��");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String balance = textField.getText();
                Connection con = null;
                try {
                    con = dbUtil.getCon();
                    if (Double.valueOf(balance) <= 0) {   //�����С�ڵ���0�򱨴�
                        JOptionPane.showMessageDialog(null, "������С�ڻ�����㣡");
                        textField.setText("");
                        return;
                    }
                    cardDao.deposit(con, user.getAccount(), balance);
                    JOptionPane.showMessageDialog(null, "���ɹ���");
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
        button.setFont(new Font("΢���ź�", Font.PLAIN, 20));
        button.setBounds(160, 169, 124, 43);
        contentPane.add(button);
        //����JFrame������ʾ
        this.setLocationRelativeTo(null);
    }
}
