package cn.my.hr.transfer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
/**
 * 人员调动面板
 * @author Administrator
 *
 */
public class Panel21 extends JPanel implements  ActionListener{
	//定义各种属性
	//定义面板
	private  JPanel pTop;
	private JPanel pCenter;
	private JPanel pBottom;
	//定义上部所需组件
	private JScrollPane js;
	private JTable table;
	//center
	private JLabel lbName; //原部门
	private JLabel lbDeptName1; //原部门
	private JLabel lbDeptName2;   //新部门
	
	private JTextField tfPersonId;
	private JTextField tfName;
	private JTextField tfDeptName1;
	private JComboBox<String> CoDeptName2;
	//bottom
	private JButton btnChangeDept;
	private JButton btnClear;
	
	public Panel21() {
		setLayout(new BorderLayout());
		initTop();
		initCenter();
		initBottom();
		
	}
	public void initTop() {
		JLabel lbTitle = new JLabel("人员调动");
		lbTitle.setFont(new Font("宋体",0,16));
		pTop=new JPanel();
    	pTop.add(lbTitle);
		String []colTitle=new String[] {"工号","姓名"
				,"性别","部门","薪酬","考核信息"};
		String [][]colvalue=new String[10][6];
		table =new JTable(colvalue,colTitle);
		colvalue[0][0]="12";
		colvalue[0][1]="李四";
		colvalue[0][2]="男";
		colvalue[0][3]="教务处";
		colvalue[0][4]="5000";
		colvalue[0][5]="优秀";
		//设置表格默认大小
		table.setPreferredScrollableViewportSize(new  Dimension(430,300));
		table.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			 public  void mouseClicked(MouseEvent e){
				
				int row =table.getSelectedRow();
				tfName.setText(colvalue[row][1]);
				tfDeptName1.setText(colvalue[row][3]);
				
			}
		});
		js=new JScrollPane(table);
		js.setPreferredSize(new Dimension(430,300));
		pTop.add(js);
		add(pTop,BorderLayout.NORTH);
	}
    public void initCenter() {
		pCenter=new JPanel();
		//姓名
		lbName=new JLabel("姓名");
		tfName=new JTextField(15);
		//原部门项
		lbDeptName1=new JLabel("原部门");
		tfDeptName1=new JTextField(15);
		//新部门
		lbDeptName2=new JLabel("新部门");
		CoDeptName2=new JComboBox<String>();
		//添加到面板
		pCenter.add(lbName);
		pCenter.add(tfName);
		pCenter.add(lbDeptName1);
		pCenter.add(tfDeptName1);
		pCenter.add(lbDeptName2);
		pCenter.add(CoDeptName2);
		add(pCenter,BorderLayout.CENTER);
	}
    public void initBottom() {
	pBottom=new JPanel();
	btnChangeDept=new JButton("调入新部门");
	pBottom.add(btnChangeDept);
	btnClear=new JButton("清空信息删除");
	pBottom.add(btnClear);
	add(pBottom,BorderLayout.SOUTH);
	
}
	@Override
	public void actionPerformed(ActionEvent arg1) {
		// TODO Auto-generated method stub
		if(arg1.getSource()==btnClear) {
		    tfName.setText(null);
			tfDeptName1.setText(null);
		}
	}
}
