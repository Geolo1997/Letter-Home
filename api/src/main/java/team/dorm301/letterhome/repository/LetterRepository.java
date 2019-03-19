package team.dorm301.letterhome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.dorm301.letterhome.entity.Letter;

public interface LetterRepository extends JpaRepository<Letter, Long> {
}
