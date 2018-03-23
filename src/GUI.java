import java.awt.Button;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

public class GUI extends JFrame{

	private JPanel imgPanel;
	private JSlider hLowSlider;
	private JSlider sLowSlider;
	private JSlider vLowSlider;
	private JSlider hHighSlider;
	private JSlider sHighSlider;
	private JSlider vHighSlider;
	private JTextField fileName;

	
	public GUI(int width, int height) {
		this.setSize(width, height);
		this.setLayout(new BoxLayout(getContentPane(),BoxLayout.PAGE_AXIS));
		init();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void init() {
		fileName = new JTextField();
		fileName.setMaximumSize(new Dimension(50,100));
		this.add(fileName);
		imgPanel = new JPanel();
		this.add(imgPanel);
		
		addSliders();
	}
	
	public void displayImage(Image img) {
	    ImageIcon icon=new ImageIcon(img);
	    JLabel lbl=new JLabel();
	    lbl.setIcon(icon);
	    imgPanel.add(lbl);
	    imgPanel.setVisible(true);
	}

	public void addSliders() {
		JPanel sliderPanel = new JPanel();
		hLowSlider = new JSlider(0,255,0);		
		sLowSlider = new JSlider(0,255,0);
		vLowSlider = new JSlider(0,255,0);
		hHighSlider = new JSlider(0,255,0);
		sHighSlider = new JSlider(0,255,0);
		vHighSlider = new JSlider(0,255,0);
		sliderPanel.add(hLowSlider);
		sliderPanel.add(sLowSlider);
		sliderPanel.add(vLowSlider);
		sliderPanel.add(hHighSlider);
		sliderPanel.add(sHighSlider);
		sliderPanel.add(vHighSlider);
		JButton btn = new JButton("Refresh");
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
			
		});
		sliderPanel.add(btn);
		this.add(sliderPanel);
	}
	
	
	
}
