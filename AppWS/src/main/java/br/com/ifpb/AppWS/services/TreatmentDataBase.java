package br.com.ifpb.AppWS.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;

import br.com.ifpb.AppWS.model.InQuestion;

public class TreatmentDataBase {
	
	public static final Map<String, List<InQuestion>> TEST_BASE = selectTestBaseByTags(0.3);
	
	public TreatmentDataBase() {
	}
	
	public static Map<String, List<InQuestion>> selectTestBaseByTags(double testPorc) {
		Map<String, List<InQuestion>> testBaseByTags = new HashMap<String, List<InQuestion>>();

		Map<String, List<InQuestion>> listQuestionsByTags = questionsByTags();
		Set<String> tags = listQuestionsByTags.keySet();
		for (String tag : tags) {
			List<InQuestion> questionsByTag = listQuestionsByTags.get(tag);
			double amountOfElements = questionsByTag.size() * testPorc;
			for (int i = 0; i < amountOfElements; i++) {
				List<InQuestion> questions = new ArrayList<InQuestion>();
				questions.add(questionsByTag.get(i));
				List<InQuestion> putIfAbsent = testBaseByTags.putIfAbsent(tag,
						questions);
				if (putIfAbsent != null) {
					putIfAbsent.add(questionsByTag.get(i));
					testBaseByTags.put(tag, putIfAbsent);
				}
			}
		}
		return testBaseByTags;
	}

	private static Map<String, List<InQuestion>> questionsByTags() {
		
		List<InQuestion> questions = new CsvFileReader().readCsvFile();
		Map<String, List<InQuestion>> tagQuestions = new HashMap<String, List<InQuestion>>();
		for (InQuestion inQuestion : questions) {
			List<InQuestion> bodyQuestions = new ArrayList<InQuestion>();
			bodyQuestions.add(inQuestion);
			List<InQuestion> putIfAbsent = tagQuestions.putIfAbsent(
					inQuestion.getTag(), bodyQuestions);
			if (putIfAbsent != null) {
				putIfAbsent.add(inQuestion);
				tagQuestions.put(inQuestion.getTag(), putIfAbsent);
			}
		}
		return tagQuestions;
	}
	
	public String clearRatings(String content) {
		String[] codes = { "code" };
		int i = 0;
		while (content.contains("<" + codes[i] + ">")) {
			if (content.contains("<" + codes[i] + ">")
					&& content.contains("</" + codes[i] + ">")) {
				int posicaoInicial = content.indexOf("<" + codes[i] + ">");
				int posicaoFinal = content.indexOf("</" + codes[i] + ">")
						+ codes[i].length() + 3;

				for (int j = posicaoInicial; j < posicaoFinal; j++) {
					content = removeCharAt(content, posicaoInicial);
				}
			}

		}
		return html2text(content);

	}

	public String replaceAndClearTag (String tag){
		tag = tag.replace("><", ", ");
		tag = removeChar(tag, '<');
		tag = removeChar(tag, '>');
		return tag;
	}

	public String removeCharAt(String s, int pos) {
		return s.substring(0, pos) + s.substring(pos + 1);
	}

	public String html2text(String html) {
		return Jsoup.parse(html).text();
	}

	public String removeChar(String s, char c) {
		String r = "";
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != c)
				r += s.charAt(i);
		}
		return r;
	}
	
}
