import styled from "styled-components";

export const DeFocusTouch = styled.div`
    position: absolute;

    top: 123px;
    left: 0;

    width: 100%;
    height: calc(100% - 123px);
`;

export const UserSection = styled.section`
    position: relative;
    width: 950px;

    margin: 0 auto;
    margin-top: 25px;
    padding: 0 20px;

    font-weight: 700;
    font-size: 3rem;

    & > :first-child {
        top: 20px;
        left: 20px;
        width: 260px;
    }

    & > :nth-child(2) {
        top: 20px;
        right: 20px;
        width: 630px;
        height: ${(props) => (props.more === 3 ? "660px" : "320px")};

        background-color: ${(props) => (props.more === 1 ? "#44475a" : "#64acef")};

        z-index: 1000;

        & > div > button,
        h3 {
            color: #ffffff;

            font-weight: 400;
        }

        overflow-x: hidden;
        overflow-y: ${(props) => (props.more === 3 ? "auto" : "hidden")};
    }

    & > :nth-child(3) {
        top: 360px;
        left: 20px;
        width: 260px;
    }

    & > :nth-child(4) {
        top: 360px;
        left: 300px;

        width: ${(props) => (props.more === 4 ? "630px" : "350px")};
    }
`;

export const UserCard = styled.article`
    position: absolute;
    width: 300px;
    height: 320px;

    display: flex;
    flex-direction: column;
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
