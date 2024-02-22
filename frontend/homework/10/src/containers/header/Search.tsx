import { useState } from "react";
import { IState } from "../State";

interface ISearchProp{
    search : IState;
}

interface ISearchProps {
search :IState[]
}
export function Search({ search }: ISearchProp) {
    const [searchText, setSearchText] = useState('');
  
    function handleSearch(event: React.ChangeEvent<HTMLInputElement>) {
      const searchValue = event.target.value.toLowerCase();
      setSearchText(searchValue);
      const filteredItems = search.listitems.filter(item => item.name.toLowerCase().startsWith(searchValue));
      search.state(filteredItems);
      console.log(filteredItems);
      
    }
  
    return (
        <>
          <input
            type="search"
            name="search"
            id="search"
            placeholder="Search Items"
            value={searchText}
            onChange={handleSearch}
          />
          {search.listitems.length === 0 && searchText !== '' && <p>No Match found</p>}
        </>
      );
  }