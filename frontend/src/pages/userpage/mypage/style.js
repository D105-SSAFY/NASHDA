import styled from "styled-components";
import { Link } from "react-router-dom";

export const Wrapper = styled.div`
    display: flex;
    align-items: center;
    justify-content: space-between;

    width: 950px;
    height: 50px;
    margin: 0 auto;
    padding: 0 20px;
`;

export const MyPageNav = styled.nav`
    display: flex;
    align-items: center;

    width: 100%;
    height: 50px;

    background-color: #f2f2f2;
`;

export const NavList = styled.ul`
    // ul을 nav 중앙에 정렬되게

    display: flex;
    justify-content: space-between;
    gap: 2rem;

    width: fit-content;
    margin: 0.8rem;
`;

export const NavLink = styled(Link)`
    font-size: 1.4rem;
    font-weight: ${(props) => (props.highlight ? "700" : "400")};
`;
