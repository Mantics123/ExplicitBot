package explicit.utils;

import java.util.List;

public class StringUtils {

	public static String combine(List<String> list, String between) {
		StringBuilder sb = new StringBuilder();
		
		for (String str : list) {
			sb.append(str);
			sb.append(between);
		}
		
		return sb.toString();
	}
	
	public static boolean contains(List<String> list, String phrase, boolean ignoreCase) {
		for  (String str : list) {
			if (ignoreCase && str.toLowerCase().contains(phrase.toLowerCase())) {
				return true;
			} else if (!ignoreCase && str.contains(phrase)) {
				return true;
			}
		}
		return false;
	}
	
	public static int getIndex(List<String> list, String phrase, boolean ignoreCase) {
		for  (int k = 0; k < list.size(); k++) {
			String str = list.get(k);
			if (ignoreCase && str.toLowerCase().contains(phrase.toLowerCase())) {
				return k;
			} else if (!ignoreCase && str.contains(phrase)) {
				return k;
			}
		}
		return -1;
	}
}
