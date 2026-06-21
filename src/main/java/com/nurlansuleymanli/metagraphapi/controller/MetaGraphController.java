package com.nurlansuleymanli.metagraphapi.controller;

import com.nurlansuleymanli.metagraphapi.dto.AnalysisDto;
import com.nurlansuleymanli.metagraphapi.dto.PostDto;
import com.nurlansuleymanli.metagraphapi.service.AnalysisService;
import com.nurlansuleymanli.metagraphapi.service.InstagramService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MetaGraphController {

    AnalysisService analysisService;
    InstagramService instagramService;

    @GetMapping("/analyze")
    public ResponseEntity<AnalysisDto> getAnalyzePosts(){

        List<PostDto> posts = instagramService.getPosts();

        return ResponseEntity.ok(analysisService.analyzePosts(posts));

    }

}
