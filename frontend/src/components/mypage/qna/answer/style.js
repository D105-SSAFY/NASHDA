import styled from "styled-components";

export const AnswerBox = styled.li`
    position: relative;

    margin-top: 30px;
    margin-bottom: 60px;
    width: fit-content;
    max-width: 500px;
    padding: 20px;

    background-color: #44475a;
    color: #ffffff;

    border-radius: 0px 12px 12px 12px;
`;

export const AnswerTitle = styled.h3`
    margin-bottom: 1.2rem;

    font-size: 1.8rem;
    font-weight: 600;
`;

export const AnswerContent = styled.p`
    margin-bottom: 2.4rem;
    font-size: 1.6rem;

    line-height: 1.2;
`;

export const AnswerDate = styled.p`
    font-size: 1.6rem;
    font-weight: 500;
    opacity: 0.8;
`;

export const SendFrom = styled.h2`
    top: -24px;
    left: 0px;
    position: absolute;

    font-size: 1.8rem;
    font-weight: 700;
    color: #44475a;

    & > svg {
        width: 1.8rem;
        height: 1.8rem;

        margin-top: -0.2rem;
        vertical-align: middle;
    }
`;
