package br.com.ifpb.AppWS.util;

import java.util.HashSet;
import java.util.Set;

public class ExistsPunctuationMarkAnalyser{

	public Set<String> analyse(String question) {
		Set<String> punctuationMark = new HashSet<String>();
		
		String questionWithOutPunctuation = question.replaceAll ("\\p{Punct}"," ");
		char[] charArray = question.toCharArray();
		char[] charArray2 = questionWithOutPunctuation.toCharArray();
		for (int i = 0; i < question.length(); i++) {
			if (charArray[i] != charArray2[i]) {
				punctuationMark.add(charArray[i] + "");
			}
		}
		
		return punctuationMark;
	}

}
