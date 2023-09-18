import styled from "styled-components";
import { Link } from "react-router-dom";

export const Box = styled.header`
    display: flex;
    justify-content: space-between;

    border-bottom: 1px solid #222222;
`;

export const Title = styled(Link)`
    font-size: 1.8rem;
    font-weight: 700;

    margin: 1rem;
`;

// NavLink를 가로로 정렬해서 담아두는 div인데 width를 내용 물 사이즈에 맞춤

export const NavList = styled.ul`
    display: flex;
    justify-content: space-between;

    width: fit-content;
    margin: 1rem;

    & > li:first-child {
        margin-right: 2rem;
    }
`;

export const NavListItem = styled.li`
    display: ${(props) => (props.visible ? "block" : "none")};
`;

export const NavLink = styled(Link)`
    font-size: 1rem;
    margin: 1rem;
`;
