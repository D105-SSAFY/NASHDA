import { useState } from "react";

import * as t from "./style";

import AirIcon from "@mui/icons-material/Air";

export default function Topbar() {
    const [isLogin, setIsLogin] = useState(false);

    const loginToggle = () => {
        setIsLogin(!isLogin);
    };

    return (
        <t.Header>
            <t.Wrapper>
                <t.Title to="/">
                    <AirIcon />
                    <h1>내쉬다</h1>
                </t.Title>
                <button onClick={loginToggle}>로그인 토글 테스트</button>
                <nav>
                    <t.NavList>
                        <li>
                            <t.NavLink to="/greeting">내쉬다는 무엇인가요?</t.NavLink>
                        </li>
                        <t.NavListItem visible={isLogin}>
                            <t.NavLink to="/mypage">내 정보</t.NavLink>
                        </t.NavListItem>
                        <t.NavListItem visible={isLogin}>
                            <t.NavLink to="/settings">설정</t.NavLink>
                        </t.NavListItem>
                        <t.NavListItem visible={isLogin}>
                            <t.NavLink to="/signout">로그아웃</t.NavLink>
                        </t.NavListItem>
                        <t.NavListItem visible={!isLogin}>
                            <t.NavLink to="/signin">로그인</t.NavLink>
                        </t.NavListItem>
                        <t.NavListItem visible={!isLogin}>
                            <t.NavLink to="/signup">회원가입</t.NavLink>
                        </t.NavListItem>
                    </t.NavList>
                </nav>
            </t.Wrapper>
        </t.Header>
    );
}
