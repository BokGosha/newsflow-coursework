package edu.mirea.bokgosha.newsflow.news.repository;

import edu.mirea.bokgosha.newsflow.news.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
}
