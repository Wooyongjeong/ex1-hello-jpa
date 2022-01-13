package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //준영속 상태로 만드는 방법
            //영속
            Member member = em.find(Member.class, 150L);
            member.setName("AAAAA");

//            em.detach(member); //JPA에서 얘를 관리를 안하게 되는거임
            em.clear(); //영속성 컨텍스트를 싹 지워버림

            Member member2 = em.find(Member.class, 150L);

            System.out.println("================");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
