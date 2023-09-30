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

export const StyledImg = styled.img`
    position: absolute;
    right: 5px;
    top: 10%;
`;

export const Wrapper = styled.div`
    width: 100%;
    position: relative;
    margin: 10px;
`;

export const Button = styled.button`
    width: 100%;
    height: 55px;
    text-align: center;
    padding: 0 12px;
    position: relative;
    background-color: white;
    font-size: 20px;
    border: 2px solid;
    border-radius: 6px;
    color: black;

    &::before {
        content: "";
        display: block;
        width: 8px;
        height: 8px;
        position: absolute;
        right: 15px;
        top: 20px;
        transition: all 0.2s;
        transform: ${(props) => (props.clicked ? "translate(5px, -1px) rotate(225deg);" : "translate(0, -1px) rotate(225deg)")};
        border-top: 2px solid;
    }

    &::after {
        content: "";
        display: block;
        width: 8px;
        height: 8px;
        position: absolute;
        right: 15px;
        top: 20px;
        transition: all 0.2s;
        transform: ${(props) => (props.clicked ? "translate(-4px, -1px) rotate(135deg);" : "translate(0, -1px) rotate(135deg)")};
        border-top: 2px solid;
    }
`;

export const List = styled.ul`
    width: 100%;
    max-height: 200px;
    overflow-y: scroll;
    position: absolute;
    top: 57px;
    padding: 4px 0;
    z-index: 1000;
    box-shadow: 0 0 5px 1px gray;
    border-radius: 6px;
    background-color: white;
    display: ${(props) => (props.clicked ? "block" : "none")};

    &::-webkit-scrollbar {
        width: 12px;
    }

    &::-webkit-scrollbar-track {
        background-color: transparent;
    }

    &::-webkit-scrollbar-thumb {
        background-color: gray;
        border-radius: 10px;
        border: 3px solid transparent;
        background-clip: padding-box;
    }

    &::-webkit-scrollbar-button {
        width: 0;
        height: 0;
    }
`;

export const ListItem = styled.li`
    width: 100%;
`;

export const ListButton = styled.button`
    width: 100%;
    border: 0;
    padding: 5px 16px;
    text-align: left;
    font-size: 20px;
    background-color: white;

    &:hover {
        background-color: lightskyblue;
        color: white;
    }
`;
