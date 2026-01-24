package com.example.feed.post.application.interfaces;

import java.util.Optional;
import com.example.feed.post.domain.Post;

public interface PostRepository {

  Post save(Post post);

  Optional<Post> findById(Long id);

}
