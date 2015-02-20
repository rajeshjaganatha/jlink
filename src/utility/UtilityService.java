package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.util.Properties;



/**
 * 
 * @author Arvind.C
 * 
 */
public abstract class UtilityService {

	public static Properties loadPropertyFile(String propertyFileName)
			throws IOException {
		System.out.println("entered");
		
		InputStream stream = ClassLoader
				.getSystemResourceAsStream(propertyFileName);
		if (stream == null) {
			System.out.println(propertyFileName
					+ " not found in classpath.");
		}
		Properties props = new Properties();
		props.load(stream);
		return props;

	}

	public static String getFileType(File file) {
		return file.getName().substring(file.getName().lastIndexOf("."),
				file.getName().length());

	}

	public static boolean removeDirectory(File directory) {

		if (directory == null)
			return false;
		if (!directory.exists())
			return true;
		if (!directory.isDirectory())
			return false;

		String[] list = directory.list();
		if (list != null) {
			for (int i = 0; i < list.length; i++) {
				File entry = new File(directory, list[i]);
				if (entry.isDirectory()) {
					if (!removeDirectory(entry))
						return false;
				} else {
					if (!entry.delete())
						return false;
				}
			}
		}
		return directory.delete();
	}

	public static boolean isFileReal(String filePath) {

		File f = new File(filePath);

		if (f.exists()) {
			return true;
		} else {
			return false;
		}
	}

/*	public static void manageUploadMediaDirs(Properties uploadProps)
			throws DataUploadException {

		File tmpDir = new File(uploadProps.getProperty("auto.dir")
				+ "\\temp");
		System.out.println("temp directory "+tmpDir.getPath());
		if(!tmpDir.isDirectory()){
			tmpDir.mkdir();
		}
		if (!UtilityService.removeDirectory(tmpDir)) {
			throw new DataUploadException("Cant delete the temp dir");
		}

		String tmpDirPath = uploadProps.getProperty("auto.dir")
				+ "\\temp";
		if(!new File(tmpDirPath).mkdir()){
			throw new DataUploadException(tmpDirPath+ " Dir not created.");
		}
		if(!new File(tmpDirPath + "\\2D").mkdir()){
			throw new DataUploadException(tmpDirPath + "\\2D"+ " Dir not created.");
		}
		
		if(!new File(tmpDirPath + "\\3D").mkdir()){
			throw new DataUploadException(tmpDirPath + "\\3D"+ " Dir not created.");
		}
		
		if(!new File(tmpDirPath + "\\image").mkdir()){
			throw new DataUploadException(tmpDirPath + "\\image"+ " Dir not created.");
		}

                if(!new File(tmpDirPath + "\\thumbnail").mkdir()){
			throw new DataUploadException(tmpDirPath + "\\thumbnail"+ " Dir not created.");
		}

	}*/

	private static void transferMedia(File sourceFile, File destFile)
			throws IOException {

		FileChannel ic = new FileInputStream(sourceFile).getChannel();
		FileChannel oc = new FileOutputStream(destFile).getChannel();
		ic.transferTo(0, ic.size(), oc);
		ic.close();
		oc.close();
	}

	@SuppressWarnings("unused")
	public static void uploadFiles(String scrMediaDir, String dstMediaDir) throws IOException {


		File mediaDir = new File(scrMediaDir);
		String[] list = mediaDir.list();
		if (list != null) {
			InputStream in = null;
			for (int i = 0; i < list.length; i++) {
				File media = new File(mediaDir, list[i]);
				File dstMedia = new File(dstMediaDir, list[i]);

				transferMedia(media, dstMedia);
			}

		}

	}
}
