import "../CSS/Orar.css";
import NavBar from "../Components/Navbar.jsx";
import A from "../CSS/App.module.css"
import {useEffect, useState} from "react";
import styled, {css} from "styled-components"

const TableRow = styled.tr`
    min-height: 100px;
    width: 100%;

`


export default function Camin() {
    const [a, setA] = useState([])
    const days = {
        "Romulus": "Eu",
        "Vera": "Vera Barloiu",
        "WEDNESDAY": "Coleg",
        "THURSDAY": "Coleg"
    }
    useEffect(async () => {
        await getData()
    }, [])


    const getData = async () => {
        fetch('http://localhost:8080/api/room/bbdb7042-46ec-4a0f-a4f9-07032d985ae9', {
            method: 'GET',
        }).then(r => r.json()).then(data => setA(data))
    }

    console.log(a)
    return (
        <div className={A.App}>
            <NavBar/>

            <div id="table-container">
                {Object.keys(days).map((key, idx) => {
                    const hours = a.filter(timeTableData => timeTableData.firstName === key)
                    console.log(hours)
                    return <table key={idx} className="styled-table">
                        <thead>
                        <TableRow>
                            <th>{days[key]}</th>
                        </TableRow>
                        </thead>
                        <tbody style={{width:"100%"}}>
                        {hours.map(hour => {

                            return <>
                                <TableRow
                                >
                                    <td>{hour.firstName}</td>
                                </TableRow>
                                <TableRow
                                >
                                    <td>{hour.lastName}</td>
                                </TableRow>
                                <TableRow
                                >
                                    <td>{hour.group}</td>
                                </TableRow>
                                <TableRow
                                >
                                    <td>{hour.email}</td>
                                </TableRow>
                                <TableRow
                                >
                                </TableRow>
                            </>

                        })
                        }
                        </tbody>
                    </table>
                })}

            </div>
        </div>
    );
}
