import * as s from "./style";

import SummarySection from "./summarysection/SummarySection";
import StreakSection from "./streaksection/StreakSection";

export default function MainPage() {
    return (
        <s.Main>
            <SummarySection />
            <StreakSection />
        </s.Main>
    );
}
