import axios from "axios";
import AuthService from "./AuthService";

const getUser = () => {
    let config = {
        method: "get",
        maxBody: Infinity,
        url: "/user",
        headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${AuthService.getToken()}`,
        },
    };

    return axios.request(config).then((r) => {
        return r.status === 200 ? r.data : null;
    });
};

const logout = () => {
    localStorage.removeItem("user");
};

const UserService = {
    getUser,
    logout,
};

export default UserService;
