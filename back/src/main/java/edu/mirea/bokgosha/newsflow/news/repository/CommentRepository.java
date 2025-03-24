package edu.mirea.bokgosha.newsflow.news.repository;

import edu.mirea.bokgosha.newsflow.news.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
