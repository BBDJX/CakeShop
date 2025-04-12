package service;

import model.Holiday;
import java.util.List;

public interface HolidayService {
    // 添加节日
    void addHoliday(Holiday holiday);
    // 删除节日
    void deleteHoliday(int id);
    // 修改节日
    void updateHoliday(Holiday holiday);
    // 查询所有节日
    List<Holiday> getAllHolidays();
    // 根据ID查询节日
    Holiday getHolidayById(int id);
}