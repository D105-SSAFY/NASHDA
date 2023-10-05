import { useState } from "react";
import * as m from "./style";

import User from "components/mypage/user/User";
import Qna from "components/mypage/qna/Qna";
import Setting from "components/mypage/setting/Setting";

export default function MyPage() {
    const [tab, setTab] = useState("내 정보");

    return (
        <>
            <m.MyPageNav>
                <m.Wrapper>
                    <m.NavList>
                        <li>
                            <m.NavLink
                                onClick={() => {
                                    setTab("내 정보");
                                }}
                                $highlight={tab === "내 정보"}
                            >
                                내 정보
                            </m.NavLink>
                        </li>
                        <li>
                            <m.NavLink
                                onClick={() => {
                                    setTab("질문 현황");
                                }}
                                $highlight={tab === "질문 현황"}
                            >
                                질문 현황
                            </m.NavLink>
                        </li>
                    </m.NavList>
                </m.Wrapper>
            </m.MyPageNav>
            <m.MyPageMain>{tab === "내 정보" || tab === "통계" ? <User /> : tab === "질문 현황" ? <Qna /> : <Setting />}</m.MyPageMain>
        </>
    );
}
