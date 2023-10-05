import IROnly from "style/IROnly";
import styled from "styled-components";

export const Section = styled.section`
    width: 100%;

    margin-top: 30px;
    padding: 20px 30px;

    border: 2px solid #f2f2f2;
    border-radius: 10px;
    box-shadow: 0 0 15px 1px #f2f2f2;

    & > header {
        ${IROnly}
    }
`;

export const Title = styled.p`
    margin-bottom: 12px;

    font-size: 1.9rem;
    font-weight: 500;
    color: rgb(120, 120, 120);
`;

export const Explain = styled.p`
    margin-bottom: 30px;

    font-size: 1.4rem;
    font-weight: 500;
    color: rgb(170, 170, 170);
`;

export const ImageWrapper = styled.div`
    width: 400px;

    border-radius: 12px;
    box-shadow: 0 0 15px 1px #f2f2f2;

    overflow: hidden;

    margin: 0 auto;

    & > img {
        width: 100%;
        height: 300px;

        object-fit: cover;
    }
`;
