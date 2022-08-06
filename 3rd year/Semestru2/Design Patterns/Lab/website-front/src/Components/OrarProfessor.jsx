import "../CSS/Orar.css";
import NavBar from "../Components/Navbar.jsx";
import A from "../CSS/App.module.css"
import {useEffect, useState} from "react";
import styled, {css} from "styled-components"

const TableRow = styled.tr`
    min-height: 100px;
    width: 100%;

    td{
    ${props => props.isSeminary ? css`background-color: #93c47d;` : null}
    ${props => props.isLecture ? css`background-color: #e06666` : null}
    ${props => props.isLaboratory ? css`background-color: #ffd966` : null}
    }
`


export default function OrarProfessor() {
    const [a, setA] = useState([])
    const days = {
        "MONDAY": "Luni",
        "TUESDAY": "Marti",
        "WEDNESDAY": "Miercuri",
        "THURSDAY": "Joi",
        "FRIDAY": "Vineri"
    }
    const activityTypes =
        {
            "Lecture": "Curs",
            "Seminary": "Seminar",
            "Laboratory": "Laborator"
        }
    useEffect(async () => {
        await getData()
    }, [])


    const getData = async () => {
        fetch('http://localhost:8080/api/timetable/professor/499ed492-ed77-459f-bf1a-402baaee1822', {
            method: 'GET',
        }).then(r => r.json()).then(data => setA(data))
    }

    console.log(a)
    return (
        <div className={A.App}>
            <NavBar/>

            <div id="table-container">
                {Object.keys(days).map((key, idx) => {
                    const hours = a.filter(timeTableData => timeTableData.day === key)
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
                                    isSeminary={hour.activityType === "Seminary"}
                                    isLecture={hour.activityType === "Lecture"}
                                    isLaboratory={hour.activityType === "Laboratory"}
                                >
                                    <td>{hour.courseName}</td>
                                </TableRow>
                                <TableRow
                                    isSeminary={hour.activityType === "Seminary"}
                                    isLecture={hour.activityType === "Lecture"}
                                    isLaboratory={hour.activityType === "Laboratory"}
                                >
                                    <td>{hour.startTime} ONLINE {hour.endTime}</td>
                                </TableRow>
                            </>

                        })
                        }
                        </tbody>
                    </table>
                })}

                <div id="Legenda-div">
                    <label id="Legenda-label">Legenda</label>
                    <label id="Curs-label">
                        <span id="Red-dot"></span> Curs
                    </label>
                    <label id="Seminar-label">
                        <span id="Green-dot"></span> Seminar
                    </label>
                    <label id="Laborator-label">
                        <span id="Yellow-dot"></span> Laborator
                    </label>
                </div>
            </div>
        </div>
    );
}
