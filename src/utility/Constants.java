package utility;

import java.util.Properties;

public class Constants {
	
	public static int SEARIAL_NO_IDX = 0;
	public static int ASSEMBLY_NO_IDX = 1;
	public static int THREE_D_FLAG_IDX = 5;
	public static int STILL_MEDIA_FILE_IDX = 8;
	public static int THREE_D_MEDIA_FILE_IDX = 7;
	
	
	public static int TWO_D_FLAG_IDX = 9;
	public static int TWO_D_MEDIA_FLAG_IDX = 11;
	
	public static int IMG_WIDTH_IDX = 12;
	public static int IMG_HIGHT_IDX = 13;
	
	public static int NO_OF_SHEETS_IDX = 14;
	public static int MEDIA_FILE_IDX = 16;
	public static int CSV_FILE_IDX = 17;
	
	
	public static int CSV_ENTRY_IDX = 0;
	public static int CSV_LEFT_CORD_IDX = 8;
	public static int CSV_TOP_CORD_IDX = 9;
	public static int CSV_RIGHT_CORD_IDX = 10;
	public static int CSV_BOTTOM_CORD_IDX = 11;
	
	public static final String PRMS_UPLOAD = "PRMS";
	public static final String E1_UPLOAD = "E1";
	
	public static void updateIdxRef(Properties uploadProps){
		
		SEARIAL_NO_IDX = Integer.parseInt(""+uploadProps.getProperty("searial.no.idx", ""+SEARIAL_NO_IDX));
		ASSEMBLY_NO_IDX = Integer.parseInt(""+uploadProps.getProperty("assembly.no.idx", ""+ASSEMBLY_NO_IDX));
		THREE_D_FLAG_IDX = Integer.parseInt(""+uploadProps.getProperty("three.d.flag.idx", ""+THREE_D_FLAG_IDX));
		STILL_MEDIA_FILE_IDX = Integer.parseInt(""+uploadProps.getProperty("still.media.file.idx", ""+STILL_MEDIA_FILE_IDX));
		THREE_D_MEDIA_FILE_IDX = Integer.parseInt(""+uploadProps.getProperty("three.d.media.file.idx", ""+THREE_D_MEDIA_FILE_IDX));
		TWO_D_FLAG_IDX = Integer.parseInt(""+uploadProps.getProperty("two.d.flag.idx", ""+TWO_D_FLAG_IDX));
		TWO_D_MEDIA_FLAG_IDX = Integer.parseInt(""+uploadProps.getProperty("two.d.media.flag.idx", ""+TWO_D_MEDIA_FLAG_IDX));
		IMG_WIDTH_IDX = Integer.parseInt(""+uploadProps.getProperty("img.width.idx", ""+IMG_WIDTH_IDX));
		IMG_HIGHT_IDX = Integer.parseInt(""+uploadProps.getProperty("img.hight.idx", ""+IMG_HIGHT_IDX));
		NO_OF_SHEETS_IDX = Integer.parseInt(""+uploadProps.getProperty("no.of.sheets.idx", ""+NO_OF_SHEETS_IDX));
		MEDIA_FILE_IDX = Integer.parseInt(""+uploadProps.getProperty("media.file.idx", ""+MEDIA_FILE_IDX));
		CSV_FILE_IDX = Integer.parseInt(""+uploadProps.getProperty("csv.file.idx", ""+CSV_FILE_IDX));
		
		CSV_ENTRY_IDX = Integer.parseInt(""+uploadProps.getProperty("csv.entry.idx", ""+CSV_ENTRY_IDX));
		CSV_LEFT_CORD_IDX = Integer.parseInt(""+uploadProps.getProperty("csv.left.cord.idx", ""+CSV_LEFT_CORD_IDX));
		CSV_TOP_CORD_IDX = Integer.parseInt(""+uploadProps.getProperty("csv.top.cord.idx", ""+CSV_TOP_CORD_IDX));
		CSV_RIGHT_CORD_IDX = Integer.parseInt(""+uploadProps.getProperty("csv.right.cord.idx", ""+CSV_RIGHT_CORD_IDX));
		CSV_BOTTOM_CORD_IDX = Integer.parseInt(""+uploadProps.getProperty("csv.bottom.cord.idx", ""+CSV_BOTTOM_CORD_IDX));
		
	}

}
