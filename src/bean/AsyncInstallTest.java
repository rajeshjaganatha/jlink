package bean;

import com.ptc.cipjava.*;
import com.ptc.pfc.pfcSession.*;
import com.ptc.pfc.pfcSolid.Solid;
import com.ptc.pfc.pfcModel.*;
import com.ptc.pfc.pfcModelItem.ModelItem;
import com.ptc.pfc.pfcModelItem.ModelItemType;
import com.ptc.pfc.pfcModelItem.ModelItems;
import com.ptc.pfc.pfcModelItem.Parameters;
import com.ptc.pfc.pfcAssembly.Assembly;
import com.ptc.pfc.pfcAsyncConnection.*;
import com.ptc.pfc.pfcExceptions.XToolkitBadInputs;
import com.ptc.pfc.pfcExceptions.XToolkitError;
import com.ptc.pfc.pfcExport.AssemblyConfiguration;
import com.ptc.pfc.pfcExport.CatiaProduct3DExportInstructions;
import com.ptc.pfc.pfcExport.Export3DInstructions;
import com.ptc.pfc.pfcExport.GeometryFlags;
import com.ptc.pfc.pfcExport.JT3DExportInstructions;
import com.ptc.pfc.pfcExport.PDFExportInstructions;
import com.ptc.pfc.pfcExport.pfcExport;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;

import example.*;
import bean.UploadFile;





public class AsyncInstallTest extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private static JLinkLoader loader = new JLinkLoader();

    @SuppressWarnings("unused")
	private static String proe_cmd = "";
    
    public static Model proeModel = null;
    
    public static Boolean status = null;
    
    public static String newFilename = null;
    
    public static final String Check_Parameter = "JLINK_TRACKER_CREATION_PARAM";
    
    public static ArrayList<ParameterTable> parametersList = new ArrayList<ParameterTable>();
    
    public static Session currSession = null;
    
    public static Parameters parameters = null;

    public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
  	//public static ArrayList<ParameterTable> GetParameters (String filewithpath){
    		
