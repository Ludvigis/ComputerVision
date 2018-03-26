import org.opencv.core.*;
import org.opencv.imgcodecs.*;
import org.opencv.videoio.VideoCapture;

public class Main {
	//TODO Maven or Gradle ...
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat img = Imgcodecs.imread("./res/images/shapesWarped.png");
		GUI gui = new GUI(800,800);
		FeatureExtraction fe = new FeatureExtraction();
		
		VideoCapture cap = new VideoCapture(0);
		if(!cap.isOpened()) {
			System.out.println("Failed to open video capture");
		}
		Mat videoFrame = new Mat();
		//gui.displayImage(ImageUtil.Mat2BufferedImage(fe.detectShape(img)));
		while(true) {
			cap.retrieve(videoFrame);
			Mat res = fe.findSign(videoFrame,new Scalar(gui.hLow,gui.sLow,gui.vLow),new Scalar(gui.hHigh,gui.sHigh,gui.vHigh));
			gui.displayImage(ImageUtil.Mat2BufferedImage(res));
		}
		
		
		
	}
	
	 


}

