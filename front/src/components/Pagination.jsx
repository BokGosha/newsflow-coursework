import React, { useMemo } from "react";

const Pagination = ({ currentPage, onPageChange, totalPages }) => {
    const pages = useMemo(() => {
        const pagesArray = [];

        for (let i = 1; i <= totalPages; i++) {
            pagesArray.push(i);
        }

        return pagesArray;
    }, [totalPages]);

    return (
        <div className="pagination">
            {totalPages !== 0 && (
                <>
                    <button
                        onClick={() => onPageChange(currentPage - 1)}
                        disabled={currentPage === 1}
                    >
                        ← Назад
                    </button>

                    {pages.map((page) => (
                        <button
                            key={page}
                            onClick={() => onPageChange(page)}
                            className={currentPage === page ? "active" : ""}
                        >
                            {page}
                        </button>
                    ))}

                    <button
                        onClick={() => onPageChange(currentPage + 1)}
                        disabled={currentPage === totalPages}
                    >
                        Вперёд →
                    </button>
                </>
            )}
        </div>
    );
};

export default Pagination;
