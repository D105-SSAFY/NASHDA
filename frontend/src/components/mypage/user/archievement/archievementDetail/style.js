import styled from "styled-components";

export const ArchievesBox = styled.div`
    position: absolute;
    top: 0px;

    width: 620px;
    display: grid;
    grid-template-columns: repeat(2, 1fr);

    column-gap: 10px;
    row-gap: 10px;
`;

export const ArchieveItem = styled.span`
    position: relative;
    width: 100%;
    height: 84px;

    & > img {
        position: absolute;
        top: 10px;
        left: 10px;

        width: 64px;
        height: 64px;
    }
`;

export const ArchieveTitle = styled.p`
    position: absolute;
    top: 24px;
    left: calc((216px / 2) + 84px);
    transform: translateX(-50%);

    width: 216px;
    text-align: center;

    font-size: 1.6rem;
    color: #000;
`;

export const ArchieveDate = styled.p`
    position: absolute;
    bottom: 24px;
    left: calc((216px / 2) + 84px);
    transform: translateX(-50%);

    width: 216px;
    text-align: center;

    font-size: 1.4rem;
    font-weight: 400;
    color: #000;
`;

export const ArchieveDivLine = styled.div`
    position: absolute;
    width: 305px;
    height: 1px;

    bottom: -6px;
    left: 0px;

    background-color: #000000;
    opacity: 0.15;
`;

export const ArchieveVertDivLine = styled.div`
    position: absolute;
    width: 1px;
    height: 84px;

    top: 0px;
    right: -6px;

    background-color: #000000;
    opacity: 0.15;
`;
