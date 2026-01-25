package com.example.feed.post.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import com.example.feed.post.application.interfaces.PostRepository;
import com.example.feed.post.domain.Post;

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
  public Post findById(Long id) {
    return store.get(id);
  }

}
