import { useState } from "react";

const wait = (ms) => {
    const start = Date.now();
    let now = start;

    while (now - start < ms) {
        now = Date.now();
    }
};

// 녹음 중, 재생 가능 여부 전달
// 녹음 시작, 녹음 중지, 재생 함수 반환
export default function voice() {
    const [stream, setStream] = useState();
    const [media, setMedia] = useState();
    const [source, setSource] = useState();
    const [analyser, setAnalyser] = useState();
    const [audioUrl, setAudioUrl] = useState();

    const onRecAudio = () => {
        setMedia();
        setSource();
        setAnalyser();
        setAudioUrl();
        setStream();

        // 음원정보를 담은 노드를 생성하거나 음원을 실행또는 디코딩 시키는 일을 한다
        const audioCtx = new (window.AudioContext || window.webkitAudioContext)();
        // 자바스크립트를 통해 음원의 진행상태에 직접접근에 사용된다.
        const analyser = audioCtx.createScriptProcessor(0, 1, 1);

        setAnalyser(analyser);

        function makeSound(stream) {
            // 내 컴퓨터의 마이크나 다른 소스를 통해 발생한 오디오 스트림의 정보를 보여준다.
            const source = audioCtx.createMediaStreamSource(stream);

            setSource(source);

            source.connect(analyser);
            analyser.connect(audioCtx.destination);
        }

        // 마이크 사용 권한 획득
        navigator.mediaDevices.getUserMedia({ audio: true }).then((stream) => {
            const mediaRecorder = new MediaRecorder(stream);

            mediaRecorder.start();

            setStream(stream);
            setMedia(mediaRecorder);
            makeSound(stream);

            analyser.onaudioprocess = function (e) {
                // 1분(60초) 지나면 자동으로 음성 저장 및 녹음 중지
                if (e.playbackTime > 60) {
                    stream.getAudioTracks().forEach(function (track) {
                        track.stop();
                    });

                    mediaRecorder.stop();
                    // 메서드가 호출 된 노드 연결 해제
                    analyser.disconnect();
                    audioCtx.createMediaStreamSource(stream).disconnect();

                    mediaRecorder.ondataavailable = function (e) {
                        setAudioUrl(e.data);
                        // SetOnRec(true);
                    };
                } else {
                    // SetOnRec(false);
                }
            };
        });
    };

    // 사용자가 음성 녹음을 중지 했을 때
    const offRecAudio = () => {
        // Dataavailable 이벤트로 Blob 데이터에 대한 응답을 받을 수 있음
        media.ondataavailable = function (e) {
            setAudioUrl(e.data);
            // SetOnRec(true);
        };

        // 모든 트랙에서 stop()을 호출해 오디오 스트림을 정지
        stream.getAudioTracks().forEach(function (track) {
            track.stop();
        });

        // 미디어 캡처 중지
        media.stop();

        // 메서드가 호출 된 노드 연결 해제
        analyser.disconnect();
        source.disconnect();

        // If (audioUrl) {
        //     const url = URL.createObjectURL(audioUrl); // 출력된 링크에서 녹음된 오디오 확인 가능

        //     console.log(url);
        // }
    };

    const createFile = () => {
        wait(100);

        if (!audioUrl) {
            return false;
        }

        // File 생성자를 사용해 파일로 변환
        const sound = new File([audioUrl], "soundBlob.wav", {
            lastModified: new Date().getTime(),
            type: "audio/wav"
        });

        return sound;
    };

    const play = () => {
        if (!audioUrl) {
            return false;
        }

        const audio = new Audio(URL.createObjectURL(audioUrl));

        audio.loop = false;
        audio.volume = 1;

        audio.play();
    };

    return {
        onRecAudio,
        offRecAudio,
        createFile,
        play
    };
}
