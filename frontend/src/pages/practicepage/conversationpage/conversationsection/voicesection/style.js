import IROnly from "style/IROnly";
import styled from "styled-components";

export const Section = styled.section`
    margin-top: auto;
    padding-top: 30px;
    box-shadow: 0 0 15px 10px #fff;

    width: 100%;

    border-top: 2px solid #e2e2e2;

    & > header {
        ${IROnly}
    }
`;

export const SoundWrapper = styled.div`
    width: fit-content;
    margin: 0 auto 30px;

    padding: 0 30px;

    border: 2px solid #f2f2f2;
    border-radius: 10px;

    display: ${(props) => (props.visible ? "block" : "none")};
`;

export const SoundText = styled.p`
    margin-top: 15px;

    font-size: 1.4rem;
    font-weight: 500;

    color: rgb(170, 170, 170);
`;

export const ButtonWrapper = styled.div`
    width: calc(max(50%, 200px));

    margin: 0 auto;

    display: flex;
`;
