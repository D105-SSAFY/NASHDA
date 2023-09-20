package com.ssafy.nashda.question.repository;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.question.entity.Question;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Modifying(clearAutomatically = true)
    @Query("SELECT q FROM Question q WHERE q.member.memberNum = :memberNum")
    List<Question> findQuestionsByMember(Long memberNum);
}
