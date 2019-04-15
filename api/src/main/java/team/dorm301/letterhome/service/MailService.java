package team.dorm301.letterhome.service;

public interface MailService {

    void sendEMail(String target, String subject, String content) throws Exception;
}
