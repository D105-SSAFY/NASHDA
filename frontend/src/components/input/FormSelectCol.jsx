import { useEffect, useState } from "react";
import * as S from "./style";

export default function FormSelectCol({ data: { list, target, callback, Idx = 0 } }) {
    const [clicked, setClicked] = useState(false);
    const [selectedItem, setSelectedItem] = useState(null);
    // 드랍 다운 버튼 클릭
    const onClickButton = (e) => {
        e.preventDefault();
        e.stopPropagation();

        setClicked(!clicked);
    };

    // 드랍 박스 이외의 영역을 눌렀을 때
    window.addEventListener("click", () => {
        setClicked(false);
    });

    // 드랍 박스 리스트 버튼 클릭
    const onClickListButton = (e, idx, text) => {
        e.preventDefault();
        setSelectedItem(text);
        setClicked(false); // 드롭다운 닫기
        callback(target, idx);
    };

    // 타겟 값 변경 시
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
