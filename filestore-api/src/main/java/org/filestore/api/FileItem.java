package org.filestore.api;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface FileItem extends Serializable {

	public String getId();

	public String getName();
	
	public String getType();
	
	public long getLength();

	public long getNbdownloads();
	
	public String getOwner();

	public List<String> getReceivers();

	public String getMessage();

	public Date getCreation();

	public Date getLastdownload();

}
