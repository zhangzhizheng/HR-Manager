package cn.my.hr.transfer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cn.hr.dao.DeptDao;
import cn.hr.dao.HistoryDao;
import cn.hr.dao.PersonDao;
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
	String []colTitle=new String[] {"工号","姓名"
			,"性别","部门","薪酬","考核信息"};
	String [][]colvalue=null;
	String PersonID=null;
	public Panel21() {
		setLayout(new BorderLayout());
		initTop();
		initCenter();
		initBottom();
		
	}
	public void initTop() {
		pTop=new JPanel();
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints cons = null;
		pTop.setLayout(gridBag);
		//添加标题
		JLabel lbTitle = new JLabel("人员调动");
		lbTitle.setFont(new Font("宋体",0,16));
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 0;
		gridBag.setConstraints(lbTitle, cons);
		pTop.add(lbTitle);
		String [][]colvalue=PersonDao.getPersonChange();
		table =new JTable(colvalue,colTitle);
		//设置表格默认大小
		table.setPreferredScrollableViewportSize(new  Dimension(430,300));
		table.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			 public  void mouseClicked(MouseEvent e){
				
				int row =table.getSelectedRow();
				PersonID=colvalue[row][0];
				tfName.setText(colvalue[row][1]);
				tfDeptName1.setText(colvalue[row][3]);
				
			}
		});
		js=new JScrollPane(table);
		js.setPreferredSize(new Dimension(430,300));
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 1;
		gridBag.setConstraints(js, cons);
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
		String [] depts=DeptDao.getDeptsForSelect();
		for (int i = 0; i < depts.length; i++) {
			CoDeptName2.addItem(depts[i]);
		}
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
	btnClear=new JButton("清空信息");
	pBottom.add(btnClear);
	add(pBottom,BorderLayout.SOUTH);
	btnChangeDept.addActionListener(this);
	btnClear.addActionListener(this);
	
}
    private void updateTable() {
    	colvalue=PersonDao.getPersonChange();
    	DefaultTableModel tableModel=new DefaultTableModel(colvalue,colTitle);
    	table.setModel(tableModel);
    }
	@Override
	public void actionPerformed(ActionEvent arg1) {
		// TODO Auto-generated method stub
		if(arg1.getSource()==btnClear) {
		    tfName.setText(null);
			tfDeptName1.setText(null);
		}
		if(arg1.getSource()==btnChangeDept){
			long pid=Long.parseLong(PersonID);
			String deptchange=CoDeptName2.getSelectedItem().toString();
			String[] deptParts=deptchange.split("-");
			String deptid=deptParts[0];
			PersonDao.updateChangeDept(pid,Long.parseLong(deptid));
			updateTable();
			String oldDept=PersonDao.getDepts(Long.parseLong(deptid));
			String newDept=deptParts[1]+deptParts[2];
			Date date=new Date();
			//时间格式转换
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String changetime=sdf.format(date);
			HistoryDao.getChangeCount(Long.parseLong(deptid), "人员调动");
			
			
			
			
		}
	}
}
