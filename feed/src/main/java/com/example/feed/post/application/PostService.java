package com.example.feed.post.application;

import com.example.feed.post.application.dto.CreatePostRequest;
import com.example.feed.post.application.dto.LikeRequest;
import com.example.feed.post.application.dto.UpdatePostRequest;
import com.example.feed.post.application.interfaces.LikeRepository;
import com.example.feed.post.application.interfaces.PostRepository;
import com.example.feed.post.domain.Post;
import com.example.feed.user.application.UserService;
import com.example.feed.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

  private final UserService userService;
  private final PostRepository postRepository;
  private final LikeRepository likeRepository;

  public Post getPost(Long id) {
    return postRepository.findById(id);
  }

  public Post createPost(CreatePostRequest request) {
    User author = userService.getUser(request.userId());
    Post post = new Post(null, author, request.content(), request.status());
    return postRepository.save(post);
  }

  public Post updatePost(Long postId, UpdatePostRequest request) {
    Post post = getPost(postId);
    User user = userService.getUser(request.userId());
    post.update(user, request.content(), request.status());
    return postRepository.save(post);
  }

  public void likePost(LikeRequest request) {
    Post post = getPost(request.targetId());
    User user = userService.getUser(request.userId());

    if (likeRepository.existsByTargetAndUser(post, user)) {
      throw new IllegalArgumentException();
    }

    post.like(user);
    likeRepository.like(post, user);
  }

  public void unlikePost(LikeRequest request) {
    Post post = getPost(request.targetId());
    User user = userService.getUser(request.userId());

    if (!likeRepository.existsByTargetAndUser(post, user)) {
      throw new IllegalArgumentException();
    }

    post.unlike(user);
    likeRepository.unlike(post, user);
  }

}
