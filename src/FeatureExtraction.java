import java.util.ArrayList;
import java.util.List;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

public class FeatureExtraction {
	
	public void detectFeature(Mat img) {
		
		detectShape(img);
	}
	
	public Mat detectShape(Mat img) {
		
		Mat edges = new Mat();
		//Mat result = new Mat();
		List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		Mat hierarchy = new Mat();
		Imgproc.Canny(img, edges, 100, 300);
		Imgproc.findContours(edges, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
		
		//third arg negative = draw all contours...
		Imgproc.drawContours(img, contours, -1, new Scalar(0,0,0),5);
		
		
		//MatOfPoint2f mop = new MatOfPoint2f(edges);
		
		//Imgproc.approxPolyDP(mop, (MatOfPoint2f) result, 0.04* Imgproc.arcLength(mop, true), true);
		
		return img;
	}
	
}
