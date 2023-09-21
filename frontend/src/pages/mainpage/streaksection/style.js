import styled from "styled-components";

export const Section = styled.section`
    padding: 22px 0 26px;
`;

export const Wrapper = styled.div`
    max-width: 920px;
    margin: 0 auto;
    padding: 0 55px;
`;

export const Header = styled.header`
    padding-bottom: 10px;
    margin-bottom: 22px;

    border-bottom: 1px solid rgba(0, 0, 0, 0.6);

    & > h2 {
        font-size: 2.4rem;
        font-weight: 500;
        line-height: 2.9rem;
        color: rgba(0, 0, 0, 0.6);
    }
`;
