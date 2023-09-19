import styled from "styled-components";
export const StyledMain = styled.main`
    display: flex;
    flex-direction: column;
    align-items: center;
`;
export const StyledMainSection = styled.section`
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 800px;
`;
export const StyledVid = styled.video`
    width: ${(props) => props.width};
    height: ${(props) => props.height};
`;

export const StyledSiginBtn = styled.button`
    margin-left: 220px;
    margin-top: 20px;
`;
export const StyledImg = styled.img`
    height: 56px;
`;

export const StyledSigninTitle = styled.h3`
    font-size: 30px;
    font-weight: bold;
    margin-top: -100px;
    margin-bottom: 35px;
`;
export const StyledForm = styled.form`
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 100%;
    margin-top: 20px;
`;
export const StyledAnchorSection = styled.section`
    display: flex;
    flex-direction: column;
    width: 35%;
    /* border : black solid 1px; */
    margin-top: 20px;
`;
export const StyledAnchor = styled.a`
    display: inline;
    font-size: ${(props) => props.size};
    font-weight: bold;
    margin-bottom: 8px;
`;
export const StyledFooter = styled.footer`
    display: flex;
    flex-direction: column;
    width: 800px;
    height: 5%;
    /* border : black solid 1px; */
`;