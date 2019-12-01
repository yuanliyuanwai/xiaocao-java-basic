package xiaocao.java.basic.design.pattern.builder;

import java.util.HashMap;
import java.util.Map;

public class Food {
	
	private Map<String, Double> foodMap = new HashMap<String, Double>();
	
	public void add(String strName, double price) {
		foodMap.put(strName, price);
	}
	
	public void show() {
		System.out.println("Food List:");
		System.out.println("---------------");
		for(Map.Entry<String, Double> entry : foodMap.entrySet()) {
			System.out.println(entry.getKey() + "/" + entry.getValue());
		}
		System.out.println("\n-------------");
	}
	

}
