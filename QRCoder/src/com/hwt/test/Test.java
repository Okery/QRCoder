package com.hwt.test;

import com.hwt.core.QRCoder;

public class Test {
	
	public static void main(String[] args) {
		try {
			//QRCoder.creatNoLogo("第一个图片", 150, 150,"d:", "第一 ");
			QRCoder.creatWithLogo("带logo图片", 200, 200, "d:", "d:/logo.jpg", 30, 30, "带logo");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
