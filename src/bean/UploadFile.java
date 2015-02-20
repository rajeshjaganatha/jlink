// Import required java libraries
package bean;

import java.io.*;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.fileupload.FileUploadException;
import utility.*;

public class UploadFile extends HttpServlet {


	private boolean isMultipart;
	public static String filePathfromClient = null;
	public static String filePathtoClient = null;

	private int maxFileSize = 50 * 1024;
	private int maxMemSize = 4 * 1024;
	public static File file ;
	public static String filewithpath ;
	public static String fileType;
	public static List<String> fileList;


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		Properties prop = new Properties();
		prop.load(this.getClass().getClassLoader().getResourceAsStream("/config.properties"));
		filePathfromClient = (String) prop.get("fromClient");
		filePathtoClient = (String) prop.get("toClient");

		System.out.println(filePathfromClient+"    "+filePathtoClient);
		// Check that we have a file upload request
		isMultipart = ServletFileUpload.isMultipartContent(request);
		response.setContentType("text/html");
		java.io.PrintWriter out = response.getWriter();
		if (!isMultipart) {
			System.out.println("No File uploaded");
			return;
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// maximum size that will be stored in memory
		// factory.setSizeThreshold(maxMemSize);
		// Location to save data that is larger than maxMemSize.
		factory.setRepository(new File("c:\\temp"));

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		// maximum file size to be uploaded.
		// upload.setSizeMax( maxFileSize );
		//

		try {
			List<FileItem> fi = new ServletFileUpload(new DiskFileItemFactory())
			.parseRequest(request);
			for (FileItem item : fi) {
				if (item.isFormField()) {

					if (item.getFieldName().equals("fileType")) {
						fileType = item.getString();
					}
				} else {
					// Get the uploaded file parameters
					String fieldName = item.getFieldName();
					String fileName = item.getName();
					String contentType = item.getContentType();
					boolean isInMemory = item.isInMemory();
					long sizeInBytes = item.getSize();
					// Write the file
					if (fileName.lastIndexOf("\\") >= 0) {
						file = new File(
								filePathfromClient
								+ fileName.substring(fileName
										.lastIndexOf("\\")));
					} else {
						file = new File(
								filePathfromClient
								+ fileName.substring(fileName
										.lastIndexOf("\\") + 1));
					}
					try {
						item.write(file);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					out.println("Uploaded Filename: " + fileName + "<br>");
				}
			}
		} catch (FileUploadException e) {
			throw new ServletException("Cannot parse multipart request.", e);
		}

		filewithpath = FilenameUtils.removeExtension(file.getPath());
		System.out.println("Before unzip file path: " + filewithpath);
		System.out.println("Before Unzip source folder: " + filePathfromClient);

		if (!fileType.equals("part")) {
			UnZip unZip = new UnZip();
			String zipFile = file.getPath();
			filePathfromClient = filePathfromClient
					+ FilenameUtils.removeExtension(file.getName());
			fileList = unZip.unZipIt(zipFile, filePathfromClient);
			filewithpath = filewithpath + File.separator
					+ FilenameUtils.removeExtension(file.getName());

			System.out.println("After unzip source folder: " + filePathfromClient);
			System.out.println("AFter unzip file path: " + filewithpath);
			System.out.println("File type: " + fileType);

			request.getSession().setAttribute("FileList", fileList);
			String nextJSP = "/FileSelection.jsp";
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher(nextJSP);
			dispatcher.forward(request, response);

		}else {
			request.getSession().setAttribute("action", filewithpath);
			String nextJSP = "/openfile.do";
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher(nextJSP);
			dispatcher.forward(request, response);
		}

	}
	public void doGet(HttpServletRequest request, 
			HttpServletResponse response)
					throws ServletException, java.io.IOException {

		throw new ServletException("GET method used with " +
				getClass( ).getName( )+": POST method required.");
	} 

}