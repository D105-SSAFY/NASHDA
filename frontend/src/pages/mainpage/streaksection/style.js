import styled from "styled-components";

export const Section = styled.section`
    padding: 22px 0 26px;
`;

export const Wrapper = styled.div`
    max-width: 920px;
    margin: 0 auto;
    padding: 0 55px;
`;

export const Header = styled.header`
    position: relative;
    padding-bottom: 10px;
    margin-bottom: 22px;

    border-bottom: 1px solid rgba(0, 0, 0, 0.6);

    & > h2 {
        font-size: 2.4rem;
        font-weight: 500;
        line-height: 2.9rem;
        color: rgba(0, 0, 0, 0.6);
    }

    & > span {
        position: absolute;
        top: 0;
        right: 0;
        height: 2.9rem;

        font-size: 1.4rem;

        // 수직 중앙정렬
        display: flex;
        align-items: center;

        & > span {
            margin: 0 5px 0 5px;

            cursor: pointer;

            transition: all ease 0.2s;
        }

        & > :first-child {
            color: ${(props) => (props.theme === "default" ? "#6446ff" : "#000")};
        }

        & > :nth-child(2) {
            color: ${(props) => (props.theme === "fruit" ? "#6446ff" : "#000")};
        }

        & > :nth-child(3) {
            color: ${(props) => (props.theme === "animal" ? "#6446ff" : "#000")};
        }

        & > :nth-child(4) {
            color: ${(props) => (props.theme === "ocean" ? "#6446ff" : "#000")};
        }

        & > :nth-child(5) {
            color: ${(props) => (props.theme === "plant" ? "#6446ff" : "#000")};
        }
    }
`;
