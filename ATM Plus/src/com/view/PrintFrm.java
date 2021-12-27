package com.view;

import com.model.User;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import com.dao.CardDao;
import com.util.DbUtil;
import com.model.User;
import com.util.ExcelExporter;
import com.util.StringUtil;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

public class PrintFrm extends JFrame {
    private JPanel contentPane;
    private JTable CardTable;
    private DbUtil dbUtil = new DbUtil();
    private CardDao cardDao = new CardDao();
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

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 64, 664, 150);

        //设置JFrame居中显示
        this.setLocationRelativeTo(null);

        CardTable = new JTable();
        CardTable.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        CardTable.setModel(new DefaultTableModel(
                new Object[][] {},
                //设置JTable的列名
                new String[] {
                        "账号", "密码", "姓名", "性别", "年龄", "身份证号", "余额"
                }
        ) {
            boolean[] columnEditables = new boolean[] {
                    false, false, false, false, false, false, false
            };
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        CardTable.getColumnModel().getColumn(2).setPreferredWidth(64);
        CardTable.getColumnModel().getColumn(3).setPreferredWidth(49);
        CardTable.getColumnModel().getColumn(4).setPreferredWidth(50);
        CardTable.getColumnModel().getColumn(5).setPreferredWidth(91);
        CardTable.getColumnModel().getColumn(6).setPreferredWidth(90);
        //自定义JTable列的宽度
        //scrollPane.setViewportView(CardTable);
        this.fillTable(user.getAccount());
        JButton button = new JButton("确定");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "已打印，请取走凭条！");
                exportActionPerformed(e);
            }
        });
        button.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        button.setBounds(160, 90, 124, 43);
        contentPane.add(button);

    }
    private void fillTable(String account) {
        DefaultTableModel dtm = (DefaultTableModel) CardTable.getModel();
        dtm.setRowCount(0);
        Connection con = null;
        try {
            con = dbUtil.getCon();
            ResultSet rs = cardDao.list(con, account);
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("account"));
                v.add(rs.getString("password"));
                v.add(rs.getString("name"));
                v.add(rs.getString("sex"));
                v.add(rs.getString("age"));
                v.add(rs.getString("idCard"));
                v.add(rs.getString("balance"));
                dtm.addRow(v);
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
    //导出excel
    private void exportActionPerformed(ActionEvent evt) {
        FileDialog fd = new FileDialog(this, "保存账户信息", FileDialog.SAVE);
        fd.setLocation(500,350);
        fd.setVisible(true);
        String stringfile = fd.getDirectory() + fd.getFile() + ".xls";
        ExcelExporter export = new ExcelExporter();
        try {
            export.exportTable(CardTable, new File(stringfile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


