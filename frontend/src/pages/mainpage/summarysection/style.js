import styled from "styled-components";
import { Link } from "react-router-dom";

export const Section = styled.section`
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: space-between;

    height: 250px;
    background-color: #f2f2f2;
    padding: 42px 0 46px;

    text-align: center;
`;

export const Header = styled.header`
    & > h2 {
        font-size: 3.2rem;
        font-weight: 700;
        line-height: 3.8rem;
    }

    & > p {
        font-size: 2.4rem;
        font-weight: 500;
        color: rgba(0, 0, 0, 0.6);
        line-height: 2.9rem;

        & > span {
            position: relative;

            color: #000000;
        }

        & > span:first-child::after {
            content: "";

            display: block;

            position: absolute;
            bottom: 0px;

            width: 100%;
            height: 2px;
            background-color: #000000;
        }
    }
`;

export const TextWrapper = styled.div`
    & > p {
        font-size: 1.8rem;
        font-weight: 500;
        line-height: 2.2rem;
        color: rgba(0, 0, 0, 0.6);
    }

    & > p:first-child {
        margin-bottom: 5px;
    }
`;

export const Words = styled.span`
    color: #d291bc;
`;

export const Sentences = styled.span`
    color: #957dad;
`;

export const Chats = styled.span`
    color: #b1b8e8;
`;

export const NameLink = styled(Link)`
    color: #000;
    text-decoration: underline;
    text-underline-offset: 4px;
`;
