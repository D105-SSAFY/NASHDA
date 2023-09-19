import styled from 'styled-components';

export const Box = styled.section`
    position: relative;
    top: calc(50vh - 383px);

    width: 100%;
    height: 640px;

    background-color: #f2f2f2;
`;

export const Header = styled.header`
    position: relative;
    top: 40px;
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
`;

export const QuestionText = styled.h1`
    text-align: center;
    font-size: 3.6rem;
    font-weight: 800;

    color: #8f47f580;
`;
