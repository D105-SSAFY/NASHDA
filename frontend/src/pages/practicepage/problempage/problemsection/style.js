import styled from "styled-components";
import IROnly from "style/IROnly";

export const Section = styled.section`
    padding: 50px 35px;

    border: 2px solid #f2f2f2;
    border-radius: 10px;
    box-shadow: 0 0 15px 1px #f2f2f2;
`;

export const Header = styled.header`
    ${IROnly}
`;

export const Problem = styled.p`
    max-width: 90%;
    margin-bottom: 40px;

    font-size: 1.9rem;
    font-weight: 500;
    color: rgb(120, 120, 120);
`;

export const RightPron = styled.p`
    margin-bottom: 18px;

    font-size: 1.4rem;
    font-weight: 500;
    color: rgb(170, 170, 170);
`;

export const SpeakWrapper = styled.div`
    width: 100%;

    display: flex;
`;

export const Pron = styled.p`
    max-width: 90%;

    font-size: 2.4rem;
    font-weight: 500;
    color: #ae7ef2;
`;

export const SpeakButton = styled.button`
    height: 20px;

    margin: auto 0 1px 20px;

    text-align: right;
    color: #8f47f5;

    & > svg {
        width: 20px;
        height: 20px;
    }
`;
