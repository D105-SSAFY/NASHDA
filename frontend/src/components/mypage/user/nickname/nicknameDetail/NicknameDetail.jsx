import { useState } from "react";
import * as n from "./style";
import { MoreButton, CloseButton } from "components/mypage/user/style";

import ArrowCircleRightOutlinedIcon from "@mui/icons-material/ArrowCircleRightOutlined";

import eetch from "apis/eetch";

export default function NicknameDetail({ userInfo, setUserInfo, getMore }) {
    const [nickname, setNickname] = useState(userInfo.nickname);

    const handleInputChange = (e) => {
        setNickname(e.target.value);
    };

    const handleSubmit = () => {
        userInfo.nickname = nickname;
        console.log(userInfo);
        eetch.updateProfile(userInfo).then((res) => {
            setUserInfo(res.data);
        });
    };

    return (
        <>
            <n.ChangeBox>
                <n.ChangeText>
                    현재 닉네임 <span>@{userInfo.nickname}</span> 를(을)
                </n.ChangeText>
                <n.ChangeText>
                    <input value={nickname} onChange={handleInputChange} /> 로 변경합니다.
                </n.ChangeText>
            </n.ChangeBox>
            <CloseButton onClick={() => getMore(0)} />
            <MoreButton onClick={handleSubmit}>
                변경 완료
                <ArrowCircleRightOutlinedIcon />
            </MoreButton>
        </>
    );
}
