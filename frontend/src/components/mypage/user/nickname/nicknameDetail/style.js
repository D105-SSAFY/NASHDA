import styled from "styled-components";

export const ChangeBox = styled.div`
    width: fit-content;

    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: left;

    margin: 0 auto;

    & > :first-child {
        margin-bottom: 1.5rem;
    }
`;

export const ChangeText = styled.p`
    font-size: 2rem;
    font-weight: 600;

    color: #fff;

    & > span {
        text-decoration: underline;
        text-underline-position: under;
    }

    & > input {
        border-radius: 3px;
        border: none;
        width: 200px;
        height: 3rem;

        margin-top: -2px;
        padding-left: 0.5rem;
        vertical-align: middle;

        font-size: 1.8rem;
        font-weight: 500;

        outline: none;
    }
`;
