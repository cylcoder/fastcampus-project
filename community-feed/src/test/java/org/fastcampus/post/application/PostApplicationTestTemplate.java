package org.fastcampus.post.application;

import org.fastcampus.post.application.dto.CreateCommentRequestDto;
import org.fastcampus.post.application.dto.CreatePostRequestDto;
import org.fastcampus.post.domain.Post;
import org.fastcampus.user.application.UserService;
import org.fastcampus.user.application.dto.CreateUserRequestDto;
import org.fastcampus.user.domain.User;

import static org.fastcampus.fake.FakeObjectFactory.*;
import static org.fastcampus.post.domain.content.PostPublicationState.PUBLIC;

public class PostApplicationTestTemplate {

    final UserService userService = getUserService();
    final PostService postService = getPostService();
    final CommentService commentService = getCommentService();
    final User user = userService.createUser(new CreateUserRequestDto("John", "https://s3.amazonaws.com/john"));
    final User otherUser = userService.createUser(new CreateUserRequestDto("Jane", "https://s3.amazonaws.com/jane"));

    final CreatePostRequestDto postRequestDto = new CreatePostRequestDto(user.getId(), "Hello, world!", PUBLIC);
    final Post post = postService.createPost(postRequestDto);

    final CreateCommentRequestDto commentRequestDto = new CreateCommentRequestDto(post.getId(), user.getId(), "Nice to meet you!");

}
