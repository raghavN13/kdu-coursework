import React, { useEffect, useRef } from 'react';
import { useLocation } from 'react-router-dom';

const ScrollToTop: React.FC = () => {
  const { pathname } = useLocation();
  const ref = useRef<HTMLDivElement>(null);

  useEffect(() => {
    if (ref.current) {
      ref.current.scrollTo(0, 0);
    }
  }, [pathname]);

  const scrollToTop = () => {
    window.scrollTo({ top: 0, behavior: 'smooth' });
  };

  return (
    <div ref={ref}>
      <div style={{ height: '1000px' }}>this is the page that we need</div>
      <button onClick={scrollToTop} style={{ position: 'fixed', bottom: '20px', right: '20px' }}>
        Scroll to Top
      </button>
    </div>
  );
};

export default ScrollToTop;
