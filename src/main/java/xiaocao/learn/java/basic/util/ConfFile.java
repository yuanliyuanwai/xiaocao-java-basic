package xiaocao.learn.java.basic.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class ConfFile {
	
	/**
	 * 分割符
	 */
	public static final String SPLIT_SIGN = "##~!@#$%^&*~$#@";
	
	/**
	 * 空格
	 */
	public static final String BLANK_STRING = " ";
	
	/**
	 * 换行符
	 */
	public static final String SWAP_LINE = "\r\n";
	
	/**
	 * 使用#号注释过的行
	 */
	private Map<Integer, String> annotationMap = new HashMap<Integer, String>();
	
	/**
	 * 真正的配置行
	 * 一个键对应多行
	 * 一行对应一个键和多个值
	 */
	private Map<String, List<List<String>>> confMap = new LinkedHashMap<String, List<List<String>>>();
	
	/**
	 * 是否对配置做过修改
	 */
	private boolean isModify = false;
	
	/**
	 * 指定的编解码
	 */
	private String charsetName = "utf-8";
	
	/**
	 * 文件全路径
	 */
	private String fileAbsolutePath;
	
	public ConfFile(String fileAbsolutePath) {
		this.fileAbsolutePath = fileAbsolutePath;
	}
	
	public void load() throws IOException {
		FileInputStream inputStream = null;
		InputStreamReader streamReader = null;
		BufferedReader bufReader = null;
		try{
			inputStream = new FileInputStream(fileAbsolutePath);
			streamReader = new InputStreamReader(inputStream, charsetName);
			bufReader = new BufferedReader(streamReader);
			int lineNo = -1; // 行号
			String line = ""; // 行的内容
			while((line = bufReader.readLine()) != null) {
				lineNo++;
				if(line == null || line.trim().length() == 0 || line.trim().startsWith("#")) {
					annotationMap.put(lineNo, line);
					continue;
				}
				List<String> row = new ArrayList<String>(Arrays.asList(line.split("\\s+")));
				String key = row.remove(0);
				if(confMap.get(key) != null) {
					confMap.get(key).add(row);
				} else {
					List<List<String>> rows = new ArrayList<List<String>>();
					rows.add(row);
					confMap.put(key, rows);
				}
				annotationMap.put(lineNo, SPLIT_SIGN + key);
			}
		} finally {
			closeStream(inputStream, streamReader, bufReader);
		}
		
	}
	
	/**
	 * 增加配置行
	 * @param key
	 * @param values
	 */
	public void addConfByLine(String key, List<String> values) {
		List<List<String>> rows = confMap.get(key);
		if(rows == null) {
			rows = new ArrayList<List<String>>();
			rows.add(values);
			confMap.put(key, rows);
		} else {
			rows.add(values);
		}
		isModify = true;
	}
	
	/**
	 * 删除某个键的所有的配置
	 * @param key
	 */
	public void deleteConf(String key) {
		confMap.put(key, new ArrayList<List<String>>());
		isModify = true;
	}
	
	/**
	 * 删除配置行
	 * @param key 键
	 * @param index 第几个键值行：可能一个键对应多行
	 */
	public void deleteConfByLine(String key, int index) {
		List<List<String>> rows = confMap.get(key);
		rows.remove(index);
		isModify = true;
	}
	
	/**
	 * 更新配置行
	 * @param key 键
	 * @param index 第几个键值行：可能一个键对应多行
	 * @param values 改行的值
	 */
	public void updateConfByLine(String key, int index, List<String> values) {
		List<List<String>> rows = confMap.get(key);
		rows.set(index, values);
		isModify = true;
	}
	
	/**
	 * 查询配置行
	 * @param key 键
	 * @param index 第几个键值行：可能一个键对应多行
	 */
	public List<String> getConfByLine(String key, int index) {
		List<List<String>> rows = confMap.get(key);
		return rows.get(index);
	}
	
	/**
	 * 得到某个键的所有的配置信息
	 * @param key
	 * @return
	 */
	public List<List<String>> getAllConf(String key) {
		return confMap.get(key);
	}
	
	/**
	 * 得到所有的配置信息
	 * 以保留先后顺序
	 * @return
	 */
	public Map<String, List<List<String>>> getAllConf() {
		return new LinkedHashMap<String, List<List<String>>>(confMap);
	}
	
	
	
	/**
	 * 对conf文件进行保存
	 */
	public void save() throws IOException {
		if(!isModify) return;
		String confContent = getContent();
		FileOutputStream outputStream = null;
		OutputStreamWriter streamWriter = null;
		BufferedWriter writer = null;
		try{
			outputStream = new FileOutputStream(fileAbsolutePath);
			streamWriter = new OutputStreamWriter(outputStream, charsetName);
			writer = new BufferedWriter(streamWriter);
			writer.write(confContent);
			writer.flush();
		} finally {
			closeStream(outputStream, streamWriter, writer);
		}
		
	}
	
	/**
	 * 集中关闭流资源
	 * @param streams
	 */
	private void closeStream(Closeable... streams) {
		for(Closeable stream : streams) {
			try{
				stream.close();
			} catch (Exception e) {
				stream = null;
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 得到某一行的内容
	 * @param key
	 * @param values
	 * @return
	 */
	private String getLine(String key, List<String> values) {
		StringBuffer buffer = new StringBuffer(key);
		buffer.append(BLANK_STRING);
		for(String value : values) {
			buffer.append(value).append(BLANK_STRING);
		}
		buffer.deleteCharAt(buffer.length() - 1).append(SWAP_LINE);
		return buffer.toString();
	}
	
	/**
	 * 得到文本的全部内容
	 * @return
	 */
	private String getContent() {
		Map<String, List<List<String>>> map = new LinkedHashMap<String, List<List<String>>>(confMap);
		StringBuffer contentBuffer = new StringBuffer();
		
		for(int i = 0; i < annotationMap.size(); i++) {
			String line = annotationMap.get(i);
			if(!line.startsWith(SPLIT_SIGN)) {
				contentBuffer.append(line).append(SWAP_LINE);
			} else{
				String key = line.substring(SPLIT_SIGN.length());
				List<List<String>> rows = map.remove(key);
				//该key已经被删除或者已经在第一次出现出集中输出
				if(rows == null || rows.isEmpty()) continue; 
				for(List<String> row : rows) {
					contentBuffer.append(getLine(key, row));
				}
			}
		}
		
		// 处理新增的配置信息
		for(Map.Entry<String, List<List<String>>> entry : map.entrySet()) {
			String key = entry.getKey();
			List<List<String>> rows = entry.getValue();
			for(List<String> row : rows) {
				contentBuffer.append(getLine(key, row));
			}
		}
		return contentBuffer.toString();
	}
	
	public String getCharsetName() {
		return charsetName;
	}

	/**
	 * 设置编码，默认utf-8编码
	 * @param charsetName
	 */
	public void setCharsetName(String charsetName) {
		this.charsetName = charsetName;
	}
	
}
