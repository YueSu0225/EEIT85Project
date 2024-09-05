package tw.RC.tutor;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


import org.mindrot.BCrypt;

public class membertest extends JFrame{
	private JTextField inputmb, inputpw;
	private JButton member;
	private JTextArea log;
	private Connection connection;
	
	private membertest() {
		super("註冊");
		
		inputmb = new JTextField(15);
		inputpw = new JTextField(15);
		member = new JButton("註冊");

		log = new JTextArea();
		
		inputmb.setFont(new Font(null, Font.BOLD|Font.ITALIC, 24));
		inputpw.setFont(new Font(null, Font.BOLD|Font.ITALIC, 24));
		setLayout(new BorderLayout());
		
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // 添加物鍵間隔距離
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        mainPanel.add(new JLabel("帳號: "), gbc);
        
        //  inputmb
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(inputmb, gbc);

        //  member
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        mainPanel.add(member, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        mainPanel.add(new JLabel("密碼: "), gbc);
        
        //  inputpw
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(inputpw, gbc);


        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.NORTH);
        add(log, BorderLayout.CENTER);
		
		setSize(640, 480);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		initEvent();
		Connect();
	}
	private void initEvent() {

        member.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = inputmb.getText();
                String password = inputpw.getText();

                if (isValidEmail(email)) {
                    if (checkEmailaccount(email)) {
                        log.setText("這個Email已經註冊過了.");
                    } else if (CheckPassword(password)) {
                        insertNewEmail(email, password);
                        log.setText("註冊成功.");
                    } else {
                        log.setText("密碼不能少於8字.");
                    }
                } else {
                    log.setText("Email格式不正確.");
                }
            }
        });
        
    }
	private void Connect() {
		Properties prop = new Properties();
		prop.put("user", "root");
		prop.put("password", "root");
		
		String url = "jdbc:mysql://127.0.0.1:3306/owntest";
		 try {
			 connection  = DriverManager.getConnection(url, prop);
			 
	        } catch (Exception e) {
	        	System.out.println(e);
	        }
	}
		private boolean isValidEmail(String email) {
        
			String emailmember = "^[a-zA-Z0-9._%+-]+@(gmail\\.com|yahoo\\.com\\.tw)$";
        
			return email.matches(emailmember);
		}
	
       
        private boolean CheckPassword(String password) {
            return password.length() >= 8;
        }
     
        private boolean checkEmailaccount(String email) {
            String sql = "SELECT COUNT(*) FROM membertest WHERE account = ?";
            try  {
            	PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, email);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            return false;
        }

        private void insertNewEmail(String email, String password) {
            String insertsql = "INSERT INTO membertest (account, passwd) VALUES (?, ?)";
            String newpasswd = BCrypt.hashpw(password, BCrypt.gensalt());
            try (PreparedStatement stmt = connection.prepareStatement(insertsql)) {
                stmt.setString(1, email);
                stmt.setString(2, newpasswd); 
                stmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

	
	
	public static void main(String[] args) {
		new membertest();
		
	}

}
