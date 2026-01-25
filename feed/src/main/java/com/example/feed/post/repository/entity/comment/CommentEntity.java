package com.example.feed.post.repository.entity.comment;

import com.example.feed.common.domain.PositiveIntegerCount;
import com.example.feed.common.repository.entity.BaseTimeEntity;
import com.example.feed.post.domain.comment.Comment;
import com.example.feed.post.domain.content.CommentContent;
import com.example.feed.post.repository.entity.post.PostEntity;
import com.example.feed.user.repository.entity.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comment")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentEntity extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String content;

  private int likeCount;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserEntity author;

  @ManyToOne
  @JoinColumn(name = "post_id")
  private PostEntity post;

  public Comment toComment() {
    return Comment.builder()
        .id(id)
        .post(post.toPost())
        .author(author.toUser())
        .content(new CommentContent(content))
        .likeCount(new PositiveIntegerCount(likeCount))
        .build();
  }

  public static CommentEntity from(Comment comment) {
    return CommentEntity.builder()
        .id(comment.getId())
        .content(comment.getContent())
        .likeCount(comment.getLikeCount())
        .author(UserEntity.from(comment.getAuthor()))
        .post(PostEntity.from(comment.getPost()))
        .build();
  }

}
