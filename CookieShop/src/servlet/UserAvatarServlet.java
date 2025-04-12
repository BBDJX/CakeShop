package servlet;

import dao.UserDao;
import model.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@WebServlet("/userAvatar")
public class UserAvatarServlet extends HttpServlet {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/cookieshop?serverTimezone=Asia/Shanghai";
    private static final String USER = "root";
    private static final String PASS = "123";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("user_login.jsp");
            return;
        }

        // 获取用户当前头像路径
        String currentAvatarPath = getCurrentAvatarPath(user);
        request.setAttribute("currentAvatarPath", currentAvatarPath);

        // 处理文件上传
        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                // 创建DiskFileItemFactory对象，设置缓冲区大小和临时目录文件
                DiskFileItemFactory factory = new DiskFileItemFactory();
                // 创建ServletFileUpload对象，并设置上传文件的大小限制
                ServletFileUpload sfu = new ServletFileUpload(factory);
                sfu.setSizeMax(10 * 1024 * 1024); // 以byte为单位，10MB
                sfu.setHeaderEncoding("utf-8");

                // 解析请求，得到保存所有上传内容的List对象
                List<FileItem> fileItemList = sfu.parseRequest(request);
                Iterator<FileItem> fileItems = fileItemList.iterator();

                // 遍历fileItems
                while (fileItems.hasNext()) {
                    FileItem fileItem = fileItems.next();
                    if (fileItem.isFormField()) {
                        // 普通表单元素处理（这里暂时不需要处理普通表单元素）
                    } else {
                        if (fileItem.getName() == null || fileItem.getFieldName() == null) {
                            // 未选择文件的情况处理（可以根据需求给出提示信息等）
                        } else {
                            // 获取文件相关信息
                            String fileName = fileItem.getName();
                            String suffix = fileName.substring(fileName.lastIndexOf('.'));
                            String newFileName = user.getId() + "_" + UUID.randomUUID().toString() + suffix;

                            // 确定文件存储路径（根据实际情况修改）
                            String uploadPath = getServletContext().getRealPath("/picture");
                            File uploadDir = new File(uploadPath);
                            if (!uploadDir.exists()) {
                                uploadDir.mkdirs();
                            }

                            // 写入文件
                            String filePath = uploadPath + File.separator + newFileName;
                            File file = new File(filePath);
                            fileItem.write(file);

                            // 更新数据库中的头像路径
                            UserDao userDao = new UserDao(); // 创建UserDao实例
                            userDao.updateAvatarPath(user.getId(), filePath);

                            // 设置新的头像路径到请求属性
                            request.setAttribute("currentAvatarPath", filePath);
                        }
                    }
                }
            } catch (FileUploadException e) {
                // 处理文件上传过程中的异常，例如文件大小超过限制、文件格式不支持等
                e.printStackTrace();
                // 可以在这里添加更详细的错误处理逻辑，如记录日志、给用户返回友好的错误提示等
            } catch (IOException e) {
                // 处理文件写入过程中的异常，例如磁盘空间不足、无法创建文件等
                e.printStackTrace();
                // 同样可以添加更详细的错误处理逻辑
            } catch (Exception e) {
                // 捕获其他未预料到的异常
                e.printStackTrace();
                // 这里也可以根据需要进行更复杂的错误处理，如记录错误信息并通知管理员等
            }
        }

        // 设置缓存控制头，禁止浏览器缓存响应
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        response.sendRedirect("user_center.jsp");
    }

    /**
     * 获取用户当前头像路径
     *
     * @param user 用户对象
     * @return 当前头像路径，若获取失败返回默认头像路径
     */
    private String getCurrentAvatarPath(User user) {
        UserService userService = new UserService();
        String currentAvatarPath = null;
        try {
            currentAvatarPath = userService.getAvatarPath(user.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (currentAvatarPath == null || currentAvatarPath.isEmpty()) {
            currentAvatarPath = "/picture/touxiang.jpg";
        }
        return currentAvatarPath;
    }
}