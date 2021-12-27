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

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 64, 664, 150);

        //����JFrame������ʾ
        this.setLocationRelativeTo(null);

        CardTable = new JTable();
        CardTable.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        CardTable.setModel(new DefaultTableModel(
                new Object[][] {},
                //����JTable������
                new String[] {
                        "�˺�", "����", "����", "�Ա�", "����", "���֤��", "���"
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
        //�Զ���JTable�еĿ��
        //scrollPane.setViewportView(CardTable);
        this.fillTable(user.getAccount());
        JButton button = new JButton("ȷ��");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "�Ѵ�ӡ����ȡ��ƾ����");
                exportActionPerformed(e);
            }
        });
        button.setFont(new Font("΢���ź�", Font.PLAIN, 20));
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
    //����excel
    private void exportActionPerformed(ActionEvent evt) {
        FileDialog fd = new FileDialog(this, "�����˻���Ϣ", FileDialog.SAVE);
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


