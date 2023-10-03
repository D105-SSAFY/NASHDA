import styled from "styled-components";

export const DeFocusTouch = styled.div`
    position: absolute;

    top: 123px;
    left: 0;

    width: 100%;
    height: 100%;
`;

export const UserSection = styled.section`
    position: relative;
    width: 950px;
    height: fit-content;
    min-height: 700px;

    margin: 0 auto;
    margin-top: 25px;
    padding: 0 20px;

    overflow: hidden;

    font-weight: 700;
    font-size: 3rem;

    & > :first-child {
        position: relative;

        top: 20px;
        left: ${(props) => (props.more === 5 ? "-280px" : "0px")};
        width: 260px;

        flex-direction: column;
    }

    & > :nth-child(2) {
        top: 20px;
        right: 20px;
        width: ${(props) => (props.more === 5 ? "910px" : "630px")};
        height: ${(props) => (props.more === 3 ? "660px" : "320px")};

        flex-direction: column;

        background-color: ${(props) => (props.more === 1 ? "#44475a" : props.more === 3 ? "#faf8f2" : "#64acef")};

        z-index: 1000;

        & > div > button,
        h3 {
            color: #ffffff;

            font-weight: 400;
        }

        & > div > button {
            opacity: ${(props) => (props.more === 5 ? "0.3" : "1")};
            pointer-events: ${(props) => (props.more === 5 ? "none" : "auto")};

            transition: opacity ease 0.5s;
        }

        overflow-x: hidden;
        overflow-y: ${(props) => (props.more === 3 ? "auto" : "hidden")};
    }

    & > :nth-child(3) {
        top: 360px;
        left: ${(props) => (props.more === 5 && props.tabChanged ? "950px" : "20px")};

        width: 260px;

        transition: ${(props) => (props.more === 5 ? "all ease 0.9s" : "all ease 0.5s")};
    }

    & > :nth-child(4) {
        top: 360px;
        left: ${(props) => (props.more === 5 && props.tabChanged ? "1230px" : "300px")};

        width: ${(props) => (props.more === 4 ? "630px" : "260px")};

        transition: ${(props) => (props.more === 5 ? "all ease 0.9s" : "all ease 0.5s")};

        background-color: #faf8f2;
    }

    & > :nth-child(5) {
        position: relative;

        margin-top: 40px;
        left: ${(props) => (props.more === 5 && props.tabChanged ? "0px" : "-930px")};
        width: 910px;
        height: fit-content;

        visibility: ${(props) => (props.more === 5 && props.tabChanged ? "show" : "hidden")};

        background-color: #f2f2f2;

        transition: ${(props) => (props.more === 5 ? "all ease 0.9s" : "all ease 0.5s")};
    }
`;

export const UserCard = styled.article`
    position: absolute;
    width: 300px;
    height: 320px;

    display: flex;
    justify-content: center;

    opacity: ${(props) => (props.defocus ? "1" : "0.5")};
    pointer-events: ${(props) => (props.defocus ? "auto" : "none")};

    border: ${(props) => (props.focus ? "2px solid #cccccc;" : "2px solid #ffffff00;")};
    border-radius: 12px;
    background-color: #f2f2f2;

    transition: all ease 0.5s;
`;

export const MoreButton = styled.button`
    position: absolute;
    right: 1rem;
    bottom: 1rem;
    vertical-align: middle;

    font-size: 1.6rem;
    font-weight: 500;

    & > svg {
        width: 2rem;
        height: 2rem;

        margin-top: -0.2rem;

        vertical-align: middle;
    }
`;

export const CloseButton = styled.button`
    position: absolute;
    right: 1rem;
    top: 1rem;

    width: 15px;
    height: 15px;

    background-color: #ff5555;
    opacity: 0.9;

    border-radius: 10px;

    visibility: ${(props) => (props.toggle ? "hidden" : "show")};
    opacity: ${(props) => (props.toggle ? "0" : "1")};
    transition: ease 0.4s;
`;

export const modeChange = styled.div`
    visibility: ${(props) => (props.toggle ? "show" : "hidden")};
    opacity: ${(props) => (props.toggle ? "1" : "0")};
    transition: ease 0.4s;
`;
