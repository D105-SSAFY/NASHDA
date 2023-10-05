import React from "react";
import * as a from "./style";

import { CloseButton } from "components/mypage/user/style";

export default function ArchievementDetail({ setMore, achieved }) {
    const archieveList = (achieved) => {
        if (achieved.length === 0) return null;
        const result = [];
        for (let i = 0; i < achieved.length; i++) {
            result.push(
                <a.ArchieveItem key={i}>
                    <img src={"/emojis/emoji" + i + ".png"}></img>
                    <a.ArchieveTitle>{achieved[i].name}</a.ArchieveTitle>
                    <a.ArchieveDate>{achieved[i].create_on.substring(0, 10)}</a.ArchieveDate>
                    <a.ArchieveDivLine />
                    {i % 2 === 0 ? <a.ArchieveVertDivLine /> : null}
                </a.ArchieveItem>
            );
        }

        return result;
    };

    return (
        <>
            {achieved.length === 0 ? <a.NoDataSign>아직 달성한 도전과제가 없어요.</a.NoDataSign> : null}
            <a.NoDataSign></a.NoDataSign>
            <a.ArchievesBox>{archieveList(achieved)}</a.ArchievesBox>
            <CloseButton onClick={() => setMore(0)} />
        </>
    );
}
