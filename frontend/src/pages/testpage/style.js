import IROnly from "style/IROnly";
import styled from "styled-components";

export const Main = styled.main`
    max-width: 920px;
    height: calc(100vh - 72px);

    margin: 0 auto;
    padding: 0 55px 30px;

    display: flex;
    flex-direction: column;
    align-items: center;
`;

export const Section = styled.section`
    width: 600px;

    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);

    display: flex;
    flex-direction: column;
    align-items: center;
`;

export const Header = styled.header`
    width: 100%;

    text-align: center;

    & > h2 {
        ${IROnly}
    }
`;

export const Title = styled.p`
    font-size: 3rem;
    font-weight: 500;
    color: rgb(100, 100, 100);

    margin-bottom: 18px;
`;

export const Content = styled.p`
    font-size: 1.8rem;
    font-weight: 500;
    color: rgb(170, 170, 170);

    margin-bottom: 45px;
`;

export const Correct = styled.p`
    font-size: 3rem;
    font-weight: 500;
    color: rgb(100, 100, 100);

    margin-bottom: 32px;
`;

export const ButtonWrapper = styled.div`
    width: 400px;

    display: flex;
    gap: 10px;
`;
