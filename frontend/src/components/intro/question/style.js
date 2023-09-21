import styled from "styled-components";

export const Box = styled.section`
    display: ${(props) => (props.visible ? "block" : "none")};

    position: relative;
    top: calc(50vh - 383px);

    width: 100%;
    height: 640px;

    background-color: #f2f2f2;
`;

export const Header = styled.header`
    position: relative;
    top: 70px;
`;

export const HeaderSmall = styled.h3`
    margin: 0.2rem;

    text-align: center;
    font-size: 2.3rem;
    font-weight: 600;
`;

export const HeaderLarge = styled.h1`
    margin-top: 1rem;

    text-align: center;
    font-size: 3.6rem;
    font-weight: 800;
`;

export const Question = styled.article`
    position: absolute;
    top: calc(50% - 75px);
    left: calc(50% - 460px);

    width: 920px;
    height: 150px;

    display: flex;
    align-items: center;
    justify-content: center;

    border-left: 8px solid #d9d9d9;
    border-right: 8px solid #d9d9d9;

    & > svg {
        position: absolute;
        bottom: 0.5rem;
        left: 0.5rem;

        width: 2.4rem;
        height: 2.4rem;

        opacity: 0.5;
    }
`;

export const QuestionText = styled.h1`
    text-align: center;
    font-size: 3.6rem;
    font-weight: 800;

    color: #8f47f580;
`;

export const Greet = styled.img`
    position: absolute;
    top: 430px;
    left: calc(50% - 300px);
    width: 600px;
`;

export const MicText = styled.p`
    position: absolute;
    bottom: 1rem;
    left: 2.9rem;

    font-size: 1.4rem;
    font-weight: 600;

    opacity: 0.5;

    display: ${(props) => (props.visible ? "block" : "none")};
`;

export const StepText = styled.p`
    position: absolute;
    right: 0.5rem;
    bottom: 1rem;

    font-size: 1.4rem;
    font-weight: 600;

    opacity: 0.5;
`;
