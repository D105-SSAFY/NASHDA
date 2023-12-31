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
    text-align: center;
    max-width: 910px;
`;
export const StyledVid = styled.video`
    width: 100%;
    max-height: 510px;
`;

export const StyledSiginBtn = styled.button`
    margin-left: auto;
    margin-top: 20px;
`;
export const StyledImg = styled.img`
    height: 56px;
`;

export const StyledSigninTitle = styled.h3`
    font-size: 35px;
    font-weight: bold;
    margin-top: -12%;
    margin-bottom: 35px;

    @media screen and (max-width: 500px) {
        font-size: 28px;
    }
`;
export const StyledForm = styled.form`
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 35%;
    min-width: 250px;
    margin-top: 20px;
`;
export const StyledLinkSection = styled.section`
    display: flex;
    flex-direction: column;
    width: 100%;
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
    height: 30px;
`;
