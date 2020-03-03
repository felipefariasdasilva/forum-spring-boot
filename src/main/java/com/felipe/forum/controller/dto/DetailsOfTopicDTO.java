package com.felipe.forum.controller.dto;

import com.felipe.forum.model.Response;
import com.felipe.forum.model.StatusTopic;
import com.felipe.forum.model.Topic;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class DetailsOfTopicDTO {

    private Long id;
    private String title;
    private String message;
    private LocalDateTime creationDate;
    private String authorName;
    private StatusTopic statusTopic;
    private List<ResponseDTO> responseList;

    public DetailsOfTopicDTO(Topic topic) {
        this.id = topic.getId();
        this.title = topic.getTitle();
        this.message = topic.getMessage();
        this.creationDate = topic.getCreationDate();
        this.authorName = topic.getAuthor().getName();
        this.statusTopic = topic.getStatus();
        this.responseList = new ArrayList<>();
        this.responseList.addAll(topic.getResponses().stream().map(ResponseDTO::new).collect(Collectors.toList()));
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getAuthorName() {
        return authorName;
    }

    public StatusTopic getStatusTopic() {
        return statusTopic;
    }

    public List<ResponseDTO> getResponseList() {
        return responseList;
    }
}
