import React from "react";
import NewsItem from "./NewsItem";

const NewsList = ({ user, news, title, deleteNews }) => {
    if (!news.length) {
        return <h1>Новости не найдены!</h1>;
    }

    return (
        <div className="news-list">
            <h1 className="news-h1">{title}</h1>

            {news.map((news, index) => (
                <NewsItem
                    deleteNews={deleteNews}
                    user={user}
                    number={index + 1}
                    news={news}
                    key={news.id}
                />
            ))}
        </div>
    );
};

export default NewsList;
