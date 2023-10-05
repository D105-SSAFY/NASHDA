import styled from "styled-components";
import { ConfirmButton } from "../defaultstyle";

export const Button = styled(ConfirmButton)`
    border: 2px solid ${(props) => props.color};
    color: ${(props) => props.color};
    transition: all 0.2s;

    &:hover {
        border: 2px solid #ffffff;
        background-color: ${(props) => props.color};
        color: #ffffff;
    }
`;
