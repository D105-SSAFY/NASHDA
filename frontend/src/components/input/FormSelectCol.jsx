import { useEffect, useState } from "react";
import * as S from "./style";

export default function FormSelectCol({ data: { list, target, callback, Idx = 0 } }) {
    const [clicked, setClicked] = useState(false);
    const [selectedItem, setSelectedItem] = useState(null);

    const onClickButton = (e) => {
        e.preventDefault();
        e.stopPropagation();

        setClicked(!clicked);
    };

    window.addEventListener("click", () => {
        setClicked(false);
    });

    const onClickListButton = (e, idx, text) => {
        e.preventDefault();
        setSelectedItem(text);
        setClicked(false);
        callback(target, idx);
    };

    useEffect(() => {
        setClicked(false);
    }, [target]);

    useEffect(() => {
        if (Idx === 0 || !list) setSelectedItem("없음");
        else {
            setSelectedItem(
                list.find((item) => {
                    return item[target === "직업" ? "jobIdx" : "hobbyIdx"] === Idx;
                })[target === "직업" ? "job" : "hobby"]
            );
        }
    }, [Idx]);

    return (
        <S.StyledDiv>
            <S.StyledLabel isFill={Boolean(selectedItem)}>{target}</S.StyledLabel>
            <S.Button clicked={clicked} onClick={onClickButton}>
                <span>{selectedItem}</span>
            </S.Button>
            <S.List
                clicked={clicked}
                onClick={(e) => {
                    e.stopPropagation();
                }}
            >
                <S.ListItem key="none">
                    <S.ListButton onClick={(e) => onClickListButton(e, 0, "없음")}>
                        <span>없음</span>
                    </S.ListButton>
                </S.ListItem>
                {list &&
                    list.map((item) => {
                        const key = target === "취미" ? item.hobbyIdx : item.jobIdx;
                        const displayText = target === "취미" ? item.hobby : item.job;

                        return (
                            <S.ListItem key={key}>
                                <S.ListButton onClick={(e) => onClickListButton(e, key, displayText)}>
                                    <span>{displayText}</span>
                                </S.ListButton>
                            </S.ListItem>
                        );
                    })}
            </S.List>
        </S.StyledDiv>
    );
}
