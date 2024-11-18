package org.fastcampus.post.repository.entity.like;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.fastcampus.post.domain.Post;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class LikeIdEntity {

    private Long targetId;
    private Long userId;
    private String targetType;

}
