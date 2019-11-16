package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
    
    private static EntityManagerFactory Movimento 
            = Persistence.createEntityManagerFactory("projeto");
    
    public static EntityManager getGerenciador(){
        return Movimento.createEntityManager();
    }
    
}
