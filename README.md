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