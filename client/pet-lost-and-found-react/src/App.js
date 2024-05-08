import './App.css';
import React from 'react';
import Home from './Home';
import { BrowserRouter as Router } from "react-router-dom";
import { Routes, Route } from "react-router-dom";
import Posts from './Posts';
import PostForm from './PostForm';

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="posts/add" element={<PostForm />} />
                <Route path="posts/edit/:id" element={<PostForm />} />
                <Route path="/posts" element={<Posts />} />
            </Routes>
        </Router>
    );
}

export default App;
