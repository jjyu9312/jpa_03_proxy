package com.example.jpa_03_proxy.jpa03;

import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("happy");

        EntityManager em = emf.createEntityManager();

        // 트랜잭션 안에서 작업해야 하기 때문에 트랜잭션을 시작해줘야함.
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("hello");
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

//            Member referenceMember = em.getReference(Member.class, member.getId());
            Member findMember = em.find(Member.class, member.getId()); // 영속성 컨텍스트에 저장됨
//            System.out.println("findMember = " + findMember.getTeam().getClass());

//            em.detach(referenceMember);
//            System.out.println("referenceMember.username = " + referenceMember.getUsername());

//            Hibernate.initialize(referenceMember); // 강제 초기화
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            System.out.println("e : " + e);

        } finally {
            em.close();
        }

        emf.close();
    }

    private static void printMember(Member member) {
        System.out.println("username = " + member.getUsername());
    }

    private static void printMemberAndTeam(Member member) {
        String username = member.getUsername();
        System.out.println("username = " + username);

        Team team = member.getTeam();
        System.out.println("team = " + team.getName());

    }
}
