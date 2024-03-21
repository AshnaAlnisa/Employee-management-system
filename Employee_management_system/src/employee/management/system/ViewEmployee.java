package employee.management.system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import net.proteanit.sql.*;

import javax.swing.*;

public class ViewEmployee extends JFrame implements ActionListener {
	
	JTable table;
	Choice cemployeeId;
	JButton search, print, update, back;
	
	public ViewEmployee() 
	{
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		
		JLabel searchlbl = new JLabel("Search by employee Id");
		searchlbl.setBounds(20, 20, 150, 20);
		add(searchlbl);
		
		cemployeeId = new Choice();
		cemployeeId.setBounds(180, 20, 150, 20);
		add(cemployeeId);
		
		table = new JTable();
		
		try {
			Statement s = Conn.conn().createStatement();
			
			 ResultSet rs = s.executeQuery("select * from employee");			 
	         table.setModel(DbUtils.resultSetToTableModel(rs));
	         
	         ResultSet rs1 = s.executeQuery("select * from employee");
	         while(rs1.next())
	         {
	        	 cemployeeId.add(rs1.getString("empId"));
	         }
	        	 
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JScrollPane jsp = new JScrollPane(table);
		jsp.setBounds(0, 100, 900, 600);
		add(jsp);
		
		
		search = new JButton("Search");
		search.setBounds(20, 70, 80, 20);
		search.addActionListener(this);
		add(search);
		
		print = new JButton("Print");
		print.setBounds(120, 70, 80, 20);
		print.addActionListener(this);
		add(print);
		
		update = new JButton("Update");
		update.setBounds(220, 70, 80, 20);
		update.addActionListener(this);
		add(update);
		
		back = new JButton("Back");
		back.setBounds(320, 70, 80, 20);
		back.addActionListener(this);
		add(back);
		
		
		setSize(900, 700);
		setLocation(300, 100);
		setVisible(true);
	}
	
	
	public static void main(String[] args)
	{
		new ViewEmployee();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == search)
		{
			String query = "select * from employee where empId ='"+cemployeeId.getSelectedItem()+"'";

			try {
				Statement s = Conn.conn().createStatement();
				ResultSet rs = s.executeQuery(query);
				table.setModel(DbUtils.resultSetToTableModel(rs));	
			} 
			catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource() == print)
		{
			try {
				table.print();
			} catch (PrinterException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource() == update)
		{
			setVisible(false);
			new UpdateEmployee(cemployeeId.getSelectedItem());
		}
		else
		{
			setVisible(false);
			new Home();
		}
		
	}

}
