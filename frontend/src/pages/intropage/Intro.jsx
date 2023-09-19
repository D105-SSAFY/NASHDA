import React from 'react';
import * as i from './style';

export default function Intro() {
    return (
        <i.Box>
            <i.Header>
                <i.HeaderSmall>처음만난 사람의 목소리를 듣는 것은</i.HeaderSmall>
                <i.HeaderSmall>언제나 즐거운 일 입니다.</i.HeaderSmall>
                <i.HeaderLarge>우리 반갑게 인사 해볼까요?</i.HeaderLarge>
            </i.Header>
            <i.Question>
                <i.QuestionText>뭐시기뭐시기 테스트</i.QuestionText>
            </i.Question>
        </i.Box>
    );
}
