import IROnly from "style/IROnly";
import styled from "styled-components";

export const Section = styled.section`
    width: 100%;
    height: 8px;

    background-color: rgb(170, 170, 170);

    border-radius: 4px;
    overflow: hidden;

    & > header {
        ${IROnly}
    }
`;

export const Progress = styled.div`
    background-color: rgb(100, 100, 100);

    width: ${(props) => props.progress + "%"};
    height: 100%;

    transition: all 1s linear;
`;
