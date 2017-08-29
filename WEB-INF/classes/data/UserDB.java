package data;

import javax.persistence.*;
import java.security.NoSuchAlgorithmException;

import utility.DBUtil;
import utility.PasswordUtil;
import business.User;
import business.TempUser;

public class UserDB {

    public static void addUser(User user) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            em.persist(user);
            et.commit();
        } catch (Exception e) {
            System.out.println(e);
            et.rollback();
        } finally {
            em.close();
        }
    }

    public static void updateUser(User user) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            em.merge(user);
            et.commit();
        } catch (Exception e) {
            System.out.println(e);
            et.rollback();
        } finally {
            em.close();
        }
    }

    public static User getUserByEmail(String email) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        try {
            User user = em.find(User.class, email);
            return user;
        } finally {
            em.close();
        }
    }

    public static boolean validateUser(String email, String password) {
        User user = getUserByEmail(email);
        if (user == null) {
            return false;
        } else {
            String salt = user.getSalt();
            String userSaledPassword = user.getSaltedPassword();
            try {
                String saltedPassword = PasswordUtil.hashPasswordWithSalt(password, salt);
                return saltedPassword.equals(userSaledPassword);
            } catch (NoSuchAlgorithmException ex) {
                return false;
            }
        }
    }

    public static String getSaltByEmail(String email) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        try {
            User user = em.find(User.class, email);
            return user.getSalt();
        } finally {
            em.close();
        }
    }

    public static void addTempUser(TempUser tempUser) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            em.persist(tempUser);
            et.commit();
        } catch (Exception e) {
            System.out.println(e);
            et.rollback();
        } finally {
            em.close();
        }
    }

    public static void deleteTempUser(TempUser tempUser) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            em.remove(em.merge(tempUser));
            et.commit();
        } catch (Exception e) {
            System.out.println(e);
            et.rollback();
        } finally {
            em.close();
        }
    }

    public static TempUser getTempUserByToken(String token) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        try {
            TempUser tempUser = em.find(TempUser.class, token);
            return tempUser;
        } finally {
            em.close();
        }
    }

    public static boolean validateTempUser(String token) {
        TempUser tempUser = getTempUserByToken(token);
        if (tempUser == null) {
            return false;
        } else {
            String tempUserToken = tempUser.getToken();
            return token.equals(tempUserToken);
        }
    }
    
    public static String activateUser(String token) {
        TempUser tempUser = getTempUserByToken(token);
        String email = tempUser.getUseremail();
        String username = tempUser.getUsername();
        String password = tempUser.getPassword();
        String salt = PasswordUtil.getSalt();
        User user = new User();
        user.setUseremail(email);
        user.setUsername(username);
        user.setSalt(salt);
        user.setType("Participant");
        user.setStudies(0);
        user.setParticipation(0);
        user.setCoins(0);
        try {
            String saltedPassword = PasswordUtil.hashPasswordWithSalt(password, salt);
            user.setSaltedPassword(saltedPassword);
        } catch (NoSuchAlgorithmException ex) {
            return null;
        }
        addUser(user);
        deleteTempUser(tempUser);
        return email;
    }
}
