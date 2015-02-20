package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
 
public class Zip 
{
    static List<String> fileList;
    

    public Zip(){
	fileList = new ArrayList<String>();
    }
    
    public static void generateFileList(File node, String sourceFolder){
    	
    	//add file only
	if(node.isFile()){
		fileList.add(generateZipEntry(node.getAbsoluteFile().toString(), sourceFolder));
	}
 
	if(node.isDirectory()){
		String[] subNote = node.list();
		for(String filename : subNote){
			generateFileList(new File(node, filename), sourceFolder);
		}
	}
 
    }
    
    public static String generateZipEntry(String file, String sourceFolder){
    	return file.substring(sourceFolder.length()+1, file.length());
    }

    @SuppressWarnings("static-access")
	public void zipIt(String zipFile, String sourceFolder){
 
     byte[] buffer = new byte[1024];
 
     try{
    	 
    	   	File newZipFile = new File(zipFile);
    	   	if(newZipFile.exists()){
    	   		newZipFile.delete();
    	   	}
    	   		newZipFile.createNewFile();
    	   		System.out.println("Zip File Created");
    	   	
 
    	FileOutputStream fos = new FileOutputStream(newZipFile);
    	ZipOutputStream zos = new ZipOutputStream(fos);
 
    	//System.out.println("Output to Zip : " + newZipFile);
 
    	for(String file : this.fileList){
 
    		//System.out.println("File Added : " + file);
    		ZipEntry ze= new ZipEntry(file);
        	zos.putNextEntry(ze);
 
        	FileInputStream in = 
                       new FileInputStream(sourceFolder + File.separator + file);
 
        	int len;
        	while ((len = in.read(buffer)) > 0) {
        		zos.write(buffer, 0, len);
        	}
 
        	in.close();
    	}
 
    	zos.closeEntry();
    	//remember close it
    	zos.close();

    	System.out.println("File Zipped");
    	
/*    	File file = new File(sourceFolder);
    	if (file.isDirectory()) {
    		File[] filelist = file.listFiles();
    		for (int i = 0; i < filelist.length; i++) {
    			System.out.println(filelist[i].getName());
    			filelist[i].delete();
    		}
    		
    	}file.delete();
    	System.out.println("Source Folder Deletion complete");*/
    
    }catch(IOException ex){
       ex.printStackTrace();   
    }
   }
 

}