import IROnly from "style/IROnly";
import styled from "styled-components";

export const Section = styled.section`
    width: 810px;

    flex-grow: 1;
    display: flex;
    flex-direction: column;

    & > header {
        ${IROnly}
    }
`;
