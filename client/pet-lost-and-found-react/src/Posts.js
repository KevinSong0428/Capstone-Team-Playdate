import { useEffect, useRef, useState } from "react";
import { Link } from "react-router-dom";
import gsap from "gsap";
import Modal from "./Modal";
import Post from "./Post";
import "./Posts.css";

export default function Posts({ searchTerm }) {
    const [posts, setPosts] = useState([]);
    const [filteredPosts, setFilteredPosts] = useState([]);
    const postRefs = useRef(new Map());
    const url = "http://localhost:8080/api/post";

    const [showModal, setShowModal] = useState(false);
    const [selectedPostId, setSelectedPostId] = useState(null);

    //fetch data
    useEffect(() => {
        fetch(url)
            .then((response) => {
                if (response.status === 200) {
                    return response.json();
                } else {
                    return Promise.reject(
                        `Unexpected status code: ${response.status}`
                    );
                }
            })
            .then((data) => setPosts(data)) // here we are setting our data to our state variable
            .catch(console.log);
    }, []);

    useEffect(() => {
        if (posts.length > 0) {
            const filteredPosts = posts.filter((post) => {
                return (
                    post.animal.name
                        .toLowerCase()
                        .includes(searchTerm.toLowerCase()) ||
                    post.animal.breed
                        .toLowerCase()
                        .includes(searchTerm.toLowerCase()) ||
                    post.animal.animal
                        .toLowerCase()
                        .includes(searchTerm.toLowerCase())
                );
            });
            setFilteredPosts(filteredPosts);
        }
    }, [searchTerm, posts]);

    const handleDelete = (postId) => {
        const post = posts.find((post) => post.id === postId);

        if (post && window.confirm(`Delete Post: ${post.animal.name}`)) {
            const element = postRefs.current.get(postId);
            if (element) {
                gsap.to(element, {
                    duration: 0.5,
                    opacity: 0,
                    x: -100,
                    ease: "power1.inOut",
                    onComplete: () => {
                        setPosts((prevPosts) =>
                            prevPosts.filter((post) => post.id !== postId)
                        );
                    },
                });
                const init = { method: "DELETE" };
                fetch(`${url}/${postId}`, init)
                    .then((response) => {
                        if (response.status === 204) {
                            // This is handled by the onComplete of the animation
                        } else {
                            throw new Error(
                                `Unexpected Status Code: ${response.status}`
                            );
                        }
                    })
                    .catch(console.error);
            }
        }
    };

    const handleOpenModal = (postId) => {
        setSelectedPostId(postId);
        setShowModal(true);
    };

    const handleCloseModal = () => {
        setShowModal(false);
        setSelectedPostId(null);
    };

    const formatDateTime = (dateTimeString) => {
        const date = new Date(dateTimeString);
        return date.toLocaleString("en-US", {
            month: "long",
            day: "2-digit",
            year: "numeric",
            hour: "2-digit",
            minute: "2-digit",
            hour12: true,
        });
    };

    const displayPost = (postArg) => {
        return (
            <div className='row'>
                {postArg.map((post) => (
                    <div
                        key={post.id}
                        className='col-lg-4 mb-4'
                        ref={(el) => postRefs.current.set(post.id, el)}
                    >
                        <div className='card'>
                            <div
                                className='card-bod'
                                onClick={() => handleOpenModal(post.id)}
                                style={{ cursor: "pointer" }}
                            >
                                <h5 className='card-title'>
                                    {post.found ? "FOUND" : "MISSING"}{" "}
                                    {post.animal.animal.toUpperCase()}{" "}
                                </h5>
                                <div className='img-container'>
                                    <img
                                        src={post.url}
                                        onError={(e) => {
                                            e.currentTarget.src =
                                                "https://t3.ftcdn.net/jpg/02/82/41/14/360_F_282411446_ISWNhmJm0no0eN5tUvCZk8LepeOxourx.jpg";
                                        }}
                                        alt={`${post.animal.animal}: ${post.animal.characteristic}`}
                                    />
                                </div>
                                <p className='card-text'>
                                    <strong>Name: </strong>
                                    {post.animal.name
                                        ? post.animal.name
                                        : "No Tag"}
                                    <br />
                                    <strong>Breed: </strong>
                                    {post.animal.breed
                                        ? post.animal.breed
                                        : "???"}
                                    <br />
                                    <strong>Description: </strong>
                                    {post.description.length > 20
                                        ? post.description.substring(0, 20) +
                                          "..."
                                        : post.description}
                                    <br />
                                    <strong>Time Found: </strong>
                                    {formatDateTime(post.dateTime)}
                                    <br />
                                    <strong>
                                        Contact {post.user.name} at:{" "}
                                    </strong>
                                    <br />
                                    {post.user.phoneNumber} <br />
                                    {post.user.email}
                                    <br />
                                </p>
                            </div>
                            <div className='button-container mb-4'>
                                <Link
                                    className='edit btn btn-primary btn-sm'
                                    to={`/posts/edit/${post.id}`}
                                >
                                    Edit
                                </Link>
                                <button
                                    className='delete btn btn-danger btn-sm delete-btn'
                                    onClick={() => handleDelete(post.id)}
                                >
                                    Delete
                                </button>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        );
    };

    return (
        <>
            <div className='home-container'>
                <h2
                    className='flex-grow d-flex justify-content-center mt-2'
                    style={{ color: "black", textAlign: "center" }}
                >
                    All Posts
                </h2>
                <div className='container mt-2'>
                    <div className='row justify-content-center'></div>
                    {searchTerm
                        ? displayPost(filteredPosts)
                        : displayPost(posts)}
                    <Modal
                        show={showModal}
                        onClose={handleCloseModal}
                    >
                        {selectedPostId && <Post postId={selectedPostId} />}
                    </Modal>
                </div>
            </div>
        </>
    );
}
