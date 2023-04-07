package com.example.service.commentsservice;

import com.example.dao.CommentsDAO;
import com.example.models.comments.Comments;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentsService {
    private CommentsDAO commentsDAO;

    public void save(Comments comments){
        commentsDAO.save(comments);
    }

    public ResponseEntity<List<Comments>> getComments(){
        List<Comments> commentsList = commentsDAO.findAll();
        return new ResponseEntity<>(commentsList, HttpStatusCode.valueOf(200));
    }

    public void updateCommentById(Comments comments){
        commentsDAO.save(comments);
    }

    public Comments getCommentById(int id){
        return commentsDAO.findById(id).get();
    }

    public void deleteUser(int id){
        commentsDAO.deleteById(id);
    }


}
