import styled from "styled-components";
export const StyledDiv = styled.div`
    position: relative;
    width: 100%;
    margin: 10px;
`;
export const StyledLabel = styled.label`
    width: fit-content;
    text-align: center;
    position: absolute;
    background-color: white;
    color: ${(props) => (props.isFill ? "black" : "grey")};
    font-size: ${(props) => (props.isFill ? "14px" : "14px")};
    font-weight: bold;
    top: ${(props) => (props.isFill ? "-11px" : "27%")};
    left: ${(props) => (props.isFill ? "15px" : "15px")};
    transition: all 0.15s;
    z-index: 200;
    padding: 5px 5px;
    border-radius: 15px;
`;

export const StyledInput = styled.input`
    width: 100%;
    border-radius: 10px;
    border: solid 1.8px black;
    font-size: 16px;
    background-color: ${(props) => (props.readOnly ? "#eee" : "")};
    height: 50px;
    padding: 0 20px;
    padding-right: ${(props) => (props.check ? "50px" : "")};
    size: 30;
    &:focus {
        outline: 1px solid #000;
        outline: ${(props) => (props.readOnly ? "none" : "")};
    }
`;
export const StyledSelect = styled.select`
    width: 100%;
    border-radius: 8px;
    border: solid 1.8px black;
    font-family: "PrVr", sans-serif;
    font-size: 16px;
    background-color: ${(props) => (props.readOnly ? "#eee" : "")};
    height: 50px;
    text-align: center;
    padding-right: ${(props) => (props.check ? "35px" : "")};
    size: 30;
    &:focus {
        outline: 1px solid #000;
        outline: ${(props) => (props.readOnly ? "none" : "")};
    }
    & > option {
        /* option 요소에 적용할 스타일 지정 */
        /* 예: */
        border-radius: 10px;
    }
`;
export const StyledOption = styled.option`
    border: solid 15px black;
`;
export const StyledButton = styled.button`
    position: absolute;
    height: auto;
    font-size: 20px;
    font-weight: bold;
    color: #8f47f5;
    display: ${(props) => (props.check ? "" : "none")};
    right: 15px;
    top: 30%;
`;
