package local.filestorage.rest.json;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
public class ObjectResponse<T> extends GenericJsonResponse
{
	private T data;

	public ObjectResponse(T data)
	{
		super(ResponseData.SUCCESS, null);
		this.data = data;
	}

	public T getData()
	{
		return data;
	}
}