package com.example.feed.post.repository.entity.post;

import com.example.feed.common.domain.PositiveIntegerCount;
import com.example.feed.common.repository.entity.BaseTimeEntity;
import com.example.feed.post.domain.Post;
import com.example.feed.post.domain.content.PostContent;
import com.example.feed.post.domain.content.PostStatus;
import com.example.feed.user.repository.entity.UserEntity;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "post")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostEntity extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String content;

  private int likeCount;

  @Convert(converter = PostStatusConverter.class)
  private PostStatus status;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private UserEntity author;

  public Post toPost() {
    return Post.builder()
        .id(id)
        .author(author.toUser())
        .content(new PostContent(content))
        .likeCount(new PositiveIntegerCount(likeCount))
        .status(status)
        .build();
  }

  public static PostEntity from(Post post) {
    return PostEntity.builder()
        .id(post.getId())
        .author(UserEntity.from(post.getAuthor()))
        .content(post.getContent())
        .likeCount(post.getLikeCount())
        .status(post.getStatus())
        .build();
  }

}