/*        if (args.length != 1) {
            System.out.println("Wrong number of arguments");
            System.out.println("Usage: java AsyncInstallTest <Pro/ENGINEER startup command&gt;");
            System.exit(1);
        } else {
            //proe_cmd = args[0];
            proe_cmd = "D:\\Proe_R&D\\jlink\\jlinkapp\\Creo.lnk";
            System.out.println(proe_cmd);
        }
*/
        try {
        	String filewithpath;
            System.out.println("AsyncInstallTest started.");
            System.out.println(request.getParameter("action"));
            if (!UploadFile.fileType.equals("part")){
            filewithpath = UploadFile.filePathfromClient+File.separator+request.getParameter("action");
            System.out.println("File with Path: "+filewithpath);
            }else {
				filewithpath = UploadFile.filewithpath;
				System.out.println("File with Path: "+filewithpath);
			}

            AsyncConnection ac = null;

            //AsyncConnection ac = pfcAsyncConnection.AsyncConnection_Start("D:\\Proe R&D\\jlink\\jlinkapp\\Creo", null);
            
            //AsyncConnection ac = pfcAsyncConnection.AsyncConnection_Start(proe_cmd, null);
            try{
            	ac = pfcAsyncConnection.AsyncConnection_Connect(null, null, null, null);
                
            }catch (UnsatisfiedLinkError e) {
            	System.out.println("error came here");
				return;
			}
            
           currSession = ac.GetSession();
            //Model model = currSession.CreatePart("models\\test1");
            //Model model = currSession.GetModel("test1", ModelType.MDL_PART);
            //String ModelName = FilenameUtils.removeExtension(UploadFile.file.getPath());
            //String ModelName = "C:\\Users\\101107\\Desktop\\check";
            status = loadModel(filewithpath, currSession);

            
            parameters = proeModel.ListParams();
            
            parametersList.clear();
        	for (int i = 0; i < parameters.getarraysize(); i++) {
        		
        		switch (parameters.get(i).GetValue().Getdiscr().getValue()) {
        		
				case 0:
					ParameterTable pt0 = new ParameterTable();
					pt0.setName(parameters.get(i).GetName());
					pt0.setValue(parameters.get(i).GetValue().GetStringValue());
					pt0.setType(parameters.get(i).GetValue().Getdiscr().getValue());
					parametersList.add(pt0);
					break;

				case 1:
					ParameterTable pt1 = new ParameterTable();
					pt1.setName(parameters.get(i).GetName());
					pt1.setValue(String.valueOf(parameters.get(i).GetValue().GetIntValue()));
					pt1.setType(parameters.get(i).GetValue().Getdiscr().getValue());
					parametersList.add(pt1);
					break;

				case 2:
					ParameterTable pt2 = new ParameterTable();
					pt2.setName(parameters.get(i).GetName());
					pt2.setValue(String.valueOf(parameters.get(i).GetValue().GetBoolValue()));
					pt2.setType(parameters.get(i).GetValue().Getdiscr().getValue());
					parametersList.add(pt2);
					break;

				case 3:
					ParameterTable pt3 = new ParameterTable();
					pt3.setName(parameters.get(i).GetName());
					pt3.setValue(String.valueOf(parameters.get(i).GetValue().GetDoubleValue()));
					pt3.setType(parameters.get(i).GetValue().Getdiscr().getValue());
					parametersList.add(pt3);
					break;

				default:
					break;
				}
			}

            //Thread.sleep(5000);         
           // ac.End();

        } catch (Throwable x) {
            System.out.println("Unexpected exception caught: " + x);
            x.printStackTrace();
        }
		request.getSession().setAttribute("ParametersList", parametersList);
		request.getSession().setAttribute("NewFilename",	AsyncInstallTest.newFilename);
		String nextJSP = "/result.jsp";
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(nextJSP);
		dispatcher.forward(request, response);
    }
    


	@SuppressWarnings("unused")
	private static void getInstanceType(Object objValue) {
    	if (objValue instanceof Integer) {
    		System.out.println(Integer.parseInt(objValue.toString()));
		}else if(objValue instanceof String) {
			System.out.println(objValue.toString());
		}else {
			System.out.println("Not found");
		}
		
	}

	public static boolean loadModel(String filewithpath, Session currSession){
		ModelType mdl_type;
		if (!UploadFile.fileType.equals("part")) {
			mdl_type = ModelType.MDL_ASSEMBLY;
		}else {
			mdl_type = ModelType.MDL_PART;
		}
        
        
        try{
        	
          ModelDescriptor proeModelDescriptor= pfcModel.ModelDescriptor_Create(mdl_type, filewithpath, "");
          //ModelDescriptor proeModelDescriptor= pfcModel.ModelDescriptor_Create(mdl_type, "D:\\Proe_R&D\\jlink\\jlinkapp\\media\\fromClient\\y.asm\\y.asm", "");
      	//ModelDescriptor proeModelDescriptor=pfcModel.ModelDescriptor_CreateFromFileName(sampleName);
        	System.out.println("Created model descriptor: "+(filewithpath)+ " type: "+mdl_type.getValue());
        	System.out.println("Full Name: "+proeModelDescriptor.GetFullName());
        	System.out.println("Path: "+proeModelDescriptor.GetPath());
        	
        	proeModel = currSession.RetrieveModel(proeModelDescriptor);
        	proeModel.Display();
            System.out.println("Model was retrieved)");
/*            JOptionPane.showMessageDialog ( null, "Checking",
                    "JResult", JOptionPane.ERROR_MESSAGE ); */
        }
        catch(jxthrowable x){
          System.out.println("pfcException: ");
          x.printStackTrace ();
          return false;
        }
        return true;
      }
	
    public static boolean unloadModel(Model proeModel, Session currSession) throws jxthrowable {
    	Solid proeSolid = (Solid)proeModel;
    	proeSolid.Regenerate(null);
    	proeModel.Save();
    	if(!UploadFile.fileType.equals("part")){
    		newFilename=FilenameUtils.removeExtension(UploadFile.file.getName())+".zip";
    	}else {
    		File tempFilename = new File(proeModel.GetOrigin());
    		newFilename = tempFilename.getName();
    	}
    	
    	GeomExportFlags gef = pfcModel.GeomExportFlags_Create();
    	//AssemblyConfiguration ac =null;
    	GeometryFlags gf = null ;
    	String cosys = null;
    	ModelItems cosyss = proeModel.ListItems(ModelItemType.ITEM_COORD_SYS);
    	int size = cosyss.getarraysize();
    	for (int i = 0; i < size; i++) {
			cosys = cosyss.get(i).GetName();
		}
    	
    	STEPExportInstructions stepinst = pfcModel.STEPExportInstructions_Create(gef);
    	IGES3DExportInstructions igesinst = pfcModel.IGES3DExportInstructions_Create(gef);
    	CatiaProduct3DExportInstructions catinst = pfcExport.CatiaProduct3DExportInstructions_Create(AssemblyConfiguration.EXPORT_ASM_ASSEMBLY_PARTS, gf);
    	JT3DExportInstructions jtinst1 = pfcExport.JT3DExportInstructions_Create(AssemblyConfiguration.EXPORT_ASM_MULTI_FILES, gf);
    	PDFExportInstructions pdfinst = pfcExport.PDFExportInstructions_Create();
    	//proeModel.Export("assy.igs", igesinst);
    	//proeModel.Export("assy.stp", stepinst);
    	//proeModel.Export("assy.catproduct", catinst);
    	//proeModel.Export("assy1.jt", jtinst1);
    	proeModel.Export("asse.pdf", pdfinst);
    	System.out.println("new file name:"+newFilename);
    	proeModel.EraseWithDependencies();
    	Purge();
    	return true;
	}
	
public static boolean Purge() {
		
		String path = "cmd /c start D:\\Proe_RnD\\jlink\\jlinkapp\\callpurge.bat";
		try {
		
			Runtime rn = Runtime.getRuntime();
			//Runtime.getRuntime().exec("cmd /c start purge.bat");
			@SuppressWarnings("unused")
			Process pr = rn.exec(path);
		       try {
		   		Thread.sleep(2000);
		   	} catch (InterruptedException e) {
		   		e.printStackTrace();
		   	}
			System.out.println("Purging complete");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
}
}