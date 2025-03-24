import axios from "axios";
import AuthService from "./AuthService";
import NewsService from "./NewsService";

const addLike = async (newsId) => {
    const response = await axios.put(`/news/${newsId}/likes`, null, {
        headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${AuthService.getToken()}`,
        },
    });

    return response.status === 200
        ? await NewsService.getNewsById(newsId)
        : null;
};

const removeLike = async (newsId) => {
    await axios.delete(`/news/${newsId}/likes`, {
        headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${AuthService.getToken()}`,
        },
    });

    return await NewsService.getNewsById(newsId);
};

const LikeService = {
    addLike,
    removeLike,
};

export default LikeService;
