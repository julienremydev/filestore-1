package fr.miage.filestore;

import java.io.InputStream;

public class BinaryStoreServiceBean implements BinaryStoreService {

	@Override
	public boolean exists(String key) throws BinaryStoreServiceException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String put(InputStream is) throws BinaryStoreServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream get(String key) throws BinaryStoreServiceException,
			BinaryStreamNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
