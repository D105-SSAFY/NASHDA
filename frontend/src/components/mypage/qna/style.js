import styled from "styled-components";

export const QuestionBackground = styled.div`
    position: absolute;

    top: 123px;
    left: 0;

    width: 100%;
    height: calc(100% - 123px);

    background-color: #f2f2f2;
`;

export const QnaList = styled.ul`
    max-height: calc(100vh - 450px);
    min-height: 400px;

    //넘어가는 것 스크롤 처리
    overflow-x: hidden;
    overflow-y: scroll;

    position: relative;
    width: 950px;

    margin: 0 auto;
    margin-top: 20px;
    padding: 0 20px;
`;

export const QnaGradBox = styled.div`
    position: absolute;
    top: 142px;
    width: 100%;
    height: 30px;

    background: rgb(242, 242, 242);
    background: linear-gradient(
        0deg,
        rgba(242, 242, 242, 0) 0%,
        rgba(242, 242, 242, 0.06486344537815125) 18%,
        rgba(242, 242, 242, 0.9220063025210083) 75%,
        rgba(242, 242, 242, 1) 100%
    );
`;

export const AskBox = styled.form`
    position: relative;
    height: 300px;
    width: 950px;

    margin: 0 auto;
    padding: 20px;
`;

export const TitleArea = styled.input`
    width: 100%;
    height: 60px;
    padding-left: 20px;

    border: none;
    border-radius: 12px 12px 0 0;

    background-color: #ffffff;
    color: #000000cc;
    font-size: 1.6rem;
    font-weight: 700;

    box-shadow:
        0 3px 6px rgba(0, 0, 0, 0.08),
        0 3px 6px rgba(0, 0, 0, 0.13);

    &:focus {
        outline: none;
    }
`;

export const ContentWrapper = styled.div`
    width: 100%;
    height: 190px;
    padding: 15px 20px 50px 20px;
    margin-top: 3px;

    background-color: #ffffff;
    border-radius: 0 0 12px 12px;

    box-shadow:
        0 3px 6px rgba(0, 0, 0, 0.08),
        0 3px 6px rgba(0, 0, 0, 0.13);

    & > svg {
        position: absolute;
        bottom: 44px;
        left: 38px;

        width: 2.4rem;
        height: 2.4rem;

        color: #6446ff;
        cursor: pointer;
    }
`;

export const ContentArea = styled.textarea`
    width: ${(props) => (props.selected > 0 ? "calc(100% - 200px)" : "100%")};
    height: 100%;

    border: none;

    background-color: transparent;
    color: #000000cc;
    font-size: 1.6rem;
    font-weight: 500;

    resize: none;

    &:focus {
        outline: none;
    }
`;

export const SubmitButton = styled.button`
    position: absolute;
    top: 20px;
    right: 20px;

    width: 80px;
    height: 60px;

    border-radius: 0 12px 0 0;

    background-color: #6446ff;
    color: #ffffff;
    font-size: 1.6rem;
    font-weight: 550;

    & > svg {
        width: 2.4rem;
        height: 2.4rem;
    }
`;

export const SelectedFiles = styled.ul`
    position: absolute;
    width: 200px;
    height: 125px;

    padding: 0 10 px 0 10px;

    top: 98px;
    right: 32px;

    overflow-x: hidden;
    overflow-y: scroll;

    & > li {
        position: relative;
        font-size: 1.4rem;
        font-weight: 500;
        color: #6446ff;

        overflow-x: hidden;
        overflow-y: hidden;

        white-space: nowrap;
        text-overflow: ellipsis;
        & > svg {
            position: relative;

            top: 2px;
            margin-right: 2px;

            width: 1.5rem;
            height: 1.5rem;
        }
    }
`;
