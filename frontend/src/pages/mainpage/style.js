import styled from "styled-components";

export const Main = styled.main`
    padding-bottom: 120px;
`;

export const Section = styled.section`
    max-width: 920px;

    margin: 0 auto;
    padding: 22px 55px 26px;
`;

export const SectionHeader = styled.header`
    padding-bottom: 10px;
    margin-bottom: 14px;

    border-bottom: 1px solid rgba(0, 0, 0, 0.6);

    & > h2 {
        font-size: 2.4rem;
        font-weight: 500;
        line-height: 2.9rem;
        color: rgba(0, 0, 0, 0.6);
    }
`;

export const SectionList = styled.ul`
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(210px, 1fr));
    gap: 20px;

    padding: 0 8px;
`;

export const Article = styled.article`
    display: flex;
    gap: 10px;

    & > svg {
        width: 26px;
        height: 26px;

        margin-top: 8px;

        color: ${(props) => (props.color ? props.color : "#000")};
    }
`;

export const ArticleHeader = styled.header`
    display: flex;
    flex-direction: column;
    gap: 4px;

    & > h3 {
        font-size: 1.8rem;
        font-weight: 500;
        line-height: 21px;
    }

    & > p {
        font-size: 1.4rem;
        font-weight: 500;
        line-height: 16px;
        color: rgba(0, 0, 0, 0.8);

        word-break: keep-all;
    }
`;
