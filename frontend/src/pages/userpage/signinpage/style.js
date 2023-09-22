import styled from "styled-components";
import { Link } from "react-router-dom";

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
    width: 100%;
    height: 450px;
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
export const StyledLinkSection = styled.section`
    display: flex;
    flex-direction: column;
    width: 35%;
    margin-top: 20px;
`;
export const StyledLink = styled(Link)`
    font-size: 16px;
    font-weight: bold;
    margin-bottom: 13px;
    width: max-content;
`;
export const StyledFooter = styled.footer`
    display: flex;
    flex-direction: column;
    width: 800px;
    height: 30px;
`;
