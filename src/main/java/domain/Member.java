package domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor
@Entity
public class Member {

    @Id
    @Column(name = "MEMBER_ID")
    private String id;
    private String username;

    @ManyToOne
    @JoinColumn(name="TEAM_ID")
    private Team team;

    public Member(String id, String username) {
        setId(id);
        setUsername(username);
    }

    public void setTeam(Team team) {
        this.team = team;

        // 무한루프에 안 빠지도록 체크
        if(!team.getMembers().contains(this)) {
            team.getMembers().add(this);
        }
    }
}
