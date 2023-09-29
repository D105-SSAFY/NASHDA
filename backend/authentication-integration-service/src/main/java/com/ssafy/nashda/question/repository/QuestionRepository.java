package com.ssafy.nashda.question.repository;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByMember(Member member);
}
