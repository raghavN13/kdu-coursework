import React, { useState, useEffect } from 'react';
import { useSelector } from 'react-redux';
import { RootState } from '../redux/Store';
import "./RoomList.scss"

export interface RoomType {
    id: number;
    name: string;
    costPerNight: string;
    currency: string;
    addOns: {
        name: string;
        cost: string;
        currency: string;
    }[];
}

export const RoomList = () => {
    const roomTypes: RoomType[] = useSelector((state: RootState) => state.stocks.rooms.roomTypes);
    const [selectedRoom, setSelectedRoom] = useState<RoomType>();
    const [startDate, setStartDate] = useState<string>('');
    const [endDate, setEndDate] = useState<string>('');
    const [totalCost, setTotalCost] = useState<number>(0);
    const [selectedAddOns, setSelectedAddOns] = useState<string[]>([]);
    const [submitClicked, setSubmitClicked] = useState<boolean>(false);
    const [isValidDateRange, setIsValidDateRange] = useState<boolean>(true);

    useEffect(() => {
        calculateTotalCost();
    }, [startDate, endDate, selectedAddOns]);

    const handleRoomClick = (room: RoomType) => {


        if (selectedRoom?.id === room.id) {
            return;
        }

        setSelectedRoom(room);
        setTotalCost(0);
        setStartDate('');
        setEndDate('');
        setSubmitClicked(false);
        setSelectedAddOns([]);
    };

    const handleAddOnSelect = (addOnName: string, addOnCost: string) => {
        const isAlreadySelected = selectedAddOns.includes(addOnName);

        let updatedAddOns: string[];

        if (isAlreadySelected) {

            updatedAddOns = selectedAddOns.filter((addOn) => addOn !== addOnName);
        } else {

            updatedAddOns = [...selectedAddOns, addOnName];
        }

        setSelectedAddOns(updatedAddOns);
    };

    const handleSubmit = () => {
        setSubmitClicked(true);
    };

    const calculateTotalCost = () => {
        let addOnsTotalCost = 0;
        for (const addOn of selectedAddOns) {
            const selectedAddOn = selectedRoom?.addOns.find((a) => a.name === addOn);
            if (selectedAddOn) {
                addOnsTotalCost += parseInt(selectedAddOn.cost);
            }
        }

        if (selectedRoom && startDate && endDate) {
            const start = new Date(startDate);
            const end = new Date(endDate);

            if (end < start) {
                setIsValidDateRange(false);
                setTotalCost(0);
                return;
            } else {
                setIsValidDateRange(true);
            }

            const nights = Math.ceil((end.getTime() - start.getTime()) / (1000 * 3600 * 24));
            const baseCost = parseInt(selectedRoom.costPerNight) * nights;
            const gst = 0.18 * baseCost;
            const total = baseCost + gst + addOnsTotalCost;
            setTotalCost(total);
        }
    };

    return (
        <div className="main-container">
            <p className='select-room-type'>Select Room Type</p>
            <div className="items-container">
                <div className="select-room-type-container"></div>
                {roomTypes.map((room: RoomType) => (
                    <div key={room.id} className="item stock-item-header" onClick={() => handleRoomClick(room)}>
                        <div className={`show ${selectedRoom?.id === room.id ? 'selected' : ''}`}>
                            <p className="room-name">{room.name}</p>
                        </div>
                    </div>
                ))}
            </div>
            <div className="select-date">
                <p>Select Dates</p>
            </div>
            <div className="date-selection">
                <label htmlFor="start-date">Arrival Date:</label>
                <input type="date" id="start-date" value={startDate} onChange={(e) => setStartDate(e.target.value)} />
                <label htmlFor="end-date">Departure Date:</label>
                <input type="date" id="end-date" value={endDate} onChange={(e) => setEndDate(e.target.value)} />
            </div>
            {!isValidDateRange && <p className="error-message">End date must be after the start date</p>}
            {selectedRoom && (
                <div className="add-on-selection">
                    <p className='addOn-select'>Select add-ons for {selectedRoom.name}:</p>
                    <div className="add-ons">
                        {selectedRoom.addOns.map((addOn, index) => (
                            <div key={index} className={`add-on ${selectedAddOns.includes(addOn.name) ? 'selected' : ''}`} onClick={() => handleAddOnSelect(addOn.name, addOn.cost)}>
                                {addOn.name}
                            </div>
                        ))}
                    </div>
                </div>
            )}
            <div className="total-cost">
                Total Cost (including GST): ${totalCost.toFixed(2)}
            </div>
            {!submitClicked && (
                <button className="submit-button" onClick={handleSubmit}>Submit</button>
            )}
            {submitClicked && (
                <div className="bill-breakdown">
                    <h3>Bill Breakdown</h3>
                    <p>Room Type: {selectedRoom?.name}</p>
                    <p>Arrival Date: {startDate}</p>
                    <p>Departure Date: {endDate}</p>
                    <p>Base Cost: ${totalCost.toFixed(2)}</p>
                    <p>Selected Add-Ons:</p>
                    <ul>
                        {selectedAddOns.map((addOn, index) => (
                            <li key={index}>
                                {addOn} - ${selectedRoom?.addOns.find((a) => a.name === addOn)?.cost}
                            </li>
                        ))}
                    </ul>
                    <p>GST : ${(0.18 * totalCost).toFixed(2)}</p>
                    <p>Total Cost: ${totalCost.toFixed(2)}</p>
                </div>
            )}
        </div>
    );
};
