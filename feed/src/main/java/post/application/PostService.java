package post.application;

import lombok.RequiredArgsConstructor;
import post.application.dto.CreatePostRequest;
import post.application.dto.LikeRequest;
import post.application.dto.UpdatePostRequest;
import post.application.interfaces.LikeRepository;
import post.application.interfaces.PostRepository;
import post.domain.Post;
import user.application.UserService;
import user.domain.User;

@RequiredArgsConstructor
public class PostService {

  private final UserService userService;
  private final PostRepository postRepository;
  private final LikeRepository likeRepository;

  public Post getPost(Long id) {
    return postRepository.findById(id).orElseThrow();
  }

  public Post createPost(CreatePostRequest request) {
    User author = userService.getUser(request.userId());
    Post post = new Post(null, author, request.content(), request.status());
    return postRepository.save(post);
  }

  public Post updatePost(UpdatePostRequest request) {
    Post post = getPost(request.postId());
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
