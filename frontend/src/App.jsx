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
import MainPage from "pages/mainpage/MainPage";

import ConversationPage from "pages/practicepage/conversationpage/ConversationPage";
import ProblemPage from "pages/practicepage/problempage/ProblemPage";
import StretchingPage from "pages/practicepage/stretchingpage/StretchingPage";

import DramaPlayPage from "pages/gamepage/dramaplaypage/DramaPlayPage";
import SpeedQuizPage from "pages/gamepage/speedquizpage/SpeedQuizPage";

import TestPage from "pages/testpage/TestPage";

export default function App() {
    return (
        <Provider store={store}>
            <PersistGate loading={null} persistor={persistor}>
                <ThemeProvider theme={theme}>
                    <GlobalStyle />
                    <BrowserRouter forceRefresh={true}>
                        <Routes>
                            <Route path="/" element={<Layout />}>
                                <Route path="/" element={<PrivateRoute isPrivate={false} />}>
                                    <Route index element={<Intro />} />
                                </Route>
                                {/* <Route path="/greeting" element={<Intro />} /> */}

                                {/* user pages */}
                                <Route path="/signin" element={<PrivateRoute isPrivate={false} />}>
                                    <Route index element={<SigninPage />} />
                                </Route>
                                <Route path="/signup" element={<PrivateRoute isPrivate={false} />}>
                                    <Route index element={<SignupPage />} />
                                </Route>
                                <Route path="/resetpw" element={<PrivateRoute isPrivate={false} />}>
                                    <Route index element={<ResetpwPage />} />
                                </Route>

                                {/* private pages */}
                                <Route path="/mypage" element={<PrivateRoute />}>
                                    <Route index element={<MyPage />} />
                                </Route>
                                <Route path="/main" element={<PrivateRoute />}>
                                    <Route index element={<MainPage />} />
                                </Route>
                                <Route path="/practice" element={<PrivateRoute />}>
                                    <Route index element={<ProblemPage />} />
                                </Route>
                                <Route path="/simulation" element={<PrivateRoute />}>
                                    <Route index element={<ConversationPage />} />
                                </Route>
                                <Route path="/dramaplay" element={<PrivateRoute />}>
                                    <Route index element={<DramaPlayPage />} />
                                </Route>
                                <Route path="/speedquiz" element={<PrivateRoute />}>
                                    <Route index element={<SpeedQuizPage />} />
                                </Route>
                                <Route path="/weeklytest" element={<PrivateRoute />}>
                                    <Route index element={<TestPage />} />
                                </Route>

                                <Route path="/1" element={<StretchingPage />} />
                            </Route>
                        </Routes>
                    </BrowserRouter>
                </ThemeProvider>
            </PersistGate>
        </Provider>
    );
}
