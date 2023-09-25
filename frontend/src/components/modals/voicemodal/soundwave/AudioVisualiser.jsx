import { useEffect, useRef } from "react";

export default function AudioVisualiser({ props: { audioData } }) {
    const canvasRef = useRef(null);

    useEffect(() => {
        const { height, width } = canvasRef.current;

        const context = canvasRef.current.getContext("2d");
        const sliceWidth = Number(width) / audioData.length;
        let x = 0;

        context.lineWidth = 2;
        context.strokeStyle = "rgb(174, 126, 242)";
        context.clearRect(0, 0, width, height);
        context.beginPath();
        context.moveTo(0, height / 2);

        for (const item of audioData) {
            const y = (item / 255.0) * height;
            context.lineTo(x, y);
            x += sliceWidth;
        }

        context.lineTo(x, height / 2);
        context.stroke();
    }, [audioData]);

    return <canvas width="520" height="200" ref={canvasRef} />;
}
