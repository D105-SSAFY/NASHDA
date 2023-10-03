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

export const StyledButton = styled.button`
    position: absolute;
    height: auto;
    font-size: 16px;
    font-weight: 600;
    color: #6366f8;
    display: ${(props) => (props.check ? "" : "none")};
    right: 15px;
    top: 30%;
`;

export const StyledImg = styled.img`
    position: absolute;
    right: 5px;
    top: 50%;
    transform: translateY(-50%);
`;

export const Button = styled.button`
    width: 100%;
    height: 55px;
    text-align: center;
    padding: 0 12px;
    position: relative;
    background-color: white;
    font-size: 16px;
    border: solid 1.8px black;
    border-radius: 10px;
    color: black;

    & > span {
        position: relative;
        display: block;
        width: fit-content;
        margin-left: 7px;
        font-weight: 400;
        font-size: 1.6rem;
    }

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

    & > span {
        position: relative;
        display: block;
        width: fit-content;
        margin-left: 4px;
        font-weight: 400;
        font-size: 1.6rem;
    }

    &:hover {
        background-color: lightskyblue;
        color: white;
    }
`;
