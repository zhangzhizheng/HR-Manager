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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cn.hr.dao.DeptDao;
import cn.hr.model.Dept;
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
	String []colTitle=new String[] {"部门编号","一级部门"
			,"二级部门"};
	String [][]colvalue=null;
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
		String [][]colvalue=DeptDao.getDeptDao();
		table =new JTable(colvalue,colTitle);
		//设置表格默认大小
		table.setPreferredScrollableViewportSize(new  Dimension(430,300));
		table.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			 public  void mouseClicked(MouseEvent e){
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
	btnAdd.setEnabled(false);
	pBottom.add(btnAdd);
	btnUpdate=new JButton("修改");
	btnUpdate.setEnabled(false);
	pBottom.add(btnUpdate);
	btnDelete=new JButton("删除");
	btnDelete.setEnabled(false);
	pBottom.add(btnDelete);
	btnClear=new JButton("清空");
	pBottom.add(btnClear);
	add(pBottom,BorderLayout.SOUTH);
	btnAdd.addActionListener(this);
	btnNextId.addActionListener(this);
	btnClear.addActionListener(this);
	btnUpdate.addActionListener(this);
	btnDelete.addActionListener(this);
}
    private void updateTable() {
    	DeptDao deptDao=new DeptDao();
    	colvalue=deptDao.getDeptDao();
    	DefaultTableModel tableModel=new DefaultTableModel(colvalue,colTitle);
    	table.setModel(tableModel);
    }
	@Override
	public void actionPerformed(ActionEvent arg1) {
		// TODO Auto-generated method stub
		if(arg1.getSource()==btnNextId) {
		long nextId=DeptDao.getNextId();
		tfDeptId.setText(String.valueOf(nextId));
		tfDeptName1.setText(null);
		tfDeptName2.setText(null);
		btnAdd.setEnabled(true);
		btnUpdate.setEnabled(true);
		btnDelete.setEnabled(true);
		}
		if(arg1.getSource()==btnAdd) {
			//增加
			//封装
			String deptid=tfDeptId.getText();
			String deptName1=tfDeptName1.getText();
			String deptName2=tfDeptName2.getText();
			//数据验证
			if(deptid==null||deptid=="") {
				JOptionPane.showMessageDialog(null, "请输入部门编号");
				return ;
			}
			Dept dept=new Dept();
			dept.setDeptID(Long.parseLong(deptid));
			dept.setB_Dept(deptName1);
			dept.setS_Dept(deptName2);
			//添加到数据库
			DeptDao.add(dept);
			JOptionPane.showMessageDialog(null, "添加成功");
			updateTable();
			
		}
		if(arg1.getSource()==btnUpdate) {
			//修改
			String deptid=tfDeptId.getText();
			String deptName1=tfDeptName1.getText();
			String deptName2=tfDeptName2.getText();
			if(deptName1==null||deptName1=="") {
				JOptionPane.showMessageDialog(null, "请输入一级部门");
				return ;
			}
			else if (deptName2==null||deptName2=="") {
				
				JOptionPane.showMessageDialog(null, "请输入二级部门");
				return ;
			}
			Dept dept=new Dept();
			dept.setDeptID(Long.parseLong(deptid));
			dept.setB_Dept(deptName1);
			dept.setS_Dept(deptName2);
			//添加到数据库
			DeptDao.updateDept(dept);
			JOptionPane.showMessageDialog(null, "修改成功");
			updateTable();//表格更新
		}
		if(arg1.getSource()==btnDelete) {
			//删除
			String deptid=tfDeptId.getText();
			if(deptid==null||deptid=="") {
				JOptionPane.showMessageDialog(null, "请输入部门编号");
				return ;
			}
			DeptDao.deleteDept(Long.parseLong(deptid));
			JOptionPane.showMessageDialog(null, "删除成功");
			updateTable();//表格更新
		}
		if(arg1.getSource()==btnClear) {
			//清空
			tfDeptName1.setText(null);
			tfDeptName2.setText(null);
		}
	}
}
