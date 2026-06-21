package com.nurlansuleymanli.metagraphapi.controller;

import com.nurlansuleymanli.metagraphapi.dto.AnalysisDto;
import com.nurlansuleymanli.metagraphapi.dto.PostDto;
import com.nurlansuleymanli.metagraphapi.service.AnalysisService;
import com.nurlansuleymanli.metagraphapi.service.InstagramService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WebController {

    InstagramService instagramService;
    AnalysisService analysisService;

    @GetMapping("/")
    public String getDashboard(Model model) {

            List<PostDto> posts = instagramService.getPosts();
            AnalysisDto analysis = analysisService.analyzePosts(posts);
            model.addAttribute("analysis", analysis);
            model.addAttribute("posts", posts);

            model.addAttribute("errorMessage", "Error fetching data from Meta API: " + e.getMessage());

        return "index";
    }
}
