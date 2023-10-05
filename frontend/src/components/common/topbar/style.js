import styled from "styled-components";
import { Link } from "react-router-dom";

export const Header = styled.header`
    position: relative;
    width: 100%;
    height: 72px;

    background-color: #fff;

    top: 0;
    left: 0;

    border-bottom: 1px solid rgba(0, 0, 0, 0.2);
    z-index: 1000;
`;

export const Wrapper = styled.div`
    display: flex;
    align-items: center;
    justify-content: space-between;

    max-width: 950px;
    height: 72px;
    margin: 0 auto;
    padding: 0 20px;
`;

export const Title = styled(Link)`
    display: flex;
    align-items: center;
    justify-content: center;

    & > svg {
        width: 34px;
        height: 30px;

        margin-right: 0.1rem;
    }

    & > h1 {
        font-size: 2.2rem;
        font-weight: 700;
        line-height: 2.6rem;
    }
`;

export const NavList = styled.ul`
    display: flex;
    justify-content: space-between;
    gap: 2rem;

    width: fit-content;
    margin: 0.8rem;

    & > li:first-child {
        margin-right: 1rem;
    }
`;

export const NavListItem = styled.li`
    display: ${(props) => (props.visible ? "block" : "none")};
`;

export const NavLink = styled(Link)`
    font-size: 1.4rem;
    font-weight: 500;
`;
