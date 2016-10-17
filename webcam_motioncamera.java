/* Requires Webcam Capture API 
   URL(zip)  https://github.com/sarxos/webcam-capture/releases/download/webcam-capture-parent-0.3.10/webcam-capture-0.3.10-dist.zip
 */

import java.io.*;
import java.awt.image.*;
import java.util.*;
import java.text.*;
import javax.imageio.*;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamMotionDetector;
import com.github.sarxos.webcam.WebcamMotionEvent;
import com.github.sarxos.webcam.WebcamMotionListener;

public class webcam_motioncamera{

	public static void main(String[] args) throws IOException{

		Webcam webcam = Webcam.getDefault();
		WebcamMotionDetector detector = new WebcamMotionDetector(webcam);
		
		detector.setInterval(100);
		detector.addMotionListener(new MyMotionListener());
		detector.start();

		System.in.read();

	}
}

class MyMotionListener implements WebcamMotionListener {

	private int i ;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HHmm_ss");
	private double motion_strength = 30;

	@Override
	public void motionDetected(WebcamMotionEvent event){

		Date date = new Date();
		System.out.println(date.toString() + ": Motion detected " + event.getArea() );

		if(event.getArea() > motion_strength ){

			System.out.println("Saving motion...");
			BufferedImage image = Webcam.getDefault().getImage();

			

			try{

				ImageIO.write(image, "PNG", new File("./captures/" + sdf.format(date) + "__" + i +".png"));
			
			}catch(IOException e){
				e.printStackTrace();

			}

			i++;
		}

	}
}

  

