import * as game from "apis/game";
import * as notice from "apis/notice";
import * as question from "apis/question";
import * as statistics from "apis/statistics";
import * as test from "apis/test";
import * as user from "apis/user";

import { refresh } from "apis/user";
import { updateRefresh } from "redux/slice/userSlice";

const eetch = async (url, options) => {
    const response = await fetch(url, options);

    return response.json();
};

eetch.tokenValidation = async (call, values, dispatch) => {
    return call(values).catch(() => {
        if (values.user.refreshToken) {
            return refresh(values.user)
                .then((res) => {
                    dispatch(
                        updateRefresh({
                            accessToken: res.accessToken
                        })
                    );

                    return call(values);
                })
                .catch(() => {
                    throw new Error("토큰이 만료되었습니다. 다시 로그인해주세요.");
                });
        }

        throw new Error("토큰이 없습니다. 다시 로그인해주세요.");
    });
};

eetch.game = (values) => game.game(values);
eetch.speed = (values) => game.speed(values);
eetch.speedOne = (values) => game.speedOne(values);
eetch.speedTwo = (values) => game.speedTwo(values);
eetch.speedResult = (values) => game.speedResult(values);
eetch.blank = (values) => game.blank(values);
eetch.blankResult = (values) => game.blankResult(values);

eetch.noticeSend = (values) => notice.noticeSend(values);
eetch.noticeList = (values) => notice.noticeList(values);
eetch.noticeEdit = (values) => notice.noticeEdit(values);
eetch.noticeGet = (values) => notice.noticeGet(values);
eetch.noticeDelete = (values) => notice.noticeDelete(values);

eetch.questionWrite = (values) => question.questionWrite(values);
eetch.questionList = (values) => question.questionList(values);
eetch.questionEdit = (values) => question.questionEdit(values);
eetch.questionGet = (values) => question.questionGet(values);
eetch.questionDelete = (values) => question.questionDelete(values);
eetch.replyWrite = (values) => question.replyWrite(values);
eetch.replyEdit = (values) => question.replyEdit(values);
eetch.replyDelete = (values) => question.replyDelete(values);

eetch.strick = (values) => statistics.strick(values);
eetch.strickDetail = (values) => statistics.strickDetail(values);
eetch.achievement = (values) => statistics.achievement(values);
eetch.gameBlankWeek = (values) => statistics.gameBlankWeek(values);
eetch.gameSpeedWeek = (values) => statistics.gameSpeedWeek(values);
eetch.practiceWord = (values) => statistics.practiceWord(values);
eetch.practiceSimul = (values) => statistics.practiceSimul(values);
eetch.practiceSimulBackground = (values) => statistics.practiceSimulBackground(values);

eetch.wordList = (values) => test.wordList(values);
eetch.wordSubmit = (values) => test.wordSubmit(values);
eetch.wordResult = (values) => test.wordResult(values);
eetch.sentenceList = (values) => test.sentenceList(values);
eetch.sentenceSubmit = (values) => test.sentenceSubmit(values);
eetch.sentenceResult = (values) => test.sentenceResult(values);
eetch.weekList = (values) => test.weekList(values);
eetch.weekSubmit = (values) => test.weekSubmit(values);
eetch.weekResult = (values) => test.weekResult(values);

eetch.valid = (values) => user.valid(values);
eetch.refresh = (values) => user.refresh(values);
eetch.signin = (values) => user.signin(values);
eetch.signup = (values) => user.signup(values);
eetch.signout = (values) => user.signout(values);
eetch.sendCode = (values) => user.sendCode(values);
eetch.unregist = (values) => user.unregist(values);
eetch.updatePw = (values) => user.updatePw(values);
eetch.resetPw = (values) => user.resetPw(values);
eetch.updateProfile = (values) => user.updateProfile(values);
eetch.mypage = (values) => user.mypage(values);
eetch.checkEmail = (values) => user.checkEmail(values);
eetch.checkCode = (values) => user.checkCode(values);
eetch.checkNickname = (values) => user.checkNickname(values);
eetch.reset = (values) => user.reset(values);
eetch.domain = (values) => user.domain(values);

export default eetch;
