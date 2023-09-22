import React from "react";
import * as u from "./style";

import ArrowCircleRightOutlinedIcon from "@mui/icons-material/ArrowCircleRightOutlined";

export default function Setting() {
    const userName = "Consolas";
    const [more, setMore] = React.useState(0);

    const getMore = (n) => {
        setMore(n);
    };

    return (
        <>
            <u.DeFocusTouch onClick={() => getMore(0)} />
            <u.UserSection>
                <u.UserCard focus={more === 1} defocus={more === 0 || more === 1}>
                    <u.ProfileName>@{userName}</u.ProfileName>
                    <u.MoreButton onClick={() => getMore(1)}>
                        별명 변경
                        <ArrowCircleRightOutlinedIcon />
                    </u.MoreButton>
                </u.UserCard>
                <u.UserCard focus={more === 2} defocus={more === 0 || more === 2}>
                    <u.MoreButton onClick={() => getMore(2)}>
                        통계
                        <ArrowCircleRightOutlinedIcon />
                    </u.MoreButton>
                </u.UserCard>
                <u.UserCard focus={more === 3} defocus={more === 0 || more === 3}>
                    <u.MoreButton onClick={() => getMore(3)}>
                        달성
                        <ArrowCircleRightOutlinedIcon />
                    </u.MoreButton>
                </u.UserCard>
                <u.UserCard focus={more === 4} defocus={more === 0 || more === 4}>
                    <u.MoreButton onClick={() => getMore(4)}>
                        상세 프로필
                        <ArrowCircleRightOutlinedIcon />
                    </u.MoreButton>
                </u.UserCard>
                <u.UserCard onClick={() => getMore(0)} />
                focused on : {more}
            </u.UserSection>
        </>
    );
}
