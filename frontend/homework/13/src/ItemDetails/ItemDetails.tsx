// ItemDetails.js
import { Link, useParams } from "react-router-dom";
import { useItemContext } from "../ItemContext";


export const ItemDetails = () => {
  const { id } = useParams();
  const { items } = useItemContext();

  // Ensure that id exists and is not undefined
  if (!id) {
    return <div>No ID provided</div>;
  }

  // Parse id as an integer
  const itemId = parseInt(id);

  // Find the item with the matching id
  const item = items.find((item) => item.id === itemId);

  // Handle case where item is not found
  if (!item) {
    return <div>Item not found</div>;
  }

  // Render item details
  return (
    <div>
      <Link to="/home">Back to Home</Link>
      <div className="item-details">
        <img src={item.image} alt={item.title} />
        <h3>{item.title}</h3>
        <p>{item.description}</p>
        <p>Price: ${item.price}</p>
        <p>Category: {item.category}</p>
      </div>
    </div>
  );
};
