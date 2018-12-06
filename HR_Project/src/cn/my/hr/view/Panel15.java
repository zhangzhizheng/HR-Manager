package cn.my.hr.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
/**
 * 部门管理
 * @author Administrator
 *
 */
public class Panel15 extends JPanel implements  ActionListener{
	//定义各种属性
	//定义面板
	private  JPanel pTop;
	private JPanel pCenter;
	private JPanel pBottom;
	//定义上部所需组件
	private JScrollPane js;
	private JTable table;
	//center
	private JLabel lbDeptId;
	private JLabel lbDept1Name;
	private JLabel lbDept2Name;
	
	private JTextField tfDeptId;
	private JTextField tfDeptName1;
	private JTextField tfDeptName2;
	//bottom
	private JButton btnNextId;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnClear;
	
	public Panel15() {
		setLayout(new BorderLayout());
		initTop();
		initCenter();
		initBottom();
		
	}
	public void initTop() {
		pTop=new JPanel();
		String []colTitle=new String[] {"部门编号","一级部门"
				,"二级部门"};
		String [][]colvalue=new String[10][3];
		table =new JTable(colvalue,colTitle);
		colvalue[0][0]="1";
		colvalue[0][1]="办公室";
		colvalue[0][2]="综合科";
		colvalue[1][0]="2";
		colvalue[1][1]="人事处";
		colvalue[1][2]="人事科";
		//设置表格默认大小
		table.setPreferredScrollableViewportSize(new  Dimension(430,300));
		table.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			 public  void mouseClicked(MouseEvent e){
				//閼惧嘲褰囬柅澶夎厬鐞涳拷
				int row =table.getSelectedRow();
				tfDeptId.setText(colvalue[row][0]);
				tfDeptName1.setText(colvalue[row][1]);
				tfDeptName2.setText(colvalue[row][2]);
				
			}
		});
		js=new JScrollPane(table);
		js.setPreferredSize(new Dimension(430,300));
		pTop.add(js);
		add(pTop,BorderLayout.NORTH);
	}
    public void initCenter() {
		pCenter=new JPanel();
		lbDeptId=new JLabel("编号");
		tfDeptId=new JTextField(15);
		//一级部门项
		lbDept1Name=new JLabel("一级部门");
		tfDeptName1=new JTextField(15);
		//二级部门项
		lbDept2Name=new JLabel("二级部门");
		tfDeptName2=new JTextField(15);
		//添加到面板
		pCenter.add(lbDeptId);
		pCenter.add(tfDeptId);
		pCenter.add(lbDept1Name);
		pCenter.add(tfDeptName1);
		pCenter.add(lbDept2Name);
		pCenter.add(tfDeptName2);
		add(pCenter,BorderLayout.CENTER);
	}
    public void initBottom() {
	pBottom=new JPanel();
	btnNextId=new JButton("获取新编号");
	pBottom.add(btnNextId);
	btnAdd=new JButton("增加");
	pBottom.add(btnAdd);
	btnUpdate=new JButton("修改");
	pBottom.add(btnUpdate);
	btnDelete=new JButton("删除");
	pBottom.add(btnDelete);
	btnClear=new JButton("清空");
	pBottom.add(btnClear);
	add(pBottom,BorderLayout.SOUTH);
	
}
	@Override
	public void actionPerformed(ActionEvent arg1) {
		// TODO Auto-generated method stub
		if(arg1.getSource()==btnNextId) {
			tfDeptId.setText("2");
			tfDeptName1.setText(null);
			tfDeptName2.setText(null);
		}
		if(arg1.getSource()==btnAdd) {
			//增加
		}
		if(arg1.getSource()==btnUpdate) {
			//修改
		}
		if(arg1.getSource()==btnDelete) {
			//删除
		}
		if(arg1.getSource()==btnClear) {
			//清空
		}
	}
}
