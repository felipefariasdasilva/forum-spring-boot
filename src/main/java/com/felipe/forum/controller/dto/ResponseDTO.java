package com.felipe.forum.controller.dto;

import com.felipe.forum.model.Response;

import java.time.LocalDateTime;

public class ResponseDTO {

    private Long id;
    private String message;
    private LocalDateTime creationDate;
    private String authorName;

    public ResponseDTO(Response response) {
        this.id = response.getId();
        this.message = response.getMessage();
        this.creationDate = response.getCreationDate();
        this.authorName = response.getAuthor().getName();
    }

    public Long getId() {
        return id;
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
}
