package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    public List<User> usersList();
    public User getUser(Long id);
    public void save(User user);
    public void update(Long id, User user);
    public void delete(Long id);
}
