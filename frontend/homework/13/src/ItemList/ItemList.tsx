import { useNavigate } from "react-router-dom";
import { useItemContext } from "../ItemContext";

export const ItemList = () => {
  const { filteredItems } = useItemContext();
  const navigate = useNavigate();

  const handleItemClick = (itemId: number) => {
    navigate(`/item/${itemId}`);
  };

  return (
    <div className="items-container">
      {filteredItems.map((item) => (
        <div key={item.id} className="item" onClick={() => handleItemClick(item.id)}>
          <img src={item.image} alt={item.title} />
          <h3>{item.title}</h3>
          <p>{item.description}</p>
          <p>Price: ${item.price}</p>
          <p>Category: {item.category}</p>
        </div>
      ))}
    </div>
  );
};
