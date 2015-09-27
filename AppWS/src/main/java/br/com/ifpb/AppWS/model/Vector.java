package br.com.ifpb.AppWS.model;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Vector {

	private Map<String, Float> vector;

	public Vector() {
		vector = new HashMap<String, Float>();
	}

	public Vector(Map<String, Float> vector) {
		this.vector = vector;
	}

	public Map<String, Float> getVector() {
		return vector;
	}

	public void setVector(Map<String, Float> vector) {
		this.vector = vector;
	}

	public String toString() {
		return vector.toString();
	}
}
