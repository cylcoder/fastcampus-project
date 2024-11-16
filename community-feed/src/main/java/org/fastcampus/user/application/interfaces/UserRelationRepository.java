package org.fastcampus.user.application.interfaces;

import org.fastcampus.user.domain.User;

public interface UserRelationRepository {

    boolean isAlreadyFollowing(User user, User targetUser);

    void save(User user, User targetUser);

    void delete(User user, User targetUser);

}
