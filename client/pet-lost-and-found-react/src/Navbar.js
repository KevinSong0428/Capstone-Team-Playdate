import { useState, useEffect } from "react";
import { Link } from "react-router-dom";

function Navbar({ onSearch }) {
    const [searchTerm, setSearchTerm] = useState("");

    const handleInputChange = (event) => {
        setSearchTerm(event.target.value);
    };

    useEffect(() => {
        onSearch(searchTerm);
    }, [searchTerm, onSearch]);

    const handleKeyPress = (event) => {
        if (event.key === "Enter") {
            onSearch(searchTerm);
        }
    };

    const handleSearchButtonClick = () => {
        onSearch(searchTerm);
    };

    return (
        <header className="navbar">
            <div className="navbar-section">
                <Link to="/" className="navbar-brand">
                    <img
                        className="rounded-circle"
                        src="https://scontent-lga3-2.xx.fbcdn.net/v/t39.30808-6/405011549_821946389937052_8724288446210311245_n.jpg?_nc_cat=105&ccb=1-7&_nc_sid=5f2048&_nc_ohc=sCR5IraClrYQ7kNvgExE73f&_nc_ht=scontent-lga3-2.xx&oh=00_AYAkHy0Xx6UXCj9192tllM0m2CjFzM3ArN5F9HHs_Jb4bA&oe=6642F147"
                        alt="Lost Paws Logo"
                        style={{ width: "50px", height: "50px" }}
                    />
                    <h1>Lost Paws</h1>
                </Link>
            </div>

            <div className="navbar-section search-bar">
                <input
                    className="form-control"
                    type="search"
                    placeholder="Search for your pet..."
                    aria-label="Search"
                    value={searchTerm}
                    onChange={handleInputChange}
                    onKeyPress={handleKeyPress}
                />
                <button
                    onClick={handleSearchButtonClick}
                    className="search-button btn btn-success"
                >
                    Search
                </button>
            </div>

            <div className="navbar-section">
                <nav>
                    <Link to="/posts/add" className="nav-link btn btn-danger">
                        Make A Report
                    </Link>
                </nav>
            </div>
        </header >
    );
}

export default Navbar;
