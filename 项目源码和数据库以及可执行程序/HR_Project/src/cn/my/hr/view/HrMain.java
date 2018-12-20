package cn.my.hr.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import cn.my.hr.transfer.Panel21;
import cn.my.hr.transfer.Panel22;
import cn.my.hr.transfer.Panel31;
import cn.my.hr.transfer.Panel32;
import cn.my.hr.transfer.Panel41;
import cn.my.hr.transfer.Panel42;
//主界面类
public class HrMain extends JFrame implements TreeSelectionListener {
	// 定义分隔面板
	public static JSplitPane splitPane;
	// 树菜单
	private JTree tree;
	private DefaultMutableTreeNode root; // 树的根结点
	private JPanel pWelcome;                //定义欢迎页面
	public HrMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 关闭时退出程序
		setSize(750, 450); // 设置大小
		setLocationRelativeTo(null); // 相对屏幕居中
//		setResizable(false); // 大小不可变
		setTitle("人事管理系统"); // 设置标题
		//设置图标
		//1.找到文件位置
		URL url=getClass().getClassLoader().getResource("icon.PNG");
		//2.加载图片到内存
		Image image=Toolkit.getDefaultToolkit().getImage(url);
		//3.把图片设置为窗口图标
     	setIconImage(image);
		setLocationRelativeTo(null);
		init(); // 调用初始化方法
		setVisible(true); // 设置窗口可见
	}

	public void init() {
		// 初始化分隔面板
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setDividerLocation(150); // 分隔条位置（左侧150像素）
		splitPane.setContinuousLayout(true); // 分隔条连续变化
		splitPane.setDividerSize(2); // 设置分隔面板宽度
		// 添加测试用的组件
		splitPane.setLeftComponent(new JButton("left"));
		splitPane.setRightComponent(new JButton("right"));
		// 初始化树菜单DefaultMutableTreeNode
		root = new DefaultMutableTreeNode("人事管理系统");
		DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("基本信息管理");
		DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("人员调动管理");
		DefaultMutableTreeNode node3 = new DefaultMutableTreeNode("人员考核管理");
		DefaultMutableTreeNode node4 = new DefaultMutableTreeNode("劳资管理");
		// 添加基本信息管理的子菜单
		node1.add(new DefaultMutableTreeNode("添加人员信息"));
		node1.add(new DefaultMutableTreeNode("修改人员信息"));
		node1.add(new DefaultMutableTreeNode("删除人员信息"));
		node1.add(new DefaultMutableTreeNode("查询人员信息"));
		node1.add(new DefaultMutableTreeNode("部门管理"));
		node2.add(new DefaultMutableTreeNode("人员调动"));
		node2.add(new DefaultMutableTreeNode("调动历史查询"));
		node3.add(new DefaultMutableTreeNode("人员考核"));
		node3.add(new DefaultMutableTreeNode("考核历史查询"));
		node4.add(new DefaultMutableTreeNode("劳资分配管理"));
		node4.add(new DefaultMutableTreeNode("劳资历史查询"));
		//node1.getDefaultMu()
		root.add(node1);
		root.add(node2);
		root.add(node3);
		root.add(node4);
		//把树菜单添加到窗口中
		  //把tree菜单添加到窗口
		  tree=new JTree(root);
		  tree.addTreeSelectionListener(this);
		  JPanel pLeft=new JPanel();
		  JScrollPane js=new JScrollPane(tree);
		  js.setPreferredSize(new Dimension(140,400));
		 // splitPane.setLeftComponent(new JScrollPane(tree));
		  pLeft.add(js);
		  splitPane.setLeftComponent(pLeft);
		 //欢迎界面
		  pWelcome=new JPanel();
		  JLabel lbWelcome=new JLabel("欢迎使用人事管理系统");
		  pWelcome.add(lbWelcome);
		  splitPane.setRightComponent(pWelcome);
		add(splitPane);
	}
	public static void main(String[] args) {
		// 修改程序外观
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			// UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 创建主窗口
		new HrMain();
	}
	// 对树菜单进行事件处理
	@Override
	public void valueChanged(TreeSelectionEvent e)
	   {
		   String menuStr=e.getPath().getLastPathComponent().toString();
		   switch(menuStr)
		   {
		   //点击根节点
		   case"人事管理系统":
			   splitPane.setRightComponent(pWelcome);
			   break; 
			   //点击一级节点
		   case"基本信息管理":
			   TreePath path1=e.getPath();
				//如果此路径是展开状态，则将其改为折叠状态
				 if(tree.isExpanded(path1))
				   {
					   tree.collapsePath(path1);//折叠
				   }else 
				   {
					   tree.expandPath(path1);
				   }
			   break;
		   case "添加人员信息": 
			   splitPane.setRightComponent(new Panel11());
			   break;
		   case "修改人员信息":
			   splitPane.setRightComponent(new Panel12());
			   break;
		   case "删除人员信息":
			   splitPane.setRightComponent(new Panel13());
			   break;
		   case "查询人员信息":
			   splitPane.setRightComponent(new Panel14());
			   break;
		   case "部门管理":
			   splitPane.setRightComponent(new Panel15());
			   break;	
		   case"人员调动管理":
			   TreePath path2=e.getPath();
				//如果此路径是展开状态，则将其改为折叠状态
				 if(tree.isExpanded(path2))
				   {
					   tree.collapsePath(path2);//折叠
				   }else 
				   {
					   tree.expandPath(path2);
				   }
			   break;
		   case"人员调动":
			   splitPane.setRightComponent(new Panel21());
		       break;
		   case"调动历史查询":
			   splitPane.setRightComponent(new Panel22());
		       break;
			case "人员考核管理":
				TreePath path3=e.getPath();
			//如果此路径是展开状态，则将其改为折叠状态
			 if(tree.isExpanded(path3))
			   {
				   tree.collapsePath(path3);//折叠
			   }else 
			   {
				   tree.expandPath(path3);
			   }
				break;
			case "人员考核":
				 splitPane.setRightComponent(new Panel31());
				break;
			case"考核历史查询":
				   splitPane.setRightComponent(new Panel32());
			       break;
			case "劳资管理":
				TreePath path4=e.getPath();
				//如果此路径是展开状态，则将其改为折叠状态
				 if(tree.isExpanded(path4))
				   {
					   tree.collapsePath(path4);//折叠
				   }else 
				   {
					   tree.expandPath(path4);
				   }
				break;
		   case "劳资分配管理":
			   splitPane.setRightComponent(new Panel41());
			   break;
		   case "劳资历史查询": 
			   splitPane.setRightComponent(new Panel42());
			   break;
		 
		   
		
		   }
	   }
}
