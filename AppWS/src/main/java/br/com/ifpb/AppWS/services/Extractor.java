package br.com.ifpb.AppWS.services;

import java.util.HashMap;
import java.util.Map;

import br.com.ifpb.AppWS.model.Vector;
import br.com.ifpb.AppWS.util.LuceneUtil;

public class Extractor {
	
	public Extractor() {
	}

	public static Vector vector(String text) {

		String[] stems = LuceneUtil.tokenizeString(new StringBuffer(text)).toString().split(" ");
		Map<String, Float> map = new HashMap<String, Float>();
		if (stems.length != 0) {
			float inc = (float) 1 / stems.length;
			for (int i = 0; i < stems.length; i++) {
				String stem = stems[i];
				Float freq = map.get(stem);
				if (freq == null) {
					map.put(stem, inc);
				} else {
					map.put(stem, freq + inc);
				}
			}
		}
		return new Vector(map);
	}
}
