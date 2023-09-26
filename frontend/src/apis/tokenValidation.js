import { refresh } from "apis/user";
import { updateRefresh } from "redux/slice/userSlice";

export default function tokenValidation(call, values, dispatch) {
    return call(values).catch(() => {
        if (values.user.refreshToken) {
            return refresh(values.user)
                .then((res) => {
                    dispatch(
                        updateRefresh({
                            accessToken: res.accessToken
                        })
                    );

                    return call(values).then((res) => {
                        return res;
                    });
                })
                .catch(() => {
                    throw new Error("토큰이 만료되었습니다. 다시 로그인해주세요.");
                });
        }

        throw new Error("토큰이 없습니다. 다시 로그인해주세요.");
    });
}
