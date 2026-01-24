package post.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import post.application.interfaces.PostRepository;
import post.domain.Post;

public class FakePostRepository implements PostRepository {

  private final Map<Long, Post> store = new HashMap<>();

  @Override
  public Post save(Post post) {
    if (post.getId() == null) {
      post = new Post(store.size() + 1L, post.getAuthor(), post.getContent(), post.getStatus());
    }
    store.put(post.getId(), post);
    return post;
  }

  @Override
  public Optional<Post> findById(Long id) {
    return Optional.ofNullable(store.get(id));
  }

}
