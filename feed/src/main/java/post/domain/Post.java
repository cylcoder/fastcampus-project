package post.domain;

import static post.domain.content.PostStatus.PUBLIC;

import common.domain.PositiveIntegerCount;
import lombok.Getter;
import post.domain.content.PostContent;
import post.domain.content.PostStatus;
import user.domain.User;

@Getter
public class Post {

  private final Long id;
  private final User author;
  private final PostContent content;
  private final PositiveIntegerCount likeCount;
  private PostStatus status;

  public Post(Long id, User author, PostContent content) {
    if (author == null) {
      throw new IllegalArgumentException();
    }
    this.id = id;
    this.author = author;
    this.content = content;
    this.likeCount = new PositiveIntegerCount();
    this.status = PUBLIC;
  }

  public int getLikeCount() {
    return likeCount.getCount();
  }

  public String getContent() {
    return content.getText();
  }

  public void like(User user) {
    if (author.equals(user)) {
      throw new IllegalArgumentException();
    }
    likeCount.increase();
  }

  public void unlike(User user) {
    if (author.equals(user)) {
      throw new IllegalArgumentException();
    }
    likeCount.decrease();
  }

  public void update(User user, String text, PostStatus status) {
    if (!author.equals(user)) {
      throw new IllegalArgumentException();
    }
    content.update(text);
    this.status = status;
  }

}
