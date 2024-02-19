import React from "react";
import "./App.css";
import { ProfileContainer } from "./containers/ProfileContainer";
import { HobbiesContainer } from "./containers/HobbiesConatiner";
import { SkillsContainer } from "./containers/SkillsContainer";

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
      <ProfileContainer
        name={profileData.name}
        fullName={profileData.fullName}
        qualification={profileData.qualification}
      >
        <div className="small-container">
          <HobbiesContainer hobbies={profileData.hobbies} /> {/* HobbiesContainer inside ProfileContainer */}
        </div>
        <div className="small-container">
          <SkillsContainer skills={profileData.skills} /> {/* SkillsContainer inside ProfileContainer */}
        </div>
      </ProfileContainer>
    </div>
  );
}

export default App;
