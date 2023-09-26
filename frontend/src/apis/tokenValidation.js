import { valid, refresh } from "apis/user";
import { updateRefresh } from "redux/slice/userSlice";

export default function tokenValidation(user, setIsValid, dispatch) {
    valid(user)
        .then(() => {
            setIsValid(true);
        })
        .catch(() => {
            if (user.refreshToken) {
                refresh(user)
                    .then((res) => {
                        setIsValid(true);

                        dispatch(
                            updateRefresh({
                                accessToken: res.accessToken
                            })
                        );
                    })
                    .catch(() => {
                        setIsValid(false);
                    });
            } else setIsValid(false);
        });
}
