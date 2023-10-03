import styled from "styled-components";
import { Link } from "react-router-dom";

export const CharactersWrapper = styled.div`
    padding-bottom: ${(props) => (props.isChar ? "80px" : "0")};
`;

export const GraphTarget = styled.span`
    display: inline;
    width: 250px;
    height: 250px;
`;

export const StatTable = styled.table`
    width: 830px;
    border-collapse: separate;
    border-spacing: 0 5px;

    margin: 0 auto;
`;
export const StatTableHead = styled.thead`
    font-size: 1.4rem;
    font-weight: 700;
`;
export const StatTableBody = styled.tbody`
    & > tr {
        background-color: #fff;
        font-size: 1.8rem;
        font-weight: 500;

        & > :first-child {
            border-radius: 10px 0 0 10px;
            font-weight: 700;
        }

        & > :nth-child(2) {
            font-weight: 700;
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

    & > :first-child {
        color: #f47560;
    }

    & > :nth-child(2) {
        color: #e8a838;
    }

    & > :nth-child(3) {
        color: #61cdbb;
    }
`;

export const CharactersExp = styled.span`
    width: 650px;

    padding-left: 20px;

    //내부 컨텐츠 좌우로 정렬
    display: flex;
    justify-content: left;
    align-items: center;
`;

export const CharactersUl = styled.ul`
    margin-left: 20px;

    transition: all ease 0.5s;

    overflow: hidden;

    & > :first-child {
        font-size: 1.6rem;
        font-weight: 600;
    }
`;

export const CharactersLi = styled.li`
    font-size: 2rem;
    margin: 10px;

    display: flex;
    justify-content: left;
    align-items: center;

    //줄넘김 제거
    white-space: nowrap;
`;

export const CharactersInfo = styled.span`
    margin-left: 10px;
    font-size: 1.6rem;
    font-weight: 500;
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
