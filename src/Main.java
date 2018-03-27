import org.opencv.core.*;
import org.opencv.imgcodecs.*;
import org.opencv.videoio.VideoCapture;

public class Main {
	//TODO Maven or Gradle ...
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat img = Imgcodecs.imread("./res/images/shapes.png");
		GUI gui = new GUI(1400,800);
		FeatureExtraction fe = new FeatureExtraction();
		
		//gui.displayImage(ImageUtil.Mat2BufferedImage(fe.detectShapeCountCurve(img)));
		//Mat[] images = fe.findSign(img,new Scalar(gui.hLow,gui.sLow,gui.vLow),new Scalar(gui.hHigh,gui.sHigh,gui.vHigh));
		//gui.displayImage(ImageUtil.Mat2BufferedImage(images[0]),ImageUtil.Mat2BufferedImage(images[1]));
		
		
		VideoCapture cap = new VideoCapture(0);
		if(!cap.isOpened()) {
			System.out.println("Failed to open video capture");
		}
		Mat videoFrame = new Mat();
		
		while(true) {
			cap.retrieve(videoFrame);
			Mat res[] = fe.findSign(videoFrame,new Scalar(gui.hLow,gui.sLow,gui.vLow),new Scalar(gui.hHigh,gui.sHigh,gui.vHigh));
			gui.displayImage(ImageUtil.Mat2BufferedImage(res[0]),ImageUtil.Mat2BufferedImage(res[1]));
		}
				
	}
	
	 


}

