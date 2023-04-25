import React from "react";
import {SalesData} from "./SalesData";

type SalesDataTableProps = {
    salesData: SalesData[];
};

export const SalesDataTable: React.FC<SalesDataTableProps> = ({ salesData }) => {
    return (
        <table>
            <thead>
            <tr>
                <th>Store Name</th>
                <th>Product Name</th>
                <th>Total Units</th>
                <th>Total Revenue</th>
            </tr>
            </thead>
            <tbody>
            {salesData.map((salesDatum) => (
                <tr key={`${salesDatum.storeName}-${salesDatum.productName}`}>
                    <td>{salesDatum.storeName}</td>
                    <td>{salesDatum.productName}</td>
                    <td>{salesDatum.totalUnits}</td>
                    <td>{salesDatum.totalRevenue}</td>
                </tr>
            ))}
            </tbody>
        </table>
    );
};
