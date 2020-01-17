package xiaocao.learn.java.basic.util;

import java.io.File;
import java.net.URLDecoder;

public class PathUtil {

	public static String getPath() {
		try {
			String invokeClassName = Thread.currentThread().getStackTrace()[2].getClassName();
			Class<?> invokeClazz = Class.forName(invokeClassName);
			String path = URLDecoder.decode(invokeClazz.getProtectionDomain().getCodeSource().getLocation().getFile(), "utf-8");
			path = path.replace("/", File.separator);
			if (path.endsWith(File.separator)) {
				path = path.substring(0, path.length() - 1);
			}
			if (path.startsWith("file:")) {
				path = path.substring(5);
			}
			if (path.endsWith(".jar")) {
				return path.substring(0, path.lastIndexOf(File.separator));
			}
			if (path.endsWith(".class")) {
				String splitStr = "classes";
				return path.substring(0, path.indexOf(splitStr) + splitStr.length());
			}
			return path;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

}
