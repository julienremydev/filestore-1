package org.filestore.ejb.file;

import java.util.List;

import javax.ejb.Local;

import org.filestore.ejb.file.entity.FileItem;

@Local
public interface FileServiceAdmin {
	
	public List<FileItem> listAllFiles() throws FileServiceException;
	
	public FileItem getNextStaleFile() throws FileServiceException;
	
	public void deleteFile(String id) throws FileServiceException;

}
