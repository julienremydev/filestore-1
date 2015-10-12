package org.filestore.client;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.filestore.ejb.file.FileService;
import org.filestore.ejb.file.FileServiceException;

public class FileStoreClient {

	private static final Logger LOGGER = Logger.getLogger(FileStoreClient.class.getName());

	@Resource(lookup = "java:comp/InAppClientContainer")
	private static boolean isInAppclient;
	@EJB
	private FileService service;

	public FileStoreClient() {
	}

	public FileService getFileServiceRemote() throws NamingException {
		if (!Boolean.TRUE.equals(isInAppclient) && service == null) {
			LOGGER.log(Level.INFO, "getting FileSerive using remote-naming");
			final Properties env = new Properties();
			env.put(Context.INITIAL_CONTEXT_FACTORY,"org.jboss.naming.remote.client.InitialContextFactory");
			env.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
			InitialContext context = new InitialContext(env);
			service = (FileService) context.lookup("filestore-ear/filestore-ejb/fileservice!org.filestore.ejb.file.FileService");
			context.close();
		}
		return service;
	}
	
	public FileService getFileServiceEJB() throws NamingException {
		if (!Boolean.TRUE.equals(isInAppclient) && service == null) {
			LOGGER.log(Level.INFO, "getting FileSerive using ejb client");
			final Properties env = new Properties();
			env.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			env.put("jboss.naming.client.ejb.context",true);
			InitialContext context = new InitialContext(env);
			service = (FileService) context.lookup("ejb:filestore-ear/filestore-ejb/fileservice!org.filestore.ejb.file.FileService");
			context.close();
		}
		return service;
	}

	public void postFile(String owner, List<String> receivers, String message,
			String filename, Path file) throws FileServiceException,
			IOException, NamingException {
		if ( Boolean.TRUE.equals(isInAppclient) ) {
			LOGGER.log(Level.INFO, "We ARE in a client container");
		}
		byte[] content = Files.readAllBytes(file);
		//getFileServiceEJB().postFile(owner, receivers, message, filename, content);
		getFileServiceRemote().postFile(owner, receivers, message, filename, content);
	}

	public static void main(String args[]) throws FileServiceException,
			IOException, NamingException {
		FileStoreClient client = new FileStoreClient();
		Path path = Paths.get(args[3]);
		client.postFile(args[0], Arrays.asList(args[1].split(",")), args[2],
				path.getFileName().toString(), path);
	}

}
