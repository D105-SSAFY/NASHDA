import { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { clearUser } from "redux/slice/userSlice";

import * as t from "./style";

import AirIcon from "@mui/icons-material/Air";

export default function Topbar() {
    // Dispath 초기화
    const dispatch = useDispatch();

    // 유저 정보 가져오기
    const user = useSelector((state) => state.user);

    // Topbar 로그인 상태
    const [isLogin, setIsLogin] = useState(false);

    // 유저 정보가 바뀔 때마다 Topbar:isLogin 상태 변경
    useEffect(() => {
        if (user.accessToken) {
            setIsLogin(true);
        } else {
            setIsLogin(false);
        }
    }, [user]);

    // 로그아웃
    const signout = () => {
        dispatch(clearUser());
    };

    // !!!!
    // 테스트용 로그인 토글 메소드 (추후 삭제)
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
                        {/* <t.NavListItem visible={isLogin}>
                            <t.NavLink to="/settings">설정</t.NavLink>
                        </t.NavListItem> */}
                        <t.NavListItem visible={isLogin}>
                            <t.NavLink to="/" onClick={signout}>
                                로그아웃
                            </t.NavLink>
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
