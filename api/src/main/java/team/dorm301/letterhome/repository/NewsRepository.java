package team.dorm301.letterhome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.dorm301.letterhome.entity.News;

public interface NewsRepository extends JpaRepository<News, Long> {
}
