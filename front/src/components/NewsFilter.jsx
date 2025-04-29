import MySelect from "../UI/select/MySelect";

const NewsFilter = ({ user, filter, setFilter }) => {
    return (
        <div className="filter-container">
            <input
                value={filter.query}
                onChange={(e) =>
                    setFilter({ ...filter, query: e.target.value })
                }
                placeholder="Поиск..."
                className="filter-input"
            />
            <MySelect
                value={filter.sort}
                onChange={(selectedSort) =>
                    setFilter({ ...filter, sort: selectedSort })
                }
                defaultValue="Сортировка"
                options={[
                    { value: "title", name: "По названию" },
                    { value: "content", name: "По описанию" },
                ]}
            />
            {user && user.role !== "ADMIN" && (
                <div className="filter-checkbox">
                    <input
                        type="checkbox"
                        id="filter-checkbox"
                        checked={filter.showOnlyUser}
                        onChange={(e) =>
                            setFilter({
                                ...filter,
                                showOnlyUser: e.target.checked,
                            })
                        }
                        className="filter-checkbox-input"
                    />
                    <label
                        htmlFor="filter-checkbox"
                        className="filter-checkbox-label"
                    >
                        Показывать только мои новости
                    </label>
                    <input
                        type="checkbox"
                        id="filter-checkbox"
                        checked={filter.showOnlyLiked}
                        onChange={(e) =>
                            setFilter({
                                ...filter,
                                showOnlyLiked: e.target.checked,
                            })
                        }
                        className="filter-checkbox-input"
                    />
                    <label
                        htmlFor="filter-checkbox"
                        className="filter-checkbox-label"
                    >
                        Показывать только понравившиеся новости
                    </label>
                </div>
            )}
        </div>
    );
};

export default NewsFilter;
