package com.nurlansuleymanli.metagraphapi.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Map;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnalysisDto {

    List<PostDto> topEngagementPosts;

    Map<String ,Integer> likesByDay;

    String summary;

}
