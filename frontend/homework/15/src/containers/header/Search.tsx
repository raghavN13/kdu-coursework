// components/Search.tsx
import React, { useEffect } from "react";
import { useDispatch} from "react-redux";
import { setSearchText,clearSearchText} from "../../redux/TodoSlice";


export function Search() {
  const dispatch = useDispatch();

  const handleSearch = (event: React.ChangeEvent<HTMLInputElement>) => {
    const searchText = event.target.value;
    dispatch(setSearchText(searchText));
  };

  useEffect(() => {
    dispatch(clearSearchText());
  }, [dispatch]); 


  return (
    <>
      <input
        type="search"
        name="search"
        id="search"
        placeholder="Search Items"
        onChange={handleSearch}
      />
    </>
  );
}
