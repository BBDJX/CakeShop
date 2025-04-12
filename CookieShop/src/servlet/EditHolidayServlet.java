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

@WebServlet("/admin/holiday_edit")
public class EditHolidayServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam!= null &&!idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                HolidayService holidayService = new HolidayServiceImpl();
                Holiday holiday = holidayService.getHolidayById(id);
                request.setAttribute("holiday", holiday);
                request.getRequestDispatcher("/holiday_edit.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                // 可以在这里返回一个错误页面给前端，或者记录日志，告知用户参数错误等情况
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "无效的节日ID参数");
            }
        } else {
            // 同样可以返回错误提示，告知缺少必要的参数
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "缺少节日ID参数");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String holidayName = request.getParameter("holidayName");
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");
        int discount = Integer.parseInt(request.getParameter("discount"));
        String description = request.getParameter("description");

        Holiday holiday = new Holiday();
        holiday.setId(id);
        holiday.setHolidayName(holidayName);
        holiday.setStartDate(java.sql.Date.valueOf(startDateStr));
        holiday.setEndDate(java.sql.Date.valueOf(endDateStr));
        holiday.setDiscount(discount);
        holiday.setDescription(description);

        HolidayService holidayService = new HolidayServiceImpl();
        holidayService.updateHoliday(holiday);

        response.sendRedirect("/admin/holiday_management");
    }
}
