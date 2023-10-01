import React, { useEffect, useState } from "react";
import * as u from "./style";
import { useSelector } from "react-redux";

import Nickname from "components/mypage/user/nickname/Nickname";
import Statistics from "components/mypage/user/statistics/Statistics";
import Archievement from "components/mypage/user/archievement/Archievement";
import Profile from "components/mypage/user/profile/Profile";

import NicknameDetail from "components/mypage/user/nickname/nicknameDetail/NicknameDetail";
import ArchievementDetail from "./archievement/archievementDetail/ArchievementDetail";

import eetch from "apis/eetch";
import StatisticsDetail from "../statistics/StatisticsDetail.jsx/StatisticsDetail";

export default function Setting() {
    // const dispatch = useDispatch();
    const user = useSelector((state) => state.user);

    const [more, setMore] = useState(0);
    const [userInfo, setUserInfo] = useState({});
    const [achievements, setAchievements] = useState([]);
    const [tabChanged, setTabChanged] = useState(false);

    const transitionHandler = () => {
        if (more === 5) {
            console.log("end!!");
            setTabChanged(true);
        }
    };

    useEffect(() => {
        eetch.mypage({ user }).then((res) => {
            setUserInfo(res.data);
        });

        eetch.achievement({ user }).then((res) => {
            setAchievements(res.data);
        });
    }, []);

    useEffect(() => {
        userInfo.user = user;
    }, [userInfo]);

    useEffect(() => {
        if (more !== 5) setTabChanged(false);
    }, [more]);

    return (
        <>
            <u.DeFocusTouch onClick={() => setMore(0)} />
            <u.UserSection more={more} tabChanged={tabChanged}>
                {/* 유저 별명 카드 */}
                <u.UserCard focus={more === 1} defocus={more === 0 || more === 1 || more === 5}>
                    <Nickname userInfo={userInfo} setMore={setMore} />
                </u.UserCard>
                {/* 유저 통계 카드 */}
                <u.UserCard
                    focus={more === 2}
                    defocus={more === 0 || more === 1 || more === 2 || more === 3 || more === 5}
                    onTransitionEnd={transitionHandler}
                >
                    <u.modeChange toggle={more === 0 || more === 2 || more === 4 || more === 5}>
                        <Statistics setMore={setMore} />
                    </u.modeChange>
                    <u.modeChange toggle={more === 1}>
                        <NicknameDetail userInfo={userInfo} setUserInfo={setUserInfo} setMore={setMore} />
                    </u.modeChange>
                    <u.modeChange toggle={more === 3}>
                        <ArchievementDetail setMore={setMore} achieved={achievements} />
                    </u.modeChange>
                </u.UserCard>
                {/* 유저 달성 카드 */}
                <u.UserCard focus={more === 3} defocus={more === 0 || more === 3 || more === 5}>
                    <Archievement setMore={setMore} mount={achievements.length} />
                </u.UserCard>
                {/* 유저 상세 카드 */}
                <u.UserCard focus={more === 4} defocus={more === 0 || more === 4 || more === 5}>
                    <Profile userInfo={userInfo} setUserInfo={setUserInfo} setMore={setMore} more={more} />
                </u.UserCard>
                <u.UserCard defocus={true}>
                    <StatisticsDetail></StatisticsDetail>
                </u.UserCard>
            </u.UserSection>
        </>
    );
}
