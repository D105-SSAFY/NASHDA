package com.ssafy.nashda.question.repository;

import com.ssafy.nashda.question.entity.Question;
import com.ssafy.nashda.question.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    @Modifying(clearAutomatically = true)
    @Query("DELETE Reply c WHERE c.question.index = :questionIndex")
    void deleteByQuestionIndex(Long questionIndex);

    Optional<Reply> findByQuestion(Question question);

}