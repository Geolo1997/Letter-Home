package team.dorm301.letterhome.dao;

import team.dorm301.letterhome.entity.LogInfo;

public interface LogInfoDao {

    void save(LogInfo logInfo);

    LogInfo getLogInfo();
}
