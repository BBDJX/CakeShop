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
import java.util.List;

@WebServlet("/admin/holiday_management")
public class ListHolidayServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HolidayService holidayService = new HolidayServiceImpl();
        List<Holiday> holidays = holidayService.getAllHolidays();
        request.setAttribute("holidayList", holidays);
        request.getRequestDispatcher("/admin/holiday_management.jsp").forward(request, response);
    }
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}