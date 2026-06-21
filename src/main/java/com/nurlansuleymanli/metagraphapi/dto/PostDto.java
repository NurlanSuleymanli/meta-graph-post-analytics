package com.nurlansuleymanli.metagraphapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostDto {

    String id;
    String caption;
    String timestamp;
    @JsonProperty("like_count")
    int likeCount;
    @JsonProperty("comments_count")
    int commentsCount;
    int engagement;

}
