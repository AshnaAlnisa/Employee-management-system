package employee.management.system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.*;

public class RemoveEmployee extends JFrame implements ActionListener {
	
	Choice cempId;
	JButton delete, back;
	
	public RemoveEmployee() {
		
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		
		JLabel labelempId = new JLabel("Employee Id");
		labelempId.setBounds(50, 50, 100, 30);
		add(labelempId);
		
		cempId = new Choice();
		cempId.setBounds(200, 50, 150, 30);
		add(cempId);
		
		
		try {
			Statement s = Conn.conn().createStatement();
			String query = "select * from employee";
			ResultSet rs = s.executeQuery(query);
			
			while(rs.next())
			{
				cempId.add(rs.getString("empId"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JLabel labelname = new JLabel("Name");
		labelname.setBounds(50, 100, 100, 30);
		add(labelname);
		
		JLabel lblname = new JLabel();
		lblname.setBounds(200, 100, 100, 30 );
		add(lblname);
		
		
		JLabel labelphone = new JLabel("Phone");
		labelphone.setBounds(50, 150, 100, 30);
		add(labelphone);
		
		JLabel lblphone = new JLabel();
		lblphone.setBounds(200, 150, 100, 30 );
		add(lblphone);
		
		
		JLabel labelemail = new JLabel("Email");
		labelemail.setBounds(50, 200, 100, 30);
		add(labelemail);
		
		JLabel lblemail = new JLabel();
		lblemail.setBounds(200, 200, 100, 30 );
		add(lblemail);
		
		try {
			Statement s = Conn.conn().createStatement();
			ResultSet rs = s.executeQuery("select * from employee where empId = '"+cempId.getSelectedItem()+"'");
			
			while(rs.next())
			{
				lblname.setText(rs.getString("name"));
				lblemail.setText(rs.getString("email"));
				lblphone.setText(rs.getString("phone"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		cempId.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				try {
					Statement s = Conn.conn().createStatement();
					ResultSet rs = s.executeQuery("select * from employee where empId = '"+cempId.getSelectedItem()+"'");
					
					while(rs.next())
					{
						lblname.setText(rs.getString("name"));
						lblemail.setText(rs.getString("email"));
						lblphone.setText(rs.getString("phone"));
					}
				} catch (SQLException se) {
					// TODO Auto-generated catch block
					se.printStackTrace();
				}
			}
		});
		
		delete = new JButton("Delete");
		delete.setBounds(80, 300, 100, 30);
		delete.setBackground(Color.BLACK);
		delete.setForeground(Color.WHITE);
		delete.addActionListener(this);
		add(delete);
		
		back = new JButton("Back");
		back.setBounds(220, 300, 100, 30);
		back.setBackground(Color.BLACK);
		back.setForeground(Color.WHITE);
		back.addActionListener(this);
		add(back);
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/delete.png"));
		Image i2 = i1.getImage().getScaledInstance(600, 400, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel image = new JLabel(i3);
		image.setBounds(350, 0, 600, 400);
		add(image);
		
		
		setSize(1000, 400);
		setLocation(300, 150);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == delete)
		{
			try {
				Statement s = Conn.conn().createStatement();
				String query = "delete from employee where empId = '"+cempId.getSelectedItem()+"'";
				s.executeUpdate(query);
				JOptionPane.showMessageDialog(null, "Employee Information Deleted successfully");
				setVisible(false);
				new Home();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else
		{
			setVisible(false);
			new Home();
		}
	}
	
	
	
	public static void main(String[] args)
	{
		new RemoveEmployee();
	}


}
