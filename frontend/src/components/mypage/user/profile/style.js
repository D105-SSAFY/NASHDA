import styled from "styled-components";

export const DetailTypeBox = styled.div`
    position: absolute;
    margin: auto 0;
    margin-left: 30px;
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
    margin-left: 120px;
`;

export const DetailContentText = styled.p`
    display: flex;

    font-size: 2rem;
    font-weight: 400;
    margin-bottom: 0.5rem;
`;

export const DivideLine = styled.div`
    position: absolute;
    left: 105px;
    margin: auto 0;

    height: 154px;
    width: 2px;

    background-color: #000;
`;

export const InputBox = styled.div`
    position: absolute;
    left: 280px;
    margin: auto 0;

    & > div > label {
        background-color: #faf8f2;
        margin-left: 20px;
    }
    & > div > input,
    select {
        display: block;
        margin: 10px;
        width: 240px;

        background-color: #faf8f2;
    }
`;
