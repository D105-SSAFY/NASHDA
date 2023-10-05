import styled from "styled-components";

export const Section = styled.section`
    margin-top: 40px;

    flex-grow: 1;

    display: flex;
    flex-direction: column;
`;

export const Header = styled.header`
    margin-bottom: 14px;
    margin-left: 3px;

    & > h2 {
        font-size: 1.4rem;
        font-weight: 500;
        color: rgb(120, 120, 120);
    }
`;

export const Box = styled.div`
    padding: 50px 35px;

    background-color: #f8f8f8;

    border-radius: 10px;

    display: flex;
`;

export const Pron = styled.p`
    font-size: 2.4rem;
    font-weight: 500;
`;

export const SpeakButton = styled.button`
    height: 20px;

    margin: auto 0 1px 20px;

    text-align: right;
    color: rgb(140, 140, 140);

    display: ${(props) => (props.visible ? "block" : "none")};

    & > svg {
        width: 20px;
        height: 20px;
    }
`;

export const FeedbackButton = styled.button`
    height: 20px;

    margin: auto 0 1px auto;

    text-align: right;
    color: #ff4455;

    display: ${(props) => (props.visible ? "block" : "none")};

    & > svg {
        width: 20px;
        height: 20px;
    }
`;

export const Match = styled.span`
    color: #57ea74;
`;

export const Unmatch = styled.span`
    color: #ff4455;
`;

export const ButtonWrapper = styled.div`
    margin-top: auto;

    display: flex;
    gap: 10px;
`;
