import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Vector;
import javax.swing.*;

public class Main{
	public static void main(String[] args) {
		MainFrame f = new MainFrame();
	}
}

class MainFrame extends JFrame {
	Vector comList=new Vector();													//动态数组，创建一个向量comList，用于事先不知道数组大小
	
	MainFrame(){
		super("房屋中介管理系统");
		
		this.setTitle("房屋中介管理系统");											//题目
		this.setSize(700,320);														//边框大小
		this.setResizable(false);													//边框不可调整
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);						//控制开关		
		this.readComFile();															
		this.showComPanel();
		this.setVisible(true);														//可见页面
		this.setLocationRelativeTo(this.getOwner());								//居中
		
	}
	
	void readComFile(){
		try {
			FileInputStream fis;
			fis = new FileInputStream("Community.txt");
			InputStreamReader dis=new InputStreamReader(fis);						//将字节流转化字符流
			BufferedReader reader=new BufferedReader(dis);							//从字符输入流中读取文本，缓冲
			String s;
			while((s=reader.readLine())!=null)										//当读出的行数不是没有时，进行下面循环
			{
				Community com=new Community();
				String[] temp=s.split(" ");
				com.setComID(temp[0]);
				com.setComName(temp[1]);
				com.setComAddress(temp[2]);
				comList.add(com);
			}
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();													//在命令行打印异常信息在程序中出错的位置及原因
		}
		
	}
	
	void showComPanel(){
		ComPanel comPanel=new ComPanel();
		comPanel.comList=this.comList;
		this.add(comPanel,BorderLayout.CENTER);										//布局管理器
		comPanel.showCommunity(0);													//offset==0代表从第一个数据开始偏移
		this.setVisible(true);
	}
}

class ComPanel extends JPanel implements ActionListener{
	private JTextField comID=new JTextField();
	private JTextField comName=new JTextField();
	private JTextField comAddress=new JTextField();
	Vector comList=new Vector();													
	private String[] btnStr={"第一个","上一个","下一个","最后一个","添加","修改","删除"};
	private JButton[] btn= new JButton[btnStr.length];
	int count=0,current=0,inserting=0;
	
	ComPanel(){
		comID.setFont(new Font(null,Font.BOLD,15));
		comName.setFont(new Font(null,Font.BOLD,15));
		comAddress.setFont(new Font(null,Font.BOLD,15));
		this.setLayout(null);
		//显示学号
		JLabel lb1=new JLabel("楼盘编号：");
		lb1.setFont(new Font(null,Font.BOLD,15));
		lb1.setBounds(140, 10, 100, 30);
		this.add(lb1);
		comID.setBounds(250,10, 300, 30);
		this.add(comID);
		//显示姓名
		JLabel lb2=new JLabel("楼盘名称：");
		lb2.setFont(new Font(null,Font.BOLD,15));
		lb2.setBounds(140, 60, 100, 30);
		this.add(lb2);
		comName.setBounds(250,60, 300, 30);
		this.add(comName);
		//显示性别
		JLabel lb3=new JLabel("楼盘地址：");
		lb3.setFont(new Font(null,Font.BOLD,15));
		lb3.setBounds(140, 110, 100, 30);
		this.add(lb3);
		comAddress.setBounds(250,110, 300, 30);
		this.add(comAddress);
		//显示控制按钮
		for(int i=0;i<btn.length;i++){
			btn[i]=new JButton(btnStr[i]);
			btn[i].setBounds(30+i*90, 210, 90, 30);
			this.add(btn[i]);
			btn[i].addActionListener(this);
		}
	}

	
	void showCommunity(int offset){													//起始偏移量
		Community com=(Community) comList.get(offset);								//
		this.comID.setText(com.getComID());
		this.comName.setText(com.getComName());
		this.comAddress.setText(com.getComAddress());		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		count=this.comList.size();
		if(e.getSource()==this.btn[0]){
			this.showCommunity(0);
			current=0;
		}
		if(e.getSource()==this.btn[1] && current>0){
			this.showCommunity(current-1);
			current=current-1;
		}
		if(e.getSource()==this.btn[2] && current<count-1){
			this.showCommunity(current+1);
			current=current+1;
		}
		if(e.getSource()==this.btn[3]){
			this.showCommunity(count-1);
			current=count-1;
		}
		if(e.getSource()==this.btn[4]){
			if(this.inserting==0){
				this.comID.setText("");
				this.comName.setText("");
				this.comAddress.setText("");
				btn[4].setText("保存");
				btn[5].setText("取消");
				this.inserting=1;
			}else{
				Community com=new Community();
				com.setComID(this.comID.getText().trim());
				com.setComName(this.comName.getText().trim());
				com.setComAddress(this.comAddress.getText().trim());
				comList.add(com);
				count++;
				current=count-1;
				btn[4].setText("添加");
				btn[5].setText("修改");
				this.inserting=0;
			}
			for(int i=0;i<btn.length;i++){
				if(i==4||i==5) continue;
				btn[i].setEnabled(!btn[i].isEnabled());
			}
		}

		if(e.getSource()==this.btn[5]){
			if(this.inserting==0){
				Community com=(Community)comList.get(current);
				com.setComID(this.comID.getText().trim());
				com.setComName(this.comName.getText().trim());
				com.setComAddress(this.comAddress.getText().trim());
			}else{
				btn[4].setText("添加");
				btn[5].setText("修改");
				for(int i=0;i<btn.length;i++){
					if(i==4||i==5) continue;
					btn[i].setEnabled(!btn[i].isEnabled());
				}
				this.inserting=0;
				this.showCommunity(current);
			}
		}
		if(e.getSource()==this.btn[6]){
			if(count==0)
				return;
			comList.remove(current);
			count--;
			if(count==0){
				this.comID.setText("");
				this.comName.setText("");
				this.comAddress.setText("");
			}else{
				if(current>count-1){
					this.showCommunity(current-1);
					current=current-1;
				}
				else
					this.showCommunity(current);
			}
		}
		this.repaint();
	}
}



class Community {
	private String comID;
	private String comName;
	private String comAddress;

	public String getComID() {
		return comID;
	}
	public void setComID(String comID) {
		this.comID = comID;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	public String getComAddress() {
		return comAddress;
	}
	public void setComAddress(String comAddress) {
		this.comAddress = comAddress;
	}
}

