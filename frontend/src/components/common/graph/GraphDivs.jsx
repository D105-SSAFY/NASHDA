import { ResponsivePie } from "@nivo/pie";
import { animated } from "react-spring";

// make sure parent container have a defined height when using
// responsive component, otherwise height will be 0 and
// no chart will be rendered.
// website examples showcase many properties,
// you'll often use just a few of them.
export default function GraphDivs({ data /* see data tab */ }) {
    return (
        <ResponsivePie
            data={data}
            margin={{ top: 20, right: 20, bottom: 20, left: 20 }}
            innerRadius={0.6}
            padAngle={0}
            cornerRadius={3}
            activeOuterRadiusOffset={8}
            colors={{ scheme: "pastel1" }}
            borderWidth={1}
            borderColor={{
                from: "color",
                modifiers: [["darker", 0.2]]
            }}
            enableArcLinkLabels={false}
            arcLabel="id"
            arcLabelsSkipAngle={10}
            arcLabelsTextColor={{
                from: "color",
                modifiers: [["darker", 2.7]]
            }}
            arcLabelsComponent={({ label, style }) => (
                <animated.g
                    transform={style.transform}
                    style={{
                        pointerEvents: "none"
                    }}
                >
                    <text
                        textAnchor="middle"
                        dominantBaseline="central"
                        fill={style.textColor}
                        style={{
                            fontSize: 14,
                            fontWeight: 600,
                            color: "#000"
                        }}
                    >
                        {label}
                    </text>
                </animated.g>
            )}
            tooltip={({ datum: { id, value, color } }) => (
                <div
                    style={{
                        padding: 12,
                        color: "#fff",
                        background: "#000000cc",
                        borderRadius: 12,
                        // 내부 span 중앙 정렬
                        display: "flex",
                        justifyContent: "center",
                        alignItems: "center",
                        boxShadow: "0 3px 4px rgba(0, 0, 0, 0.3)"
                    }}
                >
                    <span style={{ fontSize: "2.3rem", marginRight: "6px" }}>{id}</span> <span style={{ fontSize: "1.7rem", color }}>{value}%</span>
                </div>
            )}
            legends={[]}
        />
    );
}
