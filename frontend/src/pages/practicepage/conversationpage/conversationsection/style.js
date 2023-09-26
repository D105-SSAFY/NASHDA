import IROnly from "style/IROnly";
import styled from "styled-components";

export const Section = styled.section`
    height: 100%;

    display: flex;
    flex-direction: column;

    & > header {
        ${IROnly}
    }
`;

export const ConversationList = styled.ul`
    padding: 26px 0;

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
