package com.view;

import com.dao.CardDao;
import com.model.User;
import com.util.DbUtil;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class TransferFrm extends JFrame
{
    private JPanel contentPane;
    private JTextField textField;
    private DbUtil dbUtil = new DbUtil();
    private CardDao cardDao = new CardDao();
    //�����ж��������¼��ɷ����еĶ���ʱ���͵�����run��������Ч���������¼��ɷ��̵߳�����һ���߳��е�����һ������顣
    //����Ӧ�ó���
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    TransferFrm frame = new TransferFrm(new User());//ʵ����һ������
                    frame.setVisible(true);//�����Ƿ�ɼ�
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
    //�������
    public TransferFrm(User user)
    {
        setTitle("ת��");
        setResizable(false);   //�����޷�����
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);   //�رմ���
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        //����ת�˰��
        JLabel label = new JLabel("ת���ˣ�");
        label.setFont(new Font("΢���ź�", Font.PLAIN, 20));
        label.setBounds(94, 81, 102, 31);
        contentPane.add(label);
        //����ת�����ı���
        JTextField jtextField1 = new JTextField();
        jtextField1.setFont(new Font("΢���ź�", Font.PLAIN, 20));
        jtextField1.setBounds(192, 78, 118, 37);
        contentPane.add(jtextField1);
        jtextField1.setColumns(10);
        //����ת�˽���־
        JLabel label1 = new JLabel("ת�˽�");
        label1.setFont(new Font("΢���ź�", Font.PLAIN, 20));
        label1.setBounds(94, 121, 102, 31);
        contentPane.add(label1);
        //���õ�λ
        JLabel label_1 = new JLabel("Ԫ");
        label_1.setFont(new Font("΢���ź�", Font.PLAIN, 20));
        label_1.setBounds(316, 127, 24, 18);
        contentPane.add(label_1);
        //����ת�˽���ı���
        JTextField jtextField2 = new JTextField();
        jtextField2.setFont(new Font("΢���ź�", Font.PLAIN, 20));
        jtextField2.setBounds(192, 118, 118, 37);
        contentPane.add(jtextField2);
        jtextField2.setColumns(10);
        //����ȷ����ť���������¼�
        JButton button = new JButton("ȷ��");
        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String transfer = jtextField2.getText();
                String account = jtextField1.getText();
                Connection con = null;
                try
                {
                    con = dbUtil.getCon();
                    if(Double.valueOf(transfer) <= 0)
                    {  //ת�˽��С�ڵ���0����
                        JOptionPane.showMessageDialog(null, "ת�˽�����С�ڻ�����㣡");
                        jtextField2.setText("");
                        return;
                        //ת�˽����ڿ�������
                    }else if(Double.valueOf(transfer) > Double.valueOf(cardDao.checkBalance(con, user.getAccount())))
                    {
                        JOptionPane.showMessageDialog(null, "ת��ʧ�ܣ�ת�˽����ڿ�����");
                        jtextField2.setText("");
                        return;
                    }
                    if(cardDao.list(con, account).next()==false)
                    {   //�˻���ƥ�䱨��
                        JOptionPane.showMessageDialog(null, "�޴��û�");
                        jtextField2.setText("");
                        return;
                    }
                    cardDao.transfer(con, user.getAccount(), account, transfer);
                    JOptionPane.showMessageDialog(null, "ת�˳ɹ���");
                    dispose();
                } catch (Exception e1)
                {
                    e1.printStackTrace();
                }finally
                {
                    try
                    {
                        dbUtil.closeCon(con);
                    } catch (Exception e1)
                    {
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
