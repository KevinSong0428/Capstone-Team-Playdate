import React from "react";

const ExampleCarouselImage = ({ text }) => {
  return (
    <div className='relative h-40 md:h-[400px]'>
      <img
        className='d-block w-100 h-20'
        src={`https://via.placeholder.com/400x100?text=${text}`}
        alt={text}
      />
    </div>
  );
};

export default ExampleCarouselImage;
