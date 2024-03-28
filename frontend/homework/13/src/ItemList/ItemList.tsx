import { useNavigate } from "react-router-dom";
import { useItemContext } from "../ItemContext";
import "./Items.css"

export const ItemList = () => {
  const { filteredItems } = useItemContext();
  const navigate = useNavigate();

  const handleItemClick = (itemId: number) => {
    navigate(`/item/${itemId}`);
  };

  return (
    <div className="main-container">

    <div className="kdu-store">
      <p>KDU MARKETPLACE</p>
    </div>
    <div className="items-container">
      {filteredItems.map((item) => (
        <div key={item.id} className="item" onClick={() => handleItemClick(item.id)}>
          <img src={item.image} alt={item.title} />
          <div className="show">
          <h3 className="title">{item.title}</h3>
          <p className="price">${item.price}</p>
          </div>
          <p className="description">{item.description}</p>
          <p className="category">Category: {item.category}</p>
        </div>
      ))}
    </div>
    </div>
  );
};
