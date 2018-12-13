package cn.my.hr.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import cn.hr.dao.PersonDao;
/**
 * 查询人员
 * @author Administrator
 *
 */
public class Panel14 extends JPanel {
	//定义各种属性
	//定义面板
	private  JPanel pTop;
	//定义上部所需组件
	private JScrollPane js;
	private JTable table;
	
	String []colTitle=new String[] {"编号","姓名"
			,"出生年月","民族","地址","部门"};
	String [][]colvalue=null;
	public Panel14() {
		setLayout(new BorderLayout());
		initTop();
	}
	public void initTop() {
		pTop=new JPanel();
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints cons = null;
		pTop.setLayout(gridBag);
		//添加标题
		JLabel lbTitle = new JLabel("人员信息查询");
		lbTitle.setFont(new Font("宋体",0,16));
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 0;
		gridBag.setConstraints(lbTitle, cons);
		pTop.add(lbTitle);
		String [][]colvalue=PersonDao.getAllForBasic();
		table =new JTable(colvalue,colTitle);
		//设置表格默认大小
		table.setPreferredScrollableViewportSize(new  Dimension(430,300));
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
