// App.tsx
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'; // Import Routes
import ScrollToTop from './ScrollToTop';
import FocusOnFirstInput from './FocusOnFirstInput';
import TimerApplication from './TimerApplication';

const App: React.FC = () => {
  return (
    <Router>
      <ScrollToTop />
      <Routes> {/* Wrap Routes around Route components */}
        <Route path="/focus" element={<FocusOnFirstInput />} /> {/* Use element prop */}
        <Route path="/timer" element={<TimerApplication />} /> {/* Use element prop */}
        <Route path="/" element={<Home />} /> {/* Use element prop */}
      </Routes>
    </Router>
  );
};

const Home = () => {
  return (
    <div>
      <h1>Welcome to My App</h1>
      <p>Scroll down to see the ScrollToTop button in action</p>
      <p>Visit /focus to see the FocusOnFirstInput component</p>
      <p>Visit /timer to see the TimerApplication component</p>
    </div>
  );
};

export default App;
