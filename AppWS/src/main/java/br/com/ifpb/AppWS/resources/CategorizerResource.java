package br.com.ifpb.AppWS.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import br.com.ifpb.AppWS.controller.CategorizerController;
import br.com.ifpb.AppWS.model.Vector;

/**
 * Root resource (exposed at "categorizer" path)
 */
@Path("/categorizer")
public class CategorizerResource {

	CategorizerController controller;
	
	public CategorizerResource() {
		controller = new CategorizerController();
	}
	
    //http://localhost:8080/AppWS/webapi/categorizer/label
    @POST
    @Path("/label")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String getLabel(String question) {
    	return controller.getLabel(question);
    }
    
    @POST
    @Path("/similarities")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public String getSimilarities(String question) {
    	Map<String, Double> similarities = controller.getSimilarities(question);
    	Gson gson = new Gson();
    	return gson.toJson(similarities);
    }
    
    @POST
    @Path("/vectorModel")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Vector getVectorModelRepresentation(String question) {
    	return controller.getVectorModelRepresentation(question);
    }
    
    @POST
    @Path("/punctuationParticle")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public String getPunctuationParticle(String question) {
    	Set<String> punctuationParticle = controller.getPunctuationParticle(question);
    	Gson gson = new Gson();
    	return gson.toJson(punctuationParticle);
    }
    
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllInOne(String question) {
    	List<Object> all = new ArrayList<Object>();
    	all.add(controller.getSimilarities(question));
    	all.add(controller.getVectorModelRepresentation(question));
    	all.add(controller.getPunctuationParticle(question));
    	all.add(controller.getLabel(question));
    	Gson gson = new Gson();
    	return gson.toJson(all);
    }
}
