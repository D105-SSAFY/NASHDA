import { useState, useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import { Navigate, Outlet } from "react-router-dom";
import eetch from "apis/eetch";

export default function PrivateRoute({ isPrivate = true }) {
    const dispatch = useDispatch();
    const user = useSelector((state) => state.user);
    const [isValid, setIsValid] = useState(null);

    useEffect(() => {
        eetch
            .tokenValidation(eetch.valid, { user }, dispatch)
            .then(() => {
                setIsValid(true);
            })
            .catch(() => {
                setIsValid(false);
            });
    }, [dispatch, user]);

    if (isValid === null) return null;

    if (isPrivate) return isValid ? <Outlet /> : <Navigate to="/signin" />;
    return isValid ? <Navigate to="/main" /> : <Outlet />;
}
