import { useState, useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import { Navigate, Outlet } from "react-router-dom";
import tokenValidation from "apis/tokenValidation";
import { valid } from "apis/user";

export default function PrivateRoute() {
    const dispatch = useDispatch();
    const user = useSelector((state) => state.user);
    const [isValid, setIsValid] = useState(null);

    useEffect(() => {
        const values = {};

        values.user = user;
        console.log("checkout");

        tokenValidation(valid, values, dispatch)
            .then(() => {
                return setIsValid(true);
            })
            .catch(() => {
                return setIsValid(false);
            });
    }, []);

    if (isValid === null) return null;

    return isValid ? <Outlet /> : <Navigate to="/signin" />;
}
