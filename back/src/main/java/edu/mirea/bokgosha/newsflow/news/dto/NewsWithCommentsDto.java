package edu.mirea.bokgosha.newsflow.news.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class NewsWithCommentsDto {

    private Long id;
    private String author;
    private String title;
    private String content;
    private String image;
    private Integer likeCount;
    private List<CommentDto> comments;
    private Boolean liked;
}
