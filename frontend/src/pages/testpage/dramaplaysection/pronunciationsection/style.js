import styled from "styled-components";

export const Section = styled.section`
    margin-top: 30px;
    width: 100%;

    flex-grow: 1;

    display: flex;
    flex-direction: column;
`;

export const Header = styled.header`
    margin-bottom: 14px;
    margin-left: 3px;

    & > h2 {
        font-size: 1.4rem;
        font-weight: 500;
        color: rgb(120, 120, 120);
    }
`;

export const Box = styled.div`
    padding: 25px 30px;

    background-color: #f8f8f8;

    border-radius: 10px;

    display: flex;
`;

export const Pron = styled.p`
    font-size: 2rem;
    font-weight: 500;
    color: rgb(100, 100, 100);
`;

export const ButtonWrapper = styled.div`
    margin-top: auto;

    display: flex;
    gap: 10px;
`;
