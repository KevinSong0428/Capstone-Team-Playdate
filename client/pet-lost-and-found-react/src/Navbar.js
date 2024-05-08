import { Link } from "react-router-dom";

function Navbar(){
    return(
        <>
        <nav>
            <Link to={"/"}>Home</Link>
            <Link to={"/post"}>Posts</Link>
            <Link to={"/post/add"}>Upload a Post</Link>
        </nav>
        </>
    )

}
export default Navbar;
