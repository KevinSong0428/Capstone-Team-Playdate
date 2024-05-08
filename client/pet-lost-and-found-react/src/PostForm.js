import { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';

const postDefault = {
    animal: { animalId: '' },
    user: { userId: '' },
    location: { locationId: '' },
    url: "",
    description: "",
    dateTime: new Date().toISOString(),
    gender: "",
    size: 0,
    found: false
};
function PostForm(){
    const [post, setPost] = useState(postDefault);
    const [posts, setPosts] = useState([]);
    const [currentView, setCurrentView] = useState('List');
    const [errors, setErrors] = useState([]);

    const url = 'http://localhost:8080/api/post';

    const navigate = useNavigate();

    const { id } = useParams();

    useEffect(()=>{
        if(id){
            fetch(`${url}/${id}`)
            .then(response => {
                if(response.status===200){
                    return response.json();
                } else {
                    return Promise.reject(`Unexpected status code: ${response.status}`);
                }
            })
            .then(data=> {
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
    const addPost = () => {
        console.log(post, 'this i spost..........')
        const init = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(post)
        };
        fetch(url, init)
        .then(response =>{
            if(response.status === 201 || response.status === 400){
                return response.json();
            }else{
                return Promise.reject(`Unexpected status code: ${response.status}`);
            }
        })
        .then(data => {
            if(data.id){
                navigate('/');
            }else{
                setErrors(data);
            }
        })
        .catch(console.log)
    }

    const updatePost = () =>{

    }
    const handleSubmit = (e) => {
        e.preventDefault();
        if(id){
            updatePost();
        } else {
            addPost();
        }
    }

    const resetState=()=>{
        console.log(`...resertting the state...`);
        setPost(postDefault);
        setCurrentView('List');
    }

    return(
        <>
        <main className="container">
            <h2 className='mb-4'>{id>0? 'Update Post': 'Add Post'}</h2>
            {errors.length>0 && (
                           <div className="alert alert-danger">
                           <p>The following errors were found:</p>
                           <ul>
                               {errors.map(error=>(
                                   <li key={error}>{error}</li>
                               ))}
                           </ul>
                       </div>
            )}
            <form onSubmit={handleSubmit}>

            <fieldset className='form-group'>
    <label htmlFor='animal.animalId'>Animal Id</label>
    <input
        id='animal.animalId'
        name='animal.animalId'
        type='number'
        className='form form-control'
        value={post.animal.animalId || ''}
        onChange={handleChange}
    />
</fieldset>



<fieldset className='form-group'>
    <label htmlFor='user.userId'>User Id</label>
    <input
        id='user.userId'
        name='user.userId'
        type='number'
        className='form form-control'
        value={post.user.userId || ''}
        onChange={handleChange}
    />
</fieldset>


                <fieldset className='form-group'>
                    <label htmlFor='url'>Url Image</label>
                    <input
                        id='url'
                        name='url'
                        type='text'
                        className='form form-control'
                        value={post.url}
                        onChange={handleChange}
                    />
                </fieldset>

                <fieldset className='form-group'>
                    <label htmlFor='description'>Please provide a description</label>
                    <input
                        id='description'
                        name='description'
                        type='text'
                        className='form form-control'
                        value={post.description}
                        onChange={handleChange}
                    />
                </fieldset>

                <fieldset className='form-group'>
                    <label htmlFor='dateTime'>Select date time</label>
                    <input
                        id='dateTime'
                        name='dateTime'
                        type='datetime-local'
                        className='form form-control'
                        value={post.dateTime}
                        onChange={handleChange}
                    />
                </fieldset>


                <fieldset className='form-group'>
    <label htmlFor='location.locationId'>Location Id</label>
    <input
        id='location.locationId'
        name='location.locationId'
        type='number'
        className='form form-control'
        value={post.location.locationId || ''}
        onChange={handleChange}
    />
</fieldset>


                <fieldset className='form-group'>
                    <label htmlFor='gender'>Gender</label>
                    <div className='form-check'>
                           <input
                        id='female'
                        name='gender'
                        type='radio'
                        className='form-check-input'
                        value='female'
                        checked={post.gender==='female'}
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
                        checked={post.gender==='male'}
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
                        checked={post.gender==='unknown'}
                        onChange={handleChange}
                    />
                    <label htmlFor='unknown' className='form-check-label'>unknown</label>
                    </div>
                </fieldset>

                <fieldset className='form-group'>
                    <label htmlFor='size'>Size</label>
                    <input
                        id='size'
                        name='size'
                        type='number'
                        className='form form-control'
                        value={post.size}
                        onChange={handleChange}
                    />
                </fieldset>

                <fieldset className='form-group'>
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

                </fieldset>

                <div className='mt-4'>
                <button className='btn btn-success mr-2' type="submit">{updatePost > 0 ? 'update post' : 'Add Post.'}</button>
                <button className='btn btn-warning' type="button" onClick={resetState}>Cancel</button>
                </div>
            </form>
        </main>
        </>
    )
}

export default PostForm;
