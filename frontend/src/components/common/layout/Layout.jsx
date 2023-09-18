import React from "react";
import Topbar from "components/common/topbar/Topbar";
import { Outlet } from "react-router";

export default function Layout() {
    return (
        <>
            <Topbar />
            <Outlet />
        </>
    );
}
