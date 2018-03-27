import java.awt.Dimension;
import java.awt.Image;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GUI extends JFrame{
	public int asd;
	private JPanel canvas;
	public int hLow;
	public int sLow;
	public int vLow;
	public int hHigh;
	public int sHigh;
	public int vHigh;
	private JLabel imageLabel;
	private JLabel imageLabel2;
	
	public GUI(int width, int height) {
		this.setSize(width, height);
		
		this.setLayout(new BoxLayout(getContentPane(),BoxLayout.PAGE_AXIS));
		init();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void init() {
		canvas = new JPanel();
		imageLabel = new JLabel();
		imageLabel2 = new JLabel();
		imageLabel2.setAlignmentX(RIGHT_ALIGNMENT);
		canvas.add(imageLabel);
		canvas.add(imageLabel2);
		this.add(canvas);
		addSliders();
	}
	
	public void displayImage(Image img) {
	    ImageIcon icon = new ImageIcon(img);
	    imageLabel.setIcon(icon);
	    this.revalidate();
	}
	
	public void displayImage(Image img, Image img2) {
	    ImageIcon icon = new ImageIcon(img);
	    ImageIcon icon2 = new ImageIcon(img2);
	    imageLabel.setIcon(icon);
	    imageLabel2.setIcon(icon2);
	    this.revalidate();
	}

	public void addSliders() {
		JPanel sliderPanel = new JPanel();
		JSlider hLowSlider = new JSlider(JSlider.HORIZONTAL,0,255,0);
		hLowSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider)e.getSource();
				hLow = source.getValue();
			}
		});
		JSlider sLowSlider = new JSlider(JSlider.HORIZONTAL,0,255,0);
		sLowSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider)e.getSource();
				sLow = source.getValue();
			}
		});
		JSlider vLowSlider = new JSlider(JSlider.HORIZONTAL,0,255,0);
		vLowSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider)e.getSource();
				vLow = source.getValue();
			}
		});
		JSlider hHighSlider = new JSlider(JSlider.HORIZONTAL,0,255,0);
		hHighSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider)e.getSource();
				hHigh = source.getValue();
			}
		});
		JSlider sHighSlider = new JSlider(JSlider.HORIZONTAL,0,255,0);
		sHighSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider)e.getSource();
				sHigh = source.getValue();
			}
		});
		JSlider vHighSlider = new JSlider(JSlider.HORIZONTAL,0,255,0);
		vHighSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider)e.getSource();
				vHigh = source.getValue();
			}
		});
		
		sliderPanel.add(hLowSlider);
		sliderPanel.add(sLowSlider);
		sliderPanel.add(vLowSlider);
		sliderPanel.add(hHighSlider);
		sliderPanel.add(sHighSlider);
		sliderPanel.add(vHighSlider);
		this.add(sliderPanel);
	}

	
}
