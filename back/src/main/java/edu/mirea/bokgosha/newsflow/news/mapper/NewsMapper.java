package edu.mirea.bokgosha.newsflow.news.mapper;

import edu.mirea.bokgosha.newsflow.news.dto.NewsDto;
import edu.mirea.bokgosha.newsflow.news.dto.NewsWithCommentsDto;
import edu.mirea.bokgosha.newsflow.news.entity.News;
import edu.mirea.bokgosha.newsflow.news.entity.NewsLike;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
@RequiredArgsConstructor
public class NewsMapper {

    private final CommentMapper commentMapper;

    public NewsDto toDto(News news, boolean liked) {
        List<NewsLike> likes = news.getLikes();
        return NewsDto.builder()
                .id(news.getId())
                .title(news.getTitle())
                .content(news.getContent())
                .image(news.getImage())
                .author(news.getUser().getUsername())
                .liked(liked)
                .likeCount(likes == null ? 0 : likes.size())
                .build();
    }

    public NewsWithCommentsDto toDtoWithComments(News news, boolean liked) {
        List<NewsLike> likes = news.getLikes();
        return NewsWithCommentsDto.builder()
                .id(news.getId())
                .author(news.getUser().getUsername())
                .title(news.getTitle())
                .content(news.getContent())
                .image(news.getImage())
                .likeCount(likes == null ? 0 : likes.size())
                .liked(liked)
                .comments(commentMapper.toDtoList(news.getComments()))
                .build();
    }
}
