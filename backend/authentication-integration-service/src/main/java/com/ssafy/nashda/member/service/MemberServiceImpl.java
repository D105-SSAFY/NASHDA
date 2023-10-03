package com.ssafy.nashda.member.service;

import com.ssafy.nashda.common.error.code.ErrorCode;
import com.ssafy.nashda.common.error.exception.BadRequestException;
import com.ssafy.nashda.history.service.MemberHistoryService;
import com.ssafy.nashda.member.dto.request.MemberResetPasswordReqDto;
import com.ssafy.nashda.member.dto.response.MemberInfoResDto;
import com.ssafy.nashda.member.dto.request.MemberSignInReqDto;
import com.ssafy.nashda.member.dto.request.MemberSignUpReqDto;
//import com.ssafy.nashda.member.dto.response.MemberStatisticResDto;
import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.member.repository.MemberRepository;
import com.ssafy.nashda.statistic.service.GameStatisticService;
import com.ssafy.nashda.statistic.service.PracticeStatisticService;
import com.ssafy.nashda.statistic.service.StrickService;
import com.ssafy.nashda.statistic.service.WeekTestStatisticService;
import com.ssafy.nashda.week.entity.Week;
import com.ssafy.nashda.week.service.WeekService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Service("MemberService")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final PracticeStatisticService practiceStatisticService;
    private final StrickService strickService;
    private final WeekService weekService;
    private final GameStatisticService gameStatisticService;
    private final WeekTestStatisticService weekTestStatisticService;
    private final MemberHistoryService memberHistoryService;


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

            try {
                memberHistoryService.initMemberHistory(member);
            }catch(Exception e){
                throw new BadRequestException(ErrorCode.INTTERNAL_HISTORY_CREATE_ERROR);
            }


            // 발음 통계 전부 저장
            try {
                practiceStatisticService.initializePracticeStatistic(member);
            } catch (Exception e) {
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
        Member member = memberRepository.findByEmail(signinInfo.getEmail()).orElseThrow(() -> new BadRequestException(ErrorCode.USER_NOT_EXIST));

        Week week = weekService.getCurrentWeek().orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_DATA));
        if (passwordEncoder.matches(signinInfo.getPassword(), member.getPassword())) {
            //strick 생성, 로그인 할때마다
            if (!strickService.isExistStrick(member)) {
                strickService.initStrick(member);
            }

            /*
            //gamestatistic 생성, 로그인 할때마다
            if (!gameStatisticService.isExistGameStatistic(member, week)) {
                gameStatisticService.initGameStatistic(member, week);
            }

            //week_test생성
            if (!weekTestStatisticService.isExistWeekTestResult(member, week)) {
                weekTestStatisticService.initWeekTestResult(member, week);
            }

            */

            //member_history 생성
            if (!memberHistoryService.isExistMemberHistory(member)) {
                memberHistoryService.initMemberHistory(member);
            }

            return new MemberInfoResDto(member);
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
            memberRepository.unRegist(member.getMemberNum());
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
        if (profileInfo.get("hobby") != null) {
            member.setHobbyIdx(Integer.parseInt(profileInfo.get("hobby").toString()));
        }
        if (profileInfo.get("job") != null) {
            member.setJobIdx(Integer.parseInt(profileInfo.get("job").toString()));
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
    public void resetPassword(MemberResetPasswordReqDto reqDto) throws IOException {
        Member member = memberRepository.findByEmail(reqDto.getEmail()).orElseThrow(() -> new BadRequestException(ErrorCode.USER_NOT_EXIST));
        member.setPassword(passwordEncoder.encode(reqDto.getNewpassword()));
    }

    @Override
    public boolean checkProgress(String email) throws IOException {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new BadRequestException(ErrorCode.USER_NOT_EXIST));
        return member.getProgress() > 9;
    }

    @Override
    public void plusProgress(Member member, int count) {
        memberRepository.plusProgress(count, member.getMemberNum());

    }

  /*  @Override
    @Transactional
    public void updateWordCount(Member member, int wordCount) {
        memberRepository.updateWordCount(wordCount, member.getMemberNum());
    }

    @Override
    @Transactional
    public void updateSentenceCount(Member member, int sentenceCount) {
        memberRepository.updateSentenceCount(sentenceCount, member.getMemberNum());
    }

    @Override
    @Transactional
    public void updateConversationCount(Member member, int conversationCount) {
        memberRepository.updateConversationCount(conversationCount, member.getMemberNum());
    }*/


}
