import { useCallback, useEffect, useRef, useState } from "react";
import AudioVisualiser from "./AudioVisualiser";

export default function SoundWave({ props: { start } }) {
    const [audio, setAudio] = useState(null);
    const [audioData, setAudioData] = useState(new Uint8Array(0));
    const audioContext = useRef(null);
    const analyser = useRef(null);
    const dataArray = useRef(null);
    const source = useRef(null);
    const rafId = useRef(null);

    const getMic = useCallback(async () => {
        const audio = await navigator.mediaDevices.getUserMedia({
            audio: true,
            video: false
        });

        setAudio(audio);
    }, []);

    const stopMic = useCallback(() => {
        if (!audio) {
            return;
        }

        audio.getTracks().forEach((track) => track.stop());
        setAudio(null);
    }, [audio]);

    const tick = useCallback(() => {
        analyser.current.getByteTimeDomainData(dataArray.current);
        setAudioData(dataArray.current);
        rafId.current = requestAnimationFrame(tick);
    }, [setAudioData]);

    useEffect(() => {
        if (start) {
            getMic();
        } else {
            stopMic();
        }

        return () => {
            stopMic();
        };
    }, [start]);

    useEffect(() => {
        if (!audio) {
            return;
        }

        if (!start) {
            cancelAnimationFrame(rafId.current);
            analyser.current.disconnect();
            source.current.disconnect();

            return;
        }

        audioContext.current = new (window.AudioContext || window.webkitAudioContext)();
        analyser.current = audioContext.current.createAnalyser();
        dataArray.current = new Uint8Array(analyser.current.frequencyBinCount);
        source.current = audioContext.current.createMediaStreamSource(audio);
        source.current.connect(analyser.current);
        rafId.current = requestAnimationFrame(tick);

        return () => {
            cancelAnimationFrame(rafId.current);
            analyser.current.disconnect();
            source.current.disconnect();
        };
    }, [audio]);

    return <AudioVisualiser props={{ audioData }} />;
}
