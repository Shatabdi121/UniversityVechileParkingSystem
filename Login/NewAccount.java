package uvpm;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;

@SuppressWarnings({ "unused", "serial" })
public class NewAccount extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField mobileNo;
	private JTextField newPass;
	private JPasswordField conPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewAccount frame = new NewAccount();
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public NewAccount() {
		setBackground(Color.LIGHT_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 761, 580);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name :");
		lblNewLabel.setForeground(new Color(0, 0, 128));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel.setBounds(48, 58, 109, 24);
		contentPane.add(lblNewLabel);
		
		name = new JTextField();
		name.setBackground(Color.WHITE);
		name.setBounds(210, 47, 280, 51);
		contentPane.add(name);
		name.setColumns(25);
		
		JLabel mobile = new JLabel("Mobile Number");
		mobile.setForeground(new Color(0, 0, 128));
		mobile.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		mobile.setBounds(48, 123, 121, 36);
		contentPane.add(mobile);
		
		mobileNo = new JTextField();
		mobileNo.setBounds(210, 121, 280, 45);
		contentPane.add(mobileNo);
		mobileNo.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("New Password");
		lblNewLabel_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_1.setBounds(48, 308, 121, 24);
		contentPane.add(lblNewLabel_1);
		
		newPass = new JTextField();
		newPass.setBounds(210, 305, 280, 36);
		contentPane.add(newPass);
		newPass.setColumns(10);
		
		JLabel conPass = new JLabel("Confirm Password");
		conPass.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		conPass.setForeground(new Color(0, 0, 128));
		conPass.setBounds(48, 365, 149, 36);
		contentPane.add(conPass);
		
		conPassword = new JPasswordField();
		conPassword.setBounds(210, 368, 278, 36);
		contentPane.add(conPassword);
		
		
		JComboBox comboBox = new JComboBox();
		comboBox.setForeground(new Color(0, 0, 128));
		comboBox.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 22));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Student", "HOD", "Security"}));
		comboBox.setBounds(210, 194, 149, 63);
		contentPane.add(comboBox);
		
		JButton create = new JButton("Create");
		create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mobile = mobileNo.getText();
				String pass=newPass.getText();
            	String password = String.valueOf(conPassword.getPassword());
                String Name= name.getText();
                int len = mobile.length();
                boolean v=pass.equals(password);
                String type=comboBox.getSelectedItem().toString();
                if (len != 10) {
                    JOptionPane.showMessageDialog(create, "Enter a valid mobile number",mobile, JOptionPane.WARNING_MESSAGE);
                }
                else if(v) {
                	try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/uvpm", "root", "g12l8");

                        String query = "INSERT INTO user_info (u_name,u_pass,u_type,u_mob)values('" +Name+ "','" +password+ "','" +type+ "','"+mobile+"' );";
                        Statement sta = connection.createStatement();
                        int x = sta.executeUpdate(query);
                        if (x == 0) {
                            JOptionPane.showMessageDialog(create, "This is alredy exist");
                        } else {
                            JOptionPane.showMessageDialog(create,
                                    "Welcome,  Your account is sucessfully created");
                            dispose();
                            LoginPage obj=new LoginPage();
    		                obj.setTitle("Vechile Parking Management");
    		                obj.setVisible(true);
    		                obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		                obj.setResizable(true);
                        }
                        connection.close();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }	
                	
                }
                
                else {
                	JOptionPane.showMessageDialog(create, "Password mismatch",password,JOptionPane.WARNING_MESSAGE);
                
			}
			}
		});
		create.setForeground(new Color(0, 255, 0));
		create.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		create.setBounds(255, 438, 167, 45);
		contentPane.add(create);
		
		JLabel lblNewLabel_2 = new JLabel("UserType");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_2.setForeground(new Color(0, 0, 128));
		lblNewLabel_2.setBounds(48, 214, 121, 24);
		contentPane.add(lblNewLabel_2);
		
		JList list = new JList();
		list.setBounds(207, 221, 1, 1);
		contentPane.add(list);
		
		
		
		
	}
}
