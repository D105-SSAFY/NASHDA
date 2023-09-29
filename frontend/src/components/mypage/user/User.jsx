import React, { useEffect } from "react";
import * as u from "./style";
import { useSelector } from "react-redux";

import Nickname from "components/mypage/user/nickname/Nickname";
import Statistics from "components/mypage/user/statistics/Statistics";
import Archievement from "components/mypage/user/archievement/Archievement";
import Profile from "components/mypage/user/profile/Profile";

import NicknameDetail from "components/mypage/user/nickname/nicknameDetail/NicknameDetail";
import ArchievementDetail from "./archievement/archievementDetail/ArchievementDetail";

import eetch from "apis/eetch";

export default function Setting({ tabSwitch }) {
    // const dispatch = useDispatch();
    const user = useSelector((state) => state.user);

    const [more, setMore] = React.useState(0);
    const [userInfo, setUserInfo] = React.useState({});

    useEffect(() => {
        eetch.mypage({ user }).then((res) => {
            setUserInfo(res.data);
        });
    }, []);

    useEffect(() => {
        userInfo.user = user;
    }, [userInfo]);

    return (
        <>
            <u.DeFocusTouch onClick={() => setMore(0)} />
            <u.UserSection more={more}>
                {/* 유저 별명 카드 */}
                <u.UserCard focus={more === 1} defocus={more === 0 || more === 1}>
                    <Nickname userInfo={userInfo} setMore={setMore} />
                </u.UserCard>
                {/* 유저 통계 카드 */}
                <u.UserCard focus={more === 2} defocus={more === 0 || more === 1 || more === 2 || more === 3}>
                    <u.modeChange toggle={more === 0 || more === 2 || more === 4}>
                        <Statistics tabSwitch={tabSwitch} />
                    </u.modeChange>
                    <u.modeChange toggle={more === 1}>
                        <NicknameDetail userInfo={userInfo} setUserInfo={setUserInfo} user setMore={setMore} />
                    </u.modeChange>
                    <u.modeChange toggle={more === 3}>
                        <ArchievementDetail setMore={setMore} />
                    </u.modeChange>
                </u.UserCard>
                {/* 유저 달성 카드 */}
                <u.UserCard focus={more === 3} defocus={more === 0 || more === 3}>
                    <Archievement setMore={setMore} />
                </u.UserCard>
                {/* 유저 상세 카드 */}
                <u.UserCard focus={more === 4} defocus={more === 0 || more === 4}>
                    <Profile userInfo={userInfo} setUserInfo={setUserInfo} setMore={setMore} more={more} />
                </u.UserCard>
            </u.UserSection>
        </>
    );
}
