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
//            biDirection(em);
            teamSave(em);
            tx.commit();
        } catch(Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();

    }

    // 양방향 연관관계 저장
    private static void teamSave(EntityManager em) {
        Team team3 = new Team("team3", "팀3");
        em.persist(team3);

        Member member3 = new Member("member3", "회원3");
        member3.setTeam(team3); // 연관관계 설정 Member.team
        em.persist(member3);

        Member member4 = new Member("member4", "회원4");
        member4.setTeam(team3); // 연관관계 설정 Member.team
        em.persist(member4);

        // 연관관계의 주인이 아니기 때문에, DB저장시, 이 코드는 무시된다.
        Member member5 = new Member("member5", "회원5");
        em.persist(member5);
        team3.getMembers().add(member5);
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
