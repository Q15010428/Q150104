import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Vector;
import java.text.DecimalFormat;

import javax.swing.*;

@SuppressWarnings("serial")
public class Main extends JFrame implements ActionListener {
	Vector<Buyer> buyList=new Vector<Buyer>();
	Vector<Book> BookList=new Vector<Book>();
	Vector<Order> OrderList=new Vector<Order>();
	JMenu Menu0=new JMenu("文件");
	JMenuItem menuOpen=new JMenuItem("打开数据文件");
	JMenuItem menuSave=new JMenuItem("保存数据文件");
	JMenuItem menuChangePwd=new JMenuItem("更改管理密码");
	JMenuItem menuExit=new JMenuItem("退出系统");
	JMenu Menu1=new JMenu("信息维护");
	JMenuItem buyMenu=new JMenuItem("买家信息维护");
	JMenuItem BookMenu=new JMenuItem("书籍信息维护");
	JMenuItem OrderMenu=new JMenuItem("订单信息维护");
	JMenu Menu2=new JMenu("数据查询");
	JMenuItem buyQueryMenu=new JMenuItem("买家信息查询");
	JMenuItem OrderQueryMenu=new JMenuItem("订单信息查询");

	public static void main(String[] args) {
		LoginFrame f=new LoginFrame();
		f.setVisible(true);
	}

