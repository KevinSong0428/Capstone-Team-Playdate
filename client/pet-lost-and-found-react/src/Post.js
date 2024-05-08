import { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';


function Post({postId}){

    const [posts, setPosts] = useState([]);
    const url = "http://localhost:8080/api/post";

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

        const post = posts.find(post=>post.id===postId);
    // const [post, setPost] = useState(null);
    // const url = "http://localhost:8080/api/post";

    // const { id } = useParams();


    // useEffect(() => {
    //     fetch(`${url}/${postId}`)
    //         .then((response) => {
    //             if (response.status === 200) {
    //                 return response.json();
    //             } else {
    //                 return Promise.reject(`Unexpected status code: ${response.status}`);
    //             }
    //         })
    //         .then((data) => {
    //             console.log(data)
    //             setPost(data)
    //         }
    //         ) // here we are setting our data to our state variable
    //         .catch(console.log);
    // }, [postId]);


    return(
        <>
        {post &&
        <div>
            <h1>{post.animal.name}</h1>
            <img src={post.url}/>
            <p>{post.description}</p>
        </div>}
        </>
    )
}
export default Post;
