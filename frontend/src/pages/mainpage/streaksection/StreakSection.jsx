import * as s from "./style";

import Calendar from "./calendar/Calendar";

const values = {
    "2023-08-23": 1,
    "2023-08-26": 2,
    "2023-08-27": 3,
    "2023-09-18": 4,
    "2023-09-19": 4,
};
const until = "2023-09-21";

export default function StreakSection() {
    return (
        <s.Section>
            <s.Wrapper>
                <s.Header>
                    <h2>1년 사이 달성도</h2>
                </s.Header>
                <Calendar props={{ values, until }} />
            </s.Wrapper>
        </s.Section>
    );
}
