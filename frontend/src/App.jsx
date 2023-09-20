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
<<<<<<< HEAD
import SigninPage from "pages/userpage/signinpage/SigninPage";
import SignupPage from "pages/userpage/signuppage/SignupPage";
import Layout from "components/common/layout/Layout";
import Intro from "pages/intropage/Intro";
import MyPage from "pages/userpage/mypage/MyPage";
=======
import SigninPage from 'pages/userpage/signinpage/SigninPage';
import SignupPage from 'pages/userpage/signuppage/SignupPage';
import ResetpwPage from 'pages/userpage/resetpwpage/ResetpwPage';
import Layout from 'components/common/layout/Layout';
import Greeting from 'pages/intropage/greeting/Greeting';
import Intro from 'pages/intropage/Intro';
>>>>>>> 39c5709 (Feat : 회원가입 페이지 및 비밀번호 찾기 페이지 구현)

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
<<<<<<< HEAD
                                <Route path="/mypage" element={<MyPage />} />
=======
                                <Route path="/resetpw" element={<ResetpwPage />} />
>>>>>>> 39c5709 (Feat : 회원가입 페이지 및 비밀번호 찾기 페이지 구현)
                            </Route>
                            {/* main page */}
                        </Routes>
                    </BrowserRouter>
                </ThemeProvider>
            </PersistGate>
        </Provider>
    );
}
