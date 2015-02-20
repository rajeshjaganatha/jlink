package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnZip{
	public List<String> fileList = new ArrayList<String>();

public List<String> unZipIt(String zipFile, String outputFolder){
	 
    byte[] buffer = new byte[1024];

    try{

   	//create output directory is not exists
   	File folder = new File(outputFolder);
   	if(!folder.exists()){
   		folder.mkdir();
   		System.out.println("directory created");
   	}

   	//get the zip file content
   	ZipInputStream zis = 
   		new ZipInputStream(new FileInputStream(zipFile));
   	//get the zipped file list entry
   	ZipEntry ze = zis.getNextEntry();
   	
   	int i = 0;
   	while(ze!=null){
   		
   			fileList.add(ze.getName());
   			System.out.println(i+"	- "+fileList.get(i));
   			i++;
   			
			String fileName = ze.getName();
          	File newFile = new File(outputFolder + File.separator + fileName);

           //System.out.println("file unzip : "+ newFile.getAbsoluteFile());
           //create all non exists folders
           //else you will hit FileNotFoundException for compressed folder
           new File(newFile.getParent()).mkdirs();

           FileOutputStream fos = new FileOutputStream(newFile);             

           int len;
           while ((len = zis.read(buffer)) > 0) {
      		fos.write(buffer, 0, len);
           }

           fos.close();   
           ze = zis.getNextEntry();
   	}

    zis.closeEntry();
   	zis.close();
   	File zipFile1 = new File(zipFile);
   	zipFile1.delete();
   	System.out.println("File Unzipped");
   }catch(IOException ex){
      ex.printStackTrace(); 	
   }
    return fileList;
  }    
}