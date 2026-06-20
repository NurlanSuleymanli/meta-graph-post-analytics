package com.nurlansuleymanli.metagraphapi.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostDto {

    String id;
    String caption;
    String timestamp;
    int likeCount;
    int commentsCount;
    int engagement;

}
