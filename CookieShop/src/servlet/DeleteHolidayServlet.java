package servlet;

import service.HolidayService;
import service.HolidayServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/holiday_delete")
public class DeleteHolidayServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        HolidayService holidayService = new HolidayServiceImpl();
        holidayService.deleteHoliday(id);
        response.sendRedirect("/admin/holiday_management");
    }
}