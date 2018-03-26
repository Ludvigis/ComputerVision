import java.util.ArrayList;
import java.util.List;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

public class FeatureExtraction {
	
	
	public Mat detectShapeCountCurve(Mat img) {
		Mat edges = new Mat();
		List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		Mat hierarchy = new Mat();
		Imgproc.Canny(img, edges, 100, 300);
		//Imgproc.blur(edges, edges, new Size(2, 2));
		Imgproc.findContours(edges, contours, hierarchy, Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
		
		//third argument negative = draw all contours...
		Imgproc.drawContours(img, contours, -1, new Scalar(255,0,0),5);
			
		
		MatOfPoint2f approxCurve = new MatOfPoint2f();
		MatOfPoint2f curve = new MatOfPoint2f();
		for (int i = 0; i < contours.size(); i++) {
			Rect rect = Imgproc.boundingRect(contours.get(i));
			int centerX = rect.x + rect.width/2;
			int centerY = rect.y + rect.height/2;
			
			//marks center
			Imgproc.circle(edges,new Point(centerX,centerY),8,new Scalar(0,0,0),-1);
			Imgproc.circle(edges,new Point(centerX,centerY),4,new Scalar(255,255,255),-1);
			
			//writes out contour integer.
			Imgproc.putText(edges, Integer.toString(i), new Point(centerX + 10,centerY), Core.FONT_HERSHEY_SIMPLEX, 1, new Scalar(255,100,255));
			
			MatOfPoint c = contours.get(i);
			
			curve.fromList(c.toList());

			Imgproc.approxPolyDP(curve, approxCurve, 0.01* Imgproc.arcLength(curve, true), true);
			if(approxCurve.total() == 3) {
				System.out.println(i + ": " + " Triangel");
			}else if(approxCurve.total() == 4) {
				System.out.println(i + ": " + " Fyrkant");
			}else if(approxCurve.total() > 8) {
				System.out.println(i + ": " + " Cirkel");
			}else {
				System.out.println(i + ": " + approxCurve.total());
			}
			
		}
		return edges;
	}
	
	public Mat detectShapeShapeFactor(Mat img){
		Mat edges = new Mat();
		List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		Mat hierarchy = new Mat();
		Imgproc.Canny(img, edges, 100, 300);
		//Imgproc.blur(edges, edges, new Size(2, 2));
		Imgproc.findContours(edges, contours, hierarchy, Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
		
		
		return img;
		
	}
	
	
	public Mat findSign(Mat img , Scalar lowerBound, Scalar upperBound) {
		Mat hsv = img.clone();
		Imgproc.cvtColor(hsv, hsv, Imgproc.COLOR_BGR2HSV);
		Core.inRange(hsv, lowerBound, upperBound, hsv);
		return hsv;
	}
	
}
