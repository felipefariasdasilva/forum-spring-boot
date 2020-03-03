package com.felipe.forum.controller;

import com.felipe.forum.controller.dto.DetailsOfTopicDTO;
import com.felipe.forum.controller.dto.TopicDTO;
import com.felipe.forum.controller.form.TopicForm;
import com.felipe.forum.model.Topic;
import com.felipe.forum.repository.CurseRepository;
import com.felipe.forum.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicsController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CurseRepository curseRepository;

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
    public ResponseEntity<TopicDTO> create(@RequestBody @Valid TopicForm form, UriComponentsBuilder uriComonentsBuilder){
        Topic topic = form.convert(curseRepository);
        topicRepository.save(topic);

        URI uri = uriComonentsBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicDTO(topic));
    }

    @GetMapping("/{id}")
    public DetailsOfTopicDTO detail(@PathVariable Long id){
        Topic topic = topicRepository.getOne(id);
        return new DetailsOfTopicDTO(topic);
    }
}
