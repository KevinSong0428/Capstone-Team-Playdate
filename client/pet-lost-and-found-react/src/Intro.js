import gsap from "gsap";
import "./Intro.css"
import { Link } from "react-router-dom";
function Intro(){

    // gsap.registerEffect({
    //     name: "fade",
    //     defaults: {duration: 2}, //defaults get applied to the "config" object passed to the effect below
    //     effect: (targets, config) => {
    //         return gsap.to(targets, {duration: config.duration, opacity: 0});
    //     }
    // });

    // //now we can use it like this:
    // //gsap.effects.fade(".box");

    // document.querySelectorAll(".box").forEach(function(box) {
    //   box.addEventListener("mouseenter", function() {
    //     gsap.effects.fade(this);
    //   });
    // });

    return(
        <>
        <div className="container-intro-page">
            <div className="box-intro">

           <div>
            <h1>Lost Paws</h1>
           </div>

            <div className="intro-details">
                <h3>Find and Report Lost & Found Pets.</h3>
                <h3>Alert people in Your Area </h3> <h3>Notify the Rescue Sqad for Free!</h3>
            </div>

            <div className="intro-img-ct">
                <div className="card-intro-img">
                <img src="https://cdn.discordapp.com/attachments/1167573430388924557/1238298939317288960/Screenshot_2024-05-09_at_6.17.24_PM.png?ex=663ec741&is=663d75c1&hm=1075f215675e9dc3507a9d62e8ba87e3b42e3bfc931d8cbd29a9914dc8172fd7&"/>
                </div>
            </div>
            </div>
            <div className="bottom-ct">
                <div className="bottom-ct-txt">
                <h3>What can you do to help?</h3>
                <h3>Find a lost pet? </h3><h3>Take Action now and fill out a report</h3>
</div>
<div className="intro-btns-ct">
              <div>

                <Link to="/posts/add" className="nav-link btn btn-danger">
                        Make A Report
                    </Link>
                </div>
                <div >
                    <Link to="/posts" className="btn btn-browse">Browse lost pet</Link>
                </div>
</div>

            </div>
        </div>
        </>
    )
}

export default Intro;
