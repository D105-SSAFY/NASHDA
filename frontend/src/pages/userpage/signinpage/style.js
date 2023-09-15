import styled from "styled-components";
export const StyledMain = styled.main`
    display: flex;
    flex-direction: column;
    align-items: center;
    height: 100vh;
`;
export const StyledMainSection = styled.section`
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 800px;
    height: 95%;
    /* border : black solid 1px; */
`;
export const StyledVid = styled.video`
    width: ${(props) => props.width};
    height: ${(props) => props.height};
`;

export const StyledSiginBtn = styled.button`
    margin-left: 180px;
    margin-top: 20px;
`;
export const StyledImg = styled.img`
    height: 56px;
`;

export const StyledSigninTitle = styled.h3`
    font-size: 30px;
    font-weight: bold;
    margin-top: -80px;
    margin-bottom: 35px;
`;
export const StyledForm = styled.form`
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 100%;
`;
export const StyledTextSection = styled.section`
    display: flex;
    flex-direction: column;
    width: 30%;
    height: 10%;
    /* border : black solid 1px; */
    margin-top: 20px;
`;
export const StyledText = styled.p`
    font-size: ${(props) => props.size};
    font-weight: bold;
    margin-bottom: 8px;
`;
export const StyledFooter = styled.footer`
    display: flex;
    flex-direction: column;
    width: 800px;
    height: 10%;
    /* border : black solid 1px; */
`;
