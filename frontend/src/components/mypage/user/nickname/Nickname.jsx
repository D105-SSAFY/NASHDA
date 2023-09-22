import React from "react";
import * as n from "./style";
import { MoreButton } from "components/mypage/user/style";

import ArrowCircleRightOutlinedIcon from "@mui/icons-material/ArrowCircleRightOutlined";

export default function Nickname({ getMore }) {
    const userName = "Consolas";

    return (
        <>
            <n.ProfileName>@{userName}</n.ProfileName>
            <MoreButton onClick={() => getMore(1)}>
                별명 변경
                <ArrowCircleRightOutlinedIcon />
            </MoreButton>
        </>
    );
}
