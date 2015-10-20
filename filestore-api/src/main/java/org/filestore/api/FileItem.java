package org.filestore.api;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public abstract class FileItem implements Serializable {

	private static final long serialVersionUID = 1L;

	public abstract String getId();

	public abstract String getName();
	
	public abstract String getType();
	
	public abstract long getLength();

	public abstract long getNbdownloads();
	
	public abstract String getOwner();

	public abstract List<String> getReceivers();

	public abstract String getMessage();

	public abstract Date getCreation();

	public abstract Date getLastdownload();

}
