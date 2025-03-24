import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import AuthService from "../services/AuthService";
import NewsService from "../services/NewsService";

const Home = () => {
    const [allNews, setNews] = useState([]);

    const fetchNews = async () => {
        const gottenNews = await NewsService.getNews();

        setNews(gottenNews.slice(-2));
    };

    useEffect(() => {
        window.scrollTo(0, 0);

        if (AuthService.getToken()) {
            fetchNews();
        }
    }, []);

    return (
        <div className="App">
            {AuthService.getToken() ? (
                <>
                    <header className="hero-container">
                        <h1>Добро пожаловать в ленту новостей!</h1>
                        <p>
                            Здесь вы найдете последние публикации наших
                            пользователей.
                        </p>
                        <Link to="/news" className="btn">
                            Посмотреть все новости
                        </Link>
                    </header>

                    <section className="recent-news">
                        <div className="container">
                            <h2>Недавние публикации</h2>
                            {allNews.map((news) => (
                                <div key={news.id} className="home-container">
                                    <img src={news.image} alt="Изображение" />
                                    <strong>{news.title}</strong>
                                    <div>{news.content}</div>
                                </div>
                            ))}
                        </div>
                    </section>
                </>
            ) : (
                <>
                    <header className="hero-container">
                        <h1>Присоединяйтесь к нашему сообществу!</h1>
                        <p>
                            Чтобы читать и публиковать новости, пожалуйста,
                            войдите или зарегистрируйтесь.
                        </p>
                        <div>
                            <Link to="/auth/login" className="btn">
                                Войти
                            </Link>
                            <Link to="/auth/register" className="btn">
                                Зарегистрироваться
                            </Link>
                        </div>
                    </header>

                    <section className="features">
                        <div className="container">
                            <div className="feature">
                                <i className="fas fa-newspaper"></i>
                                <h3>Свежие новости</h3>
                                <p>
                                    Получайте доступ к актуальным новостям,
                                    опубликованным нашими пользователями.
                                </p>
                            </div>
                            <div className="feature">
                                <i className="fas fa-user-plus"></i>
                                <h3>Станьте частью сообщества</h3>
                                <p>
                                    Регистрация открывает новые возможности для
                                    взаимодействия с контентом.
                                </p>
                            </div>
                            <div className="feature">
                                <i className="fas fa-share-square"></i>
                                <h3>Делитесь своими историями</h3>
                                <p>
                                    Опубликуйте свою первую новость и поделитесь
                                    ею с другими участниками.
                                </p>
                            </div>
                        </div>
                    </section>
                </>
            )}

            <footer className="footer">
                <div className="container">
                    <p>&copy; 2025 Ваша компания. Все права защищены.</p>
                </div>
            </footer>
        </div>
    );
};

export default Home;
