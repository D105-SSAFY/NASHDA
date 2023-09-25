import React from "react";
import * as p from "./style";
import { MoreButton } from "components/mypage/user/style";

import ArrowCircleRightOutlinedIcon from "@mui/icons-material/ArrowCircleRightOutlined";

export default function Profile({ getMore }) {
    const detailText = (str) => {
        const result = [];
        for (let i = 0; i < str.length; i++) {
            result.push(<span key={i}>{str.charAt(i)}</span>);
        }

        return result;
    };

    return (
        <>
            <p.DetailTypeBox>
                <p.DetailTypeText>{detailText("이메일")}</p.DetailTypeText>
                <p.DetailTypeText>{detailText("본명")}</p.DetailTypeText>
                <br />
                <p.DetailTypeText>{detailText("생년월일")}</p.DetailTypeText>
                <p.DetailTypeText>{detailText("직업")}</p.DetailTypeText>
                <p.DetailTypeText>{detailText("취미")}</p.DetailTypeText>
            </p.DetailTypeBox>
            <p.DetailContentBox>
                <p.DetailContentText>he2kape@gmail.com</p.DetailContentText>
                <p.DetailContentText>안세혁</p.DetailContentText>
                <br />
                <p.DetailContentText>1997년 09월 21일</p.DetailContentText>
                <p.DetailContentText>학생</p.DetailContentText>
                <p.DetailContentText>사진</p.DetailContentText>
            </p.DetailContentBox>
            <MoreButton onClick={() => getMore(4)}>
                상세 프로필
                <ArrowCircleRightOutlinedIcon />
            </MoreButton>
        </>
    );
}