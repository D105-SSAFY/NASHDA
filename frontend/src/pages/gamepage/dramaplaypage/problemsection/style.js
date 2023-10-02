import IROnly from "style/IROnly";
import styled from "styled-components";

export const Section = styled.section`
    width: 810px;

    margin-top: 30px;
    padding: 40px 60px;

    border: 2px solid #f2f2f2;
    border-radius: 10px;
    box-shadow: 0 0 15px 1px #f2f2f2;

    & > img {
        width: 100%;
        height: 320px;
        object-fit: cover;

        border-radius: 10px;
    }
`;

export const Header = styled.header`
    ${IROnly}
`;

export const Text = styled.p`
    margin-top: 24px;

    font-size: 1.4rem;
    font-weight: 500;
    color: rgb(170, 170, 170);
`;

export const Sentence = styled.p`
    margin-top: 16px;

    font-size: 1.8rem;
    font-weight: 500;

    color: rgb(120, 120, 120);
`;
