import { useEffect, useState } from "react";

import * as s from "./style";

// Import SetWordSection from "./setwordsection/SetWordSection";
import SetImageSection from "./setimagesection/SetImageSection";

export default function SpeedQuizPage() {
    const [problem, setProblem] = useState({});

    useEffect(() => {
        // SetProblem({
        //     type: 1,
        //     index: 2,
        //     img: "https://media.istockphoto.com/id/184276818/ko/%EC%82%AC%EC%A7%84/%EB%A0%88%EB%93%9C-%EC%82%AC%EA%B3%BC%EB%82%98%EB%AC%B4.jpg?s=612x612&w=0&k=20&c=qe0XwDHYbQFgVaqM2unXZWVqI7kV2SSfXrCYaHsdmWM=",
        //     answer: "사과"
        // });

        setProblem({
            type: 1,
            index: 2,
            img: [
                "https://media.istockphoto.com/id/184276818/ko/%EC%82%AC%EC%A7%84/%EB%A0%88%EB%93%9C-%EC%82%AC%EA%B3%BC%EB%82%98%EB%AC%B4.jpg?s=612x612&w=0&k=20&c=qe0XwDHYbQFgVaqM2unXZWVqI7kV2SSfXrCYaHsdmWM=",
                "https://media.istockphoto.com/id/184276818/ko/%EC%82%AC%EC%A7%84/%EB%A0%88%EB%93%9C-%EC%82%AC%EA%B3%BC%EB%82%98%EB%AC%B4.jpg?s=612x612&w=0&k=20&c=qe0XwDHYbQFgVaqM2unXZWVqI7kV2SSfXrCYaHsdmWM=",
                "https://media.istockphoto.com/id/184276818/ko/%EC%82%AC%EC%A7%84/%EB%A0%88%EB%93%9C-%EC%82%AC%EA%B3%BC%EB%82%98%EB%AC%B4.jpg?s=612x612&w=0&k=20&c=qe0XwDHYbQFgVaqM2unXZWVqI7kV2SSfXrCYaHsdmWM=",
                "https://media.istockphoto.com/id/184276818/ko/%EC%82%AC%EC%A7%84/%EB%A0%88%EB%93%9C-%EC%82%AC%EA%B3%BC%EB%82%98%EB%AC%B4.jpg?s=612x612&w=0&k=20&c=qe0XwDHYbQFgVaqM2unXZWVqI7kV2SSfXrCYaHsdmWM="
            ],
            answer: "사과"
        });
    }, []);

    return (
        <s.Main>
            {/* <SetWordSection props={{ problem }} /> */}
            <SetImageSection props={{ problem }} />
        </s.Main>
    );
}
