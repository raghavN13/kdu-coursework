import React, { createContext, useContext, useState } from "react";

interface Item {
    id: number;
    name: string;
}

interface ItemContextType {
    items: Item[];
    filteredItems: Item[];
    addItem: (name: string) => void;
    deleteItem: (id: number) => void;
    setSearchText: (text: string) => void;
}

const ItemContext = createContext<ItemContextType | undefined>(undefined);

export const useItemContext = () => {
    const context = useContext(ItemContext);
    if (!context) {
        throw new Error("There is no context like this present ");
    }

    return context;
}

export interface IItemProviderProps {
    children: React.ReactNode;
}

export const ItemProvider = ({ children }: IItemProviderProps) => {
    const [items, setItems] = useState<Item[]>([
        {
            id: 1,
            name: "Item 1",
        },
        {
            id: 2,
            name: "Item 2",
        },
    ]);

    const [searchText, setSearchText] = useState<string>("");

    const addItem = (name: string) => {
        setItems(prevItems => {
            const newItem: Item = {
                id: prevItems.length + 1,
                name: name,
            };
            return [...prevItems, newItem];
        });
    }

    const deleteItem = (id: number) => {
        setItems(prevItems => prevItems.filter(item => item.id !== id));
    }

    const filteredItems = items.filter(item => item.name.toLowerCase().includes(searchText.toLowerCase()));

    return (
        <ItemContext.Provider value={{ items, filteredItems, addItem, deleteItem, setSearchText }}>
            {children}
        </ItemContext.Provider>
    );
}
