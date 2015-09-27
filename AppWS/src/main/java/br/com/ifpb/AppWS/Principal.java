package br.com.ifpb.AppWS;

import java.util.Set;

import br.com.ifpb.AppWS.controller.CategorizerController;
import br.com.ifpb.AppWS.services.TreatmentDataBase;

public class Principal {

	public static void main(String[] args) {
		CategorizerController controller = new CategorizerController();
		Set<String> punctuationParticle = controller.getPunctuationParticle("Ei, cara... Como programo em Java?");
		for (String string : punctuationParticle) {
			System.out.println(string);
		}
		
		System.out.println(controller.getLabel("Ei, cara... Como programo em Java?"));
		
		System.out.println(controller.getSimilarities("Ei, cara... Como programo em Java?"));
	}
}
