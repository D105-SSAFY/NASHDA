import styled from "styled-components";

export const styledMain = styled.main`
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 800px;
    height: 95%;
    /* border : black solid 1px; */
`;
export const styledVid = styled.video`
    width: ${(props) => props.width};
    height: ${(props) => props.height};
`;
export const styledImg = styled.img`
    width: ${(props) => props.width};
    height: ${(props) => props.height};
`;

export const styledTitle = styled.p`
    font-size: 30px;
    font-weight: bold;
    margin-top: -80px;
    margin-bottom: 35px;
`;
export const styledTextSection = styled.div`
    display: flex;
    flex-direction: column;
    width: 30%;
    height: 10%;
    /* border : black solid 1px; */
    margin-top: 10px;
`;
export const styledText = styled.p`
    font-size: ${(props) => props.size};
    font-weight: bold;
    margin: 1px;
`;
export const styledFooter = styled.footer`
    display: flex;
    flex-direction: column;
    width: 800px;
    height: 10%;
    /* border : black solid 1px; */
`;
