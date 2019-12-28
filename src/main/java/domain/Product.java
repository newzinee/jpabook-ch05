package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @Column(name = "PRODUCT_ID")
    private String  id;

    private String name;

//    @ManyToMany(mappedBy = "products")  // 역방향 추가
//    private List<Member> members;
}
