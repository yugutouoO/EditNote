/*****自己写出字体对话框类来，供以后调用.
 * 	     来熟悉布局和组件
 * *****/
/*字体对话框类*/
////不是用JComboBox这个组件.用JList和JTextField
////设置完字体后，再打开字体对话框，默认显示的应该是上次的字体.
////设置完字体后，再打开字体还是原来默认的，没有保存上字体.-->bug
////或者禁止改变对话框大小;或者改变大小组件也相应变大变小.
////对话框中字体JList中的各字体最好用其代表的字体显示出来.
package frame;
/*****自己写出字体对话框类来，供以后调用.
 * 	     来熟悉布局和组件
 * *****/
/*字体对话框类*/
////设置完字体后，再打开字体还是原来默认的，没有保存上字体.-->bug
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
/**********字体对话框类**********/
public class FontDia extends JDialog implements ListSelectionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int WIDTH = 50;
	private static final int HEIGHT = 100;
	public static FontDia fontDia;
	public static Font areaFont;
//	public static String lastFont = "宋体";
//	public static String lastStyle = "常规";
//	public static String lastSize = "18";
	public static JPanel panel1;
	public static JPanel panel2;
	public static JPanel panel3;
	public static JPanel panel4;
	public static JPanel panel5;
	public static JList fontList;
	public static JList styleList;
	public static JList sizeList;
	public static JLabel fontLabel;
	public static JLabel styleLabel;
	public static JLabel sizeLabel;
	public static JLabel showLabel;
	public static JTextField fontField;
	public static JTextField styleField;
	public static JTextField sizeField;
	public static JButton okButton;
	public static JButton cancleButton;
	public static Font font = new Font("宋体",Font.PLAIN,15);
	private static String[] fontItems = {"楷体","黑体","宋体","微软雅黑","仿宋"};
	private static String[] styleItems = {"常规","粗体","倾斜","粗偏斜体"};
	private static String[] sizeItems = {"8","10","12","14","16","18","20"};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		fontDia = new FontDia();
		fontDia.addComponent();
		fontDia.setVisible(true);
	}
	public FontDia(){
		this.setLocation(400,200);
		this.setSize(500,500);
		this.setTitle("字体");	
		this.setResizable(false);
	}
	public void addComponent(){
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		panel5 = new JPanel();
		//设置JList里每一行的字体
		fontList = new JList(fontItems);
		MyRenderer fontListRenderer = new MyRenderer();
		fontList.setCellRenderer(fontListRenderer);
		
		styleList = new JList(styleItems);
		MyRenderer styleListRenderer = new MyRenderer();
		styleList.setCellRenderer(styleListRenderer);
		
		sizeList = new JList(sizeItems);
		MyRenderer sizeListRenderer = new MyRenderer();
		sizeList.setCellRenderer(sizeListRenderer);
		
		fontList.setVisibleRowCount(4);
		styleList.setVisibleRowCount(4);
		sizeList.setVisibleRowCount(4);
		fontLabel = new JLabel("字体");
		styleLabel = new JLabel("字形");
		sizeLabel = new JLabel("字号");
		showLabel = new JLabel("示例文本区");
//		fontField = new JTextField(lastFont);
//		styleField = new JTextField(lastStyle);
//		sizeField = new JTextField(lastSize);
		fontField = new JTextField("宋体");
		styleField = new JTextField("常规");
		sizeField = new JTextField("18");
		showLabel.setFont(getFont());
		okButton = new JButton("确定");
		cancleButton = new JButton("取消");
		/*滚动条*/
		JScrollPane fontScroller = new JScrollPane(fontList);
		JScrollPane styleScroller = new JScrollPane(styleList);
		JScrollPane sizeScroller = new JScrollPane(sizeList);
		fontScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		fontScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		styleScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		styleScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sizeScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sizeScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		/*布局*/
		this.setLayout(null);
		panel1.setBounds(0,0,this.getWidth(),20);
		panel1.setLayout(new GridLayout(1,3));
		panel2.setBounds(0,20,this.getWidth(),20);
		panel2.setLayout(new GridLayout(1,3));
		panel3.setBounds(0,40,this.getWidth(),85);
		panel3.setLayout(new GridLayout(1,3));
		panel4.setBounds(0,125,this.getWidth(),100);
		panel5.setBounds(0,225,this.getWidth(),50);
		/*add*/
		this.add(panel1);
		this.add(panel2);
		this.add(panel3);
		this.add(panel4);
		this.add(panel5);
		panel1.add(fontLabel);
		panel1.add(styleLabel);
		panel1.add(sizeLabel);
		panel2.add(fontField);
		panel2.add(styleField);
		panel2.add(sizeField);
		panel3.add(fontScroller);
		panel3.add(styleScroller);
		panel3.add(sizeScroller);
		panel4.add(showLabel);
		panel5.add(okButton);
		panel5.add(cancleButton);
		/*监听*/
		fontList.addListSelectionListener(this);
		styleList.addListSelectionListener(this);
		sizeList.addListSelectionListener(this);
		okButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MyFrame.area.setFont(getFont());
				FontDia.areaFont = getFont();
				fontDia.dispose();
			}
		});
		cancleButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				fontDia.dispose();
			}
		});
}
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == fontList){
			fontField.setText(fontList.getSelectedValue().toString());
		}else if(e.getSource() == styleList){
			styleField.setText(styleList.getSelectedValue().toString());
		}else if(e.getSource() == sizeList){
			sizeField.setText(sizeList.getSelectedValue().toString());
		}
		showLabel.setFont(getFont());
		showLabel.setText("示例文本区");
	}
	public Font getFont(){
		String font = fontField.getText();
//		lastFont = font;
		int style = Font.PLAIN;
		if(styleField.getText().equals("常规")){
			style = Font.PLAIN;
//			lastStyle = "常规";
		}
		else if(styleField.getText().equals("粗体")){
			style = Font.BOLD;
//			lastStyle = "粗体";
		}
		else if(styleField.getText().equals("倾斜")){
			style = Font.ITALIC;
//			lastStyle = "倾斜";
		}
		else if(styleField.getText().equals("粗偏斜体")){
			style = Font.BOLD + Font.ITALIC;
//			lastStyle = "粗偏斜体"; 
		}
		int size = Integer.parseInt(sizeField.getText());
//		lastSize = Integer.toString(size);
		Font myFont = new Font(font,style,size);
		return myFont;
	}
}