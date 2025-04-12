package service;

import dao.UserDao;
import model.Page;
import model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;


public class UserService {
    private UserDao uDao = new UserDao();
    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());
    public boolean register(User user) {
        if(uDao.isUsernameExist(user.getUsername())) {
            return false;
        }
        if(uDao.isEmailExist(user.getEmail())) {
            return false;
        }
        uDao.addUser(user);
        return true;
    }

    public User login(String ue,String password) {
        User user=null;
        user = uDao.selectByUsernamePassword(ue, password);
        if(user!=null) {
            return user;
        }
        user=uDao.selectByEmailPassword(ue, password);
        if(user!=null) {
            return user;
        }
        return null;
    }
    public User selectById(int id) {
        User u=null;
        u = uDao.selectById(id);
        return u;
    }
    public void updateUserAddress(User user) {
        uDao.updateUserAddress(user);
    }
    public void updatePwd(User user) {
        uDao.updatePwd(user);
    }

    public Page getUserPage(int pageNumber) {
        Page p = new Page();
        p.setPageNumber(pageNumber);
        int pageSize = 7;
        int totalCount = 0;
        totalCount = uDao.selectUserCount();
        p.SetPageSizeAndTotalCount(pageSize, totalCount);
        List list=null;
        list = uDao.selectUserList( pageNumber, pageSize);
        p.setList(list);
        return p;
    }
    public boolean delete(int id ) {
        uDao.delete(id);
        return true;
    }
    public String getAvatarPath(int userId) {
        return uDao.getAvatarPath(userId);
    }


    public void updateAvatarPath(User user, String avatarPath) {
        // 先验证传入的用户对象和头像路径是否合法，比如用户对象不能为空，头像路径格式是否正确等
        if (user == null || avatarPath == null || avatarPath.isEmpty()) {
            LOGGER.warning("更新头像路径时传入的参数不合法，用户: " + user + ", 头像路径: " + avatarPath);
            return;
        }
        uDao.updateAvatarPath(user.getId(), avatarPath);
        user.setAvatarPath(avatarPath);
    }
}
