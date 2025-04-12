package dao;

import model.Holiday;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtil;

public class HolidayDaoImpl implements HolidayDao {

    @Override
    public void addHoliday(Holiday holiday) {
        String sql = "INSERT INTO holiday_activities (holiday_name, start_date, end_date, discount, description) VALUES (?,?,?,?,?)";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, holiday.getHolidayName());
            preparedStatement.setDate(2, new Date(holiday.getStartDate().getTime()));
            preparedStatement.setDate(3, new Date(holiday.getEndDate().getTime()));
            preparedStatement.setInt(4, holiday.getDiscount());
            preparedStatement.setString(5, holiday.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteHoliday(int id) {
        String sql = "DELETE FROM holiday_activities WHERE id =?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateHoliday(Holiday holiday) {
        String sql = "UPDATE holiday_activities SET holiday_name =?, start_date =?, end_date =?, discount =?, description =? WHERE id =?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, holiday.getHolidayName());
            preparedStatement.setDate(2, new Date(holiday.getStartDate().getTime()));
            preparedStatement.setDate(3, new Date(holiday.getEndDate().getTime()));
            preparedStatement.setInt(4, holiday.getDiscount());
            preparedStatement.setString(5, holiday.getDescription());
            preparedStatement.setInt(6, holiday.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Holiday> getAllHolidays() {
        List<Holiday> holidays = new ArrayList<>();
        String sql = "SELECT * FROM holiday_activities";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Holiday holiday = new Holiday();
                holiday.setId(resultSet.getInt("id"));
                holiday.setHolidayName(resultSet.getString("holiday_name"));
                holiday.setStartDate(resultSet.getDate("start_date"));
                holiday.setEndDate(resultSet.getDate("end_date"));
                holiday.setDiscount(resultSet.getInt("discount"));
                holiday.setDescription(resultSet.getString("description"));
                holidays.add(holiday);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return holidays;
    }

    @Override
    public Holiday getHolidayById(int id) {
        Holiday holiday = null;
        String sql = "SELECT * FROM holiday_activities WHERE id =?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                holiday = new Holiday();
                holiday.setId(resultSet.getInt("id"));
                holiday.setHolidayName(resultSet.getString("holiday_name"));
                holiday.setStartDate(resultSet.getDate("start_date"));
                holiday.setEndDate(resultSet.getDate("end_date"));
                holiday.setDiscount(resultSet.getInt("discount"));
                holiday.setDescription(resultSet.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return holiday;
    }
}