package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //EnumType.ORDINAL 사용 예제
            //만약 RoleType에 제일 앞에 GUEST가 추가된다? 큰일 나는거임
            Member member = new Member();
            member.setId(1L);
            member.setUsername("A");
            member.setRoleType(RoleType.USER);

            Member member2 = new Member();
            member2.setId(2L);
            member2.setUsername("B");
            member2.setRoleType(RoleType.ADMIN);

            Member member3 = new Member();
            member3.setId(3L);
            member3.setUsername("C");
            member3.setRoleType(RoleType.GUEST);

            em.persist(member);
            em.persist(member2);
            em.persist(member3);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
