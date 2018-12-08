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
 *劳资分配管理
 * @author Administrator
 *
 */
public class Panel41 extends JPanel implements  ActionListener{
	//定义各种属性
	//定义面板
	private  JPanel pTop;
	private JPanel pCenter;
	private JPanel pBottom;
	//定义上部所需组件
	private JScrollPane js;
	private JTable table;
	//center
	private JLabel lbName;  //姓名
	private JLabel lbOldSalary;//上次考核
	private JLabel lbNewSalary;//本次考核
	private JTextField tfName;
	private JTextField tfOldSalary;//调整前的工资
	private JTextField tfNewSalary;//调整后的工资
	//bottom
	private JButton btnOK;
	private JButton btnClear;
	public Panel41() {
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
		JLabel lbTitle = new JLabel("劳资分配");
		lbTitle.setFont(new Font("宋体",0,16));
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 0;
		gridBag.setConstraints(lbTitle, cons);
		pTop.add(lbTitle);
		String []colTitle=new String[] {"工号","姓名"
				,"性别","部门","薪酬","考核信息"};
		String [][]colvalue=new String[10][6];
		table =new JTable(colvalue,colTitle);
		colvalue[0][0]="1";
		colvalue[0][1]="张三";
		colvalue[0][2]="男";
		colvalue[0][3]="教务处";
		colvalue[0][4]="6000";
		colvalue[0][5]="优秀";
		//设置表格默认大小
		table.setPreferredScrollableViewportSize(new  Dimension(430,300));
		table.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			 public  void mouseClicked(MouseEvent e){
				
				int row =table.getSelectedRow();
				//只获取两个
				tfName.setText(colvalue[row][1]);
				tfOldSalary.setText(colvalue[row][4]);
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
		//调整前工资
		lbOldSalary=new JLabel("调整后的工资");
		tfOldSalary=new JTextField(15);
		lbNewSalary=new JLabel("调整前的工资");
		tfNewSalary=new JTextField(15);
		pCenter.add(lbName);
		pCenter.add(tfName);
		pCenter.add(lbOldSalary);
		pCenter.add(tfOldSalary);
		pCenter.add(lbNewSalary);
		pCenter.add(tfNewSalary);
		add(pCenter,BorderLayout.CENTER);
	}
    public void initBottom() {
	pBottom=new JPanel();
	btnOK=new JButton("确定");
	pBottom.add(btnOK);
	btnClear=new JButton("清空");
	pBottom.add(btnClear);
	add(pBottom,BorderLayout.SOUTH);
}
	@Override
	public void actionPerformed(ActionEvent arg1) {
		// TODO Auto-generated method stub
		if(arg1.getSource()==btnOK) {
			tfName.setText(null);
			tfOldSalary.setText(null);
			tfNewSalary.setText(null);
		}
	}
}
