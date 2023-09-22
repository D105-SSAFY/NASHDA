import styled from "styled-components";
import { Link } from "react-router-dom";

export const Header = styled.header`
    width: 100%;
    border-bottom: 1px solid #222222;

    user-select: none;
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
        line-height: 26px;
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
`;
