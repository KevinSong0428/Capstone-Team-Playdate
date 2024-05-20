import React, { useState, useLayoutEffect, useRef, useEffect } from "react";
import gsap from "gsap";
import "./styles.css";
import Posts from "./Posts";

export default function Home({ searchTerm }) {
    let timer = null;
    let elems = useRef([]);
    let timeline = gsap.timeline({
        defaults: {
            duration: 0.9,
            ease: "power3.inOut",
        },
        paused: true,
    });

    const gallery = [
        {
            title: "Save an animal",
            cover:
                "https://ahs.nyc3.cdn.digitaloceanspaces.com/live/public/styles/scale_width_960/public/media/image/2021-06/dog-wearing-collar-tilting-head-16_9.jpg?itok=RUrP_DQa",
        },
        {
            title: "Report lost pets",
            cover:
                "https://reginahumanesociety.ca/app/uploads/2021/01/FoundPet.jpg",
        },
        {
            title: "Help a neighbor",
            cover:
                "https://cdn.discordapp.com/attachments/1167573430388924557/1238269053437607978/Screenshot_2024-05-09_at_4.18.25_PM.png?ex=663eab6c&is=663d59ec&hm=bf3f4da6eab0c0fc2c56ffc18cc5e0df2b35878ee9e339d03cb80d61380eb21b&",
        },
        {
            title: "Be a friend",
            cover:
                "https://images.unsplash.com/photo-1530281700549-e82e7bf110d6?q=80&w=1976&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        },
    ];

    const [state, setState] = useState({ current: 0, next: 1 });
    const [userDetected, setUserDetected] = useState(false);

    const activateTimer = () => {
        timer = setTimeout(() => {
            stepForward();
        }, 2000);
    };

    const calculateIndexs = (index) => {
        if (index === gallery.length - 1) {
            setState({ current: index, next: 0 });
        } else {
            setState({ current: index, next: index + 1 });
        }
    };

    const flowUp = (onComplete) => {
        timeline
            .to(elems.current[0], { y: "-100%", opacity: 0, scale: -0.5 })
            .to(
                elems.current[1],
                {
                    y: "-100%",
                    opacity: 1,
                    scale: 1,
                    onComplete,
                },
                "-=0.75"
            )
            .play();
    };

    const fadeOut = (onComplete) => {
        timeline
            .to(elems.current[0], {
                duration: 0.5,
                opacity: 0,
                onComplete,
            })
            .to(elems.current[0], { opacity: 0 })
            .play();
    };

    const handleChange = (index) => {
        if (index !== state.current) {
            clearTimeout(timer);
            setUserDetected(true);
            fadeOut(() => calculateIndexs(index));
        }
    };

    const stepForward = () => {
        setUserDetected(false);
        flowUp(() => calculateIndexs(state.next));
    };

    const displayCarousel = () => {
        return (
            <div className='album-container'>
                <div className='image'>
                    <img
                        ref={(elem) => (elems.current[0] = elem)}
                        src={gallery[state.current].cover}
                        alt=''
                    />
                    <p>{gallery[state.current].title}</p>
                </div>
                <div className='image'>
                    <img
                        ref={(elem) => (elems.current[1] = elem)}
                        src={gallery[state.next].cover}
                        alt=''
                    />
                    <p>{gallery[state.next].title}</p>
                </div>
                <div className='stripes'>
                    {gallery.map((_item, index) =>
                        index === state.current ? (
                            <span
                                key={`stripe${index}`}
                                // onClick={() => handleChange(index)}
                                style={{ opacity: 1 }}
                            ></span>
                        ) : (
                            <span
                                key={`stripe${index}`}
                                // onClick={() => handleChange(index)}
                                style={{ opacity: 0.2 }}
                            ></span>
                        )
                    )}
                </div>
            </div>
        )
    }

    useLayoutEffect(() => {
        const image1 = !!elems.current[0] && elems.current[0];
        const image2 = !!elems.current[1] && elems.current[1];

        activateTimer();

        gsap.set(image2, { y: "0%", opacity: 0, scale: 1 });
        if (userDetected) {
            gsap.set(image1, { y: "0%", opacity: 0, scale: 1 });
        } else {
            gsap.set(image1, { y: "0%", opacity: 1, scale: 1 });
        }

        return () => {
            clearTimeout(timer);
        };
    }, [state]);

    return (
        <div className='Home'>
            <div className="carousel-container">
                {searchTerm ? "" : displayCarousel()}
            </div>
            <div className="home-container">
                <Posts searchTerm={searchTerm} />
            </div>
        </div>
    );
}
