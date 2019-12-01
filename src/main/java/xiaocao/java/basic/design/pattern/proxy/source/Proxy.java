package xiaocao.java.basic.design.pattern.proxy.source;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class Proxy {

	public static Object getObjectProxy(Class<?> inter,
			InvocationHandler handler) throws Exception {
		String methodStr = "";
		String rt = "\r\n";
		Method[] methods = inter.getMethods();
		for (Method m : methods) {
			methodStr += "@Override" + rt + 
			             "public void " + m.getName() + "(){" +  rt + 
					     "try{" + rt + 
					     " Method md = " + inter.getName() + ".class.getMethod(\"" + m.getName() + "\");" + rt + 
					     " handler.invoike(this,md);" + rt
					   + "}catch(Exception e){" + rt + "e.printStackTrace();" + rt
					+ "}" + rt + "}";
		}

		String src = "package xiaocao.learn.java.design.pattern.proxy.source;" + "\r\n"
				+ "import java.lang.reflect.*;" + rt
				+ "public class TankTimeProxy implements " + inter.getName()
				+ " {" + rt
				+ " xiaocao.learn.java.design.pattern.proxy.source.InvocationHandler handler;" + rt
				+ "  public TankTimeProxy(InvocationHandler handler) {" + rt
				+ "     super();" + rt + " this.handler = handler;" + rt
				+ "  }" + rt + methodStr + "}";
		String fileName = "F:/workspace/eclipse_workspace/xiaocao.learn.java/src/xiaocao/learn/java/design/pattern/proxy/source/TankTimeProxy.java";
		File f = new File(fileName);
		FileWriter fw = new FileWriter(f);
		fw.write(src);
		fw.flush();
		fw.close();
		// compiler
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(
				null, null, null);
		Iterable units = fileManager.getJavaFileObjects(fileName);
		CompilationTask task = compiler.getTask(null, fileManager, null, null,
				null, units);
		task.call();
		fileManager.close();
		// load into memory and create an instance
		URL urls[] = new URL[] { new URL("file:/" + "F:/workspace/eclipse_workspace/xiaocao.learn.java/src") };
		URLClassLoader classLoader = new URLClassLoader(urls);
		Class c = classLoader.loadClass("xiaocao.learn.java.design.pattern.proxy.source.TankTimeProxy");
		Constructor constructor = c.getConstructor(InvocationHandler.class);
		Object m = constructor.newInstance(handler);
		return m;
	}

}
