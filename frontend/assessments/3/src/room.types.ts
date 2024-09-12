// Define types for add-ons
interface AddOn {
    name: string;
    cost: string;
    currency: string;
  }
  
  // Define types for room
  export interface Room {
    id: number;
    name: string;
    costPerNight: string;
    currency: string;
    addOns: AddOn[];
  }
  
  // Define type for the whole data structure
