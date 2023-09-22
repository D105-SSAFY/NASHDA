import styled from "styled-components";

export const DeFocusTouch = styled.div`
    position: absolute;

    top: 123px;
    left: 0;

    width: 100%;
    height: calc(100% - 123px);
`;

export const UserSection = styled.section`
    display: flex;
    flex-wrap: wrap;
    justify-content: flex-start;
    gap: 2rem;

    position: relative;
    width: 950px;

    margin: 0 auto;
    margin-top: 50px;
    padding: 0 20px;

    font-weight: 700;
    font-size: 3rem;

    & > :first-child {
        width: 260px;
    }

    & > :nth-child(2) {
        width: 630px;

        background-color: #64acef;
        & > button {
            color: #ffffff;

            font-weight: 400;
        }
    }

    & > :nth-child(3) {
        width: 260px;
    }

    & > :nth-child(4) {
        width: 350px;
    }

    & > :nth-child(5) {
        width: 260px;

        opacity: 0;
        border-radius: 0px;
    }
`;

export const UserCard = styled.article`
    position: relative;
    width: 300px;
    height: 320px;

    display: flex;
    flex-direction: column;
    justify-content: center;

    opacity: ${(props) => (props.defocus ? "1" : "0.5")};

    border: ${(props) => (props.focus ? "2px solid #cccccc;" : "2px solid #ffffff00;")};
    border-radius: 12px;
    background-color: #f2f2f2;

    transition: all ease 0.3s;
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

export const ProfileName = styled.h2`
    display: flex;
    justify-content: center;

    text-decoration: underline;
    text-underline-position: under;
    text-underline-offset: -2px;
`;
