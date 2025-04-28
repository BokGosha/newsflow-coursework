package edu.mirea.bokgosha.newsflow.news.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.mirea.bokgosha.newsflow.news.dto.CommentCreationDto;
import edu.mirea.bokgosha.newsflow.news.dto.CommentDto;
import edu.mirea.bokgosha.newsflow.news.dto.NewsDto;
import edu.mirea.bokgosha.newsflow.news.dto.NewsWithCommentsDto;
import edu.mirea.bokgosha.newsflow.news.entity.News;
import edu.mirea.bokgosha.newsflow.news.service.NewsService;
import edu.mirea.bokgosha.newsflow.security.auth.principal.UserPrincipal;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    @PostMapping
    public NewsDto createPost(@AuthenticationPrincipal UserPrincipal userPrincipal,
                              @RequestParam("image") MultipartFile image,
                              @RequestParam("json") String json) {
        long userId = userPrincipal.getUserId();
        News news = objectMapper.readValue(json, News.class);
        return newsService.saveNews(news, image, userId);
    }

    @GetMapping("/{id}")
    public NewsWithCommentsDto getNews(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                       @PathVariable Long id) {
        long userId = userPrincipal.getUserId();
        return newsService.getNews(id, userId);
    }

    @GetMapping
    public List<NewsDto> getAllNews(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        long userId = userPrincipal.getUserId();
        return newsService.getAllNews(userId);
    }

    @DeleteMapping("/{id}")
    public NewsDto deleteNews(@PathVariable Long id) {
        return newsService.removeNews(id);
    }

    @PutMapping("/{id}/likes")
    public NewsWithCommentsDto putLike(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Long id) {
        long userId = userPrincipal.getUserId();
        newsService.saveNewsLike(id, userId);
        return newsService.getNews(id, userId);
    }

    @DeleteMapping("/{id}/likes")
    public NewsWithCommentsDto deleteLike(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Long id) {
        long userId = userPrincipal.getUserId();
        newsService.removeNewsLike(id, userId);
        return newsService.getNews(id, userId);
    }

    @PostMapping("/{id}/comments")
    public CommentDto createComment(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                    @Valid @RequestBody CommentCreationDto commentDto,
                                    @PathVariable Long id) {
        long userId = userPrincipal.getUserId();
        return newsService.saveComment(commentDto, id, userId);
    }
}
