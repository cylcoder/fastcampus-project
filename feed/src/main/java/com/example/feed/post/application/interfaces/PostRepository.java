package com.example.feed.post.application.interfaces;

import com.example.feed.post.domain.Post;

public interface PostRepository {

  Post save(Post post);

  Post findById(Long id);

}
