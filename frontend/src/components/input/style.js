import styled from "styled-components";
export const StyledDiv = styled.div`
    position: relative;
    width: 100%;
    margin: 10px;
`;
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
    border: solid 1.8px black;
    font-size: 15px;
    background-color: ${(props) => (props.readOnly ? "#eee" : "")};
    height: 40px;
    text-indent: 1.2em;
    padding-right: ${(props) => (props.check ? "40px" : "")};
    size: 30;
    &:focus {
        outline: 1px solid blue;
        outline: ${(props) => (props.readOnly ? "none" : "")};
    }
`;

export const StyledButton = styled.button`
    position: absolute;
    height: auto;
    font-size: 12px;
    font-weight: bold;
    color: #8f47f5;
    display: ${(props) => (props.check ? "" : "none")};
    right: 10px;
    top: 12px;
`;
