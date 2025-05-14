import axios from "axios";
import React, { useEffect, useState } from "react";
import "./App.css";
import Approuter from "./components/Approuter";
import MyHeader from "./components/Header";
import { AuthContext } from "./context";
import AuthService from "./services/AuthService";

axios.defaults.baseURL = "https://newsflow-coursework.onrender.com:8080/";

function App() {
    const [isAuth, setIsAuth] = useState(AuthService.getToken !== null);

    useEffect(() => {
        const token = AuthService.getToken();
        
        if (token) {
            axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;

            setIsAuth(true);
        } else {
            setIsAuth(false);
        }
    }, [isAuth]);

    return (
        <AuthContext.Provider value={{ isAuth, setIsAuth }}>
            <MyHeader />
            <Approuter />
        </AuthContext.Provider>
    );
}

export default App;
