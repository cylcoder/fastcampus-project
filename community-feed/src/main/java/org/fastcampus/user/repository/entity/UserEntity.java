package org.fastcampus.user.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.fastcampus.common.domain.PositiveIntegerCounter;
import org.fastcampus.common.repository.entity.TimeBaseEntity;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;

@Entity
@Table(name = "community_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 게시글은 repository를 통한 조회
    /*@OneToMany(mappedBy = "author")
    private List<PostEntity> posts;*/

    private String name;

    private String profileImage;

    private Integer followerCount;

    private Integer followingCount;

    public UserEntity(User user) {
        id = user.getId();
        name = user.getName();
        profileImage = user.getProfileImage();
        followerCount = user.getFollowerCount();
        followingCount = user.getFollowingCount();
    }

    public User toUser() {
        return User.builder()
                .id(id)
                .userInfo(new UserInfo(name, profileImage))
                .followerCount(new PositiveIntegerCounter(followerCount))
                .followingCount(new PositiveIntegerCounter(followingCount))
                .build();
    }

}
