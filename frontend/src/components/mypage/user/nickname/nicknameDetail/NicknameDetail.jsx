import React from "react";
import * as n from "./style";
import { MoreButton, CloseButton } from "components/mypage/user/style";

import ArrowCircleRightOutlinedIcon from "@mui/icons-material/ArrowCircleRightOutlined";

export default function NicknameDetail({ getMore }) {
    return (
        <>
            <n.ChangeBox>
                <n.ChangeText>
                    현재 닉네임 <span>@consolas</span> 을
                </n.ChangeText>
                <n.ChangeText>
                    <input /> 로 변경합니다.
                </n.ChangeText>
            </n.ChangeBox>
            <CloseButton onClick={() => getMore(0)} />
            <MoreButton>
                변경
                <ArrowCircleRightOutlinedIcon />
            </MoreButton>
        </>
    );
}
