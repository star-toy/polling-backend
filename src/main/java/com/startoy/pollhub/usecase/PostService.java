package com.startoy.pollhub.usecase;

import com.startoy.pollhub.domain.Post;
import com.startoy.pollhub.adapter.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PostService {
    private final PostRepository PostRepository;

    public PostService(PostRepository PostRepository) {
        this.PostRepository = PostRepository;
    }

    // 모든 게시글을 조회
    public List<Post> getAllPosts() {
        return PostRepository.findAll();
    }

    // 특정 ID의 게시글을 조회
    public Optional<Post> getPostById(Long id) {
        return PostRepository.findById(id);
    }

    // 새로운 게시글을 생성
    public Post createPost(Post post) {
        return PostRepository.save(post);
    }

    // 특정 ID의 게시글을 업데이트
    public Post updatePost(Long id, Post post) {
        Optional<Post> existingPost = PostRepository.findById(id);
        if (existingPost.isPresent()) {
            Post toUpdate = existingPost.get();
            toUpdate.setTitle(post.getTitle());
            return PostRepository.save(toUpdate);
        }
        return null;
    }

    public void deletePost(Long id) {
        PostRepository.deleteById(id);
    }
}
