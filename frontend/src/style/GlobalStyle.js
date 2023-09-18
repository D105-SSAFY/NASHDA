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

    body {
        font-family: "PrVr", Pretendard, -apple-system, BlinkMacSystemFont, system-ui, Roboto, "Helvetica Neue", "Segoe UI", "Apple SD Gothic Neo", "Noto Sans KR", "Malgun Gothic", "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", sans-serif;;
        text-rendering: optimizeLegibility;
    }

    *, *::before, *::after {
        box-sizing: border-box;
    }
    a {
        color: inherit;
        text-decoration: none;
    }
    li {
        list-style: none;
    }
    button, a {
        background-color: inherit;
        border: none;
        cursor: pointer;

        &:focus {
            outline: none;
        }
    }
    button {
        padding: 0;
    }
`;
