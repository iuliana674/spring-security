package com.example.iuliana.service;

import com.example.iuliana.dao.PostDao;
import com.example.iuliana.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostDao postDao;

    public Post save(Post post){
        return postDao.save(post);
    }

    public Post getPostById(Long id){
        return postDao.findById(id).get();
    }

    public Post delete(Long id){
        Post post = getPostById(id);
        postDao.delete(post);
        return post;
    }

    public List<Post> getAllPosts(){
        return postDao.findAll();
    }

    public Post update(Long id, Post modifiedPost) {
        Post post = getPostById(id);
        mapPosts(post, modifiedPost);
        save(post);
        return post;
    }

    private void mapPosts(Post post, Post modifiedPost) {
        post.setAuthor(modifiedPost.getAuthor());
        post.setCategory(modifiedPost.getCategory());
        post.setFullText(modifiedPost.getFullText());
        post.setTitle(modifiedPost.getTitle());
        post.setViews(modifiedPost.getViews());
    }
}
