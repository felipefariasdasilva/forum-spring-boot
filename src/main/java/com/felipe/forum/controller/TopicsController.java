package com.felipe.forum.controller;

import com.felipe.forum.controller.dto.TopicDTO;
import com.felipe.forum.controller.form.TopicForm;
import com.felipe.forum.model.Topic;
import com.felipe.forum.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicsController {

    @Autowired
    private TopicRepository topicRepository;

    @GetMapping
    public List<TopicDTO> list(String curseName){
        if(curseName == null){
            List<Topic> topics = topicRepository.findAll();
            return TopicDTO.convert(topics);
        }else {
            List<Topic> topics = topicRepository.findByCurseName(curseName);
            return TopicDTO.convert(topics);
        }
    }

    @PostMapping
    public void create(@RequestBody TopicForm form){
        Topic topic = form.convert();
        topicRepository.save(topic);
    }
}
