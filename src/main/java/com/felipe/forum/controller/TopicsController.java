package com.felipe.forum.controller;

import com.felipe.forum.controller.dto.DetailsOfTopicDTO;
import com.felipe.forum.controller.dto.TopicDTO;
import com.felipe.forum.controller.dto.UpdateTopicForm;
import com.felipe.forum.controller.form.TopicForm;
import com.felipe.forum.model.Topic;
import com.felipe.forum.repository.CurseRepository;
import com.felipe.forum.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topics")
public class TopicsController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CurseRepository curseRepository;

    @GetMapping
    public Page<TopicDTO> list(@RequestParam(required = false) String curseName,
                               @PageableDefault (sort = "id", direction = Sort.Direction.ASC) Pageable pageable){

        if(curseName == null){
            Page<Topic> topics = topicRepository.findAll(pageable);
            return TopicDTO.convert(topics);
        }else {
            Page<Topic> topics = topicRepository.findByCurseName(curseName, pageable);
            return TopicDTO.convert(topics);
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicDTO> create(@RequestBody @Valid TopicForm form, UriComponentsBuilder uriComponentsBuilder){
        Topic topic = form.convert(curseRepository);
        topicRepository.save(topic);

        URI uri = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicDTO(topic));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailsOfTopicDTO> detail(@PathVariable Long id){

        Optional<Topic> topic = topicRepository.findById(id);
        if(topic.isPresent()){
            return ResponseEntity.ok(new DetailsOfTopicDTO(topic.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicDTO> update(@PathVariable Long id, @RequestBody @Valid UpdateTopicForm form){

        Optional<Topic> topicOptional = topicRepository.findById(id);
        if(topicOptional.isPresent()){
            Topic topic = form.update(id, topicRepository);
            return ResponseEntity.ok(new TopicDTO(topic));
        }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id){

        Optional<Topic> topicOptional = topicRepository.findById(id);
        if(topicOptional.isPresent()){
            topicRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
