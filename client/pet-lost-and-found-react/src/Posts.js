import { useEffect, useRef, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import gsap from "gsap";
import Modal from "./Modal";
import Post from "./Post";
import "./Posts.css";

export default function Posts() {
    const [posts, setPosts] = useState([]);
    const postRefs = useRef(new Map());
    const url = "http://localhost:8080/api/post";
    const navigate = useNavigate();


    const [showModal, setShowModal] = useState(false);
    const [selectedPostId, setSelectedPostId] = useState(null);


    //fetch data
    useEffect(() => {
        fetch(url)
            .then((response) => {
                if (response.status === 200) {
                    return response.json();
                } else {
                    return Promise.reject(`Unexpected status code: ${response.status}`);
                }
            })
            .then((data) => setPosts(data)) // here we are setting our data to our state variable
            .catch(console.log);
    }, []);

    const handleDelete = (postId) => {
        const post = posts.find((post) => post.id === postId);
        console.log("post to delelete: ===>", post);

        if (post && window.confirm(`Delete Post: ${post.animal.name}`)) {
            const element = postRefs.current.get(postId);
            console.log(".....element to animate", element);
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
                            throw new Error(`Unexpected Status Code: ${response.status}`);
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

    const formatDateTime = (dateTimeString) => {
        const date = new Date(dateTimeString);
        return date.toLocaleString('en-US', {
            month: 'long',
            day: '2-digit',
            year: 'numeric',
            hour: '2-digit',
            minute: '2-digit',
            hour12: true
        });

    };

    return (
        <>
            <div className="container mt-5">
                <div className="row">
                    <h2 className="flex-grow">All Posts</h2>
                </div>
                <div className="row">
                    {posts.map(post => (
                        <div key={post.id} className="col-lg-4 mb-4" ref={el => postRefs.current.set(post.id, el)} >

                            <div className="card">
                                <div className="card-bod" onClick={() => handleOpenModal(post.id)} style={{ cursor: 'pointer' }}>
                                    <h5 className="card-title">{post.found ? "FOUND" : "MISSING"} {post.animal.animal.toUpperCase()} </h5>

                            </div>

                                    <p className="card-text">

                                        <strong>Name: </strong>{post.animal.name ? post.animal.name : "No Tag"}<br />
                                        <strong>Breed: </strong>{post.animal.breed ? post.animal.breed : "???"}<br />
                                        <strong>Description: </strong>{post.description.length > 20 ? post.description.substring(0, 20) + "..." : post.description}<br />
                                        <strong>Time Found: </strong>{formatDateTime(post.dateTime)}<br />
                                        <strong>Contact {post.user.name} at: </strong><br />{post.user.phoneNumber} <br />{post.user.email}<br />

                                        <strong><br />Stuff to add to modal:<br /> Weight (lb): </strong>{post.size} <br /> <strong>Gender: </strong>{post.gender}<br />
                                    </p>
                                </div>

                                <Link className="btn btn-primary btn-sm" to={`/posts/edit/${post.id}`}>Edit</Link>
                                <button className='btn btn-danger btn-sm delete-btn' onClick={() => handleDelete(post.id)}>Delete</button>
                            </div>
         </div>
                <Modal show={showModal} onClose={handleCloseModal}>
                {selectedPostId && <Post postId={selectedPostId} />}
            </Modal>
            </div>
                    ))}


                        

        </>
    );
}
