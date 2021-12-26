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
        setTitle("ATMÖ÷½çÃæ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(650, 250, 653, 449);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setOpaque(false);

        ImageIcon icon=new ImageIcon(MainFrm.class.getResource("/images/±³¾°.jpg"));
        JLabel labelback=new JLabel(icon);
        labelback.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
        getLayeredPane().add(labelback,new Integer(Integer.MIN_VALUE));

        JLabel label = new JLabel("ÇëÑ¡Ôñ·þÎñÏîÄ¿");
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 30));
        label.setBounds(215, 110, 240, 56);
        contentPane.add(label);

        JLabel label_english=new JLabel("Please select the service");
        label_english.setForeground(Color.BLACK);
        label_english.setFont(new Font("Î¢ÈíÑÅºÚ",Font.BOLD,15));
        label_english.setBounds(225,150,240,28);
        contentPane.add(label_english);

        JButton btn1 = new JButton("´æ¿î");
        btn1.setIcon(new ImageIcon(MainFrm.class.getResource("/images/´æ¿î.png")));
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DepositFrm depositFrm = new DepositFrm(user);
                depositFrm.setVisible(true);
            }
        });
        btn1.setForeground(Color.black);
        btn1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 21));
        btn1.setBounds(0, 185, 180, 40);
        contentPane.add(btn1);


        JButton btn2 = new JButton("È¡¿î");
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WithdrawFrm withdrawFrm = new WithdrawFrm(user);
                withdrawFrm.setVisible(true);
            }
        });
        btn2.setIcon(new ImageIcon(MainFrm.class.getResource("/images/È¡¿î.png")));
        btn2.setForeground(Color.BLACK);
        btn2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 21));
        btn2.setBounds(0, 245, 180, 40);
        contentPane.add(btn2);

        JButton btn3 = new JButton("²éÑ¯ÐÅÏ¢");
        btn3.setIcon(new ImageIcon(MainFrm.class.getResource("/images/²éÑ¯.png")));
        btn3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardQueryFrm cardQueryFrm = new CardQueryFrm(user);
                cardQueryFrm.setVisible(true);
            }
        });
        btn3.setForeground(Color.BLACK);
        btn3.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 20));
        btn3.setBounds(0, 305, 180, 40);
        contentPane.add(btn3);

        JButton btn4 = new JButton("ÐÞ¸ÄÃÜÂë");
        btn4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PasswordChangeFrm passwordChangeFrm = new PasswordChangeFrm(user);
                passwordChangeFrm.setVisible(true);
            }
        });
        btn4.setIcon(new ImageIcon(MainFrm.class.getResource("/images/ÐÞ¸ÄÃÜÂë.png")));
        btn4.setForeground(Color.BLACK);
        btn4.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 20));
        btn4.setBounds(460, 185, 180, 40);
        contentPane.add(btn4);

        JButton btn5 = new JButton("×ªÕË");
        btn5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TransferFrm transferFrm = new TransferFrm(user);
                transferFrm.setVisible(true);
            }
        });
        btn5.setIcon(new ImageIcon(MainFrm.class.getResource("/images/×ªÕË.png")));
        btn5.setForeground(Color.BLACK);
        btn5.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 20));
        btn5.setBounds(460, 245, 180, 40);
        contentPane.add(btn5);

        JButton btnExit = new JButton("ÍË¿¨");
        btnExit.setIcon(new ImageIcon(MainFrm.class.getResource("/images/ÍË¿¨.png")));
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "ÊÇ·ñÈ·ÈÏÍË¿¨£¿");
                if(result == 0) {
                    dispose();
                }
            }
        });
        btnExit.setForeground(Color.BLACK);
        btnExit.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 22));
        btnExit.setBounds(460, 305, 180, 40);
        contentPane.add(btnExit);

        this.setLocationRelativeTo(null); //ÉèÖÃJFrame¾ÓÖÐÏÔÊ¾
    }
}