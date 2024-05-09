import { useState, useEffect } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

function Post({ postId }) {
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

  const post = posts.find((post) => post.id === postId);
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

  return (
    <>
      {post && (
        <>
          <h5 className='card-title card-title-modal'>
            {post.found ? "FOUND" : "LOST"}: {post.animal.name}{" "}
          </h5>
          <div className='black-border'>border</div>

          <div className='container mt-5 main-modal-card'>
            <div className='card-bod card-bod-main'>
              <div className='img-p-container'>
                <div className='modal-img-container'>
                  <img
                    src={post.url}
                    onError={(e) => {
                      e.currentTarget.src =
                        "https://t3.ftcdn.net/jpg/02/82/41/14/360_F_282411446_ISWNhmJm0no0eN5tUvCZk8LepeOxourx.jpg";
                    }}
                    alt={`This is a picture of a ${post.animal.animal}: ${post.animal.characteristic}`}
                  />
                </div>
                <div className='p-div'>
                  <p className='modal-pt'>
                    A little bit more about {post.animal.name}...
                  </p>
                  <p>
                    This pet is a {post.animal.breed} {post.animal.animal}. If
                    you see this pet, just know typically some of their
                    characteristics are {post.animal.characteristic}. Below is
                    more information that can help you find or locate this pet.
                  </p>
                </div>
              </div>
              <div className='lower-card'>
                <div className='contact'>
                  <div className='contact-div'>
                    <div className='phone-icon-container'>
                      <i class='fa-solid fa-phone'></i>
                    </div>
                    <h5>{post.user.phoneNumber}</h5>
                  </div>

                  <div className='contact-div'>
                    <div className='phone-icon-container'>
                      <i class='fa-solid fa-location-dot'></i>
                    </div>
                    <h5>
                      {post.location.address}
                      <br />
                      {post.location.city}, {post.location.state}
                    </h5>
                  </div>
                  <div className='modal-edit-ct'>
                    <Link
                      className='btn btn-primary btn-sm btn-edit-modal'
                      to={`/posts/edit/${post.id}`}
                    >
                      Edit
                    </Link>
                  </div>
                </div>

                <div>
                  <i class='fa-solid fa-paw fa-paw0'></i>
                  <i class='fa-solid fa-paw fa-paw1'></i>
                  <i class='fa-solid fa-paw fa-paw2'></i>
                  <i class='fa-solid fa-paw fa-paw3'></i>
                  <p className='card-text card-text-modal'>
                    {" "}
                    <div className='img-container'></div>
                    <strong>Description: </strong>
                    {post.description}
                    <br />
                    <strong>Weight (lb): </strong>
                    {post.size}
                    <br />
                    <strong>Breed: </strong>
                    {post.animal.breed}
                    <br />
                    <strong>Species: </strong>
                    {post.animal.animal}
                    <br />
                    <strong>Characteristic: </strong>
                    {post.animal.characteristic}
                    <br />
                    <strong>Time Found: </strong>
                    {post.dateTime}
                    <br />
                    <strong>City </strong>
                    {post.location.state}
                    <br />
                    <strong>City: </strong>
                    {post.location.city}
                    <br />
                    <strong>Zip Code: </strong>
                    {post.location.zipCode}
                    <br />
                    <strong>Gender: </strong>
                    {post.gender}
                    <br />
                  </p>
                </div>
              </div>
            </div>
          </div>
        </>
      )}
    </>
  );
}
export default Post;
