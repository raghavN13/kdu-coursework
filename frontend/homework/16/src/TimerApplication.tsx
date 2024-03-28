// TimerApplication.tsx
import React, { useState, useEffect } from 'react';

const TimerApplication: React.FC = () => {
  const [seconds, setSeconds] = useState(0);

  useEffect(() => {
    const intervalId = setInterval(() => {
      setSeconds((prevSeconds) => prevSeconds + 1);
    }, 1000);

    return () => clearInterval(intervalId);
  }, []);

  return (
    <div>
      <h1>Timer Application</h1>
      <p>Seconds Elapsed: {seconds}</p>
    </div>
  );
};

export default TimerApplication;
