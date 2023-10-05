import { useEffect, useState } from "react";

import * as s from "./style";

import FilledButton from "components/buttons/filledbutton/FilledButton";

import ExitToAppIcon from "@mui/icons-material/ExitToApp";
import KeyboardDoubleArrowLeftIcon from "@mui/icons-material/KeyboardDoubleArrowLeft";
import KeyboardDoubleArrowRightIcon from "@mui/icons-material/KeyboardDoubleArrowRight";

export default function FeedbackModal({ props: { onFeedback, setOnFeedback, feedbackList } }) {
    const [index, setIndex] = useState(0);

    const moveLeft = () => {
        setIndex((index) => {
            if (index !== 0) {
                return index - 1;
            }

            return index;
        });
    };

    const moveRight = () => {
        setIndex((index) => {
            if (index !== feedbackList.length - 1) {
                return index + 1;
            }

            return index;
        });
    };

    useEffect(() => {
        setIndex(0);
    }, [onFeedback]);

    return (
        <s.Wrapper visible={onFeedback} onClick={() => setOnFeedback(false)}>
            <s.Section onClick={(e) => e.stopPropagation()}>
                <header>
                    <h3>피드백 모달</h3>
                </header>
                <s.Content>틀린 부분들은 다음과 같이 발음해보세요.</s.Content>
                <s.CarouselWrapper>
                    <s.FeedbackList move={index * -520 + "px"}>
                        {feedbackList.map((feedback, idx) => {
                            return (
                                <s.FeedbackListItem key={feedback.phoneme + idx}>
                                    <s.Pron>
                                        &quot;{feedback.phoneme}&quot;
                                        <span>에 대한 발음입니다.</span>
                                    </s.Pron>
                                    <s.PronImage src={feedback.urlList} />
                                </s.FeedbackListItem>
                            );
                        })}
                    </s.FeedbackList>
                    <s.CarouselLeft onClick={moveLeft}>
                        <KeyboardDoubleArrowLeftIcon />
                    </s.CarouselLeft>
                    <s.CarouselRight onClick={moveRight}>
                        <KeyboardDoubleArrowRightIcon />
                    </s.CarouselRight>
                </s.CarouselWrapper>
                <s.ButtonWrapper>
                    <FilledButton
                        props={{
                            background: "rgba(68, 71, 90, 0.7)",
                            color: "#ffffff",
                            hovercolor: "#44475A",
                            callback() {
                                setOnFeedback(false);
                            }
                        }}
                    >
                        <ExitToAppIcon />
                        <span>피드백 닫기</span>
                    </FilledButton>
                </s.ButtonWrapper>
            </s.Section>
        </s.Wrapper>
    );
}
