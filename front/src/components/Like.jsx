import React, { useState } from "react";
import LikeService from "../services/LikeService";
import NewsService from "../services/NewsService";

const Like = ({ newsId }) => {
    const [like, setLike] = useState();
    const [likes, setLikes] = useState(0);

    const fetchPost = async () => {
        const news = await NewsService.getNewsById(newsId);

        setLikes(news.likeCount);

        setLike(news.liked ? "red" : "white");
    };

    fetchPost();

    const toggleLike = async () => {
        if (like === "white") {
            await LikeService.addLike(newsId);

            setLikes(likes - 1);

            setLike("red");
        } else {
            await LikeService.removeLike(newsId);

            setLikes(likes + 1);

            setLike("white");
        }
    };

    const style = {
        backgroundColor: like,
    };

    return (
        <div className="like-container">
            <button onClick={toggleLike} style={style} className="like-button">
                <img
                    src="../images/heart1.png"
                    alt="Like"
                    className="like-img"
                />
            </button>

            <p className="like-p">{likes}</p>
        </div>
    );
};

export default Like;
