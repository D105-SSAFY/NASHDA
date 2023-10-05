import styled from "styled-components";

export const Wrapper = styled.div`
    display: ${(props) => (props.showModal ? "none" : "block")};

    position: absolute;
    top: 72px;
    bottom: 0;
    left: 0;
    right: 0;

    overflow: hidden;

    background-color: rgba(120, 120, 120, 0.4);

    z-index: 1000;
`;

export const Section = styled.section`
    width: 100%;

    position: absolute;
    top: 0;
    left: 0;

    padding: 45px 0;

    background-color: #f2f2f2;

    display: flex;
    flex-direction: column;
    align-items: center;
`;

export const Header = styled.header`
    text-align: center;

    & > h2 {
        margin-bottom: 24px;

        font-size: 3rem;
        font-weight: 500;

        color: rgb(100, 100, 100);
    }

    & > p {
        font-size: 2rem;
        font-weight: 500;

        color: rgb(170, 170, 170);
    }
`;

export const List = styled.ul`
    width: 100%;

    margin-top: 40px;

    display: flex;
    flex-direction: column;
    align-items: center;

    & > li {
        width: 100%;
    }
`;

export const Button = styled.button`
    width: 100%;

    padding: 4px 0;

    display: flex;

    font-size: 1.7rem;
    font-weight: 500;
    letter-spacing: 2px;
    color: rgb(120, 120, 120);

    transition: all 0.2s;

    &:hover {
        color: rgb(30, 30, 30);
        background-color: rgb(220, 220, 220);
    }
`;

export const TextWrapper = styled.span`
    width: 60px;

    margin: 0 auto;

    display: flex;
    justify-content: space-between;
`;
