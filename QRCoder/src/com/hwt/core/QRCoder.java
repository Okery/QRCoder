package com.hwt.core;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

/**
 * 
 * @author : Liuhe
 */
public class QRCoder {
	
	
	/**
	 * 	create the QR code with no logo
	 * @param content : the content in QR code 
	 * @param width : the width of image created final
	 * @param height :the height of image created final
	 * @param path : the path you want to save
	 * @param resultName : the image name
	 * @throws Exception : while the file not found,throw the Exception
	 */
	public static final void creatNoLogo(String content,int width,int height,String path,String resultName) throws Exception{
		MultiFormatWriter writer = new MultiFormatWriter();
		 HashMap<EncodeHintType, Object> hints = new HashMap<>();
		 hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); 
		BitMatrix bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, width, height,hints);
		MatrixToImageWriter.writeToPath(bitMatrix, "png", new File(path+"/"+resultName+".png").toPath());
	}
	
	/**
	 * 	create the QR code with no logo but you can set the image color that you want
	 * @param content : the content in QR code 
	 * @param width: the width of image created final
	 * @param height  :the height of image created final
	 * @param path  : the path you want to save
	 * @param resultName : the image name
	 * @param infoRGB : the color of the part with information , the value should be RGB form
	 * @param otherRGB : the color of the part with no information , the value should be RGB form
	 * @throws Exception : while the file not found,throw the Exception
	 */
	public static final void creatNoLogo(String content,int width,int height,String path,String resultName,int infoRGB,int otherRGB) throws Exception{
		MultiFormatWriter writer = new MultiFormatWriter();
		 HashMap<EncodeHintType, Object> hints = new HashMap<>();
		 hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); 
		BitMatrix bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, width, height,hints);
		BufferedImage qr = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		for(int x=0;x<width;x++){
			for(int y=0;y<height;y++){
				if(bitMatrix.get(x, y)){
					qr.setRGB(x, y, infoRGB);
				}else{
					qr.setRGB(x, y, otherRGB);
				}
			}
		}
		ImageIO.write(qr, "png", new File(path+"/"+resultName+".png"));
	}
	
	
	/**
	 * 	create the QR code with logo in center
	 * @param content : the content in QR code 
	 * @param width : the width of image created final
	 * @param height : the height of image created final
	 * @param path	 : the path you want to save
	 * @param logoPath : the path of logo
	 * @param logoWidth : The logo width to be generated (logo area should not exceed 25% of the area of the two-dimensional code).
	 * @param logoHeight	: The logo width to be generated (logo area should not exceed 25% of the area of the two-dimensional code).
	 * @param resultName : image name
	 * @throws Exception : while the file not found,throw the Exception
	 */
	public static final void creatWithLogo(String content,int width,int height,String path,String logoPath,int logoWidth,int logoHeight,String resultName) throws Exception{
		MultiFormatWriter writer = new MultiFormatWriter();
		 HashMap<EncodeHintType, Object> hints = new HashMap<>();
		 hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); 
		BitMatrix bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, width, height,hints);
		BufferedImage qr = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		for(int x=0;x<width;x++){
			for(int y=0;y<height;y++){
				if(bitMatrix.get(x, y)){
					qr.setRGB(x, y, 0x0000000);
				}else{
					qr.setRGB(x, y, 0xffffff);
				}
			}
		}
		BufferedImage logoImg = ImageIO.read(new File(logoPath));
		Image logoUse = logoImg.getScaledInstance(logoWidth, logoHeight, Image.SCALE_FAST);
		Graphics graphics = qr.getGraphics();
		int logoX = (width-logoWidth)/2;
		int logoY = (height-logoHeight)/2;
		graphics.drawImage(logoUse, logoX, logoY, null);
		ImageIO.write(qr, "png", new File(path+"/"+resultName+".png"));
	}

	/**
	 * 	create the QR code with logo and you can set the image color that you want
	 * @param content : the content in QR code 
	 * @param width : the width of image created final
	 * @param height	: the height of image created final
	 * @param path	: the path you want to save
	 * @param logoPath : the path of logo
	 * @param logoWidth  : The logo width to be generated (logo area should not exceed 25% of the area of the two-dimensional code).
	 * @param logoHeight	 : The logo height to be generated (logo area should not exceed 25% of the area of the two-dimensional code).
	 * @param resultName	 : image name
	 * @param infoRGB  : the color of the part with information , the value should be RGB form
	 * @param otherRGB  : the color of the part with no information , the value should be RGB form
	 * @throws Exception	: while the file not found,throw the Exception
	 */
	public static final void creatWithLogo(String content,int width,int height,String path,String logoPath,int logoWidth,int logoHeight,String resultName,int infoRGB,int otherRGB) throws Exception{
		MultiFormatWriter writer = new MultiFormatWriter();
		 HashMap<EncodeHintType, Object> hints = new HashMap<>();
		 hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); 
		BitMatrix bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, width, height,hints);
		BufferedImage qr = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		for(int x=0;x<width;x++){
			for(int y=0;y<height;y++){
				if(bitMatrix.get(x, y)){
					qr.setRGB(x, y, infoRGB);
				}else{
					qr.setRGB(x, y, otherRGB);
				}
			}
		}
		BufferedImage logoImg = ImageIO.read(new File(logoPath));
		Image logoUse = logoImg.getScaledInstance(logoWidth, logoHeight, Image.SCALE_FAST);
		Graphics graphics = qr.getGraphics();
		int logoX = (width-logoWidth)/2;
		int logoY = (height-logoHeight)/2;
		graphics.drawImage(logoUse, logoX, logoY, null);
		ImageIO.write(qr, "png", new File(path+"/"+resultName+".png"));
	}
	
	
	
	
	

	
	
}
