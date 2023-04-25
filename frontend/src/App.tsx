import React, {useEffect, useState} from 'react';

import './App.css';
import {SalesData} from "./sales/SalesData";
import {SalesDataTable} from "./sales/SalesDataTable";
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';
import axios from "axios";


function App() {
  const [salesData, setSalesData] = useState<SalesData[]>([]);

  useEffect(() => {
      let stompClient: any = null;
      const connect = () => {
          axios.get(`${process.env.REACT_APP_PRODUCER_ENDPOINT}/sales`).then(r => setSalesData(r.data))
          const socket = new SockJS(`${process.env.REACT_APP_PRODUCER_ENDPOINT}/ws`);
          stompClient = Stomp.over(socket);
          stompClient.connect({}, () => {
              console.log('Connected to WebSocket');
              stompClient.subscribe('/topic/salesData', (message: { body: string; }) => {
                  const newSaleData = JSON.parse(message.body) as [SalesData];
                  setSalesData(newSaleData);
              });
          }, (err: Error) => {
              console.log(err)
          });
      };
      connect();
  }, []);

  return (
    <div className="App">
      <SalesDataTable salesData={salesData} />
    </div>
  );
}

export default App;
