import { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';


const postDefault = {
    animal: {
        name: "",
        characteristic: "",
        animal: "",
        breed: ""
    },
    user: {
        name: "",
        email: "",
        phoneNumber: ""
    },
    location: {
        address: "",
        city: "",
        state: "",
        zipCode: ""
    },
    url: "",
    description: "",
    dateTime: new Date().toISOString(),
    gender: "",
    size: 0,
    found: false
};


function PostForm() {
    const [post, setPost] = useState(postDefault);
    const [animal, setAnimal] = useState(postDefault.animal);
    const [user, setUser] = useState(post.user);
    const [location, setLocation] = useState(postDefault.location);
    const [posts, setPosts] = useState([]);
    const [currentView, setCurrentView] = useState('List');
    const [errors, setErrors] = useState([]);
    const [users, setUsers] = useState([]);
    const [locations, setLocations] = useState([]);
    const [inputTouched, setInputTouched] = useState(false);

    const animalUrl = 'http://localhost:8080/api/animal';
    const userUrl = 'http://localhost:8080/api/user';
    const locationUrl = 'http://localhost:8080/api/location';
    const postUrl = 'http://localhost:8080/api/post';

    const navigate = useNavigate();

    const { id } = useParams();

    useEffect(() => {
        fetch("http://localhost:8080/api/user")
            .then(response => {
                if (response.status === 200) {
                    return response.json();
                } else {
                    return Promise.reject(`Unexpected status code: ${response.status}`);
                }
            })
            .then(data => setUsers(data)) // here we are setting our data to our state variable
            .catch(console.log);
    }, []);

    useEffect(() => {
        fetch({ locationUrl })
            .then(response => {
                if (response.status === 200) {
                    return response.json();
                } else {
                    return Promise.reject(`Unexpected status code: ${response.status}`);
                }
            })
            .then(data => setLocations(data)) // here we are setting our data to our state variable
            .catch(console.log);
    }, []);

    useEffect(() => {
        if (id) {
            fetch(`${postUrl}/${id}`)
                .then(response => {
                    if (response.status === 200) {
                        return response.json();
                    } else {
                        return Promise.reject(`Unexpected status code: ${response.status}`);
                    }
                })
                .then(data => {
                    setPost(data);
                })
                .catch(console.log);
        }
    }, [id])


    const handleChange = (event) => {
        const { name, value, type, checked } = event.target;
        const [key1, nestedKey] = name.split('.');

        if (key1 && nestedKey) {
            setPost(post => ({
                ...post,
                [key1]: {
                    ...post[key1],
                    [nestedKey]: type === 'checkbox' ? checked : value
                }
            }));
        } else {
            setPost(post => ({
                ...post,
                [name]: type === 'checkbox' ? checked : value
            }));
        }
    };

    const addAnimal = async () => {
        console.log("Creating animal");
        const init = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(post.animal)
        };
        try {
            const response = await fetch(animalUrl, init);
            const data = await response.json();

            if (!response.ok) {
                throw new Error(`Failed to create animal: ${response.status}`);
            }

            if (!data.animalId) {
                setErrors(data);
            } else {
                console.log(data);
                post.animal.animalId = data.animalId;
            }
        } catch (error) {
            console.error('Error creating animal:', error);
        }
    }

    const addUser = async () => {
        console.log("Creating user");
        const init = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(post.user)
        };
        try {
            const response = await fetch(userUrl, init);
            const data = await response.json();

            if (!response.ok) {
                throw new Error(`Failed to create user: ${response.status}`);
            }

            if (!data.userId) {
                setErrors(data);
            } else {
                console.log(data);
                post.user.userId = data.userId;
            }
        } catch (error) {
            console.error('Error creating user:', error);
        }
    }

    const addLocation = async () => {
        console.log("Creating location");
        const init = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(post.location)
        };
        try {
            const response = await fetch(locationUrl, init);
            const data = await response.json();

            if (!response.ok) {
                throw new Error(`Failed to create location: ${response.status}`);
            }

            if (!data.locationId) {
                setErrors(data);
            } else {
                console.log(data);
                post.location.locationId = data.locationId;
            }
        } catch (error) {
            console.error('Error creating location:', error);
        }
    }

    const addPost = async () => {
        console.log("Creating post")
        const init = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(post)
        };
        try {
            const response = await fetch(postUrl, init);
            const data = await response.json();

            if (!response.ok) {
                throw new Error(`Failed to create post: ${response.status}`);
            }

            if (data.id) {
                navigate('/');
            } else {
                setErrors(data);
            }
        } catch (error) {
            console.error('Error creating post:', error);
        }
    }

    const updatePost = () => {

    }
    const handleSubmit = async (e) => {
        e.preventDefault();
        if (id) {
            updatePost();
        } else {
            try {
                await addAnimal();
                await addUser();
                await addLocation();
                await addPost();
            } catch (error) {
                console.error('Error submitting post:', error);
            }
        }
    }

    const handleBlur = () => {
        setInputTouched(true);
    }

    const resetState = () => {
        console.log(`...resertting the state...`);
        setPost(postDefault);
        setCurrentView('List');
    }

    return (
        <>
            <main className="container">
                <h2 className='mb-4'>{id > 0 ? 'Update Post' : 'Add Post'}</h2>
                {errors.length > 0 && (
                    <div className="alert alert-danger">
                        <p>The following errors were found:</p>
                        <ul>
                            {errors.map(error => (
                                <li key={error}>{error}</li>
                            ))}
                        </ul>
                    </div>
                )}
                <form onSubmit={handleSubmit}>
                    <fieldset>
                        <legend>Animal Information</legend>
                        <div className='form-group'>
                            <label htmlFor='animal.name'>Animal Name: </label>
                            <input
                                id='animal.name'
                                name='animal.name'
                                type='text'
                                className='form form-control'
                                value={post.animal.name || ''}
                                onChange={handleChange}
                            />
                        </div>
                        <div className='form-group'>
                            <label htmlFor='animal.characteristic'>Animal Characteristics: </label>
                            <input
                                id='animal.characteristic'
                                name='animal.characteristic'
                                type='text'
                                className={`form form-control`}
                                value={post.animal.characteristic || ''}
                                onChange={handleChange}
                            />
                        </div>
                        <div className='form-group'>
                            <label htmlFor='animal.animal'>Animal Type: </label>
                            <input
                                id='animal.animal'
                                name='animal.animal'
                                type='text'
                                className={`form form-control`}
                                value={post.animal.animal || ''}
                                onChange={handleChange}
                            />
                        </div>
                        <div className='form-group'>
                            <label htmlFor='animal.breed'>Animal Breed: </label>
                            <input
                                id='animal.breed'
                                name='animal.breed'
                                type='text'
                                className={`form form-control`}
                                value={post.animal.breed || ''}
                                onChange={handleChange}
                            />
                        </div>
                    </fieldset>

                    <fieldset>
                        <legend>User Information</legend>
                        <div className='form-group'>
                            <label htmlFor='user.name'>User Name: </label>
                            <input
                                id='user.name'
                                name='user.name'
                                type='text'
                                className={`form form-control`}
                                value={post.user.name || ''}
                                onChange={handleChange}
                            />
                        </div>
                        <div className='form-group'>
                            <label htmlFor='user.email'>User Email: </label>
                            <input
                                id='user.email'
                                name='user.email'
                                type='text'
                                className={`form form-control`}
                                value={post.user.email || ''}
                                onChange={handleChange}
                            />
                        </div>
                        <div className='form-group'>
                            <label htmlFor='user.phoneNumber'>User Phone Number: </label>
                            <input
                                id='user.phoneNumber'
                                name='user.phoneNumber'
                                type='text'
                                className={`form form-control`}
                                value={post.user.phoneNumber || ''}
                                onChange={handleChange}
                            />
                        </div>
                    </fieldset>

                    <fieldset>
                        <legend>Location Information</legend>
                        <div className='form-group'>
                            <label htmlFor='location.address'>Address Pet was found: </label>
                            <input
                                id='location.address'
                                name='location.address'
                                type='text'
                                className={`form form-control`}
                                value={post.location.address || ''}
                                onChange={handleChange}
                            />
                        </div>
                        <div className='form-group'>
                            <label htmlFor='location.city'>City: </label>
                            <input
                                id='location.city'
                                name='location.city'
                                type='text'
                                className={`form form-control`}
                                value={post.location.city || ''}
                                onChange={handleChange}
                            />
                        </div>
                        <div className='form-group'>
                            <label htmlFor='location.state'>State: </label>
                            <input
                                id='location.state'
                                name='location.state'
                                type='text'
                                className={`form form-control`}
                                value={post.location.state || ''}
                                onChange={handleChange}
                            />
                        </div>
                        <div className='form-group'>
                            <label htmlFor='location.zipCode'>Zip: </label>
                            <input
                                id='location.zipCode'
                                name='location.zipCode'
                                type='text'
                                className={`form form-control`}
                                value={post.location.zipCode || ''}
                                onChange={handleChange}
                            />
                        </div>
                    </fieldset>

                    <fieldset>
                        <legend>Post Information</legend>

                        <div className='form-group'>
                            <label htmlFor='url'>Image Url: </label>
                            <input
                                id='url'
                                name='url'
                                type='text'
                                className='form form-control'
                                value={post.url}
                                onChange={handleChange}
                            />
                        </div>
                        <div className='form-group'>
                            <label htmlFor='description'>Description: </label>
                            <input
                                id='description'
                                name='description'
                                type='text'
                                className={`form form-control`}
                                value={post.description || ''}
                                onChange={handleChange}
                            />
                        </div>
                        <div className='form-group'>
                            <label htmlFor='dateTime'>Select date time</label>
                            <input
                                id='dateTime'
                                name='dateTime'
                                type='datetime-local'
                                className={`form form-control ${inputTouched && post.dateTime === '' ? 'invalid' : ''}`}
                                value={post.dateTime || ''}
                                onChange={handleChange}
                                onBlur={handleBlur}
                            />
                        </div>
                        <div className='form-group'>
                            <label htmlFor='gender'>Gender</label>
                            <div className='form-check'>
                                <input
                                    id='female'
                                    name='gender'
                                    type='radio'
                                    className='form-check-input'
                                    value='female'
                                    checked={post.gender === 'female'}
                                    onChange={handleChange}
                                />
                                <label htmlFor='female' className='form-check-label'>female</label>
                            </div>

                            <div className='form-check'>
                                <input
                                    id='male'
                                    name='gender'
                                    type='radio'
                                    className='form-check-input'
                                    value='male'
                                    checked={post.gender === 'male'}
                                    onChange={handleChange}
                                />
                                <label htmlFor='male' className='form-check-label'>male</label>
                            </div>

                            <div className='form-check'>
                                <input
                                    id='unknown'
                                    name='gender'
                                    type='radio'
                                    className='form-check-input'
                                    value='unknown'
                                    checked={post.gender === 'unknown'}
                                    onChange={handleChange}
                                />
                                <label htmlFor='unknown' className='form-check-label'>unknown</label>
                            </div>
                        </div>

                        <div className='form-group'>
                            <label htmlFor='size'>Weight (lb)</label>
                            <input
                                id='size'
                                name='size'
                                type='number'
                                className='form form-control'
                                value={post.size}
                                onChange={handleChange}
                            />
                        </div>

                        <div className='form-group'>
                            <div className='form-check'>
                                <input
                                    id='found'
                                    name='found'
                                    type='checkbox'
                                    className='form-check-input'
                                    checked={post.found}
                                    onChange={handleChange}
                                />
                                <label htmlFor='found' className='form-check-label'>Found</label>
                            </div>

                        </div>
                    </fieldset>

                    <div className='mt-4'>
                        <button className='btn btn-success mr-2' type="submit">{updatePost > 0 ? 'Update Post' : 'Add Post'}</button>
                        <button className='btn btn-warning' type="button" onClick={resetState}>Cancel</button>
                    </div>
                </form>
            </main>
        </>
    )
}

export default PostForm;
