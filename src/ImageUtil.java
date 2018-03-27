import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;

public class ImageUtil {
	public static BufferedImage Mat2BufferedImage(Mat m) {
	    // output can be assigned either to a BufferedImage or to an Image
	    int type = BufferedImage.TYPE_BYTE_GRAY;
	    if ( m.channels() > 1 ) {
	        type = BufferedImage.TYPE_3BYTE_BGR;
	    }
	    int bufferSize = m.channels()*m.cols()*m.rows();
	    byte [] b = new byte[bufferSize];
	    m.get(0,0,b); // get all the pixels
	    BufferedImage image = new BufferedImage(m.cols(),m.rows(), type);
	    final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
	    System.arraycopy(b, 0, targetPixels, 0, b.length);  
	    return image;
	}
	
	//Function to convert to MatOfPoint2f from MatOfPoint.
	public static MatOfPoint2f MatOfPoint2fToMatOfPoint(MatOfPoint srcMat){
		MatOfPoint2f mat = new MatOfPoint2f();
		srcMat.convertTo(mat,CvType.CV_32F);
		return mat;
	}
	
	
}
