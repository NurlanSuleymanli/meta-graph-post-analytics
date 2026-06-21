package com.nurlansuleymanli.metagraphapi.service;

import com.nurlansuleymanli.metagraphapi.dto.AnalysisDto;
import com.nurlansuleymanli.metagraphapi.dto.PostDto;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AnalysisService {

    public AnalysisDto analyzePosts(List<PostDto> posts) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");

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
                            OffsetDateTime date = OffsetDateTime.parse(postDto.getTimestamp(), formatter);
                            return date.getDayOfWeek().toString();
                        },
                        Collectors.summingInt(PostDto::getLikeCount)
                ));

        Map<Integer, Integer> likesByHour = posts.stream()
                .collect(Collectors.groupingBy(postDto -> {
                    OffsetDateTime date = OffsetDateTime.parse(postDto.getTimestamp(), formatter);
                    return date.getHour();
                },
                  Collectors.summingInt(PostDto::getLikeCount)
                ));




        String summary = buildSummary(posts, topPosts, likesByDay);

        return AnalysisDto.builder()
                .likesByDay(likesByDay)
                .topEngagementPosts(topPosts)
                .likesByHour(likesByHour)
                .summary(summary)
                .build();
    }

    private String buildSummary(List<PostDto> posts,
                                List<PostDto> topPosts,
                                Map<String, Integer> likesByDay) {

        int totalPosts = posts.size();

        double avgEngagement = posts.stream()
                .mapToInt(PostDto::getEngagement)
                .average()
                .orElse(0);

        int topEngagement = topPosts.isEmpty() ? 0 : topPosts.getFirst().getEngagement();

        String bestDay = likesByDay.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("unknown");

        int bestDayLikes = likesByDay.getOrDefault(bestDay, 0);

        return "Analyzed " + totalPosts + " posts. " +
                "Average engagement per post: " + (int) avgEngagement + " (likes + comments). " +
                "Best performing day: " + bestDay + " with " + bestDayLikes + " total likes. " +
                "Top post reached " + topEngagement + " engagements. " +
                "Focus on posting on " + bestDay + " for maximum reach.";
    }

}
