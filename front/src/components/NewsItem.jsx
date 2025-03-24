import React, { useEffect, useState } from "react";
import CommentService from "../services/CommentService";
import NewsService from "../services/NewsService";
import MyModal from "../UI/modal/MyModal";
import Comments from "./Comments";
import Like from "./Like";

const NewsItem = (props) => {
    const [modal, setModal] = useState(false);
    const [like, setLike] = useState();
    const [likes, setLikes] = useState(0);
    const [news, setNews] = useState(props.news);
    const [comments, setComments] = useState([]);

    const fetchNewsDetails = async () => {
        if (modal) {
            const fetchedNews = await NewsService.getNewsById(props.news.id);

            setComments(fetchedNews.comments);

            setNews(fetchedNews);
        }
    };

    useEffect(() => {
        fetchNewsDetails();
    }, [modal, props.news.id]);

    const handleCreateComment = async (commentData) => {
        const comment = await CommentService.addComment(
            props.news.id,
            commentData
        );

        setComments((comments) => [...comments, comment]);
    };

    const handleDeleteNews = async () => {
        const deleted = await NewsService.removeNews(props.news.id);

        props.deleteNews(deleted);
    };

    return (
        <div className="news-container">
            <div className="news-content">
                <img src={news.image} alt="Изображение" />

                <strong>{news.title}</strong>

                <div>{news.content}</div>
            </div>

            <div className="news-footer">
                <Like
                    like={like}
                    setLike={setLike}
                    newsId={props.news.id}
                    likes={likes}
                    setLikes={setLikes}
                />

                {props.user.role === "ADMIN" && (
                    <button
                        className="footer-button-admin"
                        onClick={handleDeleteNews}
                    >
                        Удалить
                    </button>
                )}

                <button
                    onClick={() => {
                        setModal(true);
                    }}
                    className="footer-button"
                >
                    Подробнее
                </button>
                <MyModal visible={modal} setVisible={setModal}>
                    <div>
                        <div className="news-content">
                            <img src={news.image} alt="Изображение" />

                            <strong>{news.title}</strong>

                            <div>{news.content}</div>
                        </div>

                        {props.user.role === "ADMIN" && (
                            <button
                                className="footer-button-module-admin"
                                onClick={handleDeleteNews}
                            >
                                Удалить
                            </button>
                        )}

                        <div>
                            <Like
                                like={like}
                                setLike={setLike}
                                newsId={props.news.id}
                            />

                            <Comments
                                user={props.user}
                                comments={comments}
                                create={handleCreateComment}
                            />
                        </div>
                    </div>
                </MyModal>
            </div>
        </div>
    );
};

export default NewsItem;
