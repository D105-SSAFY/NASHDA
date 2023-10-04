import styled from "styled-components";

export const StatDetailWrapper = styled.div`
    position: relative;
    & > :first-child {
        height: ${(props) => (props.isChar ? "250px" : "auto")};
    }

    & > :nth-child(2) {
        height: ${(props) => (props.isConv ? "116px" : "auto")};
        transition: all ease 0.5s;
    }

    & > :nth-child(3) {
        height: ${(props) => (props.isWeek ? "260px" : "auto")};
    }

    & > :nth-child(4) {
        height: ${(props) => (props.isGame ? "190px" : "auto")};
        padding-bottom: ${(props) => (props.isGame ? "80px" : "0")};
        transition: all ease 0.8s;
    }
`;

export const SizeWrapper = styled.div`
    height: auto;
    overflow: visible;
`;

export const StatDetailCard = styled.section`
    position: relative;
    width: 910px;

    overflow: hidden;

    transition: all ease 0.7s;
`;

export const CardDivLine = styled.div`
    position: absolute;
    top: 0;
    left: 1%;

    width: 98%;
    height: 1px;
    background-color: #000000;
    opacity: 0.2;
    margin: 0 auto;
`;
