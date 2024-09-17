import React from "react";
import "./SkillsContainer.css"


interface Skills{
  id : number;
  skill : string;
}

interface SkillsContainerProps {
  skills:Skills[];

}

export function SkillsContainer({ skills }: SkillsContainerProps) {
  return (
    <div className="skills">
      <h2>Skills</h2>
      <ul>
        {skills.map((skill) => (
          <li key={skill.id}>{skill.skill}</li>
        ))}
      </ul>
    </div>
  );
}
