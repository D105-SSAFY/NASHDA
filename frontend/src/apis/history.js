import eetch from "apis/eetch";

export const continuous = async ({ user }) => {
    try {
        const url = `${process.env.REACT_APP_API_URL}/history/continuous`;
        const options = {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${user.accessToken}`
            },
            credentials: "include"
        };

        return await eetch(url, options);
    } catch (err) {
        console.error(err);
    }
};
