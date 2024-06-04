package davideabbadessa;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("U1-W3-D1-jpa-crud");

    public static void main(String[] args) {
        System.out.println("Ciao che fai ?!");
    }
}
