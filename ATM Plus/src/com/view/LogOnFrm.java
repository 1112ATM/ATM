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
        //���ô���
        setResizable(false);
        setTitle("ATM");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(650, 250, 653, 449);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);//���ڵĲ��ֹ������Լ�����
        contentPane.setOpaque(false);//����Ϊ͸��

        //���ñ���
        ImageIcon icon=new ImageIcon(MainFrm.class.getResource("/images/����.jpg"));
        JLabel labelback=new JLabel(icon);
        labelback.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
        getLayeredPane().add(labelback,new Integer(Integer.MIN_VALUE));//�Ѹñ�ǩ������ײ�

        //���ñ��⡰��ӭ�����й����С�
        JLabel lblAtm = new JLabel("��ӭ�����й�����");
        //lblAtm.setIcon(new ImageIcon(LogOnFrm.class.getResource("/images/2.png")));
        lblAtm.setFont(new Font("΢���ź�", Font.BOLD, 30));
        lblAtm.setBounds(350, 25, 300, 59);
        contentPane.add(lblAtm);

        //�����˺ű�ǩ
        JLabel label = new JLabel("�˺�:");
        label.setIcon(new ImageIcon(LogOnFrm.class.getResource("/images/account_avatar_person_profile_user_24px_1225506_easyicon.net.png")));
        label.setFont(new Font("΢���ź�", Font.BOLD, 18));
        label.setBounds(150, 127, 88, 31);
        contentPane.add(label);

        //�����ı���
        accountTxt = new JTextField();
        accountTxt.setFont(new Font("����", Font.PLAIN, 18));
        accountTxt.setBounds(238, 127, 249, 32);
        contentPane.add(accountTxt);
        accountTxt.setColumns(10);

        //���������ǩ
        JLabel label_1 = new JLabel("����:");
        label_1.setIcon(new ImageIcon(LogOnFrm.class.getResource("/images/key_lock_open_password_unlock_24px_1225503_easyicon.net.png")));
        label_1.setFont(new Font("΢���ź�", Font.BOLD, 18));
        label_1.setBounds(150, 215, 88, 31);
        contentPane.add(label_1);

        //�����ı���
        passwordTxt = new JPasswordField();
        passwordTxt.setBounds(238, 216, 249, 32);
        contentPane.add(passwordTxt);

        //���õ�¼��ť
        btnLogin = new JButton("��¼");
        btnLogin.addActionListener(new ActionListener() {//��Ӽ�����
            public void actionPerformed(ActionEvent e) {
                loginActionPerformed(e);
            }
        });
        btnLogin.setBounds(132, 299, 125, 47);
        btnLogin.setFont(new Font("΢���ź�", Font.BOLD, 20));
        contentPane.add(btnLogin);

        //�������ð�ť
        JButton button = new JButton("����");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {//���ü�����
                resetValueActionPerformed(e);
            }
        });
        button.setFont(new Font("΢���ź�", Font.BOLD, 20));
        button.setBounds(389, 299, 125, 47);
        contentPane.add(button);

        //����JFrame������ʾ
        this.setLocationRelativeTo(null);
    }

    //��¼�¼�����
    private void loginActionPerformed(ActionEvent evt) {
        String account = this.accountTxt.getText();
        String password = new String(this.passwordTxt.getPassword());
        if (StringUtil.isEmpty(account)) {//������StringUtil��IsEmpty()
            JOptionPane.showMessageDialog(null, "�˺Ų���Ϊ�գ�");
            return;
        }
        if (StringUtil.isEmpty(password)) {
            JOptionPane.showMessageDialog(null, "���벻��Ϊ�գ�");
            return;
        }
        User user = new User(account, password);
        Connection con = null;
        try {
            con = dbUtil.getCon();//��ȡ���ݿ������
            User currentUser = userDao.login(con, user);//�õ���¼���û���Ϣ
            if (currentUser != null) {
                dispose();//�رյ�ǰ����
                MainFrm mainFrm = new MainFrm(user);//��MainFrm����
                mainFrm.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "�˺Ŵ�������������");
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

    //�����¼�����
    private void resetValueActionPerformed(ActionEvent evt) {
        this.accountTxt.setText("");
        this.passwordTxt.setText("");
    }
}
