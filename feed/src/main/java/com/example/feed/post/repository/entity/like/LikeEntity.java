package com.example.feed.post.repository.entity.like;

import com.example.feed.post.domain.Post;
import com.example.feed.post.domain.comment.Comment;
import com.example.feed.user.domain.User;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "likes")
@NoArgsConstructor
@AllArgsConstructor
public class LikeEntity {

  @EmbeddedId
  private LikeIdEntity id;

  public LikeEntity(Post post, User user) {
    id = new LikeIdEntity(post.getId(), user.getId(), LikeTarget.POST.name());
  }

  public LikeEntity(Comment comment, User user) {
    id = new LikeIdEntity(comment.getId(), user.getId(), LikeTarget.COMMENT.name());
  }

}
