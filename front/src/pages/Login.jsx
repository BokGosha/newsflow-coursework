import React, { useContext, useState } from "react";
import { useNavigate } from "react-router-dom";
import { AuthContext } from "../context";
import AuthService from "../services/AuthService";

const Login = () => {
    const [userEmail, setUserEmail] = useState("");
    const [userPassword, setUserPassword] = useState("");
    const { setIsAuth } = useContext(AuthContext);
    const router = useNavigate();

    const login = async (e) => {
        e.preventDefault();

        try {
            const response = await AuthService.login(userEmail, userPassword);

            if (response.status === 200) {
                if (response.data.token === null) {
                    setIsAuth(false);

                    alert("Авторизация провалена!");
                } else {
                    setIsAuth(true);
                }

                router("/news");
            } else {
                alert("Авторизация провалена!");
            }
        } catch (error) {
            alert("Авторизация провалена!");

            console.error(error);
        }
    };

    return (
        <div className="auth-form">
            <h1>Авторизация</h1>

            <form onSubmit={login}>
                <input
                    type="email"
                    placeholder="email@example.com"
                    value={userEmail}
                    onChange={(e) => setUserEmail(e.target.value)}
                />

                <input
                    type="password"
                    placeholder="Пароль"
                    value={userPassword}
                    onChange={(e) => setUserPassword(e.target.value)}
                />

                <button type="submit">Войти</button>

                <button
                    className="register-btn"
                    onClick={() => router("/auth/register")}
                >
                    Перейти к регистрации
                </button>
            </form>
        </div>
    );
};

export default Login;
