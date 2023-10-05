import { useState } from "react";
import { useNavigate } from "react-router";

import * as s from "./style";

import ConversationSection from "./conversationsection/ConversationSection";

import ErrorModal from "components/modals/errormodal/ErrorModal";

export default function ConversationPage() {
    const [error, setError] = useState(false);
    const navigate = useNavigate();

    return (
        <s.Main>
            <ConversationSection props={{ setError }} />
            <ErrorModal
                props={{
                    title: "에러 발생",
                    content: "서버에 에러가 발생했습니다. 잠시 후 다시 시도해주세요.",
                    display: error,
                    callback() {
                        navigate("/main");
                    }
                }}
            />
        </s.Main>
    );
}
