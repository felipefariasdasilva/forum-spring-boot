package com.felipe.forum.controller.form;

import com.felipe.forum.model.Curse;
import com.felipe.forum.model.Topic;
import com.felipe.forum.repository.CurseRepository;

public class TopicForm {

    private String title;
    private String message;
    private String curseName;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCurseName() {
        return curseName;
    }

    public void setCurseName(String curseName) {
        this.curseName = curseName;
    }

    public Topic convert(CurseRepository curseRepository) {

        Curse curse = curseRepository.findByName(curseName);
        return new Topic(title, message, curse);
    }
}
