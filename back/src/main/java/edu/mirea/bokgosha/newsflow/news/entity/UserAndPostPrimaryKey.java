package edu.mirea.bokgosha.newsflow.news.entity;

import edu.mirea.bokgosha.newsflow.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UserAndPostPrimaryKey {

    private User user;
    private News news;
}
