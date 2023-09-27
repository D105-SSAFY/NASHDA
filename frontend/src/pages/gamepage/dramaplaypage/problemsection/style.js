import IROnly from "style/IROnly";
import styled from "styled-components";

export const Section = styled.section`
    width: 600px;
    margin: 40px auto 0;

    & > img {
        width: 100%;
    }
`;

export const Header = styled.header`
    ${IROnly}
`;
