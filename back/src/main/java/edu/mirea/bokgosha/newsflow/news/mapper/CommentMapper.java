package edu.mirea.bokgosha.newsflow.news.mapper;

import edu.mirea.bokgosha.newsflow.news.dto.CommentCreationDto;
import edu.mirea.bokgosha.newsflow.news.dto.CommentDto;
import edu.mirea.bokgosha.newsflow.news.entity.Comment;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
public class CommentMapper {

    public CommentDto toDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .author(comment.getUser().getUsername())
                .build();
    }

    public List<CommentDto> toDtoList(List<Comment> comments) {
        return comments.stream()
                .map(this::toDto)
                .toList();
    }

    public Comment toEntity(CommentCreationDto dto) {
        return Comment.builder()
                .content(dto.getContent())
                .build();
    }
}
