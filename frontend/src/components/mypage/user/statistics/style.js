import styled from "styled-components";

export const StatisticsTitle = styled.h3`
    position: absolute;
    top: 1rem;
    left: 1rem;
    font-size: 1.6rem;
    font-weight: 500;
`;

export const BottomLine = styled.div`
    position: absolute;
    bottom: 2rem;
    left: 0rem;

    height: 1px;
    width: 380px;

    background: rgb(255, 255, 255);
    background: linear-gradient(90deg, rgba(255, 255, 255, 1) 0%, rgba(255, 255, 255, 1) 90%, rgba(255, 255, 255, 0) 100%);
`;

export const Graph = styled.div`
    position: absolute;
    bottom: 2rem;
    left: 3rem;
    width: 380px;
    height: 260px;
`;

export const GraphBar = styled.div`
    position: absolute;
    display: flex;

    right: ${(props) => 5 + 7 * props.index}rem;
    bottom: 2rem;

    height: ${(props) => 0.2 * props.score}rem;
    width: 30px;

    justify-content: center;

    border-radius: 15px 15px 0 0;

    background-color: #ffffff;

    transition: height ease 1s;
`;

export const GraphBall = styled.div`
    position: absolute;
    display: flex;

    right: ${(props) => 5.75 + 7 * props.index}rem;
    bottom: 27.5px;

    height: 15px;
    width: 15px;

    justify-content: center;

    border-radius: 15px;

    background-color: #ffffffcc;
`;

export const GraphScore = styled.p`
    position: absolute;

    margin-top: -1.4rem;

    font-size: 1.2rem;
    font-weight: 400;

    color: #ffffff;
`;

export const GraphWeek = styled.div`
    position: absolute;
    bottom: -18px;
    left: -5px;

    width: 40px;

    // div내 글자를 중앙정렬 시켜줘
    display: flex;
    justify-content: center;

    font-size: 1.3rem;
    font-weight: 400;

    color: #ffffff;
`;

export const GraphInfo = styled.div`
    position: absolute;
    top: 50%;
    right: 0;
    transform: translateY(-50%);
    margin-right: 3rem;

    text-align: right;
`;

export const GraphInfoTitle = styled.p`
    margin-bottom: 1rem;

    color: #ffffff;
    font-size: ${(props) => (props.more === 5 ? "2.5rem" : "1.8rem")};
    font-weight: 600;

    transition: font-size ease 0.5s;
`;

export const GraphInfoContent = styled.p`
    margin-bottom: 0.3rem;

    color: #ffffff;
    font-size: ${(props) => (props.more === 5 ? "2.22rem" : "1.6rem")};
    font-weight: 300;

    transition: font-size ease 0.5s;
`;
