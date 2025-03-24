import axios from "axios";
import AuthService from "./AuthService";

const addComment = async (newsId, commentData) => {
    const r = await axios.post(`/news/${newsId}/comments`, commentData, {
        headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${AuthService.getToken()}`,
        },
    });

    return r.data;
};

const CommentService = {
    addComment,
};

export default CommentService;