	Main(){
		super();
		JMenuBar menuBar=new JMenuBar();
		this.setJMenuBar(menuBar);
		buyMenu.addActionListener(this);
		buyMenu.setEnabled(false);
		Menu1.add(buyMenu);
		BookMenu.addActionListener(this);
		BookMenu.setEnabled(false);
		Menu1.add(BookMenu);
		OrderMenu.addActionListener(this);
		OrderMenu.setEnabled(false);
		Menu1.add(OrderMenu);
		menuOpen.addActionListener(this);
		Menu0.add(menuOpen);
		menuSave.addActionListener(this);
		menuSave.setEnabled(false);
		Menu0.add(menuSave);
		Menu0.addSeparator();
		Menu0.add(menuChangePwd);
		menuChangePwd.addActionListener(this);
		Menu0.addSeparator();
		menuExit.addActionListener(this);
		Menu0.add(menuExit);
		buyQueryMenu.addActionListener(this);
		buyQueryMenu.setEnabled(false);
		Menu2.add(buyQueryMenu);
		OrderQueryMenu.addActionListener(this);
		OrderQueryMenu.setEnabled(false);
		Menu2.add(OrderQueryMenu);
		menuBar.add(Menu0);
		menuBar.add(Menu1);
		menuBar.add(Menu2);

		this.setTitle("买家订单管理系统");
		this.setResizable(false);
		this.setSize(700,320);
		this.setLocationRelativeTo(this.getOwner());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	void readbuyFile(){
		try {
			FileInputStream fis = new FileInputStream("Buyer.txt");
			InputStreamReader dis=new InputStreamReader(fis);
			BufferedReader reader=new BufferedReader(dis);
			String s;
			while((s=reader.readLine())!=null)
			{
				Buyer buy=new Buyer();
				String[] temp=s.split(" ");
				buy.setbuyNo(temp[0]);
				buy.setbuySex(temp[1]);
				buy.setAddress(temp[2]);
				buy.setbuyLevel(temp[3]);
				buyList.add(buy);
			}
			reader.close();
			dis.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void writebuyFile(){
		try {
			FileOutputStream fos = new FileOutputStream("Buyer.txt");
			OutputStreamWriter dos=new OutputStreamWriter(fos);
			BufferedWriter writer=new BufferedWriter(dos);
			for(int i=0;i<buyList.size();i++){
				Buyer buy=(Buyer)buyList.get(i);
				writer.write(buy.getbuyNo()+" "+buy.getbuySex()+" "+buy.getAddress()+" "+buy.getbuyLevel()+"\n");
			}
			writer.close();
			dos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void readBookFile(){
		try {
			FileInputStream fis = new FileInputStream("Book.txt");
			InputStreamReader dis=new InputStreamReader(fis);
			BufferedReader reader=new BufferedReader(dis);
			String s;
			while((s=reader.readLine())!=null)
			{
				Book Book=new Book();
				String[] temp=s.split(" ");
				Book.setBookNo(temp[0]);
				Book.setBookName(temp[1]);
				Book.setBookComment(Double.parseDouble(temp[2]));
				Book.setBookPrice(temp[3]);
				BookList.add(Book);
			}
			reader.close();
			dis.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void writeBookFile(){
		try {
			FileOutputStream fos = new FileOutputStream("Book.txt");
			OutputStreamWriter dos=new OutputStreamWriter(fos);
			BufferedWriter writer=new BufferedWriter(dos);
			for(int i=0;i<BookList.size();i++){
				Book Book=(Book)BookList.get(i);
				writer.write(Book.getBookNo()+" "+Book.getBookName()+" "+Book.getBookComment()+" "+Book.getBookPrice()+"\n");
			}
			writer.close();
			dos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void readOrderFile(){
		try{
			FileInputStream fis;
			fis = new FileInputStream("Order.txt");
			InputStreamReader dis=new InputStreamReader(fis);
			BufferedReader reader=new BufferedReader(dis);
			String s;
			while((s=reader.readLine())!=null)
			{
				Order Order=new Order();
				String[] temp=s.split(" ");
				Order.setOrderNo(temp[0]);
				Order.setBuyNo(temp[1]);
				Order.setOrder(Double.parseDouble(temp[2]));
				OrderList.add(Order);
			}
			reader.close();
			dis.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void writeOrderFile(){
		try {
			FileOutputStream fos = new FileOutputStream("Order.txt");
			OutputStreamWriter dos=new OutputStreamWriter(fos);
			BufferedWriter writer=new BufferedWriter(dos);
			for(int i=0;i<OrderList.size();i++){
				Order Order=(Order)OrderList.get(i);
				writer.write(Order.getOrderNo()+" "+Order.getBuyNo()+" "+String.format("%.1f", Order.getTotal())+"\n");
			}
			writer.close();
			dos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void showbuyPanel(){
		buyPanel buyPanel=new buyPanel();
		buyPanel.buyList=this.buyList;
		this.add(buyPanel,BorderLayout.CENTER);
		buyPanel.showBuyer(0);
		this.setVisible(true);
	}

	void showBookPanel(){
		BookPanel BookPanel=new BookPanel();
		BookPanel.BookList=this.BookList;
		this.add(BookPanel,BorderLayout.CENTER);
		BookPanel.showBook(0);
		this.setVisible(true);
	}

	void showOrderPanel(){
		OrderPanel OrderPanel=new OrderPanel();
		OrderPanel.OrderList=this.OrderList;
		this.add(OrderPanel,BorderLayout.CENTER);
		OrderPanel.showOrder(0);
		this.setVisible(true);
	}

	void showbuyQueryPanel(){
		buyQueryPanel buyQPanel=new buyQueryPanel();
		buyQPanel.buyList=this.buyList;
		buyQPanel.OrderList=this.OrderList;
		buyQPanel.BookList=this.BookList;
		this.add(buyQPanel, BorderLayout.CENTER);
		this.setVisible(true);
	}

	void showOrderQueryPanel(){
		OrderQueryPanel OrderQPanel=new OrderQueryPanel();
		OrderQPanel.buyList=this.buyList;
		OrderQPanel.OrderList=this.OrderList;
		OrderQPanel.BookList=this.BookList;
		this.add(OrderQPanel, BorderLayout.CENTER);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==buyMenu){
			this.getContentPane().removeAll();
			this.showbuyPanel();
		}
		if(e.getSource()==BookMenu){
			this.getContentPane().removeAll();
			this.showBookPanel();
		}
		if(e.getSource()==OrderMenu){
			this.getContentPane().removeAll();
			this.showOrderPanel();
		}
		if(e.getSource()==buyQueryMenu){
			this.getContentPane().removeAll();
			this.showbuyQueryPanel();
		}
		if(e.getSource()==OrderQueryMenu){
			this.getContentPane().removeAll();
			this.showOrderQueryPanel();
		}
		if(e.getSource()==menuOpen){
			this.buyList.removeAllElements();
			this.OrderList.removeAllElements();
			this.BookList.removeAllElements();
			this.readbuyFile();
			buyMenu.setEnabled(true);
			this.readBookFile();
			BookMenu.setEnabled(true);
			this.readOrderFile();
			OrderMenu.setEnabled(true);
			menuSave.setEnabled(true);
			buyQueryMenu.setEnabled(true);
			OrderQueryMenu.setEnabled(true);
			JOptionPane.showMessageDialog(null, "您已经成功打开数据：\n买家信息"+buyList.size()+"条\n书籍信息"+BookList.size()+"条\n订单信息"+OrderList.size()+"条", "打开", JOptionPane.INFORMATION_MESSAGE);
		}
		if(e.getSource()==menuSave){
			this.writebuyFile();
			this.writeOrderFile();
			menuSave.setEnabled(true);
			OrderMenu.setEnabled(true);
			JOptionPane.showMessageDialog(null, "您已经成功保存数据：\n买家信息"+buyList.size()+"条\n书籍信息"+BookList.size()+"条\n订单信息"+OrderList.size()+"条", "保存", JOptionPane.INFORMATION_MESSAGE);
		}
		if(e.getSource()==menuChangePwd){
			String s=JOptionPane.showInputDialog(null, "请输入新密码", "管理员密码修改", JOptionPane.PLAIN_MESSAGE);
			if(s==null)return;
			s=s.trim();
			if(s.length()==0){
				JOptionPane.showMessageDialog(null, "密码不能为空！", "买家信息管理系统", JOptionPane.ERROR_MESSAGE);
				return;
			}
			String clearText = 	"0123456789abcdefghijklmnopqrbuyvwxyzABCDEFGHIJKLMNOPQRbuyVWXYZ";
			String cipherText=	"UADKIy3FxgVkl5iZzWuGd1HNhOCtvjJ2pEn6Yw7PqrcQReB8Mfm0STsLX9a4ob";
			String resultText=		"";
			for(int i=0;i<s.length();i++){
				char c=s.charAt(i);
				if(clearText.indexOf(""+c)==-1){
					JOptionPane.showMessageDialog(null, "密码中包含非法字符", "买家信息管理系统", JOptionPane.ERROR_MESSAGE);
					return;
				}else{
					resultText+=""+cipherText.charAt(clearText.indexOf(""+c));
				}
			}
			try {
				FileOutputStream fos = new FileOutputStream("Password.txt");
				OutputStreamWriter dos=new OutputStreamWriter(fos);
				BufferedWriter writer=new BufferedWriter(dos);
				writer.write(resultText);
				writer.close();dos.close();fos.close();
				JOptionPane.showMessageDialog(null, "密码修改成功！", "买家信息管理系统", JOptionPane.INFORMATION_MESSAGE);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}			
		if(e.getSource()==menuExit){
			System.exit(0);
		}
	}
}

@SuppressWarnings("serial")
class OrderPanel extends JPanel implements ActionListener{
	private JTextField OrderNo=new JTextField();						//会员号
	private JTextField BuyNo=new JTextField();							//书籍编号
	private JTextField Order=new JTextField();							//订单
	Vector<Order> OrderList=new Vector<Order>();
	int count=0,current=0,inserting=0;

	private String[] btnStr={"第一个","上一个","下一个","最后一个","添加","修改","删除"};
	private JButton[] btn= new JButton[btnStr.length];

	OrderPanel(){
		this.setLayout(null);
		//显示会员号
		JLabel lb1=new JLabel("订单号：");
		lb1.setBounds(30, 10, 100, 20);
		this.add(lb1);
		OrderNo.setBounds(100,10, 100, 20);
		this.add(OrderNo);
		//显示书籍编号
		JLabel lb2=new JLabel("会员号：");
		lb2.setBounds(30, 60, 100, 20);
		this.add(lb2);
		BuyNo.setBounds(100,60, 100, 20);
		this.add(BuyNo);
		//显示订单
		JLabel lb3=new JLabel("订单金额：");
		lb3.setBounds(30, 110, 100, 20);
		this.add(lb3);
		Order.setBounds(100,110, 100, 20);
		this.add(Order);

		for(int i=0;i<btn.length;i++){
			btn[i]=new JButton(btnStr[i]);
			btn[i].setBounds(30+i*90, 210, 90, 30);
			btn[i].addActionListener(this);
			this.add(btn[i]);
		}

	}

	void showOrder(int offset){
		Order Order=(Order)OrderList.get(offset);
		this.OrderNo.setText(Order.getOrderNo());
		this.BuyNo.setText(""+Order.getBuyNo());
		this.Order.setText(""+Order.getTotal());
	}

	public void actionPerformed(ActionEvent e) {
		count=this.OrderList.size();
		if(e.getSource()==this.btn[0]){
			this.showOrder(0);
			current=0;
		}
		if(e.getSource()==this.btn[1]&&current>0){
			this.showOrder(current-1);
			current=current-1;
		}
		if(e.getSource()==this.btn[2]&&current<count-1){
			this.showOrder(current+1);
			current=current+1;
		}
		if(e.getSource()==this.btn[3]){
			this.showOrder(count-1);
			current=count-1;
		}
		if(e.getSource()==this.btn[4]){
			if(this.inserting==0){
				this.OrderNo.setText("");
				this.BuyNo.setText("");
				this.Order.setText("");
				btn[4].setText("保存");
				btn[5].setText("取消");
				this.inserting=1;
			}
			else{
				Order Order=new Order();
				Order.setOrderNo(this.OrderNo.getText().trim());
				Order.setBuyNo(this.BuyNo.getText().trim());
				Order.setOrder(Double.parseDouble(this.Order.getText().trim()));
				OrderList.add(Order);
				count++;
				current=count-1;
				btn[4].setText("添加");
				this.inserting=0;
			}
			for(int i=0;i<btn.length;i++){
				if(i==4||i==5) continue;
				btn[i].setEnabled(!btn[i].isEnabled());
			}			
		}
		if(e.getSource()==this.btn[5]){
			if(this.inserting==0){
				Order Order=(Order)OrderList.get(current);
				Order.setOrderNo(this.OrderNo.getText().trim());
				Order.setBuyNo(this.BuyNo.getText().trim());
				Order.setOrder(Double.parseDouble(this.Order.getText().trim()));
			}else{
				btn[4].setText("添加");
				btn[5].setText("修改");
				for(int i=0;i<btn.length;i++){
					if(i==4||i==5) continue;
					btn[i].setEnabled(!btn[i].isEnabled());
				}	
				this.inserting=0;
				this.showOrder(current);
			}
		}
		if(e.getSource()==this.btn[6]){
			if(count==0)
				return;
			OrderList.remove(current);
			count--;
			if(count==0){
				this.OrderNo.setText("");
				this.BuyNo.setText("");
				this.Order.setText("");
			}else{
				if(current>count-1){
					this.showOrder(current-1);
					current=current-1;
				}
				else
					this.showOrder(current);
			}
		}
		this.repaint();
	}
}

@SuppressWarnings("serial")
class buyPanel extends JPanel implements ActionListener {
	private JTextField buyNo=new JTextField();											//会员号
	private JTextField buySex=new JTextField();										//姓名
	private JTextField Address=new JTextField();											//收货地址
	private JTextField buyLevel=new JTextField();								//等级
	Vector<Buyer> buyList=new Vector<Buyer>();
	int count=0,current=0,inserting=0;

	private String[] btnStr={"第一个","上一个","下一个","最后一个","添加","修改","删除"};
	private JButton[] btn= new JButton[btnStr.length];

	buyPanel(){
		this.setLayout(null);
		//显示会员号
		JLabel lb1=new JLabel("会员号：");
		lb1.setBounds(30, 10, 100, 20);
		this.add(lb1);
		buyNo.setBounds(100,10, 100, 20);
		this.add(buyNo);
		//显示性别
		JLabel lb2=new JLabel("性别：");
		lb2.setBounds(30, 60, 100, 20);
		this.add(lb2);
		buySex.setBounds(100,60, 100, 20);
		this.add(buySex);
		//显示收货地址
		JLabel lb3=new JLabel("收货地址：");
		lb3.setBounds(30, 110, 100, 20);
		this.add(lb3);
		Address.setBounds(100,110, 100, 20);
		this.add(Address);
		//显示等级
		JLabel lb4=new JLabel("等级：");
		lb4.setBounds(30, 160, 100, 20);
		this.add(lb4);
		buyLevel.setBounds(100,160, 100, 20);
		this.add(buyLevel);

		for(int i=0;i<btn.length;i++){
			btn[i]=new JButton(btnStr[i]);
			btn[i].setBounds(30+i*90, 210, 90, 30);
			btn[i].addActionListener(this);
			this.add(btn[i]);
		}
	}

	void showBuyer(int offset){
		Buyer buy=(Buyer) buyList.get(offset);
		this.buyNo.setText(buy.getbuyNo());
		this.buySex.setText(buy.getbuySex());
		this.Address.setText(buy.getAddress());
		this.buyLevel.setText(buy.getbuyLevel());
	}

	public void actionPerformed(ActionEvent e) {
		count=this.buyList.size();
		if(e.getSource()==this.btn[0]){
			this.showBuyer(0);
			current=0;
		}
		if(e.getSource()==this.btn[1]&&current>0){
			this.showBuyer(current-1);
			current=current-1;
		}
		if(e.getSource()==this.btn[2]&&current<count-1){
			this.showBuyer(current+1);
			current=current+1;
		}
		if(e.getSource()==this.btn[3]){
			this.showBuyer(count-1);
			current=count-1;
		}
		if(e.getSource()==this.btn[4]){
			if(this.inserting==0){
				this.buyNo.setText("");
				this.buySex.setText("");
				this.Address.setText("");
				this.buyLevel.setText("");
				btn[4].setText("保存");
				btn[5].setText("取消");
				this.inserting=1;
			}else{
				Buyer buy=new Buyer();
				buy.setbuyNo(this.buyNo.getText().trim());
				buy.setbuySex(this.buySex.getText().trim());
				buy.setAddress(this.Address.getText().trim());
				buy.setbuyLevel(this.buyLevel.getText().trim());
				buyList.add(buy);
				count++;
				current=count-1;
				btn[4].setText("添加");
				this.inserting=0;
			}
			for(int i=0;i<btn.length;i++){
				if(i==4||i==5) continue;
				btn[i].setEnabled(!btn[i].isEnabled());
			}	
		}

		if(e.getSource()==this.btn[5]){
			if(this.inserting==0){
				Buyer buy=(Buyer)buyList.get(current);
				buy.setbuyNo(this.buyNo.getText().trim());
				buy.setbuySex(this.buySex.getText().trim());
				buy.setAddress(this.Address.getText().trim());
				buy.setbuyLevel(this.buyLevel.getText().trim());
			}else{
				btn[4].setText("添加");
				btn[5].setText("修改");
				for(int i=0;i<btn.length;i++){
					if(i==4||i==5) continue;
					btn[i].setEnabled(!btn[i].isEnabled());
				}
				this.inserting=0;
				this.showBuyer(current);
			}
		}
		if(e.getSource()==this.btn[6]){
			if(count==0)
				return;
			buyList.remove(current);
			count--;
			if(count==0){
				this.buyNo.setText("");
				this.buySex.setText("");
				this.Address.setText("");
				this.buyLevel.setText("");
			}else{
				if(current>count-1){
					this.showBuyer(current-1);
					current=current-1;
				}
				else
					this.showBuyer(current);
			}
		}
		this.repaint();
	}
}

@SuppressWarnings("serial")
class BookPanel extends JPanel implements ActionListener {
	private JTextField BookNo=new JTextField();											//会员号
	private JTextField BookName=new JTextField();										//书籍名称
	private JTextField BookComment=new JTextField();										//豆瓣书评
	private JTextField BookPrice=new JTextField();												//书籍价格
	Vector<Book> BookList=new Vector<Book>();
	int count=0,current=0,inserting=0;

	private String[] btnStr={"第一个","上一个","下一个","最后一个","添加","修改","删除"};
	private JButton[] btn= new JButton[btnStr.length];

	BookPanel(){
		this.setLayout(null);
		//显示书籍编号
		JLabel lb1=new JLabel("书籍编号：");
		lb1.setBounds(30, 10, 100, 20);
		this.add(lb1);
		BookNo.setBounds(100,10, 200, 20);
		this.add(BookNo);
		//显示书籍名
		JLabel lb2=new JLabel("书籍名：");
		lb2.setBounds(30, 60, 100, 20);
		this.add(lb2);
		BookName.setBounds(100,60, 200, 20);
		this.add(BookName);
		//显示豆瓣书评
		JLabel lb3=new JLabel("豆瓣书评：");
		lb3.setBounds(30, 110, 100, 20);
		this.add(lb3);
		BookComment.setBounds(100,110, 200, 20);
		this.add(BookComment);
		//显示书籍价格
		JLabel lb4=new JLabel("书籍价格：");
		lb4.setBounds(30, 160, 100, 20);
		this.add(lb4);
		BookPrice.setBounds(100,160, 200, 20);
		this.add(BookPrice);

		for(int i=0;i<btn.length;i++){
			btn[i]=new JButton(btnStr[i]);
			btn[i].setBounds(30+i*90, 210, 90, 30);
			btn[i].addActionListener(this);
			this.add(btn[i]);
		}
	}

	void showBook(int offset){
		Book Book=(Book) BookList.get(offset);
		this.BookNo.setText(Book.getBookNo());
		this.BookName.setText(Book.getBookName());
		this.BookComment.setText(""+Book.getBookComment());
		this.BookPrice.setText(Book.getBookPrice());
	}

	public void actionPerformed(ActionEvent e) {
		count=this.BookList.size();
		if(e.getSource()==this.btn[0]){
			this.showBook(0);
			current=0;
		}
		if(e.getSource()==this.btn[1]&&current>0){
			this.showBook(current-1);
			current=current-1;
		}
		if(e.getSource()==this.btn[2]&&current<count-1){
			this.showBook(current+1);
			current=current+1;
		}
		if(e.getSource()==this.btn[3]){
			this.showBook(count-1);
			current=count-1;
		}
		if(e.getSource()==this.btn[4]){
			if(this.inserting==0){
				this.BookNo.setText("");
				this.BookName.setText("");
				this.BookComment.setText("");
				this.BookPrice.setText("");
				btn[4].setText("保存");
				btn[5].setText("取消");
				this.inserting=1;
			}else{
				Book Book=new Book();
				Book.setBookNo(this.BookNo.getText().trim());
				Book.setBookName(this.BookName.getText().trim());
				Book.setBookComment(Double.parseDouble(this.BookComment.getText().trim()));
				Book.setBookPrice(this.BookPrice.getText().trim());
				BookList.add(Book);
				count++;
				current=count-1;
				btn[4].setText("添加");
				this.inserting=0;
			}
			for(int i=0;i<btn.length;i++){
				if(i==4||i==5) continue;
				btn[i].setEnabled(!btn[i].isEnabled());
			}	
		}

		if(e.getSource()==this.btn[5]){
			if(this.inserting==0){
				Book Book=(Book)BookList.get(current);
				Book.setBookNo(this.BookNo.getText().trim());
				Book.setBookName(this.BookName.getText().trim());
				Book.setBookComment(Double.parseDouble(this.BookComment.getText().trim()));
				Book.setBookPrice(this.BookPrice.getText().trim());
			}else{
				btn[4].setText("添加");
				btn[5].setText("修改");
				for(int i=0;i<btn.length;i++){
					if(i==4||i==5) continue;
					btn[i].setEnabled(!btn[i].isEnabled());
				}
				this.inserting=0;
				this.showBook(current);
			}
		}
		if(e.getSource()==this.btn[6]){
			if(count==0)
				return;
			BookList.remove(current);
			count--;
			if(count==0){
				this.BookNo.setText("");
				this.BookName.setText("");
				this.BookComment.setText("");
				this.BookPrice.setText("");
			}else{
				if(current>count-1){
					this.showBook(current-1);
					current=current-1;
				}
				else
					this.showBook(current);
			}
		}
		this.repaint();
	}
}

@SuppressWarnings("serial")
class buyQueryPanel extends JPanel implements ActionListener{
	JComboBox<String> searchBy=new JComboBox<String>();
	JTextField keyword=new JTextField(10);
	JTextArea result=new JTextArea();
	Vector<Buyer> buyList=new Vector<Buyer>();
	Vector<Order> OrderList=new Vector<Order>();
	Vector<Book> BookList=new Vector<Book>();
	buyQueryPanel(){
		this.setLayout(new BorderLayout());
		JPanel subPanel=new JPanel();
		subPanel.setLayout(new FlowLayout());
		searchBy.addItem("按会员号查询");
		searchBy.addItem("按订单编号查询");		
		subPanel.add(searchBy);
		subPanel.add(keyword);
		JButton btn=new JButton("查询");
		btn.addActionListener(this);
		subPanel.add(btn);
		this.add(subPanel, BorderLayout.NORTH);
		JScrollPane scrollPane=new JScrollPane(result);
		this.add(scrollPane, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent arg0) {
		String str="会员号\t性别\t地址\t订单编号：总金额\n";
		for(int i=0;i<buyList.size();i++){
			Buyer buy=(Buyer)this.buyList.get(i);
			Book book=(Book)this.BookList.get(i);
			Order Order=(Order)OrderList.get(i);
			if(this.searchBy.getSelectedIndex()==0){
				if(buy.getbuyNo().startsWith(this.keyword.getText().trim())||this.keyword.getText().trim().equals("")){
					str=str+buy.getbuyNo()+"\t"+buy.getbuySex()+"\t"+buy.getAddress()+"\t"+Order.getOrderNo()+"："+Order.getTotal()+"  ";
				}else
					continue;
			}if(this.searchBy.getSelectedIndex()==1){
				if(Order.getOrderNo().startsWith(this.keyword.getText().trim())||this.keyword.getText().trim().equals("")){
					str=str+buy.getbuyNo()+"\t"+buy.getbuySex()+"\t"+buy.getAddress()+"\t"+Order.getOrderNo()+"："+Order.getTotal()+"  ";
				}else
					continue;
			}
		
			str+="\n";
		}
		this.result.setText(str);
	}

	String getBookName(String BookNo){
		for(int i=0;i<BookList.size();i++){
			Book Book=(Book)this.BookList.get(i);
			if(BookNo.equals(Book.getBookNo())){
				return Book.getBookName();
			}
		}
		return null;
	}
}

@SuppressWarnings("serial")
class OrderQueryPanel extends JPanel implements ActionListener{
	JTextField keyword=new JTextField(10);
	JTextArea result=new JTextArea();
	Vector<Buyer> buyList=new Vector<Buyer>();
	Vector<Order> OrderList=new Vector<Order>();
	Vector<Book> BookList=new Vector<Book>();
	OrderQueryPanel(){
		this.setLayout(new BorderLayout());
		JPanel subPanel=new JPanel();
		subPanel.setLayout(new FlowLayout());
		subPanel.add(new JLabel("日期："));
		subPanel.add(keyword);
		JButton btn=new JButton("查询");
		btn.addActionListener(this);
		subPanel.add(btn);
		this.add(subPanel, BorderLayout.NORTH);
		JScrollPane scrollPane=new JScrollPane(result);
		this.add(scrollPane, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent arg0) {
		if(keyword.getText().length()!=7){
			JOptionPane.showMessageDialog(null, "请输入正确的日期", "买家订单管理系统", JOptionPane.ERROR_MESSAGE);
			return;
		}
		double max,min,sum,average;
		int count;
		String str="日期\t最高金额\t最低金额\t平均金额\n";
		Order Order=null;

		max=0;min=1000;sum=0;average=0;count=0;
		for(int j=0;j<OrderList.size();j++){
			Order=OrderList.get(j);
			if(Order.getOrderNo().startsWith(this.keyword.getText().trim())){
				if(Order.getTotal()>max)
					max=Order.getTotal();
				if(Order.getTotal()<min)
					min=Order.getTotal();
				sum+=Order.getTotal();
				count++;
			}
		}
		if(count!=0){
			DecimalFormat df=new DecimalFormat("#.0");
			average=sum/count;
			String DateStr = Order.getOrderNo().substring(1, 3)+"年"+Order.getOrderNo().substring(3, 5)+"月"+Order.getOrderNo().substring(5, 7)+"日";
			str+=DateStr+"\t"+df.format(max)+"\t"+df.format(min)+"\t"+df.format(average)+"\n";
		}

		this.result.setText(str);
	}
}

class Buyer {
	private String buyNo;										//会员号
	private String buySex;										//性别
	private String Address;										//收货地址
	private String buyLevel;									//等级
	Order Order;												//订单

	public String getbuyNo() {
		return buyNo;

	}
	public void setbuyNo(String buyNo) {
		this.buyNo = buyNo;
	}
	public String getbuySex() {
		return buySex;
	}
	public void setbuySex(String buySex) {
		this.buySex = buySex;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String Address) {
		this.Address = Address;
	}
	public String getbuyLevel() {
		return buyLevel;
	}
	public void setbuyLevel(String buyLevel) {
		this.buyLevel = buyLevel;
	}
}

class Book{
	private String BookNo;										//书籍编号
	private String BookName;									//书籍名称
	private double BookComment;									//豆瓣评分
	private String BookPrice;									//书籍价格

	public String getBookNo() {
		return BookNo;
	}
	public void setBookNo(String BookNo) {
		this.BookNo = BookNo;
	}
	public String getBookName() {
		return BookName;
	}
	public void setBookName(String BookName) {
		this.BookName = BookName;
	}
	public double getBookComment() {
		return BookComment;
	}
	public void setBookComment(double BookComment) {
		this.BookComment = BookComment;
	}
	public String getBookPrice() {
		return BookPrice;
	}
	public void setBookPrice(String BookPrice) {
		this.BookPrice = BookPrice;
	}
}

class Order{
	private String OrderNo;											//订单编号
	private String BuyNo;											//会员号
	private double Total;											//订单金额

	public String getOrderNo() {
		return OrderNo;
	}

	public void setOrderNo(String OrderNo) {
		this.OrderNo = OrderNo;
	}
	public String getBuyNo() {
		return BuyNo;
	}
	public void setBuyNo(String BuyNo) {
		this.BuyNo = BuyNo;
	}
	public double getTotal() {
		return Total;
	}
	public void setOrder(double Total) {
		this.Total = Total;
	}
}

@SuppressWarnings("serial")
class LoginFrame extends JFrame implements ActionListener{
	JTextField username=new JTextField(10);
	JPasswordField pwd=new JPasswordField(10);
	LoginFrame(){
		this.setTitle("系统登录");
		this.setResizable(false);
		this.setSize(250,130);
		this.setLocationRelativeTo(this.getOwner());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lb1=new JLabel("用户名：");
		JLabel lb2=new JLabel("密　码：");
		JButton btn=new JButton("登录");
		btn.addActionListener(this);
		Container c=this.getContentPane();
		c.setLayout(null);
		lb1.setBounds(30, 10, 100, 20);
		c.add(lb1);
		username.setBounds(120, 10, 100, 20);
		username.addActionListener(this);
		c.add(username);
		lb2.setBounds(30, 40, 100, 20);
		c.add(lb2);
		pwd.setBounds(120, 40, 100, 20);
		pwd.addActionListener(this);
		c.add(pwd);
		btn.setBounds(80, 70, 90, 20);
		c.add(btn);
		this.setVisible(true);
		this.username.requestFocus();
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.username){
			this.pwd.requestFocus();
			return;
		}
		try {
			FileInputStream fis;
			fis = new FileInputStream("Password.txt");
			InputStreamReader dis=new InputStreamReader(fis);
			BufferedReader reader=new BufferedReader(dis);
			String s;
			String clearText = 	"0123456789abcdefghijklmnopqrbuyvwxyzABCDEFGHIJKLMNOPQRbuyVWXYZ";
			String cipherText=	"UADKIy3FxgVkl5iZzWuGd1HNhOCtvjJ2pEn6Yw7PqrcQReB8Mfm0STsLX9a4ob";
			if((s=reader.readLine())!=null){
				if(username.getText().trim().equals("admin")){
					boolean isCorrect=true;
					char[] ch1=pwd.getPassword();
					char[] ch2=s.toCharArray();
					if(ch1.length==ch2.length){
						for(int i=0;i<ch1.length;i++){
							if(clearText.indexOf(ch1[i])!=cipherText.indexOf(ch2[i])){
								isCorrect=false;
								break;
							}
						}
					}else{
						isCorrect=false;
					}
					if(isCorrect){
						this.setVisible(false);
						this.dispose();
						Main f=new Main();
						f.setVisible(true);
					}else{
						JOptionPane.showMessageDialog(null, "您填写的密码不正确", "用户登录", JOptionPane.WARNING_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(null, "您填写的用户名不正确", "用户登录", JOptionPane.WARNING_MESSAGE);
					this.username.requestFocus();
				}
			}
			reader.close();
			dis.close();
			fis.close();
		}catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
