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
