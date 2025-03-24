package edu.mirea.bokgosha.newsflow.news.repository;

import edu.mirea.bokgosha.newsflow.news.entity.NewsLike;
import edu.mirea.bokgosha.newsflow.news.entity.UserAndPostPrimaryKey;
import edu.mirea.bokgosha.newsflow.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<NewsLike, UserAndPostPrimaryKey> {

    List<NewsLike> findByUser(User user);
}
