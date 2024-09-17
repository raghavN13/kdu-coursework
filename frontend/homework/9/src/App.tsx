import React from "react";
import "./App.css";
import ProfileContainer  from "./containers/Profile/ProfileContainer";


function App() {
  const profileData = {
    "name": "Amey",
    "fullName": "Amey Aditya",
    "qualification": "SSE",
    "skills": [
      {
        "id": 1,
        "skill": "Python"
      },
      {
        "id": 2,
        "skill": "React"
      }
    ],
    "hobbies": [
      {
        "id": 1,
        "hobby": "Cricket"
      }
    ]
  };

  return (
    <div className="App">
      <ProfileContainer details = {profileData}/>

    </div>
  );
}

export default App;
