import React, { useEffect, useState } from 'react';
import { ApiQuote } from './types/quotes.types';
import './App.scss';
import { Quote } from './Quotes/Quote';
import TagFilter from './Filter/TagFilter';

function App() {
  const [allQuotes, setAllQuotes] = useState<ApiQuote[]>([]);
  const [quotes, setQuotes] = useState<ApiQuote[]>([]);
  const [selectedTags, setSelectedTags] = useState<string[]>([]);
  const [isLoading, setIsLoading] = useState<boolean>(false); 

  useEffect(() => {
    fetchData(3);
  }, []);

  useEffect(() => {
    setQuotes(
      allQuotes.filter((quote) => {
        return selectedTags.length === 0 || quote.tags.some(tag => selectedTags.includes(tag));
      })
    );
  }, [selectedTags, allQuotes]);

  const fetchData = (count: number) => {
    setIsLoading(true); 
    fetch(`https://api.quotable.io/quotes/random?limit=${count}`)
      .then((response) => response.json())
      .then((data: ApiQuote[]) => {
        setAllQuotes([...allQuotes, ...data]);
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
      })
      .finally(() => {
        setIsLoading(false); 
      });
  };

  const fetchNewQuote = () => {
    fetchData(1);
  };

  const handleClickTag = (tag:string) => {
    setSelectedTags(prevTags => {
      if (!prevTags.includes(tag)) {
        return [...prevTags, tag];
      }
      return prevTags;
    });
  }
  
  const handleRemoveTag = (tagToRemove: string) => {
    setSelectedTags(prevTags => prevTags.filter(tag => tag !== tagToRemove));
  };

  return (
    // <div className='container'>
    <div className='container'>

      <button onClick={fetchNewQuote} disabled={isLoading} id='new-quote'>New Quote</button> 
      {isLoading && <div className="spinner"></div>} 

      <div className="filter">
        <div className="filter-name">Filter</div>
      <TagFilter tags={selectedTags} handleRemoveTag={handleRemoveTag} />
      </div>
      <div className="quotes">
        <div className="line"></div>
      {quotes.map((quote) => (
        <Quote key={quote._id} quote={quote} handleClickTag={handleClickTag}/>
      ))}

      </div>
      </div>
    // </div>
  );
}

export default App;
