package com.example.iuliana.controller;

import com.example.iuliana.model.Post;
import com.example.iuliana.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostRestController {
    @Autowired
    private PostService postService;

    @GetMapping(value = "/posts", produces = "application/json")
    public List<Post> getAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping(value = "/post/{id}", produces = "application/json")
    public Post getPostById(@PathVariable Long id){
        return postService.getPostById(id);
    }

    @GetMapping(value = "/delete-post/{id}", produces = "application/json")
    public Post delete(@PathVariable Long id){
        return postService.delete(id);
    }

    @PostMapping(value = "/save-post", produces = "application/json")
    public Post save(@RequestBody Post post){
        return postService.save(post);
    }

    @PostMapping(value = "/update-post/{id}", produces = "application/json")
    public Post update(@PathVariable Long id, @RequestBody Post post){
        return postService.update(id, post);
    }

}