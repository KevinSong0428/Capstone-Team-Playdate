import { NavLink } from "react-router-dom";
import "./Footer.css"
function Footer() {

    return (
        <>
            <div className="footer-div">
                <h5>Contributors: </h5>
                <div className="all-contacts">


                    <div className="contact1">
                        <div>
                            Vanessa Gonzalez
                        </div>
                        <a href="https://github.com/vxg026" target="_blank">
                            <div className="git-hub-container">
                                <i className="fa-brands fa-github"></i>
                                <h6>vxg026</h6>
                            </div>
                        </a>
                        <a href="https://github.com/vxg026" target="_blank">
                            <div className="git-hub-container">
                                <i className="fa-brands fa-linkedin"></i>
                                <h6>vanessa-gonzalez-82667a1b3/</h6>
                            </div>
                        </a>
                    </div>


                    <div className="contact1">
                        <div>
                            Kevin Song
                        </div>
                        <a href="https://github.com/KevinSong0428" target="_blank">
                            <div className="git-hub-container">
                                <i className="fa-brands fa-github"></i>
                                <h6>KevinSong0428</h6>
                            </div>
                        </a>
                        <a href="https://www.linkedin.com/in/kevinsong0428/" target="_blank">
                            <div className="git-hub-container">
                                <i className="fa-brands fa-linkedin"></i>
                                <h6>LinkedInKevinSong</h6>
                            </div>
                        </a>
                    </div>


                    <div className="contact1">
                        <div>
                            Brandon Lee
                        </div>
                        <a href="https://github.com/BrandonW-Lee" target="_blank">
                            <div className="git-hub-container">
                                <i className="fa-brands fa-github"></i>
                                <h6>BrandonLee</h6>
                            </div>
                        </a>
                        <a href="https://github.com/vxg026" target="_blank">
                            <div className="git-hub-container">
                                <i className="fa-brands fa-linkedin"></i>
                                <h6>LinkedInBrandonLee</h6>
                            </div>
                        </a>
                    </div>


                </div>
            </div>
        </>
    )
}
export default Footer;
