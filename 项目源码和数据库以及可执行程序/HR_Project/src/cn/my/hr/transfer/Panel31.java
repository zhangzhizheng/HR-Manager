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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cn.hr.dao.HistoryDao;
import cn.hr.dao.PersonDao;
import cn.hr.model.History;
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
	String []colTitle=new String[] {"工号","姓名"
			,"性别","部门","薪酬","考核信息"};
	String [][]colvalue=null;
	String PersonID=null;
	public Panel31() {
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
		JLabel lbTitle = new JLabel("人员考核");
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
				//获取
			    PersonID=colvalue[row][0];
				tfName.setText(colvalue[row][1]);
				tfOldInfo.setText(colvalue[row][5]);
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
		tfName.setEnabled(false);
		//上次考核
		lbOldInfo=new JLabel("上次考核");
		tfOldInfo=new JTextField(15);
		tfOldInfo.setEnabled(false);
		//本次考核信息
		lbNewInfo=new JLabel("本次考核");
		comboInfo=new JComboBox<String>();
		comboInfo.addItem("优秀");
		comboInfo.addItem("合格");
		comboInfo.addItem("不合格");
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
	btnOK.addActionListener(this);
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
		if(arg1.getSource()==btnOK) {
			long pid=Long.parseLong(PersonID);
			String newassess=comboInfo.getSelectedItem().toString();
			String oldassess=tfOldInfo.getText();
			if(tfName.getText()==null||tfOldInfo.getText()==null||tfName.getText()==""||tfOldInfo.getText()==""){
				JOptionPane.showMessageDialog(null, "请选择正确信息");
				return;
			}
			PersonDao.updateAssess(pid, newassess);
			updateTable();
			
			String journo=String.valueOf(HistoryDao.getNextId());//流水号
			System.out.println(journo);
			Date date=new Date();
			//时间格式转换
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String changetime=sdf.format(date);
			History h=new History();
			h.setJourNo(journo);
			h.setOldInfo(oldassess);//通过选中的人的id获取部门id
			h.setNewInfo(newassess);
			h.setChgTime(String.valueOf(HistoryDao.getChangeCount("人员考核", pid)+1));//获取变动次数加一
			h.setPersonID(PersonID);
			h.setFromAcc("人员考核");
			h.setRegDate(changetime);
			HistoryDao.addHistory(h);//更改历史
			tfName.setText(null);
			tfOldInfo.setText(null);
			JOptionPane.showMessageDialog(null, "修改成功");
		}
		if(arg1.getSource()==btnClear) {
			tfName.setText(null);
			tfOldInfo.setText(null);
		}
	}
}
