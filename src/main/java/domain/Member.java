package domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity
public class Member {

    @Id
    @Column(name = "MEMBER_ID")
    private String id;

    private String username;

    @OneToOne(mappedBy = "member")
    private Locker locker;

    @ManyToOne
    @JoinColumn(name="TEAM_ID")
    private Team team;

    @ManyToMany
    @JoinTable(name = "MEMBER_PRODUCT",
                joinColumns = @JoinColumn(name="MEMBER_ID"),
                inverseJoinColumns = @JoinColumn(name="PRODUCT_ID"))
    private List<Product> products = new ArrayList<>();

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

    // 다대다 양방향을 위한 편의 메소드
    public void addProduct(Product product) {
        products.add(product);
        product.getMembers().add(this);
    }
}
