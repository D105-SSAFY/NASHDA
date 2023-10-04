import IROnly from "style/IROnly";
import styled from "styled-components";

export const Wrapper = styled.div`
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;

    background-color: rgba(120, 120, 120, 0.4);

    display: ${(props) => (props.visible ? "block" : "none")};
`;

export const Section = styled.section`
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);

    width: 600px;
    padding: 40px 40px;

    background-color: #ffffff;
    border-radius: 10px;

    & > header {
        ${IROnly}
    }
`;

export const Content = styled.p`
    font-size: 1.6rem;
    font-weight: 500;
    color: rgb(170, 170, 170);

    margin-bottom: 35px;
`;

export const CarouselWrapper = styled.div`
    width: 100%;
    height: fit-content;

    overflow: hidden;

    position: relative;
`;

export const CarouselButton = styled.button`
    position: absolute;
    top: 50%;
    transform: translate(-50%);

    & > svg {
        width: 30px;
        height: 30px;
        color: rgb(170, 170, 170);
    }
`;

export const CarouselLeft = styled(CarouselButton)`
    left: 10px;
`;

export const CarouselRight = styled(CarouselButton)`
    right: -20px;
`;

export const FeedbackList = styled.ul`
    width: 520px;

    display: flex;

    margin-left: ${(props) => props.move};

    transition: all 0.3s;
`;

export const FeedbackListItem = styled.li`
    width: 520px;

    flex-shrink: 0;
    display: flex;
    flex-direction: column;
`;

export const Pron = styled.p`
    padding-left: 10%;
    margin-bottom: 12px;

    font-size: 2.4rem;
    font-weight: 500;
    color: rgb(120, 120, 120);

    & > span {
        margin-left: 10px;

        font-size: 1.6rem;
        font-weight: 500;
        color: rgb(170, 170, 170);
    }
`;

export const PronImage = styled.img`
    width: 80%;
    height: auto;

    margin: auto;

    border-radius: 14px;

    object-fit: contain;
`;

export const ButtonWrapper = styled.div`
    margin-top: 40px;

    display: flex;
`;
