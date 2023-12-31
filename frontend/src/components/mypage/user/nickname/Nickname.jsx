// Import { useState, useEffect } from "react";
// import { useDispatch, useSelector } from "react-redux";
// import { useSelector } from "react-redux";

import * as n from "./style";
import { MoreButton } from "components/mypage/user/style";

import ArrowCircleRightOutlinedIcon from "@mui/icons-material/ArrowCircleRightOutlined";

export default function Nickname({ userInfo, setMore }) {
    return (
        <>
            <n.ProfileName>@{userInfo.nickname}</n.ProfileName>
            <MoreButton onClick={() => setMore(1)}>
                별명 변경
                <ArrowCircleRightOutlinedIcon />
            </MoreButton>
        </>
    );
}
