import java.awt.*;
import java.io.*;
import java.util.Vector;
import javax.swing.*;

public class Main{
	public static void main(String[] args) {
		MainFrame f = new MainFrame();
	}
}

class MainFrame extends JFrame {
	Vector bookList=new Vector();
	
	MainFrame(){
		super("书籍管理系统");
		
		this.setTitle("书籍管理系统");
		this.setSize(700,320);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		this.readBooFile();
		this.showBooPanel();
		this.setVisible(true);

		this.setResizable(false);
		this.setSize(700,320);
		this.setLocationRelativeTo(this.getOwner());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	void readBooFile(){
		try {
			FileInputStream fis;
			fis = new FileInputStream("bookname.txt");
			InputStreamReader dis=new InputStreamReader(fis);
			BufferedReader reader=new BufferedReader(dis);
			String s;
			while((s=reader.readLine())!=null)
			{
				Book boo=new Book();
				String[] temp=s.split(" ");
				boo.setbooNo(temp[0]);
				boo.setbooName(temp[1]);

				bookList.add(boo);
			}
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
	
	void showBooPanel(){
		BooPanel booPanel=new BooPanel();
		booPanel.bookList=this.bookList;
		this.add(booPanel,BorderLayout.CENTER);
		booPanel.showBook(0);
		this.setVisible(true);
	}
}

class BooPanel extends JPanel {
	private JTextField booNo=new JTextField();											//编号
	private JTextField booName=new JTextField();										//书名								
	Vector bookList=new Vector();
	private String[] btnStr={"第一个","上一个","下一个","最后一个","添加","修改","删除"};
	private JButton[] btn= new JButton[btnStr.length];
	
	BooPanel(){
		this.setLayout(null);
		//显示编号
		JLabel lb1=new JLabel("编号：");
		lb1.setBounds(30, 10, 100, 20);
		this.add(lb1);
		booNo.setBounds(100,10, 100, 20);
		this.add(booNo);
		//显示书名
		JLabel lb2=new JLabel("书名：");
		lb2.setBounds(30, 60, 100, 20);
		this.add(lb2);
		booName.setBounds(100,60, 100, 20);
		this.add(booName);
		//显示控制按钮
		for(int i=0;i<btn.length;i++){
			btn[i]=new JButton(btnStr[i]);
			btn[i].setBounds(30+i*90, 210, 90, 30);
			this.add(btn[i]);
		}
	}
	
	void showBook(int offset){
		Book boo=(Book) bookList.get(offset);
		this.booNo.setText(boo.getbooNo());
		this.booName.setText(boo.getbooName());
	}
}

class Book {
	private String booNo;										//编号
	private String booName;										//书名
	
	public String getbooNo() {
		return booNo;
	}
	public void setbooNo(String booNo) {
		this.booNo = booNo;
	}
	public String getbooName() {
		return booName;
	}
	public void setbooName(String booName) {
		this.booName = booName;
	}
	
	}
