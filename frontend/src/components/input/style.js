import styled from "styled-components";

export const StyledLabel = styled.label`
    text-align: center;
    position: absolute;
    background-color: white;
    color: black;
    font-size: 10px;
    font-weight: bold;
    left: 1.2em;
    top: -0.7em;
    padding: 0 0.5em;
`;

export const StyledInput = styled.input`
    width: 100%;
    border-radius: 8px;
    border: solid 1.5px black;
    font-size: 15px;
    background-color: ${(props) => (props.readOnly ? "#eee" : "")};
    height: 35px;
    text-indent: 1.2em;
    &:focus {
        outline: 1px solid blue;
        outline: ${(props) => (props.readOnly ? "none" : "")};
    }
`;
