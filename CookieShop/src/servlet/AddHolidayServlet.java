package servlet;

import model.Holiday;
import service.HolidayService;
import service.HolidayServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "admin_holiday_add", urlPatterns = "/admin/holiday_add")
public class AddHolidayServlet extends HttpServlet {

    private HolidayService holidayService = new HolidayServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从请求参数中获取节日相关信息
        String holidayName = request.getParameter("holidayName");
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");
        String discountStr = request.getParameter("discount");
        String description = request.getParameter("description");

        // 对获取的参数进行验证和转换，若有错误则直接返回错误响应，不进行后续添加操作
        Holiday holiday = validateAndCreateHoliday(holidayName, startDateStr, endDateStr, discountStr, description);
        if (holiday == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "输入参数有误，请检查后重新添加");
            return;
        }

        // 调用服务层方法添加节日信息
        holidayService.addHoliday(holiday);

        // 添加成功后重定向到节日列表页面
        response.sendRedirect("/admin/holiday_management");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * 验证并创建Holiday对象的方法，若参数验证不通过则返回null
     *
     * @param holidayName  节日名称
     * @param startDateStr 开始日期字符串
     * @param endDateStr   结束日期字符串
     * @param discountStr  折扣字符串
     * @param description  描述信息
     * @return Holiday对象（验证通过）或null（验证不通过）
     */
    private Holiday validateAndCreateHoliday(String holidayName, String startDateStr, String endDateStr, String discountStr, String description) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;

        // 验证节日名称是否为空
        if (holidayName == null || holidayName.isEmpty()) {
            return null;
        }

        try {
            // 验证并转换开始日期和结束日期格式
            startDate = sdf.parse(startDateStr);
            endDate = sdf.parse(endDateStr);
        } catch (Exception e) {
            return null;
        }

        int discount = 0;
        if (discountStr!= null &&!discountStr.isEmpty()) {
            try {
                // 验证折扣是否为合法数字格式
                discount = Integer.parseInt(discountStr);
            } catch (NumberFormatException e) {
                return null;
            }
        }

        // 创建Holiday对象并设置属性
        Holiday holiday = new Holiday();
        holiday.setHolidayName(holidayName);
        holiday.setStartDate(startDate);
        holiday.setEndDate(endDate);
        holiday.setDiscount(discount);
        holiday.setDescription(description);

        return holiday;
    }
}