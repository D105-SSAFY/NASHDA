// Router
import { BrowserRouter, Route, Routes } from "react-router-dom";

// Store
import { PersistGate } from "redux-persist/integration/react";
import { Provider } from "react-redux";
import store, { persistor } from 'redux/store/store.js';

// Style
import { ThemeProvider } from "styled-components";
import { theme } from "style/Theme";
import { GlobalStyle } from "style/GlobalStyle";

// Pages
import MainPage from "pages/mainpage/MainPage";
import SigninPage from "pages/userpage/signinpage/SigninPage";
import SignupPage from "pages/userpage/signuppage/SignupPage";

export default function App() {
    return (
        <Provider store={store}>
            <PersistGate loading={null} persistor={persistor}>
                <ThemeProvider theme={theme}>
                    <GlobalStyle />
                    <BrowserRouter forceRefresh={true}>
                        <Routes>
                            {/* main page */}
                            <Route path="/" element={<MainPage />} />

                            {/* user pages */}
                            <Route path="/signin" element={<SigninPage />} />
                            <Route path="/signup" element={<SignupPage />} />
                        </Routes>
                    </BrowserRouter>
                </ThemeProvider>
            </PersistGate>
        </Provider>
    );
}