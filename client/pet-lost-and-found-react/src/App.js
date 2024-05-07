import './App.css';
import React from 'react';
import Home from './Home';
import { BrowserRouter as Router } from "react-router-dom";
import {Routes, Route} from "react-router-dom";

function App() {
  return (
   <Router>
    <Routes>
    <Route path="/" element={<Home/>}/>
    </Routes>
   </Router>
  );
}

export default App;
