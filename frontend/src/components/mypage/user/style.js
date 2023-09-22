import styled from "styled-components";

export const DeFocusTouch = styled.div`
    position: absolute;

    top: 123px;
    left: 0;

    width: 100%;
    height: calc(100% - 123px);
`;

export const UserSection = styled.section`
    display: flex;
    flex-wrap: wrap;
    justify-content: flex-start;
    gap: 2rem;

    position: relative;
    width: 950px;

    margin: 0 auto;
    margin-top: 50px;
    padding: 0 20px;

    font-weight: 700;
    font-size: 3rem;

    & > :first-child {
        width: 260px;
    }

    & > :nth-child(2) {
        width: 630px;

        background-color: #64acef;
        & > button,
        h3 {
            color: #ffffff;

            font-weight: 400;
        }
    }

    & > :nth-child(3) {
        width: 260px;
    }

    & > :nth-child(4) {
        width: 350px;
    }

    & > :nth-child(5) {
        width: 260px;

        opacity: 0;
        border-radius: 0px;
    }
`;

export const UserCard = styled.article`
    position: relative;
    width: 300px;
    height: 320px;

    display: flex;
    flex-direction: column;
    justify-content: center;

    opacity: ${(props) => (props.defocus ? "1" : "0.5")};

    border: ${(props) => (props.focus ? "2px solid #cccccc;" : "2px solid #ffffff00;")};
    border-radius: 12px;
    background-color: #f2f2f2;

    transition: all ease 0.3s;
`;

export const MoreButton = styled.button`
    position: absolute;
    right: 1rem;
    bottom: 1rem;
    vertical-align: middle;

    font-size: 1.6rem;
    font-weight: 500;

    & > svg {
        width: 2rem;
        height: 2rem;

        margin-top: -0.2rem;

        vertical-align: middle;
    }
`;

export const ProfileName = styled.h2`
    display: flex;
    justify-content: center;

    text-decoration: underline;
    text-underline-position: under;
    text-underline-offset: -2px;
`;

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
    right: 0;
    margin: auto 0;
    margin-right: 3rem;

    text-align: right;
`;

export const GraphInfoTitle = styled.p`
    margin-bottom: 1rem;

    color: #ffffff;
    font-size: 1.8rem;
    font-weight: 600;
`;

export const GraphInfoContent = styled.p`
    margin-bottom: 0.3rem;

    color: #ffffff;
    font-size: 1.6rem;
    font-weight: 300;
`;
