import { useState } from "react";
import { Link } from "react-router-dom";

function Navbar({ onSearch }) {
  const [searchTerm, setSearchTerm] = useState("");

  const handleInputChange = (event) => {
    setSearchTerm(event.target.value);
  };

  const handleSearch = () => {
    onSearch(searchTerm);
  };

  return (
    <header className="navbar">
      <div className="navbar-section">
        <Link to="/" className="navbar-brand">
          <img
            src="https://cdn.discordapp.com/attachments/1167573430388924557/1237979456996638730/Screenshot_2024-05-08_at_9.02.43_PM.png"
            alt="Lost Paws Logo"
            style={{ width: "50px" }}
          />
          <h1>Lost Paws</h1>
        </Link>
      </div>

      <div className="navbar-section search-bar">
        <input
          className="form-control"
          type="search"
          placeholder="Search"
          aria-label="Search"
          value={searchTerm}
          onChange={handleInputChange}
          onKeyPress={event => event.key === 'Enter' && handleSearch()} 
        />
        <button onClick={handleSearch} className="search-button">Search</button>
      </div>

      <div className="navbar-section">
        <nav>
          <Link to="/posts/add" className="nav-link">
            Make A Report
          </Link>
        </nav>
      </div>
    </header>
  );
}

export default Navbar;
