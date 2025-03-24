import React, { useState, useMemo } from "react";
import Pagination from "./Pagination";

const Comments = ({ user, comments, create }) => {
    const [comment, setComment] = useState("");
    const [currentPage, setCurrentPage] = useState(1);
    const itemsPerPage = 3;
    const totalPages = Math.ceil(comments.length / itemsPerPage);

    const paginatedComments = useMemo(() => {
        const startIndex = (currentPage - 1) * itemsPerPage;
        const endIndex = startIndex + itemsPerPage;
        return comments.slice(startIndex, endIndex);
    }, [comments, currentPage, itemsPerPage]);

    const handleCreateComment = (e) => {
        e.preventDefault();

        if (comment.trim()) {
            create({
                content: comment,
            });
            
            setComment("");
        }
    };

    return (
        <div className="comment-container">
            {user.role !== "ADMIN" && (
                <form onSubmit={handleCreateComment} className="comment-form">
                    <input
                        value={comment}
                        onChange={(e) => setComment(e.target.value)}
                        type="text"
                        placeholder="Введите комментарий"
                        className="comment-input"
                    />

                    <button type="submit" className="comment-button">
                        Добавить комментарий
                    </button>
                </form>
            )}

            <h1 className="comment-h1">Обсуждение</h1>

            <ul className="comment-list">
                {paginatedComments.map((comment) => (
                    <li key={comment.id} className="comment-item">
                        <span className="comment-author">{comment.author}</span>
                        <p className="comment-content">{comment.content}</p>
                    </li>
                ))}
            </ul>

            <Pagination
                currentPage={currentPage}
                onPageChange={setCurrentPage}
                totalPages={totalPages}
            />
        </div>
    );
};

export default Comments;
