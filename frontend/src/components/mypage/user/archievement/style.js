import styled from "styled-components";

export const ArchieveBox = styled.div`
    position: relative;
    width: 100px;
    height: 100px;
    margin: auto;
`;

export const ArchieveEmoji = styled.img`
    width: 100px;
    height: 100px;
    margin: auto;

    filter: drop-shadow(0 3px 4px rgba(0, 0, 0, 0.5));
`;

export const ArchieveText = styled.p`
    position: absolute;
    right: 0;
    bottom: 0;
    width: fit-content;

    padding: 7px 14px 7px 14px;
    font-size: 1.8rem;
    font-weight: 400;

    transform: translateX(50%) translateY(50%);

    border-radius: 30px;

    background-color: #d9d9d9;
    color: #000;
`;
