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
	
	@WebMethod(operationName="postfile")
	public String postFile(@WebParam(name="owner") String owner, @WebParam(name="receivers") List<String> receivers, @WebParam(name="message") String message, @WebParam(name="filename") String name, @WebParam(name="filecontent") byte[] data) throws FileServiceException;
	
	@WebMethod(operationName="getfile")
	@WebResult(name="fileitem")
	public FileItem getFile(@WebParam(name="id") String id) throws FileServiceException;
	
	@WebMethod(operationName="getfilecontent")
	@WebResult(name="filecontent")
	public byte[] getWholeFileContent(@WebParam(name="id") String id) throws FileServiceException;

}
