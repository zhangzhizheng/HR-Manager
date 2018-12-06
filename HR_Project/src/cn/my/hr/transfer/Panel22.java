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
	public Panel22() {
		setLayout(new BorderLayout());
		initTop();
		initCenter();
	}
	public void initTop() {
		JLabel lbTitle = new JLabel("调动历史查询");
		lbTitle.setFont(new Font("宋体",0,16));
		pTop=new JPanel();
     	pTop.add(lbTitle);
     	add(pTop,BorderLayout.NORTH);
	}
	public void initCenter() {
		pCenter=new JPanel();
		String []colTitle=new String[] {"流水号","人员姓名"
				,"原部门","新部门","变更次数","变更日期"};
		String [][]colvalue=new String[10][6];
		table =new JTable(colvalue,colTitle);
		colvalue[0][0]="1";
		colvalue[0][1]="张三";
		colvalue[0][2]="";
		colvalue[0][3]="汉族";
		colvalue[0][4]="北京";
		colvalue[0][5]="办公室-综合科";
		//设置表格默认大小
		table.setPreferredScrollableViewportSize(new  Dimension(430,300));
		table.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		js=new JScrollPane(table);
		js.setPreferredSize(new Dimension(430,300));
		pCenter.add(js);
		add(pCenter,BorderLayout.CENTER);
	}
}
