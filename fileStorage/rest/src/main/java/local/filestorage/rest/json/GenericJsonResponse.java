package local.filestorage.rest.json;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
public class GenericJsonResponse implements Serializable {
	private String status;
	private String message;

	public GenericJsonResponse(String status, String message) {
		this.status = status;
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public String getMessage() {
		return this.message;
	}
}
