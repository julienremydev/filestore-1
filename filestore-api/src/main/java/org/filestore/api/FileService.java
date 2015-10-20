package org.filestore.api;

import java.util.List;

import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@Remote
@WebService
public interface FileService {
	
	@WebMethod(action="post-file")
	public String postFile(@WebParam(name="owner") String owner, @WebParam(name="receivers") List<String> receivers, @WebParam(name="message") String message, @WebParam(name="filename") String name, @WebParam(name="filecontent") byte[] data) throws FileServiceException;
	
	@WebMethod(action="get-file")
	@WebResult(name="file-item")
	public FileItem getFile(@WebParam(name="id") String id) throws FileServiceException;
	
	@WebMethod(action="get-file-content")
	@WebResult(name="file-content")
	public byte[] getWholeFileContent(@WebParam(name="id") String id) throws FileServiceException;

}
