import styled from "styled-components";

export const DetailTypeBox = styled.div`
    top: 20px;
    position: absolute;

    // 세로 수직 중앙 정렬
    left: 20px;
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
    top: 20px;
    position: absolute;

    left: 100px;
`;

export const DetailContentText = styled.p`
    display: flex;

    font-size: 2rem;
    font-weight: 400;
    margin-bottom: 0.5rem;
`;

export const DivideLine = styled.div`
    top: 20px;
    position: absolute;
    left: 90px;
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

    left: 355px;
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

export const GradBox = styled.div`
    position: absolute;
    top: 0;
    right: 0;
    width: 50px;
    height: 200px;
    background: linear-gradient(90deg, rgba(250, 248, 242, 0) 0%, rgba(250, 248, 242, 1) 65%, rgba(250, 248, 242, 1) 100%);
`;
