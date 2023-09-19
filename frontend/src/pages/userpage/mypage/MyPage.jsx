import { useState } from "react";
import * as m from "./style";

import User from "components/mypage/user/User";
import Qna from "components/mypage/qna/Qna";
import Setting from "components/mypage/setting/Setting";

export default function MyPage() {
    const [tab, setTab] = useState("내 정보");

    const tabSwitch = (category) => {
        setTab(category);
        console.log(tab);
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
                                highlight={tab === "내 정보"}
                            >
                                내 정보
                            </m.NavLink>
                        </li>
                        <li>
                            <m.NavLink
                                onClick={() => {
                                    tabSwitch("질문 현황");
                                }}
                                highlight={tab === "질문 현황"}
                            >
                                질문 현황
                            </m.NavLink>
                        </li>
                        <li>
                            <m.NavLink
                                onClick={() => {
                                    tabSwitch("설정");
                                }}
                                highlight={tab === "설정"}
                            >
                                설정
                            </m.NavLink>
                        </li>
                    </m.NavList>
                </m.Wrapper>
            </m.MyPageNav>
            {tab === "내 정보" ? <User /> : tab === "질문 현황" ? <Qna /> : <Setting />}
        </>
    );
}
