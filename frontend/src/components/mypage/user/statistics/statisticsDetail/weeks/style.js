import styled from "styled-components";
import { Link } from "react-router-dom";

export const WeekWrapper = styled.div`
    padding-bottom: ${(props) => (props.isWeek ? "80px" : "0")};
`;

export const StatTable = styled.table`
    width: 830px;
    border-collapse: separate;
    border-spacing: 0 5px;

    margin: 0 auto;
    margin-top: 20px;
    margin-bottom: 60px;

    table-layout: fixed;
`;
export const StatTableHead = styled.thead`
    font-size: 1.4rem;
    font-weight: 500;
`;
export const StatTableBody = styled.tbody`
    & > tr {
        background-color: #fff;
        font-size: 1.8rem;
        font-weight: 500;

        & > td {
            // 모든 td 넓이 동일하게
        }

        & > :first-child {
            border-radius: 10px 0 0 10px;
            font-weight: 700;

            width: 80px;
        }

        & > :nth-child(2) {
            width: 300px;
        }

        & > :last-child {
            border-radius: 0 10px 10px 0;
        }

        & > th,
        td {
            padding: 16px;

            text-align: center;
        }
    }
`;

export const WeeksResultWrapper = styled.div`
    width: 100%;

    & > :first-child {
        & > :first-child {
            margin-left: 30px;
            background-color: #f2f2f2;
        }

        & > :nth-child(2) {
            width: 830px;
            margin-left: 30px;
            background-color: transparent;
        }

        & > ul {
            width: 830px;
            margin-left: 30px;
        }
    }
`;

export const weekDetailWrapper = styled.div`
    width: 100%;
    padding: 10px 40px 0px 40px;
`;

export const weekBlankWrapper = styled.ul`
    width: 100%;
    display: grid;

    margin-bottom: 40px;
    grid-template-columns: repeat(2, 1fr);
    grid-template-rows: repeat(2, 1fr);

    //중앙만 20px 틈
    grid-gap: 20px;

    justify-items: center;
    align-items: center;
`;

export const weekBlankItem = styled.li`
    position: relative;
    width: 400px;
    height: 600px;
    background-color: #fff;

    overflow: hidden;
    border-radius: 12px;

    box-shadow:
        0 3px 6px rgba(0, 0, 0, 0.08),
        0 3px 10px rgba(0, 0, 0, 0.16);
`;

export const BlankImg = styled.img`
    position: relative;
    width: 400px;
    height: 267px;
    object-fit: cover;

    margin: auto;

    overflow: hidden;

    left: 50%;
    transform: translateX(-50%) scale(1.1);
`;

export const BlankAnswer = styled.h2`
    position: relative;
    width: 100%;
    margin-top: 20px;

    font-size: 2.2rem;
    font-weight: 600;
    text-align: center;
    line-height: 1.2;
`;

export const BlankHint = styled.h3`
    position: absolute;
    width: 100%;
    top: 356px;
    left: 20px;

    font-size: 1.7rem;
    font-weight: 400;
    line-height: 1.2;

    & > svg {
        position: absolute;
        width: 1.8rem;
        height: 1.8rem;

        top: 0px;
        left: 31px;

        color: #ff9a1c;
    }
`;

export const HintWrapper = styled.div`
    position: absolute;
    width: 100%;
    height: fit-content;

    padding-left: 17px;
    padding-right: 17px;
    top: 382px;
    left: 0;
`;

export const Hint = styled.h3`
    position: relative;
    width: 100%;

    font-size: 1.6rem;
    font-weight: 500;
    line-height: 1.2;

    margin-bottom: 5px;
`;

export const AudioWrapper = styled.div`
    position: absolute;

    bottom: 20px;
    right: 20px;

    & > button {
        color: ${(props) => (props.isPlaying ? "#6446ff" : "#000")};
        font-size: 1.7rem;
        font-weight: ${(props) => (props.isPlaying ? "600" : "400")};

        //버튼 내 수직 중앙 정렬
        display: flex;
        justify-content: center;
        align-items: center;

        transition: all 0.25s ease;

        & > svg {
            width: 1.8rem;
            height: 1.8rem;
        }
    }
`;

export const AudioAnswer = styled.audio``;

export const AnswerWrapper = styled.div``;

export const AnswerToggle = styled.button`
    position: absolute;
    bottom: 20px;
    left: 20px;

    font-size: 1.7rem;
    font-weight: 400;

    text-decoration: underline;
`;

export const Answers = styled.ul`
    position: absolute;
    bottom: 60px;
    left: 17px;

    list-style: none;
    //리스트 아래가 아닌 오른쪽으로 나열
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;

    //좌우로 틈 10px
    gap: 7px;

    transition: all 0.3s ease;

    opacity: ${(props) => (props.showAnswer ? "1" : "0")};
    visibility: ${(props) => (props.showAnswer ? "visible" : "hidden")};
`;

