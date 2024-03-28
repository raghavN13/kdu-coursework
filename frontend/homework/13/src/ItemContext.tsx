import { createContext, useContext, useEffect, useState } from "react";
import { IProduct } from "./Product.type";


interface ItemContextType{
    items : IProduct[];
    filteredItems : IProduct[];
    setSearchTerm: (term: string) => void;
    setFilterCategory: (category: string) => void;
    setSortBy: (sort: string) => void;
}

const ItemContext = createContext<ItemContextType | undefined>(undefined);

export const useItemContext = () =>{
    const context  = useContext(ItemContext);
    if(!context){
        throw new Error("No Context!");
    }

    return context;
}
export interface IItemProviderProps {
    children: React.ReactNode;
}

export const ItemProvider = ({ children }: IItemProviderProps) => {
    const [items, setItems] = useState<IProduct[]>([]);
    const [filteredItems, setFilteredItems] = useState<IProduct[]>([]);
    const [searchTerm, setSearchTerm] = useState<string>('');
    const [filterCategory, setFilterCategory] = useState<string>('');
    const [sortBy, setSortBy] = useState<string>('');
  
    useEffect(() => {
      const fetchData = () => {
        fetch(`https://fakestoreapi.com/products`)
          .then((response) => response.json())
          .then((data: IProduct[]) => {
            setItems(data);
            setFilteredItems(data);
          })
          .catch((error) => {
            console.error("Error fetching data:", error);
          });
      };
  
      fetchData();
    }, []); // Empty dependency array to run the effect only once
  
    useEffect(() => {
      let filtered = [...items];
      setFilteredItems(items);
  
      if (searchTerm !== '') {
        filtered = filtered.filter(item =>
          item.title.toLowerCase().startsWith(searchTerm.toLowerCase())
        );
      }
  
      if (filterCategory !== '') {
        filtered = filtered.filter(item => item.category === filterCategory);
      }
  
      filtered.sort((a, b) => {
        if (sortBy === 'asc') {
          return a.price-b.price;
        } else if (sortBy === 'desc') {
          return b.price-a.price;
        }
        return 0;
      });
  
      setFilteredItems(filtered);
    }, [items, searchTerm, filterCategory, sortBy]);
  
    return (
      <ItemContext.Provider
        value={{
          items,
          filteredItems,
          setSearchTerm,
          setFilterCategory,
          setSortBy,
        }}
      >
        {children}
      </ItemContext.Provider>
    );
  };
  