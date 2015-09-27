package br.com.ifpb.AppWS.controller;

import java.util.Map;
import java.util.Set;

import br.com.ifpb.AppWS.model.Vector;
import br.com.ifpb.AppWS.services.Extractor;
import br.com.ifpb.AppWS.services.VectorModel;
import br.com.ifpb.AppWS.util.ExistsPunctuationMarkAnalyser;


public class CategorizerController {

	VectorModel model;
	ExistsPunctuationMarkAnalyser punctuationMarkAnalyser;
	
	public CategorizerController() {
		model = new VectorModel();
		punctuationMarkAnalyser = new ExistsPunctuationMarkAnalyser();
	}
	
	public String getLabel(String question) {
		return model.getLabel(question);
	}

	public Map<String, Double> getSimilarities(String question) {
		return model.getSimilarities(question);
	}

	public Vector getVectorModelRepresentation(String question) {
		return Extractor.vector(question);
	}

	public Set<String> getPunctuationParticle(String question) {
		return punctuationMarkAnalyser.analyse(question);
	}

}
