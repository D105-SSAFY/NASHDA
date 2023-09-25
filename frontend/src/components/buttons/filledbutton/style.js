import styled from "styled-components";
import { ConfirmButton } from "../defaultstyle";

export const Button = styled(ConfirmButton)`
    background-color: ${(props) => props.background};
    color: ${(props) => props.color};
`;
