import IROnly from "style/IROnly";
import styled, { keyframes } from "styled-components";

const roller = keyframes`
    0% {
        transform: rotate(0deg);
    }
    100% {
        transform: rotate(360deg);
    }
`;

export const Wrapper = styled.div`
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;

    background-color: rgba(120, 120, 120, 0.4);

    z-index: 2000;
`;

export const Section = styled.section`
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);

    width: 500px;
    padding: 60px 80px;

    background-color: #ffffff;
    border-radius: 14px;

    & > header {
        ${IROnly}
    }
`;

export const Loading = styled.div`
    margin: 0 auto 30px;

    position: relative;

    width: 80px;
    height: 80px;

    & > div {
        animation: ${roller} 1.5s cubic-bezier(0.5, 0, 0.5, 1) infinite;
        transform-origin: 40px 40px;
    }

    & > div:after {
        content: " ";
        display: block;
        position: absolute;
        width: 7px;
        height: 7px;
        border-radius: 50%;
        background: rgb(70, 70, 70);
        margin: -4px 0 0 -4px;
    }

    & > div:nth-child(1) {
        animation-delay: -0.036s;
    }

    & > div:nth-child(1):after {
        top: 63px;
        left: 63px;
    }

    & > div:nth-child(2) {
        animation-delay: -0.072s;
    }

    & > div:nth-child(2):after {
        top: 68px;
        left: 56px;
    }

    & > div:nth-child(3) {
        animation-delay: -0.108s;
    }

    & > div:nth-child(3):after {
        top: 71px;
        left: 48px;
    }

    & > div:nth-child(4) {
        animation-delay: -0.144s;
    }

    & > div:nth-child(4):after {
        top: 72px;
        left: 40px;
    }

    & > div:nth-child(5) {
        animation-delay: -0.18s;
    }

    & > div:nth-child(5):after {
        top: 71px;
        left: 32px;
    }

    & > div:nth-child(6) {
        animation-delay: -0.216s;
    }

    & > div:nth-child(6):after {
        top: 68px;
        left: 24px;
    }

    & > div:nth-child(7) {
        animation-delay: -0.252s;
    }

    & > div:nth-child(7):after {
        top: 63px;
        left: 17px;
    }

    & > div:nth-child(8) {
        animation-delay: -0.288s;
    }

    & > div:nth-child(8):after {
        top: 56px;
        left: 12px;
    }
`;

export const Text = styled.p`
    font-size: 2.2rem;
    font-weight: 500;
    color: rgb(170, 170, 170);

    text-align: center;
`;
