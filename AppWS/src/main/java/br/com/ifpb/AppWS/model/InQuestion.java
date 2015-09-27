package br.com.ifpb.AppWS.model;

public class InQuestion {

	private String bodyId;
	private String body;
	private String tag;

	public InQuestion() {
	}

	public InQuestion(String bodyId, String body, String tags) {
		this.bodyId = bodyId;
		this.body = body;
		this.tag = tags;
	}

	public String getBodyId() {
		return bodyId;
	}

	public void setBodyId(String bodyId) {
		this.bodyId = bodyId;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Override
	public String toString() {
		return "Question [BodyID =" + bodyId + ", body =" + body + "]"
				+ ", tags =" + tag + "]";
	}
}
