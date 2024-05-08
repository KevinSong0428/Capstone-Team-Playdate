import './App.css';
import React from 'react';
import Home from './Home';
import { BrowserRouter as Router } from "react-router-dom";
import { Routes, Route } from "react-router-dom";
import Posts from './Posts';
import PostForm from './PostForm';
import Navbar from './Navbar';

function App() {
    return (
        <Router>
            <Navbar/>
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/post/add" element={<PostForm />} />
                <Route path="/post" element={<Posts />} />
            </Routes>
        </Router>
    );
}

export default App;
