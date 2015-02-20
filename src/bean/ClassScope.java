package bean;

import java.lang.ClassLoader;
import java.lang.reflect.Field;
import java.util.Vector;

public class ClassScope {
    private static Field LIBRARIES = null;
    static {
        try {
			LIBRARIES = ClassLoader.class.getDeclaredField("loadedLibraryNames");
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        LIBRARIES.setAccessible(true);
    }
    @SuppressWarnings("unchecked")
	public static String[] getLoadedLibraries(final ClassLoader loader)   {
        Vector<String> libraries = null;
		try {
			libraries = (Vector<String>) LIBRARIES.get(loader);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return libraries.toArray(new String[] {});
    }
}
