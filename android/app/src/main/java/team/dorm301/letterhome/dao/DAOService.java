package team.dorm301.letterhome.dao;

import team.dorm301.letterhome.entity.LogInfo;

/**
 * @author 桀骜
 */
public class DAOService {

    private static volatile DAOService instance;

    private DAOService() {
    }

    public static DAOService getInstance() {
        if (instance == null) {
            synchronized (DAOService.class) {
                if (instance == null) {
                    instance = new DAOService();
                }
            }
        }
        return instance;
    }
    private LogInfoDao logInfoDao = new LogInfoDaoImpl();

    /**
     * 获取当前登录用户
     *
     * @return
     */
    public LogInfo getLogInfo() {
        return logInfoDao.getLogInfo();
    }

    public void saveLogInfo(LogInfo logInfo) {
        logInfoDao.save(logInfo);
    }
}
