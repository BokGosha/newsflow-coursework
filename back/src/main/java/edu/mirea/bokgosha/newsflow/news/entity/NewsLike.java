package edu.mirea.bokgosha.newsflow.news.entity;

import edu.mirea.bokgosha.newsflow.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "news_like")
@IdClass(UserAndPostPrimaryKey.class)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsLike {

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private News news;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private User user;
}
