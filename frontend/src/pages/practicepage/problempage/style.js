import styled from "styled-components";

export const Main = styled.main`
    max-width: 920px;
    height: calc(100vh - 72px);

    margin: 0 auto;
    padding: 0 55px 60px;

    display: flex;
    flex-direction: column;
`;

export const Section = styled.section`
    flex-grow: 1;
    display: flex;
    flex-direction: column;
`;

export const Header = styled.header`
    padding: 60px 0;

    background-color: #f2f2f2;

    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 30px;

    & > h2 {
        font-size: 2.4rem;
        font-weight: 500;
        color: rgb(70, 70, 70);
    }

    & > p {
        max-width: 810px;

        font-size: 1.8rem;
        font-weight: 500;
        color: rgb(120, 120, 120);
    }
`;

export const ButtonWrapper = styled.div`
    width: 400px;
    margin: auto auto 60px;

    display: flex;
`;
