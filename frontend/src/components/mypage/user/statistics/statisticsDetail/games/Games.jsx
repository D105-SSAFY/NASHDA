import { useState, useEffect } from "react";
import { useSelector } from "react-redux";

import eetch from "apis/eetch";

export default function Games({ setDatas }) {
    const user = useSelector((state) => state.user);
    const [games, setGames] = useState([]);

    useEffect(() => {
        eetch.games({ user }).then((res) => {
            console.log("games", res.data);
            setGames(res.data);
        });
    }, []);

    useEffect(() => {
        if (games.length !== 0) {
            setDatas(2, true);
        }
    }, [games]);

    return <div>Games</div>;
}
