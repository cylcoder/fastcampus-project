package post.repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import post.application.interfaces.LikeRepository;
import post.domain.Post;
import post.domain.comment.Comment;
import user.domain.User;

public class FakeLikeRepository implements LikeRepository {

  private final Map<Post, Set<User>> postLikes = new HashMap<>();
  private final Map<Comment, Set<User>> commentLikes = new HashMap<>();

  @Override
  public boolean existsByTargetAndUser(Post post, User user) {
    return postLikes.getOrDefault(post, new HashSet<>()).contains(user);
  }

  @Override
  public boolean existsByTargetAndUser(Comment comment, User user) {
    return commentLikes.getOrDefault(comment, new HashSet<>()).contains(user);
  }

  @Override
  public void like(Post post, User user) {
    postLikes.computeIfAbsent(post, key -> new HashSet<>()).add(user);
  }

  @Override
  public void like(Comment comment, User user) {
    commentLikes.computeIfAbsent(comment, key -> new HashSet<>()).add(user);
  }

  @Override
  public void unlike(Post post, User user) {
    postLikes.computeIfPresent(post, (key, users) -> {
      users.remove(user);
      return users;
    });
  }

  @Override
  public void unlike(Comment comment, User user) {
    commentLikes.computeIfPresent(comment, (key, users) -> {
      users.remove(user);
      return users;
    });
  }

}
