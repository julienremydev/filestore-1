package org.filestore.ejb.file;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.DriverManager;
import java.sql.SQLNonTransientConnectionException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.filestore.ejb.file.entity.FileItem;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class FileItemTest {

	private static Logger LOGGER = Logger.getLogger(FileItemTest.class.getName());

    private static EntityManagerFactory emFactory;
    private static EntityManager em;

    @BeforeClass
    public static void setUp() throws Exception {
        try {
        	LOGGER.log(Level.INFO, "Starting memory database for unit tests");
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            DriverManager.getConnection("jdbc:derby:memory:unit-testing-jpa;create=true").close();
        } catch (Exception e) {
        	LOGGER.log(Level.SEVERE, "unable to start database", e);
            fail("Exception during database startup.");
        }
        try {
        	LOGGER.log(Level.INFO, "Building Hibernate EntityManager for unit tests");
            emFactory = Persistence.createEntityManagerFactory("testPU");
            em = emFactory.createEntityManager();
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Exception during JPA EntityManager instanciation.");
        }
    }

    @AfterClass
    public static void tearDown() throws Exception {
    	LOGGER.log(Level.INFO, "Shuting Hibernate JPA layer.");
        if (em != null) {
            em.close();
        }
        if (emFactory != null) {
            emFactory.close();
        }
        LOGGER.log(Level.INFO, "Stopping memory database.");
        try {
            DriverManager.getConnection("jdbc:derby:memory:unit-testing-jpa;shutdown=true").close();
        } catch (SQLNonTransientConnectionException ex) {
            if (ex.getErrorCode() != 45000) {
                throw ex;
            }
        }
    }

    @Test
    public void testFileItem() {
        try {
            em.getTransaction().begin();

            FileItem file = new FileItem();
            file.setId("myid");
            file.setName("Tagada");
            file.setLength(10);
            file.setMessage("this is a message");
            file.setOwner("miage");
            file.setType("text/plain");
            file.setReceivers(new ArrayList<String> ());
            
            em.persist(file);
            assertTrue(em.contains(file));
            
            FileItem file2 = em.find(FileItem.class, "myid");
            assertNotNull(file2);
            assertEquals(file, file2);

            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            LOGGER.log(Level.SEVERE, "error during testing file item", e);
            fail("Exception during testPersistence");
        }
    }

}
