import styled from "styled-components";

export const ArchievesBox = styled.div`
    //안에 span들을 한줄당 4개씩 배치하도록 설정
    display: grid;
    grid-template-columns: repeat(4, 1fr);

    //span들의 간격을 설정
    column-gap: 10px;
    row-gap: 10px;
`;

export const ArchieveItem = styled.span`
    width: 100%;
    height: 200px;
    background-color: #fff;
`;
