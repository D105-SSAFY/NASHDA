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
    padding: 40px;
`;

export const weekBlankWrapper = styled.ul`
    width: 100%;
    display: grid;
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
