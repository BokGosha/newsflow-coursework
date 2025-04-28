import React, { useContext, useState } from "react";
import { useNavigate } from "react-router-dom";
import { AuthContext } from "../context";
import AuthService from "../services/AuthService";

const Register = () => {
    const [userEmail, setUserEmail] = useState("");
    const [userPassword, setUserPassword] = useState("");
    const [userPasswordConfirm, setUserPasswordConfirm] = useState("");
    const { setIsAuth } = useContext(AuthContext);
    const router = useNavigate();

    const register = (e) => {
        e.preventDefault();

        if (userPassword !== userPasswordConfirm) {
            alert("Введенные пароли не совпадают!");

            setUserPasswordConfirm("");

            return;
        }

        handleRegister(e);
    };

    const handleRegister = async (e) => {
        try {
            const response = await AuthService.register(
                userEmail,
                userPassword
            );

            if (response.status === 200) {
                if (response.data.token === null) {
                    alert("Такой пользователь уже зарегистрирован!");
                } else {
                    router("/auth/login");
                }
            } else {
                setIsAuth(false);

                alert("Ошибка регистрации!");
            }
        } catch (error) {
            alert("Ошибка регистрации!");
        }
    };

    return (
        <div className="auth-form">
            <h1>Регистрация</h1>

            <form onSubmit={register}>
                <input
                    type="email"
                    placeholder={"email@example.com"}
                    value={userEmail}
                    onChange={(e) => setUserEmail(e.target.value)}
                />

                <input
                    type="password"
                    placeholder={"Пароль"}
                    value={userPassword}
                    onChange={(e) => setUserPassword(e.target.value)}
                />

                <input
                    type="password"
                    placeholder={"Подтверждение пароля"}
                    value={userPasswordConfirm}
                    onChange={(e) => setUserPasswordConfirm(e.target.value)}
                />

                <button type={"submit"}>Зарегистрироваться</button>
            </form>
        </div>
    );
};

export default Register;
