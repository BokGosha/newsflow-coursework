package edu.mirea.bokgosha.newsflow.news.service;

import edu.mirea.bokgosha.newsflow.cloud.firebase.FirebaseStorageService;
import edu.mirea.bokgosha.newsflow.news.dto.CommentCreationDto;
import edu.mirea.bokgosha.newsflow.news.dto.CommentDto;
import edu.mirea.bokgosha.newsflow.news.dto.NewsDto;
import edu.mirea.bokgosha.newsflow.news.dto.NewsWithCommentsDto;
import edu.mirea.bokgosha.newsflow.news.entity.Comment;
import edu.mirea.bokgosha.newsflow.news.entity.News;
import edu.mirea.bokgosha.newsflow.news.entity.NewsLike;
import edu.mirea.bokgosha.newsflow.news.entity.UserAndPostPrimaryKey;
import edu.mirea.bokgosha.newsflow.news.mapper.CommentMapper;
import edu.mirea.bokgosha.newsflow.news.mapper.NewsMapper;
import edu.mirea.bokgosha.newsflow.news.repository.CommentRepository;
import edu.mirea.bokgosha.newsflow.news.repository.LikeRepository;
import edu.mirea.bokgosha.newsflow.news.repository.NewsRepository;
import edu.mirea.bokgosha.newsflow.user.entity.User;
import edu.mirea.bokgosha.newsflow.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;
    private final FirebaseStorageService storageService;
    private final NewsMapper newsMapper;
    private final CommentMapper commentMapper;

    @Transactional
    @SneakyThrows
    @Override
    public NewsDto saveNews(News news, MultipartFile image, long userId) {
        User user = findUserById(userId);
        String imageUrl = storageService.uploadNewsImage(image);

        news.setUser(user);
        news.setImage(imageUrl);

        return newsMapper.toDto(newsRepository.save(news), false);
    }

    @Transactional(readOnly = true)
    @Override
    public NewsWithCommentsDto getNews(long newsId, long userId) {
        User user = findUserById(userId);
        News news = findPostById(newsId);
        return newsMapper.toDtoWithComments(news, isLikeExist(news, user));
    }

    @Transactional(readOnly = true)
    @Override
    public List<NewsDto> getAllNews(long userId) {
        User user = findUserById(userId);
        List<News> news = newsRepository.findAll();

        List<NewsLike> likes = likeRepository.findByUser(user);
        Set<Long> likedPostIds = getLikedPostsIds(likes);

        return news.stream()
                .map(post -> {
                    boolean isLiked = likedPostIds.contains(post.getId());
                    return newsMapper.toDto(post, isLiked);
                }).toList();
    }

    @Transactional
    @Override
    public NewsDto removeNews(long newsId) {
        News news = findPostById(newsId);
        newsRepository.deleteById(newsId);
        return newsMapper.toDto(news, false);
    }

    @Transactional
    @Override
    public void saveNewsLike(long postId, long userId) {
        User user = findUserById(userId);
        News news = findPostById(postId);

        checkLikeNotExist(news, user);

        likeRepository.save(NewsLike.builder()
                .news(news)
                .user(user)
                .build()
        );
    }

    @Transactional
    @Override
    public void removeNewsLike(long newsId, long userId) {
        User user = findUserById(userId);
        News news = findPostById(newsId);

        checkLikeExist(news, user);

        likeRepository.deleteById(UserAndPostPrimaryKey.builder()
                .news(news)
                .user(user)
                .build()
        );
    }

    @Override
    public CommentDto saveComment(CommentCreationDto commentDto, long newsId, long userId) {
        User user = findUserById(userId);
        News news = findPostById(newsId);

        Comment comment = commentMapper.toEntity(commentDto);
        comment.setUser(user);
        comment.setNews(news);

        return commentMapper.toDto(commentRepository.save(comment));
    }

    private User findUserById(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));
    }

    private News findPostById(long userId) {
        return newsRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Post Not Found"));
    }

    private boolean isLikeExist(News news, User user) {
        return likeRepository.existsById(
                UserAndPostPrimaryKey.builder()
                        .news(news)
                        .user(user)
                        .build()
        );
    }

    private void checkLikeExist(News news, User user) {
        if (!isLikeExist(news, user)) {
            throw new RuntimeException("Like Not Found");
        }
    }

    private void checkLikeNotExist(News news, User user) {
        if (isLikeExist(news, user)) {
            throw new RuntimeException("Like Already Exists");
        }
    }

    private Set<Long> getLikedPostsIds(List<NewsLike> likedPosts) {
        return likedPosts.stream()
                .map(like -> like.getNews().getId())
                .collect(Collectors.toSet());
    }
}
