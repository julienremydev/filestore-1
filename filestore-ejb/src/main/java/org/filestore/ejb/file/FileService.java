package org.filestore.ejb.file;

import java.util.List;

import javax.ejb.Remote;

import org.filestore.ejb.file.entity.FileItem;

@Remote
public interface FileService {
	
	public String postFile(String owner, List<String> receivers, String message, String name, byte[] data) throws FileServiceException;
	
	public FileItem getFile(String id) throws FileServiceException;
	
	public byte[] getWholeFileContent(String id) throws FileServiceException;

}
