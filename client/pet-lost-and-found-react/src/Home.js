import React, { useState, useLayoutEffect, useRef } from "react";
import gsap from "gsap";
import "./styles.css";
import Posts from "./Posts";

export default function Home() {
  let timer = null;
  let elems = useRef([]);
  let timeline = gsap.timeline({
    defaults: {
      duration: 0.75,
      ease: "power3.inOut",
    },
    paused: true,
  });

  const gallery = [
    {
      title: "Save an animal",
      cover:
        "https://images.unsplash.com/photo-1587300003388-59208cc962cb?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8OHx8ZG9nfGVufDB8fDB8fHww",
    },
    {
      title: "Report lost pets",
      cover:
        "https://images.unsplash.com/photo-1583337130417-3346a1be7dee?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTV8fGRvZ3xlbnwwfHwwfHx8MA%3D%3D",
    },
    {
      title: "Help a neighbor",
      cover:
        "https://images.unsplash.com/photo-1551969014-7d2c4cddf0b6?q=80&w=2115&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    },
    {
      title: "Be a friend",
      cover:
        "https://images.unsplash.com/photo-1530281700549-e82e7bf110d6?q=80&w=1976&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    },
  ];

  const [state, setState] = useState({ current: 0, next: 1 });
  const [userDetected, setUserDetected] = useState(false);

  /**
   * @TODO
   * create a state that holds two values [ current: number, next: number]
   * create an event handler that set the state the current number
   * and calculate what will be the next value
   */

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

  // console.log(state);

  return (
    <div className='Home'>
      <h2>Home Page</h2>
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
      <Posts />
    </div>
  );
}
