import styled from "styled-components";

export const QuestionBackground = styled.div`
    position: absolute;

    top: 123px;
    left: 0;

    width: 100%;
    height: calc(100% - 123px);

    background-color: #f2f2f2;
`;

export const QnaSection = styled.ul`
    max-height: calc(100vh - 300px);

    //넘어가는 것 스크롤 처리
    overflow-x: hidden;
    overflow-y: scroll;

    position: relative;
    width: 950px;

    margin: 0 auto;
    margin-top: 20px;
    padding: 0 20px;
`;

export const QnaGradBox = styled.div`
    position: absolute;
    top: 142px;
    width: 100%;
    height: 30px;

    background: rgb(242, 242, 242);
    background: linear-gradient(
        0deg,
        rgba(242, 242, 242, 0) 0%,
        rgba(242, 242, 242, 0.06486344537815125) 18%,
        rgba(242, 242, 242, 0.9220063025210083) 75%,
        rgba(242, 242, 242, 1) 100%
    );
`;
