import { createGlobalStyle } from "styled-components";
import { reset } from "styled-reset";
import PrVr from "../assets/fonts/PretendardVariable.ttf";

export const GlobalStyle = createGlobalStyle`
    ${reset}

    @font-face {
        font-family: 'PrVr';
        src: local('PrVr'), local('PrVr');
        font-style: normal;
        src: url(${PrVr}) format('truetype');
    }

    html {
        font-size: 10px;
    }

    body, input, textarea {
        font-family: "PrVr", Pretendard, -apple-system, BlinkMacSystemFont, system-ui, Roboto, "Helvetica Neue", "Segoe UI", "Apple SD Gothic Neo", "Noto Sans KR", "Malgun Gothic", "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", sans-serif;;
        text-rendering: optimizeLegibility;
    }

    *, *::before, *::after {
        box-sizing: border-box;
    }

    li {
        list-style: none;
    }

    button, a {
        background-color: inherit;
        border: none;

        font-family: 'PrVr', 'Noto Sans KR', sans-serif;

        cursor: pointer;

        &:focus {
            outline: none;
        }
    }

    button {
        padding: 0;
    }

    a {
        color: inherit;
        text-decoration: none;
    }

    *::-webkit-scrollbar {
        width: 5px;
    }

    *::-webkit-scrollbar-thumb {
        background: #ffffffcc; /* 스크롤바 색상 */
        border-radius: 3px;
    }

    *::-webkit-scrollbar-track {
        background: rgba(255, 255, 255, 0); /*스크롤바 뒷 배경 색상*/
    }

    *::-webkit-scrollbar-button {
        height: 6px;
    }
`;
