import styled from "styled-components";

export const DetailTypeBox = styled.div`
    position: absolute;
    margin: auto 0;
    margin-left: 20px;
`;

export const DetailTypeText = styled.p`
    display: flex;
    justify-content: space-between;

    & > span {
        font-size: 2rem;
        font-weight: 600;
        margin-bottom: 0.5rem;
    }
`;

export const DetailContentBox = styled.div`
    position: absolute;
    margin: auto 0;
    margin-left: 110px;
`;

export const DetailContentText = styled.p`
    display: flex;

    font-size: 2rem;
    font-weight: 400;
    margin-bottom: 0.5rem;
`;

export const DivideLine = styled.div`
    position: absolute;
    left: 95px;
    margin: auto 0;

    height: 154px;
    width: 2px;

    background-color: #000;
`;
