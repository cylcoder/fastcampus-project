package com.example.feed.user.ui;

import com.example.feed.common.ui.Response;
import com.example.feed.user.application.RelationService;
import com.example.feed.user.application.dto.FollowRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/relations")
public class RelationController {

  private final RelationService relationService;

  @PostMapping("/follow")
  public Response follow(@RequestBody FollowRequest request) {
    relationService.follow(request);
    return Response.ok();
  }

  @PostMapping("/unfollow")
  public Response unfollow(@RequestBody FollowRequest request) {
    relationService.unfollow(request);
    return Response.ok();
  }

}
