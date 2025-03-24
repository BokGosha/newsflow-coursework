import React, { useEffect, useMemo, useState } from "react";
import NewsFilter from "../components/NewsFilter";
import NewsForm from "../components/NewsForm";
import NewsList from "../components/NewsList";
import Pagination from "../components/Pagination";
import { useFetching } from "../hooks/useFetching";
import NewsService from "../services/NewsService";
import UserService from "../services/UserService";
import MyModal from "../UI/modal/MyModal";

function News() {
    const [allNews, setNews] = useState([]);
    const [filter, setFilter] = useState({
        sort: "",
        query: "",
        showOnlyUser: false,
    });
    const [modal, setModal] = useState(false);
    const [currentPage, setCurrentPage] = useState(1);
    const [user, setUser] = useState(localStorage.getItem("user"));
    const itemsPerPage = 3;

    const sortedNews = useMemo(() => {
        if (filter.sort) {
            return [...allNews].sort((a, b) =>
                a[filter.sort].localeCompare(b[filter.sort])
            );
        }
        return allNews;
    }, [filter.sort, allNews]);

    const sortedAndSearchedNews = useMemo(() => {
        setCurrentPage(1);

        return sortedNews.filter((news) => {
            const searchMatch =
                news &&
                news.title &&
                news.title.toLowerCase().includes(filter.query.toLowerCase());

            const userMatch =
                !filter.showOnlyUser || news.author === user.username;

            return searchMatch && userMatch;
        });
    }, [filter.query, filter.showOnlyUser, sortedNews]);

    const totalPages = Math.ceil(sortedAndSearchedNews.length / itemsPerPage);

    const createNews = (news) => {
        setNews([...allNews, news]);

        setModal(false);
    };

    const deleteNews = (news) => {
        const updatedNews = allNews.filter((item) => item.id !== news.id);

        setNews(updatedNews);
    };

    const [fetchNews, isLoading, error] = useFetching(async () => {
        const gottenNews = await NewsService.getNews();

        setNews(gottenNews);
    });

    const fetchCurrentUser = async () => {
        const currentUser = await UserService.getUser();

        localStorage.setItem("user", JSON.stringify(user));

        setUser(currentUser);
    };

    useEffect(() => {
        window.scrollTo(0, 0);
        fetchNews();
        fetchCurrentUser();
    }, []);

    const paginatedNews = useMemo(() => {
        const startIndex = (currentPage - 1) * itemsPerPage;
        const endIndex = startIndex + itemsPerPage;
        return sortedAndSearchedNews.slice(startIndex, endIndex);
    }, [sortedAndSearchedNews, currentPage, itemsPerPage]);

    return (
        <div className="App">
            {user && user.role !== "ADMIN" && (
                <button
                    onClick={() => {
                        setModal(true);
                    }}
                    className="main-button"
                >
                    Поделиться новостью
                </button>
            )}

            <MyModal visible={modal} setVisible={setModal}>
                <NewsForm create={createNews} />
            </MyModal>

            <NewsFilter user={user} filter={filter} setFilter={setFilter} />

            {isLoading ? (
                <div>Loading...</div>
            ) : error ? (
                <div>Error: {error}</div>
            ) : (
                <>
                    <NewsList
                        news={paginatedNews}
                        user={user}
                        title="Новости"
                        deleteNews={deleteNews}
                    />

                    <Pagination
                        currentPage={currentPage}
                        onPageChange={setCurrentPage}
                        totalPages={totalPages}
                    />
                </>
            )}
        </div>
    );
}

export default News;
