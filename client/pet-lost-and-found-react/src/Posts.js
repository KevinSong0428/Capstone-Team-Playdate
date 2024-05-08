import { useEffect, useRef, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import gsap from 'gsap';
import "./Posts.css"

export default function Posts() {
    const [posts, setPosts] = useState([]);
    const postRefs = useRef(new Map());

    const url = "http://localhost:8080/api/post";

    const navigate = useNavigate();

    //fetch data
    useEffect(() => {
        fetch(url)
            .then(response => {
                if (response.status === 200) {
                    return response.json();
                } else {
                    return Promise.reject(`Unexpected status code: ${response.status}`);
                }
            })
            .then(data => {
                console.log(data); // Add this line to see what data structure is being returned
                setPosts(data);
            }) // here we are setting our data to our state variable
            .catch(console.log);
    }, []);

    const handleDelete = (postId) => {
        const post = posts.find(post => post.id === postId);
        console.log("post to delelete: ===>", post)

        if (post && window.confirm(`Delete Post: ${post.animal.name}`)) {
            const element = postRefs.current.get(postId);
            console.log(".....element to animate", element)
            if (element) {
                gsap.to(element, {
                    duration: 0.5,
                    opacity: 0,
                    x: -100,
                    ease: "power1.inOut",
                    onComplete: () => {
                        setPosts(prevPosts => prevPosts.filter(post => post.id !== postId));
                    }
                });
                const init = { method: "DELETE" };
                fetch(`${url}/${postId}`, init)
                .then(response => {
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
                                <div className="card-bod">
                                    <h5 className="card-title">{post.found ? "FOUND" : "LOST"}: {post.animal.name} </h5>
                                    <div className="img-container">
                                        <img src={post.url} alt={`${post.animal.animal}: ${post.animal.characteristic}`} />
                                    </div>
                                    <p className="card-text">
                                        <div className="img-container">
                                        </div>
                                        <strong>Description: </strong>{post.description}<br />
                                        <strong>Found: </strong>{post.dateTime}<br />
                                        <strong>Gender: </strong>{post.gender}<br />
                                        <strong>Contact me at: </strong>{post.user.phoneNumber} or {post.user.email}<br />
                                        <strong>{post.user.name}</strong>
                                    </p>
                                </div>
                            <button className='btn btn-danger btn-sm delete-btn' onClick={()=> handleDelete(post.id)}>Delete</button>
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        </>
    )
}
