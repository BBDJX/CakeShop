package dao;

import model.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.DataSourceUtils;

import java.sql.SQLException;
import java.util.List;

public class UserDao {
    private static final QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

    // 添加用户的方法
    public void addUser(User user) {
        String sql = "insert into user(username,email,password,name,phone,address,isadmin,isvalidate) values(?,?,?,?,?,?,?,?)";
        try {
            runner.update(sql, user.getUsername(), user.getEmail(), user.getPassword(), user.getName(), user.getPhone(), user.getAddress(), user.isIsadmin(), user.isIsvalidate());
        } catch (SQLException e) {
            // 记录日志或抛出更高级别的异常，这里简单打印堆栈信息
            e.printStackTrace();
        }
    }

    // 检查用户名是否存在的方法
    public boolean isUsernameExist(String username) {
        String sql = "select * from user where username =?";
        try {
            User u = runner.query(sql, new BeanHandler<>(User.class), username);
            return u!= null;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 检查邮箱是否存在的方法
    public boolean isEmailExist(String email) {
        String sql = "select * from user where email =?";
        try {
            User u = runner.query(sql, new BeanHandler<>(User.class), email);
            return u!= null;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 根据用户名和密码查询用户的方法
    public User selectByUsernamePassword(String username, String password) {
        String sql = "select * from user where username=? and password=?";
        try {
            return runner.query(sql, new BeanHandler<>(User.class), username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 根据邮箱和密码查询用户的方法
    public User selectByEmailPassword(String email, String password) {
        String sql = "select * from user where email=? and password=?";
        try {
            return runner.query(sql, new BeanHandler<>(User.class), email, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 根据用户ID查询用户的方法
    public User selectById(int id) {
        String sql = "select * from user where id=?";
        try {
            return runner.query(sql, new BeanHandler<>(User.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 更新用户地址的方法
    public void updateUserAddress(User user) {
        String sql = "update user set name =?,phone=?,address=? where id =?";
        try {
            runner.update(sql, user.getName(), user.getPhone(), user.getAddress(), user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 更新用户密码的方法
    public void updatePwd(User user) {
        String sql = "update user set password =? where id =?";
        try {
            runner.update(sql, user.getPassword(), user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 获取用户总数的方法
    public int selectUserCount() {
        String sql = "select count(*) from user";
        try {
            return runner.query(sql, new ScalarHandler<Long>()).intValue();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // 获取用户列表的方法（分页）
    public List<User> selectUserList(int pageNo, int pageSize) {
        String sql = "select * from user limit?,?";
        try {
            return runner.query(sql, new BeanListHandler<>(User.class), (pageNo - 1) * pageSize, pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 删除用户的方法
    public void delete(int id) {
        String sql = "delete from user where id =?";
        try {
            runner.update(sql, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 更新用户头像路径的方法
    public void updateAvatarPath(int userId, String avatarPath) {
        String sql = "UPDATE user SET avatar_path =? WHERE id =?";
        try {
            runner.update(sql, avatarPath, userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 获取用户头像路径的方法
    public String getAvatarPath(int userId) {
        String sql = "SELECT avatar_path FROM user WHERE id =?";
        try {
            return runner.query(sql, new ScalarHandler<>(), userId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}