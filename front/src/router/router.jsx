import Home from "../pages/Home";
import Login from "../pages/Login";
import News from "../pages/News";
import Register from "../pages/Register";

export const privateRoutes = [
    { path: "/news", element: News },
    { path: "/home", element: Home },
];

export const publicRoutes = [
    { path: "/home", element: Home },
    { path: "/auth/login", element: Login },
    { path: "/auth/register", element: Register },
];
