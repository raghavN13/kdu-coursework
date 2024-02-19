import React from "react";

interface SkillsContainerProps {
  skills: { id: number; skill: string }[];
}

export function SkillsContainer({ skills }: SkillsContainerProps) {
  return (
    <div>
      <h2>Skills</h2>
      <ul>
        {skills.map((skill) => (
          <li key={skill.id}>{skill.skill}</li>
        ))}
      </ul>
    </div>
  );
}
