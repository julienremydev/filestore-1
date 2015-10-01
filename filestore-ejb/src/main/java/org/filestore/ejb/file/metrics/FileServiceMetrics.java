package org.filestore.ejb.file.metrics;

import javax.ejb.Local;

import org.filestore.ejb.file.FileServiceException;

@Local
public interface FileServiceMetrics {
	
	public int getTotalUploads() throws FileServiceException;
	
	public int getTotalDownloads() throws FileServiceException;
	
	public int getUptime() throws FileServiceException;

}
