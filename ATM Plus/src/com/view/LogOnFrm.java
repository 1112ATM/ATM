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

    /**
     * Launch the application.
     */
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

    /**
     * Create the frame.
     */
    public LogOnFrm() {
        setResizable(false);
        // setFont(new Font("ÀŒÃÂ", Font.PLAIN, 15));
        setTitle("ATM");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(650, 250, 653, 449);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        btnLogin = new JButton("µ«¬º");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginActionPerformed(e);
            }
        });
        btnLogin.setBounds(132, 299, 125, 47);
        btnLogin.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.BOLD, 20));
        contentPane.add(btnLogin);

        JLabel label = new JLabel("’À∫≈:");
        label.setIcon(new ImageIcon(LogOnFrm.class
                .getResource("/images/account_avatar_person_profile_user_24px_1225506_easyicon.net.png")));
        label.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.BOLD, 18));
        label.setBounds(150, 127, 88, 31);
        contentPane.add(label);

        accountTxt = new JTextField();
        accountTxt.setFont(new Font("ø¨ÃÂ", Font.PLAIN, 18));
        accountTxt.setBounds(238, 127, 249, 32);
        contentPane.add(accountTxt);
        accountTxt.setColumns(10);

        JLabel label_1 = new JLabel("√‹¬Î:");
        label_1.setIcon(new ImageIcon(
                LogOnFrm.class.getResource("/images/key_lock_open_password_unlock_24px_1225503_easyicon.net.png")));
        label_1.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.BOLD, 18));
        label_1.setBounds(150, 215, 88, 31);
        contentPane.add(label_1);

        passwordTxt = new JPasswordField();
        passwordTxt.setBounds(238, 216, 249, 32);
        contentPane.add(passwordTxt);

        JLabel lblAtm = new JLabel(" ");
        lblAtm.setIcon(new ImageIcon(LogOnFrm.class.getResource("/images/2.png")));
        lblAtm.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.BOLD, 40));
        lblAtm.setBounds(252, 25, 200, 59);
        contentPane.add(lblAtm);

        JButton button = new JButton("÷ÿ÷√");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetValueActionPerformed(e);
            }
        });
        button.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.BOLD, 20));
        button.setBounds(389, 299, 125, 47);
        contentPane.add(button);

        //…Ë÷√JFrameæ”÷–œ‘ æ
        this.setLocationRelativeTo(null);
    }

    /**
     * µ«¬º ¬º˛¥¶¿Ì
     *
     * @param
     */
    private void loginActionPerformed(ActionEvent evt) {
        String account = this.accountTxt.getText();
        String password = new String(this.passwordTxt.getPassword());
        if (StringUtil.isEmpty(account)) {
            JOptionPane.showMessageDialog(null, "’À∫≈≤ªƒ‹Œ™ø’£°");
            return;
        }
        if (StringUtil.isEmpty(password)) {
            JOptionPane.showMessageDialog(null, "√‹¬Î≤ªƒ‹Œ™ø’£°");
            return;
        }
        User user = new User(account, password);
        Connection con = null;
        try {
            con = dbUtil.getCon();
            User currentUser = userDao.login(con, user);
            if (currentUser != null) {
                dispose();
                MainFrm mainFrm = new MainFrm(user);
                mainFrm.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "’À∫≈¥ÌŒÛªÚ’ﬂ√‹¬Î¥ÌŒÛ£°");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    /**
     * ÷ÿ÷√ ¬º˛¥¶¿Ì
     *
     * @param
     */
    private void resetValueActionPerformed(ActionEvent evt) {
        this.accountTxt.setText("");
        this.passwordTxt.setText("");
    }
}
