package com.ssafy.nashda.member.service;

import com.ssafy.nashda.common.error.code.ErrorCode;
import com.ssafy.nashda.common.error.exception.BadRequestException;
import com.ssafy.nashda.member.dto.Reponse.MemberInfoResDto;
import com.ssafy.nashda.member.dto.Request.MemberSignInReqDto;
import com.ssafy.nashda.member.dto.Request.MemberSignUpReqDto;
import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.member.repository.MemberRepository;
import com.ssafy.nashda.statistic.service.PracticeStatisticService;
import com.ssafy.nashda.statistic.entity.Strick;
import com.ssafy.nashda.statistic.repository.StrickRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@Service("MemberService")
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final PracticeStatisticService practiceStatisticService;
    private final StrickRepository strickRepository;


    @Override
    @Transactional
    public void signUp(MemberSignUpReqDto signUpReqDto) {
        //데이터베이스에서 해당 사용자와 동일한 email을 가진 유저가 있는지 검색한다.
        Optional<Member> exitMember = memberRepository.findByEmail(signUpReqDto.getEmail());
        //비어있다면? 중복된 회원이 없다는 것 -> 회원가입 가능
        if (exitMember.isEmpty()) {
            String encodedPassword = passwordEncoder.encode(signUpReqDto.getPassword());
            Member member = new Member(signUpReqDto.getEmail(),
                    signUpReqDto.getName(),
                    signUpReqDto.getNickname(),
                    encodedPassword, // 이미 인코딩된 비밀번호를 사용
                    signUpReqDto.getAge(),
                    signUpReqDto.getHobbyIdx(),
                    signUpReqDto.getJobIdx());
            memberRepository.save(member);

            // 발음 통계 전부 저장
            try {
                practiceStatisticService.initializePracticeStatistic(member);
            } catch (Exception e){
                throw new BadRequestException(ErrorCode.SAVE_ERROR);
            }

        } else {
            throw new BadRequestException(ErrorCode.USER_EXIST);
        }
    }

    @Override
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email).orElseThrow(() -> new BadRequestException(ErrorCode.USER_NOT_EXIST));
    }

    @Override
    public Optional<Member> findByNickname(String nickname) {
        return Optional.ofNullable(memberRepository.findByNickname(nickname).orElseThrow(() -> new BadRequestException(ErrorCode.USER_NOT_EXIST)));
    }

    @Override
    @Transactional
    public MemberInfoResDto singIn(MemberSignInReqDto signinInfo) throws IOException, InterruptedException {
        Optional<Member> member = memberRepository.findByEmail(signinInfo.getEmail());
        if (member.isEmpty()) {
            throw new BadRequestException(ErrorCode.USER_NOT_EXIST);
        }
        if (passwordEncoder.matches(signinInfo.getPassword(), member.get().getPassword())) {

            Optional<Strick> optionalStrick = strickRepository.findByMemberAndCreatOn(member.get(), LocalDate.now());

            if (optionalStrick.isEmpty()) {
                Strick strick = Strick.builder()
                        .member(member.get())
                        .build();
                strickRepository.save(strick);
            }
            return new MemberInfoResDto(member.get());
        } else {
            throw new BadRequestException(ErrorCode.USER_NOT_MATCH);
        }

    }

    @Override
    public void unRegist(Map<String, Object> memberInfo) throws IOException {
        if (memberInfo.get("email") == null || memberInfo.get("password") == null)
            throw new BadRequestException(ErrorCode.INVALID_INPUT);
        Member member = memberRepository.findByEmail(memberInfo.get("email").toString()).orElseThrow(() -> new BadRequestException(ErrorCode.USER_NOT_EXIST));
        if (passwordEncoder.matches(memberInfo.get("password").toString(), member.getPassword())) {
            memberRepository.delete(member);
        } else {
            throw new BadRequestException(ErrorCode.USER_NOT_MATCH);
        }
    }

    @Override
    public boolean checkEmail(String email) throws IOException {
        Optional<Member> member = memberRepository.findByEmail(email);
        return member.isEmpty();
    }

    @Override
    public boolean checkNickname(String nickname) throws IOException {
        Optional<Member> member = memberRepository.findByNickname(nickname);
        return member.isEmpty();
    }

    @Override
    public MemberInfoResDto updateProfile(Map<String, Object> profileInfo) throws IOException {
        Member member = memberRepository.findByEmail(profileInfo.get("email").toString()).orElseThrow(() -> new BadRequestException(ErrorCode.USER_NOT_EXIST));
        if (profileInfo.get("nickname") != null) {
            member.setNickname(profileInfo.get("nickname").toString());
        }
        if (profileInfo.get("age") != null) {
            member.setAge(Integer.parseInt(profileInfo.get("age").toString()));
        }
        if (profileInfo.get("hobbyIdx") != null) {
            member.setHobbyIdx(Integer.parseInt(profileInfo.get("hobbyIdx").toString()));
        }
        if (profileInfo.get("jobIdx") != null) {
            member.setJobIdx(Integer.parseInt(profileInfo.get("jobIdx").toString()));
        }

        return new MemberInfoResDto(member);
    }

    @Override
    public void updatePassword(Map<String, Object> passwords) throws IOException {
        if (passwords.get("email") == null || passwords.get("password") == null || passwords.get("newpassword") == null)
            throw new BadRequestException(ErrorCode.INVALID_INPUT);
        Member member = memberRepository.findByEmail(passwords.get("email").toString()).orElseThrow(() -> new BadRequestException(ErrorCode.USER_NOT_EXIST));
        if (passwordEncoder.matches(passwords.get("password").toString(), member.getPassword())) {
            member.setPassword(passwordEncoder.encode(passwords.get("newpassword").toString()));
        } else {
            throw new BadRequestException(ErrorCode.USER_NOT_MATCH);
        }
    }

    @Override
    public void resetPassword(Map<String, Object> map) throws IOException {
        Member member = memberRepository.findByEmail(map.get("email").toString()).orElseThrow(() -> new BadRequestException(ErrorCode.USER_NOT_EXIST));
        member.setPassword(passwordEncoder.encode(map.get("newpassword").toString()));
    }

    @Override
    public boolean checkProgress(String email) throws IOException {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new BadRequestException(ErrorCode.USER_NOT_EXIST));
        return member.getProgress() > 9;
    }


}
