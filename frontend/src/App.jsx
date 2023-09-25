// Router
import { BrowserRouter, Route, Routes } from "react-router-dom";

// Store
import { PersistGate } from "redux-persist/integration/react";
import { Provider } from "react-redux";
import store, { persistor } from "redux/store/store.js";

// Style
import { ThemeProvider } from "styled-components";
import { theme } from "style/Theme";
import { GlobalStyle } from "style/GlobalStyle";

// Pages
// import MainPage from 'pages/mainpage/MainPage';
import SigninPage from "pages/userpage/signinpage/SigninPage";
import SignupPage from "pages/userpage/signuppage/SignupPage";
import ResetpwPage from "pages/userpage/resetpwpage/ResetpwPage";
import Layout from "components/common/layout/Layout";
import PrivateRoute from "components/common/layout/PrivateRoute";
import Intro from "pages/intropage/Intro";
import MyPage from "pages/userpage/mypage/MyPage";
import SentenceTestPage from "pages/testpage/sentencepage/SentenceTestPage";
import MainPage from "pages/mainpage/MainPage";

export default function App() {
    return (
        <Provider store={store}>
            <PersistGate loading={null} persistor={persistor}>
                <ThemeProvider theme={theme}>
                    <GlobalStyle />
                    <BrowserRouter forceRefresh={true}>
                        <Routes>
                            <Route path="/" element={<Layout />}>
                                <Route path="/" element={<Intro />} />

                                {/* user pages */}
                                <Route path="/signin" element={<SigninPage />} />
                                <Route path="/signup" element={<SignupPage />} />
                                <Route path="/resetpw" element={<ResetpwPage />} />
                                <Route path="/mypage" element={<PrivateRoute />}>
                                    <Route index element={<MyPage />} />
                                </Route>
                                <Route path="/main" element={<PrivateRoute />}>
                                    <Route index element={<MainPage />} />
                                </Route>
                                <Route path="/sentencetest" element={<SentenceTestPage />} />
                            </Route>
                            {/* main page */}
                        </Routes>
                    </BrowserRouter>
                </ThemeProvider>
            </PersistGate>
        </Provider>
    );
}
