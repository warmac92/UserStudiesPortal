package data;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.security.NoSuchAlgorithmException;

import utility.DBUtil;
import utility.PasswordUtil;
import business.ResetPswd;
import business.User;

public class ResetPswdDB {

    public static void addResetPwRequest(ResetPswd resetPswd) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            em.persist(resetPswd);
            et.commit();
        } catch (Exception e) {
            System.out.println(e);
            et.rollback();
        } finally {
            em.close();
        }
    }

    public static void deleteResetPwRequest(ResetPswd resetPswd) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            em.remove(em.merge(resetPswd));
            et.commit();
        } catch (Exception e) {
            System.out.println(e);
            et.rollback();
        } finally {
            em.close();
        }
    }

    public static void updatePassword(String token, String newPassword) {
        ResetPswd resetPswd = getResetPwRequestByToken(token);
        String email = resetPswd.getUseremail();
        User user = UserDB.getUserByEmail(email);
        String newSalt = PasswordUtil.getSalt();
        user.setSalt(newSalt);
        try {
            String newSaltedPassword = PasswordUtil.hashPasswordWithSalt(newPassword, newSalt);
            user.setSaltedPassword(newSaltedPassword);
        } catch (NoSuchAlgorithmException ex) {
        }
        UserDB.updateUser(user);
        deleteResetPwRequest(resetPswd);
    }

    public static ResetPswd getResetPwRequestByToken(String token) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        try {
            ResetPswd resetPswd = em.find(ResetPswd.class, token);
            return resetPswd;
        } finally {
            em.close();
        }
    }


}
