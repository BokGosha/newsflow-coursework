import AuthService from "./AuthService";
import axios from "axios";

const getNews = () => {
    let config = {
        method: "get",
        maxBody: Infinity,
        url: "/news",
        headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${AuthService.getToken()}`,
        },
    };

    return axios.request(config).then((r) => {
        return r.status === 200 ? r.data : [];
    });
};

const getNewsById = (id) => {
    let config = {
        method: "get",
        maxBody: Infinity,
        url: `/news/${id}`,
        headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${AuthService.getToken()}`,
        },
    };

    return axios.request(config).then((r) => {
        return r.status === 200 ? r.data : null;
    });
};

const addNews = async (formData) => {
    const r = await axios.post("/news", formData, {
        headers: {
            "Content-Type": "multipart/form-data",
        },
        Authorization: `Bearer ${AuthService.getToken()}`,
    });

    return r.data;
};

const removeNews = async (id) => {
    const r = await axios.delete(`/news/${id}`, {
        headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${AuthService.getToken()}`,
        },
    });
    
    return r.data;
};

const NewsService = {
    getNews,
    getNewsById,
    addNews,
    removeNews,
};

export default NewsService;
