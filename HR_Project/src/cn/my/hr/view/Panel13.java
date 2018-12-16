package cn.my.hr.view;

import static org.hamcrest.CoreMatchers.nullValue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import cn.hr.dao.PersonDao;
/**
 * 删除信息
 * @author Administrator
 *
 */
public class Panel13 extends JPanel implements  ActionListener{
	//定义各种属性
	//定义面板
	private  JPanel pTop;
	private JPanel pCenter;
	private JPanel pBottom;
	//定义上部所需组件
	private JScrollPane js;
	private JTable table;
	//center
	private JLabel lbPersonId;  //人员编号
	private JLabel lbName;  //姓名
	private JLabel lbDeptName1;   //其他说明
	
	private JTextField tfPersonId;
	private JTextField tfName;
	private JTextField tfDeptName1;
	//bottom
	private JButton btnDelete;
	String []colTitle=new String[] {"编号","姓名"
			,"出生年月","民族","地址","部门"};
	String [][]colvalue=null;
	public Panel13() {
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
		JLabel lbTitle = new JLabel("删除人员信息");
		lbTitle.setFont(new Font("宋体",0,16));
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 0;
		gridBag.setConstraints(lbTitle, cons);
		pTop.add(lbTitle);
		String []colTitle=new String[] {"编号","姓名"
				,"出生年月","民族","地址","部门"};
		String [][]colvalue=PersonDao.getAllForBasic();
		table =new JTable(colvalue,colTitle);
		//设置表格默认大小
		table.setPreferredScrollableViewportSize(new  Dimension(430,300));
		table.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			 public  void mouseClicked(MouseEvent e){
				
				int row =table.getSelectedRow();
				tfPersonId.setText(colvalue[row][0]);
				tfName.setText(colvalue[row][1]);
				tfDeptName1.setText(colvalue[row][2]);
				
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
		lbPersonId=new JLabel("编号");
		tfPersonId=new JTextField(15);
		//一级部门项
		lbName=new JLabel("姓名");
		tfName=new JTextField(15);
		//二级部门项
		lbDeptName1=new JLabel("部门");
		tfDeptName1=new JTextField(15);
		//添加到面板
		pCenter.add(lbPersonId);
		pCenter.add(tfPersonId);
		pCenter.add(lbName);
		pCenter.add(tfName);
		pCenter.add(lbDeptName1);
		pCenter.add(tfDeptName1);
		add(pCenter,BorderLayout.CENTER);
	}
    public void initBottom() {
	pBottom=new JPanel();
	btnDelete=new JButton("删除");
	pBottom.add(btnDelete);
	add(pBottom,BorderLayout.SOUTH);
	btnDelete.addActionListener(this);
	
}
    private void updateTable() {
    	PersonDao personDao=new PersonDao();
    	colvalue=PersonDao.getAllForBasic();
    	DefaultTableModel tableModel=new DefaultTableModel(colvalue,colTitle);
    	table.setModel(tableModel);
    }
	@Override
	public void actionPerformed(ActionEvent arg1) {
		// TODO Auto-generated method stub
		if(arg1.getSource()==btnDelete) {
			tfDeptName1.setText("2");
			tfDeptName1.setText(null);
			tfDeptName1.setText(null);
			String personid=tfPersonId.getText();
			PersonDao.deletePerson(Long.parseLong(personid));
			JOptionPane.showMessageDialog(null, "删除成功");
			updateTable();//表格更新
		}
	}
}
