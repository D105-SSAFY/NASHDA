import { createGlobalStyle } from "styled-components";
import { reset } from "styled-reset";

export const GlobalStyle = createGlobalStyle`
    ${reset}

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

    a {
        text-decoration: none;
    }
`;
