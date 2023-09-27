import { useState, useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import { Navigate, Outlet } from "react-router-dom";
import eetch from "apis/eetch";

export default function PrivateRoute() {
    const dispatch = useDispatch();
    const user = useSelector((state) => state.user);
    const [isValid, setIsValid] = useState(null);

    useEffect(() => {
        const values = {};

        values.user = user;

        eetch
            .tokenValidation(eetch.valid, values, dispatch)
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
