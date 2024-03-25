
import { useItemContext } from "../ItemProvider";

export function Search() {
    const {setSearchText} = useItemContext();

    const handleSearch = (event: React.ChangeEvent<HTMLInputElement>) => {
      const searchText = event.target.value;
      setSearchText(searchText);
  }
  
  
  
    return (
        <>
          <input
            type="search"
            name="search"
            id="search"
            placeholder="Search Items"
            onChange={handleSearch}
          />
          <p></p>
        </>
      );
  }