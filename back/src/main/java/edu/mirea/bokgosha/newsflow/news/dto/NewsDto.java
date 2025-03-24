package edu.mirea.bokgosha.newsflow.news.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewsDto {

    private Long id;
    private String author;
    private String title;
    private String content;
    private String image;
    private Integer likeCount;
    private Boolean liked;
}
