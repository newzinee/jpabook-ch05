# jpabook-ch05
자바 ORM 표준 JPA 프로그래밍(김영한) 참고

#### 참조를 사용하는 객체의 연관관계: 단방향

```
member1.setTeam(team1);
Team findTeam = member1.getTeam();
```

#### 외래키를 사용하는 데이블의 연관관계: 양방향

```roomsql
SELECT * 
FROM member
JOIN team ON member.TEAM_ID = team.ID
WHERE member.ID = 'member1'
```

@JoinColumn: 외래키를 매핑할 때 사용.(생략 가능)
- name: 매핑할 외래키 이름
- 생략하면, name은 필드명(team)+_+참조하는 테이블의 기본키 컬럼명(TEAM_ID)으로 됨. = team_TEAM_ID

@ManyToOne: 다대일(N:1) 관계에서 사용(필수)

--- 
조회
1. 객체 그래프 탐색(객체를 통해 조회: member.getTeam();)
2. 객체지향 쿼리 사용(JPQL)
- sql 조인과 jpql 조인은 문법이 다르다.
- jpql에서는 `:변수명`로 써서 파라미터를 바인딩 한다.

---
수정

entity 값 변경

---

연관관계 삭제

연관관계를 삭제하고, 엔티티를 삭제해야 한다.
```
member1.setTeam(null);  // 연관관계 삭제
em.remove(team);        // 엔티티 삭제
```

---

양방향 연관관계 만들기

DB에서는 외래키를 사용해서 양방향 연관관계를 가지고 있으므로, 현재 구조에서 객체쪽 연관관계를 수정한다.

현재는, 회원->팀(member.team)인 단방향 관계이다. 

팀->회원(team.members)인 단방향 관계도 추가한다. 여기서 팀->회원은 일대다 관계이다. 

`@OneToMany(mappedBy = "team")`에서 mappedby 속성에는 반대쪽 매핑의 필드 이름이다.

두 객체의 연관관계는 양방향이 아닌 두 개의 단방향이다. DB는 외래키를 사용한 한 개의 양방향 연관관계이다. 

DB 쪽은 연관관계가 1개, 객체 쪽은 연관관계가 2개이기 때문에 객체의 연관관계 중 하나를 선택해 외래키를 관리해야 하고, 이것을 `연관관계의 주인`이라 한다.

연관관계의 주인만이 DB와 매핑되어 외래키를 관리(등록, 수정, 삭제)할 수 있고, 주인이 아닌 쪽은 읽기만 할 수 있다. 

주인은 mappedby 속성을 사용하지 않고, 주인이 아닌쪽은 mappedby 속성을 통해 주인을 선택한다. 

연관관게의 주인은 테이블에 외래키가 있는 곳으로 정해야 한다. 여기서는 MEMBER 테이블 안에 TEAM_ID가 FK로 들어가있으므로, member.team이 주인이 된다. 

---

양방향 연관관계 저장은 **주인**쪽에서 해야한다.

  
   