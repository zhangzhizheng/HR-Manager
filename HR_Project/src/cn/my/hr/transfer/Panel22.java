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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
/**
 * 调动历史查询
 * @author Administrator
 *
 */
public class Panel22 extends JPanel {
	//定义各种属性
	//定义面板
	private  JPanel pTop;
	private JPanel pCenter;
	//定义上部所需组件
	private JScrollPane js;
	private JTable table;
	String []colTitle=new String[] {"流水号","人员姓名"
			,"原部门","新部门","变更次数","变更日期"};
	String [][]colvalue=null;
	public Panel22() {
		setLayout(new BorderLayout());
		initTop();
	}
	public void initTop() {
		pTop=new JPanel();
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints cons = null;
		pTop.setLayout(gridBag);
		//添加标题
		JLabel lbTitle = new JLabel("调动历史查询");
		lbTitle.setFont(new Font("宋体",0,16));
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 0;
		gridBag.setConstraints(lbTitle, cons);
		pTop.add(lbTitle);
		colvalue=new String[10][6];
		table =new JTable(colvalue,colTitle);
		//设置表格默认大小
		table.setPreferredScrollableViewportSize(new  Dimension(430,300));
		table.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		js=new JScrollPane(table);
		js.setPreferredSize(new Dimension(430,300));
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 1;
		gridBag.setConstraints(js, cons);
		pTop.add(js);
		add(pTop,BorderLayout.NORTH);
	}
}
