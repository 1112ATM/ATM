package com.view;

import com.model.User;
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

public class PrintFrm extends JFrame {
    private JPanel contentPane;

    //�����ж��������¼��ɷ����еĶ���ʱ���͵�����run��������Ч���������¼��ɷ��̵߳�����һ���߳��е�����һ������顣
    //����Ӧ�ó���
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PrintFrm frame = new PrintFrm(new User());
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //�������
    public PrintFrm(User user) {
        setTitle("��ӡƾ��");
        setResizable(false);  //�����޷�����
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);   //�رմ���
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        JButton button = new JButton("ȷ��");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "�Ѵ�ӡ����ȡ��ƾ����");
            }
        });
        button.setFont(new Font("΢���ź�", Font.PLAIN, 20));
        button.setBounds(160, 90, 124, 43);
        contentPane.add(button);
        //����JFrame������ʾ
        this.setLocationRelativeTo(null);
    }
}


