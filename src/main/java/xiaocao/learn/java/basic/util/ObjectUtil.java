package xiaocao.learn.java.basic.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * class公共处理类
 * @author
 */
public class ObjectUtil {

    /**
     * 从jar包中获取第一个子类
     * 
     * @param <T>
     * @param jarPath jar包的路径,不含文件分隔符
     * @param jarName jar包名称
     * @param superClazz 父类
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T extends Object> T getFirstSubClass(String jarPath, String jarName, Class<T> superClazz) {
        JarFile jarFile;
        T t = null;
        try {
            jarFile = new JarFile(jarPath + File.separator + jarName);
        } catch (IOException e) {
        	e.printStackTrace();
            return null;
        }
        Enumeration<JarEntry> jarEntries = jarFile.entries();
        while (jarEntries.hasMoreElements()) {
            JarEntry jarEntry = jarEntries.nextElement();
            String name = jarEntry.getName();
            if (name.endsWith(".class") && !name.contains("$")) {
                String className = name.replace("/", ".").substring(0, name.lastIndexOf("."));
                try {
                    Class<?> c = Class.forName(className);
                    if (c.getSuperclass() == superClazz) {
                        t = (T) c.newInstance();
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }
            }
        }
        try {
            jarFile.close();
        } catch (IOException e) {
            jarFile = null;
        }

        return t;
    }

    /**
     * 从jar包中查找并加载第一个实现类,实现类需要带上默认的构造器
     * 
     * @param <T>
     * @param jarPath jar包的路径,不含文件分隔符
     * @param jarName jar包名称
     * @param interfaceClazz 接口
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T extends Object> T getFirstImpl(String jarPath, String jarName, Class<T> interfaceClazz) {
        JarFile jarFile;
        T t = null;
        try {
            jarFile = new JarFile(jarPath + File.separator + jarName);
        } catch (IOException e) {
        	e.printStackTrace();
            return null;
        }
        Enumeration<JarEntry> jarEntries = jarFile.entries();
        while (jarEntries.hasMoreElements()) {
            JarEntry jarEntry = jarEntries.nextElement();
            String name = jarEntry.getName();
            if (name.endsWith(".class") && !name.contains("$")) {
                String className = name.replace("/", ".").substring(0, name.lastIndexOf("."));
                try {
                    Class<?> c = Class.forName(className);
                    for (Class<?> clazz : c.getInterfaces()) {
                        if (clazz == interfaceClazz) {
                            t = (T) c.newInstance();
                            break;
                        }
                    }
                } catch (Exception e) {
                    continue;
                }
            }
        }
        try {
            jarFile.close();
        } catch (IOException e) {
            jarFile = null;
        }
        return t;
    }

    /**
     * 从jar包中查找加载响应的类
     * 
     * @param <T>
     * @param jarPath jar包的路径
     * @param jarName jar包名称
     * @param superClazz 父类
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T extends Object> T loadObject(String jarPath, String jarName, Class<T> superClazz) {
        try {
            JarFile jarFile = new JarFile(jarPath + File.separator + jarName);
            Enumeration<JarEntry> jarEntries = jarFile.entries();
            while (jarEntries.hasMoreElements()) {
                JarEntry jarEntry = jarEntries.nextElement();
                String name = jarEntry.getName();
                if (name.endsWith(".class") && !name.contains("$")) {
                    String className = name.replace("/", ".").substring(0, name.lastIndexOf("."));
                    try {
                        Class<?> c = Class.forName(className);
                        if (c.getSuperclass() == superClazz) {
                            return (T) c.newInstance();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从jar包中查找加载所有相应的类
     * 
     * @param <T>
     * @param jarPath jar包的路径
     * @param jarName jar包名称
     * @param superClazz 父类
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T extends Object> List<T> loadAll(String jarPath, String jarName, Class<T> superClazz) {
        List<T> list = new ArrayList<T>();
        try {
            JarFile jarFile = new JarFile(jarPath + File.separator + jarName);
            Enumeration<JarEntry> jarEntries = jarFile.entries();
            while (jarEntries.hasMoreElements()) {
                JarEntry jarEntry = jarEntries.nextElement();
                String name = jarEntry.getName();
                if (name.endsWith(".class")) {
                    String className = name.replace("/", ".").substring(0, name.lastIndexOf("."));
                    try {
                        Class<?> c = Class.forName(className);
                        if (c.getSuperclass() == superClazz) {
                            list.add((T) c.newInstance());
                        }
                    } catch (Exception e) {
                    	e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

}
