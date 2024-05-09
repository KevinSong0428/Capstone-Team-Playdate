import { Link } from "react-router-dom";

function Navbar() {
  return (
    <>
      <section className='nav-container'>
        <nav className='nav'>
          <Link
            className='nav-link'
            to={"/"}
          >
            Home
          </Link>
          <Link
            className='nav-link'
            to={"/post"}
          >
            Posts
          </Link>
          <Link
            className='nav-post'
            to={"/post/add"}
          >
            Make A Report
          </Link>
        </nav>
      </section>
    </>
  );
}
export default Navbar;
