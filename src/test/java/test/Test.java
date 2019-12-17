package test;

import domain.Member;
import domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Test {
    public static void main(String[] args) {
//        test1();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ch05");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
//            testSave(em);
//            queryLogicJoin(em);
//            updateRelation(em);
//            deleteRelation(em);
            biDirection(em);
            tx.commit();
        } catch(Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();

    }

    // 양방향 연관관계 설정 후, 조회
    private static void biDirection(EntityManager em) {

        // team에서 members 조회
        Team team = em.find(Team.class, "team1");
        List<Member> members = team.getMembers();

        for(Member member : members) {
            System.out.println("name: " + member.getUsername());
        }
    }

    // 연관관계 삭제
    private static void deleteRelation(EntityManager em) {
        Member member1 = em.find(Member.class,"member1");
        member1.setTeam(null);  // 연관관계 제거
    }

    // 수정
    private static void updateRelation(EntityManager em) {

        Team team2 = new Team("team2", "팀2");
        em.persist(team2);

        Member member = em.find(Member.class, "member1");
        member.setTeam(team2);
    }

    // JPQL 조인 사용한 조회
    private static void queryLogicJoin(EntityManager em) {
        String jpql = "select m from Member m join m.team t where t.name=:teamName";

        List<Member> members = em.createQuery(jpql, Member.class)
                                .setParameter("teamName", "팀1").getResultList();

        for(Member member : members) {
            System.out.println("[query] member.username="+member.getUsername());
        }
    }

    // 저장
    private static void testSave(EntityManager em) {
        Team team1 = new Team("team1", "팀1");
        em.persist(team1);

        Member member1 = new Member("member1", "회원1");
        member1.setTeam(team1);
        em.persist(member1);

        Member member2 = new Member("member2", "회원2");
        member2.setTeam(team1);
        em.persist(member2);
    }

    private static void test1() {
        Member member1 = new Member("member1", "회원1");
        Member member2 = new Member("member2", "회원2");

        Team team1 = new Team("team1", "팀1");

        // 객체(참조) 연관관계: 단방향
        member1.setTeam(team1);
        member2.setTeam(team1);

        Team findTeam = member1.getTeam();
    }


}
