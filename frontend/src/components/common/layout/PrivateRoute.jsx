import { useState, useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import { Navigate, Outlet } from "react-router-dom";
import tokenValidation from "apis/tokenValidation";

export default function PrivateRoute() {
    const dispatch = useDispatch();
    const user = useSelector((state) => state.user);
    const [isValid, setIsValid] = useState(null);

    useEffect(() => {
        tokenValidation(user, setIsValid, dispatch);
    }, []);

    if (isValid === null) return null;

    return isValid ? <Outlet /> : <Navigate to="/signin" />;
}
