import React from "react";
import * as u from "./style";

import Nickname from "components/mypage/user/nickname/Nickname";
import Statistics from "components/mypage/user/statistics/Statistics";
import Archievement from "components/mypage/user/archievement/Archievement";
import Profile from "components/mypage/user/profile/Profile";

import NicknameDetail from "components/mypage/user/nickname/nicknameDetail/NicknameDetail";
import ArchievementDetail from "./archievement/archievementDetail/ArchievementDetail";

export default function Setting({ tabSwitch }) {
    const [more, setMore] = React.useState(0);

    return (
        <>
            <u.DeFocusTouch onClick={() => setMore(0)} />
            <u.UserSection more={more}>
                {/* 유저 별명 카드 */}
                <u.UserCard focus={more === 1} defocus={more === 0 || more === 1}>
                    <Nickname getMore={setMore} />
                </u.UserCard>
                {/* 유저 통계 카드 */}
                <u.UserCard focus={more === 2} defocus={more === 0 || more === 1 || more === 2 || more === 3}>
                    <u.modeChange toggle={more === 0 || more === 2 || more === 4}>
                        <Statistics tabSwitch={tabSwitch} />
                    </u.modeChange>
                    <u.modeChange toggle={more === 1}>
                        <NicknameDetail getMore={setMore} />
                    </u.modeChange>
                    <u.modeChange toggle={more === 3}>
                        <ArchievementDetail getMore={setMore} />
                    </u.modeChange>
                </u.UserCard>
                {/* 유저 달성 카드 */}
                <u.UserCard focus={more === 3} defocus={more === 0 || more === 3}>
                    <Archievement getMore={setMore} />
                </u.UserCard>
                {/* 유저 상세 카드 */}
                <u.UserCard focus={more === 4} defocus={more === 0 || more === 4}>
                    <Profile getMore={setMore} more={more} />
                </u.UserCard>
            </u.UserSection>
        </>
    );
}
