import styled from "styled-components";

export const ChangeBox = styled.div`
    width: fit-content;
    //flex, margin 쓰지않고 수평, 수직 중앙정렬
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);

    & > :first-child {
        margin-bottom: 1.5rem;
    }
`;

export const ChangeText = styled.p`
    font-size: 2rem;
    font-weight: 600;

    white-space: nowrap;

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
