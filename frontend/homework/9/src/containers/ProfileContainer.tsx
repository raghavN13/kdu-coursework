import React from "react";
import "./ProfileContainer.css";

interface ProfileContainerProps {
  name: string;
  fullName: string;
  qualification: string;
}

export function ProfileContainer({ name, fullName, qualification }: ProfileContainerProps) {
  return (
    <div className="profile-container">
      <div className="profile-details">
        <p>Name: {name}</p>
        <p>Full Name: {fullName}</p>
        <p>Qualification: {qualification}</p>
      </div>
      <div className="small-containers">
        {/* Hobbies and Skills containers go here */}
      </div>
    </div>
  );
}
