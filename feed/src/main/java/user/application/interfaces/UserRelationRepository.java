package user.application.interfaces;

import user.domain.User;

public interface UserRelationRepository {

  boolean isFollowing(User follower, User followee);

  void save(User follower, User followee);

  void delete(User follower, User followee);

}