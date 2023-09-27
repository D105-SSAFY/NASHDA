import styled from "styled-components";

export const ConfirmButton = styled.button`
    flex-grow: 1;
    height: 44px;

    display: flex;
    align-items: center;
    justify-content: center;
    gap: 5px;

    border-radius: 8px;

    font-size: 16px;
    font-weight: 500;

    & > svg {
        width: 18px;
        height: 18px;
    }
`;
