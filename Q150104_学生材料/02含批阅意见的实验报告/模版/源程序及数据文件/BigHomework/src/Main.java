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
	Vector stuList=new Vector();
	
	MainFrame(){
		super("学生成绩管理系统");
		
		this.setTitle("学生成绩管理系统");
		this.setSize(700,320);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		this.readStuFile();
		this.showStuPanel();
		this.setVisible(true);

		this.setResizable(false);
		this.setSize(700,320);
		this.setLocationRelativeTo(this.getOwner());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	void readStuFile(){
		try {
			FileInputStream fis;
			fis = new FileInputStream("Student.txt");
			InputStreamReader dis=new InputStreamReader(fis);
			BufferedReader reader=new BufferedReader(dis);
			String s;
			while((s=reader.readLine())!=null)
			{
				Student stu=new Student();
				String[] temp=s.split(" ");
				stu.setStuNo(temp[0]);
				stu.setStuName(temp[1]);
				stu.setStuSex(temp[2]);
				stu.setStuBirthday(temp[3]);
				stuList.add(stu);
			}
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
	
	void showStuPanel(){
		StuPanel stuPanel=new StuPanel();
		stuPanel.stuList=this.stuList;
		this.add(stuPanel,BorderLayout.CENTER);
		stuPanel.showStudent(0);
		this.setVisible(true);
	}
}

class StuPanel extends JPanel implements ActionListener {
	private JTextField stuNo=new JTextField();											//学号
	private JTextField stuName=new JTextField();										//姓名
	private JTextField stuSex=new JTextField();											//性别
	private JTextField stuBirthday=new JTextField();									//出生日期
	Vector stuList=new Vector();
	private String[] btnStr={"第一个","上一个","下一个","最后一个","添加","修改","删除"};
	private JButton[] btn= new JButton[btnStr.length];
	int count=0,current=0,inserting=0;
	
	StuPanel(){
		this.setLayout(null);
		//显示学号
		JLabel lb1=new JLabel("学号：");
		lb1.setBounds(30, 10, 100, 20);
		this.add(lb1);
		stuNo.setBounds(100,10, 100, 20);
		this.add(stuNo);
		//显示姓名
		JLabel lb2=new JLabel("姓名：");
		lb2.setBounds(30, 60, 100, 20);
		this.add(lb2);
		stuName.setBounds(100,60, 100, 20);
		this.add(stuName);
		//显示性别
		JLabel lb3=new JLabel("性别：");
		lb3.setBounds(30, 110, 100, 20);
		this.add(lb3);
		stuSex.setBounds(100,110, 100, 20);
		this.add(stuSex);
		//显示出生日期
		JLabel lb4=new JLabel("生日：");
		lb4.setBounds(30, 160, 100, 20);
		this.add(lb4);
		stuBirthday.setBounds(100,160, 100, 20);
		this.add(stuBirthday);
		//显示控制按钮
		for(int i=0;i<btn.length;i++){
			btn[i]=new JButton(btnStr[i]);
			btn[i].setBounds(30+i*90, 210, 90, 30);
			btn[i].addActionListener(this);
			this.add(btn[i]);
		}
	}
	
	void showStudent(int offset){
		Student stu=(Student) stuList.get(offset);
		this.stuNo.setText(stu.getStuNo());
		this.stuName.setText(stu.getStuName());
		this.stuSex.setText(stu.getStuSex());
		this.stuBirthday.setText(stu.getStuBirthday());
	}

	public void actionPerformed(ActionEvent e) {
		count=this.stuList.size();
		if(e.getSource()==this.btn[0]){
			this.showStudent(0);
			current=0;
		}
		if(e.getSource()==this.btn[1] && current>0){
			this.showStudent(current-1);
			current=current-1;
		}
		if(e.getSource()==this.btn[2] && current<count-1){
			this.showStudent(current+1);
			current=current+1;
		}
		if(e.getSource()==this.btn[3]){
			this.showStudent(count-1);
			current=count-1;
		}
		if(e.getSource()==this.btn[4]){
			if(this.inserting==0){
				this.stuNo.setText("");
				this.stuName.setText("");
				this.stuSex.setText("");
				this.stuBirthday.setText("");
				btn[4].setText("保存");
				btn[5].setText("取消");
				this.inserting=1;
			}else{
				Student stu=new Student();
				stu.setStuNo(this.stuNo.getText().trim());
				stu.setStuName(this.stuName.getText().trim());
				stu.setStuSex(this.stuSex.getText().trim());
				stu.setStuBirthday(this.stuBirthday.getText().trim());
				stuList.add(stu);
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
				Student stu=(Student)stuList.get(current);
				stu.setStuNo(this.stuNo.getText().trim());
				stu.setStuName(this.stuName.getText().trim());
				stu.setStuSex(this.stuSex.getText().trim());
				stu.setStuBirthday(this.stuBirthday.getText().trim());
			}else{
				btn[4].setText("添加");
				btn[5].setText("修改");
				for(int i=0;i<btn.length;i++){
					if(i==4||i==5) continue;
					btn[i].setEnabled(!btn[i].isEnabled());
				}
				this.inserting=0;
				this.showStudent(current);
			}
		}
		if(e.getSource()==this.btn[6]){
			if(count==0)
				return;
			stuList.remove(current);
			count--;
			if(count==0){
				this.stuNo.setText("");
				this.stuName.setText("");
				this.stuSex.setText("");
				this.stuBirthday.setText("");
			}else{
				if(current>count-1){
					this.showStudent(current-1);
					current=current-1;
				}
				else
					this.showStudent(current);
			}
		}
		this.repaint();
	}
}

class Student {
	private String stuNo;											//学号
	private String stuName;										//姓名
	private String stuSex;											//性别
	private String stuBirthday;									//出生日期
	
	public String getStuNo() {
		return stuNo;
	}
	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getStuSex() {
		return stuSex;
	}
	public void setStuSex(String stuSex) {
		this.stuSex = stuSex;
	}
	public String getStuBirthday() {
		return stuBirthday;
	}
	public void setStuBirthday(String stuBirthday) {
		this.stuBirthday = stuBirthday;
	}
}

class Course{
	private String courseNo;										//课程编号
	private String courseName;									//课程名称
	private double courseScore;								//课程学分
	private String teacher;											//任课教师
	
	public String getCourseNo() {
		return courseNo;
	}
	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public double getCourseScore() {
		return courseScore;
	}
	public void setCourseScore(double courseScore) {
		this.courseScore = courseScore;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
}

class Score{
	private String stuNo;											//学号
	private String courseNo;										//课程代号
	private double score;											//成绩
	
	public String getStuNo() {
		return stuNo;
	}
	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	public String getCourseNo() {
		return courseNo;
	}
	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
}