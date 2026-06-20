package com.nurlansuleymanli.metagraphapi.client;

import com.nurlansuleymanli.metagraphapi.dto.MetaResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "Meta-Graph", url = "https://graph.facebook.com/v25.0")
public interface MetaGraphClient {

    @GetMapping("/{userId}/media")
    public MetaResponseDto getPosts(@PathVariable Long userId,
                                    @RequestParam String field,
                                    @RequestParam int limit,
                                    @RequestParam("access_token") String token);
}
