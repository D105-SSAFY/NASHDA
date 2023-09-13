package com.ssafy.nashda.member.repository;
import com.ssafy.nashda.member.dto.Request.MemberSignUpReqDto;
import com.ssafy.nashda.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    //email을 조회해서 해당하는 사용자가 있는지 확인
    Optional<Member> findByEmail(String email);
}
