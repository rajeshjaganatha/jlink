package bean;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.io.FilenameUtils;
import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcModelItem.Parameters;
import com.ptc.pfc.pfcModelItem.pfcModelItem;


import bean.AsyncInstallTest;
import bean.UploadFile;
import utility.*;


public class NewValues extends HttpServlet{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static String errorMessage = null;



	@SuppressWarnings("unused")
	public void doPost(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		
	
		ArrayList<ParameterTable> parametersList = AsyncInstallTest.parametersList; 
						
		Parameters parameters = AsyncInstallTest.parameters;
		
		try {
			for (int i = 0; i < parameters.getarraysize(); i++) {
				switch (parameters.get(i).GetValue().Getdiscr().getValue()) {
				case 0:
					parameters.get(i).SetValue(pfcModelItem.CreateStringParamValue(request.getParameter(parametersList.get(i).getName())));
					break;

				case 1:
					parameters.get(i).SetValue(pfcModelItem.CreateIntParamValue(Integer.valueOf(request.getParameter(parametersList.get(i).getName()))));					
					break;

				case 2:
					parameters.get(i).SetValue(pfcModelItem.CreateBoolParamValue(Boolean.valueOf(request.getParameter(parametersList.get(i).getName()))));					
					break;

				case 3:
					parameters.get(i).SetValue(pfcModelItem.CreateDoubleParamValue(Double.valueOf(request.getParameter(parametersList.get(i).getName()))));					
					break;

				default:
					break;
				}
			}
		} catch (jxthrowable e) {
			e.printStackTrace();
		} catch (NumberFormatException e){
			System.out.println("Number Format not correct");
			request.getSession().setAttribute("Error", "Please enter only numbers");
			response.sendRedirect(request.getHeader("Referer"));
		       
		       
}

    	
    	System.runFinalization();
       try {
		Boolean status = AsyncInstallTest.unloadModel(AsyncInstallTest.proeModel, AsyncInstallTest.currSession);
	} catch (jxthrowable e) {
		e.printStackTrace();
	}
       
       if (!UploadFile.fileType.equals("part")) {
		
        Zip zip = new Zip();
     	Zip.generateFileList(new File(UploadFile.filePathfromClient), UploadFile.filePathfromClient);
     	zip.zipIt(UploadFile.filePathtoClient+(FilenameUtils.removeExtension(UploadFile.file.getName()))+".zip", UploadFile.filePathfromClient);
		
	}

       System.out.println("AsyncInstallTest finished.");
       request.getSession().setAttribute("NewFilename", AsyncInstallTest.newFilename);
       String nextJSP = "/result.jsp";
       RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
       dispatcher.forward(request,response);
       
	}

}
