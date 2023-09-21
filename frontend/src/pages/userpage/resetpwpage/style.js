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
    width: 910px;
    /* border : black solid 1px; */
`;
export const StyledVid = styled.video`
    width: 100%;
`;

export const StyledResetpwBtn = styled.button`
    margin-top: 30px;
    font-size: 15px;
    font-weight: bold;
`;
export const StyledImg = styled.img`
    height: 56px;
`;

export const StyledResetpwTitle = styled.h3`
    font-size: 35px;
    font-weight: bold;
    margin-top: -110px;
    margin-bottom: 35px;
`;
export const StyledForm = styled.form`
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 30%;
    margin-top: 20px;
`;
export const StyledAnchorSection = styled.section`
    display: flex;
    flex-direction: column;
    width: 30%;
    height: 10%;
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
    height: 90px;
    /* border : black solid 1px; */
`;

export const StyledLine = styled.hr`
    color: lightgray;
    width: 90%;
    margin: 30px 0;
`;
