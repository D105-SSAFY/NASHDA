import { useState } from "react";
import * as m from "./style";

import User from "components/mypage/user/User";
import Qna from "components/mypage/qna/Qna";
import Setting from "components/mypage/setting/Setting";
import { signin } from "apis/user";
// Import { signin, signup, signout } from "apis/user";

export default function MyPage() {
    const [tab, setTab] = useState("내 정보");

    const tabSwitch = (category) => {
        setTab(category);

        signin({ email: "t@t", password: "t" });
    };

    return (
        <>
            <m.MyPageNav>
                <m.Wrapper>
                    <m.NavList>
                        <li>
                            <m.NavLink
                                onClick={() => {
                                    tabSwitch("내 정보");
                                }}
                                $highlight={tab === "내 정보"}
                            >
                                내 정보
                            </m.NavLink>
                        </li>
                        <li>
                            <m.NavLink
                                onClick={() => {
                                    tabSwitch("통계");
                                }}
                                $highlight={tab === "통계"}
                            >
                                통계
                            </m.NavLink>
                        </li>
                        <li>
                            <m.NavLink
                                onClick={() => {
                                    tabSwitch("질문 현황");
                                }}
                                $highlight={tab === "질문 현황"}
                            >
                                질문 현황
                            </m.NavLink>
                        </li>
                        <li>
                            <m.NavLink
                                onClick={() => {
                                    tabSwitch("설정");
                                }}
                                $highlight={tab === "설정"}
                            >
                                설정
                            </m.NavLink>
                        </li>
                    </m.NavList>
                </m.Wrapper>
            </m.MyPageNav>
            <m.MyPageMain>
                {tab === "내 정보" ? <User tabSwitch={tabSwitch} /> : tab === "통계" ? <Qna /> : tab === "질문 현황" ? <Qna /> : <Setting />}
            </m.MyPageMain>
        </>
    );
}
