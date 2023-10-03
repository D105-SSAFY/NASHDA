import IROnly from "style/IROnly";
import styled from "styled-components";

export const Section = styled.section`
    width: 810px;

    flex-grow: 1;
    display: flex;
    flex-direction: column;

    & > header {
        ${IROnly}
    }
`;

export const ListenWrapper = styled.div`
    width: 500px;

    margin: 30px auto 0;

    padding: 20px 30px;

    background-color: #f8f8f8;
    border-radius: 10px;

    display: flex;
    justify-content: center;
    align-items: center;
    gap: 14px;

    & > p {
        font-size: 1.9rem;
        font-weight: 500;
        color: rgb(170, 170, 170);
    }
`;

export const SpeakButton = styled.button`
    height: 20px;
    margin-bottom: 1px;

    color: rgb(100, 100, 100);

    display: flex;
    align-items: center;

    & > span {
        font-size: 1.6rem;
        font-weight: 500;
        color: rgb(120, 120, 120);
    }

    & > svg {
        width: 20px;
        height: 20px;
    }
`;

export const ImageList = styled.ul`
    margin-top: 30px;

    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
    gap: 20px;

    & > li {
        width: 100%;

        display: flex;
        justify-content: center;
    }
`;

export const ImageButton = styled.button`
    height: 240px;

    border-radius: 12px;
    box-shadow: 0 0 15px 1px #f2f2f2;

    overflow: hidden;

    & > img {
        width: 300px;
        height: 240px;

        object-fit: cover;
    }
`;

export const ButtonWrapper = styled.div`
    width: 400px;

    margin: auto auto 0;

    display: flex;
`;
