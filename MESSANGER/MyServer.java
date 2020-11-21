import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MyServer extends JFrame
{
	ArrayList al=new ArrayList();
	ArrayList users=new ArrayList();
	ServerSocket ss;
	Socket s;

	public final static int PORT=10;
	public final static String UPDATE_USERS="updateuserslist:";
	public final static String LOGOUT_MESSAGE="@@logoutme@@:";
	public MyServer()
	{
		try
		{
			ss=new ServerSocket(PORT);
			System.out.println("Server Started "+ss);
			JOptionPane.showMessageDialog(null,"Server Is Ready,Now You Can Start Conversation","Exit",JOptionPane.INFORMATION_MESSAGE);
			while(true)
			{
				s=ss.accept();
				Runnable r=new MyThread(s,al,users);
				Thread t=new Thread(r);
				t.start();
			}
 		 }
		catch(Exception e)
		{
			System.out.println(e);
		}
		if(ss==null)
			JOptionPane.showMessageDialog(null,"Server Is Already Running","Error",JOptionPane.ERROR_MESSAGE);
}
public static void main(String [] args)
{
	new MyServer();

}
}

