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
    //�����ж��������¼��ɷ����еĶ���ʱ���͵�����run��������Ч���������¼��ɷ��̵߳�����һ���߳��е�����һ������顣
    //����Ӧ�ó���
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
    //�������
    public PasswordChangeFrm(User user) {
        setResizable(false);
        setTitle("�޸�����");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        //����ԭ�����־
        JLabel label = new JLabel("ԭ���룺");
        label.setFont(new Font("΢���ź�", Font.PLAIN, 18));
        label.setBounds(70, 41, 72, 30);
        contentPane.add(label);
        //ԭ���������ı���
        passwordField = new JPasswordField();
        passwordField.setBounds(138, 42, 220, 28);
        contentPane.add(passwordField);
        //�����������־
        JLabel label_1 = new JLabel("�����������룺");
        label_1.setFont(new Font("΢���ź�", Font.PLAIN, 18));
        label_1.setBounds(70, 92, 126, 30);
        contentPane.add(label_1);
        //�����������ı���
        passwordField_1 = new JPasswordField();
        passwordField_1.setBounds(194, 93, 164, 28);
        contentPane.add(passwordField_1);
        //�������������������־
        JLabel lblNewLabel = new JLabel("�ٴ����������룺");
        lblNewLabel.setFont(new Font("΢���ź�", Font.PLAIN, 18));
        lblNewLabel.setBounds(70, 146, 146, 24);
        contentPane.add(lblNewLabel);
        //�ڶ��������������ı���
        passwordField_2 = new JPasswordField();
        passwordField_2.setBounds(211, 144, 147, 28);
        contentPane.add(passwordField_2);
        //����ȷ����ť���������¼�
        JButton btnNewButton = new JButton("ȷ��");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Connection con = null;
                try {
                    con = dbUtil.getCon();
                    if (cardDao.password(con, user.getAccount(), new String(passwordField.getPassword())) == true) {  //ƥ��ԭ����ɹ�
                        if (new String(passwordField_1.getPassword()).equals(new String(passwordField_2.getPassword())) == true) {
                            cardDao.passwordChange(con, user.getAccount(), new String(passwordField_1.getPassword()));  //�޸�card��������
                            cardDao.passwordChange1(con, user.getAccount(), new String(passwordField_1.getPassword()));  //�޸�user��������
                            JOptionPane.showMessageDialog(null, "�����޸ĳɹ���");
                            dispose();
                        } else {  //�������������벻��ͬ����
                            JOptionPane.showMessageDialog(null, "�ٴ���������������һ������������벻ͬ�����������룡");
                            passwordField_1.setText("");
                            passwordField_2.setText("");
                        }
                    } else {   //ԭ���벻ƥ�䱨��
                        JOptionPane.showMessageDialog(null, "ԭ���������������������ԭ���룡");
                        passwordField.setText("");
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                } finally {
                    try {
                        dbUtil.closeCon(con);  //�ر����ݿ�����
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        btnNewButton.setFont(new Font("΢���ź�", Font.PLAIN, 20));
        btnNewButton.setBounds(155, 199, 134, 44);
        contentPane.add(btnNewButton);
        //����JFrame������ʾ
        this.setLocationRelativeTo(null);
    }
}
