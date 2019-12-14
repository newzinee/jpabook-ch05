package domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {
    private String id;
    private String username;
    private Team team;

    public Member(String id, String username) {
        setId(id);
        setUsername(username);
    }
}
