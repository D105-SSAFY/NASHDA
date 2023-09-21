import styled from "styled-components";

export const QuestionBox = styled.li`
    margin-left: auto;
    position: relative;

    margin-top: 20px;
    width: fit-content;
    max-width: 500px;
    padding: 20px;

    background-color: #ffffff;

    border-radius: 12px 0px 12px 12px;
`;

export const QuestionTitle = styled.h3`
    margin-bottom: 1.2rem;

    font-size: 1.8rem;
    font-weight: 600;
`;

export const QuestionContent = styled.p`
    margin-bottom: 2.4rem;
    font-size: 1.6rem;
`;

export const QuestionDate = styled.p`
    display: flex;
    justify-content: flex-end;

    font-size: 1.6rem;
    font-weight: 500;
    opacity: 0.8;
`;
