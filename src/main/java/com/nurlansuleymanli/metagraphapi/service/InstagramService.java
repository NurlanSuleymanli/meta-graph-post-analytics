package com.nurlansuleymanli.metagraphapi.service;

import com.nurlansuleymanli.metagraphapi.client.MetaGraphClient;
import com.nurlansuleymanli.metagraphapi.dto.MetaResponseDto;
import com.nurlansuleymanli.metagraphapi.dto.PostDto;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InstagramService {

    MetaGraphClient metaGraphClient;
    Long userId;
    String accessToken;

    public InstagramService(MetaGraphClient metaGraphClient,
                            @Value("${instagram.user-id}") Long userId,
                            @Value("${instagram.access-token}") String accessToken) {
        this.metaGraphClient = metaGraphClient;
        this.userId=userId;
        this.accessToken=accessToken;
    }


    public List<PostDto> getPosts(){

        MetaResponseDto metaResponseDto = metaGraphClient.getPosts
                (userId, "id,caption,timestamp,like_count,comments_count",
                20, accessToken);

        return metaResponseDto.getData()!=null ? metaResponseDto.getData() : List.of();


    }


}
