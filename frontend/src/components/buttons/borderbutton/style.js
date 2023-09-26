import styled from "styled-components";
import { ConfirmButton } from "../defaultstyle";

export const Button = styled(ConfirmButton)`
    border: 2px solid ${(props) => props.color};
    color: ${(props) => props.color};
`;
