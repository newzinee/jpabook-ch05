package test;

import domain.Member;
import domain.Team;

public class Test {
    public static void main(String[] args) {
        Member member1 = new Member("member1", "회원1");
        Member member2 = new Member("member2", "회원2");

        Team team1 = new Team("team1", "팀1");

        // 객체(참조) 연관관계: 단방향
        member1.setTeam(team1);
        member2.setTeam(team1);

        Team findTeam = member1.getTeam();
    }

}
