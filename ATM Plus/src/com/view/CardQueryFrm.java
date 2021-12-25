package com.view;

import java.awt.EventQueue;
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
import com.model.Card;
import com.model.User;
import com.util.DbUtil;

import java.awt.Font;
import java.awt.Dialog.ModalExclusionType;

public class CardQueryFrm extends JFrame {

    private JPanel contentPane;
    private JTable CardTable;

    private DbUtil dbUtil = new DbUtil();
    private CardDao cardDao = new CardDao();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CardQueryFrm frame = new CardQueryFrm(new User());
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
    public CardQueryFrm(User user) {
        setResizable(false);
        setTitle("用户信息");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //关闭并销毁 而不是退出
        setBounds(100, 100, 673, 241);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(14, 54, 639, 104);
        contentPane.add(scrollPane);

        CardTable = new JTable();
        CardTable.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        CardTable.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "账号", "密码", "姓名", "性别", "年龄", "身份证号", "余额"
                }
        ) {
            boolean[] columnEditables = new boolean[]{
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
        scrollPane.setViewportView(CardTable);

        this.fillTable(user.getAccount());

        this.setLocationRelativeTo(null); //设置JFrame居中显示
    }

    /**
     * 初始化表格
     *
     * @paramcard
     */
    private void fillTable(String account) {
        DefaultTableModel dtm = (DefaultTableModel) CardTable.getModel();
        dtm.setRowCount(0); //设置成0行
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
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
