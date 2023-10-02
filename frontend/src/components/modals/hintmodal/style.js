import IROnly from "style/IROnly";
import styled from "styled-components";

export const Wrapper = styled.div`
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;

    background-color: rgba(120, 120, 120, 0.4);

    display: ${(props) => (props.visible ? "block" : "none")};
`;

export const Section = styled.section`
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);

    width: 600px;
    padding: 40px 40px;

    background-color: #ffffff;
    border-radius: 10px;

    & > header {
        ${IROnly}
    }
`;

export const List = styled.ul`
    display: flex;
    flex-direction: column;
`;

export const ListItem = styled.li`
    & > p {
        line-height: 2.2rem;
    }

    & > p:first-child {
        margin-bottom: 12px;

        font-size: 1.4rem;
        font-weight: 500;
        color: rgb(170, 170, 170);
    }

    & > p:nth-child(2),
    & > p:nth-child(3) {
        font-size: 1.9rem;
        font-weight: 500;
        color: rgb(120, 120, 120);
    }

    & > p:nth-child(2) {
        margin-bottom: 10px;
    }

    & > p:nth-child(3) {
        margin-bottom: 32px;
    }
`;

export const ButtonWrapper = styled.div`
    margin-top: 10px;

    display: flex;
`;
