import styled from "styled-components";

export const ArchievesBox = styled.div`
    position: absolute;
    top: 0px;

    width: 620px;
    display: grid;
    grid-template-columns: repeat(4, 1fr);

    column-gap: 10px;
    row-gap: 10px;
`;

export const ArchieveItem = styled.span`
    width: 100%;
    height: 200px;
    background-color: #fff;
`;
