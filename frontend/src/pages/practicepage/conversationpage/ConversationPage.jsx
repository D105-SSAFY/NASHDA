import * as s from "./style";

import ConversationSection from "./conversationsection/ConversationSection";

export default function ConversationPage() {
    return (
        <s.Main>
            <s.Shadow></s.Shadow>
            <ConversationSection />
        </s.Main>
    );
}
