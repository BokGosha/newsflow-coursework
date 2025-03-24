import React, { useContext } from "react";
import { Navigate, Route, Routes } from "react-router-dom";
import { privateRoutes, publicRoutes } from "../router/router";
import { AuthContext } from "../context";

const AppRouter = () => {
    const { isAuth } = useContext(AuthContext);

    return isAuth ? (
        <Routes>
            {privateRoutes.map((route) => (
                <Route
                    path={route.path}
                    element={<route.element />}
                    key={route.path}
                />
            ))}
            <Route path="*" element={<Navigate to="/news" replace />} />
        </Routes>
    ) : (
        <Routes>
            {publicRoutes.map((route) => (
                <Route
                    path={route.path}
                    element={<route.element />}
                    key={route.path}
                />
            ))}
            <Route path="*" element={<Navigate to="/home" replace />} />
        </Routes>
    );
};

export default AppRouter;
