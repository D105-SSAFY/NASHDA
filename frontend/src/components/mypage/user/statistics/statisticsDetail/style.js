import styled from "styled-components";

export const StatDetailWrapper = styled.div`
    position: relative;
    & > :first-child {
        height: ${(props) => (props.isData[0] ? (props.isToggle[0] ? "880px" : "250px") : "fit-content")};
    }

    & > :nth-child(2) {
        height: ${(props) =>
            props.isData[1]
                ? props.isToggle[1]
                    ? props.amount === 1
                        ? "554px"
                        : props.amount === 2
                        ? "932px"
                        : "1310px"
                    : "116px"
                : "fit-content"};
    }

    & > :nth-child(3) {
        height: ${(props) => (props.isData[2] ? (props.isToggle[2] ? "880px" : "250px") : "fit-content")};
    }
`;

export const StatDetailCard = styled.section`
    position: relative;
    width: 910px;
    height: fit-content;

    overflow: hidden;

    transition: all ease 0.7s;
`;

export const CardDivLine = styled.div`
    position: absolute;
    bottom: 0;
    left: 1%;

    width: 98%;
    height: 1px;
    background-color: #000000;
    opacity: 0.2;
    margin: 0 auto;
`;
