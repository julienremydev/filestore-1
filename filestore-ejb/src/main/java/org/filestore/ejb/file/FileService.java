package org.filestore.ejb.file;

import java.util.List;

import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.filestore.ejb.file.entity.FileItem;

@Remote
@WebService
public interface FileService {
	
	@WebMethod(action="post-file")
	public String postFile(@WebParam(name="owner") String owner, @WebParam(name="receivers") List<String> receivers, @WebParam(name="message") String message, @WebParam(name="filename") String name, @WebParam(name="filecontent") byte[] data) throws FileServiceException;
	
	@WebMethod(action="get-file")
	public FileItem getFile(@WebParam(name="id") String id) throws FileServiceException;
	
	@WebMethod(action="get-file-content")
	public byte[] getWholeFileContent(@WebParam(name="id") String id) throws FileServiceException;

}
