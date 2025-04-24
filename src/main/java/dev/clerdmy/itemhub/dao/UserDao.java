package dev.clerdmy.itemhub.dao;

import dev.clerdmy.itemhub.model.User;
import dev.clerdmy.itemhub.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class UserDao {

    private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

    public boolean createUser(User user) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
            logger.info("User saved, id={}", user.getId());
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error("Error in user saving", e);
            return false;
        }
    }

    public Optional<User> readUserById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            User user = session.find(User.class, id);
            return Optional.ofNullable(user);
        } catch (Exception e) {
            logger.error("Error reading user, id={}", id, e);
            return Optional.empty();
        }
    }

    public Optional<User> readUserByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            User user = session.createQuery("FROM User u WHERE u.email = :email", User.class).setParameter("email", email).uniqueResult();
            return Optional.ofNullable(user);
        } catch (Exception e) {
            logger.error("Error reading user, email={}", email, e);
            return Optional.empty();
        }
    }

    public boolean updateUser(User user) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(user);
            transaction.commit();
            logger.info("User updated, id={}", user.getId());
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error("Error in user updating", e);
            return false;
        }
    }

    public boolean deleteUser(Long id) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = session.find(User.class, id);
            if (user != null) {
                session.remove(user);
                logger.info("User deleted, id={}", id);
            } else {
                logger.info("User not found, id={}", id);
            }
            transaction.commit();
            return user != null;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error("Error in user deleting", e);
            return false;
        }
    }

}