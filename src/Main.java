import java.awt.Color;

import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.*;
import org.opencv.imgproc.Imgproc;

public class Main {
	//TODO Maven or Gradle ...
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat img = Imgcodecs.imread("./res/images/shapes.png");
		GUI gui = new GUI(800,600);
		FeatureExtraction fe = new FeatureExtraction();
		gui.displayImage(ImageUtil.Mat2BufferedImage(fe.detectShape(img)));
		
	}
	
	 


}

