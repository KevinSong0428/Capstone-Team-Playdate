import './App.css';
import React, { useState } from 'react';
import Home from './Home';
import { BrowserRouter as Router } from "react-router-dom";
import { Routes, Route } from "react-router-dom";
import Posts from './Posts';
import PostForm from './PostForm';
import Navbar from './Navbar';
import Footer from './Footer';
import NotFound from './NotFound';
import Intro from './Intro';

function App() {
    const [searchTerm, setSearchTerm] = useState("");

    const handleSearch = (term) => {
        setSearchTerm(term);
    }

    return (
        <Router>
            <Navbar onSearch={handleSearch} />
            <Routes>
                <Route path="/" element={<Intro/>}/>
                <Route path="/posts" element={<Home searchTerm={searchTerm} />} />
                <Route path="posts/add" element={<PostForm />} />
                <Route path="posts/edit/:id" element={<PostForm />} />
                <Route path="*" element={<NotFound />} />

            </Routes>
            <Footer />
        </Router>
    );
}

export default App;
