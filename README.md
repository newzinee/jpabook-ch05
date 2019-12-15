# jpabook-ch05
자바 ORM 표준 JPA 프로그래밍(김영한) 참고

#### 참조를 사용하는 객체의 연관관계: 단방향

```java
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