import React, { useRef, useState } from "react";
import NewsService from "../services/NewsService";

const NewsForm = ({ create }) => {
    const [news, setNews] = useState({ image: null, title: "", content: "" });
    const fileInputRef = useRef(null);

    const addNewNews = async (e) => {
        e.preventDefault();

        if (news.image === null) {
            alert("Выберите изображение для своей новости");

            return;
        }

        const formData = new FormData();
        const jsonBody = {
            title: news.title,
            content: news.content,
        };

        formData.append("json", JSON.stringify(jsonBody));
        formData.append("image", news.image);

        const newNews = await NewsService.addNews(formData);

        create(newNews);

        setNews({ image: null, title: "", content: "" });

        fileInputRef.current.value = "";
    };

    return (
        <form className="news-form">
            <input
                ref={fileInputRef}
                type="file"
                onChange={(e) => setNews({ ...news, image: e.target.files[0] })}
                className="news-input"
            />

            <input
                value={news.title}
                onChange={(e) => setNews({ ...news, title: e.target.value })}
                type="text"
                placeholder="Заголовок новости"
                className="news-input"
            />

            <textarea
                value={news.content}
                onChange={(e) => setNews({ ...news, content: e.target.value })}
                type="text"
                placeholder="Описание новости"
                className="news-text"
            />

            <button onClick={addNewNews} className="news-button">Создать новость</button>
        </form>
    );
};

export default NewsForm;
