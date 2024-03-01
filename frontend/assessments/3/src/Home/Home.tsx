import React, { useEffect, useState } from 'react'; 
import { useDispatch, useSelector } from 'react-redux';
import { AppDispatch, RootState } from '../redux/Store';
import { loadRoomsAndNotify } from '../redux/RoomSlice';
import { RoomInfo } from '../RoomInto/RoomInfo';

export default function Home() {
    const reduxDispatch: AppDispatch = useDispatch();
    const slackMessage = useSelector((state:RootState) => state.slack.message);
    const isVisible = useSelector((state:RootState) => state.slack.isVisible);

    useEffect(() => {
        // Dispatch the thunk to load rooms and notify
        reduxDispatch(loadRoomsAndNotify());
    }, [reduxDispatch]); // Make sure to include reduxDispatch in the dependency array


    return (
      <div>
        <RoomInfo/>
        {isVisible && <div>{slackMessage}</div>}
      </div>
    )
}
