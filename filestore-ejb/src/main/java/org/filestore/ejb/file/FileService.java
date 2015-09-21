package org.filestore.ejb.file;

import java.util.List;

import org.filestore.ejb.file.entity.FileItem;

public interface FileService {
	
	public String postFile(String owner, List<String> receivers, String message, String name, String stream) throws FileServiceException;
	
	public FileItem getFile(String id) throws FileServiceException;
	
	public void deleteFile(String id) throws FileServiceException;

}
