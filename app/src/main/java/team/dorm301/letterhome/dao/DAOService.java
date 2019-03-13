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

    /**
     * 获取当前登录用户
     *
     * @return
     */
    public LogInfo getCurrentLogInfo() {
        //TODO
        return null;
    }
}
