import IROnly from "style/IROnly";
import styled from "styled-components";

export const Section = styled.section`
    height: 100%;

    position: relative;

    display: flex;
    flex-direction: column;
`;

export const Header = styled.header`
    & > h2 {
        ${IROnly}
    }
`;

export const Explain = styled.p`
    padding: 14px 22px;

    position: absolute;
    top: 30px;
    left: 50%;
    transform: translateX(-50%);

    border: 2px solid #f2f2f2;
    border-radius: 10px;

    background-color: #ffffff;

    font-size: 1.9rem;
    font-weight: 500;
    color: rgb(170, 170, 170);

    & > span {
        color: rgb(40, 40, 40);
    }
`;

export const ConversationList = styled.ul`
    padding: 86px 0 26px;

    flex-grow: 1;
    display: flex;
    flex-direction: column;
    gap: 22px;

    overflow-y: scroll;

    &::-webkit-scrollbar {
        display: none;
    }
`;

export const Talker = styled.p`
    width: 100%;

    font-size: 1.6rem;
    font-weight: 500;
    line-height: 22px;

    color: rgba(0, 0, 0, 0.6);

    margin: 6px 0;

    display: flex;
    align-items: center;
    gap: 4px;

    & > svg {
        width: 18px;
        height: 18px;

        color: #44475a;
    }
`;

export const BotTalker = styled(Talker)`
    & > p {
        margin-right: auto;
    }
`;

export const UserTalker = styled(Talker)`
    & > svg {
        margin-left: auto;
    }
`;

export const Chat = styled.p`
    width: fit-content;
    max-width: 700px;

    padding: 22px 16px;

    font-size: 1.4rem;
    font-weight: 500;
    line-height: 1.8rem;

    word-break: break-all;
`;

export const BotChat = styled(Chat)`
    margin-right: auto;

    background-color: #44475a;
    color: white;

    border-radius: 0 10px 10px 10px;
`;

export const MyChat = styled(Chat)`
    margin-left: auto;

    background-color: #eee;

    border-radius: 10px 0 10px 10px;
`;

export const Incorrect = styled.p`
    width: 100%;

    font-size: 1.3rem;
    font-weight: 500;
    line-height: 22px;

    color: #ff4455;

    margin: 6px 0;

    display: flex;
    align-items: center;
    gap: 4px;

    & > span {
        margin-left: auto;
    }
`;
