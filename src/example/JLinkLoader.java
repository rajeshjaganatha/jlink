/* 
  FILE: JLinkLoader.java

23-Aug-05  K-03-31  JCN  $$1  Submitted.

*/
package example;

/** 
 * This class statically loads the asynchronous J-Link native library.
 * Its separated from J-Link servlet implementation on purpose so that
 * Java does not attempt to load the library each time the servlet is run.
 * This class should be placed in the shared classpath for the servlet 
 * container.
 */


public class JLinkLoader
{
	
		static{
			System.out.println ("Loading library....");
			System.loadLibrary ("pfcasyncmt");
			System.out.println ("Done loading library.");
		}
		/*static{
		String[] libraries = ClassScope.getLoadedLibraries(ClassLoader.getSystemClassLoader());
		List<String> library = new ArrayList<String>();
		library.add("C:\\Program Files\\PTC\\Creo 2.0\\Common Files\\F001\\x86e_win64\\lib\\pfcasyncmt.dll");
		library.add("C:\\Program Files\\PTC\\Creo 2.0\\Common Files\\F001\\x86e_win64\\lib\\jnicipjavamtz.dll");
		String library ="C:\\Program Files\\PTC\\Creo 2.0\\Common Files\\F001\\x86e_win64\\lib\\pfcasyncmt.dll";
		String library1 = "C:\\Program Files\\PTC\\Creo 2.0\\Common Files\\F001\\x86e_win64\\lib\\jnicipjavamtz.dll";
		
		if (!Arrays.asList(libraries).contains(library))
			{
			System.out.println ("Loading library....");
			System.loadLibrary ("pfcasyncmt");
			System.out.println ("Done loading library.");
		}
	
}*/
	
}
