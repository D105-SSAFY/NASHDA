import styled from "styled-components";
import { Link } from "react-router-dom";

export const ConversationWrapper = styled.div`
    padding-bottom: ${(props) => (props.isConv ? "80px" : "0")};
    & > :nth-child(2) {
        margin-top: 20px;

        & > :first-child {
            margin-left: 30px;
            background-color: #f2f2f2;
        }

        & > :nth-child(2) {
            width: 830px;
            margin-left: 30px;
            background-color: transparent;
        }

        & > ul {
            width: 830px;
            margin-top: -181px;
            margin-left: 30px;

            border-radius: 10px;
        }
    }
`;
export const ConversationTitle = styled.h2``;

export const InfoWrapper = styled.div`
    width: 100%;
    padding: 45px;
`;

export const InfoTitle = styled.h2`
    color: #000;
    opacity: 0.6;

    margin: 0 auto;
    text-align: center;

    font-size: 2.6rem;
`;

export const NoDataWrapper = styled.div`
    width: 100%;
    padding: 45px;
`;

export const NoData = styled.h2`
    color: #000;
    opacity: 0.6;

    margin: 0 auto;
    text-align: center;

    font-size: 2.6rem;
`;

export const NoDataLink = styled(Link)`
    display: flex;
    justify-content: center;
    align-items: center;

    font-size: 1.8rem;
    font-weight: 500;

    margin-top: 8px;

    & > svg {
        width: 1.8rem;
        height: 1.8rem;

        vertical-align: middle;
    }
`;

export const TypeTitle = styled.h3`
    margin-top: 50px;
    margin-left: 40px;

    font-size: 2rem;
`;

export const ChatBox = styled.section`
    width: 830px;
    height: 300px;

    margin-top: 8px;
    margin-left: 40px;

    padding-top: 20px;

    border-radius: 10px;
    background-color: #44475a;

    overflow-y: scroll;

    & > :first-child {
        margin-top: 0;
    }
`;

export const MyChat = styled.article`
    position: relative;
    //우측으로 붙이기
    margin-right: 40px;
    margin-bottom: 12px;
    margin-left: auto;

    padding: 20px;

    max-width: 500px;
    width: fit-content;
    height: fit-content;

    background-color: #64acef;

    color: #fff;
    font-size: 2rem;
    line-height: 1.2;

    border-radius: 12px 0 12px 12px;
`;

export const AiChat = styled.article`
    position: relative;
    margin-left: 40px;
    margin-bottom: 60px;

    padding: 20px;

    max-width: 500px;
    width: fit-content;
    height: fit-content;

    background-color: #fff;

    color: #000;
    font-size: 1.8rem;
    font-weight: 500;
    line-height: 1.2;

    border-radius: 0 12px 12px 12px;

    //내부 글자 상하 간격 8px

    & > svg {
        position: absolute;
        top: -3.3rem;
        left: 0;

        width: 3rem;
        height: 3rem;

        color: #fff;
    }
`;