export const Answer = styled.li`
    // 리스트 숫자 제거 밑 우측으로 붙이기
    width: fit-content;

    padding: 6px 8px 6px 8px;

    border-radius: 10px;

    font-size: 1.6rem;
    font-weight: 500;

    background-color: #595959;

    color: #ff9a1c;
`;

export const WeekSpeedOneWrapper = styled.ul`
    position: relative;
    width: 100%;

    margin-bottom: 40px;
`;

export const WeekSpeedOneItem = styled.li`
    position: relative;
    width: 100%;
    height: 180px;
    margin-bottom: 20px;

    background-color: #fff;

    overflow: hidden;
    border-radius: 12px;

    box-shadow:
        0 3px 6px rgba(0, 0, 0, 0.08),
        0 3px 10px rgba(0, 0, 0, 0.16);

    & > button {
        position: absolute;
        top: 20px;
        right: 20px;

        color: ${(props) => (props.isPlaying ? "#6446ff" : "#000")};
        font-size: 1.7rem;
        font-weight: ${(props) => (props.isPlaying ? "600" : "400")};

        //버튼 내 수직 중앙 정렬
        display: flex;
        justify-content: center;
        align-items: center;

        transition: all 0.25s ease;

        & > svg {
            width: 1.8rem;
            height: 1.8rem;
        }
    }
`;

export const WeekSpeedOneImg = styled.img`
    position: absolute;
    width: 270px;
    height: 180px;
    object-fit: cover;

    margin: auto;

    overflow: hidden;

    left: 0;
`;

export const WeekSpeedOneAnswerWrapper = styled.div`
    position: relative;
    width: 520px;

    left: 290px;

    top: 50%;
    transform: translateY(-50%);
`;

export const WeekSpeedOneType = styled.div`
    width: fit-content;
    font-size: 1.8rem;
    font-weight: 500;

    margin-bottom: 3px;
    margin-left: 3px;

    color: #000;
`;
export const WeekSpeedOneAnswer = styled.div`
    width: fit-content;
    font-size: 3rem;
    font-weight: 700;

    margin-bottom: 20px;
`;
export const WeekSpeedOneSTT = styled.div`
    width: fit-content;
    font-size: 3rem;

    color: #ff9a1c;
`;

export const WeekSpeedTwoWrapper = styled.ul``;

export const WeekSpeedTwoItem = styled.li``;

export const WeekSpeedTwoQuizTitle = styled.h2`
    font-size: 2.2rem;
    font-weight: 600;
    margin-bottom: 5px;
`;

export const WeekSpeedTwoPhotoWrapper = styled.div`
    // 4x1 그리드
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    grid-template-rows: 1fr;

    margin-bottom: 20px;

    & > :first-child > img {
        border-radius: 12px 0 0 12px;
    }

    & > :last-child > img {
        border-radius: 0 12px 12px 0;
    }
`;

export const WeekSpeedTwoImgWrapper = styled.div`
    position: relative;

    & > svg {
        position: absolute;
        top: 4px;
        right: 4px;

        width: 4rem;
        height: 4rem;

        color: #ff9a1c;
    }
`;

export const WeekSpeedTwoImg = styled.img`
    width: 207.5px;
    height: 138.3px;
    left: 0;

    margin: auto;

    overflow: hidden;

    object-fit: cover;
    opacity: ${(props) => (props.answer ? "1" : "0.45")};
    filter: ${(props) => (props.answer ? "brightness(100%)" : "brightness(50%)")};

    transition: all 0.5s ease;
    &:hover {
        opacity: 1;
        filter: brightness(100%);
    }
`;

export const WrapperTitle = styled.h2`
    padding: 5px 12px 5px 12px;

    margin-top: 10px;
    margin-bottom: 15px;

    font-size: 2rem;
    text-align: center;
    width: fit-content;

    border-radius: 30px;

    color: #fff;

    background-color: #44475a;
`;

export const NoDataWrapper = styled.div`
    width: 100%;
    padding: 45px;
`;

export const NoData = styled.h2`
    color: #000;
    opacity: 0.6;

    margin: 0 auto;
    text-align: center;

    font-size: 2.6rem;
`;

export const NoDataLink = styled(Link)`
    display: flex;
    justify-content: center;
    align-items: center;

    font-size: 1.8rem;
    font-weight: 500;

    margin-top: 8px;

    & > svg {
        width: 1.8rem;
        height: 1.8rem;

        vertical-align: middle;
    }
`;
