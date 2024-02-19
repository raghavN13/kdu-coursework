import React from "react";

interface HobbiesContainerProps {
  hobbies: { id: number; hobby: string }[];
}

export function HobbiesContainer({ hobbies }: HobbiesContainerProps) {
  return (
    <div>
      <h2>Hobbies</h2>
      <ul>
        {hobbies.map((hobby) => (
          <li key={hobby.id}>{hobby.hobby}</li>
        ))}
      </ul>
    </div>
  );
}
