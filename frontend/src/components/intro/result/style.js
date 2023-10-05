import { Link } from "react-router-dom";
import styled from "styled-components";

export const Box = styled.section`
    position: relative;
    top: calc(50vh - 383px);

    width: 100%;
    height: 640px;
`;
// Ul 스타일드 컴포넌트를 중앙정렬하고 텍스트 데코레이션을 제거
export const AnswerList = styled.ul`
    position: relative;
    top: 2rem;

    list-style: none;
    text-align: center;
    text-decoration: none;
`;

export const AnswerListItem = styled.li`
    padding: 1.2rem;
`;

export const ThankTextBox = styled.section`
    position: absolute;
    top: 30rem;
    left: calc(50%);
    transform: translateX(-50%);

    text-align: center;
`;

export const HeaderSmall = styled.h3`
    margin: 1rem;

    font-size: 2.3rem;
    font-weight: 600;
`;

export const HeaderLarge = styled.span`
    margin-top: 1rem;
    margin-left: 0.5rem;
    margin-right: 0.5rem;

    font-size: 3.6rem;
    font-weight: 800;

    color: ${(props) => (props.correct ? "#000000D0" : "#ff7777")};
`;

export const ResultText = styled.p`
    font-size: 3.6rem;
    font-weight: 800;

    color: "#000000D0";
`;

export const RegistLink = styled(Link)`
    position: relative;
    top: 20px;
    font-size: 2.3rem;
    color: #8f47f5;

    & > svg {
        width: 2.3rem;
        height: 2.3rem;

        margin-top: -0.2rem;
        vertical-align: middle;
    }
`;

export const Greet = styled.img`
    position: absolute;
    top: 15rem;
    left: calc(50% - 50px);
    width: 100px;
`;
