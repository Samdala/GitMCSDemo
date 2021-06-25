package test.jWinAutoIt.src.main.myutils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;


/**
 * Resource utility for package and other class relative activities.
 * @author Michael.Goodnow
 */
public class ResourceUtil {
	//private static final Logger log = LoggerFactory.getLogger(ResourceUtil.class);
	
    /**
     * Gets the Package (Application) root path relative to utility class.
     * @return  the applications root path
     */
	public static String getPackagePath() {
	    return getPackagePath(null);
	}
	
    /**
     * Gets the Package (Application) root path relative to the class object.
     * @return  the applications root path
     */
    public static String getPackagePath(Class<?> classObject) {
        if (classObject == null) {
            classObject = ResourceUtil.class;
        }
    	String slash = System.getProperty("file.separator");
        String className = classObject.getName().replace('.', '/');
        String path = classObject.getResource("/" + className + ".class").toString();
        
        // Get Full Path
        String packageName = classObject.getPackage().getName().replace(".", "/");
        // Remove package name from path
        path = path.substring(0, path.indexOf(packageName) - 1);

        // If Jar file
        if (path.indexOf('!') != -1) {
            path = path.substring(0, path.lastIndexOf('/'));
        }
        
        //  Remove source "src", "bin", or "dist"
        if (path.endsWith("src") || path.endsWith("bin") || path.endsWith("dist")) {
            path = path.substring(0, path.lastIndexOf('/'));
        }
        
        // In case running from jar
        if (path.indexOf("jar:") != -1) {
            path = path.substring(4, path.length());
        }
        
        // In case console variant
        if (path.indexOf("file:") != -1) {
            path = path.substring(5, path.length());
        }
        
        // Windows
        if (path.startsWith("/C:/", 0)) {
            path = path.substring(1, path.length());
        }
        path = path.replace("/", slash);
       // log.debug("Package path: "+ path);
        return path;
    }
    
    /**
     * Determine if running from jar file
     * @return	{@code true} if running from a jar file
     */
    public static boolean isRunFromJar() {
    	String className = ResourceUtil.class.getName().replace('.', '/');
        String path = ResourceUtil.class.getResource("/" + className + ".class").toString();
      //  log.debug("Resource path = '{}'", path);
        if (path.startsWith("jar:")) {
        	return true;
        } else {
        	return false;
        }
    }
    
    /**
     * Get the jar name running from
     * @return	the jar file name, {@code empty} string if not running from a jar
     */
    public static String getJarName() {
    	String className = ResourceUtil.class.getName().replace('.', '/');
        String path = ResourceUtil.class.getResource("/" + className + ".class").toString();
        if (path.startsWith("jar:")) {
            String vals[] = path.split("/");
            for (String val: vals) {
            	if (val.contains("!")) {
            		return val.substring(0, val.length() - 1);
            	}
            }
        }
        return "";
    }
	
    /**
     * Read a properties file relative to a class in a java package.&nbsp;
     * Supports files in a Jar. 
     * @param filepath  the file path relative to the class (do not include a leading slash)
     * @param clazz     the class relative to, if {@code null} then anywhere on classpath
     * @return          a Properties set
     * @throws IOException  if resource cannot be found, or read error
     */
    public static Properties readToProperties(String filepath, Class<?> clazz) throws IOException {
        String errMsg;
        if (clazz == null) {
            errMsg = "Unable to read properties file in package (on classpath): "+ filepath;
        } else {
            errMsg = "Unable to read properties file in package (relative class <"+ clazz.getName() +">): "+ filepath;
        }
        InputStream inStream = null;
        Properties sets = new Properties();
        try {
            inStream = getInputStream(filepath, clazz);
            sets.load(inStream);
            inStream.close();
          //  log.debug("Successfully read properties='"+ filepath +"' in package");
    	} catch (IllegalArgumentException ex) {
        	throw new IOException(errMsg, ex);
    	} finally {
            try {
                inStream.close();
            } catch (Exception ex) {}
        }
        return sets;
    }
    
