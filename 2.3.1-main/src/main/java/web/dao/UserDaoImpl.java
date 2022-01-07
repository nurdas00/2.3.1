package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.*;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> usersList() {
        return entityManager.createQuery("select user from User user").getResultList();
    }

    @Override
    public void update(Long id, User user){
        Query query = entityManager.createQuery("update User set name = :namePar, age = :agePar, email = :emailPar where id = :idPar");
        query.setParameter("namePar", user.getName());
        query.setParameter("agePar", user.getAge());
        query.setParameter("emailPar", user.getEmail());
        query.setParameter("idPar", id);
        query.executeUpdate();
    }

    @Override
    public void delete(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public User getUser(Long id){
        return (User) entityManager.createQuery("select user from User user where user.id = ?1").setParameter(1, id).getSingleResult();
    }
}
