import IROnly from "style/IROnly";
import styled from "styled-components";

export const Section = styled.section`
    width: 810px;

    position: relative;

    margin-top: 30px;
    padding: 30px 60px;

    border: 2px solid #f2f2f2;
    border-radius: 10px;
    box-shadow: 0 0 15px 1px #f2f2f2;

    & > img {
        width: 50%;
        height: 260px;
        object-fit: cover;

        display: block;
        margin: 0 auto;

        border-radius: 10px;
    }
`;

export const Header = styled.header`
    ${IROnly}
`;

export const Text = styled.p`
    margin-top: 24px;

    font-size: 1.3rem;
    font-weight: 500;
    color: rgb(170, 170, 170);
`;

export const Sentence = styled.p`
    margin-top: 16px;

    font-size: 1.6rem;
    font-weight: 500;

    color: rgb(120, 120, 120);
`;
