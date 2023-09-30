import styled from "styled-components";

export const DetailTypeBox = styled.div`
    position: absolute;

    // 세로 수직 중앙 정렬
    top: 50%;
    left: 30px;
    transform: translateY(-50%);
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

    top: 50%;
    left: 120px;
    transform: translateY(-50%);
`;

export const DetailContentText = styled.p`
    display: flex;

    font-size: 2rem;
    font-weight: 400;
    margin-bottom: 0.5rem;
`;

export const DivideLine = styled.div`
    position: absolute;
    top: 50%;
    transform: translateY(-50%);
    left: 105px;
    margin: auto 0;

    height: 154px;
    width: 2px;

    background-color: #000;
`;

export const InputWrapper = styled.div`
    position: relative;
    top: 59px;
    left: 0px;
    width: 660px;
    height: 400px;

    max-width: 100%;

    overflow: hidden;
`;

export const InputBox = styled.div`
    position: absolute;

    left: 280px;
    margin: auto 0;

    & > div {
        margin-top: 12px;
        margin-bottom: 11px;
    }

    & > div > label {
        background-color: #faf8f2;
    }
    & > div > :nth-child(2) {
        display: block;
        width: 240px;
        height: 50px;

        border-radius: 10px;
        border: 1.8px solid black;

        background-color: #faf8f2;

        &:focus {
            outline: 1px solid #000;
        }
    }

    & > div > ul {
        width: 240px;
    }

    & > div > button > span {
        position: relative;
        display: block;
        width: fit-content;
        margin-left: 7px;
        font-weight: 400;
        font-size: 1.6rem;
    }

    & > div > ul > li > button > span {
        position: relative;
        display: block;
        width: fit-content;
        margin-left: 4px;
        font-weight: 400;
        font-size: 1.6rem;
    }
`;
