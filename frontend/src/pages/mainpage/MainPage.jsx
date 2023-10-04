import { Link } from "react-router-dom";

import * as s from "./style";

import SummarySection from "./summarysection/SummarySection";
import StreakSection from "./streaksection/StreakSection";

import CalendarTodayIcon from "@mui/icons-material/CalendarToday";
import ContactsIcon from "@mui/icons-material/Contacts";
import SpeedIcon from "@mui/icons-material/Speed";
import MicIcon from "@mui/icons-material/Mic";
import ConnectWithoutContactIcon from "@mui/icons-material/ConnectWithoutContact";
import TagFacesIcon from "@mui/icons-material/TagFaces";

export default function MainPage() {
    return (
        <s.Main>
            <SummarySection />
            <StreakSection />
            <s.Section>
                <s.SectionHeader>
                    <h2>시험</h2>
                </s.SectionHeader>
                <s.SectionList>
                    <li>
                        <Link to="/weeklytest">
                            <s.Article color="#B1B8E8">
                                <CalendarTodayIcon />
                                <s.ArticleHeader>
                                    <h3>주간 시험</h3>
                                    <p>한 주의 훈련 결과를 기록하기 위해주간 시험에 응시하세요!</p>
                                </s.ArticleHeader>
                            </s.Article>
                        </Link>
                    </li>
                </s.SectionList>
            </s.Section>
            <s.Section>
                <s.SectionHeader>
                    <h2>간편</h2>
                </s.SectionHeader>
                <s.SectionList>
                    <li>
                        <Link to="/dramaplay">
                            <s.Article color="#FDE4AD">
                                <ContactsIcon />
                                <s.ArticleHeader>
                                    <h3>드라마 플레이</h3>
                                    <p>그림을 보고 상황을 유추해 빈칸을 채워보세요.</p>
                                </s.ArticleHeader>
                            </s.Article>
                        </Link>
                    </li>
                    <li>
                        <Link to="/speedquiz">
                            <s.Article color="#FF9DA5">
                                <SpeedIcon />
                                <s.ArticleHeader>
                                    <h3>스피드 퀴즈</h3>
                                    <p>빠르게 넘어가는 사진 속 사물을 맞춰보세요.</p>
                                </s.ArticleHeader>
                            </s.Article>
                        </Link>
                    </li>
                </s.SectionList>
            </s.Section>
            <s.Section>
                <s.SectionHeader>
                    <h2>연습</h2>
                </s.SectionHeader>
                <s.SectionList>
                    <li>
                        <Link to="/practice">
                            <s.Article color="#8CBCCF">
                                <MicIcon />
                                <s.ArticleHeader>
                                    <h3>발음 연습</h3>
                                    <p>주어진 단어나 문장을 읽고 발음을 연습해보세요.</p>
                                </s.ArticleHeader>
                            </s.Article>
                        </Link>
                    </li>
                    <li>
                        <Link to="/simulation">
                            <s.Article color="#BEE7E3">
                                <ConnectWithoutContactIcon />
                                <s.ArticleHeader>
                                    <h3>대화 시뮬레이션</h3>
                                    <p>실제 상황과 같이 대화를 연습해보세요.</p>
                                </s.ArticleHeader>
                            </s.Article>
                        </Link>
                    </li>
                    <li>
                        <Link to="/1">
                            <s.Article color="#A38FA1">
                                <TagFacesIcon />
                                <s.ArticleHeader>
                                    <h3>스트레칭</h3>
                                    <p>연습 시작 전에 간단한 스트레칭을 해보세요.</p>
                                </s.ArticleHeader>
                            </s.Article>
                        </Link>
                    </li>
                </s.SectionList>
            </s.Section>
        </s.Main>
    );
}
