package utility;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBUtil {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Assignment4PU");

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
}
