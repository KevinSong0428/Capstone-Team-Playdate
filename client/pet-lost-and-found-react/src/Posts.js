import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

export default function Posts() {
    const [posts, setPosts] = useState([]);

    const url = "http://localhost:8080/api/post";

    useEffect(() => {
        fetch(url)
            .then(response => {
                if (response.status === 200) {
                    return response.json();
                } else {
                    return Promise.reject(`Unexpected status code: ${response.status}`);
                }
            })
            .then(data => setPosts(data)) // here we are setting our data to our state variable
            .catch(console.log);
    }, []);

    return (
        <>
            <div className="container mt-5">
                <div className="row">
                    <h2 className="flex-grow">All Posts</h2>
                </div>
                <div className="row">
                    {posts.map(post => (
                        <div key={post.postId} className="col-lg-4 mb-4">
                            <div className="card">
                                <div className="card-bod">
                                    <h5 className="card-title">{post.found ? "FOUND" : "LOST"}: {post.animal.name} </h5>
                                    <p className="card-text">
                                        <strong>Description: </strong>{post.description}<br />
                                        <strong>Found: </strong>{post.dateTime}<br />
                                        <strong>Gender: </strong>{post.gender}<br />
                                        <strong>Contact me at: </strong>{post.user.phoneNumber} or {post.user.email}<br />
                                        <strong>{post.user.name}</strong>
                                    </p>
                                </div>
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        </>
    )
}

