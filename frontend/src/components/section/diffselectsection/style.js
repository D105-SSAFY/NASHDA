import styled from "styled-components";

export const Section = styled.section`
    width: 500px;

    margin: 60px auto 0;

    flex-grow: 1;
`;

export const Header = styled.header`
    & > h2 {
        margin-bottom: 12px;

        font-size: 2.2rem;
        font-weight: 500;

        color: rgb(120, 120, 120);
    }

    & > p {
        font-size: 1.5rem;
        font-weight: 500;

        color: rgb(170, 170, 170);
    }
`;

export const List = styled.ul`
    width: 100%;

    margin-top: 40px;

    display: flex;
    flex-direction: column;
    gap: 8px;

    & > li {
        display: flex;
    }
`;

export const Button = styled.button`
    width: 100%;
`;