    /**
     * Read a file relative to a class in a java package.&nbsp;
     * Supports files in a Jar. 
     * @param filepath  the file path relative to the class (do not include a leading slash)
     * @param clazz     the class relative to, if {@code null} then anywhere on classpath
     * @return          a String of file content
     * @throws IOException  if resource cannot be found, or read error
     */
    public static String readToString(String filepath, Class<?> clazz) throws IOException {
        String errMsg;
        if (clazz == null) {
            errMsg = "Unable to read file in package (on classpath): "+ filepath;
        } else {
            errMsg = "Unable to read file file in package (relative class <"+ clazz.getName() +">): "+ filepath;
        }
        InputStream inStream = null;
        String str;
        try {
            inStream = getInputStream(filepath, clazz);
            int size = inStream.available();
            byte bytes[] = new byte[size];
            int sizeRead = inStream.read(bytes, 0, size);
            if (size != sizeRead) {
           //     log.error("Failure reading file in package: "+ filepath);
            }
            str = new String(bytes, "US-ASCII");
            inStream.close();
          //  log.debug("Successfully read file='"+ filepath +"' in package");
        } catch (NullPointerException ex) {
            throw new IOException(errMsg, ex);
        } catch (IndexOutOfBoundsException ex) {
            throw new IOException(errMsg, ex);
        } finally {
            try {
                inStream.close();
            } catch (Exception ex) {}
        }
        return str;
    }
    
    
    /**
     * Get the InputStream for a file in a package relative to the class.
     * @param resource  the file name relative to the class
     * @param clazz     the class relative to, if {@code null} then anywhere on classpath
     * @return          the InputStream
     * @throws IOException  if resource cannot be found
     */
    public static InputStream getInputStream(String resource, Class<?> clazz) throws IOException {
    	InputStream inStream;
    	if (clazz == null) {
    	    inStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
    	} else {
    	    inStream = clazz.getResourceAsStream(resource);
    	}
    	if (inStream == null) {
    	    String errMsg;
    	    if (clazz == null) {
    	        errMsg = "Unable to find resource in package (on classpath): "+ resource;
    	    } else {
    	        errMsg = "Unable to find resource in package (relative to class <"+ clazz.getName() +">): "+ resource;
    	    }
    	    throw new IOException(errMsg);
    	}
    	return inStream;
    }
    
    
    /**
     * Copy file out of resource to system's temporary directory.
     * @param filename  the file name relative to the class
     * @param clazz     the class relative to, if {@code null} then anywhere on classpath
     * @return          the absolute path to file in temporary directory
     * @throws IOException
     */
    public static String copyToTemp(String filename, Class<?> clazz) throws IOException {
        InputStream inputStream = getInputStream(filename, clazz);
        String tempDir = System.getProperty("java.io.tmpdir");
        File file = new File(tempDir, filename);
        if (file.exists() && file.isFile()) {
            file.delete();
        }
        OutputStream out = new FileOutputStream(file);
        byte buf[] = new byte[1024];
        int len;
        while ((len = inputStream.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        out.close();
        inputStream.close();
        return file.getAbsolutePath();
    }
    

    /**
     * Is OS Window.
     * @return  true if Windows
     */
    public static boolean isWindows(){
        String os = System.getProperty("os.name").toLowerCase();
        return (os.indexOf("win") >= 0); 
    }

    /**
     * Is OS Mac.
     * @return  true if Mac
     */
    public static boolean isMac(){
        String os = System.getProperty("os.name").toLowerCase();
        return (os.indexOf("mac") >= 0); 
    }

    /**
     * Is OS Unix or Linux.
     * @return  true if Unix or Linux
     */
    public static boolean isUnix(){
        String os = System.getProperty("os.name").toLowerCase();
        return (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0);
    }

    /**
     * Is system architecture 64 bit.
     * @return  true if x64
     */
    public static boolean is64() {
        String arch = System.getProperty("os.arch").toLowerCase();
        return (arch.indexOf("64") >= 0);
    }
    
    public static int getOSVersion() {
        String version = System.getProperty("os.version");
        version = version.replace(".", "");
        int i = 0;
        if (version.length() > 0) {
            try {
                i = Integer.parseInt(version.trim());
             } catch (NumberFormatException nfe) {
            //    log.error("Failed to get OS version :: "+ nfe.getMessage());
             }
        }
        return i;
    }
    
}
