// FocusOnFirstInput.tsx
import React, { useEffect, useRef } from 'react';

const FocusOnFirstInput: React.FC = () => {
  const inputRef = useRef<HTMLInputElement>(null);

  useEffect(() => {
    if (inputRef.current) {
      inputRef.current.focus();
    }
  }, []);

  return (
    <div>
      <input type="text" ref={inputRef} />
      <input type="text"/>
      <input type="text" />
    </div>
  );
};

export default FocusOnFirstInput;
