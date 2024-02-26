// components/Search.tsx
import React from "react";
import { useDispatch} from "react-redux";
import { setSearchText} from "../../redux/TodoSlice";

export function Search() {
  const dispatch = useDispatch();

  const handleSearch = (event: React.ChangeEvent<HTMLInputElement>) => {
    const searchText = event.target.value;
    dispatch(setSearchText(searchText));
  };


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
