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

    //可运行对象排在事件派发队列的队首时，就调用其run方法。其效果是允许事件派发线程调用另一个线程中的任意一个代码块。
    //启动应用程序
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

    //创建框架
    public PrintFrm(User user) {
        setTitle("打印凭条");
        setResizable(false);  //设置无法缩放
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);   //关闭窗口
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        JButton button = new JButton("确定");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "已打印，请取走凭条！");
            }
        });
        button.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        button.setBounds(160, 90, 124, 43);
        contentPane.add(button);
        //设置JFrame居中显示
        this.setLocationRelativeTo(null);
    }
}


