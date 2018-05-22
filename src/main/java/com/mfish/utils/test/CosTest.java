package com.mfish.utils.test;

import com.mfish.utils.qcloud.cos.CosUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class CosTest {
	public static void main(String[] args) throws FileNotFoundException {


		CosUtil c = new CosUtil();

		//通过硬盘方式上传文件
		/*String cosFilePath = "/t10.jpg";
		String localFilePath = "c:/test-download.jpg";
		String dskUpload = c.upload(cosFilePath, localFilePath);
		System.out.println(dskUpload);
*/

		//通过流的方式上传文件
		File initialFile = new File("e:/msdia80.dll");
		InputStream is = new FileInputStream(initialFile);
		String isUpload = c.uploadByIo("/msdia80.dll", is);
		System.out.println(isUpload);


		//下载文件
		/*String down = c.down("/test.txt", "d:/test.txt");
		System.out.println("文件下载返回信息："+down);*/


		//删除文件
		/*String msg = c.delete("/test.txt");
		System.out.println(msg);*/


		// 关闭释放资源
		/*c.cosClient.shutdown();
		System.out.println("shutdown!");*/
	}
}
