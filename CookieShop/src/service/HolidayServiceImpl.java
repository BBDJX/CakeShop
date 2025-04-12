package service;

import dao.HolidayDao;
import dao.HolidayDaoImpl;
import model.Holiday;
import java.util.List;

public class HolidayServiceImpl implements HolidayService {
    private HolidayDao holidayDao = new HolidayDaoImpl();

    @Override
    public void addHoliday(Holiday holiday) {
        holidayDao.addHoliday(holiday);
    }

    @Override
    public void deleteHoliday(int id) {
        holidayDao.deleteHoliday(id);
    }

    @Override
    public void updateHoliday(Holiday holiday) {
        holidayDao.updateHoliday(holiday);
    }

    @Override
    public List<Holiday> getAllHolidays() {
        return holidayDao.getAllHolidays();
    }

    @Override
    public Holiday getHolidayById(int id) {
        return holidayDao.getHolidayById(id);
    }
}