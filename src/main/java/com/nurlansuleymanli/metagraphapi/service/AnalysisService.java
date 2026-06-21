package com.nurlansuleymanli.metagraphapi.service;

import com.nurlansuleymanli.metagraphapi.dto.AnalysisDto;
import com.nurlansuleymanli.metagraphapi.dto.PostDto;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AnalysisService {

    public AnalysisDto analyzePosts(List<PostDto> posts){

        posts.forEach(post ->
                post.setEngagement(
                        post.getLikeCount() + post.getCommentsCount()
                )
        );

        List<PostDto> topPosts = posts.stream()
                .sorted(Comparator.comparing(PostDto::getEngagement).reversed())
                .limit(3)
                .toList();


        Map<String, Integer> likesByDay = posts.stream()
                .collect(Collectors.groupingBy(postDto -> {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");
                            OffsetDateTime date = OffsetDateTime.parse(postDto.getTimestamp(), formatter);
                            return date.getDayOfWeek().toString();
                        },
                        Collectors.summingInt(PostDto::getLikeCount)
                        ));

        return AnalysisDto.builder()
                .likesByDay(likesByDay)
                .topEngagementPosts(topPosts)
                .summary("The last 20 posts were analyzed and the 3 posts that received the most likes" +
                        " and comments and the number of likes of the posts by day of the week were presented to you." +
                        "These analyses were conducted based on the number of comments and likes.")
                .build();

    }


}
