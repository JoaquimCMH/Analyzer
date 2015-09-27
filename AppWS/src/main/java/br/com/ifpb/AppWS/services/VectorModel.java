package br.com.ifpb.AppWS.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.ifpb.AppWS.model.InQuestion;
import br.com.ifpb.AppWS.model.Vector;

public class VectorModel {

	private Map<String, List<InQuestion>> testBaseByTags = TreatmentDataBase.TEST_BASE;

	public VectorModel() {
	}

	private Map<String, String> getIndexTermsByTags() {
		Map<String, String> indexTermsByTags = new HashMap<String, String>();
		for (String tag : testBaseByTags.keySet()) {
			String tagIndexTerms = "";
			for (InQuestion inQuestion : testBaseByTags.get(tag)) {
				Vector questionTestVector = Extractor.vector(inQuestion
						.getBody());
				for (String term : questionTestVector.getVector().keySet()) {
					tagIndexTerms = tagIndexTerms + term + " ";
				}
			}
			indexTermsByTags.put(tag, tagIndexTerms);
		}

		return indexTermsByTags;
	}

	public String getLabel(String question) {

		Map<String, String> indexTermsByTags = getIndexTermsByTags();

		AngularSimilarity similarity = new AngularSimilarity();
		String tagHighestRanked = "Unclassified";
		double highestSimilarity = 0.0;
		Vector questionVector = Extractor.vector(question);

		for (String tag : testBaseByTags.keySet()) {
			Vector tagVector = Extractor.vector(indexTermsByTags.get(tag));
			double angularSimilarity = similarity.getAngularSimilarity(
					tagVector, questionVector);

			if (angularSimilarity > highestSimilarity) {
				highestSimilarity = angularSimilarity;
				tagHighestRanked = tag;
			}
		}
		return tagHighestRanked;
	}

	public Map<String, Double> getSimilarities(String question) {
		Map<String, String> indexTermsByTags = getIndexTermsByTags();

		AngularSimilarity angularSimilarity = new AngularSimilarity();
		
		Map<String, Double> similarities = new HashMap<String, Double>();
		Vector questionVector = Extractor.vector(question);

		for (String tag : testBaseByTags.keySet()) {
			Vector tagVector = Extractor.vector(indexTermsByTags.get(tag));
			Double similarity = angularSimilarity.getAngularSimilarity(tagVector, questionVector);
			similarities.put(tag, similarity);
		}
		return similarities;
	}

}
