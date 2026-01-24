package post.domain.comment;

import common.domain.PositiveIntegerCount;
import post.domain.Post;
import post.domain.content.Content;
import user.domain.User;

public class Comment {

  private final Long id;
  private final Post post;
  private final User author;
  private final Content content;
  private final PositiveIntegerCount likeCount;

  public Comment(Long id, Post post, User author, Content content) {
    if (author == null || post == null || content == null) {
      throw new IllegalArgumentException();
    }

    this.id = id;
    this.post = post;
    this.author = author;
    this.content = content;
    this.likeCount = new PositiveIntegerCount();
  }

  public int getLikeCount() {
    return likeCount.getCount();
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

  public void update(User user, String text) {
    if (!author.equals(user)) {
      throw new IllegalArgumentException();
    }
    content.update(text);
  }

}
