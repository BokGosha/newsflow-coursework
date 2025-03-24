package edu.mirea.bokgosha.newsflow.news.service;

import edu.mirea.bokgosha.newsflow.news.dto.CommentCreationDto;
import edu.mirea.bokgosha.newsflow.news.dto.CommentDto;
import edu.mirea.bokgosha.newsflow.news.dto.NewsDto;
import edu.mirea.bokgosha.newsflow.news.dto.NewsWithCommentsDto;
import edu.mirea.bokgosha.newsflow.news.entity.News;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NewsService {
    NewsDto saveNews(News news, MultipartFile image, long userId);

    NewsWithCommentsDto getNews(long newsId, long userId);

    List<NewsDto> getAllNews(long userId);

    NewsDto removeNews(long newsId);

    void saveNewsLike(long newsId, long userId);

    void removeNewsLike(long newsId, long userId);

    CommentDto saveComment(CommentCreationDto commentDto, long newsId, long userId);
}
