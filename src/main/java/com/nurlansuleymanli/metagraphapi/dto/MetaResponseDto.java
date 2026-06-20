package com.nurlansuleymanli.metagraphapi.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MetaResponseDto {

    List<PostDto> posts;

}
