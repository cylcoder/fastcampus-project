package user.repository;

import java.util.HashSet;
import java.util.Set;
import user.application.interfaces.UserRelationRepository;
import user.domain.User;

public class FakeUserRelationRepository implements UserRelationRepository {

  private final Set<Relation> store = new HashSet<>();

  @Override
  public boolean isFollowing(User follower, User followee) {
    Relation targetRelation = new Relation(follower.getId(), followee.getId());
    return store.stream()
        .anyMatch(savedRelation -> savedRelation.equals(targetRelation));
  }

  @Override
  public void save(User follower, User followee) {
    Relation relation = new Relation(follower.getId(), followee.getId());
    store.add(relation);
  }

  @Override
  public void delete(User follower, User followee) {
    Relation relation = new Relation(follower.getId(), followee.getId());
    store.remove(relation);
  }

}

record Relation(Long followerId, Long followeeId) {

}