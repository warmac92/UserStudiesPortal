package data;

import java.util.*;
import javax.persistence.*;

import utility.DBUtil;
import business.Study;
import business.Question;
import business.AnswerPK;
import business.Answer;

public class StudyDB {

    public static void addStudy(Study study) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            em.persist(study);
            et.commit();
        } catch (Exception e) {
            System.out.println(e);
            et.rollback();
        } finally {
            em.close();
        }
    }

    public static void updateStudy(Study study) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            em.merge(study);
            et.commit();
        } catch (Exception e) {
            System.out.println(e);
            et.rollback();
        } finally {
            em.close();
        }
    }

    public static Study getStudyByStudyID(String studyID) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        try {
            Study study = em.find(Study.class, studyID);
            return study;
        } finally {
            em.close();
        }
    }

    public static List<Study> getStudiesByCreatorEmail(String email) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        //prepared Statement
        String ps = "select s from Study s where s.useremail.useremail = ";
        String sqlQuery = ps + "'" + email + "'";
        TypedQuery<Study> q = em.createQuery(sqlQuery, Study.class);
        List<Study> studies;
        try {
            studies = q.getResultList();
            if (studies == null || studies.isEmpty()) {
                studies = null;
            }
        } finally {
            em.close();
        }
        return studies;
    }

    public static List<Study> getOpenStudies() {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        //Named Query
        TypedQuery<Study> q = em.createNamedQuery("Study.findBySStatusOpen", Study.class);
        List<Study> studies;
        try {
            studies = q.getResultList();
            if (studies == null || studies.isEmpty()) {
                studies = null;
            }
        } finally {
            em.close();
        }
        return studies;
    }

    public static Question getQuesByStudyID(String studyID) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        String questionID = studyID + "001";
        try {
            Question question = em.find(Question.class, questionID);
            return question;
        } finally {
            em.close();
        }
    }

    public static void addQues(Question question) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            em.persist(question);
            et.commit();
        } catch (Exception e) {
            System.out.println(e);
            et.rollback();
        } finally {
            em.close();
        }
    }

    public static void updateQuestion(Question question) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            em.merge(question);
            et.commit();
        } catch (Exception e) {
            System.out.println(e);
            et.rollback();
        } finally {
            em.close();
        }
    }

    public static void addAnsPK(AnswerPK answerPK) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            em.persist(answerPK);
            et.commit();
        } catch (Exception e) {
            System.out.println(e);
            et.rollback();
        } finally {
            em.close();
        }
    }

    public static void addAns(Answer answer) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            em.persist(answer);
            et.commit();
        } catch (Exception e) {
            System.out.println(e);
            et.rollback();
        } finally {
            em.close();
        }
    }
}
