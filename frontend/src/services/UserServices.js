const url = "";
const header = { "Content-Type": "appliction/json" };
export async function Signin(id, password) {
    try {
        const response = await fetch(`${url}/login`, {
            method: "POST",
            headers: header,
            body: JSON.stringify({
                email: id,
                password
            })
        });
        const result = await response.json();
        if (result.status === 200) {
            // eslint-disable-next-line no-alert
            alert(result.message);
            return result.data;
        }
    } catch (error) {
        console.log(error);
        if (error.status === 4001) {
            // eslint-disable-next-line no-alert
            alert(error.message);
            return;
        }

        if (error.status === 4002) {
            // eslint-disable-next-line no-alert
            alert(error.message);
        }
    }
}
