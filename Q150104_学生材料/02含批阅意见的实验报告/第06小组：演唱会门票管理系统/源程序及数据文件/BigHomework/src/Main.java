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
	Vector conList=new Vector();
	
	MainFrame(){
		super("演唱会门票管理系统");
		
		this.setTitle("演唱会门票管理系统");
		this.setSize(700,320);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		this.readConFile();
		this.showConPanel();
		this.setVisible(true);

		this.setResizable(false);
		this.setSize(700,320);
		this.setLocationRelativeTo(this.getOwner());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	void readConFile(){
		try {
			FileInputStream fis;
			fis = new FileInputStream("Concert.txt");
			InputStreamReader dis=new InputStreamReader(fis);
			BufferedReader reader=new BufferedReader(dis);
			String s;
			while((s=reader.readLine())!=null)
			{
				Concert con=new Concert();
				String[] temp=s.split(" ");
				con.setstaNo(temp[0]);
				con.setperNo(temp[1]);
				con.setconNo(temp[2]);
				con.setconTime(temp[3]);
				con.setconName(temp[4]);
				con.setconCost(temp[5]);
				conList.add(con);
			}
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
	
	void showConPanel(){
		ConPanel conPanel=new ConPanel();
		conPanel.conList=this.conList;
		this.add(conPanel,BorderLayout.CENTER);
		conPanel.showConcert(0);
		this.setVisible(true);
	}
}

class ConPanel extends JPanel {
	private JTextField staNo=new JTextField();											//学号
	private JTextField perNo=new JTextField();										//姓名
	private JTextField conNo=new JTextField();											//性别
	private JTextField conTime=new JTextField();									//出生日期
	private JTextField conName=new JTextField();
    private JTextField conCost=new JTextField();	
	Vector conList=new Vector();
	private String[] btnStr={"第一个","上一个","下一个","最后一个","添加","修改","删除"};
	private JButton[] btn= new JButton[btnStr.length];
	
	ConPanel(){
		this.setLayout(null);
		//显示场馆
		JLabel lb1=new JLabel("场馆：");
		lb1.setBounds(30, 10, 100, 20);
		this.add(lb1);
		staNo.setBounds(100,10, 100, 20);
		this.add(staNo);
		//显示艺人
		JLabel lb2=new JLabel("艺人：");
		lb2.setBounds(30, 40, 100, 20);
		this.add(lb2);
		perNo.setBounds(100,40, 100, 20);
		this.add(perNo);
		//显示编号
		JLabel lb3=new JLabel("编号：");
		lb3.setBounds(30, 70, 100, 20);
		this.add(lb3);
		conNo.setBounds(100,70, 100, 20);
		this.add(conNo);
		//显示时间
		JLabel lb4=new JLabel("时间：");
		lb4.setBounds(30, 100, 100, 20);
		this.add(lb4);
		conTime.setBounds(100,100, 100, 20);
		this.add(conTime);
		//显示名称
		JLabel lb5=new JLabel("名称：");
		lb5.setBounds(30, 130, 100, 20);
		this.add(lb5);
		conName.setBounds(100,130, 100, 20);
		this.add(conName);
		//显示票价
		JLabel lb6=new JLabel("票价：");
		lb6.setBounds(30, 160, 100, 20);
		this.add(lb6);
		conCost.setBounds(100,160, 100, 20);
		this.add(conCost);
		
		//显示控制按钮
		for(int i=0;i<btn.length;i++){
			btn[i]=new JButton(btnStr[i]);
			btn[i].setBounds(30+i*90, 210, 90, 30);
			this.add(btn[i]);
		}
	}
	
	void showConcert(int offset){
		Concert con=(Concert) conList.get(offset);
		this.staNo.setText(con.getstaNo());
		this.perNo.setText(con.getperNo());
		this.conNo.setText(con.getconNo());
		this.conTime.setText(con.getConTime());
	    this.conName.setText(con.getConTime());
	    this.conCost.setText(con.getConCost());
	}
}

class Concert {
	private String staNo; // 场馆编号
	private String perNo; // 艺人编号
	private String conNo;//演唱会编号
	private String conTime; // 时间
	private String conName; // 演唱会名称
	private String conCost; // 演唱会票价
	public String getStaNo() {
		return staNo;
	}
	public String getconNo() {
		// TODO 自动生成的方法存根
		return null;
	}
	public String getperNo() {
		// TODO 自动生成的方法存根
		return null;
	}
	public String getstaNo() {
		// TODO 自动生成的方法存根
		return null;
	}
	public void setconCost(String string) {
		// TODO 自动生成的方法存根
		
	}
	public void setconName(String string) {
		// TODO 自动生成的方法存根
		
	}
	public void setconTime(String string) {
		// TODO 自动生成的方法存根
		
	}
	public void setconNo(String string) {
		// TODO 自动生成的方法存根
		
	}
	public void setperNo(String string) {
		// TODO 自动生成的方法存根
		
	}
	public void setstaNo(String string) {
		// TODO 自动生成的方法存根
		
	}
	public void setStaNo(String staNo) {
		this.staNo = staNo;
	}
	public String getPerNo() {
		return perNo;
	}
	public void setPerNo(String perNo) {
		this.perNo = perNo;
	}
	public String getConNo() {
		return conNo;
	}
	public void setConNo(String conNo) {
		this.conNo = conNo;
	}
	public String getConTime() {
		return conTime;
	}
	public void setConTime(String conTime) {
		this.conTime = conTime;
	}
	public String getConName() {
		return conName;
	}
	public void setConName(String conName) {
		this.conName = conName;
	}
	public String getConCost() {
		return conCost;
	}
	public void setConCost(String conCost) {
		this.conCost = conCost;
	}
	
	}