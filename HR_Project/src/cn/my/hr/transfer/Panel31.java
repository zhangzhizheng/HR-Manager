package cn.my.hr.transfer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
<<<<<<< HEAD
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
=======
>>>>>>> 9a486f8a117b7afd7682fedf1a0ceadbfe126562
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
 *人员考核
 * @author Administrator
 *
 */
public class Panel31 extends JPanel implements  ActionListener{
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
	private JLabel lbOldInfo;//上次考核
	private JLabel lbNewInfo;//本次考核
	private JTextField tfName;
	private JTextField tfOldInfo;
	private JComboBox<String> comboInfo; //本次考核
	//bottom
	private JButton btnOK;
	private JButton btnClear;
	public Panel31() {
		setLayout(new BorderLayout());
		initTop();
		initCenter();
		initBottom();	
	}
	public void initTop() {
<<<<<<< HEAD
		pTop=new JPanel();
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints cons = null;
		pTop.setLayout(gridBag);
		//添加标题
		JLabel lbTitle = new JLabel("人员考核");
		lbTitle.setFont(new Font("宋体",0,16));
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 0;
		gridBag.setConstraints(lbTitle, cons);
		pTop.add(lbTitle);
=======
		JLabel lbTitle = new JLabel("人员考核");
		lbTitle.setFont(new Font("宋体",0,16));
		
		pTop=new JPanel();
//		pTop.add(lbTitle);
>>>>>>> 9a486f8a117b7afd7682fedf1a0ceadbfe126562
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
				tfOldInfo.setText(colvalue[row][5]);
			}
		});
		js=new JScrollPane(table);
		js.setPreferredSize(new Dimension(430,300));
<<<<<<< HEAD
		cons = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 1;
		gridBag.setConstraints(js, cons);
=======
>>>>>>> 9a486f8a117b7afd7682fedf1a0ceadbfe126562
		pTop.add(js);
		add(pTop,BorderLayout.NORTH);
	}
    public void initCenter() {
		pCenter=new JPanel();
		//姓名
		lbName=new JLabel("姓名");
		tfName=new JTextField(15);
		//上次考核
		lbOldInfo=new JLabel("上次考核");
		tfOldInfo=new JTextField(15);
		//本次考核信息
		lbNewInfo=new JLabel("本次考核");
		comboInfo=new JComboBox<String>();
		
		pCenter.add(lbName);
		pCenter.add(tfName);
		pCenter.add(lbOldInfo);
		pCenter.add(tfOldInfo);
		pCenter.add(lbNewInfo);
		pCenter.add(comboInfo);
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
			tfOldInfo.setText(null);
			comboInfo.setEnabled(false);
		}
	}
}
