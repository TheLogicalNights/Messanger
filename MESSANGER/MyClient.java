import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MyClient extends JFrame
{
	Socket s;
	DataInputStream dis;
	DataOutputStream dos;
	JButton sendButton, logoutButton,loginButton,exitButton,about;

	JTextArea txtBroadcast;
	JTextArea txtMessage;
	JList usersList;
	JLabel title1,title2;
	public MyClient()
	{
		setLayout(null);
		setBounds(50,20,600,600);
		setTitle("Login for Chat");

		ImageIcon bg_img = new ImageIcon("background2.jpg");
		Image img = bg_img.getImage();
		Image temp = img.getScaledInstance(600,600,Image.SCALE_SMOOTH);
		bg_img = new ImageIcon(temp);
		JLabel background = new JLabel("",bg_img,JLabel.CENTER);
		background.setBounds(0,0,600,600);

		//JPanels
		JPanel operation = new JPanel();
		operation.setBackground(new Color(0,0,0,100));
		operation.setBounds(20,10,550,540);
		operation.setLayout(null);

		//JLabels
		title1 = new JLabel("--------BroadCast messages from all online users--------");
		title1.setBounds(40,00,500,40);
		title1.setForeground(Color.WHITE);
		Font font = new Font("TimesRoman",Font.BOLD,20);
		title1.setFont(font);

		title2 = new JLabel("---------------------------Online users---------------------------");
		title2.setBounds(40,250,500,40);
		title2.setForeground(Color.WHITE);
		title2.setFont(font);

		//JTextArea
		txtBroadcast=new JTextArea(50000,30000);
		txtBroadcast.setBounds(70,50,400,200);
		JScrollPane sp1 = new JScrollPane(txtBroadcast);
		sp1.setBounds(70,50,400,200);
		txtBroadcast.setEditable(false);
		txtMessage=new JTextArea(2,20);
		JScrollPane sp2 = new JScrollPane(txtMessage);
		sp2.setBounds(30,440,400,50);

		//JList
		usersList=new JList();
		usersList.setBounds(60,280,300,200);
		JScrollPane sp3 = new JScrollPane(usersList);
		sp3.setBounds(140,300,250,120);

		//JButtons
		sendButton=new JButton("send");
		sendButton.setBounds(450,448,80,30);
		logoutButton=new JButton("Log out");
		logoutButton.setBounds(160,500,100,30);
		loginButton=new JButton("Log in");
		loginButton.setBounds(40,500,100,30);
		exitButton=new JButton("Exit");
		exitButton.setBounds(280,500,100,30);
		about=new JButton("About");
		about.setBounds(400,500,100,30);
		logoutButton.setEnabled(false);
		loginButton.setEnabled(true);

		//EventHandling
		txtMessage.addFocusListener(new FocusAdapter()
		{
			public void focusGained(FocusEvent fe)
			{
				txtMessage.selectAll();
			}
		});

		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent ev)
			{
				if(s!=null)
				{
					JOptionPane.showMessageDialog(operation,"You Are Logged Out Right Now. ","Exit",JOptionPane.INFORMATION_MESSAGE);
					logoutSession();
				}
				System.exit(0);
			}
		});

		sendButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==sendButton)
				{
					if(s==null)
				 	{
						JOptionPane.showMessageDialog(operation,"You Are Not Logged In,Please Login First");
						return;
					}
					try
					{
						dos.writeUTF(txtMessage.getText());
						txtMessage.setText("");
			 		}
					catch(Exception e)
					{
						System.out.println(e);
					}
				}
			}
		});

		loginButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==loginButton)
				{
					String uname=JOptionPane.showInputDialog(operation,"Enter Your Nick Name: ");
					if(uname!=null)
						clientChat(uname);
					about.setEnabled(false);

				}
			}
		});

		logoutButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==logoutButton)
				{
					if(s!=null)
						logoutSession();
					about.setEnabled(true);
				}
			}
		});

		exitButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==exitButton)
				{
					if(s!=null)
					{
						JOptionPane.showMessageDialog(operation,"You are Logged Out Right Now. ","Exit",JOptionPane.INFORMATION_MESSAGE);
						logoutSession();
					}
					System.exit(0);
				}
			}
		});
		about.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==about)
					new About();
				setVisible(false);
			}
		});


		operation.add(sendButton);
		operation.add(logoutButton);
		operation.add(loginButton);
		operation.add(exitButton);
		operation.add(about);
		operation.add(title1);
		operation.add(title2);
		background.add(operation);
		operation.add(sp1);
		operation.add(sp2);
		operation.add(sp3);
		add(background);
		setResizable(false);
		setVisible(true);
	}
	public void logoutSession()
	{
		if(s==null)
			return;
		try
		{
			dos.writeUTF(MyServer.LOGOUT_MESSAGE);
			Thread.sleep(500);
			s=null;
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		logoutButton.setEnabled(false);
		loginButton.setEnabled(true);
		setTitle("Login for Chat");
	}
	void clientChat(String uname)
	{
		try
		{
	    	 s=new Socket("172.20.10.2",MyServer.PORT);
	    	 dis=new DataInputStream(s.getInputStream());
	    	 dos=new DataOutputStream(s.getOutputStream());
	    	 ClientThread ct=new ClientThread(dis,this);
	    	 Thread t1=new Thread(ct);
	    	 t1.start();
	    	 dos.writeUTF(uname);
	    	 setTitle(uname+" Chat Window");
	    }
		catch(Exception e)
		{
			txtBroadcast.append("Error : Please Run Server Program First");
		}
		logoutButton.setEnabled(true);
		loginButton.setEnabled(false);
	}

	public static void main(String args[])
	{
		new MyClient();
	}
}