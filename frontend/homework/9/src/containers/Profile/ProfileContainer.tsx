import React from "react";
import "./ProfileContainer.css"
import { HobbiesContainer } from "../Hobby/HobbiesConatiner";
import { SkillsContainer } from "../Skills/SkillsContainer";

interface SkillsContainer {
  id : number;
  skill : string;
}

interface HobbiesContainer{
  id : number;
  hobby : string;
}

interface ProfileContainerProps {
  name: string,
  fullName: string,
  qualification: string,
  skills : SkillsContainer[];
  hobbies : HobbiesContainer[];

}

interface ProfileHolderDetails {
  details : ProfileContainerProps;
}

export default function ProfileContainer({ details }: ProfileHolderDetails) {
  return (
    <div className="profile-container">
      <h1>{details.name}</h1>
      <h3>{details.fullName}</h3>
      <h1>{details.qualification}</h1>

      <div className="child-container">
        <SkillsContainer skills = {details.skills} />
        <HobbiesContainer hobbies  = {details.hobbies}/>
      </div>

    </div>
  );
}

// - 89 +