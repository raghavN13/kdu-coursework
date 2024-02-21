// TagFilter.tsx
import React from 'react';
import './TagFilter.scss';

interface TagFilterProps {
  tags: string[];
  handleRemoveTag: (tag: string) => void;
}

const TagFilter: React.FC<TagFilterProps> = ({ tags, handleRemoveTag }) => {
  return (
    <div className="tag-filter-container">
      {tags.map((tag, index) => (
        <div key={index} className="tag-filter">
          <div className='tag-content'>
          <div>{tag}</div>
          <button onClick={() => handleRemoveTag(tag)}>X</button>
          </div>
        </div>
      ))}
    </div>
  );
};

export default TagFilter;
