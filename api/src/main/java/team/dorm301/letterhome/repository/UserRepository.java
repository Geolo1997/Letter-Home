package team.dorm301.letterhome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.dorm301.letterhome.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
