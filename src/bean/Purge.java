package bean;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Purge {

public static void main(String[] args){
	
/*		String path = "cmd /c start callpurge.bat";
		try {
		
			Runtime rn = Runtime.getRuntime();
			//Runtime.getRuntime().exec("cmd /c start purge.bat");
			Process pr = rn.exec(path);
			System.out.println("Purging complete");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	String path = "D:\\Proe_RnD\\jlink\\jlinkapp\\media\\fromClient\\y.asm.zip";
	
	File file = new File(path);
	if (file.isDirectory()) {
		File[] filelist = file.listFiles();
		for (int i = 0; i < filelist.length; i++) {
			System.out.println(filelist[i].getName());
			filelist[i].delete();
		}
		
	}file.delete();
	System.out.println("Deletion complete");*/
	
	
/*	File path = new File("D:\\Proe_RnD\\jlink\\jlinkapp\\media\\fromClient\\check.prt.13");
	System.out.println(path.getName());
	

	String pathnew = ("state,city,county".replaceAll(".*,", ""));
	System.out.println(pathnew);*/
	
	Properties prop = new Properties();
	try {
		prop.load(new FileInputStream("src/config.properties"));
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println(prop.get("fromClient"));
}
}
