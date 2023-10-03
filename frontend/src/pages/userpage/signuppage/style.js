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
    height: 510px;
`;

export const StyledSignupBtn = styled.button`
    padding: 10px;
    border-radius: 6px;
    margin-top: 30px;
    font-size: 20px;
    font-weight: bold;
    cursor: ${({ disabled }) => (disabled ? "not-allowed" : "pointer")};
`;
export const StyledImg = styled.img`
    height: 56px;
`;

export const StyledSignupTitle = styled.h3`
    font-size: 35px;
    font-weight: bold;
    margin-top: -110px;
    margin-bottom: 35px;
`;
export const StyledForm = styled.form`
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 35%;
    margin-top: 20px;
`;

export const StyledText = styled.p`
    width: 100%;
    height: 16px;
    text-align: left;
    margin: 0 0 1px 32px;
    font-size: 14px;
    color: ${(props) =>
        props.colorEmail === 2 || props.colorEmail === 5 || props.colorPassword === 1 || props.colorPassword === 3 || props.colorNickname === 2
            ? "#6366f8"
            : "#f47560"};
`;

export const StyledAnchorSection = styled.section`
    display: flex;
    flex-direction: column;
    width: 30%;
    height: 10%;
    /* border : black solid 1px; */
    margin-top: 20px;
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
    margin: 14px 0 30px 0;
`;
