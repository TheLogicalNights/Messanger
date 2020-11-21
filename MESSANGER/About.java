import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class About extends JFrame
{
	private JButton back;
	private JPanel operation;
	private String about="";
	private JLabel d1,d2,d3,d4,d5,d6,d7,d8,d9,d10,d11,d12,d13,heading;

	public About()
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
		operation = new JPanel();
		operation.setBackground(new Color(0,0,0,100));
		operation.setBounds(20,10,550,540);
		operation.setLayout(null);
		background.add(operation);

		//JLabels
		heading = new JLabel("About");
		heading.setForeground(Color.BLACK);
		Font font = new Font("Serif",Font.BOLD,60);
		heading.setFont(font);
		heading.setBounds(190,0,500,80);
		operation.add(heading);

		Font font1 = new Font("Comic Sans MS",Font.BOLD,18);
		about=("This is a Brodcast Messaging Application.You can send");
		d1=new JLabel(about);
		d1.setBounds(50,80,600,30);
		d1.setFont(font1);


		d2 = new JLabel("message to all online users by using this application.");
		d2.setBounds(20,110,600,30);
		d2.setFont(font1);

		Font font2 = new Font("serif",Font.BOLD,25);
		d3= new JLabel("• To Start Conversation Follow Following Steps:");
		d3.setFont(font2);
		d3.setBounds(20,150,600,40);

		d4 = new JLabel("1) Run Server Program.");
		d4.setFont(font1);
		d4.setBounds(20,180,600,40);

		d5 = new JLabel("2) Run Client Program.");
		d5.setFont(font1);
		d5.setBounds(20,200,600,40);

		d6 = new JLabel("3) Login and enjoy conversation.");
		d6.setFont(font1);
		d6.setBounds(20,220,600,40);

		d7 = new JLabel("----------------------------------------------");
		d7.setFont(font1);
		d7.setBounds(20,250,600,40);

		d8 = new JLabel("• Version : 1.0.0.2020");
		d8.setFont(font1);
		d8.setBounds(20,300,600,40);

		d9 = new JLabel("• Warning! : This computer program is protected by");
		d9.setFont(font1);
		d9.setBounds(20,330,600,40);

		d10 = new JLabel("copyright law and international treaties.");
		d10.setFont(font1);
		d10.setBounds(40,350,600,40);

		d11 = new JLabel("• Author : Swapnil Adhav");
		d11.setFont(font1);
		d11.setBounds(20,270,600,40);

		d12 = new JLabel("• For more details Call On : 8446736267");
		d12.setFont(font1);
		d12.setBounds(20,380,600,40);

		d13 = new JLabel("• We Hope You will Enjoy This Software ! Thank You !");
		d13.setFont(font1);
		d13.setBounds(20,410,600,40);

		back = new JButton("BACK");
		back.setBounds(225,470,70,30);

		back.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(ae.getSource()==back)
					new MyClient();
					setVisible(false);
			}
		});

		operation.add(d1);
		operation.add(d2);
		operation.add(d3);
		operation.add(d4);
		operation.add(d5);
		operation.add(d6);
		operation.add(d7);
		operation.add(d8);
		operation.add(d9);
		operation.add(d10);
		operation.add(d11);
		operation.add(d12);
		operation.add(d13);
		operation.add(back);
		add(background);
		setResizable(true);
		setVisible(true);
	}
	public static void main(String args[])
	{
		new About();
	}
}