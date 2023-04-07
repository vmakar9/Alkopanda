package com.example.dao;

import com.example.models.comments.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsDAO extends JpaRepository<Comments,Integer> {

}
