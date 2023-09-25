// // Router
// import { BrowserRouter, Route, Routes } from "react-router-dom";

// // Store
// import { PersistGate } from "redux-persist/integration/react";
// import { Provider } from "react-redux";
// import store, { persistor } from "redux/store/store.js";

// // Style
// import { ThemeProvider } from "styled-components";
// import { theme } from "style/Theme";
// import { GlobalStyle } from "style/GlobalStyle";

// // Pages
// // import MainPage from 'pages/mainpage/MainPage';
// import SigninPage from "pages/userpage/signinpage/SigninPage";
// import SignupPage from "pages/userpage/signuppage/SignupPage";
// import ResetpwPage from "pages/userpage/resetpwpage/ResetpwPage";
// import Layout from "components/common/layout/Layout";
// import Intro from "pages/intropage/Intro";
// import MyPage from "pages/userpage/mypage/MyPage";
// import SentenceTestPage from "pages/testpage/sentencepage/SentenceTestPage";
// import MainPage from "pages/mainpage/MainPage";

// export default function App() {
//     return (
//         <Provider store={store}>
//             <PersistGate loading={null} persistor={persistor}>
//                 <ThemeProvider theme={theme}>
//                     <GlobalStyle />
//                     <BrowserRouter forceRefresh={true}>
//                         <Routes>
//                             <Route path="/" element={<Layout />}>
//                                 <Route path="/" element={<Intro />} />

//                                 {/* user pages */}
//                                 <Route path="/signin" element={<SigninPage />} />
//                                 <Route path="/signup" element={<SignupPage />} />
//                                 <Route path="/mypage" element={<MyPage />} />
//                                 <Route path="/resetpw" element={<ResetpwPage />} />
//                                 <Route path="/main" element={<MainPage />} />
//                                 <Route path="/sentencetest" element={<SentenceTestPage />} />
//                             </Route>
//                             {/* main page */}
//                         </Routes>
//                     </BrowserRouter>
//                 </ThemeProvider>
//             </PersistGate>
//         </Provider>
//     );
// }

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
import Intro from "pages/intropage/Intro";
import MyPage from "pages/userpage/mypage/MyPage";
import SentenceTestPage from "pages/testpage/sentencepage/SentenceTestPage";
import MainPage from "pages/mainpage/MainPage";

import ConversationPage from "pages/practicepage/conversationpage/ConversationPage";
import ProblemPage from "pages/practicepage/problempage/ProblemPage";
import StretchingPage from "pages/practicepage/stretchingpage/StretchingPage";

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
                                <Route path="/mypage" element={<MyPage />} />
                                <Route path="/resetpw" element={<ResetpwPage />} />
                                <Route path="/main" element={<MainPage />} />
                                <Route path="/sentencetest" element={<SentenceTestPage />} />

                                <Route path="/1" element={<ProblemPage />} />
                                <Route path="/2" element={<ConversationPage />} />
                                <Route path="/3" element={<StretchingPage />} />
                            </Route>
                            {/* main page */}
                        </Routes>
                    </BrowserRouter>
                </ThemeProvider>
            </PersistGate>
        </Provider>
    );
}
