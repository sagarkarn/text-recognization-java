package com.pkg;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class TextReco {
	Tesseract ts;
	public TextReco() {
		ts = new Tesseract();
//		ts.setDatapath("images");
		try {
			String text = ts.doOCR(readImage());
			System.out.println(text);
		} catch (TesseractException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public BufferedImage readImage() throws IOException {
		Mat mat = Imgcodecs.imread("images\\ss.png");
		Mat des = new Mat();
		Mat resizeImage = new Mat();
		Imgproc.cvtColor(mat, des, Imgproc.COLOR_BGR2GRAY);
		Imgproc.resize(des, resizeImage, new Size(des.width() * 1.9f,des.height() * 1.9f));
		
		Imgcodecs.imwrite("images\\ss1.png", resizeImage);
		
		
		MatOfByte mof = new MatOfByte();
		byte b[];
		Imgcodecs.imencode(".png", resizeImage, mof);
		b = mof.toArray();
		InputStream is = new ByteArrayInputStream(b);
		return ImageIO.read(is);
	}
	
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		new TextReco();
	}
}
