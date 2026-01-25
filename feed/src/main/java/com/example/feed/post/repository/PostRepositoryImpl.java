package com.example.feed.post.repository;

import com.example.feed.post.application.interfaces.PostRepository;
import com.example.feed.post.domain.Post;
import com.example.feed.post.repository.entity.post.PostEntity;
import com.example.feed.post.repository.jpa.JpaPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

  private final JpaPostRepository jpaPostRepository;

  @Override
  public Post save(Post post) {
    PostEntity postEntity = PostEntity.from(post);
    jpaPostRepository.save(postEntity);
    return postEntity.toPost();
  }

  @Override
  public Post findById(Long id) {
    PostEntity postEntity = jpaPostRepository.findById(id).orElseThrow();
    return postEntity.toPost();
  }

}
