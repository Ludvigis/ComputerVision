import java.util.ArrayList;
import java.util.List;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.utils.Converters;

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
	
	
	public Mat[] findSign(Mat img , Scalar lowerBound, Scalar upperBound) {
		Mat hsv = img.clone();
		Imgproc.cvtColor(hsv, hsv, Imgproc.COLOR_BGR2HSV);
		Core.inRange(hsv, lowerBound, upperBound, hsv);
		//Imgproc.cvtColor(hsv, hsv, Imgproc.COLOR_BGR2GRAY);
		Imgproc.Canny(hsv, hsv, 100, 300);
		Imgproc.blur(hsv, hsv, new Size(5,5));
		
		List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		Mat hierarchy = new Mat();
		
		Imgproc.findContours(hsv, contours, hierarchy, Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
		
		double maxArea = 0;
		int maxAreaIndex = 0;
		MatOfPoint2f maxCurve = new MatOfPoint2f();
		
		MatOfPoint contour = null;
		MatOfPoint2f approxCurve = new MatOfPoint2f();
		
		
		for(int i = 0; i < contours.size();i++) {
			contour = contours.get(i);
			double area = Imgproc.contourArea(contour);
			if(area > maxArea) {
				
				MatOfPoint2f curve = new MatOfPoint2f(contour.toArray());
				
				Imgproc.approxPolyDP(curve, approxCurve, 0.04* Imgproc.arcLength(curve, true), true);
				
				
				if(approxCurve.total() == 4) {
					maxArea = area;
					maxAreaIndex = i;
					maxCurve = approxCurve;
					
				}
			}
		}
		//maxcurve är inte alltid 4?...
		if(maxCurve.total() == 4) {
			double[] corner1 = maxCurve.get(0, 0);
			Point p1 = new Point(corner1[0],corner1[1]);
			
			double[] corner2 = maxCurve.get(1, 0);
			Point p2 = new Point(corner2[0],corner2[1]);

			double[] corner3 = maxCurve.get(2, 0);
			Point p3 = new Point(corner3[0],corner3[1]);

			double[] corner4 = maxCurve.get(3, 0);
			Point p4 = new Point(corner4[0],corner4[1]);
		
			Imgproc.circle(img, p1, 10, new Scalar(255,0,0),4);
			Imgproc.circle(img, p2, 10, new Scalar(255,0,0),4);
			Imgproc.circle(img, p3, 10, new Scalar(255,0,0),4);
			Imgproc.circle(img, p4, 10, new Scalar(255,0,0),4);
			List<Point> cornerList = new ArrayList<Point>();
			cornerList.add(p1);
			cornerList.add(p2);
			cornerList.add(p3);
			cornerList.add(p4);

			
			Mat corners = Converters.vector_Point2f_to_Mat(cornerList);
			
			//add points to corners...
			
			Imgproc.drawContours(img, contours, maxAreaIndex, new Scalar(0,255,0),3);
			
			Mat transformationMatrix = new Mat(4,1,CvType.CV_32FC2);
			Imgproc.getPerspectiveTransform(corners, transformationMatrix);
		}
		
		
		
		Mat res[] = {img,hsv};
		return res;
	}
	
}
