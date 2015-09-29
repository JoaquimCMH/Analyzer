package br.com.ifpb.AppWS.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
    @GET
    @Path("/label/{question}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getLabel(@PathParam("question") String question) {
    	return controller.getLabel(question);
    }
    
    @GET
    @Path("/similarities/{question}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getSimilarities(@PathParam("question") String question) {
    	Map<String, Double> similarities = controller.getSimilarities(question);
    	Gson gson = new Gson();
    	return gson.toJson(similarities);
    }
    
    @GET
    @Path("/vectorModel/{question}")
    @Produces(MediaType.APPLICATION_JSON)
    public Vector getVectorModelRepresentation(@PathParam("question") String question) {
    	return controller.getVectorModelRepresentation(question);
    }
    
    @GET
    @Path("/punctuationParticle/{question}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPunctuationParticle(@PathParam("question") String question) {
    	Set<String> punctuationParticle = controller.getPunctuationParticle(question);
    	Gson gson = new Gson();
    	return gson.toJson(punctuationParticle);
    }
    
    @GET
    @Path("{question}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllInOne(@PathParam("question") String question) {
    	List<Object> all = new ArrayList<Object>();
    	all.add(controller.getSimilarities(question));
    	all.add(controller.getVectorModelRepresentation(question));
    	all.add(controller.getPunctuationParticle(question));
    	all.add(controller.getLabel(question));
    	Gson gson = new Gson();
    	return gson.toJson(all);
    }
}
