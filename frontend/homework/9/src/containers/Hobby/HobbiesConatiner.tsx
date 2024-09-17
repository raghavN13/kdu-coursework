import React from "react";
import "./HobbiesContainer.css"


interface Hobby {
  id : number;
  hobby : string;
}
interface HobbiesContainerProps{
  hobbies : Hobby[];
}

export function HobbiesContainer({ hobbies }: HobbiesContainerProps) {
  return (
    <div className="hobbies" >
      <h2>Hobbies</h2>
      <ul>
        {hobbies.map((hobby) => (
          <li key={hobby.id}>{hobby.hobby}</li>
        ))}
      </ul>
    </div>
  );
}
