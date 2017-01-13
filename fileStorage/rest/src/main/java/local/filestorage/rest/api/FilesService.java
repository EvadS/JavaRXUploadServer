package local.filestorage.rest.api;

import static local.filestorage.rest.json.ResponseData.BEAN_LOOKUP;
import static local.filestorage.rest.json.ResponseData.ERROR;
import static local.filestorage.rest.json.ResponseData.INTERNAL_SERVER_ERROR;
import static local.filestorage.rest.json.ResponseData.INVALID_PARAMETERS;
import static local.filestorage.rest.json.ResponseData.SUCCESS;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import local.filestorage.rest.json.FileUploadForm;
import local.filestorage.rest.json.GenericJsonResponse;
import local.filestorage.rest.json.ObjectResponse;
import local.filestorage.rest.util.StorageUtil;
import local.filestorage.server.StorageBean;

@Path("/files")
public class FilesService {
	private StorageBean storageBean;

	public FilesService() throws NamingException {
		storageBean = (StorageBean) (new InitialContext()).lookup(BEAN_LOOKUP);
	}

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public GenericJsonResponse upload(@Context HttpServletRequest request, @MultipartForm FileUploadForm form) {
		if (!form.isValid())
			return new GenericJsonResponse(ERROR, INVALID_PARAMETERS);
		try {
			// TODO: username validation
			StorageUtil.store(form.getFileName(), storageBean.getStorageFolder(), form.getFileData());
			return new GenericJsonResponse(SUCCESS, null);
		} catch (IOException e) {
			return new GenericJsonResponse(ERROR, INTERNAL_SERVER_ERROR + e.getMessage());
		}
	}

	@GET
	@Path("/get/{filename}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public GenericJsonResponse listFiles(@Context HttpServletRequest request, @Context ServletContext context,
			@PathParam("filename") String filename) {
		if (filename == null)
			return new GenericJsonResponse(ERROR, INVALID_PARAMETERS);
		try {
			byte[] content = StorageUtil.retrieve(filename, storageBean.getStorageFolder());
			return new ObjectResponse<byte[]>(content);
		} catch (IOException e) {
			return new GenericJsonResponse(ERROR, INTERNAL_SERVER_ERROR + e.getMessage());
		}
	}
}
