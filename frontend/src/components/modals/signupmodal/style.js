import IROnly from "style/IROnly";
import styled, { keyframes } from "styled-components";

const fadeIn = keyframes`
    0% {
        opacity: 0;
    }
    100% {
        opacity: 1;
    }
`;

const fadeOut = keyframes`
    0% {
        opacity: 1;

    }
    100% {
        opacity: 0;

    }
`;

export const Wrapper = styled.div`
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    z-index: 300;
    background-color: rgba(120, 120, 120, 0.4);
    display: ${(props) => (props.visible ? "block" : "none")};
    animation: ${(props) => (props.visible ? fadeIn : fadeOut)} 0.3s ease-in-out;
`;

export const Section = styled.section`
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 600px;
    padding: 40px 20px;

    background-color: #ffffff;
    border-radius: 10px;
    text-align: center;
    & > header {
        ${IROnly}
    }
`;

export const Read = styled.p`
    font-size: 14px;
    font-weight: 500;
    color: rgb(170, 170, 170);

    margin-bottom: 14px;
`;

export const Pron = styled.p`
    font-size: 2.2rem;
    font-weight: 500;
    color: rgb(170, 170, 170);

    margin-bottom: 40px;
`;

export const ButtonWrapper = styled.div`
    width: 220px;
    margin: 0 auto 0;

    display: flex;
`;

export const SuccessImg = styled.img`
    height: 144px;
    margin-bottom: 20px;
`;
